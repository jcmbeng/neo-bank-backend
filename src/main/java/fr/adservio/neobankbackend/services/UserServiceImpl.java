package fr.adservio.neobankbackend.services;

import fr.adservio.neobankbackend.dtos.UserRequest;
import fr.adservio.neobankbackend.dtos.UserResponse;
import fr.adservio.neobankbackend.exceptions.EntityNotFoundException;
import fr.adservio.neobankbackend.models.User;
import fr.adservio.neobankbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    @Override
    public UserResponse findById(Long id) {
        return userRepository.findById(id).map(UserResponse::fromEntityToResponse).orElseThrow(
                ()->new EntityNotFoundException("Impossible de trouver le Un utilisateur avec l'ID " + id)
        );
    }

    @Override
    public void delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setDeleted(true);
            userRepository.save(user);
        }
        new EntityNotFoundException("Impossible de trouver le Un utilisateur avec l'ID " + id);
    }

    @Override
    public UserResponse active(Long id, boolean active) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(active);
            userRepository.save(user);
            return UserResponse.fromEntityToResponse(user);
        }
        else
        {
            new EntityNotFoundException("Impossible de trouver le Un utilisateur avec l'ID " + id);
        }
        return null;
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(UserResponse::fromEntityToResponse)
                .toList();
    }

    @Override
    public UserResponse save(Object frontRequest) {
        UserRequest request = (UserRequest) frontRequest;
        Optional<User> user = userRepository.findById(Long.valueOf(2));
        if(user.isPresent())
        {
            request.setCreatedBy(user.get());
        }
        return UserResponse.fromEntityToResponse(
                userRepository.save(
                        UserRequest.fromRequestToEntity(request)
                )
        );
    }
}

package fr.adservio.neobankbackend.services;

import fr.adservio.neobankbackend.dtos.ClientRequest;
import fr.adservio.neobankbackend.dtos.ClientResponse;
import fr.adservio.neobankbackend.exceptions.EntityNotFoundException;
import fr.adservio.neobankbackend.models.Client;
import fr.adservio.neobankbackend.models.User;
import fr.adservio.neobankbackend.repositories.ClientRepository;
import fr.adservio.neobankbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    @Override
    public ClientResponse findById(Long id) {
        return clientRepository.findById(id).map(ClientResponse::fromEntityToResponse).orElseThrow(
                () -> new EntityNotFoundException("Impossible de trouver le Client avec l'ID " + id)
        );
    }

    @Override
    public void delete(Long id) {
        Optional<Client> clientToSearch = clientRepository.findById(id);
        if (clientToSearch.isPresent()) {
            Client client = clientToSearch.get();
            client.setDeleted(true);
            clientRepository.save(client);
        }
    }

    @Override
    public ClientResponse active(Long id, boolean active) {
        Optional<Client> clientToSearch = clientRepository.findById(id);
        if (clientToSearch.isPresent()) {
            Client client = clientToSearch.get();
            client.setActive(active);
            client = clientRepository.save(client);
            return ClientResponse.fromEntityToResponse(client);
        }
        else
        {
            throw new EntityNotFoundException("Impossible de trouver un client avec l'ID :" + id);
        }
    }



    @Override
    public List<ClientResponse> findAll() {
        return clientRepository.findAll().stream().map(ClientResponse::fromEntityToResponse).toList();
    }

    @Override
    public ClientResponse save(Object frontRequest) {

        ClientRequest request = (ClientRequest) frontRequest;
        Optional<User> user = userRepository.findById(Long.valueOf(2));
        if(user.isPresent())
        {
            request.setCreatedBy(user.get());
        }

        return ClientResponse.fromEntityToResponse(
                clientRepository.save(
                        ClientRequest.fromRequestToEntity(request)
                )
        );
    }
}

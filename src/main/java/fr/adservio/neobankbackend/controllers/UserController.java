package fr.adservio.neobankbackend.controllers;

import fr.adservio.neobankbackend.controllers.apis.UserApi;
import fr.adservio.neobankbackend.dtos.UserRequest;
import fr.adservio.neobankbackend.dtos.UserResponse;
import fr.adservio.neobankbackend.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserServiceImpl accountService;

    @Override
    public ResponseEntity<UserResponse> findById(Long id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @Override
    public void delete(Long id) {
        accountService.delete(id);
    }

    @Override
    public void active(Long id, boolean active) {
        accountService.active(id, active);
    }

    @Override
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @Override
    public ResponseEntity<UserResponse> save(UserRequest request) {
        return ResponseEntity.ok(accountService.save(request));
    }

}

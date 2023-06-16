package fr.adservio.neobankbackend.controllers.apis;

import fr.adservio.neobankbackend.dtos.UserRequest;
import fr.adservio.neobankbackend.dtos.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/neo/v1/users")
public interface UserApi
{
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable(name = "id") Long id);

    @DeleteMapping("/{id}")
    void delete(@PathVariable(name = "id") Long id);

    @PatchMapping("/{id}/{active}")
    void active(@PathVariable(name = "id") Long id,
                @PathVariable(name = "active") boolean active);

    @GetMapping
    ResponseEntity<List<UserResponse>> findAll();

    @PostMapping
    ResponseEntity<UserResponse> save(@RequestBody UserRequest request);



}

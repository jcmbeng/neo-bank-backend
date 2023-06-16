package fr.adservio.neobankbackend.controllers.apis;


import fr.adservio.neobankbackend.dtos.ClientRequest;
import fr.adservio.neobankbackend.dtos.ClientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/neo/v1/clients")
public interface ClientApi
{
    @GetMapping("/{id}")
    ResponseEntity<ClientResponse> findById(@PathVariable(name = "id") Long id);

    @DeleteMapping("/{id}")
    void delete(@PathVariable(name = "id") Long id);

    @PatchMapping("/{id}/{active}")
    void active(@PathVariable(name = "id") Long id,
                @PathVariable(name = "active") boolean active);

    @GetMapping
    ResponseEntity<List<ClientResponse>> findAll();

    @PostMapping
    ResponseEntity<ClientResponse> save(@RequestBody ClientRequest request);


}

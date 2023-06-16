package fr.adservio.neobankbackend.controllers;

import fr.adservio.neobankbackend.controllers.apis.ClientApi;
import fr.adservio.neobankbackend.dtos.ClientRequest;
import fr.adservio.neobankbackend.dtos.ClientResponse;
import fr.adservio.neobankbackend.services.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController implements ClientApi {

    private final ClientServiceImpl clientService;

    @Override
    public ResponseEntity<ClientResponse> findById(Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @Override
    public void delete(Long id) {
        clientService.delete(id);
    }

    @Override
    public void active(Long id, boolean active) {
        clientService.active(id, active);
    }

    @Override
    public ResponseEntity<List<ClientResponse>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @Override
    public ResponseEntity<ClientResponse> save(ClientRequest request) {
        return ResponseEntity.ok(clientService.save(request));
    }

    
}

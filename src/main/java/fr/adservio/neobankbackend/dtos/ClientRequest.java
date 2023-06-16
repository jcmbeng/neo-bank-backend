package fr.adservio.neobankbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.adservio.neobankbackend.contants.ClientType;
import fr.adservio.neobankbackend.models.Client;
import fr.adservio.neobankbackend.models.User;
import fr.adservio.neobankbackend.repositories.UserRepository;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientRequest {

    @Autowired
    private UserRepository userRepository;

    private Long id;

    private ClientType clientType;

    private String lastName;

    private String firstName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String adress;

    private String email;

    private String telephone;

    private List<AccountRequest> accounts;

    @JsonIgnore
    private User createdBy;

    public static Client fromRequestToEntity(final ClientRequest request) {
        if (request == null) {
            return null;
        }


        Client client = new Client();
        client.setCreatedBy(request.getCreatedBy());
        client.setClientType(request.getClientType());
        client.setLastName(request.getLastName());
        client.setFirstName(request.getFirstName());
        client.setAdress(request.getAdress());
        client.setTelephone(request.getTelephone());
        client.setEmail(request.getEmail());
        client.setDateOfBirth(request.getDateOfBirth());
        client.setAccounts(
                request.getAccounts() != null ?
                        request
                                .getAccounts()
                                .stream()
                                .map(AccountRequest::fromRequestToEntity)
                                .collect(Collectors.toList()) : null
        );

        return client;
    }
}

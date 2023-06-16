package fr.adservio.neobankbackend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.adservio.neobankbackend.contants.ClientType;
import fr.adservio.neobankbackend.models.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientResponse {

    private Long id;

    private ClientType clientType;

    private String lastName;

    private String firstName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;

    private String adress;

    private String email;

    private String telephone;

    @JsonIgnore
    private List<AccountResponse> account;

    public static ClientResponse fromEntityToResponse(final Client client) {
        if (client == null) {
            return null;
        }

        return ClientResponse.builder()
                .id(client.getId())
                .clientType(client.getClientType())
                .lastName(client.getLastName())
                .firstName(client.getFirstName())
                .adress(client.getAdress())
                .telephone(client.getTelephone())
                .email(client.getEmail())
                .dateOfBirth(client.getDateOfBirth())
                .build();
    }
}

package fr.adservio.neobankbackend.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.adservio.neobankbackend.models.AbstractEntity;
import fr.adservio.neobankbackend.models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String email;

    private String password;

    @JsonIgnore
    private User createdBy;

    public static User fromRequestToEntity(final UserRequest request)
    {
        final User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCreatedBy(request.getCreatedBy());
        return  user;
    }

}

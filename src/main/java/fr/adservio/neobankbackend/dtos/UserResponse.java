package fr.adservio.neobankbackend.dtos;


import fr.adservio.neobankbackend.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private  Long id;

    private String email;


    public static UserResponse fromEntityToResponse(final User user)
    {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

}

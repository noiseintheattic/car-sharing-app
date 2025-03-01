package mate.academy.carsharingapp.dto.user;

import java.util.Set;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserWithRolesResponseDto {
    private String message;
    private Long id;
    private String email;
    private Set<String> roles;
}

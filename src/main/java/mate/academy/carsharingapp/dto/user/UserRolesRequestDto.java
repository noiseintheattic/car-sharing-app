package mate.academy.carsharingapp.dto.user;

import java.util.Set;

public record UserRolesRequestDto(
        String email,
        Set<Long> rolesId
) {
}

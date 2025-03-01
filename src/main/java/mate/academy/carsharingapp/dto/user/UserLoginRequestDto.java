package mate.academy.carsharingapp.dto.user;

public record UserLoginRequestDto(
        String email,
        String password
) {
}


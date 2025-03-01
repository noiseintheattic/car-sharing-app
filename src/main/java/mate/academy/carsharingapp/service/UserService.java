package mate.academy.carsharingapp.service;

import java.util.Set;
import mate.academy.carsharingapp.dto.user.UserProfileDto;
import mate.academy.carsharingapp.dto.user.UserRegistrationRequestDto;
import mate.academy.carsharingapp.dto.user.UserResponseDto;
import mate.academy.carsharingapp.dto.user.UserWithRolesResponseDto;
import mate.academy.carsharingapp.exceptions.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;

    UserWithRolesResponseDto updateRole(Long userId, Set<Long> rolesId);

    UserProfileDto getProfileInfo();

    UserProfileDto updateProfileInfo(UserProfileDto requestDto);
}

package mate.academy.carsharingapp.service;

import mate.academy.carsharingapp.dto.UserRegistrationRequestDto;
import mate.academy.carsharingapp.dto.UserResponseDto;
import mate.academy.carsharingapp.exceptions.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}

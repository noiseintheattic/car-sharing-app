package mate.academy.carsharingapp.controller;

import javax.security.auth.login.LoginException;
import lombok.RequiredArgsConstructor;
import mate.academy.carsharingapp.dto.UserLoginRequestDto;
import mate.academy.carsharingapp.dto.UserLoginResponseDto;
import mate.academy.carsharingapp.dto.UserRegistrationRequestDto;
import mate.academy.carsharingapp.dto.UserResponseDto;
import mate.academy.carsharingapp.exceptions.RegistrationException;
import mate.academy.carsharingapp.security.AuthenticationService;
import mate.academy.carsharingapp.service.UserService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public UserResponseDto register(@RequestBody UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) throws LoginException {
        try {
            return authenticationService.authenticate(requestDto);
        } catch (AuthenticationException e) {
            throw new LoginException("Can't login user: " + requestDto.email());
        }
    }
}

package mate.academy.carsharingapp.service;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mate.academy.carsharingapp.dto.user.UserProfileDto;
import mate.academy.carsharingapp.dto.user.UserRegistrationRequestDto;
import mate.academy.carsharingapp.dto.user.UserResponseDto;
import mate.academy.carsharingapp.dto.user.UserWithRolesResponseDto;
import mate.academy.carsharingapp.exceptions.EntityNotFoundException;
import mate.academy.carsharingapp.exceptions.RegistrationException;
import mate.academy.carsharingapp.mapper.UserMapper;
import mate.academy.carsharingapp.model.Role;
import mate.academy.carsharingapp.model.User;
import mate.academy.carsharingapp.repository.RoleRepository;
import mate.academy.carsharingapp.repository.UserRepository;
import mate.academy.carsharingapp.security.AuthenticationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findUserByEmail(requestDto.getEmail()).isPresent()) {
            String message = "User with given email: "
                    + requestDto.getEmail() + "already exists";
            //log.error(message);
            throw new RegistrationException(message);
        }
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName()).getLastName();

        user.setUserRoles(Set.of(roleRepository.findById(2L)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find role with id: 2"
                ))));

        User savedUser = userRepository.save(user);
        UserResponseDto userResponseDto = userMapper.toUserResponseDto(savedUser);
        userResponseDto.setMessage("You have been successfully registered.");

        return userResponseDto;
    }

    @Override
    public UserWithRolesResponseDto updateRole(Long userId, Set<Long> rolesId) {
        User userById = userRepository.findUserById(userId).orElseThrow(
                () -> new EntityNotFoundException("Can't find user by id: " + userId)
        );
        Set<Role> updatedRoles = new HashSet<>();
        for (Long i : rolesId) {
            Role role = roleRepository.findById(i).orElseThrow(
                    () -> new EntityNotFoundException("Can't find role by id:" + i)
            );
            updatedRoles.add(role);
        }
        userById.setUserRoles(updatedRoles);
        User updatedUser = userRepository.save(userById);
        String message = "Roles have been successfully updated.";
        UserWithRolesResponseDto userWithRolesResponse
                = userMapper.toUserWithRolesResponse(updatedUser);
        userWithRolesResponse.setMessage(message);
        return userWithRolesResponse;
    }

    @Override
    public UserProfileDto getProfileInfo() {
        String email = authenticationService.getAuthenticatedUserEmail();
        User userByEmail = userRepository.findUserByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Can't find user by email:" + email)
        );
        return userMapper.toUserProfileDto(userByEmail);
    }

    @Override
    public UserProfileDto updateProfileInfo(UserProfileDto requestDto) {
        String email = authenticationService.getAuthenticatedUserEmail();
        User userByEmail = userRepository.findUserByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Can't find user by email:" + email)
        );
        userByEmail.setFirstName(requestDto.firstName());
        userByEmail.setLastName(requestDto.lastName());
        User updatedUser = userRepository.save(userByEmail);
        return userMapper.toUserProfileDto(updatedUser);
    }
}

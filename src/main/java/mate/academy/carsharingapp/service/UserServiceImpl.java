package mate.academy.carsharingapp.service;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mate.academy.carsharingapp.dto.UserRegistrationRequestDto;
import mate.academy.carsharingapp.dto.UserResponseDto;
import mate.academy.carsharingapp.exceptions.EntityNotFoundException;
import mate.academy.carsharingapp.exceptions.RegistrationException;
import mate.academy.carsharingapp.mapper.UserMapper;
import mate.academy.carsharingapp.model.User;
import mate.academy.carsharingapp.repository.RoleRepository;
import mate.academy.carsharingapp.repository.UserRepository;
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
}

package mate.academy.carsharingapp.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.carsharingapp.dto.user.UserProfileDto;
import mate.academy.carsharingapp.dto.user.UserRolesRequestDto;
import mate.academy.carsharingapp.dto.user.UserWithRolesResponseDto;
import mate.academy.carsharingapp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @PutMapping("/{id}/role")
    public UserWithRolesResponseDto update(@PathVariable Long id,
                                           @RequestBody UserRolesRequestDto requestDto) {
        return userService.updateRole(id, requestDto.rolesId());
    }

    @GetMapping("/me")
    public UserProfileDto getProfileInfo(Authentication authentication) {
        return userService.getProfileInfo();
    }

    @PutMapping("me")
    public UserProfileDto updateProfileInfo(Authentication authentication,
                                            @RequestBody UserProfileDto requestDto) {
        return userService.updateProfileInfo(requestDto);
    }
}

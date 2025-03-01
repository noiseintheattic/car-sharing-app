package mate.academy.carsharingapp.mapper;

import java.util.HashSet;
import java.util.Set;
import mate.academy.carsharingapp.config.MapperConfig;
import mate.academy.carsharingapp.dto.user.UserProfileDto;
import mate.academy.carsharingapp.dto.user.UserRegistrationRequestDto;
import mate.academy.carsharingapp.dto.user.UserResponseDto;
import mate.academy.carsharingapp.dto.user.UserWithRolesResponseDto;
import mate.academy.carsharingapp.model.Role;
import mate.academy.carsharingapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toUserResponseDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);

    @Mapping(source = "userRoles",
            target = "roles",
            qualifiedByName = "rolesToString")
    UserWithRolesResponseDto toUserWithRolesResponse(User user);

    UserProfileDto toUserProfileDto(User user);

    @Named("rolesToString")
    default Set<String> rolesToString(Set<Role> userRoles) {
        Set<String> roles = new HashSet<>();
        for (Role r : userRoles) {
            roles.add(r.toString());
        }
        return roles;
    }
}

package ua.com.sergeiokon.converter;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.com.sergeiokon.model.dto.UserDto;
import ua.com.sergeiokon.repository.entity.User;

import java.util.stream.Collectors;

public class UserConverter {

    public static UserDto convertToDto(User user) {

        UserDto userDto = new UserDto();
        if (user == null) {
            userDto = null;
        } else {
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setRole(user.getRole());
            userDto.setActive(user.isActive());
            userDto.setPassword(user.getPassword());
//            userDto.setPassword(passwordEncoder.encode(user.getPassword()));

            if (user.getEvents() == null) {
                userDto.setEventsDto(null);
            } else {
                userDto.setEventsDto(user.getEvents().stream()
                        .map(EventConverter::convertToDto)
                        .collect(Collectors.toList()));
            }
        }
        return userDto;
    }

    public static User convertToEntity(UserDto userDto) {
        User user = new User();
        if (userDto == null) {
            user = null;
        } else {
            user.setId(userDto.getId());
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setRole(userDto.getRole());
            user.setActive(userDto.isActive());
            user.setPassword(userDto.getPassword());
//            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            if (userDto.getEventsDto() == null) {
                user.setEvents(null);
            } else {
                user.setEvents(userDto.getEventsDto().stream()
                        .map(EventConverter::convertToEntity)
                        .collect(Collectors.toList()));
            }
        }
        return user;
    }
}

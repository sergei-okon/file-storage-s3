package ua.com.sergeiokon.model.dto;

import lombok.Data;
import ua.com.sergeiokon.repository.entity.Event;
import ua.com.sergeiokon.repository.entity.Role;
import ua.com.sergeiokon.repository.entity.User;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private boolean active;
    private List<EventDto> eventsDto;
}


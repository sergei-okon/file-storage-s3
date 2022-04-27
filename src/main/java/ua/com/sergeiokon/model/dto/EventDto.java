package ua.com.sergeiokon.model.dto;

import lombok.Data;
import ua.com.sergeiokon.repository.entity.File;
import ua.com.sergeiokon.repository.entity.User;

import java.time.LocalDateTime;

@Data
public class EventDto {

    private Long id;
    private User user;
    private File file;
    private LocalDateTime created;
    private ua.com.sergeiokon.repository.entity.Operation Operation;
}

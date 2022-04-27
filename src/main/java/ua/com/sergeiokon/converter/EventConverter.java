package ua.com.sergeiokon.converter;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.com.sergeiokon.model.dto.EventDto;
import ua.com.sergeiokon.repository.entity.Event;
import ua.com.sergeiokon.service.FileService;
import ua.com.sergeiokon.service.UserService;

@Data
@AllArgsConstructor
public class EventConverter {

    private final UserService userService;
    private final FileService fileService;

    public static EventDto convertToDto(Event event) {
        EventDto eventDto = new EventDto();

        if (event == null) {
            eventDto = null;
        } else {
            eventDto.setId(event.getId());
            if (event.getUser() == null) {
                eventDto.setUser(null);
            } else {
                eventDto.setUser(event.getUser());
            }
            if (event.getFile() == null) {
                eventDto.setFile(null);
            } else {
                eventDto.setFile(event.getFile());
            }
            eventDto.setCreated(event.getCreated());
            eventDto.setOperation(event.getOperation());
        }
        return eventDto;
    }

    public static Event convertToEntity(EventDto eventDto) {
        Event event = new Event();

        if (eventDto == null) {
            event = null;
        } else {
            event.setId(eventDto.getId());
            if (eventDto.getUser() == null) {
                event.setUser(null);
            } else {
                event.setUser(eventDto.getUser());
            }
            if (eventDto.getFile() == null) {
                event.setFile(null);
            } else {
                event.setFile(eventDto.getFile());
            }
            event.setCreated(eventDto.getCreated());
            event.setOperation(eventDto.getOperation());
        }
        return event;
    }
}

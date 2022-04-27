package ua.com.sergeiokon.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.sergeiokon.converter.EventConverter;
import ua.com.sergeiokon.model.dto.EventDto;
import ua.com.sergeiokon.repository.entity.Event;
import ua.com.sergeiokon.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/events")
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDto>> getFiles() {
        return ResponseEntity.ok(eventService.findAll().stream()
                .map(EventConverter::convertToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEvent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(EventConverter.convertToDto(eventService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<EventDto> addEvent(@RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EventConverter.convertToDto(eventService.save(event)));
    }

    @PutMapping
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        Event updatedEvent = eventService.update(event);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(
            IllegalArgumentException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

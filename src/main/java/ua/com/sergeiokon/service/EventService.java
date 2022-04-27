package ua.com.sergeiokon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.sergeiokon.converter.EventConverter;
import ua.com.sergeiokon.repository.EventRepository;
import ua.com.sergeiokon.repository.entity.Event;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event with id " + id + " not found"));
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event update(Event event) {
        eventRepository.findById(event.getId())
                .orElseThrow(() -> new IllegalArgumentException("Event with id " + event.getId() +
                        " not found. Unable to update event"));
        return eventRepository.save(event);
    }

    public void deleteById(Long id) {
        if (eventRepository.findById(id).isPresent()) {
            eventRepository.deleteById(id);
        } else
            throw new IllegalArgumentException("Event with id " + id + " not found. Unable to delete event");
    }
}

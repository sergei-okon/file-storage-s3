package ua.com.sergeiokon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ua.com.sergeiokon.repository.EventRepository;
import ua.com.sergeiokon.repository.entity.Event;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EventServiceTest {

    @Mock
    EventRepository eventRepositoryMock;
    @InjectMocks
    EventService eventService;

    @BeforeEach
    void setUp() {
        eventRepositoryMock = mock(EventRepository.class);
        eventService = new EventService(eventRepositoryMock);
    }

    @Test
    void findAllSuccess() {
        eventService.findAll();
        verify(eventRepositoryMock).findAll();
    }

    @Test
    void findById_Success() {
        Long id = 1L;
        Event event = new Event();
        when(eventRepositoryMock.findById(anyLong())).thenReturn(java.util.Optional.of(event));
        eventService.findById(id);
        verify(eventRepositoryMock).findById(id);
    }

    @Test
    void save_Success() {
        Event event = new Event();
        eventService.save(event);
        verify(eventRepositoryMock).save(event);
    }
}
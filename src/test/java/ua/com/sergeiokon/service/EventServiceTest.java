package ua.com.sergeiokon.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.com.sergeiokon.repository.EventRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EventServiceTest {

    @Mock
    EventRepository eventRepositoryMock;
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

        eventService.findById(id);
        verify(eventRepositoryMock).findById(id);
    }

//        @Test
//        void save_Success() {
//            EventDto eventDto = new EventDto();
//
//            eventService.save(eventDto);
//            verify(eventRepositoryMock).save(EventConverter.convertToEntity(eventDto));
//        }
//
//        @Test
//        void deleteById_Success() {
//            Long id = 1L;
//            eventService.deleteById(id);
//            verify(eventRepositoryMock).deleteById(id);
//        }
//
//        @Test
//        void update_Success() {
//            EventDto eventDto = new EventDto();
//
//            eventService.update(eventDto);
//            verify(eventRepositoryMock).update(EventConverter.convertToEntity(eventDto));
//        }
}
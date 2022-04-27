package ua.com.sergeiokon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ua.com.sergeiokon.repository.FileRepository;
import ua.com.sergeiokon.repository.entity.File;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class FileServiceTest {

    @Mock
    FileRepository fileRepositoryMock;
    @InjectMocks
    FileService fileService;

    @BeforeEach
    void setUp() {
        fileRepositoryMock = mock(FileRepository.class);
        fileService = new FileService(fileRepositoryMock);
    }

    @Test
    void findAllSuccess() {
        fileService.findAll();
        verify(fileRepositoryMock).findAll();
    }

    @Test
    void findById_Success() {
        Long id = 1L;
        File file = new File();
        when(fileRepositoryMock.findById(anyLong())).thenReturn(java.util.Optional.of(file));
        fileService.findById(id);
        verify(fileRepositoryMock).findById(id);
    }

    @Test
    void save_Success() {
        File file = new File();
        fileService.save(file);
        verify(fileRepositoryMock).save(file);
    }

    @Test
    void deleteById_Success() {
        Long id = 1L;
        File file = new File();
        file.setId(id);
        when(fileRepositoryMock.findById(anyLong())).thenReturn(java.util.Optional.of(file));
        fileService.deleteById(id);
        verify(fileRepositoryMock).deleteById(id);
    }

    @Test
    void update_Success() {
        File file = new File();
        file.setId(1L);
        when(fileRepositoryMock.findById(anyLong())).thenReturn(java.util.Optional.of(file));
        fileService.update(file);
        verify(fileRepositoryMock).save(file);
    }
}
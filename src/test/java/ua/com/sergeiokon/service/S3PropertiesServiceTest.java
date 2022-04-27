package ua.com.sergeiokon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ua.com.sergeiokon.repository.S3PropertyRepository;
import ua.com.sergeiokon.repository.entity.S3Property;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class S3PropertiesServiceTest {

    @Mock
    S3PropertyRepository s3PropertyRepositoryMock;
    @InjectMocks
    S3PropertiesService s3PropertiesService;

    @BeforeEach
    void setUp() {
        s3PropertyRepositoryMock = mock(S3PropertyRepository.class);
        s3PropertiesService = new S3PropertiesService(s3PropertyRepositoryMock);
    }

    @Test
    void getPropertiesByName() {
        String key = "123";
        S3Property s3Property = new S3Property();

        when(s3PropertyRepositoryMock.findByKey(anyString())).thenReturn(java.util.Optional.of(s3Property));
        s3PropertiesService.getPropertiesByName(key);
        verify(s3PropertyRepositoryMock).findByKey(key);
    }
}
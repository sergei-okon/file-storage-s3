package ua.com.sergeiokon.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.sergeiokon.repository.S3PropertyRepository;
import ua.com.sergeiokon.repository.entity.S3Property;

@Service
@RequiredArgsConstructor
public class S3PropertiesService {

    private final S3PropertyRepository s3PropertyRepository;

    public S3Property getPropertiesByName(String keyProperties) {
        return s3PropertyRepository.findByKey(keyProperties)
                .orElseThrow(() -> new IllegalArgumentException("Key not found"));
    }
}

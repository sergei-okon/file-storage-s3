package ua.com.sergeiokon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ua.com.sergeiokon.repository.UserRepository;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class FileStorageS3Application {

    public static void main(String[] args) {
        SpringApplication.run(FileStorageS3Application.class, args);
    }
}

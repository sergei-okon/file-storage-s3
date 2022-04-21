package ua.com.sergeiokon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileStorageS3Application {

    public static void main(String[] args) {
        SpringApplication.run(FileStorageS3Application.class, args);


//        S3Connector s3Connector = new S3Connector();
//        S3FileService s3FileService = new S3FileService(new FileRepository());
//        s3FileService.uploadFile();
    }
}

package ua.com.sergeiokon.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.sergeiokon.repository.entity.Event;
import ua.com.sergeiokon.repository.entity.File;
import ua.com.sergeiokon.repository.entity.Operation;
import ua.com.sergeiokon.repository.entity.User;
import ua.com.sergeiokon.service.EventService;
import ua.com.sergeiokon.service.FileService;
import ua.com.sergeiokon.service.UserService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor
public class S3FileService {

    private final S3Connector s3Connector;
    private final FileService fileService;
    private final UserService userService;
    private final EventService eventService;

    @Value("${aws.bucket}")
    private String bucket;

    public ObjectListing getListFiles(String bucketName) {
        AmazonS3 s3client = s3Connector.getS3client();

        ObjectListing objectListing = s3client.listObjects(bucketName);
        for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
            log.info(os.getKey());
        }
        return objectListing;
    }

    public void uploadFile(MultipartFile uploadFile, Long userId) {
        User user = userService.findById(userId);

        String originalFilename = uploadFile.getOriginalFilename();
        File file = new File();
        file.setFileName(originalFilename);
        file.setBucket(bucket);
        String location = user.getEmail() + "/" + file.getFileName();
        file.setLocation(location);
        fileService.save(file);

        AmazonS3 s3client = s3Connector.getS3client();
        s3client.putObject(bucket, location, convertMultiPartToFile(uploadFile));

        createEvent(file, user, Operation.UPLOAD);
    }

    public void downloadFile(Long fileId, String path, Long userId) throws IOException {
        User user = userService.findById(userId);
        File file = fileService.findById(fileId);
        java.io.File fileToDownload = new java.io.File(path);

        if (!fileToDownload.createNewFile()) {
            throw new IOException("Unable to create file with path " + path);
        } else {
            AmazonS3 s3client = s3Connector.getS3client();
            S3Object s3object = s3client.getObject(file.getBucket(), file.getLocation());
            S3ObjectInputStream inputStream = s3object.getObjectContent();
            FileUtils.copyInputStreamToFile(inputStream, fileToDownload);

            createEvent(file, user, Operation.DOWNLOAD);
        }
    }

    public void deleteFileByIdFromS3(Long fileId) {
        AmazonS3 s3client = s3Connector.getS3client();
        File file = fileService.findById(fileId);

        if (!s3client.doesObjectExist(bucket, file.getLocation())) {
            throw new AmazonServiceException("Unable to delete file from bucket. File is not exist.");
        } else {
            s3client.deleteObject(bucket, file.getLocation());
        }
    }

    private java.io.File convertMultiPartToFile(MultipartFile file) {
        java.io.File convFile = new java.io.File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(String.valueOf(convFile));) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }

    private void createEvent(File file, User user, Operation operation) {
        Event event = new Event();
        event.setFile(file);
        event.setUser(user);
        event.setCreated(LocalDateTime.now());
        event.setUpdated(LocalDateTime.now());
        event.setOperation(operation);
        eventService.save(event);
    }
}



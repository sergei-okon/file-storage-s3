package ua.com.sergeiokon.controller;

import com.amazonaws.services.s3.model.ObjectListing;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.sergeiokon.s3.S3FileService;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class S3Controller {

    private final S3FileService s3FileService;

    @GetMapping
    public ResponseEntity<ObjectListing> getFiles(@RequestBody String bucketName) {
        return ResponseEntity.ok(s3FileService.getListFiles(bucketName));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFileToS3(@RequestParam("file") MultipartFile file,
                                            @RequestParam("userId") Long userId) {
        s3FileService.uploadFile(file, userId);
        return ResponseEntity.ok("File uploaded successful");
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam Long fileId,
                                          @RequestParam String path,
                                          @RequestParam Long userId) {
        s3FileService.downloadFile(fileId, path, userId);
        return ResponseEntity.ok("File downloaded successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable String id) {
        s3FileService.deleteFileByIdFromS3(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(
            IllegalArgumentException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
package ua.com.sergeiokon.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.sergeiokon.repository.entity.File;
import ua.com.sergeiokon.service.FileService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/files")
public class FileController {

    private final FileService fileService;

    @GetMapping
    public ResponseEntity<List<File>> getFiles() {
        return ResponseEntity.ok(fileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<File> getFile(@PathVariable("id") Long id) {
        File file = fileService.findById(id);
        return ResponseEntity.ok(file);
    }

    @PostMapping
    public ResponseEntity<File> addFile(@RequestBody File file) {
        File savedFile = fileService.save(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFile);
    }

    @PutMapping
    public ResponseEntity<File> updateFile(@RequestBody File file) {
        File updatedFile = fileService.update(file);
        return ResponseEntity.ok(updatedFile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable("id") Long id) {
        fileService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(
            IllegalArgumentException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
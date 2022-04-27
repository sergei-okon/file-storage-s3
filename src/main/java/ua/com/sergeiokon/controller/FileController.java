package ua.com.sergeiokon.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.sergeiokon.converter.FileConverter;
import ua.com.sergeiokon.model.dto.FileDto;
import ua.com.sergeiokon.repository.entity.File;
import ua.com.sergeiokon.service.FileService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/files")
public class FileController {

    private final FileService fileService;

    @GetMapping
    public ResponseEntity<List<FileDto>> getFiles() {
        return ResponseEntity.ok(fileService.findAll().stream()
                .map(FileConverter::convertToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileDto> getFile(@PathVariable("id") Long id) {
        File file = fileService.findById(id);
        return ResponseEntity.ok(FileConverter.convertToDto(file));
    }

    @PostMapping
    public ResponseEntity<FileDto> addFile(@RequestBody File file) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(FileConverter.convertToDto(fileService.save(file)));
    }

    @PutMapping
    public ResponseEntity<FileDto> updateFile(@RequestBody File file) {
        return ResponseEntity.ok(FileConverter.convertToDto(fileService.update(file)));
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
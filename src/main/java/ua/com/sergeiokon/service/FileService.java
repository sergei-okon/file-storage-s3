package ua.com.sergeiokon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.sergeiokon.repository.FileRepository;
import ua.com.sergeiokon.repository.entity.File;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public List<File> findAll() {
        return fileRepository.findAll();
    }

    public File findById(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("File with id " + id + " not found"));
    }

    public File save(File file) {
        return fileRepository.save(file);
    }

    public File update(File file) {
        fileRepository.findById(file.getId())
                .orElseThrow(() -> new IllegalArgumentException("File with id " + file.getId() +
                        " not found. Unable to update file"));
        return fileRepository.save(file);
    }

    public void deleteById(Long id) {
        if (fileRepository.findById(id).isPresent()) {
            fileRepository.deleteById(id);
        } else
            throw new IllegalArgumentException("File with id " + id + " not found. Unable to delete file");
    }
}


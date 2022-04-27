package ua.com.sergeiokon.converter;

import ua.com.sergeiokon.model.dto.FileDto;
import ua.com.sergeiokon.repository.entity.File;

public class FileConverter {

    public static FileDto convertToDto(File file) {
        FileDto fileDto = new FileDto();
        if (file == null) {
            fileDto = null;
        } else {
            fileDto.setId(file.getId());
            fileDto.setFileName(file.getFileName());
            fileDto.setLocation(file.getLocation());
            fileDto.setBucket(file.getBucket());
        }
        return fileDto;
    }

    public static File convertToEntity(FileDto fileDto) {
        File file = new File();
        if (fileDto == null) {
            file = null;
        } else {
            file.setId(fileDto.getId());
            file.setFileName(fileDto.getFileName());
            file.setLocation(fileDto.getLocation());
            file.setBucket(fileDto.getBucket());
        }
        return file;
    }
}


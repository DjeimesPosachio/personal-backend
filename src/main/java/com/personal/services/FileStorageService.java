package com.personal.services;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.personal.config.FileStorageProperties;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    private final FileStorageProperties properties;

    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.properties = fileStorageProperties;
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
    }

    public String SaveGif(MultipartFile file) {
        try {
            String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String hash = generateHash(file.getBytes());
            String fileNameWithHash = hash + "_" + originalFileName;
            Path targetLocation = fileStorageLocation.resolve(fileNameWithHash);
            file.transferTo(targetLocation);
            return properties.getUploadDir() + "/" + fileNameWithHash;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String generateHash(byte[] bytes) {
        return DigestUtils.md5DigestAsHex(bytes);
    }
}

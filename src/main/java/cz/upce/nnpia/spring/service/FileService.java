package cz.upce.nnpia.spring.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(MultipartFile image);
}

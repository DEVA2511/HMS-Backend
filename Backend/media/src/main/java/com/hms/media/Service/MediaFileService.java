package com.hms.media.Service;

import com.hms.media.DTO.MediaFileDTO;
import com.hms.media.Modal.MediaFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface MediaFileService {
    MediaFileDTO storeFIle(MultipartFile file) throws IOException;
    public Optional<MediaFile> getFile(Long id);
}

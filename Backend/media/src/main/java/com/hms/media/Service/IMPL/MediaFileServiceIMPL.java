package com.hms.media.Service.IMPL;

import com.hms.media.DTO.MediaFileDTO;
import com.hms.media.Modal.MediaFile;
import com.hms.media.Modal.Storage;
import com.hms.media.Repository.MediaFileRepository;
import com.hms.media.Service.MediaFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediaFileServiceIMPL implements MediaFileService {
    private final MediaFileRepository mediaFileRepository;

    @Override
    public MediaFileDTO storeFIle(MultipartFile file) throws IOException {
      MediaFile mediaFile = MediaFile.builder()
              .name(file.getOriginalFilename())
              .type(file.getContentType())
              .size(file.getSize())
              .data(file.getBytes())
              .storage(Storage.DB)
              .build();
     MediaFile saveFile=mediaFileRepository.save(mediaFile);
     return MediaFileDTO.builder()
             .id(saveFile.getId())
             .name(saveFile.getName())
             .type(saveFile.getType())
             .size(saveFile.getSize())
             .build();

    }

    @Override
    public Optional<MediaFile> getFile(Long id) {
        return mediaFileRepository.findById(id);

    }
}

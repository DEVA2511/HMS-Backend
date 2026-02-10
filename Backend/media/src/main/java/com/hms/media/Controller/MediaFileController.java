package com.hms.media.Controller;

import com.hms.media.DTO.MediaFileDTO;
import com.hms.media.Modal.MediaFile;
import com.hms.media.Service.MediaFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
@Validated
@CrossOrigin("http://localhost:3000")
public class MediaFileController {
    private final MediaFileService mediaFileService;

    @PostMapping("/upload")
    public ResponseEntity<MediaFileDTO> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            MediaFileDTO mediaFileDTO = mediaFileService.storeFIle(file);
            return ResponseEntity.ok(mediaFileDTO);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<MediaFile> mediaFileOptional = mediaFileService.getFile(id);
        if(mediaFileOptional.isPresent()) {
            MediaFile mediaFile = mediaFileOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+mediaFile.getName() + "\"" )
                    .contentType(MediaType.parseMediaType((mediaFile.getType())))
                    .body(mediaFile.getData());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

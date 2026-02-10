package com.hms.media.Repository;

import com.hms.media.Modal.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaFileRepository  extends JpaRepository<MediaFile,Long> {
}

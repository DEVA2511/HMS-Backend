package com.hms.media.Modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type; //contant pdf or word
    private Long size;
    @Lob
    private byte[] data;

    private Storage storage; //default is -> DB
    @CreationTimestamp
    private LocalDateTime createdAt;



//    why means here we are useing the builder partten
//    public MediaFile setName(String name){
//        this.name = name;
//        return this;
//    }
//
//    public MediaFile setType(String type){
//        this.type = type;
//        return this;
//    }
//
//    public MediaFile build(){
//        return this;
//    }
//    public  void getObj() {
//        MediaFile.builder().name().typr().build()
//    }

}

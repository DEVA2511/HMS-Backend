package com.hms.user.Model;

import com.hms.user.DTO.Roles;
import com.hms.user.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column( unique = true)
    private String email;
    private String password;
    private Roles role;
    private Long profileId;
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdAt;
    @Column(updatable = true,nullable = false)
    private LocalDateTime updatedAt;

    public UserDTO toDTO() {
        return new UserDTO(this.id, this.name, this.email, this.password, this.role,this.profileId,this.createdAt,this.updatedAt);
    }

    @PrePersist //this use to set the createdAt and updatedAt before saving to database
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate //this use to set the updatedAt before updating to database
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

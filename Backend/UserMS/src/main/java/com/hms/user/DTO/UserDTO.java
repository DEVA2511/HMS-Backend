package com.hms.user.DTO;

import com.hms.user.Model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;
    private Roles role;
    private Long profileId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User toEntity() {
        return new User(this.id, this.name, this.email, this.password, this.role, this.profileId, this.createdAt,this.updatedAt);
    }
}

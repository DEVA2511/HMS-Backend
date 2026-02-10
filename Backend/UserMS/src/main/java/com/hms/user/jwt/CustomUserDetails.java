package com.hms.user.jwt;

import com.hms.user.DTO.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Roles role;
    private String name;
    private Long profileId;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(
            Long id,
            String email,
            String password,
            Roles role,
            String name,
            Long profileId
    ) {
        this.id = id;
        this.username = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.profileId = profileId;

        // ✅ MUST NOT BE NULL
        this.authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + role.name())
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}


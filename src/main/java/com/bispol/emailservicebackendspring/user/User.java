package com.bispol.emailservicebackendspring.user;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Collections;

@Getter()
@Setter(AccessLevel.PACKAGE)
@Entity()
@Table(name = "users")
@EqualsAndHashCode()
@ToString()
class User implements UserDetails {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Length(min = 2)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Email()
    private String email;

    private boolean enable;

    @PersistenceConstructor()
    User() {
        this.role = UserRole.USER;
        this.enable = false;
    }

    User(String username, String password, String email) {
        this.role = UserRole.USER;
        this.enable = false;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.toString()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}

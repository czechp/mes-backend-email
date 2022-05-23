package com.bispol.emailservicebackendspring.utb;

import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter()
@Setter(AccessLevel.PACKAGE)
@ToString()
@EqualsAndHashCode()
@Entity()
@Table(name = "utbs")
class Utb {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email()
    private String email;

    @Enumerated(EnumType.STRING)
    private UtbSystem system;

    @PersistenceConstructor()
    Utb() {
        this.system = UtbSystem.B;
    }

    Utb(String email, UtbSystem system) {
        this.email = email;
        this.system = system;
    }

}

package com.bispol.emailservicebackendspring.mes_breakdown;

import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "breakdowns")
@Getter
@Setter(AccessLevel.PACKAGE)
@ToString
@EqualsAndHashCode
class Breakdown {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private BreakdownType breakdownType;

    @PersistenceConstructor
    Breakdown() {
    }

    Breakdown(String email, BreakdownType breakdownType) {
        this.email = email;
        this.breakdownType = breakdownType;
    }
}

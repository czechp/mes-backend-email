package com.bispol.emailservicebackendspring.mes_quality_control;

import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter()
@Setter(AccessLevel.PACKAGE)
@ToString
@EqualsAndHashCode
@Entity()
@Table(name = "quality_controls")
class QualityControl {
    @Id()
    @GeneratedValue()
    private long id;

    @Email()
    private String email;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @PersistenceConstructor()
    QualityControl() {
    }

    QualityControl(String email, ProductType productType) {
        this.email = email;
        this.productType = productType;
    }

}

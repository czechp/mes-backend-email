package com.bispol.emailservicebackendspring.mes_quality_control.dto;

import com.bispol.emailservicebackendspring.mes_quality_control.ProductType;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class QualityControlCommandDto {
    @Email
    private String email;
    private ProductType productType;
}

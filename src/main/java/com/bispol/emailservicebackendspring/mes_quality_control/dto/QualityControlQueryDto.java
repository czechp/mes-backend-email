package com.bispol.emailservicebackendspring.mes_quality_control.dto;

import com.bispol.emailservicebackendspring.mes_quality_control.ProductType;
import lombok.Data;

@Data()
public class QualityControlQueryDto {
    private long id;
    private String email;
    private ProductType productType;
}

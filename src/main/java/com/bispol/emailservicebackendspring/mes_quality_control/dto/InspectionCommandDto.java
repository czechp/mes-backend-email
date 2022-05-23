package com.bispol.emailservicebackendspring.mes_quality_control.dto;

import com.bispol.emailservicebackendspring.mes_quality_control.ProductType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class InspectionCommandDto {
    @NotNull
    private long id;
    @NotNull
    private String inspector;
    @NotNull
    private UserRole userRole;
    @NotNull
    private String message;
    @NotNull
    private LocalDateTime creationDate;
    @NotNull
    private ProductType productType;
    @NotNull
    private String lineName;
}

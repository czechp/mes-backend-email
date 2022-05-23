package com.bispol.emailservicebackendspring.mes_breakdown.dto;

import com.bispol.emailservicebackendspring.mes_breakdown.BreakdownType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class BreakdownCommandEmailDto {
    @NotNull()
    private String line;
    @NotNull()
    private String content;
    @NotNull()
    private LocalDateTime creationDate;
    @NotNull
    private BreakdownType breakdownType;
    @NotNull()
    private String applicant;
}

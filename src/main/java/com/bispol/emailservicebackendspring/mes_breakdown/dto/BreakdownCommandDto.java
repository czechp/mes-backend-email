package com.bispol.emailservicebackendspring.mes_breakdown.dto;

import com.bispol.emailservicebackendspring.mes_breakdown.BreakdownType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BreakdownCommandDto {
    @Email
    private String email;

    private BreakdownType breakdownType;
}

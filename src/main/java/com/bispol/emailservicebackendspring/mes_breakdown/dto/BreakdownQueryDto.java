package com.bispol.emailservicebackendspring.mes_breakdown.dto;

import com.bispol.emailservicebackendspring.mes_breakdown.BreakdownType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BreakdownQueryDto {
    private long id;

    private String email;

    private BreakdownType breakdownType;
}

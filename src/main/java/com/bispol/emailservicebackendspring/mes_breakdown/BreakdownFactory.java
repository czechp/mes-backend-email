package com.bispol.emailservicebackendspring.mes_breakdown;

import com.bispol.emailservicebackendspring.mes_breakdown.dto.BreakdownCommandDto;
import com.bispol.emailservicebackendspring.mes_breakdown.dto.BreakdownQueryDto;

class BreakdownFactory {
    static BreakdownQueryDto toDto(Breakdown breakdown) {
        return new BreakdownQueryDto(
                breakdown.getId(),
                breakdown.getEmail(),
                breakdown.getBreakdownType()
        );
    }

    static Breakdown toEntity(BreakdownCommandDto breakdownCommandDto) {
        return new Breakdown(
                breakdownCommandDto.getEmail(),
                breakdownCommandDto.getBreakdownType()
        );
    }
}

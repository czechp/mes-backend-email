package com.bispol.emailservicebackendspring.mes_breakdown;

import com.bispol.emailservicebackendspring.exception.BadRequestException;
import com.bispol.emailservicebackendspring.mes_breakdown.dto.BreakdownCommandDto;
import com.bispol.emailservicebackendspring.mes_breakdown.dto.BreakdownCommandEmailDto;
import com.bispol.emailservicebackendspring.tools.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BreakdownCommandService {
    private final BreakdownCommandRepository breakdownCommandRepository;

    @Autowired
    BreakdownCommandService(BreakdownCommandRepository breakdownCommandRepository) {
        this.breakdownCommandRepository = breakdownCommandRepository;
    }

    void save(BreakdownCommandDto breakdownCommandDto) {
        breakdownCommandRepository.save(BreakdownFactory.toEntity(breakdownCommandDto));
    }

    void deleteById(long breakdownId) {
        if (breakdownCommandRepository.existsById(breakdownId))
            breakdownCommandRepository.deleteById(breakdownId);
        else
            throw new BadRequestException("Taki adres email nie istnieje");
    }

}

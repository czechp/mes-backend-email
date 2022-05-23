package com.bispol.emailservicebackendspring.mes_breakdown;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Profile("development")
class BreakdownWarmup {
    private final BreakdownRepository breakdownRepository;

    @Autowired
    BreakdownWarmup(BreakdownRepository breakdownRepository) {
        this.breakdownRepository = breakdownRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    void init() {
        List<Breakdown> breakdowns = Arrays.asList(
                new Breakdown("webcoderc@gmail.com", BreakdownType.PTS),
                new Breakdown("webcoderc@gmail.com", BreakdownType.CANDLE),
                new Breakdown("webcoderc@gmail.com", BreakdownType.TEALIGHT),
                new Breakdown("webcoderc@gmail.com", BreakdownType.MANAGEMENT)
        );

        breakdownRepository.saveAll(breakdowns);
    }
}

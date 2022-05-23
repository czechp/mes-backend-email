package com.bispol.emailservicebackendspring.mes_breakdown;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class BreakdownQueryRepositoryImpl implements BreakdownQueryRepository {
    private final BreakdownRepository breakdownRepository;

    @Autowired
    BreakdownQueryRepositoryImpl(BreakdownRepository breakdownRepository) {
        this.breakdownRepository = breakdownRepository;
    }

    @Override
    public List<Breakdown> findBy() {
        return breakdownRepository.findAll();
    }

    @Override
    public List<Breakdown> findByBreakdownType(BreakdownType breakdownType) {
        return breakdownRepository.findByBreakdownType(breakdownType);
    }
}

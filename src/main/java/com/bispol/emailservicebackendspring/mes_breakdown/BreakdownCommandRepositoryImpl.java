package com.bispol.emailservicebackendspring.mes_breakdown;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BreakdownCommandRepositoryImpl implements BreakdownCommandRepository {
    private final BreakdownRepository breakdownRepository;

    @Autowired
    BreakdownCommandRepositoryImpl(BreakdownRepository breakdownRepository) {
        this.breakdownRepository = breakdownRepository;
    }

    @Override
    public void save(Breakdown breakdown) {
        breakdownRepository.save(breakdown);
    }

    @Override
    public boolean existsById(long id) {
        return breakdownRepository.existsById(id);
    }

    @Override
    public void deleteById(long id) {
        breakdownRepository.deleteById(id);
    }

}

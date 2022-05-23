package com.bispol.emailservicebackendspring.mes_quality_control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class QualityControlCommandRepositoryImpl implements QualityControlCommandRepository {
    private final QualityControlRepository qualityControlRepository;

    @Autowired
    QualityControlCommandRepositoryImpl(QualityControlRepository qualityControlRepository) {
        this.qualityControlRepository = qualityControlRepository;
    }

    @Override
    public void save(QualityControl qualityControl) {
        qualityControlRepository.save(qualityControl);
    }

    @Override
    public boolean existsById(final long qualityControlId) {
        return qualityControlRepository.existsById(qualityControlId);
    }

    @Override
    public void deleteById(long qualityControlId) {
        qualityControlRepository.deleteById(qualityControlId);
    }
}

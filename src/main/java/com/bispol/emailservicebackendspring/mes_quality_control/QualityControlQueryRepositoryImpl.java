package com.bispol.emailservicebackendspring.mes_quality_control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class QualityControlQueryRepositoryImpl implements QualityControlQueryRepository {
    private final QualityControlRepository qualityControlRepository;

    @Autowired
    QualityControlQueryRepositoryImpl(QualityControlRepository qualityControlRepository) {
        this.qualityControlRepository = qualityControlRepository;
    }

    @Override
    public List<QualityControl> findBy() {
        return qualityControlRepository.findBy();
    }

    @Override
    public List<QualityControl> findByProductType(ProductType productType) {
        return qualityControlRepository.findByProductType(productType);
    }
}

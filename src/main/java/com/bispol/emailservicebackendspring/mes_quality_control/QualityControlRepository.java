package com.bispol.emailservicebackendspring.mes_quality_control;

import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository()
interface QualityControlRepository extends Repository<QualityControl, Long> {
    void save(QualityControl qualityControl);

    List<QualityControl> findBy();

    boolean existsById(long qualityControlId);

    void deleteById(long qualityControlId);

    List<QualityControl> findByProductType(ProductType productType);
}

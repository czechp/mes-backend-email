package com.bispol.emailservicebackendspring.mes_quality_control;

import java.util.List;

interface QualityControlQueryRepository {
    List<QualityControl> findBy();

    List<QualityControl> findByProductType(ProductType productType);
}

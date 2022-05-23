package com.bispol.emailservicebackendspring.mes_quality_control;

interface QualityControlCommandRepository {
    void save(final QualityControl toEntity);

    boolean existsById(final long qualityControlId);

    void deleteById(final long qualityControlId);
}

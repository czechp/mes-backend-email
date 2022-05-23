package com.bispol.emailservicebackendspring.mes_quality_control;

import com.bispol.emailservicebackendspring.mes_quality_control.dto.QualityControlCommandDto;
import com.bispol.emailservicebackendspring.mes_quality_control.dto.QualityControlQueryDto;

class QualityControlFactory {
    static QualityControlQueryDto toDto(QualityControl qualityControl) {
        QualityControlQueryDto qualityControlQueryDto = new QualityControlQueryDto();
        qualityControlQueryDto.setId(qualityControl.getId());
        qualityControlQueryDto.setEmail(qualityControl.getEmail());
        qualityControlQueryDto.setProductType(qualityControl.getProductType());
        return qualityControlQueryDto;
    }

    static QualityControl toEntity(QualityControlCommandDto qualityControlCommandDto) {
        QualityControl qualityControl = new QualityControl();
        qualityControl.setEmail(qualityControlCommandDto.getEmail());
        qualityControl.setProductType(qualityControlCommandDto.getProductType());
        return qualityControl;
    }
}

package com.bispol.emailservicebackendspring.mes_quality_control;

import com.bispol.emailservicebackendspring.exception.NotFoundException;
import com.bispol.emailservicebackendspring.mes_quality_control.dto.QualityControlCommandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class QualityControlCommandService {
    private final QualityControlCommandRepository qualityControlCommandRepository;

    @Autowired
    QualityControlCommandService(QualityControlCommandRepository qualityControlCommandRepository) {
        this.qualityControlCommandRepository = qualityControlCommandRepository;
    }

    void save(QualityControlCommandDto qualityControlCommandDto) {
        qualityControlCommandRepository.save(QualityControlFactory.toEntity(qualityControlCommandDto));
    }

    void deleteById(final long qualityControlId) {
        if (qualityControlCommandRepository.existsById(qualityControlId))
            qualityControlCommandRepository.deleteById(qualityControlId);
        else
            throw new NotFoundException("Such quality control address does not exist");
    }


}

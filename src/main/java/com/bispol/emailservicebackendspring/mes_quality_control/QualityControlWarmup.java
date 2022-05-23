package com.bispol.emailservicebackendspring.mes_quality_control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Profile("development")
class QualityControlWarmup {
    private final QualityControlRepository qualityControlRepository;

    @Autowired
    QualityControlWarmup(QualityControlRepository qualityControlRepository) {
        this.qualityControlRepository = qualityControlRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void init() {
        Arrays.asList(
                        new QualityControl("webcoderc@gmail.com", ProductType.PTS),
                        new QualityControl("webcoderc@gmail.com", ProductType.CANDLE),
                        new QualityControl("webcoderc@gmail.com", ProductType.TEALIGHT)
                )
                .forEach(qualityControlRepository::save);
    }
}

package com.bispol.emailservicebackendspring.utb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component()
@Profile("development")
class UtbWarmup {
    private final UtbRepository utbRepository;

    @Autowired()
    UtbWarmup(UtbRepository utbRepository) {
        this.utbRepository = utbRepository;
    }


    @EventListener(ApplicationReadyEvent.class)
    void warmup() {
        List<Utb> utbs = Arrays.asList(
                new Utb("someEmail1@gmail.com", UtbSystem.AS),
                new Utb("someEmail2@gmail.com", UtbSystem.B),
                new Utb("someEmail3@gmail.com", UtbSystem.F)
        );
        utbs.stream()
                .forEach(u -> utbRepository.save(u));
    }

}

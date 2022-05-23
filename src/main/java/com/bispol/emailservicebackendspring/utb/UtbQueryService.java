package com.bispol.emailservicebackendspring.utb;

import com.bispol.emailservicebackendspring.tools.EmailService;
import com.bispol.emailservicebackendspring.utb.dto.UtbDtoQueryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service()
public class UtbQueryService {
    private final UtbRepository utbRepository;
    private final EmailService emailService;

    @Autowired()
    UtbQueryService(UtbRepository utbRepository, EmailService emailService) {
        this.utbRepository = utbRepository;
        this.emailService = emailService;
    }


    List<UtbDtoQueryInfo> findAll() {
        return utbRepository.findAll();
    }

    void sendEmails(UtbSystem system, long chargerNumber) {
        List<String> emails = utbRepository.findBySystem(system)
                .stream()
                .map(u -> u.getEmail())
                .collect(Collectors.toList());
        String message = prepareMessage(system, chargerNumber);
        emailService.sendEmails("powiadomienia-utb@bispol.pl",
                "Wykryto nieprawidłowe ładowanie UTB. System " + system,
                message,
                emails
        );
    }

    private String prepareMessage(UtbSystem system, long chargerNumber) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return new StringBuilder()
                .append("Wykryto nieprawidłowe ładowanie wózka UTB.<br>")
                .append("System: <b>" + system.toString() + "</b> <br>")
                .append("Prostownik: <b>" + chargerNumber + "</b><br>")
                .append("Data i czas: " + LocalDateTime.now().format(format))
                .append("<br><br>")
                .append("-----------------------------------------------------------------------------------------------------------------------------------<br>")
                .append("<i>Wiadomość wygenereowana przez system monitorowania UTB. Proszę nie odpisywać.</i> <br>")
                .toString();
    }
}

package com.bispol.emailservicebackendspring.mes_breakdown;

import com.bispol.emailservicebackendspring.mes_breakdown.dto.BreakdownCommandEmailDto;
import com.bispol.emailservicebackendspring.mes_breakdown.dto.BreakdownQueryDto;
import com.bispol.emailservicebackendspring.tools.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
class BreakdownQueryService {
    private final BreakdownQueryRepository breakdownQueryRepository;
    private final EmailService emailService;
    private static final String EMAIL_ADDRESS = "awarie-mes@bispol.pl";
    private static final String SUBJECT = "Awaria - MES";


    @Autowired
    BreakdownQueryService(BreakdownQueryRepository breakdownQueryRepository, EmailService emailService) {
        this.breakdownQueryRepository = breakdownQueryRepository;
        this.emailService = emailService;
    }

    List<BreakdownQueryDto> findBy() {
        return breakdownQueryRepository.findBy()
                .stream()
                .map(BreakdownFactory::toDto)
                .collect(Collectors.toList());
    }

    void sendEmails(BreakdownCommandEmailDto breakdownCommandEmailDto, BreakdownType breakdownType) {
        List<Breakdown> emails = breakdownQueryRepository.findByBreakdownType(breakdownType);
        emailService.sendEmails(EMAIL_ADDRESS, SUBJECT,
                prepareEmailContent(breakdownCommandEmailDto),
                emails.stream().map(Breakdown::getEmail).collect(Collectors.toList()));
    }

    private String prepareEmailContent(BreakdownCommandEmailDto breakdownCommandEmailDto) {
        return new StringBuilder()
                .append("<b>Zgłoszono awarie: </b>").append("<br>")
                .append("Linia: ").append(breakdownCommandEmailDto.getLine()).append("<br>")
                .append("Zgłaszający: ").append(breakdownCommandEmailDto.getApplicant()).append("<br>")
                .append("Data zgłoszenia: ").append(breakdownCommandEmailDto.getCreationDate()
                        .format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"))).append("<br>")
                .append("Awaria: ")
                .append("<span style='color: red'><b>")
                .append(breakdownCommandEmailDto.getContent())
                .append("</b></span><br>")
                .append("<i>--------------------------------------------------------------------------------</i><br>")
                .append("<i>Wiadomość automatycznie wygenerowana przez system MES - proszę nie odpisywać</i>")
                .toString();
    }

    void sendEmailsMaintenance(BreakdownCommandEmailDto breakdownCommandEmailDto) {
        sendEmails(breakdownCommandEmailDto, breakdownCommandEmailDto.getBreakdownType());
    }

    void sendEmailsManagement(BreakdownCommandEmailDto breakdownCommandEmailDto) {
        sendEmails(breakdownCommandEmailDto, BreakdownType.MANAGEMENT);
    }
}

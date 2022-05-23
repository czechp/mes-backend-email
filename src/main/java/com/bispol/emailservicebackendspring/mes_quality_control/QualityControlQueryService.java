package com.bispol.emailservicebackendspring.mes_quality_control;

import com.bispol.emailservicebackendspring.mes_quality_control.dto.InspectionCommandDto;
import com.bispol.emailservicebackendspring.mes_quality_control.dto.QualityControlQueryDto;
import com.bispol.emailservicebackendspring.tools.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service()
class QualityControlQueryService {
    private final QualityControlQueryRepository qualityControlQueryRepository;
    private final EmailService emailService;

    @Autowired
    QualityControlQueryService(QualityControlQueryRepository qualityControlQueryRepository, EmailService emailService) {
        this.qualityControlQueryRepository = qualityControlQueryRepository;
        this.emailService = emailService;
    }

    List<QualityControlQueryDto> findByQueryDto() {
        return qualityControlQueryRepository.findBy()
                .stream()
                .map(QualityControlFactory::toDto)
                .collect(Collectors.toList());

    }

    void sendEmails(InspectionCommandDto inspectionCommandDto) {
        String subject = "Kontrola jakości - produkt niepoprawny";
        String content = prepareEmailBody(inspectionCommandDto);

        List<String> emails = qualityControlQueryRepository.findByProductType(inspectionCommandDto.getProductType())
                .stream()
                .map(QualityControl::getEmail)
                .collect(Collectors.toList());

        emailService.sendEmails("kontrola-jakosci-mes@bispol.pl", subject, content, emails);
    }

    private String prepareEmailBody(InspectionCommandDto inspectionCommandDto) {
        return new StringBuilder()
                .append("<b>Wykryto niepoprawny produkt podczas kontroli jakości: </b>").append("<br>")
                .append("Linia: ").append(inspectionCommandDto.getLineName()).append("<br>")
                .append("Wydział: ").append(inspectionCommandDto.getProductType().getName()).append("<br>")
                .append("Wykonał: ").append(inspectionCommandDto.getInspector()).append("<br>")
                .append("Dział: ").append(inspectionCommandDto.getUserRole().getName()).append("<br>")
                .append("Data: ").append(inspectionCommandDto.getCreationDate().format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"))).append("<br>")
                .append("<span style='color: red'><b>")
                .append("Właściwość produktu NOK: ").append(inspectionCommandDto.getMessage())
                .append("</b></span><br>")
                .append("<i>--------------------------------------------------------------------------------</i><br>")
                .append("<i>Wiadomość automatycznie wygenerowana przez system MES - proszę nie odpisywać</i>")
                .toString();
    }
}

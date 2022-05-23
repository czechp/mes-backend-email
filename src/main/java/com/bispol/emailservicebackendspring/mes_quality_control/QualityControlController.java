package com.bispol.emailservicebackendspring.mes_quality_control;

import com.bispol.emailservicebackendspring.mes_quality_control.dto.InspectionCommandDto;
import com.bispol.emailservicebackendspring.mes_quality_control.dto.QualityControlCommandDto;
import com.bispol.emailservicebackendspring.mes_quality_control.dto.QualityControlQueryDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@CrossOrigin("*")
@RequestMapping("/api/quality-controls")
class QualityControlController {
    private final QualityControlQueryService qualityControlQueryService;
    private final QualityControlCommandService qualityControlCommandService;

    QualityControlController(QualityControlQueryService qualityControlQueryService, QualityControlCommandService qualityControlCommandService) {
        this.qualityControlQueryService = qualityControlQueryService;
        this.qualityControlCommandService = qualityControlCommandService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<QualityControlQueryDto> findBy() {
        return qualityControlQueryService.findByQueryDto();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void save(@RequestBody @Valid QualityControlCommandDto qualityControlCommandDto) {
        qualityControlCommandService.save(qualityControlCommandDto);
    }

    @DeleteMapping("/{qualityControlId}")
    void deleteById(
            @PathVariable final long qualityControlId
    ) {
        qualityControlCommandService.deleteById(qualityControlId);
    }

    @PostMapping("/send-emails")
    @ResponseStatus(HttpStatus.OK)
    void sendEmails(@RequestBody @Valid InspectionCommandDto inspectionCommandDto) {
        qualityControlQueryService.sendEmails(inspectionCommandDto);
    }
}

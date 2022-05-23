package com.bispol.emailservicebackendspring.mes_breakdown;

import com.bispol.emailservicebackendspring.mes_breakdown.dto.BreakdownCommandDto;
import com.bispol.emailservicebackendspring.mes_breakdown.dto.BreakdownCommandEmailDto;
import com.bispol.emailservicebackendspring.mes_breakdown.dto.BreakdownQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/breakdowns")
@CrossOrigin("*")
@Validated
class BreakdownController {
    private final BreakdownQueryService breakdownQueryService;
    private  final BreakdownCommandService breakdownCommandService;

    @Autowired
    BreakdownController(BreakdownQueryService breakdownQueryService, BreakdownCommandService breakdownCommandService) {
        this.breakdownQueryService = breakdownQueryService;
        this.breakdownCommandService = breakdownCommandService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<BreakdownQueryDto> findBy() {
        return breakdownQueryService.findBy();
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    void save(@RequestBody @Valid BreakdownCommandDto breakdownCommandDto){
        breakdownCommandService.save(breakdownCommandDto);
    }

    @DeleteMapping("/{breakdownId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable(name = "breakdownId")final long breakdownId){
        breakdownCommandService.deleteById(breakdownId);
    }

    @PostMapping("/send-emails")
    @ResponseStatus(HttpStatus.OK)
    void sendEmails(@RequestBody @Valid BreakdownCommandEmailDto breakdownCommandEmailDto){
        breakdownQueryService.sendEmailsMaintenance(breakdownCommandEmailDto);
    }


    @PostMapping("/send-emails-management")
    @ResponseStatus(HttpStatus.OK)
    void sendEmailsManagement(@RequestBody @Valid BreakdownCommandEmailDto breakdownCommandEmailDto){
        breakdownQueryService.sendEmailsManagement(breakdownCommandEmailDto);
    }
}

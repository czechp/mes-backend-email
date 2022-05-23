package com.bispol.emailservicebackendspring.utb;

import com.bispol.emailservicebackendspring.utb.dto.UtbDtoCmdAdd;
import com.bispol.emailservicebackendspring.utb.dto.UtbDtoQueryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@CrossOrigin("*")
@RequestMapping("/api/utb")
class UtbController {
    private final UtbQueryService utbQueryService;
    private final UtbCommandService utbCommandService;

    @Autowired()
    UtbController(UtbQueryService utbQueryService, UtbCommandService utbCommandService) {
        this.utbQueryService = utbQueryService;
        this.utbCommandService = utbCommandService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<UtbDtoQueryInfo> findAll() {
        return utbQueryService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    void add(
            @RequestBody() @Valid() UtbDtoCmdAdd utbDtoCmdAdd
    ) {
        utbCommandService.add(utbDtoCmdAdd);
    }


    @DeleteMapping("/{utbId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(
            @PathVariable(name = "utbId") final long utbId
    ) {
        utbCommandService.delete(utbId);
    }

    @GetMapping("/send-emails")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void sendEmail(
            @RequestParam(name = "system") final UtbSystem system,
            @RequestParam(name = "charger") final long chargerNumber
    ) {
        utbQueryService.sendEmails(system, chargerNumber);
    }
}

package com.bispol.emailservicebackendspring.utb;

import com.bispol.emailservicebackendspring.exception.BadRequestException;
import com.bispol.emailservicebackendspring.exception.NotFoundException;
import com.bispol.emailservicebackendspring.utb.dto.UtbDtoCmdAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
class UtbCommandService {
    private final UtbRepository utbRepository;

    @Autowired()
    UtbCommandService(UtbRepository utbRepository) {
        this.utbRepository = utbRepository;
    }


    void add(UtbDtoCmdAdd utbDtoCmdAdd) {
        if (!utbRepository.existsByEmailAndSystem(utbDtoCmdAdd.getEmail(), utbDtoCmdAdd.getSystem()))
            utbRepository.save(UtbFactory.toEntity(utbDtoCmdAdd));
        else
            throw new BadRequestException("Taki adres email już istnieje.");
    }

    void delete(final long utbId) {
        Utb utb = utbRepository.findById(utbId)
                .orElseThrow(() -> new NotFoundException("Taki adres email nie istnieje."));
        utbRepository.delete(utb);
    }
}

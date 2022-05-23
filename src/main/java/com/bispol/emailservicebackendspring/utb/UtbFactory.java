package com.bispol.emailservicebackendspring.utb;

import com.bispol.emailservicebackendspring.utb.dto.UtbDtoCmdAdd;

class UtbFactory {
    static Utb toEntity(UtbDtoCmdAdd dto) {
        Utb utb = new Utb(dto.getEmail(), dto.getSystem());

        return utb;
    }
}

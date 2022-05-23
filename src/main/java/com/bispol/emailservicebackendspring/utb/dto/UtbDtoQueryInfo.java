package com.bispol.emailservicebackendspring.utb.dto;

import com.bispol.emailservicebackendspring.utb.UtbSystem;

public interface UtbDtoQueryInfo {
    long getId();

    String getEmail();

    UtbSystem getSystem();
}

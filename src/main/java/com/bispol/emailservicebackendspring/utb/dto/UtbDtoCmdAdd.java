package com.bispol.emailservicebackendspring.utb.dto;

import com.bispol.emailservicebackendspring.utb.UtbSystem;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data()
public class UtbDtoCmdAdd {
    @Email()
    private String email;

    @NotNull()
    private UtbSystem system;
}

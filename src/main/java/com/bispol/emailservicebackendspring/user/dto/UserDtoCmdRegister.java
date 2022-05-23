package com.bispol.emailservicebackendspring.user.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data()
public class UserDtoCmdRegister {
    @NotNull()
    @Length(min = 2)
    private String username;

    @NotNull()
    @Length(min = 2)
    private String password;

    @NotNull()
    @Length(min = 2)
    private String passwordConf;

    @NotNull()
    @Email()
    private String email;
}

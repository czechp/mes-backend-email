package com.bispol.emailservicebackendspring.user.dto;

public interface UserDtoQueryInfo {
    long getId();

    String getUsername();

    String getEmail();

    String getRole();

    boolean isEnable();
}

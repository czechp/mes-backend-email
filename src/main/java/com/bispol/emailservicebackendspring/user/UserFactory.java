package com.bispol.emailservicebackendspring.user;

import com.bispol.emailservicebackendspring.user.dto.UserDtoCmdRegister;

class UserFactory {
    static User toEntity(UserDtoCmdRegister dto) {
        User user = new User(dto.getUsername(), dto.getPassword(), dto.getEmail());
        return user;
    }
}

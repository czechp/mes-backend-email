package com.bispol.emailservicebackendspring.user;

import com.bispol.emailservicebackendspring.user.dto.UserDtoCmdLogin;
import com.bispol.emailservicebackendspring.user.dto.UserDtoQueryInfo;
import com.bispol.emailservicebackendspring.user.dto.UserDtoQueryLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service()
class UserQueryService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired()
    UserQueryService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    Optional<UserDtoQueryLogin> login(final UserDtoCmdLogin userDtoCmdLogin) {
        return userRepository.findLoginQueryByUsername(userDtoCmdLogin.getUsername());
    }

    List<UserDtoQueryInfo> findAll() {
        return userRepository.findDtoBy();
    }
}

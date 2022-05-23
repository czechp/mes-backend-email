package com.bispol.emailservicebackendspring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component()
@Profile("development")
class UserWarmup {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired()
    UserWarmup(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @EventListener(ApplicationReadyEvent.class)
    void warmup() {
        User admin = new User("admin", "admin", "webcoderc@gmail.com");
        admin.setRole(UserRole.ADMIN);
        admin.setEnable(true);

        User user = new User("user", "user", "some@gmail.com");
        user.setEnable(true);

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(admin);
        userRepository.save(user);
    }
}

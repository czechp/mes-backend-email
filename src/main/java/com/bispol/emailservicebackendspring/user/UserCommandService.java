package com.bispol.emailservicebackendspring.user;

import com.bispol.emailservicebackendspring.exception.BadRequestException;
import com.bispol.emailservicebackendspring.exception.NotFoundException;
import com.bispol.emailservicebackendspring.user.dto.UserDtoCmdRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service()
class UserCommandService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired()
    UserCommandService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    void register(UserDtoCmdRegister userDtoCmdRegister) {
        boolean userNotExists
                = !userRepository.existsByUsernameOrEmail(userDtoCmdRegister.getUsername(), userDtoCmdRegister.getEmail());
        boolean passwordEqual = userDtoCmdRegister.getPassword().equals(userDtoCmdRegister.getPasswordConf());

        if (userNotExists && passwordEqual) {
            User user = UserFactory.toEntity(userDtoCmdRegister);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else
            throw new BadRequestException("Taki użytkownik już istnieje.");
    }


    @Transactional()
    public void enableUser(final long userId, final boolean active) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Taki użykownik nie istnieje"));
        if (user.isEnable() != active)
            user.setEnable(active);
    }

    @Transactional()
    public void changeUserRole(final long userId, final String role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Taki użykownik nie istnieje"));

        UserRole newRole = Arrays.stream(UserRole.values())
                .filter(u -> u.toString().equals(role.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new BadRequestException("Niepoprawna rola"));

        user.setRole(newRole);
    }

    void deleteUser(final long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Taki użytkownik nie istnieje"));
        userRepository.delete(user);
    }
}

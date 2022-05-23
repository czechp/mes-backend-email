package com.bispol.emailservicebackendspring.user;

import com.bispol.emailservicebackendspring.user.dto.UserDtoCmdLogin;
import com.bispol.emailservicebackendspring.user.dto.UserDtoCmdRegister;
import com.bispol.emailservicebackendspring.user.dto.UserDtoQueryInfo;
import com.bispol.emailservicebackendspring.user.dto.UserDtoQueryLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@CrossOrigin("*")
@RequestMapping("/api/users")
class UserController {
    private final UserQueryService userQueryService;
    private final AuthenticationManager authenticationManager;
    private final UserCommandService userCommandService;

    @Autowired()
    UserController(UserQueryService userQueryService, AuthenticationManager authenticationManager, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.authenticationManager = authenticationManager;
        this.userCommandService = userCommandService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    UserDtoQueryLogin login(
            @RequestBody() @Valid() UserDtoCmdLogin userDtoCmdLogin
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDtoCmdLogin.getUsername(), userDtoCmdLogin.getPassword()));
        return userQueryService.login(userDtoCmdLogin)
                .orElseThrow(() -> new UsernameNotFoundException("This user does not exists"));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    void register(@RequestBody() @Valid() UserDtoCmdRegister userDtoCmdRegister) {
        userCommandService.register(userDtoCmdRegister);
    }

    @GetMapping()
    @Secured({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    List<UserDtoQueryInfo> findAll() {
        return userQueryService.findAll();
    }

    @PatchMapping("/{userId}/enable")
    @Secured({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void enableUser(
            @RequestParam(name = "enable") final boolean active,
            @PathVariable(name = "userId") final long userId
    ) {
        userCommandService.enableUser(userId, active);
    }

    @PatchMapping("/{userId}/role")
    @Secured({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void changeRole(
            @RequestParam(name = "role") final String role,
            @PathVariable(name = "userId") final long userId
    ) {
        userCommandService.changeUserRole(userId, role);
    }

    @DeleteMapping("/{userId}")
    @Secured({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(
            @PathVariable(name = "userId") final long userId
    ) {
        userCommandService.deleteUser(userId);
    }
}

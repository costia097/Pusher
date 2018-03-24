package main.controllers;

import main.dtos.AuthDto;
import main.dtos.RegistrationFormDto;
import main.dtos.UpdateUserDto;
import main.dtos.UserDto;
import main.entities.User;
import main.secure.CustomAuthProvider;
import main.services.EmailService;
import main.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "person")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final CustomAuthProvider authProvider;
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public UserController(CustomAuthProvider authProvider, UserService userService, EmailService emailService) {
        this.authProvider = authProvider;
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/login")
    @PermitAll
    public AuthDto login() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        LOGGER.info("Hello: " + authentication.getPrincipal());
        return createAuthDto(authentication, authorities);
    }

    private AuthDto createAuthDto(Authentication authentication, Collection<? extends GrantedAuthority> authorities) {
        AuthDto authDto = new AuthDto();
        authDto.setName(authentication.getPrincipal().toString());
        authDto.setRoles(authorities.stream().map(Object::toString).collect(Collectors.toList()));
        return authDto;
    }

    @PostMapping(path = "/registration", consumes = APPLICATION_JSON_VALUE)
    @PermitAll
    public String registration(@RequestBody RegistrationFormDto registrationFormDto, HttpServletRequest request) {
        userService.registrateUser(registrationFormDto);
        return "OK";
    }

    @PostMapping("/logout")
    @PermitAll
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }

    @PostMapping("/sendEmail")
    public void sendRegistrationEmail(HttpServletRequest request) {
        String name = request.getHeader("name");
        String email = request.getHeader("email");
        String emailContent = emailService.createEmailContentForConfirmRegistration(name, email);
        emailService.sendEmailToPerson(email, "Confirm registration", emailContent);
    }



    @GetMapping("/confirm/{uuid}")
    public String confirmUserRegistration(@PathVariable("uuid") String personUuid) {
        User user = userService.findUserByUuid(UUID.fromString(personUuid));
        if (user != null && !user.getActive()) {
            user.setActive(true);
            userService.updateUser(user);
            return "Successfully activated!!!" +
                    "<a href=\"http://localhost:4200/\">Start using service</a>";
        }
        LOGGER.warn("Current user has incorrect uuid");
        return null;
    }

    @GetMapping("/forgotPassword")
    public void forgotPassword(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user != null && user.getActive()) {
            String userEmail = user.getEmail();
            String content = emailService.createEmailContentForForgotPassword(user.getName());
            emailService.sendEmailToPerson(userEmail, "Restore password", content);
        }
    }

    @PostMapping("/forgotPassword/update")
    public void forgotPasswordConfirmation(@RequestBody UpdateUserDto updateUserDto) {
        //todo create ascess token and check it before update user
        User user = userService.findByEmail(updateUserDto.getEmail());
        user.setPassword(updateUserDto.getNewPass());
        userService.updateUser(user);
    }

    @PostMapping("/do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void doSomeWork() {
        LOGGER.info("Do some work");
    }
}

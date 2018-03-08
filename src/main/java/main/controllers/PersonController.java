package main.controllers;

import main.aop.SecondBeanPostProssesor;
import main.entities.Person;
import main.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("person")
public class PersonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @PostMapping("/login")
    @PreAuthorize("hasRole('ADMIN')")
    public void save() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object credentials = authentication.getCredentials();
        LOGGER.info("Hello: " + authentication.getPrincipal());
        Object principal = authentication.getPrincipal();
    }

    @GetMapping("/do")
    @PreAuthorize("hasRole('ADMIN')")
    public void doSomeWork() {
        LOGGER.info("Do some work");
    }
}

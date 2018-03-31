package main.controllers;

import main.dtos.UsersLoginEmailDto;
import main.entities.User;
import main.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ServerController {
    private final UserService userService;

    @Autowired
    public ServerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllRegistrationData")
    public UsersLoginEmailDto resolveRegistrationData() {
        List<User> allUsers = userService.findAllUsers();
        List<UsersLoginEmailDto.LoginEmailWrapper> result = allUsers.stream()
                .map(user -> new UsersLoginEmailDto.LoginEmailWrapper(user.getLogin(), user.getEmail()))
                .collect(Collectors.toList());
        return new UsersLoginEmailDto(result);
    }

    @GetMapping("/test")
    public Object test() {
        return null;
    }
}

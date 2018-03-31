package main.services;

import main.dtos.RegistrationFormDto;
import main.entities.Role;
import main.entities.User;
import main.repositories.RoleRepository;
import main.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setActive(true);
        Role roleUser = roleRepository.findByRole("ADMIN");
        List<Role> authorities = new ArrayList<>();
        authorities.add(roleUser);
        user.setRoles(authorities);
        userRepository.save(user);
    }

    public User findByLogin(String name) {
        return userRepository.findByLogin(name);
    }

    public void registrateUser(RegistrationFormDto registrationFormDto) {
        User user = new User();
        user.setName(registrationFormDto.getFirstName());
        user.setLastName(registrationFormDto.getLastName());
        user.setLogin(registrationFormDto.getLogin());
        user.setEmail(registrationFormDto.getEmail());
        user.setGender(registrationFormDto.getGender());
        user.setPassword(registrationFormDto.getPassword());
        user.setActive(false);
        user.setCreatedAt(new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()));
        user.setUuid(UUID.randomUUID());
        addDefaultRoleForUser(user);
        userRepository.save(user);
    }

    private void addDefaultRoleForUser(User user) {
        Role role = new Role();
        role.setRole(Role.RoleStatus.USER);
        role.setUser(user);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
    }

    public User findUserByUuid(UUID userUuid) {
        return userRepository.findUserByUuid(userUuid);
    }

    public void updateUser(User user) {
        userRepository.mergeUser(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }
}

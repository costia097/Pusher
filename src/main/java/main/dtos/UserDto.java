package main.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.entities.Role;
import main.entities.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class UserDto {
    @JsonIgnore
    private Long id;
    private String email;
    private String login;
    private String password;
    private String name;
    private String lastName;
    private Boolean active;
    private Timestamp createdAt;
    private UUID uuid;
    private List<Role> roles;
    private String gender;

    public static UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setActive(user.getActive());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUuid(user.getUuid());
        userDto.setRoles(user.getRoles());
        userDto.setGender(user.getGender());
        return userDto;
    }

    public static User converToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setActive(userDto.getActive());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUuid(userDto.getUuid());
        user.setRoles(userDto.getRoles());
        user.setGender(userDto.getGender());
        return user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

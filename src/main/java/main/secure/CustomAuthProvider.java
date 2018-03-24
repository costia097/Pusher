package main.secure;

import main.entities.User;
import main.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthProvider implements AuthenticationProvider {
    private final UserService userService;

    @Autowired
    public CustomAuthProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> authorities = new ArrayList<>();
        User user = userService.findByLogin(login);
        if (user != null && checkPassword(password, user.getPassword())) {
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole().toString())));
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login, password, authorities);
            return authenticationToken;
        }
        throw new BadCredentialsException("Authentication failed for user: " + login);
    }

    private boolean checkPassword(String actual, String expected) {
        return actual.equals(expected);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

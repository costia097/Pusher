package main;

import main.dtos.RegistrationFormDto;
import main.repositories.UserRepository;
import main.services.UserService;
import org.hibernate.HibernateError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Spy
    @InjectMocks
    private UserService userService;

    @Test
    public void testRegistrateUserSuccusess() {
        RegistrationFormDto registrationFormDto = new RegistrationFormDto();
        registrationFormDto.setFirstName("test");
        registrationFormDto.setLastName("test");
        registrationFormDto.setGender("test");
        registrationFormDto.setPassword("test");
        doNothing().when(userRepository).save(any());
        userService.registrateUser(registrationFormDto);
        verify(userRepository, never());
    }

    @Test(expected = HibernateError.class)
    public void testRegistrateUserUnsucusses() {
        RegistrationFormDto registrationFormDto = new RegistrationFormDto();
        registrationFormDto.setFirstName("test");
        registrationFormDto.setLastName("test");
        doThrow(HibernateError.class).when(userRepository).save(any());
        userService.registrateUser(registrationFormDto);
    }
}

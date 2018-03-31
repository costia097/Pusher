package main;

import main.services.SenderResolver;
import main.services.UserService;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmailServiceTest {
    @Mock
    private  SenderResolver senderResolver;
    @Mock
    private JavaMailSenderImpl javaMailSender;
    @Mock
    private UserService userService;
}

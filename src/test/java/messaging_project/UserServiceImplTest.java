package messaging_project;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    IOUtils ioUtils;

    private UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        ioUtils = mock(IOUtils.class);
        userService = new UserServiceImpl(ioUtils);
    }

    @Test
    public void checkUserExists() throws IOException {
        userService.addUser();
        doNothing().when(ioUtils).writeMessage("Enter email: ");
        when(ioUtils.readNextLine()).thenReturn("something wrong");
        when(ioUtils.fileExist("asasd.txt")).thenReturn(true);
        doNothing().when(ioUtils).writeMessage("User exist");
    }

    @Test
    public void login() {
    }

    @Test
    public void loginSecond_with_parse() {
    }
}
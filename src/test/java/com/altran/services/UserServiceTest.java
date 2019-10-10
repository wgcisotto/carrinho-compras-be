package com.altran.services;

import com.altran.models.Item;
import com.altran.models.User;
import com.altran.repositories.ItemRepository;
import com.altran.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    private User user;

    private static final String ID = "5d9eb050b1746f1d188c8e55";

    @Before
    public void init(){
        user = User.builder().name("William").email("teste@teste.com").build();
    }

    @Test
    public void save(){

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User uCreated = userService.save(user);

        Assert.assertEquals(user, uCreated);

    }

    @Test
    public void findById(){
        Mockito.when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));

        Optional<User> userFound = userService.findById(ID);

        Assert.assertEquals(user.getName(), userFound.get().getName());
    }

    @Test
    public void update() throws Exception {
        Mockito.when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));

        User userUpdated = userService.update(ID, user);

        Assert.assertEquals(user.getName(), userUpdated.getName());
    }
}

package com.altran.services;

import com.altran.models.Cart;
import com.altran.models.CartItem;
import com.altran.models.Item;
import com.altran.models.User;
import com.altran.repositories.CartRepository;
import com.altran.repositories.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    CartRepository cartRepository;

    @Mock
    UserService userService;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    CartService cartService;

    private static final String ID = "5d9eb050b1746f1d188c8e55";

    private User user;
    private Item item;
    private Cart cart;

    @Before
    public void init(){
        user = User.builder()
                .name("item 1")
                .email("teste@teste.com")
                .build();

        item = Item.builder()
                .name("item 1")
                .id(ID)
                .price(10L)
                .build();
    }

    @Test
    public void addItem() throws Exception {

        CartItem cartItem = CartItem.builder()
                .qtd(1)
                .item(item)
                .build();

        when(cartRepository.findByUser(user)).thenReturn(Optional.ofNullable(cart));

        when(userService.findById(ID)).thenReturn(Optional.ofNullable(user));

        Cart cartAdd = cartService.addItem(ID,item);

        Assert.assertEquals(cart,cartAdd);
    }

    @Test
    public void createCart(){
        cartService.createCart(user, item);
    }


}

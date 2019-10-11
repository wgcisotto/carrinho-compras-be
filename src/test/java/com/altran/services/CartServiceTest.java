package com.altran.services;

import com.altran.exceptions.ValidationException;
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
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Array;
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
    private CartItem cartItem;

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


        cartItem = CartItem.builder()
                .qtd(10)
                .item(item)
                .build();

        cart = Cart.builder()
                .user(user)
                .items(Arrays.asList(cartItem))
                .build();
    }

    @Test
    public void addItem() throws Exception {

        when(cartRepository.findByUser(user)).thenReturn(Optional.ofNullable(cart));
        when(userService.findById(ID)).thenReturn(Optional.ofNullable(user));
        when(cartRepository.save(cart)).thenReturn(cart);

        Cart cartAdd = cartService.addItem(ID,item);

        Assert.assertEquals(cart,cartAdd);
    }

    @Test
    public void removeItem() throws Exception {
        when(userService.findById(ID)).thenReturn(Optional.ofNullable(user));
        when(cartRepository.findByUser(user)).thenReturn(Optional.ofNullable(cart));
        when(cartRepository.save(cart)).thenReturn(cart);

        Cart cartRemoved = cartService.removeItem(ID,item);

        Assert.assertEquals(cart,cartRemoved);


    }

    @Test
    public void findByUserId() throws  Exception {
        when(userService.findById(ID)).thenReturn(Optional.ofNullable(user));
        when(cartRepository.findByUser(user)).thenReturn(Optional.ofNullable(cart));

        Cart cartFound = cartService.findByUserId(ID);
        Assert.assertEquals(cart,cartFound);
    }

    @Test
    public void delete() throws ValidationException {
        when(cartRepository.findById(ID)).thenReturn(Optional.ofNullable(cart));
        cartService.delete(ID);
    }

    public void create() throws Exception{
        when(cartRepository.save(cart)).thenReturn(cart);
        Cart cartSaved = cartService.createCart(user,item);

        Assert.assertEquals(cart,cartSaved);
    }

}

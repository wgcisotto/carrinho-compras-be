package com.altran.services;

import com.altran.exceptions.ValidationException;
import com.altran.models.Cart;
import com.altran.models.CartItem;
import com.altran.models.Item;
import com.altran.models.User;
import com.altran.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart findByUserId(String userId) throws Exception {
        Optional<User> optionalUser = userService.findById(userId);

        if(!optionalUser.isPresent())
            throw new ValidationException("001","User not found");

        Optional<Cart> optionalCart = cartRepository.findByUser(optionalUser.get());

        if(!optionalCart.isPresent())
            throw new ValidationException("002","Cart not found");

        return optionalCart.get();

    }

    public Cart addItem(String userId, Item item) throws ValidationException {
        Optional<User> optionalUser = userService.findById(userId);

        if(!optionalUser.isPresent())
            throw new ValidationException("001","User not found");

        User user = optionalUser.get();

        Optional<Cart> optionalCart = cartRepository.findByUser(user);

        if(!optionalCart.isPresent())
            return createCart(user,item);

        Cart cart = optionalCart.get();

        updateOrAdd(cart, item);

        return save(cart);
    }

    public Cart createCart(User user, Item item) {
        CartItem cartItem = CartItem.builder()
                .item(item)
                .qtd(1)
                .build();

        Cart cart = Cart.builder()
                .items(Arrays.asList(cartItem))
                .user(user).build();

        return save(cart);
    }

    private Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public void updateOrAdd(Cart cart, Item item){
        if(cart.getItems().stream().anyMatch(i->i.getItem().getId().equals(item.getId()))){
            cart.getItems().stream()
                    .filter(i->i.getItem().getId().equals(item.getId()))
                    .forEach(CartItem::addItem);
        }else{
            cart.getItems().add(CartItem.builder()
                    .item(item)
                    .qtd(1)
                    .build());
        }
    }

    public void delete(String id) throws ValidationException {
        Optional<Cart> optionalCart = cartRepository.findById(id);

        if(!optionalCart.isPresent())
            throw new ValidationException("002","Cart not found");

        Cart cart = optionalCart.get();
        cartRepository.delete(cart);
    }

    public Cart removeItem(String userId, Item item) throws ValidationException {
        Optional<User> optionalUser = userService.findById(userId);

        if(!optionalUser.isPresent())
            throw new ValidationException("001","User not found");

        User user = optionalUser.get();

        Optional<Cart> optionalCart = cartRepository.findByUser(user);

        if(!optionalCart.isPresent())
            throw new ValidationException("002","Cart not found");

        Cart cart = optionalCart.get();

        cart.getItems().stream()
                .filter(i->i.getItem().getId().equals(item.getId()))
                .forEach(CartItem::removeItem);

        validateQtd(cart);

        return save(cart);
    }

    private void validateQtd(Cart cart) {
        Optional<CartItem> cartItem = cart.getItems().stream()
                .filter(i->i.getQtd()==0).findFirst();
        if(cartItem.isPresent())
            cart.getItems().remove(cartItem.get());
    }
}

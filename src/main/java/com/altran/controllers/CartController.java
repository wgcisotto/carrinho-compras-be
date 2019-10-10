package com.altran.controllers;

import com.altran.models.Cart;
import com.altran.models.Item;
import com.altran.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(method= RequestMethod.GET, value="/carts")
    public Iterable<Cart> carts() {
        return cartService.findAll();
    }

    @RequestMapping(method= RequestMethod.GET, value="/carts/user/{id}")
    public Cart getByUserId(@PathVariable("id") String userId) throws Exception {
        return cartService.findByUserId(userId);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/carts/user/{id}")
    public Cart addItem(@PathVariable("id") String userId,
                        @RequestBody @Valid Item item) throws Exception {
        return cartService.addItem(userId, item);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/carts/{id}")
    public Cart removeItem(@PathVariable("id") String cartId,
                           @RequestBody @Valid Item item) throws Exception {
        return cartService.removeItem(cartId, item);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/carts/{id}")
    public String delete(@PathVariable String id) {
        cartService.delete(id);
        return "";
    }





}

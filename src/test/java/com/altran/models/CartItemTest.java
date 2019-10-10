package com.altran.models;

import org.junit.Assert;
import org.junit.Test;

public class CartItemTest {

    @Test
    public void add(){
        CartItem item = CartItem.builder()
                .qtd(1)
                .item(Item.builder().name("Item 1").price(10L).build())
                .build();

        item.addItem();
        Assert.assertEquals(2L, Long.parseLong(item.getQtd().toString()));
    }

    @Test
    public void remove(){
        CartItem item = CartItem.builder()
                .qtd(2)
                .item(Item.builder().name("Item 1").price(10L).build())
                .build();

        item.removeItem();
        Assert.assertEquals(1L, Long.parseLong(item.getQtd().toString()));

    }

}

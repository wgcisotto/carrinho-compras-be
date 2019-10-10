package com.altran.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItem {

    private Item item;
    private Integer qtd;

    public void addItem(){
        qtd++;
    }

    public void removeItem() {
        qtd--;
    }
}

package com.altran.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "carts")
@Data
@Builder
public class Cart {

    @Id
    private String id;
    @NotNull(message = "User is mandatory")
    private User user;
    private List<CartItem> items;
}

package com.altran.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Document(collection = "users")
@Data
@Builder
public class User {

    @Id
    private String id;

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^\\S+@\\S+$", message = "Email invalid")
    private String email;
}

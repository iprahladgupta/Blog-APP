package com.prahlad.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min=4,message = "username must be min of 4 characters")
    private String name;

    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty
    @Size(min=3,max=10,message = "password should be min of 3 characters and max 10 characters ")
    private String password;

    @NotEmpty
    private String about;
}

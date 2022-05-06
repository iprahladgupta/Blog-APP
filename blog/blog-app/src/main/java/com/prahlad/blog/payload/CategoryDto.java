package com.prahlad.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private String categoryId;

    @NotBlank
    @Size(min=3,message = "title should be min of 3 characters")
    private String categoryTitle;

    @NotBlank
    @Size(min=10,message = "min 10 characters required")
    private  String CategoryDescription;

}

package com.spring.mvc.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 50, message = "Product name must be between 3 and 50 characters")
    private String proName;

    @NotNull(message = "Product price is required")
    @Min(value = 1, message = "Price should not be less than 1")
    private Double proPrice;

    @NotBlank(message = "Brand name is required")
    private String proBrand;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String proDescription;

    @NotBlank(message = "Category is required")
    private String proCategory;

}
	
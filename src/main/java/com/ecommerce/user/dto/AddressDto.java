package com.ecommerce.user.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class AddressDto {

    @NotBlank(message = "Address Line is required.")
    private String addressLine;

    @NotBlank(message = "City is required.")
    private String city;

    @NotBlank(message = "State is required.")
    private String state;

    @NotBlank(message = "Postal Code is required.")
    private String postalCode;

    @NotBlank(message = "Country is required.")
    private String country;
}

package com.ecommerce.user.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class ContactDto {

    @NotBlank(message = "Country code is required.")
    private String countryCode;

    @NotBlank(message = "Phone number is required.")
    private String phoneNumber;

}

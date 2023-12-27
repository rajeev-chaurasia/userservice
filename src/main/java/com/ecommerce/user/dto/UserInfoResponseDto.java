package com.ecommerce.user.dto;

import lombok.Data;

@Data
public class UserInfoResponseDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private ContactDto contact;
    private AddressDto address;

}

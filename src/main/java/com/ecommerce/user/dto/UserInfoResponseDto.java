package com.ecommerce.user.dto;

import com.ecommerce.user.beans.Address;
import com.ecommerce.user.beans.Contact;
import lombok.Data;

@Data
public class UserInfoResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Contact contact;
    private Address address;

}

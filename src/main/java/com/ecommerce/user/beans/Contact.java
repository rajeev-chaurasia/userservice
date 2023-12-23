package com.ecommerce.user.beans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Contact {

    @Column(nullable = false)
    private String countryCode;

    @Column(nullable = false)
    private String phoneNumber;
}

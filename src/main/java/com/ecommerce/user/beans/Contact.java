package com.ecommerce.user.beans;

import com.ecommerce.user.dto.ContactDto;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Contact {

    @Column(nullable = false)
    private String countryCode;

    @Column(nullable = false)
    private String phoneNumber;

    public void update(ContactDto updateRequest) {
        if(StringUtils.hasLength(updateRequest.getCountryCode())) {
            this.setCountryCode(updateRequest.getCountryCode());
        }

        if(StringUtils.hasLength(updateRequest.getPhoneNumber())) {
            this.setPhoneNumber(updateRequest.getPhoneNumber());
        }
    }
}

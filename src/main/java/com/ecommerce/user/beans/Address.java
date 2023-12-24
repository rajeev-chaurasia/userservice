package com.ecommerce.user.beans;

import com.ecommerce.user.dto.AddressDto;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {

    @Column(nullable = false)
    private String addressLine;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String country;

    public void update(AddressDto updateRequest) {
        if (StringUtils.hasLength(updateRequest.getAddressLine())) {
            this.setAddressLine(updateRequest.getAddressLine());
        }
        if (StringUtils.hasLength(updateRequest.getCity())) {
            this.setCity(updateRequest.getCity());
        }
        if (StringUtils.hasLength(updateRequest.getState())) {
            this.setState(updateRequest.getState());
        }
        if (StringUtils.hasLength(updateRequest.getPostalCode())) {
            this.setPostalCode(updateRequest.getPostalCode());
        }
        if (StringUtils.hasLength(updateRequest.getCountry())) {
            this.setCountry(updateRequest.getCountry());
        }
    }
}

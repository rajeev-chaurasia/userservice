package com.ecommerce.user.utils;

import com.ecommerce.user.beans.Address;
import com.ecommerce.user.beans.Contact;
import com.ecommerce.user.beans.User;
import com.ecommerce.user.dto.AddressDto;
import com.ecommerce.user.dto.ContactDto;
import com.ecommerce.user.dto.UserInfoResponseDto;
import com.ecommerce.user.dto.UserRegistrationRequestDto;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class MapperUtils {

    public static User convertUserRegistrationRequestDtoToUser(UserRegistrationRequestDto requestDto) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setAddress(mapAddressDtoToAddress(requestDto.getAddress()));
        user.setContact(mapContactDtoToContact(requestDto.getContact()));
        return user;
    }

    public static UserInfoResponseDto convertUserToUserInfoResponseDto(User user) {
        UserInfoResponseDto userResponse = new UserInfoResponseDto();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setAddress(mapAddressToAddressDto(user.getAddress()));
        userResponse.setContact(mapContactToContactDto(user.getContact()));
        return userResponse;
    }

    public static Address mapAddressDtoToAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setAddressLine(addressDto.getAddressLine());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setPostalCode(addressDto.getPostalCode());
        address.setCountry(addressDto.getCountry());
        return address;
    }

    public static AddressDto mapAddressToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressLine(address.getAddressLine());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setPostalCode(address.getPostalCode());
        addressDto.setCountry(address.getCountry());
        return addressDto;
    }

    public static Contact mapContactDtoToContact(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setCountryCode(contactDto.getCountryCode());
        contact.setPhoneNumber(contactDto.getPhoneNumber());
        return contact;
    }

    public static ContactDto mapContactToContactDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setCountryCode(contact.getCountryCode());
        contactDto.setPhoneNumber(contact.getPhoneNumber());
        return contactDto;
    }

}

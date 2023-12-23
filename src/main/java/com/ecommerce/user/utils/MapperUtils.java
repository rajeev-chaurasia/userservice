package com.ecommerce.user.utils;

import com.ecommerce.user.beans.User;
import com.ecommerce.user.dto.UserInfoResponseDto;

public class MapperUtils {

    public static UserInfoResponseDto convertUserToResponseDto(User user) {
        UserInfoResponseDto userResponse = new UserInfoResponseDto();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setAddress(user.getAddress());
        userResponse.setContact(user.getContact());
        return userResponse;
    }


}

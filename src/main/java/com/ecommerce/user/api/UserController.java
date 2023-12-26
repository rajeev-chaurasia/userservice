package com.ecommerce.user.api;

import com.ecommerce.user.dto.ChangePasswordRequestDto;
import com.ecommerce.user.dto.UserInfoResponseDto;
import com.ecommerce.user.dto.UserRegistrationRequestDto;
import com.ecommerce.user.dto.UserUpdateRequestDto;
import com.ecommerce.user.exceptions.TResponseEntityBuilder;
import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        UserInfoResponseDto registeredUser = userService.registerUser(requestDto);
        return TResponseEntityBuilder.okCreatedResponseEntity(registeredUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable(name = "userId") Long userId, @RequestBody @Valid UserUpdateRequestDto requestDto) {
        UserInfoResponseDto updatedUser = userService.updateUser(userId, requestDto);
        return TResponseEntityBuilder.okCreatedResponseEntity(updatedUser);
    }

    @PutMapping("/{userId}/change-password")
    public ResponseEntity changePassword(@PathVariable(name = "userId") Long userId, @RequestBody @Valid ChangePasswordRequestDto requestDto) {
        userService.changePassword(userId, requestDto);
        return TResponseEntityBuilder.okResponseEntity("success");
    }

    @GetMapping("/{email}")
    public ResponseEntity getUserProfileByEmail(@PathVariable(name = "email") @Email(message = "Invalid email address") String emailAddress) {
        return TResponseEntityBuilder.okResponseEntity(userService.getUserProfileByEmail(emailAddress));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return TResponseEntityBuilder.okResponseEntity("success");
    }

}

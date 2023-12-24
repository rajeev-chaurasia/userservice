package com.ecommerce.user.api;

import com.ecommerce.user.dto.ChangePasswordRequestDto;
import com.ecommerce.user.dto.UserInfoResponseDto;
import com.ecommerce.user.dto.UserRegistrationRequestDto;
import com.ecommerce.user.dto.UserUpdateRequestDto;
import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        UserInfoResponseDto registeredUser = userService.registerUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable(name = "userId") Long userId, @RequestBody @Valid UserUpdateRequestDto requestDto) {
        UserInfoResponseDto updatedUser = userService.updateUser(userId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
    }

    @PutMapping("/{userId}/change-password")
    public ResponseEntity changePassword(@PathVariable(name = "userId") Long userId, @RequestBody @Valid ChangePasswordRequestDto requestDto) {
        userService.changePassword(userId, requestDto);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/{email}")
    public ResponseEntity getUserProfileByEmail(@PathVariable(name = "email") @Email(message = "Invalid email address") String emailAddress) {
        return ResponseEntity.ok(userService.findByEmail(emailAddress));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("success");
    }

}

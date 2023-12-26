package com.ecommerce.user.api;

import com.ecommerce.user.dto.LoginRequestDto;
import com.ecommerce.user.exceptions.TResponseEntityBuilder;
import com.ecommerce.user.service.authentication.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthenticationService userAuthenticationService;

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody @Valid LoginRequestDto requestDto) {
        return TResponseEntityBuilder.okResponseEntity(userAuthenticationService.login(requestDto));
    }

}

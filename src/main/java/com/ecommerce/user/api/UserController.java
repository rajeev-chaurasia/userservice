package com.ecommerce.user.api;

import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/email/{emailAddress}")
    public ResponseEntity getUserByEmail(@PathVariable(name = "emailAddress") String emailAddress) {
        return ResponseEntity.ok(userService.findByEmail(emailAddress));
    }


}

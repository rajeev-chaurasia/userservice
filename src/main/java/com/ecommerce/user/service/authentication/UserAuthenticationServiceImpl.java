package com.ecommerce.user.service.authentication;

import com.bastiaanjansen.jwt.exceptions.JWTCreationException;
import com.ecommerce.user.dto.LoginRequestDto;
import com.ecommerce.user.dto.LoginResponseDto;
import com.ecommerce.user.exceptions.TBaseError;
import com.ecommerce.user.exceptions.TBaseRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            // Generate JWT token
            String token = jwtTokenProvider.generateToken(authentication);

            // Response
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setUserEmail(loginRequest.getEmail());
            loginResponseDto.setToken(token);

            return loginResponseDto;
        } catch (AuthenticationException e) {
            log.error("Authentication failed with exception ", e);
            throw new TBaseRuntimeException(TBaseError.invalidCredentials);
        } catch (JWTCreationException e) {
            log.error("Token generation failed with exception ", e);
            throw new TBaseRuntimeException(TBaseError.tokenCouldNotBeGenerated);
        }
    }
}

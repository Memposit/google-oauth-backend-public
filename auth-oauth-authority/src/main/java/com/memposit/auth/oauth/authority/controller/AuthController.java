package com.memposit.auth.oauth.authority.controller;

import com.memposit.auth.oauth.authority.exeption.UserAlreadyExistAuthenticationException;
import com.memposit.auth.oauth.authority.model.ActionResponse;
import com.memposit.auth.oauth.authority.model.TokenResponse;
import com.memposit.auth.oauth.authority.security.jwt.JWTTokenProvider;
import com.memposit.auth.oauth.authority.service.TokenService;
import com.memposit.user.service.common.CurrentUser;
import com.memposit.user.service.mapper.UserMapperImpl;
import com.memposit.user.service.model.ActionRequest;
import com.memposit.user.service.model.LocalUser;
import com.memposit.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import java.util.UUID;

import static javax.security.auth.message.AuthStatus.SUCCESS;

/**
 * The Auth controller.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserMapperImpl userMapper;
    private final JWTTokenProvider tokenProvider;
    private final TokenService tokenService;

    private final UserService userService;

    /**
     * Authenticate user response entity.
     *
     * @param actionRequest the action request
     * @return the response entity
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody ActionRequest actionRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(actionRequest.getEmail(), actionRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LocalUser localUser = (LocalUser)authentication.getPrincipal();
        String token = tokenProvider.createToken(localUser);
        return ResponseEntity.ok(new TokenResponse(token, userMapper.buildUserDto(actionRequest,localUser)));
    }

    /**
     * Register user response entity.
     *
     * @param actionRequest the action request
     * @return the response entity
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody ActionRequest actionRequest) {
        try {
            tokenService.createVerificationTokenForUser(userService.registerNewUser(actionRequest), UUID.randomUUID().toString());
        } catch (UserAlreadyExistAuthenticationException e) {
            log.error("Exception Occurred", e);
            return new ResponseEntity<>(new ActionResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new ActionResponse(true, "User registered successfully"));
    }

    /**
     * Verify code response entity.
     *
     * @param user the user
     * @return the response entity
     */
    @PostMapping("/verify")
    @PreAuthorize("hasRole('PRE_VERIFICATION_USER')")
    public ResponseEntity<?> verifyCode( @CurrentUser LocalUser user) {
        String jwt = tokenProvider.createToken(user);
        return ResponseEntity.ok(new TokenResponse(jwt, userMapper.buildUserInfo(user)));
    }

    /**
     * Confirm registration response entity.
     *
     * @param token the token
     * @return the response entity
     */
    @PostMapping("/token/verify")
    public ResponseEntity<?> confirmRegistration(@NotEmpty @RequestBody String token) {
        final String result = tokenService.validateVerificationToken(token);
        return ResponseEntity.ok().body(new ActionResponse(true, result));
    }

    /**
     * Resend registration token response entity.
     *
     * @param expiredToken the expired token
     * @return the response entity
     */
    @PostMapping("/token/resend")
    @ResponseBody
    public ResponseEntity<?> resendRegistrationToken(@NotEmpty @RequestBody String expiredToken) {
        if (!tokenService.resendVerificationToken(expiredToken)) {
            return new ResponseEntity<>(new ActionResponse(false, "Token not found!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new ActionResponse(true, SUCCESS.toString()));
    }

}

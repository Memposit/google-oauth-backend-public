package com.memposit.auth.oauth.authority.controller;

import com.memposit.auth.oauth.authority.model.AccountDto;
import com.memposit.user.service.common.CurrentUser;
import com.memposit.user.service.mapper.UserMapper;
import com.memposit.user.service.model.LocalUser;
import com.memposit.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * The User controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    /**
     * Gets current user.
     *
     * @param localUser the local user
     * @return the current user
     */
    @GetMapping("/users/me")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MODERATOR')")
    public ResponseEntity<?> getCurrentUser(@CurrentUser LocalUser localUser) {
        return ResponseEntity.ok(
                userMapper.buildUserInfo(localUser)
        );
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    @GetMapping("/all")
    public ResponseEntity<?> getContent() {
        return ResponseEntity.ok("Public content");
    }

    /**
     * Gets user content.
     *
     * @return the user content
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getUserContent() {
        return ResponseEntity.ok("User content");
    }

    @GetMapping("users/mod")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> getModeratorContent() {
        return ResponseEntity.ok("Moderator content");
    }

    @PostMapping("/users/mod/stripe")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> setStripeAccountConnector(@CurrentUser LocalUser localUser, @RequestBody AccountDto accountDto) {
        userService.setStripeCustomerAccount(localUser.getUser().getId(), accountDto.getStripeAccountId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/stripe")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> setStripeCustomerAccount(@CurrentUser LocalUser localUser, @RequestBody AccountDto accountDto) {
        userService.setStripeCustomerAccount(localUser.getUser().getId(), accountDto.getStripeAccountId());
        return ResponseEntity.ok().build();
    }


}
package com.memposit.user.service.mapper;

import com.memposit.user.service.entity.User;
import com.memposit.user.service.enums.Provider;
import com.memposit.user.service.model.ActionRequest;
import com.memposit.user.service.model.LocalUser;
import com.memposit.user.service.model.UserDto;
import com.memposit.user.service.entity.Role;
import com.memposit.user.service.providers.UserProvidersInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * The User mapper.
 */
@Component
public class UserMapper implements UserMapperImpl {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User convertActionRequestToUser(ActionRequest actionRequest, Role role) {
        User user = new User();
        Date now = Calendar.getInstance().getTime();
        if (actionRequest.getProviderUserId() != null) {
            user.setProvider(Provider.fromString(actionRequest.getProvider()));
            user.setProviderId(actionRequest.getProviderUserId());
        } else {
            user.setProvider(Provider.LOCAL);
        }
        user.setName(actionRequest.getDisplayName());
        user.setCreatedDate(now);
        user.setEmailVerified(true);
        if (actionRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(actionRequest.getPassword()));
        }
        user.setModifiedDate(now);
        user.setEmail(actionRequest.getEmail());
        user.setImageUrl(actionRequest.getImgUrl());
        user.setRole(role);
        return user;
    }

    public UserDto buildUserDto(ActionRequest actionRequest, LocalUser local) {
        return UserDto.builder()
                .email(actionRequest.getEmail())
                .displayName(local.getUser().getName())
                .id(local.getUser().getId())
                .role(local.getUser().getRole().getName()).build();
    }

    public UserDto buildUserInfo(LocalUser localUser) {
        User user = localUser.getUser();
        return UserDto.builder()
                .id(user.getId())
                .displayName(user.getName())
                .email(user.getEmail())
                .role(localUser.getAuthorities().stream().findFirst().get().toString())
                .build();
    }


    public ActionRequest toUserRegistrationObject(String registrationId, UserProvidersInfo userProvidersInfo) {
        return ActionRequest.builder()
                .providerUserId(userProvidersInfo.getId())
                .imgUrl(userProvidersInfo.getImageUrl())
                .displayName(userProvidersInfo.getName())
                .email(userProvidersInfo.getEmail())
                .provider(registrationId)
                .build();
    }

}

package com.memposit.user.service.service.impl;

import com.memposit.user.service.entity.User;
import com.memposit.user.service.exeption.UserAlreadyExistAuthenticationException;
import com.memposit.user.service.model.ActionRequest;
import com.memposit.user.service.model.LocalUser;
import com.memposit.user.service.model.OidcLocalUser;
import com.memposit.user.service.exeption.ProviderNotSupportedException;
import com.memposit.user.service.mapper.UserMapperImpl;
import com.memposit.user.service.providers.UserProvidersInfo;
import com.memposit.user.service.providers.UserProvidersInfoFactory;
import com.memposit.user.service.repository.RoleRepository;
import com.memposit.user.service.repository.UserRepository;
import com.memposit.user.service.service.CustomUserDetailsService;
import com.memposit.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

/**
 * The type User service.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapperImpl userMapper;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public User registerNewUser(
        ActionRequest actionRequest) throws UserAlreadyExistAuthenticationException {
        if (actionRequest.getUserID() != null && userRepository.existsById(actionRequest.getUserID())) {
            throw new UserAlreadyExistAuthenticationException("User with User id " + actionRequest.getUserID() + " already exist");
        } else if (Boolean.TRUE.equals(userRepository.existsByEmail(actionRequest.getEmail()))) {
            throw new UserAlreadyExistAuthenticationException("User with email id " + actionRequest.getEmail() + " already exist");
        }
        return userRepository.save(userMapper.convertActionRequestToUser(actionRequest, roleRepository.findByName("ROLE_USER").orElseThrow(() -> new EntityNotFoundException("The entity role not found"))));
    }

    @Override
    public OidcLocalUser processOAuth2User(String clientRegistration, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        User user = getUserProvider(clientRegistration, attributes);
        return OidcLocalUser.create(user, attributes, idToken, userInfo);
    }

    @Override
    public LocalUser processLoadUser(String clientRegistration, Map<String, Object> attributes) {
        User user = getUserProvider(clientRegistration, attributes);
        return LocalUser.create(user, attributes, clientRegistration);
    }

    private User getUserProvider(String clientRegistration, Map<String, Object> attributes) {
        UserProvidersInfo userProvidersInfo = UserProvidersInfoFactory.getUserInfo(clientRegistration, attributes);
        Optional<User> userOptional = customUserDetailsService.findByEmail(userProvidersInfo.getEmail());
        User user;
        if (userOptional.isPresent()) {
            user = updateUserProvider(clientRegistration, userProvidersInfo, userOptional);
        } else {
            user = registerNewUser(userMapper.toUserRegistrationObject(clientRegistration, userProvidersInfo));
        }
        return user;
    }


    private User updateUserProvider(String clientRegistration, UserProvidersInfo userProvidersInfo, Optional<User> userOptional) {
        User user = userOptional.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        if (!user.getProvider().getProviderType().equals(clientRegistration)) {
            throw new ProviderNotSupportedException("Looks like you're signed up with " +
                    user.getProvider() + " account. Please use your " + user.getProvider() +
                    " account to login.");
        }
        user = updateExistingUser(user, userProvidersInfo);
        return user;
    }

    /**
     * Update existing user user.
     *
     * @param existingUser      the existing user
     * @param providersUserInfo the providers user info
     * @return the user
     */
    private User updateExistingUser(User existingUser, UserProvidersInfo providersUserInfo) {
        existingUser.setName(providersUserInfo.getName());
        existingUser.setImageUrl(providersUserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }

    public void saveUser(Optional<User> newUser){
        newUser.ifPresent(userRepository::save);
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void setStripeAccountId(String userId, String stripeAccountId){
        User user = getUserById(userId);
        user.setStipeAccountId(stripeAccountId);
        userRepository.save(user);
    }

    @Override
    public void setStripeCustomerAccount(String userId, String stripeAccountId){
        User user = getUserById(userId);
        user.setStripeCustomerId(stripeAccountId);
        userRepository.save(user);
    }


}

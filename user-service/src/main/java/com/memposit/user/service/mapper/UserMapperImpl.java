package com.memposit.user.service.mapper;

import com.memposit.user.service.entity.User;
import com.memposit.user.service.model.ActionRequest;
import com.memposit.user.service.model.LocalUser;
import com.memposit.user.service.model.UserDto;
import com.memposit.user.service.entity.Role;
import com.memposit.user.service.providers.UserProvidersInfo;

/**
 * The interface User mapper.
 */
public interface UserMapperImpl {

    /**
     * Convert action request to user user.
     *
     * @param actionRequest the action request
     * @param role          the role
     * @return the user
     */
    User convertActionRequestToUser(ActionRequest actionRequest, Role role);

    /**
     * To user registration object action request.
     *
     * @param registrationId    the registration id
     * @param userProvidersInfo the user providers info
     * @return the action request
     */
    ActionRequest toUserRegistrationObject(String registrationId, UserProvidersInfo userProvidersInfo);

    /**
     * Build user info user dto.
     *
     * @param localUser the local user
     * @return the user dto
     */
    UserDto buildUserInfo(LocalUser localUser);

    /**
     * Build user dto user dto.
     *
     * @param actionRequest the action request
     * @param local         the local
     * @return the user dto
     */
    UserDto buildUserDto(ActionRequest actionRequest, LocalUser local);
}

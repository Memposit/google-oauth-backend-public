package com.memposit.user.service.service;

import com.memposit.user.service.entity.User;
import com.memposit.user.service.enums.Provider;
import com.memposit.user.service.model.LocalUser;
import com.memposit.user.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Optional;

/**
 * The User service.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public LocalUser loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
                );

        return createLocalUser(user);
    }


    /**
     * Load user by id user details.
     *
     * @param id the id
     * @return the user details
     */
    public LocalUser loadUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found with id : " + id)
        );
        return user.getProvider().equals(Provider.LOCAL) ? createLocalUser(user) : createOathUser(user);
    }

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private LocalUser createLocalUser(User user) {
        return new LocalUser(user.getEmail(), user.getPassword(), user.getEmailVerified(), true, true, true, Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName())), user);
    }

    private LocalUser createOathUser(User user) {
        return new LocalUser(user.getEmail(), user.getId() + user.getProviderId(), user.getEmailVerified(), true, true, true, Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName())), user);
    }
}

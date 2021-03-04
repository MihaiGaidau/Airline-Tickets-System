package com.airiline.tickets.service.impl;


import com.airiline.tickets.domain.ATSUser;
import com.airiline.tickets.repository.ATSUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ATSUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        ATSUser user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found by username : " + username));

        return new User(user.getUsername(), user.getPassword(), user.getEnabled(),
                true, true, true,
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString())));
    }
}

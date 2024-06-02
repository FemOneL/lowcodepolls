package com.lowcodepolls.surveyView.core.services.impl;

import com.lowcodepolls.surveyView.core.dto.User;
import com.lowcodepolls.surveyView.core.dto.enums.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserRestService  implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(1, "taras", "taras", "email@email.com", "password", Role.USER);
    }
}

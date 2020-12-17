package com.ufrn.embarcados.reaqua.security;

import com.ufrn.embarcados.reaqua.model.ApplicationUser;
import com.ufrn.embarcados.reaqua.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserService applicationUserService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserService.getUserByEmail(email);
        if(applicationUser == null)
            throw new UsernameNotFoundException(String.format("Application user email '%s' not found", email));
        return applicationUser;
    }
}
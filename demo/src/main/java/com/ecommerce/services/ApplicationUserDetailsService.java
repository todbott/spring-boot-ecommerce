package com.ecommerce.services;


import com.ecommerce.database.AppUser;
import com.ecommerce.database.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private UserRepository applicationUserRepository;

    public ApplicationUserDetailsService(UserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser applicationUser = applicationUserRepository.findByUserName(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getName(), applicationUser.getPassword(), emptyList());
    }
}

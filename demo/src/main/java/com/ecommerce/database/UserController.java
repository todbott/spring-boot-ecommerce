package com.ecommerce.database;

import org.springframework.http.MediaType;
//import com.ecommerce.database.User;
//import com.ecommerce.database.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/users")

public class UserController {

    private UserRepository applicationUserRepository;
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(value = "/record", consumes = MediaType.ALL_VALUE)
    public void signUp(@RequestBody AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }
}

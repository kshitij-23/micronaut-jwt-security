package com.ksh.controllers;

import com.ksh.entities.User;
import com.ksh.repositories.UserRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

@Controller
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserController {

    @Inject
    private UserRepository userRepository;

    @Get("/findAll")
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Post("/findByUserAndPassword")
    public User findByUser(@QueryValue String username, @QueryValue String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Post("/save")
    public User save(User user) {
        return userRepository.save(user);
    }

    @Get("/test")
    public User save() {
        User user = new User();
        user.setEmail("ks@ks.com");
        user.setId(1);
        user.setName("Kshitij");
        user.setMobile(9033513017L);
        user.setBirthDate(LocalDateTime.now());
        user.setUsername("kshitij-1");
        user.setPassword("password");
        return user;
    }



}

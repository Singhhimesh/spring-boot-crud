package com.bagisto.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bagisto.demo.entities.User;
import com.bagisto.demo.respsitories.UserRepository;
import com.bagisto.demo.services.impl.UserService;

@Component
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}

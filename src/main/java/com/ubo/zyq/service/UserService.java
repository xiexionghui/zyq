package com.ubo.zyq.service;

import com.ubo.zyq.entity.User;
import com.ubo.zyq.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveOrUpateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Page<User> listUsersByNameLike(String name, Pageable pageable) {
        // 模糊查询
        name = "%" + name + "%";
        return userRepository.findByNameLike(name, pageable);
    }

    public User loadByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}

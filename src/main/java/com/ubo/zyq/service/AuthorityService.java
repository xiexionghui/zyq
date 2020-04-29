package com.ubo.zyq.service;

import com.ubo.zyq.entity.Authority;
import com.ubo.zyq.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    
    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }


    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    public Optional<Authority> getAuthorityById(Long id){
        return authorityRepository.findById(id);
    }

}

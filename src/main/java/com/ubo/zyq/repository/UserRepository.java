package com.ubo.zyq.repository;

import com.ubo.zyq.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Page<User> findByNameLike(String name, Pageable pageable);
}

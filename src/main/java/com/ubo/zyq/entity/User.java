package com.ubo.zyq.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增策略
    private Long id;

    @NotEmpty(message = "姓名不能为空")
    @Size(min = 2, max = 20)
    @Column(nullable = false, length = 20)
    private String name;

    @NotEmpty(message = "账号不能为空")
    @Size(min = 3, max = 20)
    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 100)
    @Column(length = 100)
    private String password;
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private List<Authority> authorities;

}

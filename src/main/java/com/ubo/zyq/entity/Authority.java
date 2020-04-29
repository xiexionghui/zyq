package com.ubo.zyq.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="authority")
public class Authority {

    @Id
    @GeneratedValue
    @Column
    private Long id;
    @Column(nullable = false)
    private String name;
}


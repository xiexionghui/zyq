package com.ubo.zyq.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Table(name="honor")
public class Honor {
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id;
    @Size(max = 256)
    @Column
    private String title1;
    @Size(max = 256)
    @Column
    private String title2;
    @Column
    private String status;
    @Column
    private String img_path;
    @Column
    private Integer queue;
    @Column
    private String type;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createdate;
    
    
}

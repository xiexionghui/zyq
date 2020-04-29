package com.ubo.zyq.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name="news")
public class News implements Serializable {
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; // 用户的唯一标识

    @NotEmpty(message = "标题不能为空")
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String title;

    @NotEmpty(message = "摘要不能为空")
    @Size(min = 2, max = 300)
    @Column(nullable = false)
    private String summary;

    @NotEmpty(message = "分类不能为空")
    @Size(max = 2)
    @Column(nullable = false)
    private String newstype;

    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch = FetchType.LAZY) // 懒加载
    @NotEmpty(message = "内容不能为空")
    @Size(min = 2)
    @Column(nullable = false)
    private String content;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false,name = "createtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "readsize")
    private Integer readSize = 0; // 访问量、阅读量

    @NotEmpty(message = "缩略图不可为空")
    @Column
    private String img;

    @Column
    private String status;

    public News(String title, String summary, String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }


}

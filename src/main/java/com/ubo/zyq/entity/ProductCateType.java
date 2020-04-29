package com.ubo.zyq.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author zyq
 * @date 2020-1-17 16:13
 */
@Entity
@Table(name = "product_cate_type", schema = "uboindex")
public class ProductCateType {
    private Integer cateId;
    private String cateName;
    private Boolean active;
    private List<ProductCate> children;


    @Id
    @Column(name = "cate_id", nullable = false)
    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    @Basic
    @Column(name = "cate_name", nullable = true, length = 64)
    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    @Basic
    @Column(name = "active", nullable = true)
    public Boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Transient
    public List<ProductCate> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCate> children) {
        this.children = children;
    }
}

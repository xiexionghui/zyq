package com.ubo.zyq.entity;

import javax.persistence.*;

/**
 * @author zyq
 * @date 2020-1-16 9:47
 */
@Entity
@Table(name = "product_cate")
public class ProductCate {
    private Integer cateId;
    private String cateName;
    private Integer cateType;
    private Boolean active;
    private String cateTypeText;

    @Id
    @Column(name = "cate_id", nullable = false)
    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    @Basic
    @Column(name = "cate_name", nullable = false, length = 64)
    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    @Basic
    @Column(name = "cate_type", nullable = false, length = 16)
    public Integer getCateType() {
        return cateType;
    }

    public void setCateType(Integer cateType) {
        this.cateType = cateType;
    }

    @Basic
    @Column(name = "active", nullable = false)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Transient
    public String getCateTypeText() {
        return cateTypeText;
    }

    public void setCateTypeText(String cateTypeText) {
        this.cateTypeText = cateTypeText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductCate that = (ProductCate) o;

        if (cateId != that.cateId) return false;
        if (active != that.active) return false;
        if (cateName != null ? !cateName.equals(that.cateName) : that.cateName != null) return false;
        if (cateType != null ? !cateType.equals(that.cateType) : that.cateType != null) return false;

        return true;
    }

}

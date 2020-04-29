package com.ubo.zyq.entity;

import javax.persistence.*;

/**
 * @author zyq
 * @date 2020-1-17 14:07
 */
@Entity
@Table(name = "product_detail", schema = "uboindex")
public class ProductDetail {
    private String productId;
    private int cateId;
    private String productName;
    private String productDesc;
    private String imgInTable;
    private String img1InDetail;
    private String img2InDetail;
    private String img3InDetail;
    private String img4InDetail;
    private String img5InDetail;
    private String detailHtml;
    private Boolean active;
    private String cateText;

    @Id
    @Column(name = "product_id", nullable = false, length = 64)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "cate_id", nullable = false)
    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    @Basic
    @Column(name = "product_name", nullable = false, length = 128)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "product_desc", nullable = true, length = 256)
    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    @Basic
    @Column(name = "img_in_table", nullable = true, length = 256)
    public String getImgInTable() {
        return imgInTable;
    }

    public void setImgInTable(String imgInTable) {
        this.imgInTable = imgInTable;
    }

    @Basic
    @Column(name = "img1_in_detail", nullable = true, length = 256)
    public String getImg1InDetail() {
        return img1InDetail;
    }

    public void setImg1InDetail(String img1InDetail) {
        this.img1InDetail = img1InDetail;
    }

    @Basic
    @Column(name = "img2_in_detail", nullable = true, length = 256)
    public String getImg2InDetail() {
        return img2InDetail;
    }

    public void setImg2InDetail(String img2InDetail) {
        this.img2InDetail = img2InDetail;
    }

    @Basic
    @Column(name = "img3_in_detail", nullable = true, length = 256)
    public String getImg3InDetail() {
        return img3InDetail;
    }

    public void setImg3InDetail(String img3InDetail) {
        this.img3InDetail = img3InDetail;
    }

    @Basic
    @Column(name = "img4_in_detail", nullable = true, length = 256)
    public String getImg4InDetail() {
        return img4InDetail;
    }

    public void setImg4InDetail(String img4InDetail) {
        this.img4InDetail = img4InDetail;
    }

    @Basic
    @Column(name = "img5_in_detail", nullable = true, length = 256)
    public String getImg5InDetail() {
        return img5InDetail;
    }

    public void setImg5InDetail(String img5InDetail) {
        this.img5InDetail = img5InDetail;
    }

    @Basic
    @Column(name = "detail_html", nullable = true, length = 10240)
    public String getDetailHtml() {
        return detailHtml;
    }

    public void setDetailHtml(String detailHtml) {
        this.detailHtml = detailHtml;
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
    public String getCateText() {
        return cateText;
    }

    public void setCateText(String cateText) {
        this.cateText = cateText;
    }
}

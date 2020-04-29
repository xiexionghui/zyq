package com.ubo.zyq.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "t_product")
/**
 * 产品信息表
 * 
 * @author 谢雄辉 2020年4月2日
 */
public class Product {

	/**
	 * 产品表主键id
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	/**
	 * 产品名字
	 */
	@Column(name = "productName")

	public String productName;

	/**
	 * 产品图片
	 */
	@Column(name = "productImages")

	public String productImages;

	/**
	 * 产品信息介绍
	 */
	@Column(name = "productSeries")

	public String productSeries;

	/**
	 * 材质
	 */
	@Column(name = "productMaterial")

	public String productMaterial;

	/**
	 * 创建时间
	 */
	@Column(name = "createTime")
	public Date createTime;

	/**
	 * 备注
	 */
	@Column(name = "reMark")
	public String remark;

	/**
	 * 版本号
	 */
	@Column(name = "versionNo")
	public Integer versionNo;
	
	/**
	 * 产品状态
	 */
	@Column(name="status")
	public Integer status;
	
	
	/**
	 * 产品类型 1-5 代表某些类型 
	 */
	@Column(name="productType")
	public Integer productType;
}

package com.ubo.zyq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="t_enterpriseinformation")
public class EnterPriseinFormation {
	
	/**
	 * 主键id
	 * @return
	 */
	
	@Column(name="id")
	@Id // 主键
    // 自增长注解 @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	/**
	 * 图片logo1
	 */
	
	public String logo;
	
	/**
	 * ,第二个logo图片
	 */
	public String logotwo;
	
	
	/**
	 * 浏览器标题
	 */
	public String browserTitle;
	
	/**
	 * 副标题
	 */
	public String subHeading;
	
	/**
	 * 全国服务热线
	 */
	public String serviceRe;
	
	/**
	 * 备案号
	 */
	public String recordNumber;
	
	/**
	 * 底部服务热线
	 */
	public String bottomServiceHotline;
	
	
	/**
	 * 公司地址
	 */
	public String companyaddress;
	
	
	/**
	 * 公司邮箱
	 */
	public String companyEmail;
	
	

}

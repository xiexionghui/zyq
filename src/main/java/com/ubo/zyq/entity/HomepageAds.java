package com.ubo.zyq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="t_homepageads")
public class HomepageAds {
	
	/**
	 * 主键id
	 * @return
	 */
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	/**
	 * 首页广告轮番图
	 */
	
	@Column(name="logo")
	public String logo;
	
	
	
	
	
	
	
	
	

}

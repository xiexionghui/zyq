/**
 * 谢雄辉
 *2020年4月10日
 */
package com.ubo.zyq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * ,联系我们 实体表
 * @author 谢雄辉
 * 2020年4月10日  
 */

@Data
@Table(name="t_contactus")
@Entity
public class ContactUs {
	
	/**
	 *  .主键id 自增长
	 */
	
	@Column(name="id")
	@Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	/**
	 *     .图片
	 */
	public String images;
	
	/**
	 * .客户服务热线
	 */
	public String customerServiceHotline;
	
	
	/**
	 * .一般查询
	 */
	public String generalQuery;

	/**
	 * .地址
	 */
	
	public String address;
	
	/**
	 * .企业邮箱
	 */
	public String enterpriseMailbox;
	
	/**
	 * .媒体查询
	 */
	public String mediaQuery;

	
	/**
	 * .联络电话
	 */
	
	public String contactTelephoneNumber;
	
	/**
	 * .业务QQ
	 */
	public Integer businessQQ;
	
	/**
	 * .电邮
	 */
	public String email;
	




	





}

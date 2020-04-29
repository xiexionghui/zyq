package com.ubo.zyq.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubo.zyq.entity.ContactUs;
import com.ubo.zyq.entity.DataTableResult;
import com.ubo.zyq.entity.Response;
import com.ubo.zyq.repository.ContactusRepository;

@Service
public class ContactUsService {
    
	
	@Autowired
	ContactusRepository contactusRepository;
	
	
	/**
	 * 根据id查询联系我们表
	 * @param id
	 * @return
	 */
	public ContactUs findById(Long id) {
		
		 Optional<ContactUs> optional = contactusRepository.findById(id);
		   ContactUs us = optional.get();
		return us;
		
		
		
	}
	
	/**
	 * 查询全部,因为只有一条数据,所以不做分页处理
	 * @return
	 */
	public DataTableResult findAllContactUs(Pageable pageable
			){
		
		  
		Page<ContactUs> page = contactusRepository.findAll(pageable);
		
		return new DataTableResult(0,"",page.getTotalElements(),page.getContent());		
	}
	
	/**
	 * 编辑联系我们
	 */
	
	public Response save(ContactUs us) {
		  Optional<ContactUs> optional = contactusRepository.findById(us.getId());
		
		if(optional.isPresent()) {
			 ContactUs contactUs = optional.get();
			 contactUs.setAddress(us.getAddress());
			 contactUs.setBusinessQQ(us.getBusinessQQ());
			 contactUs.setContactTelephoneNumber(us.getContactTelephoneNumber());
			 contactUs.setCustomerServiceHotline(us.getCustomerServiceHotline());
			 contactUs.setEmail(us.getEmail());
			 contactUs.setEnterpriseMailbox(us.getEnterpriseMailbox());
			 contactUs.setGeneralQuery(us.getGeneralQuery());
			 contactUs.setId(us.getId());
			 contactUs.setImages(us.getImages());
			 contactUs.setMediaQuery(us.getMediaQuery());
			 contactusRepository.save(contactUs);
			return new Response(true,"编辑成功");
		}
		return new Response(false, "所提交的内容不允许为空");
		
		
				
	}
	
	


   
}

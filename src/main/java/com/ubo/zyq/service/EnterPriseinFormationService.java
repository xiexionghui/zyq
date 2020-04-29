package com.ubo.zyq.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.ubo.zyq.entity.EnterPriseinFormation;
import com.ubo.zyq.repository.EnterPriseinFormationRepository;

@Service
public class EnterPriseinFormationService {

	@Autowired
	EnterPriseinFormationRepository enterPriseinFormationRepository;

	/**
	 * 根据id查询,只要一条数据 更改网站信息固定id为1
	 * 
	 * @return
	 */

	@Query(value = "select * from t_enterpriseinformation where id = ?1", nativeQuery = true)
	public Optional<EnterPriseinFormation> getEnterPriseinFormationRepositoryById(Long id) {
		return enterPriseinFormationRepository.findById(id);
	}

	
	public Page<EnterPriseinFormation> getEnterPriseinFormationList(Pageable pageable) {
		return enterPriseinFormationRepository.findAll(pageable);
	}

	public EnterPriseinFormation updateEnterpriseinformation(EnterPriseinFormation entity) {
		
		return enterPriseinFormationRepository.save(entity);
		
		
	}
	
	
	public EnterPriseinFormation save(EnterPriseinFormation enterPriseinFormation) {
	
	return enterPriseinFormationRepository.save(enterPriseinFormation);
	}
}

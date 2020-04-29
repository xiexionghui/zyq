package com.ubo.zyq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubo.zyq.entity.EnterPriseinFormation;

@Repository
public interface EnterPriseinFormationRepository extends JpaRepository<EnterPriseinFormation, Long> {
	
	
}

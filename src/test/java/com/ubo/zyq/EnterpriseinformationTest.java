package com.ubo.zyq;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ubo.zyq.entity.EnterPriseinFormation;
import com.ubo.zyq.service.EnterPriseinFormationService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EnterpriseinformationTest {

	
	@Autowired
	EnterPriseinFormationService enterPriseinFormationService;
	
	
	@Test
	public void getYearMonthDay() {
		
		 LocalDate getDate = LocalDate.now();
		log.info("获取当前时间{}",getDate);
		
		
		/**
		 * 通过localdatetime获取时间 包含年月日时分秒
		 */
		
		LocalDateTime dateTime = LocalDateTime.now();
		log.info("获取当前时间包含时分秒{}",dateTime);
	}
	
	
	@Test
	public void findById() {
		
	}
	
}

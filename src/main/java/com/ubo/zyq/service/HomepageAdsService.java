
package com.ubo.zyq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubo.zyq.entity.DataTableResult;
import com.ubo.zyq.entity.HomepageAds;
import com.ubo.zyq.repository.HomepageAdsRepository;

/**
 * @author 谢雄辉 2020年4月14日
 */

@Service
public class HomepageAdsService {

	@Autowired
	HomepageAdsRepository homepageAdsRepository;

	/**
	 * 查询广告轮番图 带分页,实际上可以不用做分页处理
	 * 
	 * @param pageable
	 * @return
	 */
	public DataTableResult findBylist(Pageable pageable) {

		Page<HomepageAds> page = homepageAdsRepository.findAll(pageable);

		return new DataTableResult(0,"",page.getTotalElements(),page.getContent());
	}
	
	
	/**
	 * 前台展示广告轮番图 
	 */
	
	public List<HomepageAds> findAll(){
		
		List<HomepageAds> homepageAds = homepageAdsRepository.findAll();
		if(homepageAds.size()<0) {
			homepageAds = new ArrayList<>();
		}
		return homepageAds;
	}
	
	/**
	 * 保存
	 * @param homepageAds
	 * @return
	 */
	@Transactional
	public HomepageAds save(HomepageAds homepageAds) {
		return homepageAdsRepository.save(homepageAds);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Optional<HomepageAds> findById(Long id) {
		return homepageAdsRepository.findById(id);
	}
	
	

}

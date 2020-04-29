package com.ubo.zyq.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ubo.zyq.entity.Product;

public interface ProductXRepository extends JpaRepository<Product, Long> {
	/*
	 * Page<Product> findByNewstypeAndStatusOrderByCreateTimeDesc(String
	 * status,Pageable pageable);
	 */

    Page<Product> findByStatusOrderByCreateTimeDesc(Integer status, Pageable pageable);
    
    
    
    /**
     *   @author 谢雄辉,其他推荐 随机数3条产品信息
     *   
     */
    @Query(value="select * from t_product where status = 10 ORDER BY RAND() LIMIT 4",nativeQuery=true)
     List<Product> findProductRandomLimitThere();
    
      
    
   
    /**
     * @author 谢雄辉
     * 查询产品表 条件是 状态为10,对产品类型进行选择,对时间倒序,最后添加的产品信息排在最前面
     * @param productType
     * @return
     */
    @Query(value="select * from t_product where status = 10 and productType =?1 order by createTime desc limit 8",nativeQuery=true)
    List<Product> findProductConditionProductTypeAndStatusOrderByCreateTime(Integer productType);
    
    
    
    
  
    
    
    
}

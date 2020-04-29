package com.ubo.zyq.repository;

import com.ubo.zyq.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zyq
 * @date 2020-1-17 14:11
 */
@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, String>, JpaSpecificationExecutor<ProductDetail> {
    List<ProductDetail> findByCateIdInAndActiveIsTrueOrderByCateIdDescProductIdDesc(List<Short> cateId);

    @Modifying
    @Transactional
    @Query("update ProductDetail p set p.active=?1 where p.productId=?2")
    Integer updateActiveByProductId(boolean active, String productId);
}

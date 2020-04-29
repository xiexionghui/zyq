package com.ubo.zyq.repository;

import com.ubo.zyq.entity.ProductCate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author zyq
 * @date 2020-1-15 14:56
 */
@Repository
public interface ProductCateRepository extends JpaRepository<ProductCate, Integer> {
    Page<ProductCate> findByActiveOrderByCateIdDesc(boolean isActive, Pageable pageable);

    @Query(value = "select MAX(p.cateId) from ProductCate p")
    Integer findMaxCateId();

}

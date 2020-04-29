package com.ubo.zyq.repository;

import com.ubo.zyq.entity.ProductCateType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zyq
 * @date 2020-1-17 16:15
 */
@Repository
public interface ProductCateTypeRepository extends JpaRepository<ProductCateType, Integer> {
    Page<ProductCateType> findByActiveOrderByCateIdDesc(boolean isActive, Pageable pageable);

    List<ProductCateType> findAll();

    @Query(value = "select MAX(p.cateId) from ProductCateType p")
    Integer findMaxCateId();
}

package com.ubo.zyq.repository;

import com.ubo.zyq.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findByNewstypeAndStatusOrderByCreateTimeDesc(String newstype, String status,Pageable pageable);

    Page<News> findByStatusOrderByCreateTimeDesc(String status, Pageable pageable);
}

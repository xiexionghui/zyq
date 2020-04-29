package com.ubo.zyq.service;

import com.ubo.zyq.entity.News;
import com.ubo.zyq.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    private final NewsRepository newsRepository;


    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Page<News> listNewsByNewstype(String newstype, Pageable pageable) {
        return newsRepository.findByNewstypeAndStatusOrderByCreateTimeDesc(newstype, "0",pageable);
    }

    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }

    @Transactional
    public void readingIncrease(Long id) {
        Optional<News> news = newsRepository.findById(id);
        News news1;
        if (news.isPresent()) {
            news1 = news.get();
            news1.setReadSize(news1.getReadSize() + 1);
            newsRepository.save(news1);
        }
    }

    @Transactional
    public News save(News news) {
        return newsRepository.save(news);
    }



    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }

    public Page<News> findByStatusOrderByCreateTimeDesc(String status, Pageable pageable) {
        return newsRepository.findByStatusOrderByCreateTimeDesc(status, pageable);
    }
}

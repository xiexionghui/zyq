package com.ubo.zyq.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubo.zyq.entity.Product;
import com.ubo.zyq.repository.ProductXRepository;

@Service
public class ProductService {
    private final ProductXRepository productXRepository;


    @Autowired
    public ProductService(ProductXRepository productXRepository) {
        this.productXRepository = productXRepository;
    }

	/*
	 * public Page<News> listNewsByNewstype(String newstype, Pageable pageable) {
	 * return newsRepository.findByNewstypeAndStatusOrderByCreateTimeDesc(newstype,
	 * "0",pageable); }
	 */

    public Optional<Product> getProductById(Long id) {
        return productXRepository.findById(id);
    }


    @Transactional
    public Product save(Product product) {
        return productXRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productXRepository.findById(id);
    }

    public Page<Product> listProductByProducttype(Integer status, Pageable pageable) {
        return productXRepository.findByStatusOrderByCreateTimeDesc(status, pageable);
    }
    
   public List<Product> findProductRandomLimitThere(){
	   return productXRepository.findProductRandomLimitThere();
   }
   
  public List<Product> findProductConditionProductTypeAndStatusOrderByCreateTime(Integer productType){
	  return productXRepository.findProductConditionProductTypeAndStatusOrderByCreateTime(productType);
  }
  
  /**
   * ,查询产品表全部信息带分页处理
   * @param status
   * @param pageable
   * @return
   */
  public Page<Product> findByProduct(Pageable pageable){
	  
	  return productXRepository.findAll(pageable);
  }

   
}

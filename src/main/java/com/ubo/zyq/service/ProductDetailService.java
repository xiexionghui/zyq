package com.ubo.zyq.service;

import com.ubo.zyq.entity.DataTableResult;
import com.ubo.zyq.entity.ProductCate;
import com.ubo.zyq.entity.ProductDetail;
import com.ubo.zyq.entity.Response;
import com.ubo.zyq.repository.ProductCateRepository;
import com.ubo.zyq.repository.ProductDetailRepository;
import com.ubo.zyq.util.ZYQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author zyq
 * @date 2020-1-17 14:39
 */
@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductCateRepository cateRepository;

    public void getProductTabs(List<Short> cateId) {
        List<ProductDetail> detailList = productDetailRepository.findByCateIdInAndActiveIsTrueOrderByCateIdDescProductIdDesc(cateId);

    }

    public ProductDetail find(String productId) {
        if (StringUtils.isEmpty(productId)) {
            return new ProductDetail();
        }
        Optional<ProductDetail> productDetail = productDetailRepository.findById(productId);
        return productDetail.orElse(new ProductDetail());
    }

    public Response save(ProductDetail productDetail) {
        if (StringUtils.isEmpty(productDetail.getProductId())) {
            productDetail.setProductId(ZYQ.getUUID());
            productDetail.setActive(true);
        }
        productDetailRepository.save(productDetail);
        return new Response(true, "保存完成");
    }

    public Response invalidProduct(ProductDetail productDetail) {
        productDetailRepository.updateActiveByProductId(productDetail.getActive(), productDetail.getProductId());
        return new Response(true, "已作废产品");
    }

    public DataTableResult find(String productName, Integer cateId, int page, int size) {
        Page<ProductDetail> productDetailPage = productDetailRepository.findAll((Root<ProductDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(productName)) {
                predicates.add(cb.like(root.get("productName").as(String.class), "%" + productName + "%"));
            }
            if (cateId != null) {
                predicates.add(cb.equal(root.get("cateId").as(Integer.class), cateId));
            }
            predicates.add(cb.equal(root.get("active").as(Boolean.class), true));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, PageRequest.of(page - 1, size, Sort.by("productId").descending()));
        List<ProductDetail> detailList = productDetailPage.getContent();
        List<ProductCate> cateList = cateRepository.findAll();
        for (ProductDetail detail : detailList) {
            for (ProductCate cate : cateList) {
                if (detail.getCateId() == cate.getCateId()) {
                    detail.setCateText(cate.getCateName());
                    break;
                }
            }
        }
        return new DataTableResult(0, "查询完成", productDetailPage.getTotalElements(), detailList);
    }
}

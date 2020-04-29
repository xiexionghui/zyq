package com.ubo.zyq.service;

import com.ubo.zyq.entity.DataTableResult;
import com.ubo.zyq.entity.ProductCate;
import com.ubo.zyq.entity.ProductCateType;
import com.ubo.zyq.entity.Response;
import com.ubo.zyq.repository.ProductCateRepository;
import com.ubo.zyq.repository.ProductCateTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author zyq
 * @date 2020-1-15 15:00
 */
@Service
public class ProductCateService {
    @Autowired
    private ProductCateRepository cateRepository;
    @Autowired
    private ProductCateTypeRepository cateTypeRepository;

    public DataTableResult getProductCateTable(int currPage, int limit) {
        Page<ProductCate> catePage = cateRepository.findByActiveOrderByCateIdDesc(true, PageRequest.of(currPage - 1, limit));
        List<ProductCateType> cateTypeList = cateTypeRepository.findAll();
        List<ProductCate> cateList = catePage.getContent();
        for (ProductCate cate : cateList) {
            for (ProductCateType cateType : cateTypeList) {
                if (cate.getCateType().equals(cateType.getCateId())) {
                    cate.setCateTypeText(cateType.getCateName());
                    break;
                }
            }
        }
        return new DataTableResult(0, null, catePage.getTotalElements(), cateList);
    }

    public List<ProductCateType> getTabCardTextAndChildCate() {
        Page<ProductCateType> cateTypePage = cateTypeRepository.findByActiveOrderByCateIdDesc(true, PageRequest.of(0, 20));
        Page<ProductCate> catePage = cateRepository.findByActiveOrderByCateIdDesc(true, PageRequest.of(0, 100));
        List<ProductCateType> cateTypeList = cateTypePage.getContent();
        Map<Integer, List<ProductCate>> cateMap = catePage.getContent().stream().collect(Collectors.groupingBy(ProductCate::getCateType));

        for (ProductCateType cateType : cateTypeList) {
            cateType.setChildren(cateMap.getOrDefault(cateType.getCateId(), Collections.emptyList()));
        }
        return cateTypeList;
    }

    public Response saveCate(ProductCate productCate) {
        if (productCate.getCateId() == null) {
            Integer maxId = cateRepository.findMaxCateId();
            productCate.setCateId(maxId == null ? 1 : maxId + 1);
            productCate.setActive(true);
        }
        cateRepository.save(productCate);
        return new Response(true, "保存成功");
    }

    public List<ProductCate> getProductCateActiveList() {
        Page<ProductCate> catePage = cateRepository.findByActiveOrderByCateIdDesc(true, PageRequest.of(0, 50));
        return catePage.getContent();
    }
}

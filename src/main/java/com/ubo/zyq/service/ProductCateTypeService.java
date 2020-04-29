package com.ubo.zyq.service;

import com.ubo.zyq.entity.DataTableResult;
import com.ubo.zyq.entity.ProductCateType;
import com.ubo.zyq.entity.Response;
import com.ubo.zyq.repository.ProductCateTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author zyq
 * @date 2020-1-17 16:40
 */
@Service
public class ProductCateTypeService {
    @Autowired
    private ProductCateTypeRepository productCateTypeRepository;

    public DataTableResult getProductCateTable(int currPage, int limit) {
        Page<ProductCateType> catePage = productCateTypeRepository.findByActiveOrderByCateIdDesc(true, PageRequest.of(currPage - 1, limit));
        return new DataTableResult(0, null, catePage.getTotalElements(), catePage.getContent());
    }

    public Response saveCate(ProductCateType productCateType) {
        if (productCateType.getCateId() == null) {
            Integer maxId = productCateTypeRepository.findMaxCateId();
            productCateType.setCateId(maxId == null ? 1 : maxId + 1);
            productCateType.setActive(true);
        }
        productCateTypeRepository.save(productCateType);
        Page<ProductCateType> catePage = productCateTypeRepository.findByActiveOrderByCateIdDesc(true, PageRequest.of(0, 20));
        return new Response(true, "保存成功", catePage.getContent());
    }
}

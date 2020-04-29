package com.ubo.zyq.controller.admin;

import com.ubo.zyq.entity.*;
import com.ubo.zyq.service.ProductCateService;
import com.ubo.zyq.service.ProductCateTypeService;
import com.ubo.zyq.service.ProductDetailService;
import com.ubo.zyq.util.PARAM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author zyq
 * @date 2020-1-15 15:07
 */
@Controller
@RequestMapping("/management/product")
@Slf4j
public class ProductManageController {

    @Autowired
    private ProductCateService cateService;
    @Autowired
    private ProductCateTypeService cateTypeService;
    @Autowired
    private ProductDetailService detailService;

    @GetMapping("/getCatePage")
    public String getCatePage(Model model) {
        DataTableResult dataTableResult = cateTypeService.getProductCateTable(1, 20);
        model.addAttribute("cateType", dataTableResult.getData());
        return "admin/product-cate";
    }

    @GetMapping("/getProductCateTable")
    @ResponseBody
    public DataTableResult getProductCateTable(@RequestParam int limit, @RequestParam int page) {
        return cateService.getProductCateTable(page, limit);
    }

    @PostMapping("/saveCate")
    @ResponseBody
    public Response saveCate(@RequestBody ProductCate productCate) {
        try {
            return cateService.saveCate(productCate);
        } catch (Exception e) {
            log.error("saveCate", e);
            return new Response(false, "保存出错");
        }
    }

    @GetMapping("/getProductCateTypeTable")
    @ResponseBody
    public DataTableResult getProductCateTypeTable(@RequestParam int limit, @RequestParam int page) {
        return cateTypeService.getProductCateTable(page, limit);
    }

    @PostMapping("/saveCateType")
    @ResponseBody
    public Response saveCateType(@RequestBody ProductCateType cateType) {
        try {
            return cateTypeService.saveCate(cateType);
        } catch (Exception e) {
            log.error("saveCateType", e);
            return new Response(false, "保存出错");
        }
    }

    @GetMapping("/productDetail")
    public String productDetail(Model model) {
        List<ProductCate> cateSelectList = cateService.getProductCateActiveList();
        model.addAttribute("cateSelectList", cateSelectList);
        return "admin/product-detail";
    }

    @GetMapping("/getProductDetailTable")
    @ResponseBody
    public DataTableResult getProductDetailTable(@RequestParam int limit, @RequestParam int page,
                                                 @RequestParam(required = false) String productName,
                                                 @RequestParam(required = false) Integer cateId) {
        return detailService.find(productName, cateId, page, limit);
    }

    @GetMapping("/productFrame")
    public String productFrame(Model model, @RequestParam(required = false) String productId) {
        List<ProductCate> cateSelectList = cateService.getProductCateActiveList();
        model.addAttribute("cateSelectList", cateSelectList);
        ProductDetail product = detailService.find(productId);
        model.addAttribute("product", product);
        return "admin/product-frame";
    }

    @PostMapping("/uploadProductImgFirst")
    @ResponseBody
    public LayuiMsg uploadProductImgFirst(@RequestPart MultipartFile file) throws IllegalStateException, IOException {
        String realName = "";
        if (file != null) {
            String fileName = file.getOriginalFilename();
            String fileNameExtension = fileName.substring(fileName.lastIndexOf("."));
            realName = UUID.randomUUID().toString() + fileNameExtension;
            File uploadFile = new File(PARAM.PRODUCT_IMG_PATH, realName);
            file.transferTo(uploadFile);
        } else {
            return new LayuiMsg(1, "上传失败，未能接收到图片", null);
        }
        return new LayuiMsg(0, "上传完成", "/getProductImg/" + realName);
    }

    @PostMapping("/uploadTextareaImg")
    @ResponseBody
    public imgResp uploadTextareaImg(@RequestPart MultipartFile file) throws IllegalStateException, IOException {
        String location = "";
        if (file != null) {
            String fileName = file.getOriginalFilename();
            String fileNameExtension = fileName.substring(fileName.lastIndexOf("."));
            location = UUID.randomUUID().toString() + fileNameExtension;
            File uploadFile = new File(PARAM.PRODUCT_IMG_PATH, location);
            file.transferTo(uploadFile);
        }
        return new imgResp("/getProductImg/" + location);
    }

    @PostMapping("/saveProductDetail")
    @ResponseBody
    public Response saveProductDetail(@RequestBody ProductDetail productDetail) {
        try {
            return detailService.save(productDetail);
        } catch (Exception e) {
            log.error("保存产品信息异常", e);
            return new Response(false, "保存产品信息异常");
        }
    }

    @PostMapping("/invalidProduct")
    @ResponseBody
    public Response invalidProduct(@RequestBody ProductDetail productDetail) {
        try {
            return detailService.invalidProduct(productDetail);
        } catch (Exception e) {
            log.error("作废产品异常", e);
            return new Response(false, "作废产品异常");
        }
    }

    class imgResp {
        private String location;

        imgResp(String location) {
            this.location = location;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}

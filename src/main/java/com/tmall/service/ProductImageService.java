package com.tmall.service;

import com.tmall.dao.ProductImageDAO;
import com.tmall.pojo.OrderItem;
import com.tmall.pojo.Product;
import com.tmall.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {
    public static final String type_single = "single";
    public static final String type_detail = "detail";
    @Autowired
    ProductImageDAO productImageDAO;
    @Autowired
    ProductService productService;

    public void add(ProductImage bean) {
        productImageDAO.save(bean);
    }

    public void delete(int id) {
        productImageDAO.delete(id);
    }

    public ProductImage get(int id) {
        return productImageDAO.findOne(id);
    }

    public List<ProductImage> listSingleProductImage(Product product) {
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product, type_single);
    }

    public List<ProductImage> listDetailProductImage(Product product) {
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product, type_detail);
    }

    public void setFirstProductImages(Product product) {
        List<ProductImage> singleImages = listSingleProductImage(product);
        if (!singleImages.isEmpty()) {
            product.setFirstProductImage(singleImages.get(0));
        }
        else {
            product.setFirstProductImage(new ProductImage());
        }
    }

    public void setFirstProductImages(List<Product> products) {
        for (Product product : products) {
            setFirstProductImages(product);
        }
    }

    public void setFirstProductImageOnOrderItem(List<OrderItem> ois) {
        for (OrderItem oi : ois) {
            setFirstProductImages(oi.getProduct());
        }
    }


}

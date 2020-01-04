package com.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@Document(indexName = "tmall_springboot", type = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @ManyToOne
    @JoinColumn(name = "cid")
    Category category;

    private String name;
    private String subTitle;
    private float originalPrice;
    private float promotePrice;
    private int stock;
    private Date createDate;
    @Transient
    private ProductImage firstProductImage;
    @Transient
    private List<ProductImage> productSingleImages;
    @Transient
    private List<ProductImage> productDetailImages;
    @Transient
    private int reviewCount;
    @Transient
    private int saleCount;

    public int getReviewCount() {
        return reviewCount;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public List<ProductImage> getProductDetailImages() {
        return productDetailImages;
    }

    public List<ProductImage> getProductSingleImages() {
        return productSingleImages;
    }

    public void setProductDetailImages(List<ProductImage> productDetailImages) {
        this.productDetailImages = productDetailImages;
    }

    public void setProductSingleImages(List<ProductImage> productSingleImages) {
        this.productSingleImages = productSingleImages;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public ProductImage getFirstProductImage() {
        return firstProductImage;
    }

    public void setFirstProductImage(ProductImage firstProductImage) {
        this.firstProductImage = firstProductImage;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public float getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(float promotePrice) {
        this.promotePrice = promotePrice;
    }
    @Override
    public String toString() {
        return "Product [id=" + id + ", category=" + category + ", name=" + name + ", subTitle=" + subTitle
                + ", originalPrice=" + originalPrice + ", promotePrice=" + promotePrice + ", stock=" + stock
                + ", createDate=" + createDate + ", firstProductImage=" + firstProductImage + ", reviewCount="
                + reviewCount + ", saleCount=" + saleCount + "]";
    }
}

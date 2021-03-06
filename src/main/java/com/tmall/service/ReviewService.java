package com.tmall.service;

import com.tmall.dao.ReviewDAO;
import com.tmall.pojo.Product;
import com.tmall.pojo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewDAO reviewDAO;
    @Autowired
    ProductService productService;

    public void add(Review review) {
        reviewDAO.save(review);
    }

    public List<Review> list(Product product) {
        List<Review> reviews = reviewDAO.findByProductOrderByIdDesc(product);
        return reviews;
    }

    public int getCount(Product product) {
        return reviewDAO.countByProduct(product);
    }
}

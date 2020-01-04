package com.tmall.web;

import com.tmall.pojo.*;
import com.tmall.service.*;
import com.tmall.util.Result;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.persistence.ManyToOne;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ForeRESTController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ReviewService reviewService;

    @GetMapping("/forehome")
    public Object home() {
        List<Category> cs = categoryService.list();
        productService.fill(cs);
        productService.fillByRow(cs);
        categoryService.removeCategoryFromProduct(cs);
        return cs;
    }

    @PostMapping("foreregister")
    public Object register(@RequestBody User user) {
        String name = user.getName();
        String password = user.getPassword();

        //转义name
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExit(name);
        if (exist) {
            String message = "用户名重复，请重新输入";
            return Result.fail(message);
        }
        user.setPassword(password);
        userService.add(user);
        return Result.success();
    }

    @PostMapping("/forelogin")
    public Object login(@RequestBody User userParam, HttpSession session) {
        String name = HtmlUtils.htmlEscape(userParam.getName());
        System.out.println("查看传入的name:" + name);
        User user = userService.get(name, userParam.getPassword());
        if (null == user) {
            String message = "账号或密码错误";
            return Result.fail(message);
        } else {
            session.setAttribute("user", user);
            return Result.success();
        }
    }

    @GetMapping("/foreproduct/{pid}")
    public Object product(@PathVariable("pid") int pid) {
        Product product = productService.get(pid);

        List<ProductImage> productSingleImages = productImageService.listSingleProductImage(product);
        List<ProductImage> productDetailImages = productImageService.listDetailProductImage(product);
        product.setProductSingleImages(productSingleImages);
        product.setProductDetailImages(productDetailImages);

        List<PropertyValue> pvs = propertyValueService.list(product);
        List<Review> reviews = reviewService.list(product);
        productService.setSaleAndReviewNumber(product);
        productImageService.setFirstProductImages(product);

        Map<String, Object> map = new HashMap<>();
        map.put("product", product);
        map.put("pvs", pvs);
        map.put("reviews", reviews);
        return Result.success(map);
    }
}

package com.tmall.web;

import com.tmall.pojo.Category;
import com.tmall.pojo.User;
import com.tmall.service.CategoryService;
import com.tmall.service.ProductService;
import com.tmall.service.UserService;
import com.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
public class ForeRESTController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

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
}

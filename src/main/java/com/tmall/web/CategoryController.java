package com.tmall.web;

import com.tmall.pojo.Category;
import com.tmall.service.CategoryService;
import com.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public Page4Navigator<Category> list(@RequestParam(value = "start",defaultValue = "0")
              int start, @RequestParam(value = "size",defaultValue = "5")int size) throws Exception{
        start = start < 0 ? 0 : start;
        // navigatePage:表示导航分页最多有五个[1,2,3,4,5],size表示每一页展示的数据条数
        Page4Navigator<Category> page = categoryService.list(start, size, 5);
        return page;
    }
}
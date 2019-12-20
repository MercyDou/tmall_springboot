package com.tmall.service;

import com.tmall.dao.CategoryDAO;
import com.tmall.pojo.Category;
import com.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> list() {
        //创建一个sort对象，通过id倒序排序，然后通过categoryDao进行查询。
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    public Page4Navigator<Category> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        //Pageable是接口，PageRequest是接口实现
        //在JPA中提供了很方便的分页功能，那就是Pageable（org.springframework.data.domain.Pageable）
        // 以及它的实现类PageRequest（org.springframework.data.domain.PageRequest）
        //PageRequest的对象构造函数有多个，page是页数，初始值是0，size是查询结果的条数，后两个参数参考Sort对象的构造方法
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = categoryDAO.findAll(pageable);

        //pagefromJPA是查询出的所有数据，navigatePages=5
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    public void add(Category bean) {
        categoryDAO.save(bean);
    }

    public void delete(int id) {
        categoryDAO.delete(id);
    }

    public Category get(int id) {
        Category c = categoryDAO.findOne(id);
        return c;
    }

    public void update(Category bean) {
        categoryDAO.save(bean);
    }
}

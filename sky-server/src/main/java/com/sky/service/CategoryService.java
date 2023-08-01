package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);
    void deleteById(Long id);
    void StartOrStop(Integer status,Long id);
    void update(CategoryDTO categoryDTO);
    void add(CategoryDTO categoryDTO);
    List<Category> getList(Integer type);
}

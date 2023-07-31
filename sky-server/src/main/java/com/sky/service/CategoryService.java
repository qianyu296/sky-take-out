package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

public interface CategoryService {
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);
    void deleteById(Long id);
    void StartOrStop(Integer status,Long id);
    void update(CategoryDTO categoryDTO);
    void add(CategoryDTO categoryDTO);
}

package com.sky.service;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;

public interface SetMealService {
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
}

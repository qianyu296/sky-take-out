package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.DishFlavor;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.springframework.transaction.annotation.Transactional;

public interface DishService {
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);
    void addDishAndFlavor(DishDTO dishDTO);
    void deleteById(String ids);
    void setStatus(Long id,Integer status);
    void updateDish(DishDTO dishDTO);
    DishVO getDishById(Long id);
}

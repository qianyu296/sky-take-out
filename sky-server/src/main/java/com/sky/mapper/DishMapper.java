package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper {
    Page<Dish> pageQuery(Dish dish);
}

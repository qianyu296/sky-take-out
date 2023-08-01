package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Dish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {
    Page<Dish> pageQuery(DishPageQueryDTO dishPageQueryDTO);
    @Select("select * from category where type = #{type}")
    List<Category> getList(Integer type);
    @Insert("insert dish())")
    void addDish(DishDTO dishDTO);

}

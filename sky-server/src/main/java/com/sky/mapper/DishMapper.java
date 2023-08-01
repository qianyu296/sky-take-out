package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishMapper {
    Page<Dish> pageQuery(DishPageQueryDTO dishPageQueryDTO);
    @AutoFill(OperationType.INSERT)
    void addDish(Dish dish);
    void deleteById(List<Long> ids);
    @AutoFill(OperationType.UPDATE)
    @Update("update dish set status = #{status} where id = #{id}")
    void setStatus(Long id,Integer status);
    @AutoFill(OperationType.UPDATE)
    void updateDish(Dish dish);
    @Select("select * from dish where id = #{id}")
    Dish getDishById(Long id);
}

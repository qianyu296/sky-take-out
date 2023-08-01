package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入数据
     * @param list
     * */
    @AutoFill(OperationType.INSERT)
    public void insertBatch(List<DishFlavor> list);
    void deleteById(List<Long> ids);
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getDishFlavorById(Long dishId);
}

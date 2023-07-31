package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CategoryMapper {
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);
    @AutoFill(OperationType.UPDATE)
    @Update("update category set status = #{status} where id = #{id}")
    void startOrStop(Integer status,Long id);
    @AutoFill(OperationType.UPDATE)
    @Update("update category set name = #{name},sort = #{sort},update_time = #{updateTime},update_user = #{updateUser} where id = #{id}")
    void update(Category category);
    @AutoFill(OperationType.INSERT)
    @Insert("insert category(id,type,name,sort,status,create_time,update_time,create_user,update_user)" +
            " values(#{id},#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void add(Category category);
}

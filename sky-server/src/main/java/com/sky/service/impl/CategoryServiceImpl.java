package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    /**
     * 套餐分页查询
     * @param categoryPageQueryDTO
     * @return
     * */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        // 开始分页
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        // 获取pageHelper的分页对象,并且将dao层获取的结果集输入到分页对象当中
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(page.getResult());
        return pageResult;
    }
    /**
     * 根据id删除分类
     * @param id
     * @return
     * */
    @Override
    public void deleteById(Long id) {
        categoryMapper.deleteById(id);
    }
    /**
     * 启用、禁用分类
     * @param id,status
     * */
    @Override
    public void StartOrStop(Integer status, Long id) {
        categoryMapper.startOrStop(status,id);
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        // 将dto模型中的数据拷贝到category当中
        BeanUtils.copyProperties(categoryDTO,category);
        // category.setUpdateTime(LocalDateTime.now());
        // category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.update(category);
    }
    /**
     * 新增分类
     * @param categoryDTO
     * */
    @Override
    public void add(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        // category.setCreateTime(LocalDateTime.now());
        // category.setUpdateTime(LocalDateTime.now());
        // category.setCreateUser(BaseContext.getCurrentId());
        // category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.add(category);
    }

    @Override
    public List<Category> getList(Integer type) {
        return categoryMapper.getList(type);
    }
}

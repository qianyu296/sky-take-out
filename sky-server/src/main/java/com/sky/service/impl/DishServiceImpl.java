package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return pageResult
     * */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        // 开始分页
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<Dish> page = dishMapper.pageQuery(dishPageQueryDTO);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(page.getResult());
        return pageResult;
    }
    /**
     * 新增菜品
     * @param dishDTO
     * */
    @Transactional // 开启事务
    @Override
    public void addDishAndFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        // 添加菜品
        dishMapper.addDish(dish);
        // 添加菜品口味
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size() > 0){
            flavors.forEach(dishFlavor -> {
                // 遍历集合中的数据，并且将数据中的id赋值
                dishFlavor.setDishId(dish.getId());
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }
    /**
     * 批量删除菜品
     * @param ids
     * */
    @Transactional
    @Override
    public void deleteById(String ids) {
        // 将前端传递来的字符串分割成集合对象
        String[] str = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String s : str) {
            // 将分割后的数组转化为数字并且添加到集合当中
            idList.add(Long.parseLong(s.trim()));
        }
        dishMapper.deleteById(idList);
        // 删除菜品的同时删除菜品的口味
        dishFlavorMapper.deleteById(idList);
    }
    /**
     * 菜品起售、停售
     * @param id,status
     * */
    @Override
    public void setStatus(Long id, Integer status) {
        dishMapper.setStatus(id,status);
    }
    /**
     * 修改菜品
     * @param dishDTO
     * */
    @Transactional
    @Override
    public void updateDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        List<DishFlavor> list = dishDTO.getFlavors();
        // 将菜品id输入到每一个菜品口味的属性当中
        list.forEach(dishFlavor -> {
            dishFlavor.setDishId(dishDTO.getId());
        });
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.updateDish(dish);
        // 由于口味的update语句很难写,所以采用先删除id对应的口味,在插入新的口味的方法
        List<Long> ids = new ArrayList<>();
        ids.add(dishDTO.getId());
        // 删除该菜品所有的口味
        dishFlavorMapper.deleteById(ids);
        // 重新插入该菜品的口味
        dishFlavorMapper.insertBatch(list);
    }
    /**
     * 根据id获取菜品
     * @param id 
     * */
    @Override
    public DishVO getDishById(Long id) {
        DishVO dishVO = new DishVO();
        Dish dish = dishMapper.getDishById(id);
        List<DishFlavor> list = dishFlavorMapper.getDishFlavorById(id);
        BeanUtils.copyProperties(dish,dishVO);
        dishVO.setFlavors(list);
        return dishVO;
    }
}

package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     * */
    @ApiOperation(value = "菜品分页查询")
    @GetMapping("/page")
    public Result<PageResult> getAllDish(DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }
    /**
     * 根据分类id查询菜品
     * @param type
     * @return
     * */
    @ApiOperation(value = "根据分类id查询菜品")
    @GetMapping("/list")
    public Result<List<Category>> getList(Integer type){
        List<Category> list = dishService.getList(type);
        return Result.success(list);
    }
    /**
     * 新增菜品
     * @param dishDTO
     * @return
     * */
    @ApiOperation(value = "新增菜品")
    @PostMapping
    public Result<DishDTO> addDish(@RequestBody DishDTO dishDTO){

        return Result.success();
    }
}

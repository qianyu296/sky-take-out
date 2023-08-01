package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    @ApiOperation(value = "菜品分页查询")
    @GetMapping("/page")
    public Result<PageResult> getAllDish(DishPageQueryDTO dishPageQueryDTO) {
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    @ApiOperation(value = "新增菜品")
    @PostMapping
    public Result<String> addDish(@RequestBody DishDTO dishDTO) {
        dishService.addDishAndFlavor(dishDTO);
        return Result.success("新增成功");
    }

    /**
     * 删除菜品
     * @param ids
     * @return
     * */
    @ApiOperation(value = "删除菜品")
    @DeleteMapping
    public Result<String> deleteById(String ids){
        dishService.deleteById(ids);
        return Result.success("删除成功");
    }
    /**
     * 菜品起售、停售
     * @param id,status
     * @return
     */
    @ApiOperation("菜品起售停售")
    @PostMapping("/status/{status}")
    public Result<String> setStatus(@PathVariable Integer status,Long id){
        dishService.setStatus(id,status);
        return Result.success("操作成功");
    }
    // TODO 稍后完善
    /**
     * 修改菜品
     * @param dishDTO
     * @return
     * */
    @ApiOperation(value = "修改菜品")
    @PutMapping
    public Result<String> updateDish(@RequestBody DishDTO dishDTO){
        dishService.updateDish(dishDTO);
        return Result.success("修改成功");
    }
    /**
     * 根据id查找菜品
     * @param id
     * @return
     * */
    @ApiOperation(value = "根据id查找菜品")
    @GetMapping("/{id}")
    public Result<DishVO> getDishById(@PathVariable Long id){
        DishVO dishById = dishService.getDishById(id);
        return Result.success(dishById);
    }
}

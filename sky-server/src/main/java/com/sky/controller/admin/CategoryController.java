package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类管理相关接口")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @ApiOperation(value = "分类分页查询")
    @GetMapping("/page")
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     * */
    @ApiOperation(value = "根据id删除分类")
    @DeleteMapping
    public Result<String> deleteById(Long id){
        categoryService.deleteById(id);
        return Result.success("删除成功");
    }
    /**
     * 启用禁用分类
     * @param id,status
     * @return
     * */
    @ApiOperation(value = "启用禁用分类")
    @PostMapping("/status/{status}")
    public Result<String> StartOrStop(@PathVariable Integer status,Long id){
        categoryService.StartOrStop(status,id);
        return Result.success("操作成功");
    }

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     * */
    @ApiOperation(value = "修改分类")
    @PutMapping
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return Result.success("修改成功");
    }
    /**
     * 新增分类
     * @param categoryDTO
     * @return
     * */
    @ApiOperation("新增分类")
    @PostMapping
    public Result<String> add(@RequestBody CategoryDTO categoryDTO){
        categoryService.add(categoryDTO);
        return Result.success("新增成功");
    }
    /**
     * 根据类型查询套餐
     * @param type
     * @return
     * */
    @GetMapping("/list")
    public Result<List<Category>> getList(Integer type){
        List<Category> list = categoryService.getList(type);
        return Result.success(list);
    }
}

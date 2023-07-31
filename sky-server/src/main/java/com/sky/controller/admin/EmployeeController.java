package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "员工退出")
    public Result<String> logout() {
        return Result.success();
    }
    /**
     * 新增员工
     * @param employeeDTO
     * @return
     * */
    @ApiOperation(value = "新增员工")
    @PostMapping
    public Result<String> addEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.addEmployee(employeeDTO);
        return Result.success();
    }

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     * */
    @ApiOperation(value = "员工分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }
    /**
     * 启用、禁用员工
     * @param status,id
     * @return
     * */
    @ApiOperation(value = "启用禁用员工")
    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status,Long id){
        log.info("状态:{},id:{}",status,id);
        employeeService.startOrStop(status,id);
        return Result.success("设置成功");
    }
    /**
     * 根据id查询员工
     * @param id
     * @return
     * */
    @ApiOperation(value = "根据id查询员工")
    @GetMapping("/{id}")
    public Result<Employee> getEmployee(@PathVariable Long id){
        Employee employee = employeeService.getEmployeeById(id);
        return Result.success(employee);
    }
    /**
     * 编辑员工信息
     * @param employeeDTO
     * @return
     * */
    @ApiOperation(value = "编辑员工信息")
    @PutMapping
    public Result<String> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.UpdateEmployee(employeeDTO);
        return Result.success("修改成功");
    }
}

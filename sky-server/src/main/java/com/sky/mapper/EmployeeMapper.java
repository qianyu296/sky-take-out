package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);
    @Insert("insert employee(name,username,password,phone,sex,id_number,status,create_time,update_time,create_user,update_user) " +
            "values" +
            "(#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void addEmployee(Employee employee);
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
    @Update("update employee set status = #{status} where id = #{id}")
    void startOrStop(Integer status,Long id);
    @Select("select * from employee where id = #{id}")
    Employee getEmployeeById(Long id);
    @Update("update employee " +
            "set name = #{name},username = #{username},phone = #{phone},sex = #{sex}," +
            "id_number = #{idNumber},update_time = #{updateTime},update_user = #{updateUser} where id = #{id}")
    void updateEmployee(Employee employee);
}

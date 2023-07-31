package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 自定义切面,实现公共字段自动填充处理逻辑
 * */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    // 切入点
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void pointCut(){}
    // 前置通知
    @Before("pointCut()")
    public void beforeAutoFill(JoinPoint joinPoint){
        log.info("开始进行公共字段填充...");
        // 获得有注解的方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获得方法对象并且获得注解对象
        AutoFill autoFill = methodSignature.getMethod().getAnnotation(AutoFill.class);
        // 获取数据库操作类型
        OperationType type = autoFill.value();
        // 获取当前被拦截的对象的属性
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0){
            return;
        }
        Object arg = args[0]; // 第一个数据就是对象数据

        // 准备赋值的数据
        LocalDateTime now = LocalDateTime.now(); // 创建以及修改的时间
        Long id = BaseContext.getCurrentId(); // 创建以及修改的用户
        // 根据不同的操作类型,为对应的属性赋值
        if(type == OperationType.INSERT){
            try {
                // 获得方法对象
                Method setCreateTime = arg.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
                Method setUpdateTime = arg.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setCreateUser = arg.getClass().getDeclaredMethod("setCreateUser", Long.class);
                Method setUpdateUser = arg.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                // 通过反射为对象属性赋值
                setCreateTime.invoke(arg,now);
                setUpdateTime.invoke(arg,now);
                setCreateUser.invoke(arg,id);
                setUpdateUser.invoke(arg,id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(type == OperationType.UPDATE){
            try {
                Method setUpdateTime = arg.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser = arg.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                setUpdateTime.invoke(arg,now);
                setUpdateUser.invoke(arg,id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

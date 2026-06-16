package com.rag.common.common.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rag.common.common.annotations.CheckPromise;
import com.rag.common.dto.LoginUserInfo;
import com.rag.common.util.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.rag.common.common.instances.JwtRequestHeader.*;

@Slf4j
@Component
@Aspect
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CheckPromiseAspect {


    //这里和参数中的注解变量名对应
    @Before("@annotation(checkPromise)")
    public void insertLoginUser(JoinPoint joinPoint, CheckPromise checkPromise) throws JsonProcessingException {

        //1.获取到当前请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //2.获取 token 解析后的请求体中的用户信息
        String userId = request.getHeader(JWT_USER_ID);
        String tenantId = request.getHeader(JWT_TENANT_ID);
        String deptId = request.getHeader(JWT_DEPT_ID);
        String roleId = request.getHeader(JWT_ROLE_ID);

        //3.进行权限校验

        String[] needPromises = checkPromise.promises();
        if (!(needPromises == null || needPromises.length == 0)){
            //todo 遍历判断，用户所有权限，是否满足需要的所有权限
            for (String needPromise : needPromises) {

            }
        }

        //4.保存用户登录信息
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setUserId(Long.parseLong(userId));
        loginUserInfo.setTenantId(Long.parseLong(tenantId));
        loginUserInfo.setDeptId(deptId);
        loginUserInfo.setRoleId(Long.parseLong(roleId));

        //将用户信息保存到 threadLocal
        UserContext.setUserInfo(loginUserInfo);

    }

    /**
     * 在一次请求结束后，清理用户信息
     * springboot 使用 tomcat 的线程池，线程会复用，如果不清理，可能会读取到上一个用户的信息
     */
    @After("@annotation(com.rag.common.common.annotations.CheckPromise)")
    public void removeLoginUser(JoinPoint joinPoint){
        UserContext.remove();
    }

}

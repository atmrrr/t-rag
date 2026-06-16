package com.rag.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rag.common.dto.error.ResponseCode;
import com.rag.common.exception.BusinessException;
import com.rag.common.util.JwtUtil;
import com.rag.common.util.Md5Util;
import com.rag.crm.dto.req.user.cmd.UserLoginCmd;
import com.rag.crm.pojo.CrmUser;
import com.rag.crm.service.CrmUserService;
import com.rag.crm.mapper.CrmUserMapper;
import com.rag.crm.vo.UserInfoVo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
* @author tjr
* @description 针对表【crm_user】的数据库操作Service实现
* @createDate 2026-06-05 21:32:54
*/
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CrmUserServiceImpl extends ServiceImpl<CrmUserMapper, CrmUser>
    implements CrmUserService{

    private final JwtUtil jwtUtil;

    @Override
    public UserInfoVo login(UserLoginCmd userLoginCmd) {

        String name = userLoginCmd.getName();
        String password = userLoginCmd.getPassword();

        LambdaQueryWrapper<CrmUser> eq = Wrappers.
                lambdaQuery(CrmUser.class)
                .eq(CrmUser::getName, name);

        CrmUser user = this.getOne(eq);
        if (user == null){
            throw new BusinessException(ResponseCode.NO_INFO,"用户信息不存在");
        }

        String inputPwd= Md5Util.md5(password);
        if (!user.getPassword().equals(inputPwd))
            throw new BusinessException(ResponseCode.ERROR_PASSWORD);

        //返回响应信息
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(user, userInfoVo);
        return userInfoVo;
    }
    
}





package com.rag.crm.controller;
import com.rag.common.common.annotations.CheckPromise;
import com.rag.common.dto.resp.BaseResponse;
import com.rag.common.util.JwtUtil;
import com.rag.common.util.ResultUtil;
import com.rag.crm.dto.req.user.cmd.UserAddCmd;
import com.rag.crm.dto.req.user.cmd.UserLoginCmd;
import com.rag.crm.manager.UserManager;
import com.rag.crm.service.CrmUserService;
import com.rag.crm.vo.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "用户操作相关接口")
public class UserController {

    private final UserManager userManager;
    private final CrmUserService userService;
    private final JwtUtil jwtUtil;



    @PostMapping("/doLogin")
    @Operation(
            summary = "用户登录",
            description = "用户通过用户名和密码登录系统，登录成功后返回用户基础信息，已经在响应的 cookie 中置入 token，key 为 token"
    )
    public BaseResponse<UserInfoVo> login(@RequestBody UserLoginCmd userLoginCmd, HttpServletResponse httpServerResponse){
        UserInfoVo userInfoVo = userService.login(userLoginCmd);

        //登录成功返回 token信息 与个人基本信息给前端
        String token = jwtUtil.getToken(userInfoVo.getId(), userInfoVo.getTenantId(), userInfoVo.getDeptId(), userInfoVo.getRoleId(), null);
        //将 token 放入 cookie 中
        httpServerResponse.addHeader("token", token);
        httpServerResponse.addHeader("Access-Control-Allow-Headers","token");

        return ResultUtil.success(userInfoVo);
    }


    @GetMapping("/getLoginId")
    public Object getLoginId(){

        return null;
    }

    @PostMapping("/add")
    @CheckPromise(promises = "")
    @Operation(
            summary = "新增用户",
            description = ""
    )
    public BaseResponse add(@RequestBody @Validated UserAddCmd userAddCmd){

        return null;
    }


}

package com.rag.crm.service;

import com.rag.crm.dto.req.user.cmd.UserLoginCmd;
import com.rag.crm.pojo.CrmUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rag.crm.vo.UserInfoVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.server.reactive.ServerHttpResponse;

/**
* @author tjr
* @description 针对表【crm_user】的数据库操作Service
* @createDate 2026-06-05 21:32:54
*/
public interface CrmUserService extends IService<CrmUser> {

    UserInfoVo login(UserLoginCmd userLoginCmd);

}

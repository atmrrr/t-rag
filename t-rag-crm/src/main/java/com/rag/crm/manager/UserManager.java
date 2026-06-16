package com.rag.crm.manager;

import com.rag.crm.dto.req.user.cmd.UserAddCmd;
import com.rag.crm.service.CrmUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserManager {

    private final CrmUserService crmUserService;


    public void addUser(UserAddCmd userAddCmd){

        //1.从线程中获取操作员信息，进行 权限校验

    }

}

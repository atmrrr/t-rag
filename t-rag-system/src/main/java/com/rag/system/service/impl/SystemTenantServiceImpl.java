package com.rag.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rag.common.dto.LoginUserInfo;
import com.rag.common.dto.error.ResponseCode;
import com.rag.common.exception.BusinessException;
import com.rag.common.util.UserContext;
import com.rag.system.dto.req.TenantAddReq;
import com.rag.system.dto.req.TenantSearchQuery;
import com.rag.system.pojo.SystemTenant;
import com.rag.system.service.SystemTenantService;
import com.rag.system.mapper.SystemTenantMapper;
import com.rag.system.util.PageUtil;
import com.rag.system.vo.TenantVo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.rag.common.dto.error.ResponseCode.REPEAT_TENANT;
import static org.apache.hc.core5.http2.H2PseudoResponseHeaders.STATUS;

/**
* @author tjr
* @description 针对表【system_tenant】的数据库操作Service实现
* @createDate 2026-06-14 15:09:10
*/
@Service
public class SystemTenantServiceImpl extends ServiceImpl<SystemTenantMapper, SystemTenant>
    implements SystemTenantService{

    @Override
    public IPage<TenantVo> getTenantVoList(TenantSearchQuery tenantSearchQuery) {

        if (tenantSearchQuery == null)
            throw new BusinessException(ResponseCode.ERROR_PARAM);

        String tenantName = tenantSearchQuery.getTenantName();
        String contact = tenantSearchQuery.getContact();
        Integer pageSize = tenantSearchQuery.getPageSize();
        Integer pageNo = tenantSearchQuery.getPageNo();

        Page<SystemTenant> page = new Page<>(pageNo, pageSize);

        LambdaQueryWrapper<SystemTenant> like = Wrappers.lambdaQuery(SystemTenant.class)
                .like(SystemTenant::getTenantName, tenantName)
                .like(SystemTenant::getContact, contact);

        this.page(page, like);

        return PageUtil.pageChange(page, SystemTenantServiceImpl::TenantToVo);
    }

    @Override
    public boolean addTenant(TenantAddReq tenantAddReq) {

        String tenantName = tenantAddReq.getTenantName();
        LoginUserInfo loginUser = UserContext.getLoginUser();
        //1.判断名称是否重复
        LambdaQueryWrapper<SystemTenant> eq = Wrappers.lambdaQuery(SystemTenant.class)
                .eq(SystemTenant::getTenantName, tenantName);
        SystemTenant one = getOne(eq);
        if (one != null)
            throw new BusinessException(REPEAT_TENANT);

        Date now = new Date();

        SystemTenant systemTenant = new SystemTenant();
        BeanUtil.copyProperties(tenantAddReq, systemTenant);
        systemTenant.setCreateUser(loginUser.getUserId());
        systemTenant.setUpdateUser(loginUser.getUserId());
        systemTenant.setCTime(now);
        systemTenant.setStatus(STATUS);


        return save(systemTenant);

    }

    public static TenantVo TenantToVo(SystemTenant tenant) {

        TenantVo tenantVo = new TenantVo();

        BeanUtil.copyProperties(tenant, tenantVo);

        return tenantVo;
    }
}





package com.rag.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rag.system.dto.req.TenantAddReq;
import com.rag.system.dto.req.TenantSearchQuery;
import com.rag.system.pojo.SystemTenant;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rag.system.vo.TenantVo;

import java.util.List;

/**
* @author tjr
* @description 针对表【system_tenant】的数据库操作Service
* @createDate 2026-06-14 15:09:10
*/
public interface SystemTenantService extends IService<SystemTenant> {

    IPage<TenantVo> getTenantVoList(TenantSearchQuery tenantSearchQuery);

    boolean addTenant(TenantAddReq tenantAddReq);
}

package com.rag.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rag.common.common.annotations.CheckPromise;
import com.rag.common.dto.resp.BaseResponse;
import com.rag.common.util.ResultUtil;
import com.rag.system.dto.req.TenantAddReq;
import com.rag.system.dto.req.TenantSearchQuery;
import com.rag.system.service.SystemTenantService;
import com.rag.system.vo.TenantVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/tenant")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TenantController {

    private final SystemTenantService tenantService;

    /**
     * 查询
     * @param tenantSearchQuery
     * @return
     */
    @GetMapping("/search-list")
    public BaseResponse searchTenantList(TenantSearchQuery tenantSearchQuery){

        IPage<TenantVo> tenantVoList = tenantService.getTenantVoList(tenantSearchQuery);
        return ResultUtil.success(tenantVoList);

    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @CheckPromise
    public BaseResponse addTenant(@RequestBody @Validated TenantAddReq tenantAddReq){

        return ResultUtil.success(tenantService.addTenant(tenantAddReq));

    }


}

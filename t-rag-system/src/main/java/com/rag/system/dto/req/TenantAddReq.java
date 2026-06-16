package com.rag.system.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TenantAddReq {


    @NotBlank
    private String tenantName;

    @NotBlank
    private String contact;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @NotBlank
    private String address;

}

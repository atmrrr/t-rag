package com.rag.system.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class TenantVo {

    @TableId(value = "id")
    private Integer id;

    /**
     * 租户名称
     */
    @TableField(value = "tenant_name")
    private String tenantName;

    /**
     * 联系人
     */
    @TableField(value = "contact")
    private String contact;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱地址
     */
    @TableField(value = "email")
    private String email;

    /**
     * 租户公司详细地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 租户状态
     */
    @TableField(value = "status")
    private String status;

    /**
     * vip 等级，等级越高使用的功能越多
     */
    @TableField(value = "vip_step")
    private String vipStep;

    /**
     * 资源容量
     */
    @TableField(value = "file_capacity")
    private Long fileCapacity;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private Long createUser;

    /**
     * 更新人
     */
    @TableField(value = "update_user")
    private Long updateUser;

    /**
     * 更新时间
     */
    @TableField(value = "u_time")
    private Date uTime;

    /**
     * 创建时间
     */
    @TableField(value = "c_time")
    private Date cTime;

}

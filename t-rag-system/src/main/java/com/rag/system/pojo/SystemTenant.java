package com.rag.system.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName system_tenant
 */
@TableName(value ="system_tenant")
@Data
public class SystemTenant {
    /**
     * 
     */
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tenantName=").append(tenantName);
        sb.append(", contact=").append(contact);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", address=").append(address);
        sb.append(", status=").append(status);
        sb.append(", vipStep=").append(vipStep);
        sb.append(", fileCapacity=").append(fileCapacity);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", uTime=").append(uTime);
        sb.append(", cTime=").append(cTime);
        sb.append("]");
        return sb.toString();
    }
}
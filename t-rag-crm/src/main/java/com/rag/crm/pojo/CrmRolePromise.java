package com.rag.crm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 
 * @TableName crm_role_promise
 */
@TableName(value ="crm_role_promise")
@Data
public class CrmRolePromise {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 权限控制的方法
     */
    @TableField(value = "function")
    private String function;

    /**
     * 
     */
    @TableField(value = "org_id")
    private Long orgId;

    /**
     * 
     */
    @TableField(value = "dept_id")
    private Integer deptId;

    /**
     * 新建操作员
     */
    @TableField(value = "create_user")
    private Long createUser;

    /**
     * 修改操作员
     */
    @TableField(value = "update_user")
    private Long updateUser;

    /**
     * 
     */
    @TableField(value = "u_time")
    private Date uTime;

    /**
     * 
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
        sb.append(", function=").append(function);
        sb.append(", orgId=").append(orgId);
        sb.append(", deptId=").append(deptId);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", uTime=").append(uTime);
        sb.append(", cTime=").append(cTime);
        sb.append("]");
        return sb.toString();
    }
}
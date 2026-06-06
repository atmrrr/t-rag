package com.rag.crm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName crm_user_role
 */
@TableName(value ="crm_user_role")
@Data
public class CrmUserRole {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 角色
     */
    @TableField(value = "role")
    private String role;

    /**
     * 权限列表
     */
    @TableField(value = "promise")
    private String promise;

    /**
     * 租户id
     */
    @TableField(value = "org_id")
    private Long orgId;

    /**
     * 部门id
     */
    @TableField(value = "dept_id")
    private Long deptId;

    /**
     * 
     */
    @TableField(value = "create_user")
    private Long createUser;

    /**
     * 
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
        sb.append(", role=").append(role);
        sb.append(", promise=").append(promise);
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
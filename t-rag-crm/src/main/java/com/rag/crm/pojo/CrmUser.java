package com.rag.crm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName crm_user
 */
@TableName(value ="crm_user")
@Data
public class CrmUser {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录用户名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 密码，md5加密存储
     */
    @TableField(value = "password")
    private String password;

    /**
     * 总消耗 token 数量
     */
    @TableField(value = "tokens")
    private Long tokens;

    /**
     * 系统角色，来自于字典
     */
    @TableField(value = "role")
    private String role;

    /**
     * 租户 id
     */
    @TableField(value = "org_id")
    private String orgId;

    /**
     * 部门 id
     */
    @TableField(value = "dept_id")
    private String deptId;

    /**
     * 创建人
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
     * 注册日期
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
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", tokens=").append(tokens);
        sb.append(", role=").append(role);
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
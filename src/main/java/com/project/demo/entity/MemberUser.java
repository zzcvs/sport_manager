package com.project.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 会员用户：(MemberUser)表实体类
 *
 */
@TableName("`member_user`")
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberUser implements Serializable {

    // MemberUser编号
    @TableId(value = "member_user_id", type = IdType.AUTO)
    private Integer member_user_id;

    // 会员姓名
    @TableField(value = "`member_name`")
    private String member_name;
    // 联系方式
    @TableField(value = "`contact_information`")
    private String contact_information;
    // 会籍类型
    @TableField(value = "`membership_type`")
    private String membership_type;
    // 到期日期
    @TableField(value = "`due_date`")
    private Timestamp due_date;
    // 账户余额
    @TableField(value = "`account_balance`")
    private Double account_balance;









    // 用户编号
    @TableField(value = "user_id")
    private Integer userId;











		


	// 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}

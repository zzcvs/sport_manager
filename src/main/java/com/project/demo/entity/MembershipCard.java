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
 * 会员卡：(MembershipCard)表实体类
 *
 */
@TableName("`membership_card`")
@Data
@EqualsAndHashCode(callSuper = false)
public class MembershipCard implements Serializable {

    // MembershipCard编号
    @TableId(value = "membership_card_id", type = IdType.AUTO)
    private Integer membership_card_id;

    // 会员卡名称
    @TableField(value = "`membership_card_name`")
    private String membership_card_name;
    // 会员卡类型
    @TableField(value = "`membership_card_type`")
    private String membership_card_type;
    // 会员卡时效
    @TableField(value = "`membership_card_expiration_date`")
    private String membership_card_expiration_date;
    // 会员卡费用
    @TableField(value = "`membership_card_fees`")
    private Double membership_card_fees;
    // 会员卡优惠
    @TableField(value = "`membership_card_discounts`")
    private String membership_card_discounts;
    // 会员卡介绍
    @TableField(value = "`membership_card_introduction`")
    private String membership_card_introduction;
    // 会员卡详情
    @TableField(value = "`membership_card_details`")
    private String membership_card_details;

    // 点击数
    @TableField(value = "hits")
    private Integer hits;

    // 点赞数
    @TableField(value = "praise_len")
    private Integer praise_len;

    // 收藏数
    @TableField(value = "collect_len")
    private Integer collect_len;

    // 评论数
    @TableField(value = "comment_len")
    private Integer comment_len;
















		


	// 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}

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
 * 健身建议：(FitnessAdvice)表实体类
 *
 */
@TableName("`fitness_advice`")
@Data
@EqualsAndHashCode(callSuper = false)
public class FitnessAdvice implements Serializable {

    // FitnessAdvice编号
    @TableId(value = "fitness_advice_id", type = IdType.AUTO)
    private Integer fitness_advice_id;

    // 会员用户
    @TableField(value = "`member_user`")
    private Integer member_user;
    // 会员姓名
    @TableField(value = "`member_name`")
    private String member_name;
    // 建议标题
    @TableField(value = "`suggested_title`")
    private String suggested_title;
    // 推荐课程
    @TableField(value = "`recommend_courses`")
    private String recommend_courses;
    // 建议内容
    @TableField(value = "`suggested_content`")
    private String suggested_content;




















			// 来源表
	@TableField(value = "source_table")
	private String source_table;
	
	// 来源ID
	@TableField(value = "source_id")
	private Integer source_id;
	
	// 来源用户ID
	@TableField(value = "source_user_id")
	private Integer source_user_id;
	


	// 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}

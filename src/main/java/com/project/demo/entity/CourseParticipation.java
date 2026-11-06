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
 * 课程参与：(CourseParticipation)表实体类
 *
 */
@TableName("`course_participation`")
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseParticipation implements Serializable {

    // CourseParticipation编号
    @TableId(value = "course_participation_id", type = IdType.AUTO)
    private Integer course_participation_id;

    // 课程名称
    @TableField(value = "`course_name`")
    private String course_name;
    // 课程类型
    @TableField(value = "`course_type`")
    private String course_type;
    // 课程时间
    @TableField(value = "`course_time`")
    private String course_time;
    // 课程地点
    @TableField(value = "`course_location`")
    private String course_location;
    // 教练姓名
    @TableField(value = "`name_of_coach`")
    private String name_of_coach;
    // 会员用户
    @TableField(value = "`member_user`")
    private Integer member_user;
    // 会员姓名
    @TableField(value = "`member_name`")
    private String member_name;
    // 参与备注
    @TableField(value = "`participation_notes`")
    private String participation_notes;



















		// 建议限制次数
	@TableField(value = "fitness_advice_limit_times")
	private String fitness_advice_limit_times;
	
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

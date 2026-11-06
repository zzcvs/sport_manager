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
 * 课程信息：(CourseInformation)表实体类
 *
 */
@TableName("`course_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseInformation implements Serializable {

    // CourseInformation编号
    @TableId(value = "course_information_id", type = IdType.AUTO)
    private Integer course_information_id;

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
    // 课程图片
    @TableField(value = "`course_picture`")
    private String course_picture;
    // 课程介绍
    @TableField(value = "`course_introduction`")
    private String course_introduction;
    // 课程详情
    @TableField(value = "`course_details`")
    private String course_details;

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















		// 参与限制次数
	@TableField(value = "course_participation_limit_times")
	private String course_participation_limit_times;
	
		


	// 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}

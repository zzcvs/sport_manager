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
 * 设备信息：(DeviceInformation)表实体类
 *
 */
@TableName("`device_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class DeviceInformation implements Serializable {

    // DeviceInformation编号
    @TableId(value = "device_information_id", type = IdType.AUTO)
    private Integer device_information_id;

    // 设备编号
    @TableField(value = "`equipment_number`")
    private String equipment_number;
    // 设备名称
    @TableField(value = "`equipment_name`")
    private String equipment_name;
    // 设备类型
    @TableField(value = "`equipment_type`")
    private String equipment_type;
    // 设备状态
    @TableField(value = "`equipment_status`")
    private String equipment_status;
    // 设备数量
    @TableField(value = "`equipment_quantity`")
    private Double equipment_quantity;
    // 设备图片
    @TableField(value = "`equipment_pictures`")
    private String equipment_pictures;
    // 使用情况
    @TableField(value = "`usage`")
    private String usage;
    // 设备介绍
    @TableField(value = "`equipment_introduction`")
    private String equipment_introduction;
    // 设备详情
    @TableField(value = "`device_details`")
    private String device_details;




















		


	// 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}

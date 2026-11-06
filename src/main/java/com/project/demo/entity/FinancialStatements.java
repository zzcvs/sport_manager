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
 * 财务报表：(FinancialStatements)表实体类
 *
 */
@TableName("`financial_statements`")
@Data
@EqualsAndHashCode(callSuper = false)
public class FinancialStatements implements Serializable {

    // FinancialStatements编号
    @TableId(value = "financial_statements_id", type = IdType.AUTO)
    private Integer financial_statements_id;

    // 报表名称
    @TableField(value = "`report_name`")
    private String report_name;
    // 报表类型
    @TableField(value = "`report_type`")
    private String report_type;
    // 统计日期
    @TableField(value = "`statistical_date`")
    private Timestamp statistical_date;
    // 统计周期
    @TableField(value = "`statistical_cycle`")
    private String statistical_cycle;
    // 统计金额
    @TableField(value = "`statistical_amount`")
    private Double statistical_amount;
    // 报表详情
    @TableField(value = "`statement_details`")
    private String statement_details;




















		


	// 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}

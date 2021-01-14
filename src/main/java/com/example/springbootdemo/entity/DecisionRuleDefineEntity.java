package com.example.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 *
 * @author zhanglinchun
 * @email ex_zhanglinchun@axatp.com
 * @date 2020-07-22 18:17:44
 */
@Data
@TableName("t_decision_rule_define")
public class DecisionRuleDefineEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdDate;
    /**
     * 修改人
     */
    private String updatedBy;
    /**
     * 修改时间
     */
    private Date updatedDate;
    /**
     * 失效人
     */
    private String deletedBy;
    /**
     * 失效时间
     */
    private Date deletedDate;
    /**
     * 是否有效标识：1:无效;0:有效
     */
    private Integer deletedFlag;
    /**
     * 决策表定义ID
     */
    @TableId(type = IdType.INPUT)
    private Long ruleDefineId;
    /**
     * 决策表定义名称
     */
    private String ruleDefineName;
    /**
     * 规则类型
     */
    private String ruleType;
    /**
     * 运行方式
     */
    private String executeType;
    /**
     * 事实类型
     */
    private String factType;
    /**
     * 结论类型.DIRECTRETURN:直接返回.CONDITION:判断条件
     */
    private String conclusionType;
    /**
     * 决策表定义CODE
     */
    private String businessType;
    /**
     * 别名
     */
    private String alias;


    /**
     * 口径
     */
    @TableField(exist = false)
    private String calibCode;

    /**
     * 渠道
     */
    @TableField(exist = false)
    private String channelSourceType;
}

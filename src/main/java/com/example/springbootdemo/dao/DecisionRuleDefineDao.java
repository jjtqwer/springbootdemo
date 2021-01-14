package com.example.springbootdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootdemo.entity.DecisionRuleDefineEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 决策之规则定义表(政策定义)
 *
 * @author zhanglinchun
 * @email ex_zhanglinchun@axatp.com
 * @date 2020-07-15 11:01:25
 */
@Mapper
public interface DecisionRuleDefineDao extends BaseMapper<DecisionRuleDefineEntity> {


}

package com.example.springbootdemo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootdemo.entity.DecisionRuleDefineEntity;

/**
 * ${comments}
 *
 * @author zhanglinchun
 * @email ex_zhanglinchun@axatp.com
 * @date 2020-07-22 18:17:44
 */
public interface DecisionRuleDefineService extends IService<DecisionRuleDefineEntity> {


    void downloadAllData();
}


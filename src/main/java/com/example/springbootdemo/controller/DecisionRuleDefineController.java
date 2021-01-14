package com.example.springbootdemo.controller;

import com.example.springbootdemo.ResultData;
import com.example.springbootdemo.exception.BizException;
import com.example.springbootdemo.exception.CommonEnum;
import com.example.springbootdemo.service.DecisionRuleDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("rule/define")
@RestController
public class DecisionRuleDefineController {

    @Autowired
    private DecisionRuleDefineService decisionRuleDefineService;

    @RequestMapping("downloadExcel")
    public ResultData downloadExcel(){
        try {
            decisionRuleDefineService.downloadAllData();
            return ResultData.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(CommonEnum.INTER_SERVER_ERROR);
        }
    }
}

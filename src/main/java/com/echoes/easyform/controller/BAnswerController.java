package com.echoes.easyform.controller;



import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.echoes.easyform.entity.BAnswer;
import com.echoes.easyform.service.BAnswerService;
import com.echoes.easyform.utils.Calculator;
import com.echoes.easyform.utils.ResponseStatus;
import com.echoes.easyform.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 劳威锟
 * @since 2023-09-18
 */
@RestController
@RequestMapping("/easy/BAnswer")
public class BAnswerController {

    @Autowired
    private BAnswerService bAnswerService;


    @PostMapping("saveAnswer")
    @Transactional
    public Result saveAnswer(@RequestBody BAnswer answer) {
        try {
            answer.setCreateTime(new Date());
            boolean save = bAnswerService.save(answer);
            if (save) {
                if(StringUtils.isNotBlank(answer.getLogic()) &&  StringUtils.isNotBlank(answer.getLogicUI())) {
                    String formula = answer.getLogic();
                    String resultStr = "";
                    /** 将所有的 【x和÷】 提换为 【*和/】 **/
                    formula = formula.replaceAll("x","*");
                    formula = formula.replaceAll("÷","/");

                    /** 拆分为多个算式 **/
                    String[] splitFormula = formula.split(",");
                    /** 初始化计算器 **/
                    Calculator calculator = new Calculator();
                    /** 初始化 数据map **/
                    Map<String, String> dataMap = new HashMap<>();
                    for(int i=0; i < splitFormula.length; i++) {
                        /** 摘取 算式的等于号 以进行运算 **/
                        int index = splitFormula[i].indexOf("=");
                        splitFormula[i] = splitFormula[i].substring(0,index);

                        /** 按序使用计算器 计算结果，并塞进map **/
                        double result = calculator.executeExpression(splitFormula[i]);
                        dataMap.put("result"+(i+1), result+"");
                        /** 结果 用 , 相连 **/
                        resultStr = resultStr + result + ",";
                        /** 双重循环将 resultxx 提换为具体的结果值 **/
                        for (int j=0; j< splitFormula.length; j++){
                            splitFormula[j] = splitFormula[j].replaceAll("result"+(i+1),result+"");
                        }
                    }
                    return Result.success().data("dataMap",dataMap).data("resultStr",resultStr).data("splitFormula",splitFormula);
                }
                return Result.success();
            } else {
                return Result.error().code(201).msg("添加失败");
            }
        } catch (MybatisPlusException e) {
            // 这是 mybatis-plus 的报错
            return Result.error().code(201).msg("数据错误，请注意填写规范或联系管理员");
        } catch (Exception e) {
            // 其他类型的错误
            return Result.error().code(201).msg("系统繁忙，请稍后再试或联系管理员");
        }
    }

}


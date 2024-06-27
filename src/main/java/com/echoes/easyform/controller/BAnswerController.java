package com.echoes.easyform.controller;



import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.echoes.easyform.entity.BAnswer;
import com.echoes.easyform.service.BAnswerService;
import com.echoes.easyform.utils.ResponseStatus;
import com.echoes.easyform.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
    public Result saveAnswer(@RequestBody BAnswer answer) {
        try {
            answer.setCreateTime(new Date());
            boolean save = bAnswerService.save(answer);
            if (save) {
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


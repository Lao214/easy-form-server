package com.echoes.easyform.controller;


import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.echoes.easyform.entity.BForm;
import com.echoes.easyform.service.BFormService;
import com.echoes.easyform.utils.Result;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author 劳威锟
 * @since 2023-09-09
 */
@RestController
@RequestMapping("/easy/BForm")
public class BFormController {

    @Autowired
    private BFormService formService;


    @PostMapping("saveForm")
    public Result saveForm(@RequestBody BForm form) {
        try {
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            form.setUserId(loginIdAsLong);
            boolean save = formService.save(form);
            if (save) {
                return Result.success().data("one",form);
            } else {
                return Result.error().code(201).msg("添加失败");
            }
        } catch (MybatisPlusException e) {
            // 这是 mybatis-plus 的报错
            return Result.error().code(201).msg("数据错误，请注意填写规范或联系管理员");
        } catch (SaTokenException e) {
            // 这是 sa-token 的报错
            return Result.success().code(201).msg("登录过期或者没有登录，请重新登录");
        } catch (Exception e) {
            // 其他类型的错误
            return Result.error().code(201).msg("系统繁忙，请稍后再试或联系管理员");
        }
    }

    @PostMapping("getFormList")
    public Result getFormList(@RequestBody BForm form) {
        try {
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            QueryWrapper<BForm> bFormQueryWrapper = new QueryWrapper<BForm>();
            if(StringUtils.isNoneBlank(form.getFormName())) {
                bFormQueryWrapper.eq("form_name",form.getFormName());
            }
            if(form.getSortType().equals("SortByCreateTimeDesc")) {
                bFormQueryWrapper.orderByDesc("create_time");
            } else if(form.getSortType().equals("SortByCreateTimeAsc")) {
                bFormQueryWrapper.orderByAsc("create_time");
            } else if(form.getSortType().equals("SortByNameDesc")) {
                bFormQueryWrapper.orderByDesc("form_name");
            } else if(form.getSortType().equals("SortByNameAsc")) {
                bFormQueryWrapper.orderByAsc("form_name");
            } else if(form.getSortType().equals("SortByWriteCountDesc")) {
                bFormQueryWrapper.orderByDesc("write_count");
            } else if(form.getSortType().equals("SortByWriteCountAsc")) {
                bFormQueryWrapper.orderByAsc("write_count");
            }
            bFormQueryWrapper.eq("user_id",loginIdAsLong);
            bFormQueryWrapper.select("id","form_name","user_id","form_status","create_time","form_type");
            List<BForm> list = formService.list(bFormQueryWrapper);
            return Result.success().data("list",list);
        } catch (MybatisPlusException e) {
            // 这是 mybatis-plus 的报错
            return Result.error().code(201).msg("数据错误，请注意填写规范或联系管理员");
        } catch (SaTokenException e) {
            // 这是 sa-token 的报错
            return Result.success().code(201).msg("登录过期或者没有登录，请重新登录");
        } catch (Exception e) {
            // 其他类型的错误
            return Result.error().code(201).msg("系统繁忙，请稍后再试或联系管理员");
        }
    }


}


package com.echoes.easyform.controller;



import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.echoes.easyform.entity.BForm;
import com.echoes.easyform.service.BFormService;
import com.echoes.easyform.utils.JwtUtil;
import com.echoes.easyform.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    @GetMapping("getFormByKey/{formKey}")
    public Result getFormByKey(@PathVariable("formKey") String formKey,HttpServletRequest request) {
        try {
            Map<String, String> memberIdByJwtToken = JwtUtil.getMemberIdByJwtToken(request);
            String loginIdAsLong = memberIdByJwtToken.get("userId");
            QueryWrapper<BForm> formQueryWrapper = new QueryWrapper<BForm>();
            formQueryWrapper.eq("user_id", loginIdAsLong);
            formQueryWrapper.eq("form_key", formKey);
            BForm one = formService.getOne(formQueryWrapper);
            if(one != null) {
                return Result.success().data("one",one);
            } else  {
                return Result.error().code(201).msg("查询失败");
            }
        } catch (MybatisPlusException e) {
            // 这是 mybatis-plus 的报错
            return Result.error().code(201).msg("数据错误，请注意填写规范或联系管理员");
        } catch (Exception e) {
            // 其他类型的错误
            return Result.error().code(201).msg("系统繁忙，请稍后再试或联系管理员");
        }
    }

    @GetMapping("getFormByKeyPublic/{formKey}")
    public Result getFormByKeyPublic(@PathVariable("formKey") String formKey) {
        try {
            QueryWrapper<BForm> formQueryWrapper = new QueryWrapper<BForm>();
            formQueryWrapper.eq("form_key", formKey);
            BForm one = formService.getOne(formQueryWrapper);
            if(one != null) {
                return Result.success().data("one",one);
            } else  {
                return Result.error().code(201).msg("查询失败");
            }
        } catch (MybatisPlusException e) {
            // 这是 mybatis-plus 的报错
            return Result.error().code(201).msg("数据错误，请注意填写规范或联系管理员");
        } catch (Exception e) {
            // 其他类型的错误
            return Result.error().code(201).msg("系统繁忙，请稍后再试或联系管理员");
        }
    }

    @PostMapping("saveForm")
    public Result saveForm(@RequestBody BForm form, HttpServletRequest request) {
        try {
            Map<String, String> memberIdByJwtToken = JwtUtil.getMemberIdByJwtToken(request);
            String loginIdAsLong = memberIdByJwtToken.get("userId");
            form.setUserId(Long.valueOf(loginIdAsLong));
            long uuid = IdUtil.getSnowflake(1, 1).nextId();
            form.setFormKey(uuid + "");
            boolean save = formService.save(form);
            if (save) {
                return Result.success().data("one",form);
            } else {
                return Result.error().code(201).msg("添加失败");
            }
        } catch (MybatisPlusException e) {
            // 这是 mybatis-plus 的报错
            return Result.error().code(201).msg("数据错误，请注意填写规范或联系管理员");
        }catch (Exception e) {
            // 其他类型的错误
            return Result.error().code(201).msg("系统繁忙，请稍后再试或联系管理员");
        }
    }

    @PostMapping("updateForm")
    public Result updateForm(@RequestBody BForm form, HttpServletRequest request) {
        try {
            Map<String, String> memberIdByJwtToken = JwtUtil.getMemberIdByJwtToken(request);
            String loginIdAsLong = memberIdByJwtToken.get("userId");
            QueryWrapper<BForm> qw = new QueryWrapper<>();
            qw.eq("form_key",form.getFormKey());
            qw.eq("user_id",loginIdAsLong);
            boolean update = formService.update(form,qw);
            if (update) {
                return Result.success().data("one",form);
            } else {
                return Result.error().code(201).msg("修改失败");
            }
        } catch (MybatisPlusException e) {
            // 这是 mybatis-plus 的报错
            return Result.error().code(201).msg("数据错误，请注意填写规范或联系管理员");
        } catch (Exception e) {
            // 其他类型的错误
            return Result.error().code(201).msg("系统繁忙，请稍后再试或联系管理员");
        }
    }

    @PostMapping("getFormList")
    public Result getFormList(@RequestBody BForm form, HttpServletRequest request) {
        try {
            Map<String, String> memberIdByJwtToken = JwtUtil.getMemberIdByJwtToken(request);
            String userId = memberIdByJwtToken.get("userId");
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
            bFormQueryWrapper.eq("user_id",userId);
            bFormQueryWrapper.select("id","form_name","user_id","form_status","create_time","form_type","form_key");
            List<BForm> list = formService.list(bFormQueryWrapper);
            return Result.success().data("list",list);
        } catch (MybatisPlusException e) {
            // 这是 mybatis-plus 的报错
            return Result.error().code(201).msg("数据错误，请注意填写规范或联系管理员");
        }  catch (Exception e) {
            // 其他类型的错误
            return Result.error().code(201).msg("系统繁忙，请稍后再试或联系管理员");
        }
    }


}


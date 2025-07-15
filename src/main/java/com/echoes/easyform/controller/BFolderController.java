package com.echoes.easyform.controller;


import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.echoes.easyform.entity.BFolder;
import com.echoes.easyform.service.BFolderService;
import com.echoes.easyform.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 劳威锟
 * @since 2025-07-09
 */
@RestController
@RequestMapping("/easy/folder")
@Slf4j
public class BFolderController {

    @Autowired
    private BFolderService bFolderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @PostMapping("/save")
    public Result save(@RequestBody BFolder bFolder,  @RequestHeader("easy-token") String token) {

        if (StringUtils.isBlank(token)) {
            return Result.error().code(201).msg("请重新登录");
        }
        String username = stringRedisTemplate.opsForValue().get(token);
        if(!username.equals(bFolder.getUsername())) {
            return  Result.error().msg("当前用户名与页面信息不符，请刷新页面");
        }

        try {
            // 新增文件夹操作
            boolean save = bFolderService.save(bFolder);
            if(save) {
                return Result.success().data("one",bFolder);
            } else  {
                return Result.error().msg("添加文件夹失败");
            }
        } catch (Exception e) {
            log.error("讲师报名失败：{}", e.getMessage(), e);
            return Result.error().msg("添加文件夹失败");
        }
    }

}


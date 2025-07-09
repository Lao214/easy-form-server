package com.echoes.easyform.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.echoes.easyform.entity.BFriends;
import com.echoes.easyform.entity.SaUser;
import com.echoes.easyform.entity.enumVo.FriendsStatus;
import com.echoes.easyform.service.BFormService;
import com.echoes.easyform.service.BFriendsService;
import com.echoes.easyform.utils.JwtUtil;
import com.echoes.easyform.utils.LoginUtil;
import com.echoes.easyform.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 好友关系表 前端控制器
 * </p>
 *
 * @author 劳威锟
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/easy/friends")
public class BFriendsController {

    @Autowired
    private BFriendsService bFriendsService;

    @GetMapping("/getFriends")
    public Result getFriends(HttpServletRequest request){
        Long loginId = LoginUtil.getLoginId(request);
        List<SaUser> list = bFriendsService.getFriends(loginId);
        return Result.success().data("list",list);
    }

    @GetMapping("/searchUser")
    public Result searchUser(HttpServletRequest request){
        Long loginId = LoginUtil.getLoginId(request);
        List<SaUser> list = bFriendsService.searchUser(loginId);
        return Result.success().data("list",list);
    }

    @PostMapping("/addFriends")
    public Result addFriends(HttpServletRequest request, @RequestBody BFriends bFriends) {
        Long loginId = LoginUtil.getLoginId(request);

        if(loginId.equals(bFriends.getFriendId())) {
            return Result.error().code(201).msg("不能添加自己为好友");
        }

        BFriends one = bFriendsService.getOne(new QueryWrapper<BFriends>()
                .eq("user_id", loginId)
                .eq("friend_id", bFriends.getFriendId()));
        if(one != null) {
            if(one.getStatus().equals(FriendsStatus.ACCEPTED.getStatus())) {
                return Result.error().code(201).msg("已经是好友");
            } else if(one.getStatus().equals(FriendsStatus.BLOCKED.getStatus())) {
                one.setStatus(FriendsStatus.PENDING.getStatus());
                boolean update = bFriendsService.updateById(one);
                if(update) {
                    return Result.success();
                } else {
                    return Result.error().code(201).msg("添加失败");
                }

            } else if(one.getStatus().equals(FriendsStatus.PENDING.getStatus())) {
                return Result.error().code(201).msg("请勿重复添加");
            }
        }

        bFriends.setUserId(loginId);
        boolean save = bFriendsService.save(bFriends);
        if(save) {
            return Result.success();
        } else {
            return Result.error().code(201).msg("添加失败");
        }
    }

    @PostMapping("/agreeFriends")
    public Result agreeFriends(HttpServletRequest request, @RequestBody BFriends bFriends) {
        Long loginId = LoginUtil.getLoginId(request);
        if(!loginId.equals(bFriends.getFriendId())) {
            return Result.error().code(201).msg("非法操作");
        }
        bFriends.setStatus(FriendsStatus.ACCEPTED.getStatus());
        bFriendsService.updateById(bFriends);
        return Result.success();
    }

    @PostMapping("/rejectFriends")
    public Result rejectFriends(HttpServletRequest request, @RequestBody BFriends bFriends) {
        Long loginId = LoginUtil.getLoginId(request);
        if(!loginId.equals(bFriends.getFriendId())) {
            return Result.error().code(201).msg("非法操作");
        }
        bFriends.setStatus(FriendsStatus.BLOCKED.getStatus());
        bFriendsService.updateById(bFriends);
        return Result.success();
    }

    @GetMapping("/checkMyFriendsApply")
    public Result checkMyFriendsApply(HttpServletRequest request) {
        Long loginId = LoginUtil.getLoginId(request);
        List<BFriends> bFriends = bFriendsService.checkMyFriendsApply(loginId);
        return Result.success().data("list",bFriends);
    }

    @GetMapping("/checkMyFriendsApplyCount")
    public Result checkMyFriendsApplyCount(HttpServletRequest request) {
        Long loginId = LoginUtil.getLoginId(request);
        QueryWrapper<BFriends> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("friend_id",loginId);
        queryWrapper.eq("status", FriendsStatus.PENDING.getStatus());
        int count = bFriendsService.count(queryWrapper);
        return Result.success().data("count",count);
    }



}


package com.echoes.easyform.controller;


import com.echoes.easyform.entity.BFriends;
import com.echoes.easyform.entity.SaUser;
import com.echoes.easyform.service.BFormService;
import com.echoes.easyform.service.BFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/getFriends")
    public List<SaUser> getFriends(Long userId){
        return bFriendsService.getFriends(userId);
    }

}


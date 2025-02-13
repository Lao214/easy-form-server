package com.echoes.easyform.service.impl;

import com.echoes.easyform.entity.BFriends;
import com.echoes.easyform.entity.SaUser;
import com.echoes.easyform.mapper.BFriendsMapper;
import com.echoes.easyform.service.BFriendsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echoes.easyform.service.SaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 好友关系表 服务实现类
 * </p>
 *
 * @author 劳威锟
 * @since 2025-02-11
 */
@Service
public class BFriendsServiceImpl extends ServiceImpl<BFriendsMapper, BFriends> implements BFriendsService {

    @Autowired
    private SaUserService saUserService;


    @Override
    public List<SaUser> getFriends(Long userId) {

        return null;
    }

    @Override
    public List<SaUser> searchUser(Long loginId) {
        return baseMapper.searchUser(loginId);
    }
}

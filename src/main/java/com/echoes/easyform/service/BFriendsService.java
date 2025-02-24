package com.echoes.easyform.service;

import com.echoes.easyform.entity.BFriends;
import com.baomidou.mybatisplus.extension.service.IService;
import com.echoes.easyform.entity.SaUser;

import java.util.List;

/**
 * <p>
 * 好友关系表 服务类
 * </p>
 *
 * @author 劳威锟
 * @since 2025-02-11
 */
public interface BFriendsService extends IService<BFriends> {

    List<SaUser> getFriends(Long userId);

    List<SaUser> searchUser(Long loginId);

    List<BFriends>  checkMyFriendsApply(Long loginId);
}

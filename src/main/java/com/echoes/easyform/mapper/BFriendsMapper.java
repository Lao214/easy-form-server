package com.echoes.easyform.mapper;

import com.echoes.easyform.entity.BFriends;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echoes.easyform.entity.SaUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 好友关系表 Mapper 接口
 * </p>
 *
 * @author 劳威锟
 * @since 2025-02-11
 */
public interface BFriendsMapper extends BaseMapper<BFriends> {

    List<SaUser> searchUser(@Param("loginId") Long loginId);
}

package com.echoes.easyform.auth;/*
 *@title SecurityLoginService
 *@description
 *@author echoes
 *@version 1.0
 *@create 2024/5/14 09:39
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.echoes.easyform.entity.SaUser;
import com.echoes.easyform.entity.Vo.LoginUser;
import com.echoes.easyform.service.SaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class SecurityLoginService implements UserDetailsService {

    @Autowired
    private SaUserService bUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SaUser> usersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 根据用户名称查询用户信息
        usersLambdaQueryWrapper.eq(SaUser::getUsername, username);
        SaUser users = bUserService.getOne(usersLambdaQueryWrapper);
        // users==null登录失败，users！=null登陆成功
        if(Objects.isNull(users)) {
            throw new UsernameNotFoundException("用户名错误！");
        }
        // 查询数据库获取用户权限信息
//        List<String> authorityList = bUserService.getAuthority(users.getId());
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(users.getUsername());
        loginUser.setPassword(users.getPassword());
//        loginUser.setAuthorityList(authorityList);
        loginUser.setNickname(users.getNickname());
        loginUser.setAvatar(users.getAvatar());
        loginUser.setId(users.getId() + "");
        return loginUser;
    }

}
package com.echoes.easyform.controller;


import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.echoes.easyform.entity.SaUser;
import com.echoes.easyform.entity.Vo.LoginForm;
import com.echoes.easyform.entity.Vo.RegisterForm;
import com.echoes.easyform.service.SaUserService;
import com.echoes.easyform.utils.RedisUtil;
import com.echoes.easyform.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

import static org.apache.poi.sl.usermodel.PresetColor.Red;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 劳威锟
 * @since 2023-07-31
 */
@RestController
@RequestMapping("/easy/user")
public class SaUserController {

    @Autowired
    private SaUserService saUserService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * @description:  登录
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/7/31 2:31 PM
     */
    @PostMapping("doLogin")
    public Result doLogin(@RequestBody LoginForm loginForm) {
        // 第1步，先登录上
        QueryWrapper<SaUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",loginForm.getUsername());
        /**对称加密**/
        queryWrapper.eq("password", SaSecureUtil.aesEncrypt("echoes",loginForm.getPassword()));
        SaUser one = saUserService.getOne(queryWrapper);
        if(one != null) {
            StpUtil.login(one.getId());
            List<String> permissionList = StpUtil.getPermissionList(one.getId());
            // 第2步，获取 Token  相关参数
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            // 第3步，返回给前端
            return Result.success().data("tokenInfo",tokenInfo).data("permissionList",permissionList);
        }else  {
            // 用户不存在
            return Result.error().code(500).msg("用户名或密码不存在");
        }
    }


    /**
     * @description:  注册
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/7/31 2:31 PM
     */
    @PostMapping("doRegister")
    public Result doRegister(@RequestBody SaUser saUser) {
        QueryWrapper<SaUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",saUser.getUsername());
        SaUser one = saUserService.getOne(queryWrapper);
        if(one != null) {
            return Result.error().code(500).msg("用户名已存在");
        } else {
            saUser.setPassword( SaSecureUtil.aesEncrypt("echoes",saUser.getPassword()));
            boolean save = saUserService.save(saUser);
            if(save) {
                return  Result.success().msg("注册成功");
            } else {
                return  Result.error().code(500).msg("注册失败");
            }
        }
    }

    /**
     * @description:  邮箱发送验证码进行注册
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/7/31 2:31 PM
     */
    @GetMapping("sendRegister/{email}")
    public Result sendRegister(@PathVariable String email){
        /** 校验邮箱是否已经注册 **/
        QueryWrapper<SaUser> queryWrapper = new QueryWrapper<SaUser>();
        queryWrapper.eq("email", email);
        SaUser one = saUserService.getOne(queryWrapper);
        if (one != null) {
            return  Result.success().msg("该邮箱已经被注册");
        }
        /** 校验邮箱的验证码有没有过期 **/
        Long ttl = redisUtil.ttl(email);
        if (ttl != null && ttl > 0) {
            return  Result.success().msg("您的验证码尚未过期，请不要频繁发送,还有"+ttl+"秒才过期");
        }

        // 发送人
        String from = "1085444086@qq.com";
        // 接收人
        String to = email;
        // 标题
        String subject = "easyForm给您发送的注册验证码";
        // 随机生成六位验证码
        String verificationCode = generateRandomCode(6);
        // 正文
        String text = "尊敬的用户，您的验证码为"+ verificationCode +",请妥善保管，切勿泄露，有效期为30分钟。";
        // 简单邮件发送
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from + "(easyForm官方邮箱)");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);

        /** 发送邮件后，保存验证码和对应邮箱账号在redis中，并且有效时间为30分钟 **/
        redisUtil.setex(email,verificationCode,1800l);

        return  Result.success().msg("发送成功");
    }

    /**
     * @description:  邮箱发送验证码进行注册
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/7/31 2:31 PM
     */
    @GetMapping("sendLogin/{email}")
    public Result sendLogin(@PathVariable String email){
        /** 校验邮箱是否已经注册 **/
        QueryWrapper<SaUser> queryWrapper = new QueryWrapper<SaUser>();
        queryWrapper.eq("email", email);
        SaUser one = saUserService.getOne(queryWrapper);
        if (one != null) {
            /** 校验邮箱的验证码有没有过期 **/
            Long ttl = redisUtil.ttl(email+"-login");
            if (ttl != null && ttl > 0) {
                return  Result.success().msg("您的验证码尚未过期，请不要频繁发送,还有"+ttl+"秒才过期");
            }
            // 发送人
            String from = "1085444086@qq.com";
            // 接收人
            String to = email;
            // 标题
            String subject = "easyForm给您发送的登录验证码";
            // 随机生成六位验证码
            String verificationCode = generateRandomCode(6);
            // 正文
            String text = "尊敬的用户，您的验证码为"+ verificationCode +",请妥善保管，切勿泄露，有效期为30分钟。";
            // 简单邮件发送
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from + "(easyForm官方邮箱)");
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(text);
            javaMailSender.send(mailMessage);

            /** 发送邮件后，保存验证码和对应邮箱账号在redis中，并且有效时间为30分钟 **/
            redisUtil.setex(email+"-login",verificationCode,1800l);
            return  Result.success().msg("发送成功");
        } else {
            return  Result.success().msg("该邮箱尚未进行注册");
        }
    }


    public static String generateRandomCode(int length) {
        char[] chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length);
            sb.append(chars[index]);
        }
        return sb.toString();
    }

    /**
     * @description:  邮箱注册
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/7/31 2:31 PM
     */
    @PostMapping("emailRegister")
    public Result emailRegister(@RequestBody RegisterForm registerForm) {
        Long ttl = redisUtil.ttl(registerForm.getEmail());
        /** 如果有验证码则继续 **/
        if (ttl != null && ttl > 0) {
            Object o = redisUtil.get(registerForm.getEmail());
            /** 校验验证码 **/
            if(o.toString().equals(registerForm.getEmailCode())) {
                SaUser saUser = new SaUser();
                /** 用户名为邮箱 **/
                saUser.setUsername(registerForm.getEmail());
                saUser.setPassword(SaSecureUtil.aesEncrypt("echoes",registerForm.getPassword()));
                saUser.setEmail(registerForm.getEmail());
                saUser.setIsAdmin(registerForm.getIsAdmin());
                saUser.setCreateTime(new Date());
                boolean save = saUserService.save(saUser);
                if (save) {
                    /** 注册成功，删除验证码 **/
                    redisUtil.del(registerForm.getEmail());
                    return Result.success().msg("注册成功");
                } else {
                    return Result.success().msg("注册失败,请稍后再试");
                }
            } else {
                return Result.error().msg("验证码错误，请输入正确的验证码");
            }
        } else {  /** 无验证码则返回失败 **/
            return Result.error().msg("验证码可能已经过期，请重新发送验证码");
        }
    }


    /**
     * @description:  邮箱登录
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/7/31 2:31 PM
     */
    @PostMapping("emailLogin")
    public Result emailLogin(@RequestBody RegisterForm registerForm) {
        Long ttl = redisUtil.ttl(registerForm.getEmail() + "-login");
        /** 如果有验证码则继续 **/
        if (ttl != null && ttl > 0) {
            Object o = redisUtil.get(registerForm.getEmail());
            /** 校验验证码 **/
            if(o.toString().equals(registerForm.getEmailCode())) {
                QueryWrapper<SaUser> saUserQueryWrapper = new QueryWrapper<SaUser>();
                saUserQueryWrapper.eq("email",registerForm.getEmail());
                SaUser one = saUserService.getOne(saUserQueryWrapper);
                if(one != null) {
                    StpUtil.login(one.getId());
                    List<String> permissionList = StpUtil.getPermissionList(one.getId());
                    // 第2步，获取 Token  相关参数
                    SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                    // 第3步，返回给前端
                    return Result.success().data("tokenInfo",tokenInfo).data("permissionList",permissionList);
                } else  {
                    return Result.error().msg("用户不存在");
                }
            } else {
                return Result.error().msg("验证码错误，请输入正确的验证码");
            }
        } else {  /** 无验证码则返回失败 **/
            return Result.error().msg("您的验证码可能已经过期，请重新发送验证码");
        }
    }

    /**
     * @description:  邮箱登录
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/7/31 2:31 PM
     */
    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        // 获取请求头token字符串
        String token = request.getHeader("ec-token");

        // 根据token获取ID
        try {
            Object loginId = StpUtil.getLoginId();
            QueryWrapper<SaUser> saUserQueryWrapper =new QueryWrapper<>();
            saUserQueryWrapper.eq("id",loginId);
            SaUser one = saUserService.getOne(saUserQueryWrapper);
            //根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
//            Map<String,Object> result = new HashMap<>();
//            result.put("name",one.getUsername());
//            result.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//            result.put("roles","[\"admin\"]");
            //菜单权限数据
//            result.put("routers",routerVolist);
            //按钮权限数据
//            result.put("buttons",permsList);
            return Result.success().data("one",one);
        }catch (Exception e) {
            return Result.error().code(201).msg("请重新登录");
        }

    }

}


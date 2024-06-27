package com.echoes.easyform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.echoes.easyform.auth.LoginService;
import com.echoes.easyform.entity.SaUser;
import com.echoes.easyform.entity.Vo.LoginUser;
import com.echoes.easyform.service.SaUserService;
import com.echoes.easyform.utils.*;
import com.echoes.easyform.utils.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;



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


    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;


    /**
     * @description:  登录
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/7/31 2:31 PM
     */
    @PostMapping("/login")
    public Result login(@RequestBody SaUser userValidate) {
        // 认证通过，返回给前端jjwt
        String jjwtStr = loginService.Login(userValidate);

        // 计算过期时间，假设过期时间为12小时后
        long expTimeMillis = System.currentTimeMillis() + 12 * 60 * 60 * 1000;
        // 可选：将过期时间格式化为更友好的字符串格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedExpTime = Instant.ofEpochMilli(expTimeMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .format(formatter);
        return Result.success().msg("登录成功").data("token",jjwtStr).data("expTime", formattedExpTime);
    }

    @GetMapping("/logout")
    public Result loginOut() {
        String msg = loginService.loginOut();
        return Result.success().msg(msg);
    }


    @PostMapping("/addUser")
    @Transactional(rollbackFor = Exception.class)
    public Result addUser(@RequestBody SaUser bUser) {
        // 创建密码解析器
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // 对密码进行加密
        String bpePassword = bCryptPasswordEncoder.encode(bUser.getPassword());
        bUser.setPassword(bpePassword);
        bUser.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        boolean save = saUserService.save(bUser);
        if(save) {
//            BUserRole bUserRole = new BUserRole();
//            bUserRole.setUserId(bUser.getId());
//            bUserRole.setRoleId(RoleEnum.GENERAL_USER.getRoleId());
//            bUserRoleService.save(bUserRole);
            return Result.success().msg("添加成功");
        } else  {
            return Result.error().msg("添加成功");
        }
    }


    /**
     * @description:  获取用户信息
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/7/31 2:31 PM
     */
    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        // 获取请求头token字符串
        String token = request.getHeader("easy-token");
        if (token == null) {
            return Result.error().code(ResponseStatus.TOKEN_NOT_EXIST.getCode()).msg(ResponseStatus.TOKEN_NOT_EXIST.getMessage());
        }
        Map<String, String> memberIdByJwtToken = JwtUtil.getMemberIdByJwtToken(token);
        String username = memberIdByJwtToken.get("username");
        // 获取redis中用户信息
        LoginUser loginUser = redisService.getCacheObject(username);
        if (Objects.isNull(loginUser)) {
            return Result.error().code(ResponseStatus.TOKEN_NOT_EXIST.getCode()).msg(ResponseStatus.TOKEN_NOT_EXIST.getMessage());
        }
        // 根据token获取ID
        try {
            loginUser.setPassword("");
            return Result.success().data("one",loginUser);
        }catch (Exception e) {
            return Result.error().code(ResponseStatus.TOKEN_VALID.getCode()).msg(ResponseStatus.TOKEN_VALID.getMessage());
        }
    }


    /**
     * @description:  注册
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/7/31 2:31 PM
     */
//    @PostMapping("doRegister")
//    public Result doRegister(@RequestBody SaUser saUser) {
//        QueryWrapper<SaUser> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username",saUser.getUsername());
//        SaUser one = saUserService.getOne(queryWrapper);
//        if(one != null) {
//            return Result.error().code(500).msg("用户名已存在");
//        } else {
//            saUser.setPassword( SaSecureUtil.aesEncrypt("echoes",saUser.getPassword()));
//            boolean save = saUserService.save(saUser);
//            if(save) {
//                return  Result.success().msg("注册成功");
//            } else {
//                return  Result.error().code(500).msg("注册失败");
//            }
//        }
//    }

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
//    @PostMapping("emailRegister")
//    public Result emailRegister(@RequestBody RegisterForm registerForm) {
//        Long ttl = redisUtil.ttl(registerForm.getEmail());
//        /** 如果有验证码则继续 **/
//        if (ttl != null && ttl > 0) {
//            Object o = redisUtil.get(registerForm.getEmail());
//            /** 校验验证码 **/
//            if(o.toString().equals(registerForm.getEmailCode())) {
//                SaUser saUser = new SaUser();
//                /** 用户名为邮箱 **/
//                saUser.setUsername(registerForm.getEmail());
//                saUser.setPassword(SaSecureUtil.aesEncrypt("echoes",registerForm.getPassword()));
//                saUser.setEmail(registerForm.getEmail());
//                saUser.setIsAdmin(registerForm.getIsAdmin());
//                saUser.setCreateTime(new Date());
//                boolean save = saUserService.save(saUser);
//                if (save) {
//                    /** 注册成功，删除验证码 **/
//                    redisUtil.del(registerForm.getEmail());
//                    return Result.success().msg("注册成功");
//                } else {
//                    return Result.success().msg("注册失败,请稍后再试");
//                }
//            } else {
//                return Result.error().msg("验证码错误，请输入正确的验证码");
//            }
//        } else {  /** 无验证码则返回失败 **/
//            return Result.error().msg("验证码可能已经过期，请重新发送验证码");
//        }
//    }


    /**
     * @description:  邮箱登录
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/7/31 2:31 PM
     */
//    @PostMapping("emailLogin")
//    public Result emailLogin(@RequestBody RegisterForm registerForm) {
//        Long ttl = redisUtil.ttl(registerForm.getEmail() + "-login");
//        /** 如果有验证码则继续 **/
//        if (ttl != null && ttl > 0) {
//            Object o = redisUtil.get(registerForm.getEmail());
//            /** 校验验证码 **/
//            if(o.toString().equals(registerForm.getEmailCode())) {
//                QueryWrapper<SaUser> saUserQueryWrapper = new QueryWrapper<SaUser>();
//                saUserQueryWrapper.eq("email",registerForm.getEmail());
//                SaUser one = saUserService.getOne(saUserQueryWrapper);
//                if(one != null) {
//                    StpUtil.login(one.getId());
//                    List<String> permissionList = StpUtil.getPermissionList(one.getId());
//                    // 第2步，获取 Token  相关参数
//                    SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
//                    // 第3步，返回给前端
//                    return Result.success().data("tokenInfo",tokenInfo).data("permissionList",permissionList);
//                } else  {
//                    return Result.error().msg("用户不存在");
//                }
//            } else {
//                return Result.error().msg("验证码错误，请输入正确的验证码");
//            }
//        } else {  /** 无验证码则返回失败 **/
//            return Result.error().msg("您的验证码可能已经过期，请重新发送验证码");
//        }
//    }


}


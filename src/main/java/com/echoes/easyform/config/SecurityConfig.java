package com.echoes.easyform.config;

import com.echoes.easyform.auth.JwtAuthenticationEntryPoint;
import com.echoes.easyform.filter.JwtSecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/** @EnableMethodSecurity 开启方法级别的权限验证 **/
//@EnableMethodSecurity
/** 开启security权限配置 **/
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 自定义加密方法
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * 认证
     * */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private JwtSecurityFilter jwtSecurityFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    /*
     * 访问路径拦截
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // 关闭csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 不通过session获取SecurityContext
                .and()
                .authorizeRequests()
                .antMatchers("/easy/user/login","/easy/user/logout","/easy/BForm/getFormByKeyPublic/{formKey}","/easy/BAnswer/saveAnswer","/websocket/**").permitAll()  // 允许登录接口匿名访问
                .anyRequest().authenticated();  // 除上述之外的全部请求都需要鉴权认证
        http    // 将自定义JWT校验过滤链方法UsernamePasswordAuthenticationToken过滤链之前
                .addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http.cors();
    }

}
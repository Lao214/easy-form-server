package com.echoes.easyform.auth;

import com.alibaba.fastjson.JSON;
import com.echoes.easyform.utils.ResponseStatus;
import com.echoes.easyform.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *@title MyAuthenticationFailureHandler (认证失败处理)
 *@description 创建MyAuthenticationFailureHandler类实现AuthenticationFailureHandler接口，用于认证失败的处理
 *@author echoes
 *@version 1.0
 *@create 2024/5/11 14:32
 */


@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(JSON.toJSONString(Result.error().msg(ResponseStatus.AUTH_FAILURE.getMessage()).code(ResponseStatus.AUTH_FAILURE.getCode())));
    }

}

package com.echoes.easyform.utils;/*
 *@title LoginUtil
 *@description
 *@author echoes
 *@version 1.0
 *@create 2025/2/11 11:53
 */

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class LoginUtil {

    public static Long getLoginId(HttpServletRequest request) {
        Map<String, String> memberIdByJwtToken = JwtUtil.getMemberIdByJwtToken(request);
        return Long.valueOf(memberIdByJwtToken.get("userId"));
    }

    public static String getLoginIdStr(HttpServletRequest request) {
        Map<String, String> memberIdByJwtToken = JwtUtil.getMemberIdByJwtToken(request);
        return memberIdByJwtToken.get("userId");
    }
}

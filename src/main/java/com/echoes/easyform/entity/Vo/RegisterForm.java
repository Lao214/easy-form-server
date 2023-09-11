package com.echoes.easyform.entity.Vo;

import lombok.Data;

/**
 * @author kevinL
 * @version 1.0
 * @description: TODO
 * @date 2023/8/1 2:05 PM
 */
@Data
public class RegisterForm {

    String username;

    String password;

    String email;

    String emailCode;

    Integer isAdmin;
}

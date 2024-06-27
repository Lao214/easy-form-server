package com.echoes.easyform.entity.Vo;/*
 *@title Menu
 *@description
 *@author echoes
 *@version 1.0
 *@create 2024/6/27 11:57
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {
    private static final long serialVersionUID = -54979041104113736L;

    private Integer id;
    private String menu_name;
    private String perms;
}
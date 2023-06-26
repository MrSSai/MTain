package com.mybatis.sbmp.entity;

import lombok.Data;

import java.util.Date;


/**
 * @author zss
 * @date 2023年06月26日 15:43
 */

@Data
public class Users {

    private int id;
    private String phoneNumber;
    private String nickname;
    private String password;
    private Date createTime;
    private Date loginTime;
    private int isDisabled;
}
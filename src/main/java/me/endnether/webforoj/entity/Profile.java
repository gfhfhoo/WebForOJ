package me.endnether.webforoj.entity;

import me.endnether.webforoj.enums.UserGroup;

public class Profile extends UserBase {
    private int rank;//排名
    private String regTime;//注册时间
    private UserGroup userGroup;//用户组
    private String ps;//个性签名
    private int accepted;//已经通过的题目数
    private int submitted;//提交代码次数
    private String intro;//个人背景板介绍
}

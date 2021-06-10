package com.macro.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmsAdminParam {
    //用户名
    private String username;
    //密码
    private String password;
    //用户头像
    private String icon;
    //邮箱
    private String email;
    //用户昵称
    private String nickName;
    //备注
    private String note;
}

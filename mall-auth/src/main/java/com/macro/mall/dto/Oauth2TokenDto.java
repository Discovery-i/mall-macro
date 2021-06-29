package com.macro.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Oauth2TokenDto {
    private String token;
    private String refreshToken;
    private String tokenHead;
    private int expiresIn;
}

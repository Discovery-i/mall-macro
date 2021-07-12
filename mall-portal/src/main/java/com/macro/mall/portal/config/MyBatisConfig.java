package com.macro.mall.portal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.macro.mall.portal"})
public class MyBatisConfig {
}

package com.macro.mall.portal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.macro.mall.mapper","com.macro.mall.portal.dao"})
public class MyBatisConfig {
}

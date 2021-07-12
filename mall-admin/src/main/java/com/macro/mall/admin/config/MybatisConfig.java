package com.macro.mall.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.macro.mall.mapper","com.macro.mall.admin.dao"})
public class MybatisConfig {
}

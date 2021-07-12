package com.macro.mall.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.macro.mall.mapper","com.macro.mall.demo.dao"})
public class MyBatisConfig {
}

package com.shanzhu.purchase.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis 配置类
 *
 * @author: ShanZhu
 * @date: 2024-05-01
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.shanzhu.purchase.mapper","com.shanzhu.purchase.dao"})
public class MyBatisConfig {
}

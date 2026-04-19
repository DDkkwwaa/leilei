package com.shanzhu.purchase.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分页 配置类
 *
 * @author: ShanZhu
 * @date: 2024-05-01
 */
@Configuration
public class PageHelperConfigure {

    @Bean
    public Interceptor[] plugins() {
        return new Interceptor[]{new PageInterceptor()};
    }

}

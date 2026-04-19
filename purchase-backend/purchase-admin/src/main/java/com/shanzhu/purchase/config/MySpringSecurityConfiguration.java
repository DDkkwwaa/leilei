package com.shanzhu.purchase.config;


import com.shanzhu.purchase.filter.TokenLoginFilter;
import com.shanzhu.purchase.filter.TokenVerifyFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * SpringSecurity 配置类
 *
 * @author: ShanZhu
 * @date: 2024-05-01
 */
@Configuration
public class MySpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService) // 绑定自定义的认证Service
                .passwordEncoder(new BCryptPasswordEncoder()); // 绑定密码处理器
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //测试用例以及swagger
                .antMatchers("/doc.html",
                        "/doc.html/**", "/webjars/**",
                        "/v2/**", "/swagger-resources",
                        "/swagger-resources/**",  //"/swagger-ui.html",
                        "/swagger-ui.html/**").permitAll()
                .antMatchers("/api/*/auth/**","/test/**").permitAll()  //登陆注册
                .anyRequest().authenticated()
                .and()
                // 设置跨域的处理  corsConfigurationSource
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .addFilter(new TokenLoginFilter(super.authenticationManager())) // 绑定认证的接口
                .addFilter(new TokenVerifyFilter(super.authenticationManager())) // 绑定校验的接口
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    /**
     * 设置跨域的信息--------------替代原来的GlobalCorsConfig
     * @return
     */
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        // 配置跨域拦截的相关信息
        //放行全部原始头信息
        config.setAllowedHeaders(Arrays.asList("*"));
        //允许所有请求方法跨域调用
        config.setAllowedMethods(Arrays.asList("*"));
        //允许所有域名进行跨域调用
        config.setAllowedOrigins(Arrays.asList("*"));
        //设置请求的时间
        config.setMaxAge(3600l);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
    }

}

package com.shanzhu.purchase.filter;

import com.alibaba.fastjson.JSON;
import com.shanzhu.purchase.constant.Constant;
import com.shanzhu.purchase.model.XtmdAdmin;
import com.shanzhu.purchase.service.OperationLogService;
import com.shanzhu.purchase.util.JWTUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public TokenLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        XtmdAdmin admin = null;
        try {
            String loginInfo = getRequestJSON(request);
            admin = JSON.parseObject(loginInfo, XtmdAdmin.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(admin.getUserName(), admin.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getRequestJSON(HttpServletRequest request) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            sb.append(inputStr);
        }
        return sb.toString();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Map<String, String> map = new HashMap<>();
        map.put("username", authResult.getName());
        String token = JWTUtils.getToken(map);
        response.addHeader("Authorization", Constant.SYS_TOKEN_PREFIX + token);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", HttpServletResponse.SC_OK);
        resultMap.put("msg", "Authentication passed");
        try {
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
            if (context != null) {
                OperationLogService operationLogService = context.getBean(OperationLogService.class);
                operationLogService.record(authResult.getName(), "LOGIN", "AUTH", null, "User login success", request);
            }
        } catch (Exception ignored) {
        }
        writer.write(JSON.toJSONString(resultMap));
        writer.flush();
        writer.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", HttpServletResponse.SC_UNAUTHORIZED);
        resultMap.put("msg", "Username or password is invalid");
        writer.write(JSON.toJSONString(resultMap));
        writer.flush();
        writer.close();
    }
}

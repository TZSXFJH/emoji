package com.ustclab.emoji.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.ustclab.emoji.common.model.dao.User;
import com.ustclab.emoji.common.model.vo.Result;
import com.ustclab.emoji.common.model.vo.ResultCodeEnum;
import com.ustclab.emoji.common.util.JwtUtil;
import com.ustclab.emoji.common.util.UserLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author TZSXFJH
 * @date 2024/11/27
 */
@Slf4j
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1 获取请求方式
        //如果请求方式是options 预检请求，直接放行
        String method = request.getMethod();
        if("OPTIONS".equals(method)) {
            return true;
        }

        //2 从请求头获取token
        log.info(request.getRequestURI());
        log.info("登录校验");
        String token = request.getHeader("token");

        //3 如果token为空，返回错误提示
        if(token == null || token.isEmpty()) {
            responseNoLoginInfo(response);
            return false;
        }
        String userId;
        try {
            userId = JwtUtil.getAudience(token, 0);
            JwtUtil.verifyToken(token, userId);

        } catch (Exception e) {
            responseNoLoginInfo(response);
            return false;
        }
        User user = JSON.parseObject(JwtUtil.getClaimByName(token, "user").asString(), User.class);
        if(!user.getUserId().toString().equals(userId)) {
            responseNoLoginInfo(response);
            return false;
        }
        UserLocalUtil.set(user);
        // 放行
        log.info("登录校验通过");
        return true;
    }

    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserLocalUtil.remove();  // 移除threadLocal中的数据
    }

}

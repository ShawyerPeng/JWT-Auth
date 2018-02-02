package com.bitswild.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.bitswild.bean.User;
import com.bitswild.bean.UserData;
import com.bitswild.config.Constant;
import com.bitswild.util.JwtUtil;
import com.bitswild.util.ResponseUtil;
import com.bitswild.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private JwtUtil jwt;

    public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response, UserData userData) {
        try {
            if (!"admin".equals(userData.getAccount()) || !"123456".equals(userData.getPassword())) {
                return ResponseUtil.exception("账号或者密码错误");
            }
            userData.setRoleId(1L);
            User user = UserUtil.userDataToUser(userData);
            String subject = JwtUtil.generalSubject(user);
            String token = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
            String refreshToken = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
            JSONObject jo = new JSONObject();
            jo.put("token", token);
            jo.put("refreshToken", refreshToken);
            return ResponseUtil.success(jo);
        } catch (Exception e) {
            logger.error(e);
            return ResponseUtil.unKonwException();
        }
    }
}
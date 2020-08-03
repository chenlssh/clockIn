package com.cls.clock.controller;

import com.alibaba.fastjson.JSONObject;
import com.cls.clock.dao.LoginDAO;
import com.cls.clock.util.HttpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Description 根据code获取微信openID等信息并保存用户信息
 * @Author chenLongshun
 * @DATE 2020/7/12
 */
@RestController
public class LoginController {

    @Autowired
    private LoginDAO loginDAO;
    private static final Log logger = LogFactory.getLog(LoginController.class);
    private static final String appId = "wx26e04e5261bca62f";
    private static final String appSecret = "fb6783958d0ac0e45c0fd7d26866ed54";

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String clockInLogin(@RequestBody JSONObject jsonParams){
        String wxLoginCode = jsonParams.getString("code");
        System.out.println(wxLoginCode);
        String wxLoginUrl =  "https://api.weixin.qq.com/sns/jscode2session?appid="+appId
                +"&secret="+appSecret
                +"&js_code="+wxLoginCode
                +"&grant_type=authorization_code";

        String userWxMsx = HttpUtil.httpsRequest(wxLoginUrl,"GET",null);
        logger.info("微信返回用户信息："+userWxMsx);
        JSONObject userWxMsxJson =  (JSONObject) JSONObject.parse(userWxMsx);
        String wxOpenId = userWxMsxJson.getString("openid");
        if(loginDAO.checkUserRegister(wxOpenId) == 0){
            loginDAO.saveUser(wxOpenId);
        }
        return wxOpenId;
    }
}

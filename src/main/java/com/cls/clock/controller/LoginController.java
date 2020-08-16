package com.cls.clock.controller;

import com.alibaba.fastjson.JSONObject;
import com.cls.clock.dao.LoginDAO;
import com.cls.clock.util.HttpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.cls.clock.util.constant.clockStatus.*;

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
    private static final String REG_STATUS = "wx26e04e5261bca62f";
    private static final String appId = "wx26e04e5261bca62f";
    private static final String appSecret = "fb6783958d0ac0e45c0fd7d26866ed54";

    @RequestMapping(value = "/checkRegisterStatus",method = RequestMethod.POST)
    public JSONObject checkRegisterStatus(@RequestBody JSONObject requestParams){
        String wxLoginCode = requestParams.getString("code");
        JSONObject response = new JSONObject();
        logger.info("loginCode："+wxLoginCode);
        String wxLoginUrl =  "https://api.weixin.qq.com/sns/jscode2session?appid="+appId
                +"&secret="+appSecret
                +"&js_code="+wxLoginCode
                +"&grant_type=authorization_code";

        String userWxMsx = HttpUtil.httpsRequest(wxLoginUrl,"GET",null);
        logger.info("微信返回用户信息："+userWxMsx);
        JSONObject userWxMsxJson =  (JSONObject) JSONObject.parse(userWxMsx);
        String wxOpenId = userWxMsxJson.getString("openid");
        if(loginDAO.checkUserRegister(wxOpenId) == 0){
            response.put("registerStatus",NEED_REGISTER);
        }else{
            response.put("registerStatus",HAVING_REGISTER);
        }
        response.put("wxOpenId",wxOpenId);
        return response;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public void register(@RequestBody JSONObject jsonParams){
        if(loginDAO.checkUserRegister(jsonParams.getString("open_id")) == 0){
            loginDAO.saveUser(jsonParams);
        }else{
            loginDAO.updateUser(jsonParams); // 已注册，则更新昵称（昵称有可能会改）
        }

    }
}

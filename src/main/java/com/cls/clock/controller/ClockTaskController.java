package com.cls.clock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cls.clock.dao.ClockTaskDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Description 每日打卡相关操作：查询每日打卡任务，打卡
 * @Author chenLongshun
 * @DATE 2020/5/20
 */
@RestController
@RequestMapping(value = "/clockTask")
public class ClockTaskController {

    @Autowired
    private ClockTaskDAO clockTaskDAO;
    private static final Log logger = LogFactory.getLog(ClockTaskController.class);

    @RequestMapping(value = "/getTodayTaskList",method = RequestMethod.POST)
    public String getTodayTaskById(@RequestBody JSONObject jsonParams){
        return JSON.toJSONString(this.clockTaskDAO.getTodayTaskByUserId(jsonParams.getString("user_id")));
    }

    @RequestMapping(value = "/clockTodayTask",method = RequestMethod.POST)
    public void clockTodayTask(@RequestBody JSONObject jsonParams){
        logger.info("用户打卡记录："+jsonParams.toString());
        this.clockTaskDAO.clockTodayTask(jsonParams.getString("user_id"),jsonParams.getString("task_id"));
    }
}

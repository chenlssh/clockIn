package com.cls.clock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cls.clock.dao.StatisticCardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Description 統計打卡次數
 * @Author chenLongshun
 * @DATE 2020/5/20
 */
@RestController
@RequestMapping(value = "/statisticCard")
public class StatisticCardController {

    @Autowired
    private StatisticCardDAO statisticCardDAO;

    @RequestMapping(method = RequestMethod.POST)
    public String getStatisticsByUserId(@RequestBody JSONObject jsonParams){
        return JSON.toJSONString(this.statisticCardDAO.getStatisticCardByUserId(jsonParams.getString("user_id")));
    }
}

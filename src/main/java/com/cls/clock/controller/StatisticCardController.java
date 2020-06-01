package com.cls.clock.controller;

import com.alibaba.fastjson.JSON;
import com.cls.clock.dao.StatisticCardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public String getStatisticsByUserId(@PathVariable("userId") String userId){
        return JSON.toJSONString(this.statisticCardDAO.getStatisticCardByUserId(userId));
    }
}

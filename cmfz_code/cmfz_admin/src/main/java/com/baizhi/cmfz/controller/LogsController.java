package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Logs;
import com.baizhi.cmfz.service.LogsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
@Controller
@RequestMapping("/logs")
@RestController
public class LogsController {

    @Resource
    private LogsService logsService;
    @RequestMapping("/page")
    @ResponseBody
    public HashMap<String, Object> pageView(Integer page, Integer rows){

        List<Logs> pictures = logsService.queryPage(page, rows);
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 查询所有的总条数
        Long page1 = logsService.queryCount();
        map.put("total",page1);
        map.put("rows",pictures);
        return map;
    }
}

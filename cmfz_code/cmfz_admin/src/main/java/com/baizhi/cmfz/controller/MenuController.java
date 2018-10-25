package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;
    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Menu> queryAllMenu(){
        List<Menu> menus = menuService.queryAll();
        return menus;
    }
}

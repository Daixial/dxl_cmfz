package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Person;
import com.baizhi.cmfz.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/person")
public class PersonController {
    @Resource
    private PersonService personService;


    @RequestMapping("findAll")
    public @ResponseBody List<Person> findAll(){
        return personService.queryAll();
    }
}

package com.imooc.controller;

import com.imooc.pojo.Stu;
import com.imooc.service.StuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class StuController {

    @Autowired
    private StuService stuService;

    @GetMapping("getStuById")
    public Stu getStuById(int id){
        return stuService.getStuInfo(id);
    }

    @PostMapping("/saveStu")
    public String saveStu(){
        stuService.saveStu();
        return "ok";
    }

    @PostMapping("/updateStu")
    public String updateStu(int id){
        stuService.updateStu(id);
        return "ok";
    }

    @PostMapping("/deleteStuById")
    public String deleteStuById(int id){
        stuService.deleteStu(id);
        return "ok";
    }
}

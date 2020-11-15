package com.imooc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class HelloController {

    final static  Logger logger = LoggerFactory.getLogger(HelloController.class);
    @RequestMapping("/hello")
    public  Object String(){
        logger.info("info:hello");
        logger.warn("warn:hello");
        logger.debug("debug:hello");
        logger.error("error:hello");
        return "Hello~";
    }
}

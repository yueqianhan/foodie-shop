package com.imooc.enums;

/**
 * 性别枚举
 */
public enum Sex {

    man(0,"男"),
    woman(1,"女"),
    secret(2,"保密");

    public final Integer type;
    public final String value;

    Sex(Integer type,String value){
        this.type = type;
        this.value = value;
    }

}

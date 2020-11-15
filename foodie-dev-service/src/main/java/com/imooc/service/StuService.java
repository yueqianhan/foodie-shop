package com.imooc.service;

import com.imooc.pojo.Stu;

public interface StuService {
    //根据id获取学生信息
    public Stu getStuInfo(int id);

    //根据id更新学生信息
    public void updateStu(int id);

    //保存学生信息
    public void saveStu();

    //根据id删除学生信息
    public void deleteStu(int id);


}

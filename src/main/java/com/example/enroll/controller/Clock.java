package com.example.enroll.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enroll.api.CommonResult;
import com.example.enroll.mapper.CountMapper;
import com.example.enroll.mapper.StudentMapper;
import com.example.enroll.mapper.VisitorMapper;
import com.example.enroll.pojo.CountStudent;
import com.example.enroll.pojo.Student;
import com.example.enroll.pojo.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author 滚韬
 * @date 2021.10.3
 */
@RestController
public class Clock {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CountMapper countMapper;
    @Autowired
    VisitorMapper visitorMapper;
    @PostMapping("/clock")
    public CommonResult clock(@RequestParam String specialty, @RequestParam String username) {
        QueryWrapper<Student> wrapperStudent = new QueryWrapper<>();
        QueryWrapper<CountStudent> wrapperCount = new QueryWrapper<>();
        QueryWrapper<Visitor> wrapperVisitor = new QueryWrapper<>();
        wrapperStudent //学生查询构造器
                .eq("username", username);
        wrapperCount //计数查询构造器
                .ne("count", -1);
        wrapperVisitor
                .eq("username",username);
        Student student = studentMapper.selectOne(wrapperStudent);//找到该学生
        if(student!=null) { //学生表里找到了
            if (student.getTime() != null) {
                return CommonResult.success(student, "获取成功,该学生已经打卡");
            } else {
                Timestamp time = Timestamp.valueOf(LocalDateTime.now());//获得当前时间
                CountStudent count = countMapper.selectOne(wrapperCount);//获取当前计数
                student.setCount(count.getCount());//设置计数
                student.setSpecialty(specialty);//设置专业
                student.setTime(time);//设置时间
                studentMapper.update(student, wrapperStudent);//更新
                count.setCount(count.getCount() + 1);//+1操作
                countMapper.update(count, wrapperCount);//更新
                return CommonResult.success(student, "学生打卡成功,未重复");
            }
        }
        //没找到,判断访客表里有没有
        Visitor visitorExist = visitorMapper.selectOne(wrapperVisitor);

        if (visitorExist!=null) { //访客表里找到了
            Timestamp time = Timestamp.valueOf(LocalDateTime.now());//获得当前时间
            int count = (int) (Math.random()*100+100);
            visitorExist.setUsername(username);//设置名字
            visitorExist.setCount(count);//设置计数
            visitorExist.setTime(time);//设置时间
            visitorMapper.update(visitorExist, wrapperVisitor);//更新
            return CommonResult.success(visitorExist, "访客打卡成功,已覆盖");
        } else {
            Timestamp time = Timestamp.valueOf(LocalDateTime.now());//获得当前时间
            int count = (int) (Math.random()*100+100);
            Visitor visitor = new Visitor();
            visitor.setUsername(username);//设置名字
            visitor.setCount(count);//设置计数
            visitor.setTime(time);//设置时间
            visitorMapper.insert(visitor);//插入
            return CommonResult.success(visitor, "访客打卡成功,未重复");
        }
    }
}

package com.example.enroll.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enroll.api.CommonResult;
import com.example.enroll.mapper.StudentImageMapper;
import com.example.enroll.pojo.StudentImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 滚韬
 * @date 2021.10.5
 */
@RestController
public class GetOne {
    @Autowired
    StudentImageMapper studentImageMapper;
    @GetMapping("/getone")
    public CommonResult Get(@RequestParam String username){
        QueryWrapper<StudentImage> wrapperstudentImage = new QueryWrapper<>();
        wrapperstudentImage
                .eq("username", username);
        StudentImage studentImage = studentImageMapper.selectOne(wrapperstudentImage);
        return CommonResult.success(studentImage,"获取图片成功");
    }
}

package com.example.enroll.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enroll.api.CommonResult;
import com.example.enroll.mapper.StudentImageMapper;
import com.example.enroll.pojo.StudentImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 滚韬
 * @date 2021.10.5
 */
@RestController
public class GetAll {
    @Autowired
    StudentImageMapper studentImageMapper;
    @GetMapping("/getall")
    public CommonResult Get(){
        QueryWrapper<StudentImage> wrapperstudentImage = new QueryWrapper<>();
        List<StudentImage> studentImages = studentImageMapper.selectList(null);
        return CommonResult.success(studentImages,"获取所有学生对应的图片成功");
    }
}

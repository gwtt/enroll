package com.example.enroll.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enroll.api.CommonResult;
import com.example.enroll.mapper.StudentImageMapper;
import com.example.enroll.pojo.StudentImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author 滚韬
 * @date 2021.10.5
 */
@RestController
public class Upload {
    @Autowired
    StudentImageMapper studentImageMapper;
    @PostMapping("/upload")
    public CommonResult Save(@RequestBody StudentImage Image){
        QueryWrapper<StudentImage> wrapperstudentImage = new QueryWrapper<>();
        wrapperstudentImage
                .eq("username", Image.getUsername());
        StudentImage studentImage = studentImageMapper.selectOne(wrapperstudentImage);
        if(studentImage==null)
            return CommonResult.success("保存失败,访客不需要保存图片");
        if(studentImage.getImage()!=null)
            return CommonResult.success("已经有图片");
        studentImage.setImage(Image.getImage());
        studentImageMapper.update(studentImage,wrapperstudentImage);
        return CommonResult.success("保存图片成功");
    }
}

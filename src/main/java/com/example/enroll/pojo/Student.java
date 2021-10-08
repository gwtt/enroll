package com.example.enroll.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * @author 滚韬
 * @date 2021.10.3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    String username;
    String specialty;
    int count;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    Date time;
}

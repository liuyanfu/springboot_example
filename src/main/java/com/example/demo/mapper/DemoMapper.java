package com.example.demo.mapper;


import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DemoMapper {

    @Insert("INSERT INTO demo(class, student_name) VALUES(#{classNo}, #{studentName})")
    int insert(@Param("classNo") String classNo, @Param("studentName") String studentName);

    @Select("select * from demo")
    List<Map<String, Object>> getAll();
}

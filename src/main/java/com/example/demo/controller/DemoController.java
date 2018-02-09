package com.example.demo.controller;

import com.example.demo.mapper.DemoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
@Api(value = "Shop")
public class DemoController {

    @Autowired
    DemoMapper demoMapper;

    @ApiOperation(value = "获取helloWorld", notes = "简单SpringMVC请求")
    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<Map<String, Object>> home() {

        List<Map<String, Object>> lst = demoMapper.getAll();

        return  lst;
    }

    @ApiOperation(value = "获得A+B", notes = "根据url的classNo和url的studentName获得请求参数的字符串相加，RestFul风格的请求")
    @ApiImplicitParams({@ApiImplicitParam(name = "classNo", paramType = "path", value = "班级编号", required = true, dataType = "String"), })
    @RequestMapping(value = "/class/{classNo}/to/{studentName}", method = RequestMethod.GET)
    String world(@PathVariable("classNo") String classNo, @PathVariable("studentName") String studentName) {
        return classNo + "  " + studentName;
    }
}

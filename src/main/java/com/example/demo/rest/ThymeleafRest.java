package com.example.demo.rest;

import cn.hutool.core.util.StrUtil;
import com.example.demo.po.pojo.ApiInfo;
import com.example.demo.po.pojo.FromInfo;
import com.example.demo.po.pojo.ServiceInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ThymeleafRest extends BaseCode{

    @GetMapping(value = {"/hello"})
    public ModelAndView index(ModelAndView model){
        return model;
    }
    @PostMapping(value = {"/addInfo"})
    public boolean index(@RequestBody ApiInfo apiInfo){
        executeOneApi(apiInfo);
        System.out.println(apiInfo);
        return true;
    }

    private static List<ServiceInfo> serviceInfoList = new ArrayList<>();
    public static void executeOneApi(ApiInfo info){
        executeFrom("From.java.vm",info.getFromClass(),info.getFromList());
        executeFrom("Vo.java.vm",info.getVoClass(),info.getVoList());
        ServiceInfo serviceInfo = new ServiceInfo(info.getTitle(),info.getMethod());
        serviceInfo.setRequestMode(info.getRequestMode());
        serviceInfo.setIsValid(Integer.valueOf(1).equals(info.getIsValid()));
        serviceInfo.setFromUpperCase(info.getFromClass());
        serviceInfo.setFromLowerCase(StrUtil.lowerFirst(info.getFromClass()));
        serviceInfo.setVo(info.getVoClass());
        serviceInfoList.add(serviceInfo);
        if(Integer.valueOf(1).equals(info.getCommitServiceMethod())){
            executeService("Service.java.vm","TestService", serviceInfoList);
            serviceInfoList.clear();
        }
    }


    public static void executeFrom(String templatePath, String className, List<FromInfo> list){
        String projectPath = System.getProperty("user.dir")+"/src/main/java/com/example/demo";
        File file = new File(projectPath+"/aa/"+className+".java");
        Map<String,Object> map = new ConcurrentHashMap<>();
        map.put("package","com.example.demo.aa");
        map.put("className",className);
        map.put("fields",list);
        outputFile(file,map, "template/"+templatePath,true);
    }

    public static void executeService(String templatePath, String className, List<ServiceInfo> list){
        String projectPath = System.getProperty("user.dir")+"/src/main/java/com/example/demo";
        File file = new File(projectPath+"/aa/"+className+".java");
        Map<String,Object> map = new ConcurrentHashMap<>();
        map.put("package","com.example.demo.aa");
        map.put("className",className);
        map.put("services",list);
        outputFile(file,map, "template/"+templatePath,true);
    }


}

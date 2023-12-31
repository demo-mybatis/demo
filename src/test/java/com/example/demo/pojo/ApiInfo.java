package com.example.demo.pojo;


import lombok.Data;

import java.util.List;

@Data
public class ApiInfo {

    private String fromClass;
    private String voClass;
    private String serviceClass;
    private List<FromInfo> fromList;
    private List<FromInfo> voList;
    private List<ServiceInfo> serviceInfoList;
}

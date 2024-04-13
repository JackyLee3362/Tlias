package com.jacky.pojo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data
public class Operation {
    private Integer id;
    private Integer user;
    private LocalDateTime operateTime;
    private String className;
    private String methodName;
    private String runtimeArgs;
    private String returnValue;
    private Long runtimeTime;
    // ( 123 )
}

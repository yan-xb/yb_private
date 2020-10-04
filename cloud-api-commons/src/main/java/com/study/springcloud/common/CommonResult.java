package com.study.springcloud.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回前台Json封装体
 * @param <T>  通用指定泛型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>{
    private Integer code;
    private String message;
    private T data;

    /** 添加一个data为空的构造方法*/
    public CommonResult(Integer code, String message){
        this(code,message,null);
    }

}

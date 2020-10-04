package com.study.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author asus
 * AllArgsConstructor   全参构造
 * NoArgsConstructor    无参构造
 * Serializable         实现序列化接口   分布式可能会用到
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    /** 数据库表中类型为bigint 所以这里为long类型*/
    private Long id;
    private String serial;
}

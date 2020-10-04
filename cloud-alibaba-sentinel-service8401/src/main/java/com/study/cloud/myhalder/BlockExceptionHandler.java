package com.study.cloud.myhalder;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.springcloud.common.CommonResult;

public class BlockExceptionHandler {
    /**
     *static 关键字必须有 ， 否则不生效
     *  */
    public static CommonResult blockExceptionHandler1(BlockException be)
    {
        return new CommonResult(400,"BlockExceptionHandler1 -----服务不可用！ ");
    }

    public static CommonResult blockExceptionHandler2(BlockException be)
    {
        return new CommonResult(400,"BlockExceptionHandler2 -----服务不可用！ ");
    }
}

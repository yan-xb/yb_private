package com.study.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.cloud.myhalder.BlockExceptionHandler;
import com.study.springcloud.common.CommonResult;
import com.study.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {


    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "deal_byResource")
    public CommonResult byResource()
        {
            return new CommonResult(200,"按资源名称测试限流OK",new Payment(2020L,"serial001"));
        }

    public CommonResult deal_byResource(BlockException be)
        {
            return new CommonResult(400,be.getClass().getCanonicalName()+"\t 服务不可用！");
        }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl()
        {
            return new CommonResult(200,"按byUrl测试限流OK",new Payment(2020L,"serial001"));
        }


    /**
     *globel 为设置的流量监控热点，而非 /golbel
     * */
    @GetMapping("/golbel")
    @SentinelResource(value = "globel",
            blockHandlerClass = BlockExceptionHandler.class,
            blockHandler = "blockExceptionHandler2")
    public CommonResult globel()
    {
        return new CommonResult(200,"按byUrl测试限流OK",new Payment(2020L,"serial001"));
    }

}

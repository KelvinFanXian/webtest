package com.kelvin.webtest.controller;

import com.kelvin.webtest.holder.DeferredResultHolder;
import com.kelvin.webtest.queue.MockQueue;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author Kelvin范显
 * @createDate 2018年01月23日
 */
@RestController
@Slf4j
@RequestMapping("/demo/")
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("/order")
    public DeferredResult<String> order() throws Exception {
        log.info("主线程开始");

        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber,result);
        log.info("主线程返回");
        return result;
    }
}

package com.mouse.sentinel_test.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/pay")
public class PayController {

//    @SentinelResource(value = "HelloWorld",blockHandler = "block", defaultFallback= "default fallback")
    @GetMapping(value = "/helloworld")
    public String pay(
            @RequestParam(name = "a", required = false) Integer a,
            @RequestParam(name = "b", required = false) Integer b
            ){
log.info("a+b");
        try (Entry entry = SphU.entry("HelloWorld")) {
            // Your business logic here.
            System.out.println("hello world");
        } catch (BlockException e) {
            // Handle rejected request.
            e.printStackTrace();
            log.error("block {}", e.getMessage(), e);
        }
        return a+b + "result";
    }

    public String block() {
        return "block";
    }
}

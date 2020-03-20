package com.yada.ssp.msp.svc.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String index(
            @RequestHeader(value = "X-YADA-ORG-ID") String orgId,
            @RequestHeader(value = "X-YADA-USER-ID") String userId
    ) {
        return orgId + ":" + userId;
    }
}

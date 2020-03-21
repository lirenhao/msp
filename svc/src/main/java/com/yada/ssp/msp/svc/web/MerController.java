package com.yada.ssp.msp.svc.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/merInfo")
public class MerController {

    @GetMapping
    public Map<String, String> info(
            @RequestHeader(value = "X-YADA-ORG-ID") String orgId,
            @RequestHeader(value = "X-YADA-USER-ID") String merNo
    ) {
        Map<String, String> info = new HashMap<>();
        info.put("merNo", merNo);
        info.put("merName", "test");
        info.put("status", "00");
        return info;
    }
}

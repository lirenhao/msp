package com.yada.ssp.msp.svc.web;

import com.yada.ssp.msp.svc.model.Res;
import com.yada.ssp.msp.svc.model.ResOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/res_list")
public class ResController {

    private List<String> exPaths = Arrays.asList("/error", "/res_list", "/app");
    private WebApplicationContext webApplicationContext;

    @Autowired
    public ResController(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @GetMapping
    public List<Res> index() {
        List<Res> resList = new ArrayList<>();
        RequestMappingHandlerMapping bean = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        handlerMethods.forEach((requestMappingInfo, handlerMethod) ->
                requestMappingInfo.getPatternsCondition().getPatterns().stream()
                        .filter(uri -> exPaths.indexOf(uri) < 0)
                        .forEach(uri -> resList.add(
                                new Res(uri, requestMappingInfo.getMethodsCondition().getMethods().stream()
                                        .map(method -> ResOps.get(method.name()))
                                        .collect(Collectors.toList()).toArray(new ResOps[]{})
                                ))
                        )
        );
        return resList;
    }
}

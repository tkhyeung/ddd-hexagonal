package com.example.ddd.driving_adapter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
public class AsyncPassAlongContextAndMDCController {

    @GetMapping("/async")
    @ResponseStatus(HttpStatus.OK)
    public String async() {
        log.info("Inside /async");
        log.info("Getting header value {}: {}", "host", getHeaderValue("host"));
        asyncMethod();
        asyncMethodPassingAlongRequestContext();
        return "test";
    }

    //Get header from core service
    private String getHeaderValue(String name) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(requestAttributes)) {
            return requestAttributes.getRequest().getHeader(name);
        } else {
            return null;
        }

    }

    private void asyncMethod() {
        log.info("inside async method not passing Request Context");
        CompletableFuture.runAsync(() ->
                log.info("async testing (not passing Request Context) to get the same request context from other thread {}: {}",
                        "host", getHeaderValue("host")));
    }

    private void asyncMethodPassingAlongRequestContext() {
        log.info("inside async method passing Request Context");
        asyncTrigger(() ->
                log.info("async testing (passing Request Context) to get the same request context from other thread {}: {}",
                        "host", getHeaderValue("host")));
        //REMARK: The request context will be cleared once the response is committed, even though request attribute is not null.
    }

    private void asyncTrigger(Runnable runnable) {
        //for passing along request context and MDC map if useful in another thread
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Map<String, String> map = MDC.getCopyOfContextMap();
        CompletableFuture.runAsync(
                () -> {
                    try {
                        RequestContextHolder.setRequestAttributes(requestAttributes);
                        if (Objects.nonNull(map)) {
                            MDC.setContextMap(map);
                        }
                        runnable.run();
                    } finally {
                        RequestContextHolder.resetRequestAttributes();
                        MDC.clear();
                    }
                }
        );
    }


}

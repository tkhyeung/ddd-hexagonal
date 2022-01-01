package com.example.ddd.config.interceptor;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
public class LoggingInterceptor implements HandlerInterceptor {

    //https://www.tutorialspoint.com/spring_boot/spring_boot_interceptor.htm
    //https://www.baeldung.com/spring-mvc-handlerinterceptor
    //https://medium.com/techno101/servlet-filter-and-handler-interceptor-spring-boot-implementation-b58d397d9dbd


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request instanceof RequestWrapper) {
            log.info("RequestInterceptor::preHandle::body{}", ((RequestWrapper) request).getBody());
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (request instanceof RequestWrapper) {
            log.info("RequestInterceptor::AfterCompletion::RequestRemoteAddr:{},RequestHeader:{},RequestMethod:{},RequestUri:{},RequestBody:{}",
                    request.getRemoteAddr(), getHeaders(request), request.getMethod(), request.getRequestURI(), ((RequestWrapper) request).getBody());
        }

        if (response instanceof ResponseWrapper) {
            log.info("Interceptor::AfterCompletion::responseStatus:{},responseBody:{}",
                    response.getStatus(), ((ResponseWrapper) response).getResponseBody());
        }
    }

    private Map<String, List<String>> getHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(request.getHeaders(h))
                ));
    }

}

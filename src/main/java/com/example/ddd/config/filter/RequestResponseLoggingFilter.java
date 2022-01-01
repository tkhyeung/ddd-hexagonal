package com.example.ddd.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
public class RequestResponseLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Start do Filter");
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
            ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
            try {
                printRequest(requestWrapper);
                printResponse(responseWrapper);
                log.info("called chain.doFilter");
                chain.doFilter(requestWrapper, responseWrapper);
            } finally {
                printRequest(requestWrapper);
                printResponse(responseWrapper);
            }
        } else {
            chain.doFilter(request, response);
        }
        log.info("Complete do Filter");
    }

    private void printRequest(ContentCachingRequestWrapper requestWrapper) {
        ServletServerHttpRequest request = new ServletServerHttpRequest((HttpServletRequest) requestWrapper.getRequest());
        log.info("Request URI::[{}]::{}", request.getMethod() ,request.getURI());
        log.info("Request Headers:: {}", request.getHeaders());
        if (requestWrapper != null && requestWrapper.getContentAsByteArray() != null && requestWrapper.getContentAsByteArray().length > 0) {
            String body = "";
            try {
                body = new String(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
                body = "UnsupportedEncoding:: "+requestWrapper.getCharacterEncoding();
            }
            log.info("Request Body:: {}", body);
        }else{
            log.info("Cannot get Request body");
        }
    }

    private void printResponse(ContentCachingResponseWrapper responseWrapper)
            throws IOException {
        ServletServerHttpResponse response = new ServletServerHttpResponse((HttpServletResponse) responseWrapper.getResponse());
        log.info("Response Headers:: {}", response.getHeaders());
        if (responseWrapper != null && responseWrapper.getContentAsByteArray() != null
                && responseWrapper.getContentAsByteArray().length > 0) {
            String body = "";
            try {
                body = new String(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
                body = "UnsupportedEncoding:: "+responseWrapper.getCharacterEncoding();
            }
            log.info("Response Body:: {}", body);

        } else {
            log.info("Cannot get Response body, status code:{}", responseWrapper.getStatus());
        }
        responseWrapper.copyBodyToResponse();
    }
}

package com.example.ddd.config.interceptor;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class ResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream bos;
    private ServletOutputStream sos;

    public ResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (sos == null) {
            bos = new ByteArrayOutputStream();
            sos = new ServletOutputStream() {
                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setWriteListener(WriteListener listener) {
                    throw new UnsupportedOperationException();
                }

                @Override
                public void write(int b) throws IOException {
                    log.info("write({})", b);
                    bos.write(b);
                }
            };
        }
        return sos;
    }

    public String getResponseBody() throws IOException {
        String body = bos.toString(Charset.defaultCharset());
        bos.reset();
        bos.write(body.getBytes());
        return body;
    }
}

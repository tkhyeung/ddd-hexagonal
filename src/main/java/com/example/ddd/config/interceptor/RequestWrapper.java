package com.example.ddd.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

@Slf4j
public class RequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = IOUtils.toByteArray(super.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        log.info("getInputStream()");
        ServletInputStream servletInputStream = new WrappedServletInputStream(new ByteArrayInputStream(this.body));
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.getInputStream()));
        log.info("getReader()");
        return reader;
    }

    public String getBody() {
        return new String(this.body, Charset.defaultCharset());
    }


    public class WrappedServletInputStream extends ServletInputStream {

        private final InputStream sourceStream;

        private boolean finished = false;


        /**
         * Create a DelegatingServletInputStream for the given source stream.
         *
         * @param sourceStream the source stream (never {@code null})
         */
        public WrappedServletInputStream(InputStream sourceStream) {
            this.sourceStream = sourceStream;
        }

        /**
         * Return the underlying source stream (never {@code null}).
         */
        public final InputStream getSourceStream() {
            return this.sourceStream;
        }


        @Override
        public int read() throws IOException {
            log.info("read()");
            int data = this.sourceStream.read();
            if (data == -1) {
                this.finished = true;
            }
            return data;
        }

        @Override
        public void close() throws IOException {
            super.close();
            this.sourceStream.close();
        }

        @Override
        public boolean isFinished() {
            return this.finished;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            throw new UnsupportedOperationException();
        }

    }
}

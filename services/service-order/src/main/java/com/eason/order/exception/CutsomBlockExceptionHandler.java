package com.eason.order.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.eason.common.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component
public class CutsomBlockExceptionHandler implements BlockExceptionHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       String resourceName, BlockException e) throws Exception {
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = response.getWriter();
        R<String> error = R.error("服务降级处理+"+ e.getClass());
        writer.write(objectMapper.writeValueAsString(error));
        writer.flush();
        writer.close();
    }
}

package com.smart.getway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 请求异常过滤器
 */
public class ThrowExceptionFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(ThrowExceptionFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }


    @Override
    public Object run() throws ZuulException {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            if(context.getThrowable()!=null){
                ZuulException exception = findZuulException(context.getThrowable());
                logger.error("进入系统异常拦截", exception);

                HttpServletResponse response = context.getResponse();
                response.setContentType("application/json; charset=utf8");
                response.setStatus(exception.nStatusCode);
                PrintWriter writer = null;
                try {
                    writer = response.getWriter();
                    writer.print("{code:"+ exception.nStatusCode +",message:\""+ exception.getMessage() +"\"}");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(writer!=null){
                        writer.close();
                    }
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            ReflectionUtils.rethrowRuntimeException(var5);
        }

        return null;
    }

    ZuulException findZuulException(Throwable throwable) {
        if (ZuulRuntimeException.class.isInstance(throwable.getCause())) {
            return (ZuulException)throwable.getCause().getCause();
        } else if (ZuulException.class.isInstance(throwable.getCause())) {
            return (ZuulException)throwable.getCause();
        } else {
            return ZuulException.class.isInstance(throwable) ? (ZuulException)throwable : new ZuulException(throwable, 500, (String)null);
        }
    }


}
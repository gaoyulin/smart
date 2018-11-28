package com.smart.getway.route;

import com.sun.tools.javac.main.Main;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义error错误页面
 * @author zhiguang
 */
@RestController
public class ErrorHandlerController implements ErrorController {
 
    /**
     * 出异常后进入该方法，交由下面的方法处理
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }
 
    @RequestMapping("/error")
    public void error(HttpServletRequest request, HttpServletResponse response){
        //
        /*RequestContext ctx = RequestContext.getCurrentContext();
        ZuulException exception = (ZuulException)ctx.getThrowable();
        return Result.choose(exception.nStatusCode, exception.getMessage());*/
        response.setContentType("text/html;charset=UTF-8");
        Integer status = (Integer)request.getAttribute("javax.servlet.error.status_code");
        //return Result.choose(status, status == 404 ? "访问地址不存在" : "内部服务器错误,正在处理");
        PrintWriter writer = null;
        try {
            //outputStream = response.getOutputStream();
            writer = response.getWriter();
            writer.print( status == 404 ? "访问地址不存在" : "内部服务器错误,正在处理"); //换成这个就好了
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (writer!=null){
                writer.close();
            }
        }
    }
}

package com.zongcc.boot.exception;

import com.zongcc.boot.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chunchengzong on 2017-03-20.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        Result<String> r = new Result<>();
        r.setCode(-100);
        r.setData(e.getMessage());
        r.setURL(req.getRequestURL().toString());
        return r;
    }

    @ExceptionHandler(value = SpringBootException.class)
    @ResponseBody
    public Result<String> jsonErrorHandler(HttpServletRequest req, SpringBootException e) throws Exception {
        Result<String> r = new Result<>();
        r.setCode(-200);
        r.setData(e.getMessage());
        r.setURL(req.getRequestURL().toString());
        return r;
    }

}

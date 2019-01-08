package com.kenny.service.logistics.controller;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.json.JsonBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AdviceController {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonBean errorHandler(Exception ex) {
        JsonBean jsonBean = new JsonBean();
        jsonBean.setSuccess(false);
        jsonBean.setError_code(500);
        jsonBean.setMsg("系统错误，请联系开发人员："+ex.getMessage());
        return jsonBean;
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ErrorCodeException.class)
    public JsonBean myErrorHandler(ErrorCodeException ex) {
        return new JsonBean(ex.getErrorCode());
    }

}

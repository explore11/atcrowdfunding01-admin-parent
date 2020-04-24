package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.util.CrowdConstants;
import com.atguigu.crowd.util.CrowdUtils;
import com.atguigu.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ControllerAdvice  表示当前类 是一个机遇注解的异常处理类
 */
@ControllerAdvice
public class CrowdExceptionResolver {

    /**
     * 将一个具体的异常类型和一个方法进行绑定
     *
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ModelAndView resolverNullPointException(NullPointerException nullPointerException, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String viewName = "system-error";
        return commonResolver(nullPointerException, request, response, viewName);
    }


    private ModelAndView commonResolver(Exception exception, HttpServletRequest request, HttpServletResponse response, String viewName) throws IOException {
        //判断请求类型是不是ajax
        boolean judgeRequestType = CrowdUtils.judgeRequestType(request);
        if (judgeRequestType) {  //为true  则是ajax请求
            ResultEntity<Object> resultEntity = ResultEntity.failWithOutData(exception.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);
            response.getWriter().write(json);
            return null;
        } else {  // false  普通请求
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("", exception);
            modelAndView.setViewName(viewName);
            return modelAndView;
        }
    }
}

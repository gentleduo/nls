package org.duo.nls.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 统一系统异常处理类
 */
@Controller
public class SystemErrorHandler extends BasicErrorController {

    @Autowired
    public SystemErrorHandler(ErrorAttributes errorAttributes,
                              ServerProperties serverProperties,
                              List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {

//        Map<String, Object> body = getErrorAttributes(request,getErrorAttributeOptions(request,MediaType.ALL));
        Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.APPLICATION_JSON));
        HttpStatus status = getStatus(request);
        if (status.value() == HttpStatus.NOT_FOUND.value()) {
            body.put("message", SystemErrorMsg.NOT_FOUND_ERROR.getDesc());
        } else if (status.value() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            body.put("message", SystemErrorMsg.INTERNAL_SERVER_ERROR.getDesc());
        } else {
            body.put("message", SystemErrorMsg.UNEXPECTED_ERROR.getDesc());
        }
        return new ResponseEntity<>(body, status);
    }
}

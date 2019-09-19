package com.yqh.exception.handler;

import com.yqh.dto.ResultDto;
import com.yqh.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangq
 * Create time in 2019-09-19 09:44
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResultDto exception(Exception ex, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (ex instanceof BaseException) {
            log.error("发送异常：", ex);
            return baseExceptionInternal(HttpStatus.INTERNAL_SERVER_ERROR, ex, request, response);
        } else {
            throw ex;
        }
    }

    private ResultDto baseExceptionInternal(HttpStatus badRequest, Exception ex, HttpServletRequest req, HttpServletResponse res) {
        res.setStatus(badRequest.value());
        ResultDto result = new ResultDto();
        result.setCode(badRequest.value());
        result.setMessage(ex.getMessage());
        result.setSuccess(false);
        return result;
    }

    @ExceptionHandler(value = Exception.class)
    public ResultDto otherException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        log.error("发送异常：", ex);
        HttpStatus status;
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            status = HttpStatus.METHOD_NOT_ALLOWED;
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            status = HttpStatus.NOT_ACCEPTABLE;
        } else if (ex instanceof MissingPathVariableException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof MissingServletRequestParameterException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof ServletRequestBindingException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof ConversionNotSupportedException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof TypeMismatchException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof HttpMessageNotReadableException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof HttpMessageNotWritableException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof MethodArgumentNotValidException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof MissingServletRequestPartException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof BindException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof NoHandlerFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof AsyncRequestTimeoutException) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
        } else {
            status = HttpStatus.SERVICE_UNAVAILABLE;
        }
        return baseExceptionInternal(status, ex, request, response);
    }
}

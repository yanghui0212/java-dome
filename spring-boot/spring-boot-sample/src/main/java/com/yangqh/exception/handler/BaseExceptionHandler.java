package com.yangqh.exception.handler;

import com.yangqh.common.dto.BaseResponseDto;
import com.yangqh.exception.Base400Exception;
import com.yangqh.exception.BaseException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
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
 * Create time in 2018/07/10 09:33
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public BaseResponseDto exception(Exception ex, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (ex instanceof Base400Exception) {
            return baseExceptionInternal(HttpStatus.BAD_REQUEST, ex, request,response);
        } else if (ex instanceof BaseException) {
            return baseExceptionInternal(HttpStatus.INTERNAL_SERVER_ERROR, ex, request, response);
        } else {
            throw ex;
        }
    }

    private BaseResponseDto baseExceptionInternal(HttpStatus badRequest, Exception ex, HttpServletRequest req, HttpServletResponse res) {
        res.setStatus(badRequest.value());
        BaseResponseDto response = new BaseResponseDto();
        response.setCode(badRequest.value());
        response.setMessage(ex.getMessage());
        response.setPath(req.getRequestURL().toString());
        response.setSuccess(false);
        return response;
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponseDto otherException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
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

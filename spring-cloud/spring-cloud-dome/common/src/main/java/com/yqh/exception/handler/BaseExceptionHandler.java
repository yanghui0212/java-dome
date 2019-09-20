package com.yqh.exception.handler;

import com.yqh.dto.base.ResultDto;
import com.yqh.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author yangq
 * Create time in 2019-09-19 09:44
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResultDto exception(Exception ex, HttpServletResponse response) throws Exception {
        if (ex instanceof BaseException) {
            log.error("发生异常：", ex);
            return baseExceptionInternal(HttpStatus.INTERNAL_SERVER_ERROR, ex, response);
        } else {
            throw ex;
        }
    }

    @ExceptionHandler(value = Exception.class)
    public ResultDto otherException(Exception ex, HttpServletResponse response) {
        log.error("发生异常：", ex);
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
        return baseExceptionInternal(status, ex, response);
    }

    /**
     * 用来处理bean validation异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultDto resolveConstraintViolationException(ConstraintViolationException ex, HttpServletResponse response) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMsg;
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ConstraintViolation constraintViolation : constraintViolations) {
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            errorMsg = msgBuilder.toString();
        } else {
            errorMsg = ex.getMessage();
        }
        return baseExceptionInternal(status, errorMsg, response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultDto resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletResponse response) {
        String errorMsg;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            errorMsg = msgBuilder.toString();
        } else {
            errorMsg = ex.getMessage();
        }
        return baseExceptionInternal(status, errorMsg, response);
    }

    private ResultDto baseExceptionInternal(HttpStatus badRequest, Exception ex, HttpServletResponse res) {
        res.setStatus(badRequest.value());
        ResultDto result = new ResultDto();
        result.setCode(badRequest.value());
        result.setMessage(ex.getMessage());
        result.setSuccess(false);
        return result;
    }

    private ResultDto baseExceptionInternal(HttpStatus badRequest, String message, HttpServletResponse res) {
        res.setStatus(badRequest.value());
        ResultDto result = new ResultDto();
        result.setCode(badRequest.value());
        result.setMessage(message);
        result.setSuccess(false);
        return result;
    }

}

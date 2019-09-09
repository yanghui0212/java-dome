package com.yqh.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author yangq
 * Create time in 2019-09-09 16:04
 */
@Component
@Slf4j
public class ZuulPreLogFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if (false) {
            log.warn("access token is empty");
            //令zuul过滤该请求，不对其进行路由
            context.setSendZuulResponse(false);
            //设置返回的错误码
            context.setResponseStatusCode(500);
        }
        String requestUri = request.getRequestURI();
        log.info("请求的URI：{}", requestUri);
        return null;
    }
}

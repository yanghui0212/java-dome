package com.yqh.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author yangq
 * Create time in 2019-09-09 16:32
 */
@Component
@Slf4j
public class RateLimitZuulFilter extends ZuulFilter {

    private final RateLimiter rateLimiter = RateLimiter.create(1000.0);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        if (!rateLimiter.tryAcquire()) {
            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            // 过滤该请求，不对其进行路由
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(500);
            try {
                response.getWriter().write("TOO MANY REQUESTS");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            log.info("OK !!!");
        }
        return null;
    }
}

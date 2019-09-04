package com.yqh.springcloud.config.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yangq
 * Create time in 2018-07-28 14:58
 * pre：路由之前
 * routing：路由之时
 * post： 路由之后
 * error：发送错误调用
 *
 *
 * filterOrder：通过int值来定义过滤器的执行顺序，数值越小优先级越高。
 * shouldFilter：返回一个boolean类型来判断该过滤器是否要执行。我们可以通过此方法来指定过滤器的有效范围。
 * run：过滤器的具体逻辑。包括查sql，nosql去判断该请求到底有没有权限访问。
 */
@Component
@Slf4j
public class ZuulLoginFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}

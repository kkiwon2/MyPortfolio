package com.myportfolio.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

// 필터를 적용할 요청의 패턴 지정 - 모든 요청에 필터를 적용.
@WebFilter(urlPatterns="/*")
public class PerformanceFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("PerformanceFilter 초기화");
        // 초기화 작업
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 1. 전처리 작업
        long startTime = System.currentTimeMillis();

        // 2. 서블릿 또는 다음 필터를 호출
        chain.doFilter(request, response);

        // 3. 후처리 작업
        HttpServletRequest req = (HttpServletRequest) request;
        String  referer = req.getHeader("referer"); //어디서 요청(from)을 했는지 알 수 있다. index의 경우 -> http://localhost/web/
        String method = req.getMethod();

        //어디서 요청(from)을 보냈는지 알수 있으며, 전송방식(get,post)과 어디로 요청(to)을 했는지 출력
        System.out.print("["+referer+"] --> " + method + "[" + req.getRequestURI() +"]");
        System.out.println(" 요청 소요시간 = "+(System.currentTimeMillis()-startTime)+"ms");
    }

    @Override
    public void destroy() {
        // 정리 작업
        System.out.println("PerformanceFilter 종료");
    }

}
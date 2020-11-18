package tce.cmm;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : AppscanFilter.java
 * @Description : 클래스 설명을 기술합니다.
 * @author HSW
 * @since 2020年3月4日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2020年3月4日     HSW     	최초 생성
 * </pre>
 */

public class AppscanFilter implements Filter {

    /*
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig paramFilterConfig) throws ServletException {
        
    }

    /*
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        //获得在下面代码中要用的request,response,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();
        
        // 获得用户请求的URI
        String path = servletRequest.getRequestURI();
        System.out.println(path);
        
        //判断是否为不合法页面
        boolean result = path.endsWith(".do");
        if(result){
        }else{
         // 跳转到登陆页面
            servletResponse.sendRedirect("webapp/WEB-INF/jsp/com/loginForm.jsp");
        }
    }

    /*
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        
    }
    
    
    


}

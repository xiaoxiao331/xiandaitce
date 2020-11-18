package tce.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : HTMLTagFilter.java
 * @Description : 클래스 설명을 기술합니다.
 * @author 9312952
 * @since 2019. 11. 13.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 11. 13.     9312952       최초 생성
 * </pre>
 */

public class HTMLTagFilter implements Filter {

    private static Map<String, String> excludeUrls = new HashMap<String, String>();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        if (excludeUrls.containsKey(uri)) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(new HTMLTagFilterRequestWrapper((HttpServletRequest) request), response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        String urls = config.getInitParameter("exclude_urls");
        if (urls != null) {
            StringTokenizer st = new StringTokenizer(urls, ",");
            String url = null;
            while (st.hasMoreElements()) {
                url = ((String) st.nextElement()).trim();
                excludeUrls.put(url, url);
            }
        }
    }

    public void destroy() {
    }
}

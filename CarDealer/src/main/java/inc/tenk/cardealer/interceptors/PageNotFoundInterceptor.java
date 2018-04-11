package inc.tenk.cardealer.interceptors;

import inc.tenk.cardealer.exceptions.PageNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PageNotFoundInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String errorURL = "/error";
        if(request.getRequestURL().toString().endsWith(errorURL)) {
            throw new PageNotFoundException();
        }
        return true;
    }
}

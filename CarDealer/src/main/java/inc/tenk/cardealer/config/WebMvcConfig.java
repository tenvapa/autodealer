package inc.tenk.cardealer.config;


import inc.tenk.cardealer.interceptors.PageNotFoundInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private PageNotFoundInterceptor pageNotFoundInterceptor;

    @Autowired
    public WebMvcConfig(PageNotFoundInterceptor pageNotFoundInterceptor) {
        this.pageNotFoundInterceptor = pageNotFoundInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.pageNotFoundInterceptor);
    }
}


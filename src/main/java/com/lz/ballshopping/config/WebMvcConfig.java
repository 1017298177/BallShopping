package com.lz.ballshopping.config;


import com.lz.ballshopping.interceptor.RequestViewInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RequestViewInterceptor requestViewInterceptor;
    @Autowired
    private ResourceConfigBean resourceConfigBean;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestViewInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().startsWith("win")) {
            registry.addResourceHandler(resourceConfigBean.getRelativePathPattern())
                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX +
                            resourceConfigBean.getLocationPathForWindows());
        } else {
            registry.addResourceHandler(resourceConfigBean.getRelativePathPattern())
                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX +
                            resourceConfigBean.getLocationPathForLinux());
        }
    }



}

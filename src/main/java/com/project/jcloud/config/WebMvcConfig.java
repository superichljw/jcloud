package com.project.jcloud.config;

import com.project.jcloud.interceptor.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

/*static file source location config*/
    /*오히려 설정하면 제대로 못읽어옴 애당초 정적 파일 호출은 컨텍스트패스를 읽어와서 작업*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/").setCachePeriod(31556926);
//        registry.addResourceHandler("/image/**").addResourceLocations("/resources/image/").setCachePeriod(31556926);
//        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/").setCachePeriod(31556926);
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    // 인터셉터 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor());
    }

}

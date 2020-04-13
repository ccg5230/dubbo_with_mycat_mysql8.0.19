package dubbo.boot.cofig;

/**
 * @className WebConfig
 * @Description
 * @Author chungaochen
 * Date 2020/4/13 11:56
 * Version 1.0
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * WebMvcConfigurerAdapter 已过时 首选WebMvcConfigurer接口，其次WebMvcConfigurationSupport类
 * <p>
 * 查看源码发现: WebMvcConfigurerAdapter只是对WebMvcCofigurer的空实现,而WebMvcConfigurationSupport的实现的方法更全面
 * 但是继承WebMvcConfigurationSupport会发现Spring Boot的WebMvc自动配置失效(WebMvcAutoConfiguration自动化配置)，
 * 导致无法视图解析器无法解析并返回到对应的视图
 *
 * WebMvcConfigurationSupport-->不需要返回逻辑视图,可以选择继承此类
 * WebMvcCofigurer-->返回逻辑视图,可以选择实现此方法,重写addInterceptor方法
 * </p>
 */
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * swagger-ui有long类型丢失精度，末尾被替换成00:本质上是因为long类型在转换中失真了，所以改成String类型的传就没有问题了。
     * spring boot web 组件默认依赖的就是  jackson
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * 序列换成json时,将所有的long变成string
         * 因为js中得数字类型不能包含所有的java long值
         */
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

}


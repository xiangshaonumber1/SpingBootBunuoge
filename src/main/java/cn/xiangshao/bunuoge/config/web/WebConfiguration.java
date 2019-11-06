package cn.xiangshao.bunuoge.config.web;


import cn.xiangshao.bunuoge.Utils.CommonInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.concurrent.Executors;

/**
 * 跨域请求支持
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport{

	static final String origins[] = new String[]{"GET","POST"};

	@Autowired
	CommonInterceptor commonInterceptor;

	/**
	 * 重写此方法以添加SpringMVC拦截器，用于控制器调用的前处理和后处理。
	 */
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonInterceptor);
	}

	/**
	 * 验证此方法以配置跨源请求处理。
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowCredentials(true)
				.allowedHeaders("*")
				.allowedMethods(origins)
				.allowedOrigins("*");
	}

	/**
	 * 重写此方法以配置异步请求处理选项
	 */
	@Override
	protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
		configurer.setDefaultTimeout(30000);
	}

	/**
	 * Spring Boot自动配置本身不会自动把/swagger-ui.html这个路径映射到对应的目录META-INF/resources/下面。我们加上这个映射即可。
	 * 重写此方法以添加为静态资源提供服务的资源处理程序
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		//重新指定静态资源，该路径的下一级文件夹名，应该在ShiroConfig中放行，不然会导致资源无法访问, swagger 也无法访问
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/META-INF/resources/")
				.addResourceLocations("classpath:/resources/")
				.addResourceLocations("classpath:/static/")
				.addResourceLocations("file:C://Projects/myblog/upload_files/");
		super.addResourceHandlers(registry);
	}

}

package com.ssafy.webterview.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.ssafy.webterview.interceptor.JwtInterceptor;

@Configuration
//@EnableAspectJAutoProxy
//@MapperScan(basePackages = "com.ssafy.**.mapper")
public class WebConfig implements WebMvcConfigurer {

	private static final String[] EXCLUDE_PATHS = { "/user/**", "/error/**" };

	@Autowired
	private JwtInterceptor jwtInterceptor;

	// 토큰 사용가능한지 체크하고 불가능하면 중단하는 인터셉터
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")// 기본 적용 경로
//				.excludePathPatterns(EXCLUDE_PATHS);// 적용 제외 경로
////		registry.addInterceptor(jwtInterceptor).addPathPatterns("/user/**", "/article/**", "/memo/**") // 기본 적용 경로
////        .excludePathPatterns(Arrays.asList("/user/confirm/**", "/article/list"));// 적용 제외 경로
//	}

//  Interceptor를 이용해서 처리하므로 전역의 Cross Origin 처리를 해준다.
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		default 설정.
//		Allow all origins.
//		Allow "simple" methods GET, HEAD and POST.
//		Allow all headers.
//		Set max age to 1800 seconds (30 minutes).
		registry.addMapping("/**").allowedOrigins("*")
//			.allowedOrigins("http://localhost:8080", "http://localhost:8081")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//			.allowedHeaders("*")
				.maxAge(6000);
	}

//	Swagger UI 실행시 404처리 ->주석
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/**/*")
//		.addResourceLocations("classpath:/static/")
//		.resourceChain(true)
//		.addResolver(new PathResourceResolver() {
//			@Override
//			protected Resource getResource(String resourcePath,Resource location) throws IOException {
//				Resource requestedResource = location.createRelative(resourcePath);
//				return requestedResource.exists() && requestedResource.isReadable()? requestedResource:new ClassPathResource("/static/index.html");
//			}
//			
//		});
//		registry.addResourceHandler("/swagger-ui/index.html**")
//				.addResourceLocations("classpath:/META-INF/resources/swagger-ui/index.html");
//		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
package com.ssafy.webterview.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.webterview.interceptor.JwtInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private static final String[] EXCLUDE_PATHS = {"/user/**","/error/**","/swagger-resources/**","/swagger-ui/**","/v2/api-docs",
			"/interview/confirm", "/interview/applicant/unique","/interview/applicant/room","/score/eval",
			"/naverapi/sms","/score/save","/admin/decrypt","/interview/applicant/info","/interview/applicant/savefile","/interview/applicant/url/**"};

	private JwtInterceptor jwtInterceptor;
	@Autowired
	public WebConfig(JwtInterceptor jwtInterceptor) {
		this.jwtInterceptor = jwtInterceptor;
	}

	// 토큰 사용가능한지 체크하고 불가능하면 중단하는 인터셉터
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")// 기본 적용 경로
				.excludePathPatterns(EXCLUDE_PATHS);// 적용 제외 경로
	}

//  Interceptor를 이용해서 처리하므로 전역의 Cross Origin 처리를 해준다.
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		default 설정.
//		Allow all origins.
//		Allow "simple" methods GET, HEAD and POST.
//		Allow all headers.
//		Set max age to 1800 seconds (30 minutes).
		registry.addMapping("/**")
//			.allowedOrigins("http://localhost:8080", "http://localhost:8081")
				.allowedOrigins("https://i7c205.p.ssafy.io", "http://i7c205.p.ssafy.io:8081")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
			.allowedHeaders("*")
				.maxAge(6000);
	}


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//vue project static에 넣은 뒤에 할 설정
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
		
		//	Swagger UI 실행시 404처리(개발완료 후 할 것) ->주석
//		registry.addResourceHandler("/swagger-ui/index.html**")
//				.addResourceLocations("classpath:/META-INF/resources/swagger-ui/index.html");
//		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}

package com.demo.version;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @ClassName WebMvcRegistrationsConfig
 * @Description TODO
 * @Author yangguoqing
 * @Date 2019/12/25 9:49 上午
 */
@Configuration
public class WebMvcRegistrationsConfig implements WebMvcRegistrations {
	@Override
	public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
		return new ApiVersionRequestMappingHandlerMapping();
	}
}
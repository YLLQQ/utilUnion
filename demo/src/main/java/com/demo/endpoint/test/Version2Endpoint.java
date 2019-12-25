package com.demo.endpoint.test;

import com.demo.version.ApiVersion;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName VersionEndpoint
 * @Description TODO
 * @Author yangguoqing
 * @Date 2019/12/25 9:40 上午
 */
@RequestMapping("/{version}")
@RestController
@ApiVersion(2)
@Api(tags = "版本测试相关接口", protocols = "http")
public class Version2Endpoint {

	@GetMapping("/test")
	public String version2() {
		return "version2";
	}
}

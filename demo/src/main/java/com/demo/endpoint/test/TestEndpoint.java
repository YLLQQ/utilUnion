package com.demo.endpoint.test;

import com.demo.demo.interactive.TestAddRequest;
import com.demo.demo.interactive.TestAddResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "测试相关接口", protocols = "http")
public class TestEndpoint {

    @ApiOperation("增加")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 401, message = "权限不足")})
    @PostMapping("/test/add")
    public TestAddResponse add(@RequestBody TestAddRequest request) {
        if (log.isInfoEnabled()) {
            log.info("input request is {}", request);
        }

        TestAddResponse testAddResponse = new TestAddResponse();

        testAddResponse.setResult(true);

        return testAddResponse;
    }

    @ApiOperation("获取信息")
    @GetMapping("/test/info/{id}")
    public boolean info(@PathVariable Integer id) {
        if (log.isInfoEnabled()) {
            log.info("input id is {}", id);
        }

        return true;
    }
}

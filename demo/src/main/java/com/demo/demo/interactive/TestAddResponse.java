package com.demo.demo.interactive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class TestAddResponse {

    @ApiModelProperty(name = "result", value = "操作结果：true，成功；false，失败", dataType = "boolean")
    private Boolean result;
}

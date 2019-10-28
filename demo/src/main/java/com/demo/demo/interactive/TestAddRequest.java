package com.demo.demo.interactive;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TestAddRequest {

    @ApiModelProperty(name = "account", value = "账号", dataType = "String", required = true)
    private String account;

    @ApiModelProperty(name = "password", value = "密码", dataType = "String", required = true)
    private String password;

    @ApiModelProperty(name = "insertDate", value = "入库时间", dataType = "java.util.Date", allowEmptyValue = true)
    private Date insertDate;
}

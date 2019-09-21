package com.demo.demo.domain;

import lombok.Data;
import self.yang.mybatis.domain.BaseDO;

import java.util.Date;

/**
 * com.demo.demo.domain.AccountDO
 *
 * @author eleven
 * @date 2019/09/20
 */
@Data
public class AccountDO implements BaseDO {

    private Integer id;

    private String account;

    private String password;

    private Date toDbTime;

}

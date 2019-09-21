package com.demo.demo.service;

import com.demo.demo.domain.AccountDO;
import com.demo.demo.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.yang.mybatis.service.BaseService;

/**
 * com.demo.demo.service.AccountService
 *
 * @author eleven
 * @date 2019/09/21
 */
@Service
public class AccountService extends BaseService<AccountMapper, AccountDO> {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    protected AccountMapper getMapper() {
        return accountMapper;
    }

    @Override
    protected String getTableName() {
        return "account";
    }

}

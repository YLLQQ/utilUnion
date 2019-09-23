package com.demo.demo.service;

import com.demo.demo.domain.AccountDO;
import com.demo.demo.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.yang.mybatis.domain.PageModel;
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

    public AccountDO getAccountById(Integer id) {
        return super.getWithColumnsById(id);
    }

    public PageModel<AccountDO> getAccountWithPage(int page, int size) {
        return super.listByConditionWithPage(null, null, null, null, page, size);
    }

}

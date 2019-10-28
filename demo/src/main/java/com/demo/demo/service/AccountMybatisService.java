package com.demo.demo.service;

import com.demo.demo.domain.AccountDO;
import com.demo.demo.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.yang.model.domain.PageModel;
import self.yang.mybatis.mapper.BaseMapper;
import self.yang.mybatis.service.BaseMybatisService;

/**
 * com.demo.demo.service.AccountMybatisService
 *
 * @author eleven
 * @date 2019/09/21
 */
@Service
public class AccountMybatisService extends BaseMybatisService<AccountDO> {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    protected BaseMapper<AccountDO> getMapper() {
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

package com.demo.demo.service;

import com.demo.demo.domain.AccountDO;
import org.springframework.stereotype.Service;
import self.yang.mybatis.domain.PageModel;
import self.yang.mybatis.service.BaseMybatisService;

/**
 * com.demo.demo.service.AccountMybatisService
 *
 * @author eleven
 * @date 2019/09/21
 */
@Service
public class AccountMybatisService extends BaseMybatisService<AccountDO> {

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

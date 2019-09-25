package com.demo.demo.service;

import com.demo.demo.domain.LogDO;
import org.springframework.stereotype.Service;
import self.yang.mybatis.service.BaseMybatisService;

/**
 * com.demo.demo.service.LogMybatisService
 *
 * @author eleven
 * @date 2019/09/21
 */
@Service
public class LogMybatisService extends BaseMybatisService<LogDO> {

    @Override
    protected String getTableName() {
        return "log";
    }
}

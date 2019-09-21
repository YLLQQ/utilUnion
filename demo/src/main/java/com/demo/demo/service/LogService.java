package com.demo.demo.service;

import com.demo.demo.domain.LogDO;
import com.demo.demo.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.yang.mybatis.service.BaseService;

/**
 * com.demo.demo.service.LogService
 *
 * @author eleven
 * @date 2019/09/21
 */
@Service
public class LogService extends BaseService<LogMapper, LogDO> {

    @Autowired
    private LogMapper logMapper;

    @Override
    protected LogMapper getMapper() {
        return logMapper;
    }

    @Override
    protected String getTableName() {
        return "log";
    }
}

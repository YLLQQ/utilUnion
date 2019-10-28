package com.demo.demo.service;

import com.demo.demo.domain.LogDO;
import com.demo.demo.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.yang.mybatis.mapper.BaseMapper;
import self.yang.mybatis.service.BaseMybatisService;

/**
 * com.demo.demo.service.LogMybatisService
 *
 * @author eleven
 * @date 2019/09/21
 */
@Service
public class LogMybatisService extends BaseMybatisService<LogDO> {

    @Autowired
    private LogMapper logMapper;

    @Override
    protected BaseMapper<LogDO> getMapper() {
        return logMapper;
    }

    @Override
    protected String getTableName() {
        return "log";
    }
}

package com.demo.demo.mapper;

import com.demo.demo.domain.LogDO;
import org.apache.ibatis.annotations.Mapper;
import self.yang.mybatis.mapper.BaseMapper;

/**
 * com.demo.demo.mapper.LogMapper
 *
 * @author eleven
 * @date 2019/09/21
 */
@Mapper
public interface LogMapper extends BaseMapper<LogDO> {
}

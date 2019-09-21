package com.demo.demo.mapper;

import com.demo.demo.domain.AccountDO;
import org.apache.ibatis.annotations.Mapper;
import self.yang.mybatis.mapper.BaseMapper;

/**
 * com.demo.demo.mapper.AccountMapper
 *
 * @author eleven
 * @date 2019/09/20
 */
@Mapper
public interface AccountMapper extends BaseMapper<AccountDO> {

}

package com.itheima.mapper;

import com.itheima.dao.AccountDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    int insert(AccountDO record);

    int insertSelective(AccountDO record);
}
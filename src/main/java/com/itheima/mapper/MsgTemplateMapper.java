package com.itheima.mapper;

import com.itheima.dao.MsgTemplateDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MsgTemplateMapper {
    int deleteByPrimaryKey(Long templateId);

    int insert(MsgTemplateDO record);

    int insertSelective(MsgTemplateDO record);

    MsgTemplateDO selectByPrimaryKey(Long templateId);

    int updateByPrimaryKeySelective(MsgTemplateDO record);

    int updateByPrimaryKey(MsgTemplateDO record);
}
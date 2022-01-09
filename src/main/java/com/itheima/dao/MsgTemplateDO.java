package com.itheima.dao;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
    * 消息模板表
    */
@Data
@Builder
public class MsgTemplateDO implements Serializable {

    /**
     * 模板id
     */
    private Long templateId;

    /**
    * 模板名称
    */
    private String name;

    /**
    * 发布账号id
    */
    private Long accountId;

    /**
    * 发布内容
    */
    private String content;

    /**
    * 消息元id
    */
    private Long msgId;

    /**
    * 预发送时间
    */
    private Date gmtSend;

    /**
    * 状态 0-草稿 1-审批 2-通过审批 3-审批拒绝 4-已撤回 5-删除 6-已发送
    */
    private Integer status;

    private Date gmtCreate;

    private static final long serialVersionUID = 1L;
}
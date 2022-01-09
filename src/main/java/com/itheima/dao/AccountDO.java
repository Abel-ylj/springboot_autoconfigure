package com.itheima.dao;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
    * 账号表
    */
@Data
@Builder
public class AccountDO implements Serializable {

    /**
    * 账号id
    */
    private Long accountId;

    /**
    * 昵称
    */
    private String name;

    /**
    * 账号状态 0-上线 1-下线
    */
    private Integer status;

    private Date gmtCreate;

    private static final long serialVersionUID = 1L;
}
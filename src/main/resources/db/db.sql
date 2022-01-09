CREATE TABLE `tb_account` (
  `account_id` bigint(20) DEFAULT NULL COMMENT '账号id',
  `name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `status` tinyint(4) DEFAULT NULL COMMENT '账号状态 0-上线 1-下线',
  `gmt_create` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账号表'


CREATE TABLE `tb_msg_template` (
  `template_id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '模板名称',
  `account_id` bigint(20) DEFAULT NULL COMMENT '发布账号id',
  `content` text COMMENT '发布内容',
  `msg_id` bigint(20) DEFAULT NULL COMMENT '消息元id',
  `gmt_send` timestamp NULL DEFAULT NULL COMMENT '预发送时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 0-草稿 1-审批 2-通过审批 3-审批拒绝 4-已撤回 5-删除 6-已发送',
  `gmt_create` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息模板表'



-- TABLE 
drop table if exists tb_interface_definition;

create table if not exists tb_interface_definition(
 serial_no varchar(255) not null  comment '物理主键 渠道号:交易码:版本号',
 tx_no varchar(255) not null  comment '交易码',
 version varchar(255) not null  default '1'  comment '版本号',
 channel varchar(255) not null  default 'public'  comment '通道，默认为公共通道',
 interface_desc text null  comment '接口描述',
 interface_type varchar(255) not null  default 'SYNC'  comment '接口类型 SYNC:同步  ASYNC:异步  ',
 interface_direction varchar(255) not null  default 'INNER'  comment '接口方向 INNER:内部调用  OUTER:外部调用  ',
 gateway_url varchar(255) null  comment '网关地址 INNER:内部调用  OUTER:外部调用  ',
 http_timeout_second integer not null  default 0 comment 'HTTP同步通信时的超时时间（秒）',
 service_class_name varchar(255) not null  comment '服务类名',
 method_name varchar(255) not null  comment '方法名',
 encrypt_algorithm varchar(255) not null  default 'AES'  comment '加密算法',
 decrypt_algorithm varchar(255) not null  default 'AES'  comment '解密算法',
 sign_algorithm varchar(255) not null  default 'SHA512'  comment '签字算法',
 verify_algorithm varchar(255) not null  default 'SHA512'  comment '验签算法',
 use_token_as_password tinyint not null  default 1 comment '使用TOKEN作为密码',
 password varchar(255) null  comment '密码',
 first_sign_second_encrypt tinyint not null  default 1 comment '首先签字然后加密',
 first_verify_second_decrypt tinyint not null  default 1 comment '首先校验然后解密',
 validate_token tinyint null  default 1 comment '是否校验TOKEN',
 idempotent_redo tinyint null  default 1 comment '是否允许幂等进入重做，只适用于异步接口',
 write_message tinyint null  default 1 comment '是否记录通讯信息',
 write_mode varchar(255) null  default 'SYNC'  comment '写入模式 SYNC:同步  ASYNC:异步  ',
 key_vector varchar(255) null  default '1234567890654321'  comment '秘钥向量 SYNC:同步  ASYNC:异步  ',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(serial_no)
);
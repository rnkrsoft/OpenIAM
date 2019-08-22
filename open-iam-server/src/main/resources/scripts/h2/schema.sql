-- TABLE 
drop table if exists tb_action_info;

create table if not exists tb_action_info(
 action_id integer not null  auto_increment comment '功能主键',
 action_code varchar(255) null  comment '功能代码',
 action_title varchar(255) null  comment '功能标题',
 product_id varchar(255) null  comment '应用编号',
 action_status integer null  default 1 comment '状态 0:禁用  1:启用  ',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(action_id)
);


-- TABLE 
drop table if exists tb_application_info;

create table if not exists tb_application_info(
 app_id integer not null  auto_increment comment '应用主键',
 app_code varchar(255) null  comment '应用代码',
 app_title varchar(255) null  comment '应用标题',
 app_url varchar(255) null  comment '应用链接地址',
 app_icon varchar(255) null default '/static/icon/application.png'  comment '应用图片地址',
 app_status integer null  default 1 comment '状态 0:禁用  1:启用  ',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(app_id)
);


-- TABLE 
drop table if exists tb_authority_info;

create table if not exists tb_authority_info(
 authority_id integer not null  auto_increment comment '权限主键',
 resource_type integer not null  comment '权限主键 1:Skeleton4j  2:资源  ',
 resource_id integer null  comment '操作主键',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(authority_id)
);


-- TABLE 
drop table if exists tb_branch_info;

create table if not exists tb_branch_info(
 branch_id integer not null  auto_increment comment '分支机构主键',
 branch_name varchar(255) null  comment '分支机构名称',
 city_id integer null  comment '城市主键',
 branch_status integer null  default 1 comment '分支机构状态 0:禁用  1:启用  ',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(branch_id)
);


-- TABLE 
drop table if exists tb_channel_info;

create table if not exists tb_channel_info(
 channel_no varchar(255) not null  comment '渠道编号',
 channel_name varchar(255) not null  comment '渠道名',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(channel_no)
);


-- TABLE 
drop table if exists tb_city_info;

create table if not exists tb_city_info(
 city_id integer not null  auto_increment comment '城市主键',
 city_name varchar(255) not null  comment '城市名称',
 city_status integer null  default 1 comment '状态 0:禁用  1:启用  ',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(city_id)
);


-- TABLE 
drop table if exists tb_department_info;

create table if not exists tb_department_info(
 department_id integer not null  auto_increment comment '部门主键',
 department_name varchar(255) null  comment '部门名称',
 branch_id integer null  comment '分支机构主键',
 department_status integer null  default 1 comment '状态 0:禁用  1:启用  ',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(department_id)
);


-- TABLE 
drop table if exists tb_group_info;

create table if not exists tb_group_info(
 group_id integer not null  auto_increment comment '用户组主键',
 group_name varchar(255) null  comment '用户组名称',
 group_status integer null  default 1 comment '用户组状态 0:禁用  1:启用  ',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(group_id)
);


-- TABLE 
drop table if exists tb_group_role_mapping;

create table if not exists tb_group_role_mapping(
 mapping_id integer not null  auto_increment comment '物理主键',
 group_id integer not null  comment '用户组主键',
 role_id integer null  comment '角色主键',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(mapping_id)
);


-- TABLE 
drop table if exists tb_job_info;

create table if not exists tb_job_info(
 job_id integer not null  auto_increment comment '岗位主键',
 job_name varchar(255) null  comment '岗位名称',
 department_id integer not null  comment '部门编号',
 job_status integer null  default 1 comment '岗位状态 0:禁用  1:启用  ',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(job_id)
);


-- TABLE 
drop table if exists tb_product_info;

create table if not exists tb_product_info(
 product_id integer not null  auto_increment comment '产品主键',
 product_code varchar(255) null  comment '产品代码',
 product_title varchar(255) null  comment '产品标题',
 app_id varchar(255) null  comment '应用编码',
 product_status integer null  default 1 comment '产品状态 0:禁用  1:启用  ',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(product_id)
);


-- TABLE 
drop table if exists tb_role_authority_mapping;

create table if not exists tb_role_authority_mapping(
 mapping_id integer not null  auto_increment comment '物理主键',
 role_id integer not null  comment '角色主键',
 authority_id varchar(255) null  comment '权限主键',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(mapping_id)
);


-- TABLE 
drop table if exists tb_role_info;

create table if not exists tb_role_info(
 role_id integer not null  auto_increment comment '角色主键',
 role_name varchar(255) null  comment '角色名',
 role_status integer null  default 1 comment '角色状态 0:禁用  1:启用  ',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(role_id)
);


-- TABLE 
drop table if exists tb_sub_action_info;

create table if not exists tb_sub_action_info(
 sub_action_id integer not null  auto_increment comment '子功能主键',
 sub_action_code varchar(255) null  comment '子功能代码',
 sub_action_title varchar(255) null  comment '子功能标题',
 action_id varchar(255) null  comment '功能编号',
 sub_action_status integer null  default 1 comment '子功能状态 0:禁用  1:启用  ',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(sub_action_id)
);


-- TABLE 
drop table if exists tb_user_info;

create table if not exists tb_user_info(
 user_id varchar(255) not null  comment '用户号',
 user_name varchar(255) null  comment '用户名',
 nick_name varchar(255) null  comment '昵称',
 user_avatar varchar(255) not null  default '/avatars/default.jpg'  comment '用户头像',
 sex integer null  default -1 comment '性别 -1:未知性别  1:男性  2:女性  ',
 mobile_no varchar(255) null  comment '手机号',
 first_login tinyint null  default 1 comment '是否首次登录',
 latest_department_id integer null  comment '上一次登录的部门',
 user_status integer null  default 1 comment '状态 1:正常  2:禁用  3:已删除  ',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(user_id)
);


-- TABLE 
drop table if exists tb_user_group_mapping;

create table if not exists tb_user_group_mapping(
 mapping_id integer not null  auto_increment comment '物理主键',
 user_id varchar(255) not null  comment '用户号',
 group_id integer not null  comment '用户组编号',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(mapping_id)
);


-- TABLE 
drop table if exists tb_user_job_mapping;

create table if not exists tb_user_job_mapping(
 mapping_id integer not null  auto_increment comment '物理主键',
 user_id varchar(255) not null  comment '用户号',
 job_id integer null  comment '岗位编号',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(mapping_id)
);


-- TABLE 
drop table if exists tb_user_role_mapping;

create table if not exists tb_user_role_mapping(
 mapping_id integer not null  auto_increment comment '物理主键',
 user_id varchar(255) not null  comment '用户号',
 role_id integer not null  comment '角色编号',
 create_date timestamp not null  default current_timestamp comment '创建日期',
 last_update_date timestamp not null  default current_timestamp comment '更新日期',
 primary key(mapping_id)
);



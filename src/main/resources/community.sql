create table if not exists user
(
	id int auto_increment
		primary key,
	name varchar(20) null,
	account_id varchar(30) null,
	token varchar(50) null,
	avatar_url varchar(100) null,
	gmt_create_time bigint null,
	gmt_update_time bigint null
)
comment '用户表';


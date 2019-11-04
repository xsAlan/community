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

create table question
(
	id int auto_increment,
	title varchar(50) null,
	description varchar(256) null,
	tag varchar(50) null,
	creator int null,
	view_count int default 0 null,
	like_count int default 0 null,
	comment_count int default 0 null,
	gmt_create_time bigint null,
	gmt_update_time bigint null,
	constraint question_pk
		primary key (id)
)
comment '问题表';




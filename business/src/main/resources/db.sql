# select 1 from dual;
select * from user;

drop table if exists user;
create table user(
# 账户信息
    id              int primary key auto_increment      comment '用户ID',
    username        varchar(8) unique not null          comment '用户名（登录）',
    nickname        varchar(20)                         comment '昵称',
    password        varchar(20)                         comment '密码',
    phone           varchar(11) unique                  comment '手机号',
    email           varchar(20) unique                  comment '邮箱',
    avatar          varchar(200)                        comment '头像',
    status          char(1)                             comment '账号状态（0正常 1停用）',
    del_flag        char(1)                             comment '删除标志（0代表存在 1代表删除）',
    login_ip        varchar(10)                         comment '最后登录IP',
    login_date      datetime                            comment '最后登录时间',
# 基本信息
    name            varchar(20)                         comment '姓名',
    gender          char(1)                             comment '性别(字典)',
    age             varchar(3)                          comment '年龄',
    birthday        varchar(10)                         comment '生日',
    height          varchar(5)                          comment '身高',
    weight          varchar(5)                          comment '体重',
    marriage        varchar(50)                         comment '婚姻状态（字典）',
    home_city       varchar(10)                         comment '家乡',
    live_city       varchar(10)                         comment '工作居住地',
    introduce       varchar(50)                         comment '自我介绍',
# 工作信息
    company         varchar(50)                         comment '公司',
    job             varchar(50)                         comment '职业（字典）',
    school          varchar(50)                         comment '毕业院校',
    education       varchar(50)                         comment '最高学历（字典）',
# 资产信息
    salary          varchar(50)                         comment '年薪（字典）',
    house           varchar(50)                         comment '有房',
    car             varchar(50)                         comment '有车',

# 更多信息
    remark          varchar(100)                        comment '备注',
    create_time     datetime                            comment '创建时间',
    update_time     datetime                            comment '更新时间'
) comment = '用户表';


drop table if exists dict_type;
create table dict_type
(
    id              int primary key auto_increment      comment 'ID',
    code            varchar(50)                         comment '字典项标识码',
    name            varchar(50)                         comment '字典项类型名称',
    create_time     datetime                            comment '创建时间',
    update_time     datetime                            comment '更新时间'
) comment = '数据字典类型表';

insert into dict_type (id, code, name, create_time, update_time) values (null, 'gender', '性别', '2020-01-01 00:00:00', null);
insert into dict_type (id, code, name, create_time, update_time) values (null, 'marriage', '婚姻状况', '2020-01-01 00:00:00', null);
insert into dict_type (id, code, name, create_time, update_time) values (null, 'education', '最高学历', '2020-01-01 00:00:00', null);
insert into dict_type (id, code, name, create_time, update_time) values (null, 'job', '行业/职业', '2020-01-01 00:00:00', null);
insert into dict_type (id, code, name, create_time, update_time) values (null, 'salary', '年薪', '2020-01-01 00:00:00', null);

create table dict_item
(
    id              int primary key auto_increment      comment 'ID',
    code            varchar(50)                         comment '字典项标识码',
    seq             varchar(50)                         comment '排序',
    name            varchar(50)                         comment '字典项名称',
    create_time     datetime                            comment '创建时间',
    update_time     datetime                            comment '更新时间'
) comment '数据字典子项表';

insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'gender', 1, '男', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'gender', 2, '女', '2020-01-01 00:00:00', null);

insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'marriage', 1, '未婚', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'marriage', 2, '离异', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'marriage', 3, '丧偶', '2020-01-01 00:00:00', null);

insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 1, '程序员', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 2, '公务员', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 3, '行政', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 4, '人事', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 5, '销售', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 6, '财务', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 7, '教师', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 8, '医生', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 9, '自媒体', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 10, '客服', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 11, '运营', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 12, '市场营销', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 13, '物流运输', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 14, '个体户', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 15, '自由职业', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 16, '学生', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'job', 17, '其他', '2020-01-01 00:00:00', null);

insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'education', 1, '大专', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'education', 2, '本科', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'education', 3, '硕士', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'education', 4, '博士', '2020-01-01 00:00:00', null);

insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'salary', 1, '5w以下', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'salary', 2, '5-10w', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'salary', 3, '10-20w', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'salary', 4, '20-30w', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'salary', 4, '30-40w', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'salary', 5, '40-50w', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'salary', 6, '50-70w', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'salary', 7, '70-100w', '2020-01-01 00:00:00', null);
insert into dict_item (id, code, seq, name, create_time, update_time) values (null, 'salary', 8, '100w以上', '2020-01-01 00:00:00', null);




select * from user;
select * from dict_type;
select * from dict_item;
select * from dict_item ORDER BY RAND() LIMIT 1;

select * from dict_type left join dict_item on dict_type.code = dict_item.code where dict_type.name = ''
drop table t_user;

create table t_user(
  user_id varchar(32) not null primary key,
  type char(1),
  user_name varchar(32),
  create_time TIMESTAMP default current timestamp
);

insert into t_user values('1111',0,'张三',current_date );
insert into t_user values('2222',0,'李四',current_date);

select * from t_user;

-- db2分页（fetch。。）
select * from (
select rownumber() over() as rownumber_,
user_id AS userId,
CREATE_TIME AS createTime,
TYPE AS type,
USER_NAME AS userName
FROM T_USER ) as temp_
where rownumber_ fetch first 20 rows only;


-- db2分页（between..and...）
select *
from (select tmp_page.*, rownumber() over () as row_id
      from (SELECT user_id AS userId, CREATE_TIME AS createTime, TYPE AS type, USER_NAME AS userName
            FROM T_USER
            WHERE (user_name LIKE '%ceshi%')) as tmp_page)
where row_id between 1 and 20;
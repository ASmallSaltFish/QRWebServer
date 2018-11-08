select * from t_user;

drop table t_user;

create table t_user(
  user_id varchar(32),
  user_name varchar(32)
);

update t_user set user_name='张三' where user_id='1111';
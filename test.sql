select * from t_user;

drop table t_user;

create table t_user(
  user_id varchar(32),
  user_name varchar(32)
);

insert into t_user values('1111','张三');
insert into t_user values('2222','李四');
drop table if exists property;

create table property
(
    id         bigint       not null auto_increment,
    prop_key   varchar(220) not null unique ,
    prop_value varchar(540) not null,
    primary key (id)
) engine = MyISAM;



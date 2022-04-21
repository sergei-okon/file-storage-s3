drop table if exists files;

create table files
(
    id        bigint        not null auto_increment,
    file_name varchar(100)  not null,
    location  varchar(1020) not null,
    bucket    varchar(255),
    primary key (id)
) engine = MyISAM;



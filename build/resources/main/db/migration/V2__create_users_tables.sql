drop table if exists users;

create table users
(
    id       bigint      not null auto_increment,
    name     varchar(45) not null,
    email    VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    role     enum ( 'ADMIN', 'MODERATOR', 'USER'),
    primary key (id)
) engine = MyISAM;
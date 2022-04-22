drop table if exists events;

create table events
(
    id        bigint      not null auto_increment,
    created   datetime,
    updated   datetime,
    file_id   bigint      NOT NULL,
    user_id   bigint      NOT NULL,
    operation VARCHAR(45) NOT NULL,
    primary key (id)
) engine = MyISAM;


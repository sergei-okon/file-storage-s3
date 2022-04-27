drop table if exists events;

create table events
(
    id        bigint      not null auto_increment,
    created   datetime,
    file_id   bigint      NOT NULL,
    user_id   bigint      NOT NULL,
    operation VARCHAR(45) NOT NULL,
    primary key (id),
    constraint foreign key (file_id) references files (id),
    constraint foreign key (user_id) references users (id)
) engine = MyISAM;


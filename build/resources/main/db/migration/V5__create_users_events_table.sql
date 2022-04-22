create table users_events
(
    user_id   bigint not null,
    events_id bigint not null
) engine = MyISAM;

alter table users_events
    add constraint unique (events_id);

alter table users_events
    add constraint
        foreign key (events_id)
            references events (id);

alter table users_events
    add constraint
        foreign key (User_id)
            references users (id)
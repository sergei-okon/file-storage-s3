alter table events
    add constraint
        foreign key (file_id)
            references files (id);

alter table events
    add constraint
        foreign key (user_id)
            references users (id);




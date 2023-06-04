use wordcloud;

drop table if exists word_count;
drop table if exists work_order;

create table work_order (
    id bigint auto_increment primary key,
    uuid uuid not null,
    status text not null,
    created_at timestamp not null,
    updated_at timestamp default null,
    processing_started_at timestamp default null,
    processing_finished_at timestamp default null,
    failed_at timestamp default null,
    processing_error text default null
);

create table word_count (
    id bigint auto_increment primary key,
    work_order_id bigint not null,
    uuid uuid not null,
    word text not null,
    count int not null,
    created_at timestamp not null,
    constraint fk_word_count_work_order
        foreign key (work_order_id) references work_order(id)
        on delete cascade
        on update cascade
);
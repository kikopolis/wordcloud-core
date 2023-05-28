use wordcloud;

drop table if exists texts;
drop table if exists words;

create table texts (
    id bigint auto_increment primary key,
    uuid text not null,
    content text not null,
    status enum('PENDING', 'PROCESSING', 'PROCESSED') not null default 'PENDING',
    created_at timestamp not null
);

create table words (
    id bigint auto_increment primary key,
    uuid text not null,
    text_id bigint not null,
    word text not null,
    count int not null,
    created_at timestamp not null,
    foreign key (text_id) references texts(id)
);
create table if not exists question_status
(
    id     bigint not null
        primary key,
    status varchar(255)
);

alter table question_status
    owner to postgres;

insert into question_status values (1 ,'Нет ответа');
insert into question_status values (2 ,'Ответ получен');

create table if not exists intensity
(
    id   bigint not null
        primary key,
    type varchar(255)
);

alter table intensity
    owner to postgres;


insert into intensity values (4 ,'Низкая');
insert into intensity values (5 ,'Средняя');
insert into intensity values (6 ,'Высокая');

create table if not exists logs_type
(
    id   bigint not null
        primary key,
    type varchar(255)
);

alter table logs_type
    owner to postgres;

insert into logs_type values (7 ,'Эмоциональное состояние');
insert into logs_type values (8 ,'Спортивная активность');
insert into logs_type values (9 ,'Приём пищи');

create table if not exists meal
(
    id   bigint not null
        primary key,
    type varchar(255)
);

alter table meal
    owner to postgres;


insert into meal values (10 ,'Завтрак');
insert into meal values (11 ,'Обед');
insert into meal values (12 ,'Ужин');
insert into meal values (13 ,'Другое');

create table if not exists notification_status
(
    id     bigint not null
        primary key,
    status varchar(255)
);

alter table notification_status
    owner to postgres;



insert into notification_status values (14 ,'Нет ответа');
insert into notification_status values (15 ,'Ответ просмотрен');
insert into notification_status values (16 ,'Новый ответ');

create table if not exists notification_type
(
    id   bigint not null
        primary key,
    type varchar(255)
);

alter table notification_type
    owner to postgres;

insert into notification_type values (17 ,'Добавление в отслеживание');
insert into notification_type values (18 ,'Удаление из отслеживания');
insert into notification_type values (19 ,'Вопрос');
insert into notification_type values (20 ,'Новая запись участника');
insert into notification_type values (21 ,'Ответ ментора на запись');
insert into notification_type values (22 ,'Ответ ментора на вопрос');
insert into notification_type values (23 ,'Отказ в отслеживании');


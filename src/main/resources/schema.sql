create sequence if not exists hibernate_sequence start with 1 increment by 1;

create table if not exists tv_series
(
    id         int primary key auto_increment,
    series_name varchar(255),
    overview   varchar(255),
    banner     varchar(255),
);

insert into tv_series
values (1, 'test series', 'test overview 1', 'test banner 1');
insert into tv_series
values (2, 'test series', 'test overview 2', 'test banner 2');
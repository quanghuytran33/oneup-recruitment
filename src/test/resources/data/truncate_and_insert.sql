truncate table tv_series;
insert into tv_series
values (1, 'test series', 'test overview 1', 'test banner 1');
COMMIT;
insert into tv_series
values (2, 'test series', 'test overview 2', 'test banner 2');
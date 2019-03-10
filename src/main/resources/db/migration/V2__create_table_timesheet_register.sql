create table timesheet_register (
    id int8 not null,
    type varchar(2) not null,
    time_in timestamp without time zone,
    lunch_start timestamp without time zone,
    lunch_end timestamp without time zone,
    time_out timestamp without time zone,
    hours_worked time without time zone default '00:00:00',
    primary key (id)
);

alter table timesheet_register
drop constraint if exists UK_r43af9ap4edm43mmtq01oddj6;

create sequence seq_timesheet_register start 1 increment 1;
DROP DATABASE IF EXISTS dependency;

CREATE DATABASE dependency;

USE dependency;

drop table if exists account;

create table account(
id int(11),
balance double,
primary key (id)
);

drop table if exists creditcard;

create table creditcard (
id int(11),
account_id int(11),
last_used datetime,
pin_code varchar(45),
wrong_pin_code_attempts int(11),
blocked boolean,
primary key (id),
foreign key (account_id) references account(id)
);

insert into `account`(`id`, `balance`) VALUES

('1', '222222.33'),

('3', '51324.11'),

('2', '13644.52'),

('4', '43456789765.3'),

('5', '0.00');

insert into `creditcard`(`id`, `account_id`, `last_used`, `pin_code`, `wrong_pin_code_attempts`, `blocked`) VALUES

('1', '3', '1996-1-2', '1234', '0', '0'),

('2', '1', '1-1-1', '4321', '100', '1'),

('3', '5', '2000-1-30', '1111', '0', '0'),

('4', '4', '2015-1-1', '2222', '0', '1'),

('5', '2', '2002-4-30', '0987', '5', '0');
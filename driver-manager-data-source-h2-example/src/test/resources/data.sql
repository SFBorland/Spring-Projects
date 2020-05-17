DROP TABLE IF EXISTS person;

create table person
(
    id        bigint      not null,
    firstName varchar(25) not null,
    lastName  varchar(25) not null,
    dob       date        not null,
    primary key (id)
);

insert into person
values (1001, 'Sean', 'Borland', '1984-02-13'),
       (1002, 'Brandon', 'Borland', '2017-05-29'),
       (1003, 'Eliana', 'Borland', '2019-08-19');

DROP TABLE IF EXISTS player;

CREATE TABLE player
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    last_name  VARCHAR(250) NOT NULL,
    first_name VARCHAR(250) NOT NULL,
    position   CHAR(3)      NOT NULL,
    number     INT(2)       NOT NULL
);

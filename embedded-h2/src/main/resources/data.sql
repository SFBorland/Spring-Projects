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
       (1002, 'Brandon', 'Borland', '2017-05-29');

DROP TABLE IF EXISTS billionaires;

CREATE TABLE billionaires (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              first_name VARCHAR(250) NOT NULL,
                              last_name VARCHAR(250) NOT NULL,
                              career VARCHAR(250) DEFAULT NULL
);

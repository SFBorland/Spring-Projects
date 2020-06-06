--Create Table

CREATE DATABASE IF NOT EXISTS `Ecommerce-mvc` DEFAULT CHARACTER SET utf8;

USE `Ecommerce-mvn`;

CREATE TABLE IF NOT EXISTS `Customer` (
`uuid` BINARY(16) NOT NULL,
`firstName` VARCHAR(25) NOT NULL,
`lastName` VARCHAR(25) NOT NULL,
`gender` CHAR(4) NOT NULL,
`dateOfBirth` DATE NOT NULL,
`email` VARCHAR(255) NOT NULL,
PRIMARY KEY(`uuid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `Address` (
`uuid` BINARY(16) NOT NULL PRIMARY KEY,
`Street` VARCHAR(255) NOT NULL,
`City` CHAR(25),
`State` CHAR(2),
`zipCode` INT(5),
FOREIGN KEY(`uuid`)
REFERENCES Customer(uuid)
);

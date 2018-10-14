DROP DATABASE IF EXISTS `contactsdb`;
CREATE DATABASE `contactsdb`;

DROP TABLE IF EXISTS `contactsdb`.`contacts`;
CREATE TABLE `contactsdb`.`contacts` (
  `contact_id` INT NOT NULL AUTO_INCREMENT,
  `userImage` VARCHAR(1024),
  `fname` VARCHAR(100) NOT NULL,
  `lname` VARCHAR(100) NOT NULL,
  `birthdate` DATE NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(12) NULL,
  PRIMARY KEY (`contact_id`));


INSERT INTO `contactsdb`.`contacts`
(`contact_id`,
`userImage`,
`fname`,
`lname`,
`birthdate`,
`address`,
`phone`)
VALUES
(NULL,
NULL,
'Nathaniel',
'Fisher',
'1992-12-26',
'123 King Street',
'705-555-5555'),
(NULL,
NULL,
'Charles',
'Babbage',
'1791-12-26',
'44 Crosby Row, London',
'647-555-5555'),
(NULL,
NULL,
'Ada',
'Lovelace',
'1815-12-10',
'East Horsley Towers, Surrey',
'905-555-5555');
use contactsdb;
select * from contacts;
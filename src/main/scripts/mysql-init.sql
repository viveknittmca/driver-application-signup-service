DROP DATABASE IF EXISTS registration_service;
DROP USER IF EXISTS `registration_service`@`%`;
CREATE DATABASE IF NOT EXISTS registration_service CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `registration_service`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `registration_service`.* TO `registration_service`@`%`;

FLUSH PRIVILEGES;
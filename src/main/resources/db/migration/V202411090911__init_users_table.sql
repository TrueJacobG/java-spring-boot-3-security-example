CREATE TABLE `users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `salt` VARCHAR(255) NOT NULL,
    `role` VARCHAR(15) NOT NULL DEFAULT ('USER'),
    `version` int(6) NOT NULL,
    `created_at` datetime NOT NULL,
    `updated_at` datetime NOT NULL,
    PRIMARY KEY(`id`),
    CONSTRAINT chk_role CHECK (`role` IN ('USER', 'ADMIN'))
);
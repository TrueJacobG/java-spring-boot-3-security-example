CREATE TABLE `tokens` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `value` VARCHAR(255) NOT NULL,
    `expire_date` DATETIME NOT NULL,
    `removed` BOOLEAN NOT NULL DEFAULT (false),
    `version` int(6) NOT NULL,
    `created_at` datetime NOT NULL,
    `updated_at` datetime NOT NULL,
    PRIMARY KEY(`id`)
    );
ALTER TABLE `tokens` ADD CONSTRAINT `fk_user_id` FOREIGN KEY (user_id) REFERENCES users(id);
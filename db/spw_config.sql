DROP DATABASE if exists `spw_config`;

CREATE DATABASE `spw_config` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

use spw_config;

-- spw_config.spw_common_config definition

CREATE TABLE `spw_common_config`
(
    `variable` varchar(150)   NOT NULL,
    `value`    varchar(500)   NOT NULL,
    `active`   enum ('Y','N') NOT NULL,
    PRIMARY KEY (`variable`, `value`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;


-- spw_config.spw_instance_config definition

CREATE TABLE `spw_instance_config`
(
    `instance` varchar(150)   NOT NULL,
    `process`  varchar(150)   NOT NULL,
    `variable` varchar(150)   NOT NULL,
    `value`    varchar(500)   NOT NULL,
    `active`   enum ('Y','N') NOT NULL,
    PRIMARY KEY (`instance`, `process`, `variable`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;


-- spw_config.spw_process_config definition

CREATE TABLE `spw_process_config`
(
    `process`  varchar(150)   NOT NULL,
    `variable` varchar(150)   NOT NULL,
    `value`    varchar(500)   NOT NULL,
    `active`   enum ('Y','N') NOT NULL,
    PRIMARY KEY (`process`, `variable`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;


-- spw_config.spw_resource_config definition

CREATE TABLE `spw_resource_config`
(
    `config_type`  varchar(50)         DEFAULT NULL,
    `config_name`  varchar(50)    NOT NULL,
    `auth_info`    varchar(1000)       DEFAULT NULL,
    `resource_url` varchar(1000)  NOT NULL,
    `active`       enum ('Y','N') NOT NULL,
    `created_date` timestamp      NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    `user_name`    varchar(50)    NOT NULL,
    `password`     varchar(50)    NOT NULL,
    `driver_class` varchar(100)   NOT NULL,
    PRIMARY KEY (`config_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;

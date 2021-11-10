DROP DATABASE if exists `spw_audit`;

CREATE DATABASE `spw_audit` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

use spw_audit;

-- `batch_audit` definition

CREATE TABLE `batch_audit`
(
    `batch_id`       bigint(20)  NOT NULL AUTO_INCREMENT,
    `instance_id`    bigint(20)  NOT NULL,
    `statement_id`   bigint(20)  NOT NULL,
    `command_name`   varchar(50) NOT NULL,
    `rows_processed` int(11) DEFAULT NULL,
    `time_taken`     float       NOT NULL,
    PRIMARY KEY (`batch_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;


-- `command_audit` definition

CREATE TABLE `command_audit`
(
    `command_id`     bigint(20)   NOT NULL,
    `instance_id`    bigint(20)   NOT NULL,
    `start`          timestamp    NULL DEFAULT current_timestamp(),
    `end`            datetime          DEFAULT NULL,
    `action_name`    varchar(150) NOT NULL,
    `process_name`   varchar(150) NOT NULL,
    `command_config` text              DEFAULT NULL,
    `status`         smallint(6)  NOT NULL,
    PRIMARY KEY (`command_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;


-- `instance_audit` definition

CREATE TABLE `instance_audit`
(
    `instance_id`   bigint(20)   NOT NULL,
    `instance_name` varchar(150) NOT NULL,
    `mode`          varchar(100) NOT NULL,
    `start`         timestamp    NULL DEFAULT current_timestamp(),
    `end`           datetime          DEFAULT NULL,
    `hostname`      varchar(50)       DEFAULT NULL,
    `file`          varchar(500)      DEFAULT NULL,
    `status`        smallint(6)  NOT NULL,
    `context_log`   text              DEFAULT NULL,
    `root_ref_id`   int(11)           DEFAULT NULL,
    PRIMARY KEY (`instance_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;


-- `statement_audit` definition

CREATE TABLE `statement_audit`
(
    `statement_id`     bigint(20)  NOT NULL,
    `action_id`        bigint(20)  NOT NULL,
    `action_name`      varchar(50) NOT NULL,
    `process_name`     varchar(50) NOT NULL,
    `start`            timestamp   NULL DEFAULT current_timestamp(),
    `end`              datetime         DEFAULT NULL,
    `rows_written`     int(11)          DEFAULT NULL,
    `statement_config` text             DEFAULT NULL,
    `rows_read`        int(11)          DEFAULT NULL,
    `status`           smallint(6) NOT NULL,
    PRIMARY KEY (`statement_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;

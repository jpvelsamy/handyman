DROP TABLE if exists action_audit;
DROP TABLE if exists action_execution_audit;
DROP TABLE if exists config_entity;
DROP TABLE if exists pipeline_audit;
DROP TABLE if exists pipeline_execution_audit;
DROP TABLE if exists resource_connection;
DROP TABLE if exists statement_audit;

-- handyman_vm.`action` definition

CREATE TABLE `action`
(
    `action_id`            bigint(20) NOT NULL,
    `created_by`           int(11)    NOT NULL,
    `created_date`         timestamp  NOT NULL                                DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    `last_modified_by`     int(11)    NOT NULL,
    `last_modified_date`   timestamp  NOT NULL                                DEFAULT '0000-00-00 00:00:00',
    `context_node`         longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`context_node`)),
    `execution_status_id`  int(11)                                            DEFAULT NULL,
    `lambda_name`          varchar(255)                                       DEFAULT NULL,
    `parent_pipeline_id`   int(11)                                            DEFAULT NULL,
    `parent_pipeline_name` varchar(255)                                       DEFAULT NULL,
    `parent_action_id`     int(11)                                            DEFAULT NULL,
    `parent_action_name`   varchar(255)                                       DEFAULT NULL,
    `pipeline_name`        varchar(255)                                       DEFAULT NULL,
    `action_name`          varchar(255)                                       DEFAULT NULL,
    `input_node`           longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`input_node`)),
    `pipeline_id`          bigint(20)                                         DEFAULT NULL,
    `rows_processed`       int(11)                                            DEFAULT NULL,
    `rows_read`            int(11)                                            DEFAULT NULL,
    `rows_written`         int(11)                                            DEFAULT NULL,
    `time_taken`           double                                             DEFAULT NULL,
    `log`                  text                                               DEFAULT NULL,
    `execution_group_id`   int(11)                                            DEFAULT NULL,
    PRIMARY KEY (`action_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_vm.action_execution_audit definition

CREATE TABLE `action_execution_audit`
(
    `id`                  bigint(20)  NOT NULL AUTO_INCREMENT,
    `action_id`           bigint(20)  NOT NULL,
    `created_by`          bigint(20)  NOT NULL,
    `created_date`        datetime(6) NOT NULL,
    `last_modified_by`    bigint(20)  NOT NULL,
    `last_modified_date`  datetime(6) NOT NULL,
    `execution_status_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_vm.config_entity definition

CREATE TABLE `config_entity`
(
    `id`                 bigint(20)   NOT NULL,
    `config_type_id`     int(11)      NOT NULL,
    `name`               varchar(255) NOT NULL,
    `variable`           varchar(255) NOT NULL,
    `active`             bit(1)       NOT NULL,
    `created_by`         bigint(20)   NOT NULL,
    `created_date`       datetime(6)  NOT NULL,
    `last_modified_by`   bigint(20)   NOT NULL,
    `last_modified_date` datetime(6)  NOT NULL,
    `value`              varchar(255) NOT NULL,
    UNIQUE KEY `config_entity_UN` (`config_type_id`, `name`, `variable`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_vm.pipeline definition

CREATE TABLE `pipeline`
(
    `pipeline_id`          bigint(20)  NOT NULL,
    `created_by`           bigint(20)  NOT NULL,
    `created_date`         datetime(6) NOT NULL,
    `last_modified_by`     bigint(20)  NOT NULL,
    `last_modified_date`   datetime(6) NOT NULL,
    `context_node`         longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`context_node`)),
    `execution_status_id`  int(11)                                            DEFAULT NULL,
    `lambda_name`          varchar(255)                                       DEFAULT NULL,
    `parent_pipeline_id`   bigint(20)                                         DEFAULT NULL,
    `parent_pipeline_name` varchar(255)                                       DEFAULT NULL,
    `pipeline_name`        varchar(255)                                       DEFAULT NULL,
    `file_content`         longtext                                           DEFAULT NULL,
    `host_name`            varchar(255)                                       DEFAULT NULL,
    `mode_of_execution`    varchar(255)                                       DEFAULT NULL,
    `pipeline_load_type`   varchar(255)                                       DEFAULT NULL,
    `relative_path`        varchar(255)                                       DEFAULT NULL,
    `request_body`         varchar(255)                                       DEFAULT NULL,
    `thread_name`          varchar(255)                                       DEFAULT NULL,
    `parent_action_id`     bigint(20)                                         DEFAULT NULL,
    `parent_action_name`   varchar(255)                                       DEFAULT NULL,
    PRIMARY KEY (`pipeline_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_vm.pipeline_execution_audit definition

CREATE TABLE `pipeline_execution_audit`
(
    `id`                  bigint(20)  NOT NULL AUTO_INCREMENT,
    `pipeline_id`         bigint(20)  NOT NULL,
    `created_by`          bigint(20)  NOT NULL,
    `created_date`        datetime(6) NOT NULL,
    `last_modified_by`    bigint(20)  NOT NULL,
    `last_modified_date`  datetime(6) NOT NULL,
    `execution_status_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_vm.resource_connection definition

CREATE TABLE `resource_connection`
(
    `name`               varchar(255) NOT NULL,
    `created_by`         bigint(20)   NOT NULL,
    `created_date`       datetime(6)  NOT NULL,
    `last_modified_by`   bigint(20)   NOT NULL,
    `last_modified_date` datetime(6)  NOT NULL,
    `active`             bit(1)       NOT NULL,
    `config_type`        varchar(255) NOT NULL,
    `driver_class_name`  varchar(255) NOT NULL,
    `password`           varchar(255) NOT NULL,
    `url`                varchar(255) NOT NULL,
    `user_name`          varchar(255) NOT NULL,
    PRIMARY KEY (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_vm.statement_audit definition

CREATE TABLE `statement_audit`
(
    `statement_id`       bigint(20)  NOT NULL,
    `action_id`          bigint(20)  NOT NULL,
    `created_by`         bigint(20)  NOT NULL,
    `created_date`       datetime(6) NOT NULL,
    `last_modified_by`   bigint(20)  NOT NULL,
    `last_modified_date` datetime(6) NOT NULL,
    `rows_processed`     int(11) DEFAULT NULL,
    `rows_read`          int(11) DEFAULT NULL,
    `rows_written`       int(11) DEFAULT NULL,
    `time_taken`         double  DEFAULT NULL,
    `statement`          text    DEFAULT NULL,
    PRIMARY KEY (`statement_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO config_entity
(id, config_type_id, name, variable, active, created_by, created_date, last_modified_by, last_modified_date, value)
VALUES (1, 1, 'SYS_PACKAGE', 'DEFAULT', 1, 1, '2021-10-30 00:00:00.000', 1, '2021-10-30 00:00:00.000',
        'in.handyman.raven.lib');
INSERT INTO config_entity
(id, config_type_id, name, variable, active, created_by, created_date, last_modified_by, last_modified_date, value)
VALUES (2, 4, 'RestApiActionTest', 'filepath', 1, 1, '2021-10-30 00:00:00.000', 1, '2021-10-30 00:00:00.000',
        'test-file/restapi.test.lmd');


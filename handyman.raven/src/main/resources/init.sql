DROP TABLE if exists action_audit;
DROP TABLE if exists action_execution_audit;
DROP TABLE if exists config_entity;
DROP TABLE if exists pipeline_audit;
DROP TABLE if exists pipeline_execution_audit;
DROP TABLE if exists resource_config;
DROP TABLE if exists statement_audit;

-- config_entity definition

CREATE TABLE `config_entity`
(
    `config_type_id`     int(11)      NOT NULL,
    `name`               varchar(255) NOT NULL,
    `variable`           varchar(255) NOT NULL,
    `active`             bit(1)       NOT NULL,
    `created_by`         bigint(20)   NOT NULL,
    `created_date`       datetime(6)  NOT NULL,
    `last_modified_by`   bigint(20)   NOT NULL,
    `last_modified_date` datetime(6)  NOT NULL,
    `value`              varchar(255) NOT NULL,
    PRIMARY KEY (`config_type_id`, `name`, `variable`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO config_entity
(config_type_id, name, variable, active, created_by, created_date, last_modified_by, last_modified_date, value)
VALUES (1, 'SYS_PACKAGE', 'DEFAULT', 1, 1, '2021-10-30 00:00:00.000', 1, '2021-10-30 00:00:00.000',
        'in.handyman.raven.lib');
INSERT INTO config_entity
(config_type_id, name, variable, active, created_by, created_date, last_modified_by, last_modified_date, value)
VALUES (4, 'RestApiActionTest', 'filepath', 1, 1, '2021-10-30 00:00:00.000', 1, '2021-10-30 00:00:00.000',
        'test-file/restapi.test.lmd');



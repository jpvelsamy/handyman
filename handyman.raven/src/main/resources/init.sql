TRUNCATE TABLE action_audit;
TRUNCATE TABLE action_execution_audit;
TRUNCATE TABLE config_entity;
TRUNCATE TABLE pipeline_audit;
TRUNCATE TABLE pipeline_execution_audit;
TRUNCATE TABLE resource_config;
TRUNCATE TABLE statement_audit;


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


-- handyman_vm.`action` definition

CREATE TABLE `action`
(
    `action_id`            bigint(20) NOT NULL,
    `created_by`           bigint(20)   DEFAULT NULL,
    `created_date`         datetime     DEFAULT NULL,
    `last_modified_by`     bigint(20)   DEFAULT NULL,
    `last_modified_date`   datetime     DEFAULT NULL,
    `context_node`         text         DEFAULT NULL,
    `execution_status_id`  int(11)      DEFAULT NULL,
    `lambda_name`          varchar(255) DEFAULT NULL,
    `parent_action_id`     bigint(20)   DEFAULT NULL,
    `parent_action_name`   varchar(255) DEFAULT NULL,
    `parent_pipeline_id`   bigint(20)   DEFAULT NULL,
    `parent_pipeline_name` varchar(255) DEFAULT NULL,
    `pipeline_name`        varchar(255) DEFAULT NULL,
    `action_name`          varchar(255) DEFAULT NULL,
    `execution_group_id`   int(11)      DEFAULT NULL,
    `input_node`           text         DEFAULT NULL,
    `log`                  text         DEFAULT NULL,
    `pipeline_id`          bigint(20)   DEFAULT NULL,
    PRIMARY KEY (`action_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_vm.action_execution_audit definition

CREATE TABLE `action_execution_audit`
(
    `id`                  bigint(20) NOT NULL,
    `created_by`          bigint(20) DEFAULT NULL,
    `created_date`        datetime   DEFAULT NULL,
    `last_modified_by`    bigint(20) DEFAULT NULL,
    `last_modified_date`  datetime   DEFAULT NULL,
    `action_id`           bigint(20) DEFAULT NULL,
    `execution_status_id` int(11)    DEFAULT NULL,
    `pipeline_id`         bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_vm.config_store definition

CREATE TABLE `config_store`
(
    `id`                 bigint(20) NOT NULL,
    `active`             bit(1)     NOT NULL,
    `config_type_id`     int(11)      DEFAULT NULL,
    `name`               varchar(255) DEFAULT NULL,
    `value`              text         DEFAULT NULL,
    `variable`           varchar(255) DEFAULT NULL,
    `created_by`         bigint(20)   DEFAULT NULL,
    `created_date`       datetime     DEFAULT NULL,
    `last_modified_by`   bigint(20)   DEFAULT NULL,
    `last_modified_date` datetime     DEFAULT NULL,
    `configTypeId`       int(11)      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_vm.lambda_execution_audit definition

CREATE TABLE `lambda_execution_audit`
(
    `id`                  bigint(20) NOT NULL,
    `created_by`          bigint(20) DEFAULT NULL,
    `created_date`        datetime   DEFAULT NULL,
    `last_modified_by`    bigint(20) DEFAULT NULL,
    `last_modified_date`  datetime   DEFAULT NULL,
    `execution_status_id` int(11)    DEFAULT NULL,
    `pipeline_id`         bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_vm.pipeline definition

CREATE TABLE `pipeline`
(
    `pipeline_id`          bigint(20) NOT NULL,
    `created_by`           bigint(20)   DEFAULT NULL,
    `created_date`         datetime     DEFAULT NULL,
    `last_modified_by`     bigint(20)   DEFAULT NULL,
    `last_modified_date`   datetime     DEFAULT NULL,
    `context_node`         text         DEFAULT NULL,
    `execution_status_id`  int(11)      DEFAULT NULL,
    `lambda_name`          varchar(255) DEFAULT NULL,
    `parent_action_id`     bigint(20)   DEFAULT NULL,
    `parent_action_name`   varchar(255) DEFAULT NULL,
    `parent_pipeline_id`   bigint(20)   DEFAULT NULL,
    `parent_pipeline_name` varchar(255) DEFAULT NULL,
    `pipeline_name`        varchar(255) DEFAULT NULL,
    `file_content`         text         DEFAULT NULL,
    `host_name`            varchar(255) DEFAULT NULL,
    `mode_of_execution`    varchar(255) DEFAULT NULL,
    `pipeline_load_type`   varchar(255) DEFAULT NULL,
    `relative_path`        varchar(255) DEFAULT NULL,
    `request_body`         text         DEFAULT NULL,
    `thread_name`          varchar(255) DEFAULT NULL,
    PRIMARY KEY (`pipeline_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_vm.resource_connection definition

CREATE TABLE `resource_connection`
(
    `name`               varchar(255) NOT NULL,
    `created_by`         bigint(20)   DEFAULT NULL,
    `created_date`       datetime     DEFAULT NULL,
    `last_modified_by`   bigint(20)   DEFAULT NULL,
    `last_modified_date` datetime     DEFAULT NULL,
    `active`             bit(1)       NOT NULL,
    `config_type`        varchar(255) DEFAULT NULL,
    `driver_class_name`  varchar(255) DEFAULT NULL,
    `password`           varchar(255) DEFAULT NULL,
    `url`                varchar(255) DEFAULT NULL,
    `user_name`          varchar(255) DEFAULT NULL,
    PRIMARY KEY (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_vm.`statement` definition

CREATE TABLE `statement`
(
    `statement_id`       bigint(20) NOT NULL,
    `created_by`         bigint(20)   DEFAULT NULL,
    `created_date`       datetime     DEFAULT NULL,
    `last_modified_by`   bigint(20)   DEFAULT NULL,
    `last_modified_date` datetime     DEFAULT NULL,
    `action_id`          bigint(20)   DEFAULT NULL,
    `rows_processed`     int(11)      DEFAULT NULL,
    `rows_read`          int(11)      DEFAULT NULL,
    `rows_written`       int(11)      DEFAULT NULL,
    `statement_content`  varchar(255) DEFAULT NULL,
    `time_taken`         double       DEFAULT NULL,
    PRIMARY KEY (`statement_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


INSERT INTO handyman_vm.config_store (id, active, config_type_id, name, value, variable) VALUES(1, 1, 1, 'SYS_PACKAGE', 'in.handyman.raven.lib', 'DEFAULT');
INSERT INTO handyman_vm.config_store (id, active, config_type_id, name, value, variable) VALUES(3, 1, 4, 'RestApiActionTest', 'process "cub.test"
{
	try {

	 multitude as "test audit entry with multitude" on "PARALLEL" using {

            restapi as "test audit entry with none"  source "${cub_eval}" using url "https://vada-chennai.free.beeceptor.com"
              method "POST" with body type {"NONE"}
              {}

           restapi as "test audit entry with JSON"  source "${cub_eval}" using url "https://vada-chennai.free.beeceptor.com"
             method "POST" with body type {"JSON"}
             {
             { part as "attribute1" with "select 1"}
             }

             restapi as "test audit entry with FORM"  source "${cub_eval}" using url "https://vada-chennai.free.beeceptor.com"
              method "POST" with body type {"FORM"}
             {
             { part as "attribute1" with "select 1" type as "TEXT" }
             { part as "attribute2" with "select ''src/main/resources/init.sql''" type as "FILE" }
             }

     } fielding 2

	}

	catch {

	}

	finally {

	}
}
', 'processContent');


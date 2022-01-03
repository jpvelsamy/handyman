CREATE DATABASE `handyman_audit` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

use handyman_audit;

-- handyman_audit.execution_group definition

CREATE TABLE `execution_group`
(
    `id`    int(11)      NOT NULL,
    `value` varchar(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `execution_group_UN` (`value`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_audit.execution_status definition

CREATE TABLE `execution_status`
(
    `id`    int(11)      NOT NULL,
    `value` varchar(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `execution_status_UN` (`value`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_audit.pipeline_execution_audit definition

CREATE TABLE `pipeline_execution_audit`
(
    `pipeline_id`          bigint(20)   NOT NULL,
    `created_by`           bigint(20)   NOT NULL,
    `created_date`         datetime     NOT NULL,
    `last_modified_by`     bigint(20)   NOT NULL,
    `last_modified_date`   datetime     NOT NULL,
    `context_node`         longtext     NOT NULL,
    `execution_status_id`  int(11)      NOT NULL,
    `lambda_name`          varchar(255) NOT NULL,
    `parent_action_id`     bigint(20)   NOT NULL,
    `parent_action_name`   varchar(255) NOT NULL,
    `parent_pipeline_id`   bigint(20)   NOT NULL,
    `parent_pipeline_name` varchar(255) NOT NULL,
    `pipeline_name`        varchar(255) NOT NULL,
    `file_content`         longtext     NOT NULL,
    `host_name`            varchar(255) NOT NULL,
    `mode_of_execution`    varchar(255) NOT NULL,
    `pipeline_load_type`   varchar(255) NOT NULL,
    `relative_path`        varchar(255) NOT NULL,
    `request_body`         longtext     NOT NULL,
    `thread_name`          varchar(255) NOT NULL,
    `process_name`         varchar(100) NOT NULL,
    `root_pipeline_id`     bigint(20)   NOT NULL,
    PRIMARY KEY (`pipeline_id`),
    KEY `pipeline_execution_audit_FK` (`execution_status_id`),
    CONSTRAINT `pipeline_execution_audit_FK` FOREIGN KEY (`execution_status_id`) REFERENCES `execution_status` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_audit.pipeline_execution_status_audit definition

CREATE TABLE `pipeline_execution_status_audit`
(
    `id`                  bigint(20) NOT NULL,
    `created_by`          bigint(20) DEFAULT NULL,
    `created_date`        datetime   DEFAULT NULL,
    `last_modified_by`    bigint(20) DEFAULT NULL,
    `last_modified_date`  datetime   DEFAULT NULL,
    `execution_status_id` int(11)    DEFAULT NULL,
    `pipeline_id`         bigint(20) DEFAULT NULL,
    `root_pipeline_id`    bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `pipeline_execution_status_audit_FK` (`pipeline_id`),
    KEY `pipeline_execution_status_audit_FK_2` (`execution_status_id`),
    CONSTRAINT `pipeline_execution_status_audit_FK` FOREIGN KEY (`pipeline_id`) REFERENCES `pipeline_execution_audit` (`pipeline_id`),
    CONSTRAINT `pipeline_execution_status_audit_FK_2` FOREIGN KEY (`execution_status_id`) REFERENCES `execution_status` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_audit.action_execution_audit definition

CREATE TABLE `action_execution_audit`
(
    `action_id`            bigint(20)   NOT NULL,
    `created_by`           bigint(20)   NOT NULL,
    `created_date`         datetime     NOT NULL,
    `last_modified_by`     bigint(20)   NOT NULL,
    `last_modified_date`   datetime     NOT NULL,
    `context_node`         longtext DEFAULT NULL,
    `execution_status_id`  int(11)      NOT NULL,
    `lambda_name`          varchar(255) NOT NULL,
    `parent_action_id`     bigint(20)   NOT NULL,
    `parent_action_name`   varchar(255) NOT NULL,
    `parent_pipeline_id`   bigint(20)   NOT NULL,
    `parent_pipeline_name` varchar(255) NOT NULL,
    `pipeline_name`        varchar(255) NOT NULL,
    `action_name`          varchar(255) NOT NULL,
    `execution_group_id`   int(11)      NOT NULL,
    `input_node`           longtext DEFAULT NULL,
    `log`                  longtext DEFAULT NULL,
    `pipeline_id`          bigint(20)   NOT NULL,
    `thread_name`          varchar(100) NOT NULL,
    `root_pipeline_id`     bigint(20)   NOT NULL,
    PRIMARY KEY (`action_id`),
    KEY `action_FK` (`pipeline_id`),
    KEY `pipeline_execution_status_audit_FK_execution_status` (`execution_status_id`),
    KEY `action_execution_audit_FK_1` (`execution_group_id`),
    CONSTRAINT `action_execution_audit_FK` FOREIGN KEY (`pipeline_id`) REFERENCES `pipeline_execution_audit` (`pipeline_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `action_execution_audit_FK_1` FOREIGN KEY (`execution_group_id`) REFERENCES `execution_group` (`id`),
    CONSTRAINT `pipeline_execution_status_audit_FK_execution_status` FOREIGN KEY (`execution_status_id`) REFERENCES `execution_status` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_audit.action_execution_status_audit definition

CREATE TABLE `action_execution_status_audit`
(
    `id`                  bigint(20) NOT NULL,
    `created_by`          bigint(20) NOT NULL,
    `created_date`        datetime   NOT NULL,
    `last_modified_by`    bigint(20) NOT NULL,
    `last_modified_date`  datetime   NOT NULL,
    `action_id`           bigint(20) NOT NULL,
    `execution_status_id` int(11)    NOT NULL,
    `pipeline_id`         bigint(20) NOT NULL,
    `root_pipeline_id`    bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `action_execution_status_audit_FK` (`action_id`),
    KEY `action_execution_status_audit_FK_1` (`execution_status_id`),
    CONSTRAINT `action_execution_status_audit_FK` FOREIGN KEY (`action_id`) REFERENCES `action_execution_audit` (`action_id`),
    CONSTRAINT `action_execution_status_audit_FK_1` FOREIGN KEY (`execution_status_id`) REFERENCES `execution_status` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- handyman_audit.statement_execution_audit definition

CREATE TABLE `statement_execution_audit`
(
    `statement_id`       bigint(20) NOT NULL,
    `created_by`         bigint(20) DEFAULT NULL,
    `created_date`       datetime   DEFAULT NULL,
    `last_modified_by`   bigint(20) DEFAULT NULL,
    `last_modified_date` datetime   DEFAULT NULL,
    `action_id`          bigint(20) DEFAULT NULL,
    `rows_processed`     int(11)    DEFAULT NULL,
    `rows_read`          int(11)    DEFAULT NULL,
    `rows_written`       int(11)    DEFAULT NULL,
    `statement_content`  longtext   DEFAULT NULL,
    `time_taken`         double     DEFAULT NULL,
    `root_pipeline_id`   bigint(20) DEFAULT NULL,
    PRIMARY KEY (`statement_id`),
    KEY `statement_execution_audit_FK` (`action_id`),
    CONSTRAINT `statement_execution_audit_FK` FOREIGN KEY (`action_id`) REFERENCES `action_execution_audit` (`action_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE DATABASE `handyman_config` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

use handyman_config;

-- handyman_config.spw_common_config definition

CREATE TABLE `spw_common_config`
(
    `id`                 bigint(20)     NOT NULL AUTO_INCREMENT,
    `variable`           varchar(150)   NOT NULL,
    `value`              longtext       NOT NULL,
    `active`             enum ('Y','N') NOT NULL,
    `created_by`         bigint(20)     NOT NULL,
    `created_date`       datetime       NOT NULL,
    `last_modified_by`   bigint(20)     NOT NULL,
    `last_modified_date` datetime       NOT NULL,
    `version`            int(11)        NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `spw_common_config_UN` (`variable`, `version`) USING HASH
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;


-- handyman_config.spw_instance_config definition

CREATE TABLE `spw_instance_config`
(
    `id`                 bigint(20)     NOT NULL AUTO_INCREMENT,
    `instance`           varchar(150)   NOT NULL,
    `process`            varchar(150)   NOT NULL,
    `variable`           varchar(150)   NOT NULL,
    `value`              longtext       NOT NULL,
    `active`             enum ('Y','N') NOT NULL,
    `created_by`         bigint(20)     NOT NULL,
    `created_date`       datetime       NOT NULL,
    `last_modified_by`   bigint(20)     NOT NULL,
    `last_modified_date` datetime       NOT NULL,
    `version`            int(11)        NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `spw_instance_config_UN` (`instance`, `process`, `variable`, `version`) USING HASH
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;


-- handyman_config.spw_process_config definition

CREATE TABLE `spw_process_config`
(
    `id`                 bigint(20)     NOT NULL AUTO_INCREMENT,
    `process`            varchar(150)   NOT NULL,
    `variable`           varchar(150)   NOT NULL,
    `value`              varchar(500)   NOT NULL,
    `active`             enum ('Y','N') NOT NULL,
    `created_by`         bigint(20)     NOT NULL,
    `created_date`       datetime       NOT NULL,
    `last_modified_by`   bigint(20)     NOT NULL,
    `last_modified_date` datetime       NOT NULL,
    `version`            int(11)        NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `spw_process_config_UN` (`process`, `variable`, `version`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb3;


-- handyman_config.spw_resource_config definition

CREATE TABLE `spw_resource_config`
(
    `id`                 bigint(20)     NOT NULL AUTO_INCREMENT,
    `config_name`        varchar(50)    NOT NULL,
    `resource_url`       varchar(1000)  NOT NULL,
    `active`             enum ('Y','N') NOT NULL,
    `user_name`          varchar(50)    NOT NULL,
    `password`           varchar(50)    NOT NULL,
    `driver_class`       varchar(100)   NOT NULL,
    `host`               varchar(100)   NOT NULL,
    `port`               varchar(100)   NOT NULL,
    `database_name`      varchar(100)   NOT NULL,
    `created_by`         bigint(20)     NOT NULL,
    `created_date`       datetime       NOT NULL,
    `last_modified_by`   bigint(20)     NOT NULL,
    `last_modified_date` datetime       NOT NULL,
    `version`            int(11)        NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `spw_resource_config_UN` (`host`, `database_name`, `user_name`, `port`, `version`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;


DROP DATABASE if exists `handyman_qrtz`;
CREATE DATABASE `handyman_qrtz` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

use handyman_qrtz;

#
# Quartz seems to work best
#
# PLEASE consider using mysql with innodb tables to avoid locking issues
#
# In your Quartz properties file, you'll need to set
# org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.StdJDBCDelegate
#

DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;
DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;
DROP TABLE IF EXISTS QRTZ_LOCKS;
DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_SIMPROP_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;
DROP TABLE IF EXISTS QRTZ_CALENDARS;


CREATE TABLE QRTZ_JOB_DETAILS
(
    SCHED_NAME        VARCHAR(120) NOT NULL,
    JOB_NAME          VARCHAR(200) NOT NULL,
    JOB_GROUP         VARCHAR(200) NOT NULL,
    DESCRIPTION       VARCHAR(250) NULL,
    JOB_CLASS_NAME    VARCHAR(250) NOT NULL,
    IS_DURABLE        VARCHAR(1)   NOT NULL,
    IS_NONCONCURRENT  VARCHAR(1)   NOT NULL,
    IS_UPDATE_DATA    VARCHAR(1)   NOT NULL,
    REQUESTS_RECOVERY VARCHAR(1)   NOT NULL,
    JOB_DATA          BLOB         NULL,
    PRIMARY KEY (SCHED_NAME, JOB_NAME, JOB_GROUP)
);

CREATE TABLE QRTZ_TRIGGERS
(
    SCHED_NAME     VARCHAR(120) NOT NULL,
    TRIGGER_NAME   VARCHAR(200) NOT NULL,
    TRIGGER_GROUP  VARCHAR(200) NOT NULL,
    JOB_NAME       VARCHAR(200) NOT NULL,
    JOB_GROUP      VARCHAR(200) NOT NULL,
    DESCRIPTION    VARCHAR(250) NULL,
    NEXT_FIRE_TIME BIGINT(13)   NULL,
    PREV_FIRE_TIME BIGINT(13)   NULL,
    PRIORITY       INTEGER      NULL,
    TRIGGER_STATE  VARCHAR(16)  NOT NULL,
    TRIGGER_TYPE   VARCHAR(8)   NOT NULL,
    START_TIME     BIGINT(13)   NOT NULL,
    END_TIME       BIGINT(13)   NULL,
    CALENDAR_NAME  VARCHAR(200) NULL,
    MISFIRE_INSTR  SMALLINT(2)  NULL,
    JOB_DATA       BLOB         NULL,
    PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    FOREIGN KEY (SCHED_NAME, JOB_NAME, JOB_GROUP)
        REFERENCES QRTZ_JOB_DETAILS (SCHED_NAME, JOB_NAME, JOB_GROUP)
);

CREATE TABLE QRTZ_SIMPLE_TRIGGERS
(
    SCHED_NAME      VARCHAR(120) NOT NULL,
    TRIGGER_NAME    VARCHAR(200) NOT NULL,
    TRIGGER_GROUP   VARCHAR(200) NOT NULL,
    REPEAT_COUNT    BIGINT(7)    NOT NULL,
    REPEAT_INTERVAL BIGINT(12)   NOT NULL,
    TIMES_TRIGGERED BIGINT(10)   NOT NULL,
    PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
        REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

CREATE TABLE QRTZ_CRON_TRIGGERS
(
    SCHED_NAME      VARCHAR(120) NOT NULL,
    TRIGGER_NAME    VARCHAR(200) NOT NULL,
    TRIGGER_GROUP   VARCHAR(200) NOT NULL,
    CRON_EXPRESSION VARCHAR(200) NOT NULL,
    TIME_ZONE_ID    VARCHAR(80),
    PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
        REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

CREATE TABLE QRTZ_SIMPROP_TRIGGERS
(
    SCHED_NAME    VARCHAR(120)   NOT NULL,
    TRIGGER_NAME  VARCHAR(200)   NOT NULL,
    TRIGGER_GROUP VARCHAR(200)   NOT NULL,
    STR_PROP_1    VARCHAR(512)   NULL,
    STR_PROP_2    VARCHAR(512)   NULL,
    STR_PROP_3    VARCHAR(512)   NULL,
    INT_PROP_1    INT            NULL,
    INT_PROP_2    INT            NULL,
    LONG_PROP_1   BIGINT         NULL,
    LONG_PROP_2   BIGINT         NULL,
    DEC_PROP_1    NUMERIC(13, 4) NULL,
    DEC_PROP_2    NUMERIC(13, 4) NULL,
    BOOL_PROP_1   VARCHAR(1)     NULL,
    BOOL_PROP_2   VARCHAR(1)     NULL,
    PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
        REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

CREATE TABLE QRTZ_BLOB_TRIGGERS
(
    SCHED_NAME    VARCHAR(120) NOT NULL,
    TRIGGER_NAME  VARCHAR(200) NOT NULL,
    TRIGGER_GROUP VARCHAR(200) NOT NULL,
    BLOB_DATA     BLOB         NULL,
    PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
        REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

CREATE TABLE QRTZ_CALENDARS
(
    SCHED_NAME    VARCHAR(120) NOT NULL,
    CALENDAR_NAME VARCHAR(200) NOT NULL,
    CALENDAR      BLOB         NOT NULL,
    PRIMARY KEY (SCHED_NAME, CALENDAR_NAME)
);

CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS
(
    SCHED_NAME    VARCHAR(120) NOT NULL,
    TRIGGER_GROUP VARCHAR(200) NOT NULL,
    PRIMARY KEY (SCHED_NAME, TRIGGER_GROUP)
);

CREATE TABLE QRTZ_FIRED_TRIGGERS
(
    SCHED_NAME        VARCHAR(120) NOT NULL,
    ENTRY_ID          VARCHAR(95)  NOT NULL,
    TRIGGER_NAME      VARCHAR(200) NOT NULL,
    TRIGGER_GROUP     VARCHAR(200) NOT NULL,
    INSTANCE_NAME     VARCHAR(200) NOT NULL,
    FIRED_TIME        BIGINT(13)   NOT NULL,
    SCHED_TIME        BIGINT(13)   NOT NULL,
    PRIORITY          INTEGER      NOT NULL,
    STATE             VARCHAR(16)  NOT NULL,
    JOB_NAME          VARCHAR(200) NULL,
    JOB_GROUP         VARCHAR(200) NULL,
    IS_NONCONCURRENT  VARCHAR(1)   NULL,
    REQUESTS_RECOVERY VARCHAR(1)   NULL,
    PRIMARY KEY (SCHED_NAME, ENTRY_ID)
);

CREATE TABLE QRTZ_SCHEDULER_STATE
(
    SCHED_NAME        VARCHAR(120) NOT NULL,
    INSTANCE_NAME     VARCHAR(200) NOT NULL,
    LAST_CHECKIN_TIME BIGINT(13)   NOT NULL,
    CHECKIN_INTERVAL  BIGINT(13)   NOT NULL,
    PRIMARY KEY (SCHED_NAME, INSTANCE_NAME)
);

CREATE TABLE QRTZ_LOCKS
(
    SCHED_NAME VARCHAR(120) NOT NULL,
    LOCK_NAME  VARCHAR(40)  NOT NULL,
    PRIMARY KEY (SCHED_NAME, LOCK_NAME)
);


commit;

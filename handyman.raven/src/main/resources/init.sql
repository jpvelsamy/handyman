DROP TABLE if exists action_audit;
DROP TABLE if exists action_execution_audit;
DROP TABLE if exists config_entity;
DROP TABLE if exists pipeline_audit;
DROP TABLE if exists pipeline_execution_audit;
DROP TABLE if exists resource_config;
DROP TABLE if exists statement_audit;

CREATE TABLE CONFIG_ENTITY
(
    config_type_id     int(11)      NOT NULL,
    name               varchar(255) NOT NULL,
    variable           varchar(255) NOT NULL,
    active             boolean      NOT NULL,
    created_by         int(11)   NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_by   int(11)   NOT NULL,
    last_modified_date TIMESTAMP    NOT NULL,
    value              varchar(255) NOT NULL,
    PRIMARY KEY (config_type_id, name, variable)
);

INSERT INTO CONFIG_ENTITY
(config_type_id, name, variable, active, created_by, created_date, last_modified_by, last_modified_date, value)
VALUES (1, 'SYS_PACKAGE', 'DEFAULT', 1, 1, '2021-10-30 00:00:00.000', 1, '2021-10-30 00:00:00.000',
        'in.handyman.raven.lib');
INSERT INTO CONFIG_ENTITY
(config_type_id, name, variable, active, created_by, created_date, last_modified_by, last_modified_date, value)
VALUES (4, 'RestApiActionTest', 'filepath', 1, 1, '2021-10-30 00:00:00.000', 1, '2021-10-30 00:00:00.000',
        'test-file/restapi.test.lmd');


CREATE TABLE RESOURCE_CONFIG
(
    name               varchar(255) NOT NULL,
    created_by         int(11)   NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_by   int(11)   NOT NULL,
    last_modified_date TIMESTAMP    NOT NULL,
    active             boolean      NOT NULL,
    config_type        varchar(255) NOT NULL,
    driver_class_name  varchar(255) NOT NULL,
    password           varchar(255) NOT NULL,
    url                varchar(255) NOT NULL,
    user_name          varchar(255) NOT NULL,
    PRIMARY KEY (name)
);



CREATE TABLE ACTION_AUDIT
(
    action_id            int(11) NOT NULL,
    created_by           int(11) NOT NULL,
    created_date         TIMESTAMP  NOT NULL,
    last_modified_by     int(11) NOT NULL,
    last_modified_date   TIMESTAMP  NOT NULL,
    context_node         json,
    execution_status_id  int(11)      DEFAULT NULL,
    lambda_name          varchar(255) DEFAULT NULL,
    parent_pipeline_id   int(11)   DEFAULT NULL,
    parent_pipeline_name varchar(255) DEFAULT NULL,
    parent_action_id     int(11)   DEFAULT NULL,
    parent_action_name   varchar(255) DEFAULT NULL,
    pipeline_name        varchar(255) DEFAULT NULL,
    action_name          varchar(255) DEFAULT NULL,
    input_node           json,
    pipeline_id          int(11)   DEFAULT NULL,
    rows_processed       int(11)      DEFAULT NULL,
    rows_read            int(11)      DEFAULT NULL,
    rows_written         int(11)      DEFAULT NULL,
    time_taken           double       DEFAULT NULL,
    log                  text         DEFAULT NULL,
    execution_group_id   int(11)      DEFAULT NULL,
    PRIMARY KEY (action_id)
)
;

CREATE TABLE ACTION_EXECUTION_AUDIT
(
    id                  int(11) NOT NULL AUTO_INCREMENT,
    action_id           int(11) NOT NULL,
    created_by          int(11) NOT NULL,
    created_date        TIMESTAMP  NOT NULL,
    last_modified_by    int(11) NOT NULL,
    last_modified_date  TIMESTAMP  NOT NULL,
    execution_status_id int(11) DEFAULT NULL,
    PRIMARY KEY (id)
)
;

CREATE TABLE PIPELINE_AUDIT
(
    pipeline_id          int(11) NOT NULL,
    created_by           int(11) NOT NULL,
    created_date         TIMESTAMP  NOT NULL,
    last_modified_by     int(11) NOT NULL,
    last_modified_date   TIMESTAMP  NOT NULL,
    context_node         json,
    execution_status_id  int(11)      DEFAULT NULL,
    lambda_name          varchar(255) DEFAULT NULL,
    parent_pipeline_id   int(11)   DEFAULT NULL,
    parent_pipeline_name varchar(255) DEFAULT NULL,
    pipeline_name        varchar(255) DEFAULT NULL,
    file_content         varchar(255) DEFAULT NULL,
    host_name            varchar(255) DEFAULT NULL,
    mode_of_execution    varchar(255) DEFAULT NULL,
    pipeline_load_type   varchar(255) DEFAULT NULL,
    relative_path        varchar(255) DEFAULT NULL,
    request_body         varchar(255) DEFAULT NULL,
    thread_name          varchar(255) DEFAULT NULL,
    parent_action_id     int(11)   DEFAULT NULL,
    parent_action_name   varchar(255) DEFAULT NULL,
    PRIMARY KEY (pipeline_id)
)
;

CREATE TABLE PIPELINE_EXECUTION_AUDIT
(
    id                  int(11) NOT NULL AUTO_INCREMENT,
    pipeline_id         int(11) NOT NULL,
    created_by          int(11) NOT NULL,
    created_date        TIMESTAMP  NOT NULL,
    last_modified_by    int(11) NOT NULL,
    last_modified_date  TIMESTAMP  NOT NULL,
    execution_status_id int(11) DEFAULT NULL,
    PRIMARY KEY (id)
)
;


CREATE TABLE STATEMENT_AUDIT
(
    statement_id       int(11) NOT NULL,
    action_id          int(11) NOT NULL,
    created_by         int(11) NOT NULL,
    created_date       TIMESTAMP  NOT NULL,
    last_modified_by   int(11) NOT NULL,
    last_modified_date TIMESTAMP  NOT NULL,
    rows_processed     int(11) DEFAULT NULL,
    rows_read          int(11) DEFAULT NULL,
    rows_written       int(11) DEFAULT NULL,
    time_taken         double  DEFAULT NULL,
    statement          text    DEFAULT NULL,
    PRIMARY KEY (statement_id)
)
;

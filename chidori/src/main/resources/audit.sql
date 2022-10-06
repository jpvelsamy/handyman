    -- DROP SCHEMA audit;

    CREATE SCHEMA audit AUTHORIZATION postgres;
    -- audit.action_execution_audit definition

    -- Drop table

    -- DROP TABLE audit.action_execution_audit;

CREATE TABLE audit.action_execution_audit
(
    action_id            int8 NOT NULL,
    created_by           int8 NULL,
    created_date         timestamp NULL,
    last_modified_by     int8 NULL,
    last_modified_date   timestamp NULL,
    context_node         text NULL,
    execution_status_id  int4 NULL,
    lambda_name          varchar NULL,
    parent_action_id     int8 NULL,
    parent_action_name   varchar NULL,
    parent_pipeline_id   int8 NULL,
    parent_pipeline_name varchar NULL,
    pipeline_name        varchar NULL,
    action_name          varchar NULL,
    execution_group_id   int4 NULL,
    input_node           text NULL,
    log                  text NULL,
    pipeline_id          int8 NULL,
    thread_name          varchar NULL,
    root_pipeline_id     int8 NULL,
    CONSTRAINT action_execution_audit_pkey PRIMARY KEY (action_id)
);


-- audit.action_execution_status_audit definition

-- Drop table

-- DROP TABLE audit.action_execution_status_audit;

CREATE TABLE audit.action_execution_status_audit
(
    id                  int8      NOT NULL,
    created_by          int8      NOT NULL,
    created_date        timestamp NOT NULL,
    last_modified_by    int8      NOT NULL,
    last_modified_date  timestamp NOT NULL,
    action_id           int8      NOT NULL,
    execution_status_id int4      NOT NULL,
    pipeline_id         int8      NOT NULL,
    root_pipeline_id    int8      NOT NULL,
    CONSTRAINT action_execution_status_audit_pkey PRIMARY KEY (id)
);


-- audit.execution_group definition

-- Drop table

-- DROP TABLE audit.execution_group;

CREATE TABLE audit.execution_group
(
    id    int4    NOT NULL,
    value varchar NOT NULL
);


-- audit.execution_status definition

-- Drop table

-- DROP TABLE audit.execution_status;

CREATE TABLE audit.execution_status
(
    id    int4    NOT NULL,
    value varchar NOT NULL,
    CONSTRAINT execution_status_pkey PRIMARY KEY (id)
);


-- audit.pipeline_execution_audit definition

-- Drop table

-- DROP TABLE audit.pipeline_execution_audit;

CREATE TABLE audit.pipeline_execution_audit
(
    pipeline_id          int8 NOT NULL,
    created_by           int8 NULL,
    created_date         timestamp NULL,
    last_modified_by     int8 NULL,
    last_modified_date   timestamp NULL,
    context_node         text NULL,
    execution_status_id  int4 NULL,
    lambda_name          varchar NULL,
    parent_action_id     int8 NULL,
    parent_action_name   varchar NULL,
    parent_pipeline_id   int8 NULL,
    parent_pipeline_name varchar NULL,
    pipeline_name        varchar NULL,
    file_content         text NULL,
    host_name            varchar NULL,
    mode_of_execution    varchar NULL,
    pipeline_load_type   varchar NULL,
    relative_path        varchar NULL,
    request_body         text NULL,
    thread_name          varchar NULL,
    process_name         varchar NULL,
    root_pipeline_id     int8 NULL,
    process_id           int8 NULL,
    CONSTRAINT pipeline_execution_audit_pkey PRIMARY KEY (pipeline_id)
);


-- audit.pipeline_execution_status_audit definition

-- Drop table

-- DROP TABLE audit.pipeline_execution_status_audit;

CREATE TABLE audit.pipeline_execution_status_audit
(
    id                  int8 NOT NULL,
    created_by          int8 NULL,
    created_date        timestamp NULL,
    last_modified_by    int8 NULL,
    last_modified_date  timestamp NULL,
    execution_status_id int4 NULL,
    pipeline_id         int8 NULL,
    root_pipeline_id    int8 NULL,
    CONSTRAINT pipeline_execution_status_audit_pkey PRIMARY KEY (id)
);


-- audit.statement_execution_audit definition

-- Drop table

-- DROP TABLE audit.statement_execution_audit;

CREATE TABLE audit.statement_execution_audit
(
    statement_id       int8 NOT NULL,
    created_by         int8 NULL,
    created_date       timestamp NULL,
    last_modified_by   int8 NULL,
    last_modified_date timestamp NULL,
    action_id          int8 NULL,
    rows_processed     int4 NULL,
    rows_read          int4 NULL,
    rows_written       int4 NULL,
    statement_content  text NULL,
    time_taken         float8 NULL,
    root_pipeline_id   int8 NULL,
    CONSTRAINT statement_execution_audit_pkey PRIMARY KEY (statement_id)
);
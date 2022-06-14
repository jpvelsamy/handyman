-- DROP SCHEMA audit;

CREATE SCHEMA config AUTHORIZATION postgres;
-- config.spw_common_config definition

-- Drop table

-- DROP TABLE config.spw_common_config;

CREATE TABLE config.spw_common_config
(
    id                 int8         NOT NULL,
    variable           varchar(150) NOT NULL,
    value              text         NOT NULL,
    active             bool         NOT NULL,
    created_by         int8         NOT NULL,
    created_date       timestamp    NOT NULL,
    last_modified_by   int8         NOT NULL,
    last_modified_date timestamp    NOT NULL,
    "version"          int4         NOT NULL,
    CONSTRAINT spw_common_config_pkey PRIMARY KEY (id)
);


-- config.spw_instance_config definition

-- Drop table

-- DROP TABLE config.spw_instance_config;

CREATE TABLE config.spw_instance_config
(
    id                 int8         NOT NULL,
    "instance"         varchar(150) NOT NULL,
    process            varchar(150) NOT NULL,
    variable           varchar(150) NOT NULL,
    value              text         NOT NULL,
    active             bool         NOT NULL,
    created_by         int8         NOT NULL,
    created_date       timestamp    NOT NULL,
    last_modified_by   int8         NOT NULL,
    last_modified_date timestamp    NOT NULL,
    "version"          int4         NOT NULL,
    CONSTRAINT spw_instance_config_pkey PRIMARY KEY (id)
);


-- config.spw_process_config definition

-- Drop table

-- DROP TABLE config.spw_process_config;

CREATE TABLE config.spw_process_config
(
    id                 int8         NOT NULL,
    process            varchar(150) NOT NULL,
    variable           varchar(150) NOT NULL,
    value              varchar(500) NOT NULL,
    active             bool         NOT NULL,
    created_by         int8         NOT NULL,
    created_date       timestamp    NOT NULL,
    last_modified_by   int8         NOT NULL,
    last_modified_date timestamp    NOT NULL,
    "version"          int4         NOT NULL,
    CONSTRAINT spw_process_config_pkey PRIMARY KEY (id)
);


-- config.spw_resource_config definition

-- Drop table

-- DROP TABLE config.spw_resource_config;

CREATE TABLE config.spw_resource_config
(
    id                 int8      NOT NULL,
    config_name        varchar   NOT NULL,
    resource_url       text      NOT NULL,
    active             bool      NOT NULL,
    user_name          varchar   NOT NULL,
    "password"         varchar   NOT NULL,
    driver_class       varchar   NOT NULL,
    host               varchar   NOT NULL,
    port               varchar   NOT NULL,
    database_name      varchar   NOT NULL,
    created_by         int8      NOT NULL,
    created_date       timestamp NOT NULL,
    last_modified_by   int8      NOT NULL,
    last_modified_date timestamp NOT NULL,
    "version"          int4      NOT NULL,
    CONSTRAINT spw_resource_config_pkey PRIMARY KEY (id)
);
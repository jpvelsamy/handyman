CREATE DATABASE `spw_audit` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

CREATE DATABASE `spw_config` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;


-- spw_config.spw_common_config definition

CREATE TABLE `spw_common_config` (
                                     `variable` varchar(150) NOT NULL,
                                     `value` varchar(500) NOT NULL,
                                     `active` enum('Y','N') NOT NULL,
                                     PRIMARY KEY (`variable`,`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- spw_config.spw_instance_config definition

CREATE TABLE `spw_instance_config` (
                                       `instance` varchar(150) NOT NULL,
                                       `process` varchar(150) NOT NULL,
                                       `variable` varchar(150) NOT NULL,
                                       `value` varchar(500) NOT NULL,
                                       `active` enum('Y','N') NOT NULL,
                                       PRIMARY KEY (`instance`,`process`,`variable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- spw_config.spw_process_config definition

CREATE TABLE `spw_process_config` (
                                      `process` varchar(150) NOT NULL,
                                      `variable` varchar(150) NOT NULL,
                                      `value` varchar(500) NOT NULL,
                                      `active` enum('Y','N') NOT NULL,
                                      PRIMARY KEY (`process`,`variable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- spw_config.spw_resource_config definition

CREATE TABLE `spw_resource_config` (
                                       `config_type` varchar(50) DEFAULT NULL,
                                       `config_name` varchar(50) NOT NULL,
                                       `auth_info` varchar(1000) DEFAULT NULL,
                                       `resource_url` varchar(1000) NOT NULL,
                                       `active` enum('Y','N') NOT NULL,
                                       `created_date` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
                                       `user_name` varchar(50) NOT NULL,
                                       `password` varchar(50) NOT NULL,
                                       `driver_class` varchar(100) NOT NULL,
                                       PRIMARY KEY (`config_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- spw_audit.command_audit definition

CREATE TABLE `command_audit` (
                                 `command_id` bigint(20) NOT NULL,
                                 `instance_id` bigint(20) NOT NULL,
                                 `start` timestamp NULL DEFAULT current_timestamp(),
                                 `end` datetime DEFAULT NULL,
                                 `action_name` varchar(150) NOT NULL,
                                 `process_name` varchar(150) NOT NULL,
                                 `command_config` text DEFAULT NULL,
                                 `status` smallint(6) NOT NULL,
                                 PRIMARY KEY (`command_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;


-- spw_audit.instance_audit definition

CREATE TABLE `instance_audit` (
                                  `instance_id` bigint(20) NOT NULL,
                                  `instance_name` varchar(150) NOT NULL,
                                  `mode` varchar(100) NOT NULL,
                                  `start` timestamp NULL DEFAULT current_timestamp(),
                                  `end` datetime DEFAULT NULL,
                                  `hostname` varchar(50) DEFAULT NULL,
                                  `file` varchar(500) DEFAULT NULL,
                                  `status` smallint(6) NOT NULL,
                                  `context_log` text DEFAULT NULL,
                                  `root_ref_id` int(11) DEFAULT NULL,
                                  PRIMARY KEY (`instance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- spw_audit.statement_audit definition

CREATE TABLE `statement_audit` (
                                   `statement_id` bigint(20) NOT NULL,
                                   `action_id` bigint(20) NOT NULL,
                                   `action_name` varchar(50) NOT NULL,
                                   `process_name` varchar(50) NOT NULL,
                                   `start` timestamp NULL DEFAULT current_timestamp(),
                                   `end` datetime DEFAULT NULL,
                                   `rows_written` int(11) DEFAULT NULL,
                                   `statement_config` text DEFAULT NULL,
                                   `rows_read` int(11) DEFAULT NULL,
                                   `status` smallint(6) NOT NULL,
                                   PRIMARY KEY (`statement_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO spw_config.spw_common_config (variable, value, active) VALUES('scanLambdaPackage', 'in.handyman.raven.lib', 'Y');
INSERT INTO spw_config.spw_common_config (variable, value, active) VALUES('timeZone', 'Asia/Kolkata', 'Y');


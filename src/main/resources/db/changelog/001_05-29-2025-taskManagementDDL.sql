CREATE SEQUENCE IF NOT EXISTS users_id_seq INCREMENT 1 NO MINVALUE NO MAXVALUE START 1 CACHE 1;

CREATE TABLE IF NOT EXISTS users (
    id                  BIGINT                      NOT NULL DEFAULT nextval('users_id_seq' :: REGCLASS),
    name               VARCHAR(250)                 NOT NULL,
    user_name               VARCHAR(250)             NOT NULL,
    password               VARCHAR(250)                NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id)
    );

CREATE SEQUENCE IF NOT EXISTS task_id_seq INCREMENT 1 NO MINVALUE NO MAXVALUE START 1 CACHE 1;

CREATE TABLE IF NOT EXISTS task (
    id                  BIGINT                      NOT NULL DEFAULT nextval('task_id_seq' :: REGCLASS),
    title               VARCHAR(250)                NOT NULL,
    description         VARCHAR(500)                ,
    status              VARCHAR(50)                 ,
    priority            VARCHAR(50)                 ,
    created_by          BIGINT                      NOT NULL,
    due_date           TIMESTAMP                    ,
    CONSTRAINT created_by_users_id_fk FOREIGN KEY (created_by) REFERENCES users (id),
    CONSTRAINT task_pk PRIMARY KEY (id)
    );
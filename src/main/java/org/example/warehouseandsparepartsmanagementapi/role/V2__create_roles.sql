CREATE TABLE role
(
    id   bigint IDENTITY (1, 1) NOT NULL,
    role varchar(255) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
)
    GO

ALTER TABLE role
    ADD CONSTRAINT uc_role_role UNIQUE (role)
    GO
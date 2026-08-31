ALTER TABLE users
    ADD role_id bigint
    GO

ALTER TABLE users
ALTER
COLUMN role_id bigint NOT NULL
GO

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES role (id)
    GO

ALTER TABLE users
ALTER
COLUMN first_name varchar(255)
GO

ALTER TABLE users
ALTER
COLUMN last_name varchar(255)
GO

ALTER TABLE users
ALTER
COLUMN username varchar(255)
GO
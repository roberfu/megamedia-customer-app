CREATE DATABASE customer_database;

CREATE TABLE role (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE _user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE billing_data (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL UNIQUE,
    country VARCHAR(255) NOT NULL,
    account VARCHAR(255) NOT NULL,
    currency VARCHAR(255) NOT NULL
);

CREATE INDEX idex_user_username ON _user(username);

CREATE INDEX index_customer_email ON customer(email);

ALTER TABLE user_role
ADD CONSTRAINT fk_user_role_user
FOREIGN KEY (user_id) REFERENCES _user(id) ON DELETE CASCADE;

ALTER TABLE user_role
ADD CONSTRAINT fk_user_role_role
FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE;

ALTER TABLE billing_data
ADD CONSTRAINT fk_billing_data_customer
FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE;
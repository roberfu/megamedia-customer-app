INSERT INTO role (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO _user (username, password) VALUES ('user', '$2a$10$9Ld.dXSdaEDrooif0.QHAeLSsNp4b2u5hiPY3NRrglMUcfYMnblA2'); -- user
INSERT INTO _user (username, password) VALUES ('admin', '$2a$10$mKa3S7mJzz5X6RVN/rPAjObXdySqmUIYlzHIv3WuOqv/c376a/S/6'); -- admin

INSERT INTO user_role (user_id, role_id) VALUES (1, 1)
INSERT INTO user_role (user_id, role_id) VALUES (2, 2)

INSERT INTO customer (first_name, last_name, email) VALUES ('Fernanda', 'Fuentes', 'fernanda.fuentes@email.com');
INSERT INTO customer (first_name, last_name, email) VALUES ('Oscar', 'Arce', 'oscar.arce@email.com');

INSERT INTO billing_data (customer_id, country, account, currency) VALUES (1, 'Argentina', 'AR0987654321', 'Argentine Peso');
INSERT INTO billing_data (customer_id, country, account, currency) VALUES (2, 'Chile', 'CL1234567890', 'Chilean Peso');
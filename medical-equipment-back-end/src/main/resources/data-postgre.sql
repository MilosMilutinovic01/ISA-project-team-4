INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-1, 'Kraljevo', 'Srbija', 'Ibarska 2');
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-2, 'Vlasenica', 'BIH', 'Karadjordjeva 17b');
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-3, 'Bijeljina', 'BIH', 'ulica1');
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-4, 'Novi Sad', 'Srbija', 'ulica2');

INSERT INTO stakeholders.users(
    id, enabled, name, lastname, last_password_reset_date, password, phone_number, username, address_id)
VALUES (-1, 'true', 'miki', 'miki', null, 'test', '0603908001', 'milosmilutinovic6@gmail.com', -1);

INSERT INTO stakeholders.role (name) VALUES ('ROLE_USER');
INSERT INTO stakeholders.role (name) VALUES ('ROLE_ADMIN');

INSERT INTO stakeholders.user_role (user_id, role_id) VALUES (-1, 1); -- user-u dodeljujemo rolu USER
INSERT INTO stakeholders.user_role (user_id, role_id) VALUES (-2, 2); -- user-u dodeljujemo rolu ADMIN
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-1, 'Kraljevo', 'Srbija', 'Ibarska 2');
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-2, 'Vlasenica', 'BIH', 'Karadjordjeva 17b');
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-3, 'Bijeljina', 'BIH', 'ulica1');
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-4, 'Novi Sad', 'Srbija', 'ulica2');

INSERT INTO stakeholders.customers(
    category, user_type, address_id, id, penalty_points, email, lastname, name, password, phone_number, profession)
VALUES (0, 2, -1, -1, 10, 'milosmilutinovicg@gmail.com', 'milutinovic', 'milos', 'sifra1', '0603908001', 'student');
INSERT INTO stakeholders.customers(
    category, user_type, address_id, id, penalty_points, email, lastname, name, password, phone_number, profession)
VALUES (0, 2, -2, -2, 10, 'jblanusa13@gmail.com', 'blanusa', 'jelena', 'sifra2', '0603908001', 'student');

INSERT INTO stakeholders.company_administrators(
    user_type, address_id, company_id, id, email, lastname, name, password, phone_number)
VALUES (1, -1, -1, -1, 'pajic@gmail.com', 'Pajic', 'Petar', '123', '+381649699696');
INSERT INTO stakeholders.company_administrators(
    user_type, address_id, company_id, id, email, lastname, name, password, phone_number)
VALUES (1, -2, -1, -2, 'gajic@gmail.com', 'Gajic', 'Goran', '123456', '+381649699696');
INSERT INTO stakeholders.company_administrators(
    user_type, address_id, company_id, id, email, lastname, name, password, phone_number)
VALUES (1, -3, -3, -3, 'djajic@gmail.com', 'Djajic', 'Zoran', '321', '+381633699696');

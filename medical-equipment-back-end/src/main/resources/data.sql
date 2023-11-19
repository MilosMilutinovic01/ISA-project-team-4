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
VALUES (1, -2, -2, -2, 'gajic@gmail.com', 'Gajic', 'Goran', '123', '+381649699696');


INSERT INTO stakeholders.companies(
    average_rating, end_time, start_time, address_id, id, description, name)
VALUES (8, '14:00:00', '07:00:00', -1, -1, 'opis', 'Hemofarm');
INSERT INTO stakeholders.companies(
    average_rating, end_time, start_time, address_id, id, description, name)
VALUES (9, '14:00:00', '07:00:00', -2, -2, 'opis', 'Benu');

INSERT INTO stakeholders.equipment(
    price, type, id, description, name)
VALUES (2, 0, -1, 'opis', 'igle');
INSERT INTO stakeholders.equipment(
    price, type, id, description, name)
        VALUES (4, 1, -2, 'opis', 'oprema');


INSERT INTO stakeholders.equipment_tracking(
    count, company_id, equipment_id, id)
VALUES (5, -1, -1, -1);INSERT INTO stakeholders.equipment_tracking(
    count, company_id, equipment_id, id)
                       VALUES (6, -1, -2, -2);
INSERT INTO stakeholders.equipment_tracking(
    count, company_id, equipment_id, id)
VALUES (5, -2, -2, -3);

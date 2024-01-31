INSERT INTO stakeholders.addresses(id, city, country, street, longitude, latitude) VALUES (-1, 'Kraljevo', 'Srbija', 'Ibarska 2', 19.849171,45.242092);
INSERT INTO stakeholders.addresses(id, city, country, street, longitude, latitude) VALUES (-2, 'Vlasenica', 'BIH', 'Karadjordjeva 17b', 19.849171,45.242092);
INSERT INTO stakeholders.addresses(id, city, country, street, longitude, latitude) VALUES (-3, 'Bijeljina', 'BIH', 'ulica1', 19.849171,45.242092);
INSERT INTO stakeholders.addresses(id, city, country, street, longitude, latitude) VALUES (-4, 'Novi Sad', 'Srbija', 'ulica2', 19.849171,45.242092);
INSERT INTO stakeholders.addresses(id, city, country, street, longitude, latitude) VALUES (-5, 'Beograd', 'Srbija', 'ulica3', 19.849171,45.242092);
INSERT INTO stakeholders.addresses(id, city, country, street, longitude, latitude) VALUES (-6, 'Beograd', 'Srbija', 'ulica4', 19.849171,45.242092);
INSERT INTO stakeholders.addresses(id, city, country, street, longitude, latitude) VALUES (-7, 'Beograd', 'Srbija', 'ulica5', 19.849171,45.242092);
INSERT INTO stakeholders.addresses(id, city, country, street, longitude, latitude) VALUES (-11, 'Beograd', 'Srbija', 'ulica6', 19.849171,45.242092);
INSERT INTO stakeholders.addresses(id, city, country, street, longitude, latitude) VALUES (-12, 'Beograd', 'Srbija', 'ulica6', 19.849171,45.242092);


INSERT INTO stakeholders.users(
	id, enabled, lastname, name, password, phone_number, role, username, address_id)
	VALUES (-1, true, 'Customer', 'Miki', '$2a$10$lnAdFe/m6/0IuJtGoO11IuYPp.rgG0gchAud/480F84lwnI5Ejau6', 0504901001, 2, 'miki@gmail.com', -1);

INSERT INTO stakeholders.users(
    id, enabled, lastname, name, password, phone_number, role, username, address_id)
VALUES (-2, true, 'Company', 'Petar', '$2a$10$lnAdFe/m6/0IuJtGoO11IuYPp.rgG0gchAud/480F84lwnI5Ejau6', 0504901001, 1, 'petar@gmail.com', -2);

INSERT INTO stakeholders.users(
    id, enabled, lastname, name, password, phone_number, role, username, address_id)
VALUES (-3, true, 'Company', 'Vlado', '$2a$10$lnAdFe/m6/0IuJtGoO11IuYPp.rgG0gchAud/480F84lwnI5Ejau6', 0504901001, 1, 'vlado@gmail.com', -3);

INSERT INTO stakeholders.users(
    id, enabled, lastname, name, password, phone_number, role, username, address_id)
VALUES (-4, true, 'Company', 'Verica', '$2a$10$lnAdFe/m6/0IuJtGoO11IuYPp.rgG0gchAud/480F84lwnI5Ejau6', 0504901001, 1, 'verica@gmail.com', -4);

INSERT INTO stakeholders.users(
    id, enabled, lastname, name, password, phone_number, role, username, address_id)
VALUES (-5, true, 'Company', 'Minja', '$2a$10$lnAdFe/m6/0IuJtGoO11IuYPp.rgG0gchAud/480F84lwnI5Ejau6', 0504909001, 1, 'minja@gmail.com', -5);

INSERT INTO stakeholders.users(
    id, enabled, lastname, name, password, phone_number, role, username, address_id)
VALUES (-6, true, 'Company', 'Milica', '$2a$10$lnAdFe/m6/0IuJtGoO11IuYPp.rgG0gchAud/480F84lwnI5Ejau6', 0504901001, 1, 'milica@gmail.com', -6);

INSERT INTO stakeholders.users(
    id, enabled, lastname, name, password, phone_number, role, username, address_id)
VALUES (-7, true, 'System', 'Marija', '$2a$10$lnAdFe/m6/0IuJtGoO11IuYPp.rgG0gchAud/480F84lwnI5Ejau6', 0504909001, 0, 'marija@gmail.com', -7);

INSERT INTO stakeholders.users(
    id, enabled, lastname, name, password, phone_number, role, username, address_id)
VALUES (-8, true, 'Customer', 'Tester1', '$2a$10$lnAdFe/m6/0IuJtGoO11IuYPp.rgG0gchAud/480F84lwnI5Ejau6', 0504901001, 2, 'tester1@gmail.com', -11);

INSERT INTO stakeholders.users(
    id, enabled, lastname, name, password, phone_number, role, username, address_id)
VALUES (-9, true, 'Customer', 'Tester2', '$2a$10$lnAdFe/m6/0IuJtGoO11IuYPp.rgG0gchAud/480F84lwnI5Ejau6', 0504901001, 2, 'tester2@gmail.com', -12);


INSERT INTO stakeholders.customers(
    category, penalty_points, profession, customer_id)
VALUES (0, 0, 'student', -1);

INSERT INTO stakeholders.customers(
    category, penalty_points, profession, customer_id)
VALUES (0, 0, 'tester', -8);

INSERT INTO stakeholders.customers(
    category, penalty_points, profession, customer_id)
VALUES (0, 0, 'tester', -9);

INSERT INTO stakeholders.company_administrators(
    company_id, company_administrator_id)
VALUES (-1, -2);
INSERT INTO stakeholders.company_administrators(
    company_id, company_administrator_id)
VALUES (-2, -3);
INSERT INTO stakeholders.company_administrators(
    company_id, company_administrator_id)
VALUES (-3, -4);

INSERT INTO stakeholders.company_administrators(
    company_id, company_administrator_id)
VALUES (-1, -5);
INSERT INTO stakeholders.company_administrators(
    company_id, company_administrator_id)
VALUES (-1, -6);

INSERT INTO stakeholders.system_administrators(
    has_logged_before, system_administrator_id)
VALUES (true, -7);

INSERT INTO stakeholders.addresses(

    id, city, country, street, longitude, latitude)
VALUES (-8, 'Novi Sad', 'Srbija', 'Cara Dusana 12', 19.849171,45.242092);
INSERT INTO stakeholders.companies(
    average_rating, end_time, start_time, address_id, id, description, name)
VALUES (4,'14:30','08:30', -8, -1, 'Neki opiss', 'MEDLAB');

INSERT INTO stakeholders.addresses(
    id, city, country, street, longitude, latitude)
VALUES (-9, 'Novi Sad', 'Srbija', 'Fruskogorska 12', 19.849171,45.242092);
INSERT INTO stakeholders.companies(
    average_rating, end_time, start_time, address_id, id, description, name)
VALUES (3,'17:30','07:30', -9, -2, 'Neki opiss', 'MEDICINSKA OPREMA NS');

INSERT INTO stakeholders.addresses(
    id, city, country, street, longitude, latitude)
VALUES (-10, 'Novi Sad', 'Srbija', 'Cara Dusana 12', 19.849171,45.242092);
INSERT INTO stakeholders.companies(
    average_rating, end_time, start_time, address_id, id, description, name)
VALUES (5,'14:30','08:30', -10, -3, 'Neki opiss', 'OPREMA021');

INSERT INTO stakeholders.equipment(
    id, description, name, price, type)
VALUES (-1, 'hirurska', 'maska', 10, 2);
INSERT INTO stakeholders.equipment(
    id, description, name, price, type)
VALUES (-2, 'hirurski', 'mantil', 1500, 2);
INSERT INTO stakeholders.equipment(
    id, description, name, price, type)
VALUES (-3, '50ml', 'spric', 250, 0);
INSERT INTO stakeholders.equipment(
    id, description, name, price, type)
VALUES (-4, 'za intramuskularne injekcije', 'igla', 500, 0);

INSERT INTO stakeholders.equipment_tracking(
    id, count, company_id, equipment_id)
VALUES (-1, 3000, -1, -1);

INSERT INTO stakeholders.equipment_tracking(
    id, count, company_id, equipment_id)
VALUES (-2, 2500, -2, -2);

INSERT INTO stakeholders.equipment_tracking(
    id, count, company_id, equipment_id)
VALUES (-3, 10000, -2, -3);

INSERT INTO stakeholders.equipment_tracking(
    id, count, company_id, equipment_id)
VALUES (-4, 2500, -1, -4);

INSERT INTO stakeholders.equipment_tracking(
    id, count, company_id, equipment_id)
VALUES (-5, 2400, -3, -4);

INSERT INTO stakeholders.appointments(
    id, end_time, start_time, is_predefined, company_administrator_id)
VALUES (-1, '2024-02-03T10:00:00.000Z', '2024-02-03T09:30:00.000Z', true, -2);

INSERT INTO stakeholders.appointments(
    id, end_time, start_time, is_predefined, company_administrator_id)
VALUES (-2, '2024-02-05T11:00:00.000Z', '2024-02-05T10:30:00.000Z', true, -2);

INSERT INTO stakeholders.appointments(
    id, end_time, start_time, is_predefined, company_administrator_id)
VALUES (-3, '2024-02-05T13:00:00.000Z', '2024-02-05T12:30:00.000Z', true, -2);


INSERT INTO stakeholders.appointments(
    id, end_time, start_time, is_predefined, company_administrator_id)
VALUES (-4, '2024-02-06T11:00:00.000Z', '2024-02-06T10:30:00.000Z', true, -2);

INSERT INTO stakeholders.appointments(
    id, end_time, start_time, is_predefined, company_administrator_id)
VALUES (-5, '2024-02-07T12:00:00.000Z', '2024-02-07T11:30:00.000Z', true, -2);


INSERT INTO stakeholders.appointments(
    id, end_time, start_time, is_predefined, company_administrator_id)
VALUES (-6, '2024-01-27T12:00:00.000Z', '2024-01-27T11:30:00.000Z', true, -2);

INSERT INTO stakeholders.appointments(
    id, end_time, start_time, is_predefined, company_administrator_id)
VALUES (-7, '2024-01-25T14:00:00.000Z', '2024-01-25T13:30:00.000Z', true, -2);

INSERT INTO stakeholders.appointments(
    id, end_time, start_time, is_predefined, company_administrator_id)
VALUES (-8, '2024-01-24T11:00:00.000Z', '2024-01-24T10:30:00.000Z', true, -2);

INSERT INTO stakeholders.appointments(
    id, end_time, start_time, is_predefined, company_administrator_id)
VALUES (-9, '2024-01-23T11:00:00.000Z', '2024-01-23T10:30:00.000Z', true, -2);


INSERT INTO stakeholders.items(
    id, count, appointment_id, company_id, customer_id, equipment_id, picked_up, qr_code_processed)
VALUES (-1, 1, -9, -1, -1, -1, TRUE, TRUE);

INSERT INTO stakeholders.items(
    id, count, appointment_id, company_id, customer_id, equipment_id, picked_up, qr_code_processed)
VALUES (-2, 10, -8, -1, -1, -4, FALSE, TRUE);

INSERT INTO stakeholders.items(
    id, count, appointment_id, company_id, customer_id, equipment_id, picked_up, qr_code_processed)
VALUES (-3, 100, -8, -1, -1, -1, FALSE, TRUE);

INSERT INTO stakeholders.items(
    id, count, appointment_id, company_id, customer_id, equipment_id, picked_up, qr_code_processed)
VALUES (-4, 5, -7, -1, -1, -4, TRUE, TRUE);

INSERT INTO stakeholders.items(
    id, count, appointment_id, company_id, customer_id, equipment_id, picked_up, qr_code_processed)
VALUES (-5, 15, -6, -1, -1, -4, TRUE, TRUE);

INSERT INTO stakeholders.items(
    id, count, appointment_id, company_id, customer_id, equipment_id, picked_up, qr_code_processed)
VALUES (-6, 15, -6, -1, -1, -1, TRUE, TRUE);

INSERT INTO stakeholders.items(
    id, count, appointment_id, company_id, customer_id, equipment_id, picked_up, qr_code_processed)
VALUES (-7, 2, -1, -1, -1, -4, FALSE, FALSE);

INSERT INTO stakeholders.items(
    id, count, appointment_id, company_id, customer_id, equipment_id, picked_up, qr_code_processed)
VALUES (-8, 20, -4, -1, -1, -4, FALSE, FALSE);

INSERT INTO stakeholders.items(
    id, count, appointment_id, company_id, customer_id, equipment_id, picked_up, qr_code_processed)
VALUES (-9, 2, null, -1, -8, -4, FALSE, FALSE);

INSERT INTO stakeholders.items(
    id, count, appointment_id, company_id, customer_id, equipment_id, picked_up, qr_code_processed)
VALUES (-10, 2, null, -1, -9, -4, FALSE, FALSE);


INSERT INTO stakeholders.contracts(
    hospital, count, date_in_month, equipment_id, cancelled_this_month)
VALUES ('New hospital', 500, 5, -1,false);
INSERT INTO stakeholders.contracts(
    hospital, count, date_in_month, equipment_id, cancelled_this_month)
VALUES ('Eurolab', 1000, 5, -3,false);
INSERT INTO stakeholders.contracts(
    hospital, count, date_in_month, equipment_id, cancelled_this_month)
VALUES ('HealthMedic', 50, 10, -2,false);
INSERT INTO stakeholders.contracts(
    hospital, count, date_in_month, equipment_id, cancelled_this_month)
VALUES ('Poliklinika Galetic', 500, 31, -4,false);
INSERT INTO stakeholders.contracts(
    hospital, count, date_in_month, equipment_id, cancelled_this_month)
VALUES ('Poliklinika Pekic', 1000, 30, -2,false);

INSERT INTO stakeholders.addresses(
    id, city, country, latitude, longitude, street)
VALUES (-13, 'Novi Sad', 'Srbija', 45.242092, 19.849171, 'Ilije Bircanina 17');

INSERT INTO stakeholders.users(
    id, enabled, lastname, name, password, phone_number, role, username, address_id)
VALUES (-10, true, 'Ranic', 'Ana', '$2a$10$MV0rMLQti72ukEK25abmzuBd1N55Jj4cR7r7c4SvQYHptWjnWaef2', '0666666666', 2, 'anaranic01@gmail.com', -11);

INSERT INTO stakeholders.customers(
    category, penalty_points, profession, verification_token, customer_id)
VALUES (0, 0, 'student', '8ece0a34377047839d598e9afadc308c', -10);
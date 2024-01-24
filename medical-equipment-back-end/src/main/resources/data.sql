INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-1, 'Kraljevo', 'Srbija', 'Ibarska 2');
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-2, 'Vlasenica', 'BIH', 'Karadjordjeva 17b');
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-3, 'Bijeljina', 'BIH', 'ulica1');
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-4, 'Novi Sad', 'Srbija', 'ulica2');
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-8, 'Beograd', 'Srbija', 'ulica3');

INSERT INTO stakeholders.users(
	id, enabled, lastname, name, password, phone_number, role, username, address_id)
	VALUES (-1, true, 'Customer', 'Mare', '$2a$10$lnAdFe/m6/0IuJtGoO11IuYPp.rgG0gchAud/480F84lwnI5Ejau6', 0504901001, 2, 'mare@gmail.com', -1);

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
VALUES (-5, true, 'System', 'Marija', '$2a$10$lnAdFe/m6/0IuJtGoO11IuYPp.rgG0gchAud/480F84lwnI5Ejau6', 0504909001, 0, 'marija@gmail.com', -8);

INSERT INTO stakeholders.customers(
    category, penalty_points, profession, customer_id)
VALUES (0, 0, 'student', -1);

INSERT INTO stakeholders.company_administrators(
    company_id, company_administrator_id)
VALUES (-1, -2);
INSERT INTO stakeholders.company_administrators(
    company_id, company_administrator_id)
VALUES (-2, -3);
INSERT INTO stakeholders.company_administrators(
    company_id, company_administrator_id)
VALUES (-3, -4);

INSERT INTO stakeholders.system_administrators(
    has_logged_before, system_administrator_id)
VALUES (true, -5);

INSERT INTO stakeholders.addresses(
    id, city, country, street)
VALUES (-5, 'Novi Sad', 'Srbija', 'Cara Dusana 12');
INSERT INTO stakeholders.companies(
    average_rating, end_time, start_time, address_id, id, description, name)
VALUES (4,'14:30','08:30', -5, -1, 'Neki opiss', 'MEDLAB');

INSERT INTO stakeholders.addresses(
    id, city, country, street)
VALUES (-6, 'Novi Sad', 'Srbija', 'Fruskogorska 12');
INSERT INTO stakeholders.companies(
    average_rating, end_time, start_time, address_id, id, description, name)
VALUES (3,'17:30','07:30', -6, -2, 'Neki opiss', 'MEDICINSKA OPREMA NS');

INSERT INTO stakeholders.addresses(
    id, city, country, street)
VALUES (-7, 'Novi Sad', 'Srbija', 'Cara Dusana 12');
INSERT INTO stakeholders.companies(
    average_rating, end_time, start_time, address_id, id, description, name)
VALUES (5,'14:30','08:30', -7, -3, 'Neki opiss', 'OPREMA021');

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
VALUES (-1, 300, -1, -1);

INSERT INTO stakeholders.equipment_tracking(
    id, count, company_id, equipment_id)
VALUES (-2, 250, -2, -2);

INSERT INTO stakeholders.equipment_tracking(
    id, count, company_id, equipment_id)
VALUES (-3, 1000, -2, -3);

INSERT INTO stakeholders.equipment_tracking(
    id, count, company_id, equipment_id)
VALUES (-4, 250, -1, -4);

INSERT INTO stakeholders.equipment_tracking(
    id, count, company_id, equipment_id)
VALUES (-5, 240, -3, -4);

INSERT INTO stakeholders.appointments(
    id, end_time, start_time, company_administrator_id)
VALUES (-1, '2024-01-29T13:00:00.000Z', '2024-01-29T12:30:00.000Z', -2);

INSERT INTO stakeholders.appointments(
    id, end_time, start_time, company_administrator_id)
VALUES (-2, '2024-01-29T11:00:00.000Z', '2024-01-29T10:30:00.000Z', -2);

INSERT INTO stakeholders.items(
    id, count, appointment_id, company_id, customer_id, equipment_id)
VALUES (-1, 2, -1, -1, -1, -1);

INSERT INTO stakeholders.items(
    id, count, appointment_id, company_id, customer_id, equipment_id)
VALUES (-2, 3, -2, -1, -1, -2);

INSERT INTO stakeholders.appointments(
    id, end_time, start_time, company_administrator_id)
VALUES (-3, '2024-01-30T13:00:00.000Z', '2024-01-30T12:30:00.000Z', -2);


INSERT INTO stakeholders.appointments(
    id, end_time, start_time, company_administrator_id)
VALUES (-4, '2024-01-30T11:00:00.000Z', '2024-01-30T10:30:00.000Z', -2);

INSERT INTO stakeholders.appointments(
    id, end_time, start_time, company_administrator_id)
VALUES (-5, '2024-01-27T12:00:00.000Z', '2024-01-27T11:30:00.000Z', -2);


INSERT INTO stakeholders.appointments(
    id, end_time, start_time, company_administrator_id)
VALUES (-6, '2024-01-26T10:00:00.000Z', '2024-01-26T09:30:00.000Z', -2);



INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-1, 'Kraljevo', 'Srbija', 'Ibarska 2');
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-2, 'Vlasenica', 'BIH', 'Karadjordjeva 17b');
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-3, 'Bijeljina', 'BIH', 'ulica1');
INSERT INTO stakeholders.addresses(id, city, country, street) VALUES (-4, 'Novi Sad', 'Srbija', 'ulica2');

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

INSERT INTO stakeholders.equipment_tracking(
    id, count, company_id, equipment_id)
VALUES (-6, 100, -2, -3);
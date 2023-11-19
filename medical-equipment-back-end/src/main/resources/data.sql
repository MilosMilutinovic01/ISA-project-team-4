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
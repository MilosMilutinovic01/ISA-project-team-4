/*drop table stakeholders.customers;
drop table stakeholders.users;*/

create table stakeholders.customers (
                                        id bigserial not null,
                                        address varchar(255),
                                        city varchar(255),
                                        country varchar(255),
                                        email varchar(255),
                                        lastname varchar(255),
                                        name varchar(255),
                                        password varchar(255),
                                        phone_number varchar(255),
                                        profession varchar(255),
                                        user_id bigint,
                                        primary key (id)
);
create table stakeholders.users (
        id bigserial not null,
        password varchar(255),
        user_type smallint check (user_type between 0 and 1),
        username varchar(255),
        primary key (id)
    );

alter table if exists stakeholders.customers
       drop constraint if exists UK_euat1oase6eqv195jvb71a93s;

alter table if exists stakeholders.customers
       add constraint UK_euat1oase6eqv195jvb71a93s unique (user_id);

alter table if exists stakeholders.customers
       add constraint FKrh1g1a20omjmn6kurd35o3eit
       foreign key (user_id)
       references stakeholders.users;

insert into stakeholders.users (id, username, password, user_type) values ('-1', 'admin', 'admin', 0);
insert into stakeholders.users (id, username, password, user_type) values ('-2', 'email1@gmail.com', 'sifra1', 1);
insert into stakeholders.customers(address, city, country, email, lastname, name, password, phone_number, profession, user_id) VALUES ('adresa', 'grad', 'zemlja', 'email1@gmail.com', 'prezime', 'ime', 'sifra1', '0603908001', 'profesija', -2);
insert into stakeholders.users (id, username, password, user_type) values ('-3', 'email2@gmail.com', 'sifra2', 1);
insert into stakeholders.customers(address, city, country, email, lastname, name, password, phone_number, profession, user_id) VALUES ('adresa', 'grad', 'zemlja', 'email2@gmail.com', 'prezime', 'ime', 'sifra2', '0603908001', 'profesija', -3);
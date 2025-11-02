SET search_path TO blj;

-- Create Libraries
INSERT INTO blj.car_garage VALUES (1);
INSERT INTO blj.car_garage VALUES (2);
INSERT INTO blj.car_garage VALUES (3);
INSERT INTO blj.car_garage VALUES (4);

-- Create People
INSERT INTO blj.user (user_id, firstname, lastname, email, password, role, is_banned, age) VALUES
                                                                                               ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'Michel', 'Mahadeva', 'michel@mail.com', '$2a$10$sypmy5Giz7FGAH.Hm7emXux0OGdjc2OpSy4shaSu4LcyfafijoJF6', 'ADMIN', false, 18),
                                                                                               ('550e8400-e29b-41d4-a716-446655440000', 'Jan', 'Ludwig', 'jan@mail.com', '$2a$10$sypmy5Giz7FGAH.Hm7emXux0OGdjc2OpSy4shaSu4LcyfafijoJF6', 'USER', false, 20),
                                                                                               ('6ba7b810-9dad-11d1-80b4-00c04fd430c8', 'Nico', 'Linder', 'nico@mail.com', '$2a$10$sypmy5Giz7FGAH.Hm7emXux0OGdjc2OpSy4shaSu4LcyfafijoJF6', 'USER', false, 14),
                                                                                               ('7c9e6679-7425-40de-944b-e07fc1f90ae7', 'Moreno', 'Ramanti', 'moreno@mail.com', '$2a$10$sypmy5Giz7FGAH.Hm7emXux0OGdjc2OpSy4shaSu4LcyfafijoJF6', 'USER', true, 8),
                                                                                               ('8f14e45f-ceea-4f8e-9d0c-1f7e6a4c3b2d', 'Denis', 'Suciu', 'denis@mail.com', '$2a$10$sypmy5Giz7FGAH.Hm7emXux0OGdjc2OpSy4shaSu4LcyfafijoJF6', 'USER', true, 26),
                                                                                               ('9b8c7d6e-5f4b-4c3a-9f2e-3a2b1c0d9e8f', 'Anna', 'Muster', 'anna@mail.com', '$2a$10$sypmy5Giz7FGAH.Hm7emXux0OGdjc2OpSy4shaSu4LcyfafijoJF6', 'USER', false, 25),
                                                                                               ('a4b3c2d1-6e5f-4d3c-8f1e-4b3a2c1d0e9f', 'Peter', 'Schneider', 'peter@mail.com', '$2a$10$sypmy5Giz7FGAH.Hm7emXux0OGdjc2OpSy4shaSu4LcyfafijoJF6', 'USER', false, 30),
                                                                                               ('b5c4d3e2-7f6a-5e4d-9a2f-5c4b3d2e1f0a', 'Lena', 'Meier', 'lena@mail.com', '$2a$10$sypmy5Giz7FGAH.Hm7emXux0OGdjc2OpSy4shaSu4LcyfafijoJF6', 'ADMIN', false, 40),
                                                                                               ('c6d5e4f3-8a7b-6f5e-a3b0-6d5c4e3f2a1b', 'Max', 'Huber', 'max@mail.com', '$2a$10$sypmy5Giz7FGAH.Hm7emXux0OGdjc2OpSy4shaSu4LcyfafijoJF6', 'USER', true, 16),
                                                                                               ('d7e6f5a4-9b8c-7a6f-b4c1-7e6d5f4a3b2c', 'Sophie', 'Weber', 'sophie@mail.com', '$2a$10$sypmy5Giz7FGAH.Hm7emXux0OGdjc2OpSy4shaSu4LcyfafijoJF6', 'USER', false, 22);


-- Create Cars

INSERT INTO blj.car (id, brand, model, required_age, maximum_rental_period_in_days, category, release_date, price_per_day, id_car_garage) VALUES
                                                                                                                                              ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Toyota', 'Corolla', 21, 30, 'SEDAN', '2020-05-15', 45.50, 1),
                                                                                                                                              ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Honda', 'CR-V', 25, 60, 'SUV', '2022-03-01', 65.00, 2),
                                                                                                                                              ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'Porsche', '911 Carrera', 28, 7, 'SPORT', '2023-10-20', 199.99, 1),
                                                                                                                                              ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Ford', 'F-150', 25, 90, 'TRUCK', '2019-11-05', 85.75, 2),
                                                                                                                                              ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15', 'BMW', '4 Series', 23, 45, 'COUPE', '2021-08-10', 78.50, 1),
                                                                                                                                              ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Audi', 'A4 Avant', 21, 30, 'COMBI', '2022-01-25', 55.90, 2),
                                                                                                                                              ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a17', 'Tesla', 'Model 3', 21, 30, 'SEDAN', '2024-04-01', 60.00, 1),
                                                                                                                                              ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Subaru', 'Outback', 21, 60, 'COMBI', '2023-07-12', 58.50, 2);

-- Create Contracts
INSERT INTO blj.loan_contract VALUES
    ('2026-04-30', '2026-03-01', 1,'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13',
     '34cd8e11-5cf6-4bd6-9a40-f33e382c6f70',
     '25ab0237-47e5-46be-8fe4-24d7aa90c288', 'OPEN');

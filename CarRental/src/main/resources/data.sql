
SET search_path To blj;

-- AI

-- Create Libraries
insert into blj.car_garage VALUES (1);
insert into blj.car_garage VALUES (2);

-- Create People
INSERT INTO blj.person VALUES
     ('1992-02-25', false, 1,
      '2c54f6b3-2924-40e3-9f24-8b8e8cd677ec', 'John', 'Smith');

INSERT INTO blj.person VALUES
    ('2008-03-09', false, 1, '34cd8e11-5cf6-4bd6-9a40-f33e382c6f70',
      'Max', 'Muster');

INSERT INTO blj.person VALUES
    ('2002-04-05', false,2,  '42de376f-0335-4dd9-af04-eed6e8fb2f31',
     'Tobi', 'Oliver');

INSERT INTO blj.person VALUES
    ('2002-02-05', true, 1,'72291937-2e6b-40d7-993f-29e6c8a5f285',
      'Jan', 'Jansson');

INSERT INTO blj.person VALUES
    ('2001-09-11', true, 2, '53819c97-672b-423e-9b13-c2b5717a79e4',
      'Ahmed', 'Ali');


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



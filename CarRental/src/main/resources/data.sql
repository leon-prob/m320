/*
SET search_path To blj;

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

-- Create Media

INSERT INTO blj.book VALUES
  (300,false, true,  43,
   '2010-02-23', 12, 1, '18b12fb2-1aa2-409d-b155-7e876e8f9ba8', 'Joel',
   'It is a murder story' ,'THRILLER','The Knife');

INSERT INTO blj.cd VALUES
    (true, 72.5, 18, '2006-01-12', 10,
     1, '16ba7cff-280c-4047-b016-950df8f6084b', 'IGOR', 'The Creator',
     'eyerything', 'HIPHOP','The world');

INSERT INTO blj.game VALUES
    (false, false, 140, '1992-07-30', 7,
     128, 2,'e9cec32b-3bdc-46c8-89ff-c3b735f2bb2d', 'Steve Jobs',
     'Ohhh iPhone', 'MUSIC', 'iPhone 15 Pro Max');

INSERT INTO blj.game VALUES
    (true, true,150,'2014-04-26', 10, 2.4,
     2,'523afcde-ca93-4395-97d4-211b09c11b74', 'Money Maker',
     'Angry kid playing Fortnite', 'SHOOTER', 'The money maker saga');

-- Create Contracts
INSERT INTO blj.loan_contract VALUES
  ('2025-04-30', '2025-03-01', 1,'2c54f6b3-2924-40e3-9f24-8b8e8cd677ec',
   '523afcde-ca93-4395-97d4-211b09c11b74',
   '25ab0237-47e5-46be-8fe4-24d7aa90c288', 'OPEN');


 */
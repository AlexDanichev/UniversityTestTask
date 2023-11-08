-- Insert departments if they do not exist
INSERT INTO department (name)
SELECT * FROM (SELECT 'Computer Science') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM department WHERE name = 'Computer Science'
    ) LIMIT 1;
SET @comp_sci_id = (SELECT id FROM department WHERE name = 'Computer Science');

INSERT INTO department (name)
SELECT * FROM (SELECT 'Supernatural') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM department WHERE name = 'Supernatural'
    ) LIMIT 1;
SET @supernatural_id = (SELECT id FROM department WHERE name = 'Supernatural');

INSERT INTO department (name)
SELECT * FROM (SELECT 'Mathematics') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM department WHERE name = 'Mathematics'
    ) LIMIT 1;
SET @math_id = (SELECT id FROM department WHERE name = 'Mathematics');

-- Insert lecturers if they do not exist and capture their IDs
-- Note: Replace '...' with actual values for new lecturers
INSERT INTO lector (name, surname, degree, salary)
SELECT * FROM (SELECT 'John', 'Doe', 'PROFESSOR', 70000) AS tmp
WHERE NOT EXISTS (
        SELECT 1 FROM lector WHERE name = 'John' AND surname = 'Doe'
    );
SET @john_doe_id = (SELECT id FROM lector WHERE name = 'John' AND surname = 'Doe');

-- Insert Jane Smith
INSERT INTO lector (name, surname, degree, salary)
SELECT * FROM (SELECT 'Jane', 'Smith', 'ASSOCIATE_PROFESSOR', 60000) AS tmp
WHERE NOT EXISTS (
        SELECT 1 FROM lector WHERE name = 'Jane' AND surname = 'Smith'
    );
SET @jane_smith_id = (SELECT id FROM lector WHERE name = 'Jane' AND surname = 'Smith');

-- Insert Emily Johnson
INSERT INTO lector (name, surname, degree, salary)
SELECT * FROM (SELECT 'Emily', 'Johnson', 'ASSISTANT', 50000) AS tmp
WHERE NOT EXISTS (
        SELECT 1 FROM lector WHERE name = 'Emily' AND surname = 'Johnson'
    );
SET @emily_johnson_id = (SELECT id FROM lector WHERE name = 'Emily' AND surname = 'Johnson');

-- Insert Michael Williams
INSERT INTO lector (name, surname, degree, salary)
SELECT * FROM (SELECT 'Michael', 'Williams', 'ASSISTANT', 52000) AS tmp
WHERE NOT EXISTS (
        SELECT 1 FROM lector WHERE name = 'Michael' AND surname = 'Williams'
    );
SET @michael_williams_id = (SELECT id FROM lector WHERE name = 'Michael' AND surname = 'Williams');

-- Insert David Brown
INSERT INTO lector (name, surname, degree, salary)
SELECT * FROM (SELECT 'David', 'Brown', 'ASSOCIATE_PROFESSOR', 61000) AS tmp
WHERE NOT EXISTS (
        SELECT 1 FROM lector WHERE name = 'David' AND surname = 'Brown'
    );
SET @david_brown_id = (SELECT id FROM lector WHERE name = 'David' AND surname = 'Brown');

-- Insert Lisa Davis
INSERT INTO lector (name, surname, degree, salary)
SELECT * FROM (SELECT 'Lisa', 'Davis', 'ASSISTANT', 53000) AS tmp
WHERE NOT EXISTS (
        SELECT 1 FROM lector WHERE name = 'Lisa' AND surname = 'Davis'
    );
SET @lisa_davis_id = (SELECT id FROM lector WHERE name = 'Lisa' AND surname = 'Davis');

-- Insert Mark Miller
INSERT INTO lector (name, surname, degree, salary)
SELECT * FROM (SELECT 'Mark', 'Miller', 'PROFESSOR', 72000) AS tmp
WHERE NOT EXISTS (
        SELECT 1 FROM lector WHERE name = 'Mark' AND surname = 'Miller'
    );
SET @mark_miller_id = (SELECT id FROM lector WHERE name = 'Mark' AND surname = 'Miller');

-- Insert Patricia Wilson
INSERT INTO lector (name, surname, degree, salary)
SELECT * FROM (SELECT 'Patricia', 'Wilson', 'PROFESSOR', 71000) AS tmp
WHERE NOT EXISTS (
        SELECT 1 FROM lector WHERE name = 'Patricia' AND surname = 'Wilson'
    );
SET @patricia_wilson_id = (SELECT id FROM lector WHERE name = 'Patricia' AND surname = 'Wilson');

-- Insert Jennifer Moore
INSERT INTO lector (name, surname, degree, salary)
SELECT * FROM (SELECT 'Jennifer', 'Moore', 'ASSISTANT', 54000) AS tmp
WHERE NOT EXISTS (
        SELECT 1 FROM lector WHERE name = 'Jennifer' AND surname = 'Moore'
    );
SET @jennifer_moore_id = (SELECT id FROM lector WHERE name = 'Jennifer' AND surname = 'Moore');

-- Insert Charles Taylor
INSERT INTO lector (name, surname, degree, salary)
SELECT * FROM (SELECT 'Charles', 'Taylor', 'ASSOCIATE_PROFESSOR', 62000) AS tmp
WHERE NOT EXISTS (
        SELECT 1 FROM lector WHERE name = 'Charles' AND surname = 'Taylor'
    );
SET @charles_taylor_id = (SELECT id FROM lector WHERE name = 'Charles' AND surname = 'Taylor');

-- Associate new lecturers with the 'Computer Science' department
INSERT IGNORE INTO department_lector (department_id, lector_id) VALUES
(@comp_sci_id, @emily_johnson_id),
(@comp_sci_id, @michael_williams_id),
(@comp_sci_id, @david_brown_id),
(@comp_sci_id, @lisa_davis_id),
(@comp_sci_id, @mark_miller_id);

-- Associate new lecturers with the 'Supernatural' department
INSERT IGNORE INTO department_lector (department_id, lector_id) VALUES
(@supernatural_id, @patricia_wilson_id),
(@supernatural_id, @jennifer_moore_id),
(@supernatural_id, @charles_taylor_id);

-- Since 'Mathematics' should have 2 from 'Computer Science' and 2 from 'Supernatural', let's use some of those we have IDs for
INSERT IGNORE INTO department_lector (department_id, lector_id) VALUES
(@math_id, @emily_johnson_id),
(@math_id, @michael_williams_id),
(@math_id, @patricia_wilson_id),
(@math_id, @jennifer_moore_id);

-- Set John Doe as head of 'Computer Science' and 'Supernatural' departments if not already set
UPDATE department SET head_id = @john_doe_id WHERE id IN (@comp_sci_id, @supernatural_id) AND head_id IS NULL;

-- Set a different lecturer as head of the 'Mathematics' department if not already set
-- For this example, we use Jane Smith, but replace with the correct lecturer ID as needed
UPDATE department SET head_id = @jane_smith_id WHERE id = @math_id AND head_id IS NULL;
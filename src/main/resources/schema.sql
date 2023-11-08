CREATE TABLE IF NOT EXISTS lector (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    degree ENUM('ASSISTANT', 'ASSOCIATE_PROFESSOR', 'PROFESSOR') NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
    );

CREATE TABLE IF NOT EXISTS department (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          name VARCHAR(255) NOT NULL,
    head_id BIGINT,
    CONSTRAINT fk_head FOREIGN KEY (head_id) REFERENCES lector (id)
    );

CREATE TABLE IF NOT EXISTS department_lector (
                                                 department_id BIGINT NOT NULL,
                                                 lector_id BIGINT NOT NULL,
                                                 PRIMARY KEY (department_id, lector_id),
    CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES department (id),
    CONSTRAINT fk_lector FOREIGN KEY (lector_id) REFERENCES lector (id)
    );

DROP TABLE IF EXISTS student;

CREATE TABLE student (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO student (name, email) VALUES ('tom', 'tom@yahoo.com')
INSERT INTO student (name, email) VALUES ('jack', 'jack@yahoo.com')
INSERT INTO student (name, email) VALUES ('helen', 'helen@yahoo.com')
DROP TABLE IF EXISTS T_USERS;

CREATE TABLE T_USERS
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name  VARCHAR(250) NOT NULL,
    password   VARCHAR(250) NOT NULL,
    role       VARCHAR(250) NOT NULL,
    username   VARCHAR(250) NOT  NULL,
    UNIQUE (username)
);

DROP TABLE IF EXISTS T_STUDENTS;

CREATE TABLE T_STUDENTS
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(250) NOT NULL,
    first_name VARCHAR(250),
    sur_name   VARCHAR(250),
    UNIQUE (email)
);

DROP TABLE IF EXISTS T_NOTES;

CREATE TABLE T_NOTES
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    date_of_creation DATE,
    text       VARCHAR(250) NOT NULL,
    student_id int,
    FOREIGN KEY (student_id) REFERENCES T_STUDENTS (id)
);
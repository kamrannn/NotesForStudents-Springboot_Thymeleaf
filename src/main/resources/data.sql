INSERT INTO T_USERS (FIRST_NAME, LAST_NAME, PASSWORD, ROLE, USERNAME) VALUES ('Sully', 'James', '$2a$10$uI1MYN81H/3Gf8yopA0RSedioJ8uwZOciCk5zC4toKNJP6KiWG3BS', 'ROLE_MENTOR', 'user1');

INSERT INTO T_USERS (FIRST_NAME, LAST_NAME, PASSWORD, ROLE, USERNAME) VALUES ('Kamran', 'Abbasi', '$2a$10$uI1MYN81H/3Gf8yopA0RSedioJ8uwZOciCk5zC4toKNJP6KiWG3BS', 'ROLE_REVIEWER', 'user2');

INSERT INTO T_STUDENTS(EMAIL, FIRST_NAME, SUR_NAME) VALUES ( 'kamran@gmail.com','Kamran','Abbasi');

INSERT INTO T_STUDENTS(EMAIL, FIRST_NAME, SUR_NAME) VALUES ( 'sully@gmail.com','Sully','James');

INSERT INTO T_STUDENTS(EMAIL, FIRST_NAME, SUR_NAME) VALUES ( 'muneeza@gmail.com','Muneeza','Kamran');

INSERT INTO T_NOTES (DATE_OF_CREATION, TEXT, STUDENT_ID) VALUES ( '2021-07-12', 'Note related to kamran', 1 );

INSERT INTO T_NOTES (DATE_OF_CREATION, TEXT, STUDENT_ID) VALUES ( '2021-07-12', 'Note related to sully', 2 );

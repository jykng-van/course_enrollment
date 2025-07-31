INSERT INTO teachers (`name`) VALUES ('John');
INSERT INTO teachers (`name`) VALUES ('Francine');
INSERT INTO teachers (`name`) VALUES ('Alejandro');

INSERT INTO students (`name`) VALUES ('Bob');
INSERT INTO students (`name`) VALUES ('Sara');
INSERT INTO students (`name`) VALUES ('Mimi');
INSERT INTO students (`name`) VALUES ('Hilda');
INSERT INTO students (`name`) VALUES ('Jasmine');
INSERT INTO students (`name`) VALUES ('Reza');
INSERT INTO students (`name`) VALUES ('Joe');
INSERT INTO students (`name`) VALUES ('Manuella');
INSERT INTO students (`name`) VALUES ('Ali');

INSERT INTO subjects (`name`) VALUES ('Math');
INSERT INTO subjects (`name`) VALUES ('Physics');
INSERT INTO subjects (`name`) VALUES ('Computer Science');
INSERT INTO subjects (`name`) VALUES ('English');
INSERT INTO subjects (`name`) VALUES ('French');
INSERT INTO subjects (`name`) VALUES ('Geology');

INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (1, '2024-02-01', '2024-06-01', 1);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (2, '2024-09-01', '2025-01-01', 2);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (3, '2024-03-15', '2024-07-15', 3);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (4, '2025-01-10', '2025-05-10', 1);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (5, '2024-05-01', '2024-09-01', 2);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (6, '2024-10-01', '2025-02-01', 3);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (1, '2025-03-01', '2025-07-01', 1);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (2, '2024-06-01', '2024-10-01', 2);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (3, '2025-04-01', '2025-08-01', 3);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (4, '2024-08-15', '2024-12-15', 1);

INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (1, '2025-09-01', '2025-12-01', 2);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (1, '2025-10-15', '2026-01-15', 3);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (2, '2026-03-01', '2026-06-01', 1);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (2, '2026-04-15', '2026-07-15', 2);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (3, '2026-06-01', '2026-09-01', 3);
INSERT INTO courses (`subject_id`, `start_date`, `end_date`, `teacher_id`) VALUES (3, '2026-07-15', '2026-10-15', 1);


INSERT INTO student_courses(`student_id`, `course_id`, `grade`) VALUES (1, 1, 0.0);
INSERT INTO student_courses(`student_id`, `course_id`, `grade`) VALUES (2, 2, 4.0);      -- A
INSERT INTO student_courses(`student_id`, `course_id`, `grade`) VALUES (3, 3, 3.3);      -- B+
INSERT INTO student_courses(`student_id`, `course_id`, `grade`) VALUES (4, 4, 0.0);      -- F
INSERT INTO student_courses(`student_id`, `course_id`, `grade`) VALUES (5, 5, 2.7);      -- B-
INSERT INTO student_courses(`student_id`, `course_id`, `grade`) VALUES (6, 6, 3.7);      -- A-
INSERT INTO student_courses(`student_id`, `course_id`, `grade`) VALUES (7, 7, 0.0);      -- F
INSERT INTO student_courses(`student_id`, `course_id`, `grade`) VALUES (8, 8, 4.33);     -- A+
INSERT INTO student_courses(`student_id`, `course_id`, `grade`) VALUES (9, 9, 0.0);      -- F
INSERT INTO student_courses(`student_id`, `course_id`, `grade`) VALUES (1, 10, 0.0);     -- F
INSERT INTO student_courses(`student_id`, `course_id`, `grade`) VALUES (2, 3, 3.0);
INSERT INTO student_courses(`student_id`, `course_id`, `grade`) VALUES (3, 5, 3.0);
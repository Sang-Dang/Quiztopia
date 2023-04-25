USE QuizDatabase

DELETE FROM [results];
DELETE FROM [answers];
DELETE FROM [questions];
DELETE FROM [quizzes];
DELETE FROM [users];

-- INSERT INTO USERS
INSERT INTO [users]
    (id, username, password, email, role)
VALUES
    ('765DCFE9-FC14-4B06-9B50-E3CD3CBFFE72', 'admin', '12345', 'admin@gmail.com', 'TS')
INSERT INTO [users]
    (id, username, password, email, role)
VALUES
    ('97CDC05C-1824-4149-A4DC-F9CE663C7EB6', 'user', '12345', 'user@gmail.com', 'US')

-- INSERT INTO QUIZZES
INSERT INTO [quizzes]
    (title, description, user_id, code, password)
VALUES
    ('Math', 'A quiz about maths', (SELECT id
        FROM users
        WHERE username = 'admin'), 'A1fr33', '');
INSERT INTO [quizzes]
    (title, description, user_id, code, password)
VALUES
    ('History', 'A quiz about history', (SELECT id
        FROM users
        WHERE username = 'admin'), 'Pore12', '12345');
INSERT INTO [quizzes]
    (title, description, user_id, code, password)
VALUES
    ('Geography', 'A quiz about geography', (SELECT id
        FROM users
        WHERE username = 'user'), '24J22A', '');
INSERT INTO [quizzes]
    (title, description, user_id, code, password)
VALUES
    ('English', 'A quiz about english', (SELECT id
        FROM users
        WHERE username = 'user'), 'fdWEWS', '');
INSERT INTO [quizzes]
    (title, description, user_id, code, password)
VALUES
    ('Programming', 'A quiz about programming', (SELECT id
        FROM users
        WHERE username = 'admin'), '321JFD', '');

-- INSERT INTO QUESTIONS
INSERT INTO [questions]
    (quiz_id, question)
VALUES
    ((SELECT id
        FROM quizzes
        WHERE title = 'Math'), 'What is 2 + 2?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'Math'), 'What is 3 + 3?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'Math'), 'What is 4 + 4?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'History'), 'When was the first world war?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'History'), 'When was the second world war?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'History'), 'When was the third world war?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'Geography'), 'What is the capital of France?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'Geography'), 'What is the capital of Germany?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'Geography'), 'What is the capital of Italy?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'English'), 'What is the past tense of the verb "to be"?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'English'), 'What is the past tense of the verb "to have"?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'English'), 'What is the past tense of the verb "to do"?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'Programming'), 'What is the name of the programming language used in this project?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'Programming'), 'Which programming language is object oriented?'),
    ((SELECT id
        FROM quizzes
        WHERE title = 'Programming'), 'Which programming language is not object oriented?');

-- INSERT INTO ANSWERS
INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'What is 2 + 2?'), '4', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'What is 2 + 2?'), '5', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is 2 + 2?'), '8', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is 2 + 2?'), '1', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'What is 3 + 3?'), '6', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'What is 3 + 3?'), '9', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is 3 + 3?'), '12', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is 3 + 3?'), '3', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'What is 4 + 4?'), '8', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'What is 4 + 4?'), '12', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is 4 + 4?'), '16', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is 4 + 4?'), '4', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'When was the first world war?'), '1914', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'When was the first world war?'), '1915', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'When was the first world war?'), '1916', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'When was the first world war?'), '1917', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'When was the second world war?'), '1939', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'When was the second world war?'), '1940', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'When was the second world war?'), '1941', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'When was the second world war?'), '1942', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'When was the third world war?'), '1945', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'When was the third world war?'), '1946', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'When was the third world war?'), '1947', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'When was the third world war?'), '1948', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'What is the capital of France?'), 'Paris', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the capital of France?'), 'Lyon', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the capital of France?'), 'Marseille', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the capital of France?'), 'Toulouse', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'What is the capital of Germany?'), 'Berlin', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the capital of Germany?'), 'Hamburg', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the capital of Germany?'), 'Munich', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the capital of Germany?'), 'Cologne', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'What is the capital of Italy?'), 'Rome', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the capital of Italy?'), 'Milan', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the capital of Italy?'), 'Naples', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the capital of Italy?'), 'Turin', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'What is the past tense of the verb "to have"?'), 'had', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the past tense of the verb "to have"?'), 'have', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the past tense of the verb "to have"?'), 'has', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the past tense of the verb "to have"?'), 'having', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'What is the past tense of the verb "to be"?'), 'was', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the past tense of the verb "to be"?'), 'were', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the past tense of the verb "to be"?'), 'be', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the past tense of the verb "to be"?'), 'being', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'What is the past tense of the verb "to do"?'), 'did', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the past tense of the verb "to do"?'), 'do', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the past tense of the verb "to do"?'), 'does', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the past tense of the verb "to do"?'), 'doing', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'What is the name of the programming language used in this project?'), 'PHP', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the name of the programming language used in this project?'), 'C#', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the name of the programming language used in this project?'), 'Java', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'What is the name of the programming language used in this project?'), 'Python', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'Which programming language is object oriented?'), 'C++', 1),
    ((SELECT id
        FROM questions
        WHERE question = 'Which programming language is object oriented?'), 'C', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'Which programming language is object oriented?'), 'Ruby', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'Which programming language is object oriented?'), 'Pascal', 0);

INSERT INTO [answers]
    (question_id, answer, is_correct)
VALUES
    ((SELECT id
        FROM questions
        WHERE question = 'Which programming language is not object oriented?'), 'C++', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'Which programming language is not object oriented?'), 'Java', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'Which programming language is not object oriented?'), 'Javascript', 0),
    ((SELECT id
        FROM questions
        WHERE question = 'Which programming language is not object oriented?'), 'Pascal', 1);
DROP TABLE IF EXISTS [results]
DROP TABLE IF EXISTS [answers]
DROP TABLE IF EXISTS [questions]
DROP TABLE IF EXISTS [quiz_code]
DROP TABLE IF EXISTS [quizzes]
DROP TABLE IF EXISTS [users]

CREATE TABLE [users]
(
    [id] UNIQUEIDENTIFIER NOT NULL PRIMARY KEY DEFAULT NEWID(),
    [username] VARCHAR(50) NOT NULL UNIQUE,
    [password] VARCHAR(255) NOT NULL,
    [email] VARCHAR(50) NOT NULL UNIQUE,
    [role] VARCHAR(2) NOT NULL DEFAULT 'US'
)

CREATE TABLE [quizzes]
(
    [id] UNIQUEIDENTIFIER NOT NULL PRIMARY KEY DEFAULT NEWID(),
    [title] VARCHAR(50) NOT NULL UNIQUE,
    [description] VARChAR(255) NOT NULL,
    [created_at] DATETIME NOT NULL DEFAULT GETDATE(),
    [user_id] UNIQUEIDENTIFIER NOT NULL FOREIGN KEY REFERENCES users(id) ON DELETE CASCADE,
    [password] VARCHAR(255) NULL,
    [is_public] BIT NOT NULL DEFAULT 1,
    [code] VARCHAR(6) NULL
)

CREATE TABLE [questions]
(
    [id] UNIQUEIDENTIFIER NOT NULL PRIMARY KEY DEFAULT NEWID(),
    [quiz_id] UNIQUEIDENTIFIER NOT NULL FOREIGN KEY REFERENCES quizzes(id) ON DELETE CASCADE,
    [question] VARCHAR(255) NOT NULL,
)

CREATE TABLE [answers]
(
    [id] UNIQUEIDENTIFIER NOT NULL PRIMARY KEY DEFAULT NEWID(),
    [question_id] UNIQUEIDENTIFIER NOT NULL FOREIGN KEY REFERENCES questions(id) ON DELETE CASCADE,
    [answer] VARCHAR(255) NOT NULL,
    [is_correct] BIT NOT NULL DEFAULT 0
)

CREATE TABLE [results]
(
    [id] UNIQUEIDENTIFIER NOT NULL PRIMARY KEY DEFAULT NEWID(),
    [user_id] UNIQUEIDENTIFIER NOT NULL FOREIGN KEY REFERENCES users(id) ON DELETE CASCADE,
    [quiz_id] UNIQUEIDENTIFIER NOT NULL FOREIGN KEY REFERENCES quizzes(id),
    [score] INT NOT NULL CHECK (score >= 0),
    [completed_at] DATETIME NOT NULL DEFAULT GETDATE()
)
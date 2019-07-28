DROP TABLE if exists Enrollment;
DROP TABLE if exists Submission;
DROP TABLE if exists Grades;
DROP TABLE if exists Grader;
DROP TABLE if exists Assignment;
DROP TABLE if exists Course;
DROP TABLE if exists User;
DROP TABLE if exists AssignmentType;
create table if not exists Course(
    name VARCHAR(16),
    shortDescription VARCHAR(64),
    description VARCHAR(255),
    PRIMARY KEY (name));
create table if not exists User(
    name VARCHAR(32),
    email VARCHAR(255),
    first VARCHAR(255),
    last VARCHAR(255),
    password VARCHAR(255),
    role ENUM('ADMIN', 'STUDENT'),
    active TINYINT(1) DEFAULT 0,
    PRIMARY KEY (name));
create table if not exists Enrollment(
    user VARCHAR(32),
    course VARCHAR(16),
    section INT,
    year YEAR(4),
    quarter ENUM('Fall', 'Winter', 'Spring', 'Summer'),
    PRIMARY KEY (user, course, year, quarter),
    FOREIGN KEY (course) REFERENCES Course(name),
    FOREIGN KEY (user) REFERENCES User(name));
create table if not exists AssignmentType(
    id INT AUTO_INCREMENT,
    name VARCHAR(32),
    PRIMARY KEY (id));
create table if not exists Assignment(
    id INT AUTO_INCREMENT,
    course VARCHAR(16),
    name VARCHAR(255),
    type INT,
    open TIMESTAMP,
    due TIMESTAMP,
    instruction_link VARCHAR(255),
    instruction TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (course) REFERENCES Course(name),
    FOREIGN KEY (type) REFERENCES AssignmentType(id));
create table if not exists Submission(
    id INT AUTO_INCREMENT,
    assignment INT,
    user VARCHAR(32) NOT NULL, 
    submissionTime TIMESTAMP,
    status ENUM('Submitted', 'Grading', 'Graded') DEFAULT 'Submitted',
    score INT,
    filePath VARCHAR(255) NOT NULL,
    resultFile VARCHAR(255),
    result TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (assignment) REFERENCES Assignment(id),
    FOREIGN KEY (user) REFERENCES User(name));
create table if not exists Grader(
    id INT AUTO_INCREMENT NOT NULL,
    assignment INT NOT NULL,
    seq INT DEFAULT 1 NOT NULL,
    filePath VARCHAR(255) NOT NULL,
    program VARCHAR(255),
    args VARCHAR(255),
    copy TINYINT(1) DEFAULT 0,
    type ENUM("TEST", "LINT") DEFAULT "TEST",
    PRIMARY KEY (id),
    FOREIGN KEY (assignment) REFERENCES Assignment(id));
create table if not exists Grade (
    user VARCHAR(32) NOT NULL,
    assignment INT NOT NULL,
    course VARCHAR(16) NOT NULL,
    grade INT,
    PRIMARY KEY (user, assignment),
    FOREIGN KEY (assignment) REFERENCES Assignment(id),
    FOREIGN KEY (user) REFERENCES User(name),
    FOREIGN KEY (course) REFERENCES Course(name)
);


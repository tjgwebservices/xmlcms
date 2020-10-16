PRAGMA foreign_keys;
PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS AdministratorGroup (
id integer PRIMARY KEY,
groupName TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Administrator (
id integer PRIMARY KEY,
administratorName text NOT NULL,
administratorGroupId integer NOT NULL,
FOREIGN KEY (administratorGroupId)
REFERENCES AdministratorGroup (id)
ON UPDATE CASCADE
ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Article (
id integer PRIMARY KEY,
author text NOT NULL,
authorDate text NOT NULL,
title text NOT NULL,
description text NOT NULL,
content text NOT NULL);

CREATE TABLE IF NOT EXISTS Artist (
id INTEGER PRIMARY KEY,
artistName TEXT
);

CREATE TABLE IF NOT EXISTS Subscription (
id integer PRIMARY KEY,
subscriptionPlan text NOT NULL,
publisher text NOT NULL,
topic text NOT NULL);

CREATE TABLE IF NOT EXISTS Journal (
id integer PRIMARY KEY,
journal text NOT NULL);

CREATE TABLE IF NOT EXISTS Subscriber (
id integer PRIMARY KEY,
subscriber text NOT NULL);

CREATE TABLE IF NOT EXISTS Course
( id INTEGER PRIMARY KEY AUTOINCREMENT,
courseName VARCHAR);

CREATE TABLE IF NOT EXISTS Student
( id INTEGER PRIMARY KEY AUTOINCREMENT,
lastName VARCHAR NOT NULL,
firstName VARCHAR,
courseId INTEGER,
CONSTRAINT fk_Course
FOREIGN KEY (courseId)
REFERENCES Course(id)
ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Lecture(
instructor TEXT,
lectureName TEXT,
lecturePoster BINARY,
PRIMARY KEY(instructor,lectureName)
);

CREATE TABLE IF NOT EXISTS LectureNote(
id INTEGER,
noteInstructor TEXT,
noteLecture TEXT,
noteText TEXT,
FOREIGN KEY(noteInstructor, noteLecture) 
REFERENCES Lecture(instructor, lectureName)
);

CREATE TABLE IF NOT EXISTS Lecturer(
id INTEGER PRIMARY KEY,
lecturerName TEXT
);

CREATE TABLE IF NOT EXISTS School(
id INTEGER PRIMARY KEY,
schoolName TEXT,
schoolLecturer INTEGER 
REFERENCES Lecturer(id) 
ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS HrGroup (
id integer PRIMARY KEY,
groupName TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS HrClient (
id integer PRIMARY KEY,
clientFirstName text NOT NULL,
clientLastName text NOT NULL,
clientSpecialty text NOT NULL,
clientContact text NOT NULL,
hrGroupId integer NOT NULL,
FOREIGN KEY (hrGroupId)
REFERENCES HrGroup (id)
ON UPDATE CASCADE
ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS HrEmployer (
id integer PRIMARY KEY,
employerName text NOT NULL,
employerContact text NOT NULL,
employerContactType text NOT NULL,
employerContactInfo text NOT NULL,
hrGroupId integer NOT NULL,
FOREIGN KEY (hrGroupId)
REFERENCES HrGroup (id)
ON UPDATE CASCADE
ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Researcher (
id integer PRIMARY KEY,
researcherFirstName TEXT NOT NULL,
researcherLastName TEXT NOT NULL,
researcherDegree TEXT NOT NULL,
researcherMajor TEXT NOT NULL,
researcherInstitution TEXT NOT NULL,
researcherSpecialty TEXT NOT NULL,
);

CREATE TABLE IF NOT EXISTS Topic (
id integer PRIMARY KEY,
topicName text NOT NULL,
topicSubject text NOT NULL,
topicDescription text NOT NULL,
researcherId integer NOT NULL,
FOREIGN KEY (researcherId)
REFERENCES Researcher (id)
ON UPDATE CASCADE
ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Project (
id integer PRIMARY KEY,
projectName text NOT NULL,
projectSubject text NOT NULL,
projectDescription text NOT NULL,
researcherId integer NOT NULL,
FOREIGN KEY (researcherId)
REFERENCES Researcher (id)
ON UPDATE CASCADE
ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Video(
artist TEXT,
videoName TEXT,
videoContent BINARY,
PRIMARY KEY(artist,videoName)
);

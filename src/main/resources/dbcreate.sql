PRAGMA foreign_keys;
PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS Article (
id integer PRIMARY KEY,
author text NOT NULL,
authorDate text NOT NULL,
title text NOT NULL,
description text NOT NULL,
content text NOT NULL);

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

CREATE TABLE Course
( id INTEGER PRIMARY KEY AUTOINCREMENT,
courseName VARCHAR);

CREATE TABLE Student
( id INTEGER PRIMARY KEY AUTOINCREMENT,
lastName VARCHAR NOT NULL,
firstName VARCHAR,
courseId INTEGER,
CONSTRAINT fk_Course
FOREIGN KEY (courseId)
REFERENCES Course(id)
ON DELETE CASCADE
);

CREATE TABLE Lecture(
instructor TEXT,
lectureName TEXT,
lecturePoster BINARY,
PRIMARY KEY(instructor,lectureName)
);

CREATE TABLE LectureNote(
id INTEGER,
noteInstructor TEXT,
noteLecture TEXT,
noteText TEXT,
FOREIGN KEY(noteInstructor, noteLecture) 
REFERENCES Lecture(instructor, lectureName)
);

CREATE TABLE Lecturer(
id INTEGER PRIMARY KEY,
lecturerName TEXT
);

CREATE TABLE School(
id INTEGER PRIMARY KEY,
schoolName TEXT,
schoolLecturer INTEGER 
REFERENCES Lecturer(id) 
ON UPDATE CASCADE
);

CREATE TABLE AdministratorGroup (
id integer PRIMARY KEY,
groupName TEXT NOT NULL
);

CREATE TABLE Administrator (
id integer PRIMARY KEY,
administratorName text NOT NULL,
administratorGroupId integer NOT NULL,
FOREIGN KEY (administratorGroupId)
REFERENCES AdministratorGroup (id)
ON UPDATE CASCADE
ON DELETE CASCADE
);


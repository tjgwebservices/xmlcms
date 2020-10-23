package com.tjgwebservices.tjgxmlcms.dbo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAdmin {
    
    final private static String CONNECTIONSTRING = "jdbc:sqlite:memory:articledb?cache=shared";
    
    public static void startDatabase(){
        String sql = "ATTACH DATABASE 'articledb.db' AS articledb;";
        try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        StringBuilder query = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("src/main/resources/dbcreate.sql")
            );
            while (bufferedReader.readLine()!=null) {
                query.append(bufferedReader.readLine());
                
            }
            Connection conn = DriverManager.getConnection(CONNECTIONSTRING);
                Statement stmt = conn.createStatement();
            stmt.execute(query.toString());
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        runSQLQuery("CREATE TABLE IF NOT EXISTS Article (\n"
                + " id integer PRIMARY KEY,\n"
                + " author text NOT NULL,\n"
                + " authorDate text NOT NULL,\n"
                + " title text NOT NULL,\n"
                + " description text NOT NULL,\n"
                + " content text NOT NULL);");
        runSQLQuery("CREATE TABLE IF NOT EXISTS Review (\n"
                + " id integer PRIMARY KEY,\n"
                + " author text NOT NULL,\n"
                + " authorDate text NOT NULL,\n"
                + " title text NOT NULL,\n"
                + " description text NOT NULL,\n"
                + " content text NOT NULL);");
        runSQLQuery("CREATE TABLE IF NOT EXISTS AdministratorGroup (\n" +
                "id integer PRIMARY KEY,\n" +
                "groupName TEXT NOT NULL\n" +
                ");\n");
        runSQLQuery("CREATE TABLE IF NOT EXISTS Administrator (\n" +
                "id integer PRIMARY KEY,\n" +
                "administratorName text NOT NULL,\n" +
                "administratorGroupId integer NOT NULL,\n" +
                "FOREIGN KEY (administratorGroupId)\n" +
                "REFERENCES AdministratorGroup (id)\n" +
                "ON UPDATE CASCADE\n" +
                "ON DELETE CASCADE\n" +
                ");\n");       
        runSQLQuery("CREATE TABLE IF NOT EXISTS Artist(\n" +
                "id INTEGER PRIMARY KEY,\n" +
                "artistName TEXT\n" +
                ");");  
        runSQLQuery("CREATE TABLE IF NOT EXISTS Lecture(\n" +
                "instructor TEXT,\n" +
                "lectureName TEXT,\n" +
                "lecturePoster BINARY,\n" +
                "PRIMARY KEY(instructor,lectureName)\n" +
                ");\n");
        runSQLQuery("CREATE TABLE IF NOT EXISTS LectureNote(\n" +
                "id INTEGER,\n" +
                "noteInstructor TEXT,\n" +
                "noteLecture TEXT,\n" +
                "noteText TEXT,\n" +
                "FOREIGN KEY(noteInstructor, noteLecture) \n" +
                "REFERENCES Lecture(instructor, lectureName)\n" +
                ");\n");       
        runSQLQuery("CREATE TABLE IF NOT EXISTS Lecturer(\n" +
                "id INTEGER PRIMARY KEY,\n" +
                "lecturerName TEXT\n" +
                ");");       
        runSQLQuery("CREATE TABLE IF NOT EXISTS School(\n" +
                "id INTEGER PRIMARY KEY,\n" +
                "schoolName TEXT,\n" +
                "schoolLecturer INTEGER \n" +
                "REFERENCES Lecturer(id) \n" +
                "ON UPDATE CASCADE\n" +
                ");\n"); 
        runSQLQuery("CREATE TABLE IF NOT EXISTS Course\n" +
                "( id INTEGER PRIMARY KEY,\n" +
                "courseName text NOT NULL);"); 
        runSQLQuery("CREATE TABLE IF NOT EXISTS Journal (\n" +
                "id integer PRIMARY KEY,\n" +
                "journal text NOT NULL);\n"); 
        runSQLQuery("CREATE TABLE IF NOT EXISTS Subscriber (\n" +
                "id integer PRIMARY KEY,\n" +
                "subscriber text NOT NULL);"); 
        runSQLQuery("CREATE TABLE IF NOT EXISTS Subscription (\n" +
                "id integer PRIMARY KEY,\n" +
                "subscriptionPlan text NOT NULL,\n" +
                "publisher text NOT NULL,\n" +
                "topic text NOT NULL);"); 
        runSQLQuery("CREATE TABLE IF NOT EXISTS Student\n" +
                "( id INTEGER PRIMARY KEY,\n" +
                "lastName VARCHAR NOT NULL,\n" +
                "firstName VARCHAR,\n" +
                "courseId INTEGER,\n" +
                "CONSTRAINT fk_Course\n" +
                "FOREIGN KEY (courseId)\n" +
                "REFERENCES Course(id)\n" +
                "ON DELETE CASCADE\n" +
                ");");         
        runSQLQuery("CREATE TABLE IF NOT EXISTS HrGroup (\n" +
                "id integer PRIMARY KEY,\n" +
                "groupName TEXT NOT NULL\n" +
                ");\n");         
        runSQLQuery("CREATE TABLE IF NOT EXISTS HrClient (\n" +
                "id integer PRIMARY KEY,\n" +
                "clientFirstName text NOT NULL,\n" +
                "clientLastName text NOT NULL,\n" +
                "clientSpecialty text NOT NULL,\n" +
                "clientContact text NOT NULL,\n" +
                "hrGroupId integer NOT NULL,\n" +
                "FOREIGN KEY (hrGroupId)\n" +
                "REFERENCES HrGroup (id)\n" +
                "ON UPDATE CASCADE\n" +
                "ON DELETE CASCADE);");         
        runSQLQuery("CREATE TABLE IF NOT EXISTS HrEmployer (\n" +
                "id integer PRIMARY KEY,\n" +
                "employerName text NOT NULL,\n" +
                "employerContact text NOT NULL,\n" +
                "employerContactType text NOT NULL,\n" +
                "employerContactInfo text NOT NULL,\n" +
                "hrGroupId integer NOT NULL,\n" +
                "FOREIGN KEY (hrGroupId)\n" +
                "REFERENCES HrGroup (id)\n" +
                "ON UPDATE CASCADE\n" +
                "ON DELETE CASCADE\n" +
                ");\n");         
        runSQLQuery("CREATE TABLE IF NOT EXISTS Researcher (\n" +
                "id integer PRIMARY KEY,\n" +
                "researcherFirstName TEXT NOT NULL,\n" +
                "researcherLastName TEXT NOT NULL,\n" +
                "researcherDegree TEXT NOT NULL,\n" +
                "researcherMajor TEXT NOT NULL,\n" +
                "researcherInstitution TEXT NOT NULL,\n" +
                "researcherSpecialty TEXT NOT NULL\n" +
                ");\n");  
        runSQLQuery("CREATE TABLE IF NOT EXISTS Topic (\n" +
                "id integer PRIMARY KEY,\n" +
                "topicName text NOT NULL,\n" +
                "topicSubject text NOT NULL,\n" +
                "topicDescription text NOT NULL,\n" +
                "researcherId integer NOT NULL,\n" +
                "FOREIGN KEY (researcherId)\n" +
                "REFERENCES Researcher (id)\n" +
                "ON UPDATE CASCADE\n" +
                "ON DELETE CASCADE\n" +
                ");");  
        runSQLQuery("CREATE TABLE IF NOT EXISTS Project (\n" +
                "id integer PRIMARY KEY,\n" +
                "projectName text NOT NULL,\n" +
                "projectSubject text NOT NULL,\n" +
                "projectDescription text NOT NULL,\n" +
                "researcherId integer NOT NULL,\n" +
                "FOREIGN KEY (researcherId)\n" +
                "REFERENCES Researcher (id)\n" +
                "ON UPDATE CASCADE\n" +
                "ON DELETE CASCADE\n" +
                ");\n" +
                "\n");  
        runSQLQuery("CREATE TABLE IF NOT EXISTS Video(\n" +
                "id integer PRIMARY KEY,\n" +
                "artist TEXT,\n" +
                "videoName TEXT,\n" +
                "videoPath TEXT);");
        runSQLQuery("CREATE TABLE IF NOT EXISTS Event (\n" +
                "id integer PRIMARY KEY,\n" +
                "title text NOT NULL,\n" +
                "startDate text NOT NULL,\n" +
                "endDate text NOT NULL,\n" +
                "location text NOT NULL,\n" +
                "description text NOT NULL);");
        runSQLQuery("CREATE TABLE IF NOT EXISTS EventAdministrator (\n" +
                "id integer PRIMARY KEY,\n" +
                "administratorName text NOT NULL,\n" +
                "title text NOT NULL,\n" +
                "subTitle text NOT NULL,\n" +
                "contactInfo text NOT NULL,\n" +
                "eventId integer,\n" +
                "CONSTRAINT fk_Event\n" +
                "FOREIGN KEY (eventId)\n" +
                "REFERENCES Event(id)\n" +
                "ON DELETE CASCADE);");
        runSQLQuery("CREATE TABLE IF NOT EXISTS EventAdvertisement (\n" +
                "id integer PRIMARY KEY,\n" +
                "title text NOT NULL,\n" +
                "subTitle text NOT NULL,\n" +
                "adImagePath text NOT NULL,\n" +
                "contactInfo text NOT NULL,\n" +
                "eventId integer,\n" +
                "CONSTRAINT fk_Event\n" +
                "FOREIGN KEY (eventId)\n" +
                "REFERENCES Event(id)\n" +
                "ON DELETE CASCADE);");
        runSQLQuery("CREATE TABLE IF NOT EXISTS ArtificialIntelligence(\n" +
                "id integer PRIMARY KEY,\n" +
                "title text NOT NULL,\n" +
                "description text NOT NULL,\n" +
                "algorithmPath text,\n" +
                "dataSourcePath text,\n" +
                "dataTargetPath text);");
        runSQLQuery("CREATE TABLE IF NOT EXISTS MachineLearning(\n" +
                "id integer PRIMARY KEY,\n" +
                "title text NOT NULL,\n" +
                "description text NOT NULL,\n" +
                "algorithmPath text,\n" +
                "dataSourcePath text,\n" +
                "dataTargetPath text);");
        runSQLQuery("Select * FROM LectureNote");
        runSQLQuery("Select * FROM Lecture");
    }
    
    
    private static void runSQLQuery(String sql){
        try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }        
    }
}

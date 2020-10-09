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
        runSQLQuery("CREATE TABLE AdministratorGroup (\n" +
                "id integer PRIMARY KEY,\n" +
                "groupName TEXT NOT NULL\n" +
                ");\n");
        runSQLQuery("CREATE TABLE Administrator (\n" +
                "id integer PRIMARY KEY,\n" +
                "administratorName text NOT NULL,\n" +
                "administratorGroupId integer NOT NULL,\n" +
                "FOREIGN KEY (administratorGroupId)\n" +
                "REFERENCES AdministratorGroup (id)\n" +
                "ON UPDATE CASCADE\n" +
                "ON DELETE CASCADE\n" +
                ");\n");       
        runSQLQuery("CREATE TABLE Lecture(\n" +
                "instructor TEXT,\n" +
                "lectureName TEXT,\n" +
                "lecturePoster BINARY,\n" +
                "PRIMARY KEY(instructor,lectureName)\n" +
                ");\n");
        runSQLQuery("CREATE TABLE LectureNote(\n" +
                "id INTEGER,\n" +
                "noteInstructor TEXT,\n" +
                "noteLecture TEXT,\n" +
                "noteText TEXT,\n" +
                "FOREIGN KEY(noteInstructor, noteLecture) \n" +
                "REFERENCES Lecture(instructor, lectureName)\n" +
                ");\n");       
        runSQLQuery("CREATE TABLE Lecturer(\n" +
                "id INTEGER PRIMARY KEY,\n" +
                "lecturerName TEXT\n" +
                ");");       
        runSQLQuery("CREATE TABLE School(\n" +
                "id INTEGER PRIMARY KEY,\n" +
                "schoolName TEXT,\n" +
                "schoolLecturer INTEGER \n" +
                "REFERENCES Lecturer(id) \n" +
                "ON UPDATE CASCADE\n" +
                ");\n"); 
        runSQLQuery("CREATE TABLE Course\n" +
                "( id INTEGER PRIMARY KEY,\n" +
                "courseName VARCHAR);"); 
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
        runSQLQuery("CREATE TABLE Student\n" +
                "( id INTEGER PRIMARY KEY,\n" +
                "lastName VARCHAR NOT NULL,\n" +
                "firstName VARCHAR,\n" +
                "courseId INTEGER,\n" +
                "CONSTRAINT fk_Course\n" +
                "FOREIGN KEY (courseId)\n" +
                "REFERENCES Course(id)\n" +
                "ON DELETE CASCADE\n" +
                ");");         
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

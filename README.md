# University Test Task Application

## Description
This is a Test Task Project for a university management system. 
It uses Spring Boot, Spring Shell, and JPA to provide a command-line application 
that manages departments, lectors, and their associations within a university setting.

## Prerequisites

-  JDK 11
-  Maven 3.x
-  MySQL Server


# Installation
## Setting Up the Database

Before you run the application, set up the MySQL database:

1. Install MySQL and create a database named 'university_db_2'.
2. Update the spring.datasource.username and spring.datasource.password 
   properties in application.properties to match your MySQL credentials.

## Application Setup
1. Clone the project repository.
2. Navigate to the project directory and build the application using Maven:
   mvn clean install
 
## Running the application
- Once the build is complete, you can run the application using:
  java -jar target/application-0.0.1-SNAPSHOT.jar

## Usage
Once the application is running, you can use Spring Shell commands to interact with the system. Examples of commands include:

- Who is head of department {department_name}
- Show {department_name} statistics
- Show the average salary for the department {department_name}
- Show count of employee for {department_name}
- Global search by {template}
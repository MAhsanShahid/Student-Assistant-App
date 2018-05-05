package com.student.studentassistantapp;

/**
 * Created by TALHA on 30.3.16.
 */
public class Student {
    String FirstName;
    String LastName;
    String Gender;
    String Email;
    String Password;


    public Student(String firstName, String lastName, String gender, String email, String password) {
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        Email = email;
        Password = password;
    }


    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }



};

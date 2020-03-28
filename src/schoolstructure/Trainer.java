/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolstructure;

import java.util.ArrayList;

import static java.util.stream.Collectors.joining;

/**
 * @author Nikos Syrios
 */
public class Trainer {

    private String firstName;
    private String lastName;
    private String subject;
    private ArrayList<Course> courses;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        if (this.courses == null) {
            this.courses = new ArrayList<Course>() {
                {
                    addAll(courses);
                }
            };
        } else {
            this.courses.addAll(courses);
        }
    }

    public void setCourses(Course course) {
        if (this.courses == null) {
            courses = new ArrayList<Course>() {
                {
                    add(course);
                }
            };
        } else {
            this.courses.add(course);
        }
    }

    public ArrayList<String> getCoursesTitles() {
        ArrayList<String> result = new ArrayList<>();
        for (Course course : courses) {
            result.add(course.getTitle());
        }
        return result;
    }

    @Override
    public String toString() {

        final String coursesString = getCoursesTitles().stream()
                .collect(joining(", "));

        return String.format("Trainer Name: %s %s\nSubject: %s\nCourses: %s",
                getFirstName(), getLastName(), getSubject(), coursesString);
    }

}

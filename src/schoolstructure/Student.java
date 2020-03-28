/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolstructure;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import static java.util.stream.Collectors.joining;

/**
 * @author Nikos Syrios
 */
public class Student {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.UK);

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private double tuitionFees;
    private ArrayList<Course> courses;

    public Student() {
        this.dateOfBirth = LocalDate.MAX;
    }

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getFees() {
        return tuitionFees;
    }

    public void setFees(double tuitionFees) {
        this.tuitionFees = tuitionFees;
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

        return String.format("Student Name: %s %s\nDate Of Birth: %s\nTuition Fees: %s€\nCourses: %s",
                getFirstName(), getLastName(), getDateOfBirth().format(DATE_FORMATTER), getFees(), coursesString);
    }

}

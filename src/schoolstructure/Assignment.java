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
public class Assignment {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy", Locale.UK);

    private String title;
    private String description;
    private LocalDate subDateTime;
    private double oralMark;
    private double totalMark;
    private ArrayList<Course> courses;

    public Assignment() {
        this.subDateTime = LocalDate.MAX;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(LocalDate submissionDate) {

        this.subDateTime = submissionDate;
    }

    public double getOralMark() {
        return oralMark;
    }

    public void setOralMark(double oralMark) {
        this.oralMark = oralMark;
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
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

        return String.format("Assignment Title: %s\nAssignment Description: %s\nSubmission Date: %s\nOral Mark: %s\nTotal Mark: %s\nCourses: %s",
                getTitle(), getDescription(), getSubDateTime().format(DATE_FORMATTER), getOralMark(), getTotalMark(), coursesString);
    }

}

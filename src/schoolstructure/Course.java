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

/**
 * @author Nikos Syrios
 */
public class Course {

    final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.UK);

    public String objectID;
    ArrayList<Assignment> assignments;
    private String title;
    private String stream;
    private String type;
    private LocalDate start_date;
    private LocalDate end_date;

    public Course() {
        this.start_date = LocalDate.MAX;
        this.end_date = LocalDate.MAX;

    }

    public String getObjectID() {
        return objectID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return start_date;
    }

    public void setStartDate(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEndDate() {
        return end_date;
    }

    public void setEndDate(LocalDate end_date) {
        this.end_date = end_date;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        if (this.assignments == null) {
            this.assignments = new ArrayList<Assignment>() {
                {
                    addAll(assignments);
                }
            };
        } else {
            this.assignments.addAll(assignments);
        }
    }

    public void setAssignments(Assignment assignment) {
        if (this.assignments == null) {
            assignments = new ArrayList<Assignment>() {
                {
                    add(assignment);
                }
            };
        } else {
            this.assignments.add(assignment);
        }
    }

    @Override
    public String toString() {
        return String.format("Course Title: %s\nCourse Stream: %s\nCourse Type: %s\nCourse Starts: %s\nCourse Ends: %s",
                getTitle(), getStream(), getType(), getStartDate().format(DATE_FORMATTER), getEndDate().format(DATE_FORMATTER));
    }

}

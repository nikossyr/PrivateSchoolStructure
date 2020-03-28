/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import schoolstructure.Assignment;
import schoolstructure.Course;
import schoolstructure.Student;
import schoolstructure.Trainer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import static java.util.stream.Collectors.joining;

/**
 * Class that contains the methods to generate a Menu that gives the option to
 * the user to View (print) the attributes. There are several View modes the
 * user could choose from.
 *
 * @author Nikos Syrios
 */
public class DataDisplayMenu {

    private static final Scanner SC = new Scanner(System.in);
    private static final String RESET_SHORTCUT_MESSAGE_STRING = "You can always type \""
            + SchoolUtils.CANCEL_COMMAND_STRING + "\" to start over ";

    /**
     * Main method used to generate the Menu and Dialogue. It gives the user the
     * option to View the attributes. There are several View modes the user
     * could choose from:
     * <ul>
     * <li>All Students
     * <li>All Trainers
     * <li>All Assignments
     * <li>All Students per Course
     * <li>All Trainers per Course
     * <li>All Assignments per Course
     * <li>All Students that belong to more than one Course
     * <li>All students that need to submit Assignments on the same calendar
     * week as the user typed Date.
     * </ul>
     *
     * @param schoolAttrAL the ArrayList of ArrayLists of all Object Types
     *                     (Course, Trainer, Student, Assignment with that order)
     */
    public static void generateMenu(ArrayList<ArrayList> schoolAttrAL) {

        ArrayList<Course> coursesAL = new ArrayList<>();
        coursesAL.addAll(schoolAttrAL.get(0));
        ArrayList<Trainer> trainersAL = new ArrayList<>();
        trainersAL.addAll(schoolAttrAL.get(1));
        ArrayList<Student> studentsAL = new ArrayList<>();
        studentsAL.addAll(schoolAttrAL.get(2));
        ArrayList<Assignment> assignmentsAL = new ArrayList<>();
        assignmentsAL.addAll(schoolAttrAL.get(3));

        System.out.println("What you you like to view?");
        boolean hasNextInput = true;
        while (hasNextInput) {
            System.out.println("==================================");
            System.out.println("1 - View all Courses");
            System.out.println("2 - View all Trainers");
            System.out.println("3 - View all Students");
            System.out.println("4 - View all Assignments");
            System.out.println("**********************************");
            System.out.println("5 - View all Students per Course");
            System.out.println("6 - View all Trainers per Course");
            System.out.println("7 - View all Assignements per Course");
            System.out.println("**********************************");
            System.out.println("8 - View all Students that are enrolled to multiple Courses");
            System.out.println("9 - View all Students that need to submit one or more Assignements in a set week");
            System.out.println("----------------------------------");
            System.out.println("0 - Retrun to previous menu screen");
            System.out.println("----------------------------------");
            if (SC.hasNextInt()) {
                int currentUserInput = SC.nextInt();
                SC.nextLine();
                switch (currentUserInput) {
                    case 0:
                        hasNextInput = false;
                        DataViewAddMenu.generateMenu(schoolAttrAL);
                        break;
                    case 1:
                        if (coursesAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Courses first!");
                            System.out.println("");
                        } else {
                            System.out.println("Courses");
                            System.out.println("**********************************");
                            for (Course course : coursesAL) {
                                System.out.println(course);
                                System.out.println("");
                            }
                        }
                        break;
                    case 2:
                        if (trainersAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Trainers first!");
                            System.out.println("");
                        } else {
                            System.out.println("Trainers");
                            System.out.println("**********************************");
                            for (Trainer trainer : trainersAL) {
                                System.out.println(trainer);
                                System.out.println("");
                            }
                        }
                        break;
                    case 3:
                        if (studentsAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Students first!");
                            System.out.println("");
                        } else {
                            System.out.println("Students");
                            System.out.println("**********************************");
                            for (Student student : studentsAL) {
                                System.out.println(student);
                                System.out.println("");
                            }
                        }
                        break;
                    case 4:
                        if (assignmentsAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Assignments first!");
                            System.out.println("");
                        } else {
                            System.out.println("Assignments");
                            System.out.println("**********************************");
                            for (Assignment assignment : assignmentsAL) {
                                System.out.println(assignment);
                                System.out.println("");
                            }
                        }
                        break;
                    case 5:
                        if (coursesAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Courses first!");
                            System.out.println("");
                        } else if (studentsAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Students first!");
                            System.out.println("");
                        } else {
                            printAttributtesPerCourse(studentsAL, SchoolUtils.selectCourse(coursesAL));
                        }
                        break;

                    case 6:
                        if (coursesAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Courses first!");
                            System.out.println("");
                        } else if (trainersAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Trainers first!");
                            System.out.println("");
                        } else {
                            printAttributtesPerCourse(trainersAL, SchoolUtils.selectCourse(coursesAL));
                        }
                        break;
                    case 7:
                        if (coursesAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Courses first!");
                            System.out.println("");
                        } else if (assignmentsAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Assignmenets first!");
                            System.out.println("");
                        } else {
                            printAttributtesPerCourse(assignmentsAL, SchoolUtils.selectCourse(coursesAL));
                        }
                        break;
                    case 8:
                        if (coursesAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Courses first!");
                            System.out.println("");
                        } else if (studentsAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Students first!");
                            System.out.println("");
                        } else {
                            printStudentsWithMultipleCourses(studentsAL, coursesAL);
                        }
                        break;
                    case 9:
                        if (studentsAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Students first!");
                            System.out.println("");
                        } else if (assignmentsAL.isEmpty()) {
                            System.out.println("Oops! You need to create some Assignents first!");
                            System.out.println("");
                        }
                        System.out.println(RESET_SHORTCUT_MESSAGE_STRING);
                        System.out.println("----------------------------------");
                        printStudentsWithAssignmentsDue(studentsAL, assignmentsAL);
                        break;
                    default:
                        System.out.println("Please insert the number next to the option you want to choose");
                        hasNextInput = true;
                        break;
                }
            } else {
                hasNextInput = true;
                SC.nextLine();
                System.out.println("Please insert the number next to the option you want to choose");

            }
        }
    }

    private static void printAttributtesPerCourse(ArrayList attributesAL, Course course) {
        if (attributesAL != null && !attributesAL.isEmpty()) {
            if (attributesAL.get(0) instanceof Student) {
                int counter = 0;
                System.out.println("Students in " + course.getTitle());
                System.out.println("----------------------------------");
                for (int i = 0; i < attributesAL.size(); i++) {
                    Student currentStudent = (Student) attributesAL.get(i);
                    if (currentStudent.getCourses() != null && currentStudent.getCourses().contains(course)) {
                        System.out.println(currentStudent.getFirstName() + " " + currentStudent.getLastName());
                        counter++;
                    }
                }
                System.out.println("----------------------------------");
                if (counter == 0) {
                    System.out.println("No Students are enrolled in this Course");
                } else {
                    System.out.println(counter + " Students found.");
                }
            } else if (attributesAL.get(0) instanceof Trainer) {
                int counter = 0;
                System.out.println("Trainers in " + course.getTitle());
                System.out.println("----------------------------------");
                for (int i = 0; i < attributesAL.size(); i++) {
                    Trainer currentTrainer = (Trainer) attributesAL.get(i);
                    if (currentTrainer.getCourses() != null && currentTrainer.getCourses().contains(course)) {
                        System.out.println(currentTrainer.getFirstName() + " " + currentTrainer.getLastName());
                        System.out.println("Subject: " + currentTrainer.getSubject());
                        System.out.println("");
                        counter++;
                    }
                }
                System.out.println("----------------------------------");
                if (counter == 0) {
                    System.out.println("No Trainers are teaching this Course");
                } else {
                    System.out.println(counter + " Trainers found.");
                }
            } else if (attributesAL.get(0) instanceof Assignment) {
                int counter = 0;
                System.out.println("Assignments in " + course.getTitle());
                System.out.println("----------------------------------");
                for (int i = 0; i < attributesAL.size(); i++) {
                    Assignment currentAssignement = (Assignment) attributesAL.get(i);
                    if (currentAssignement.getCourses() != null && currentAssignement.getCourses().contains(course)) {
                        System.out.println(currentAssignement.getTitle());
                        System.out.println("Due on: " + currentAssignement.getSubDateTime().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy", Locale.UK)));
                        System.out.println("");
                        counter++;
                    }
                }
                System.out.println("----------------------------------");
                if (counter == 0) {
                    System.out.println("No Assignments are included in this Course");
                } else {
                    System.out.println(counter + " Assignements found.");
                }
            }
        }
    }

    private static void printStudentsWithMultipleCourses(ArrayList<Student> studentsAL, ArrayList<Course> coursesAL) {
        System.out.println("Students enrolled in multiple Courses");
        System.out.println("*************************************");
        int counter = 0;
        for (int i = 0; i < studentsAL.size(); i++) {
            Student currentStudent = studentsAL.get(i);
            ArrayList<Course> currentStudentCoursesAL = currentStudent.getCourses();
            if (currentStudentCoursesAL.size() > 1) {
                counter++;
                System.out.println(currentStudent.getFirstName() + " " + currentStudent.getLastName());
                String coursesString = currentStudent.getCoursesTitles().stream()
                        .collect(joining(", "));
                System.out.println("Courses: " + coursesString);
                System.out.println("");
            }
        }
        if (counter == 0) {
            System.out.println("No students found");
        } else {
            System.out.println(counter + " Students found.");
        }
    }

    private static void printStudentsWithAssignmentsDue(ArrayList<Student> studentsAL, ArrayList<Assignment> assignmentsAL) {

        boolean isInputInvalid = true;

        while (isInputInvalid) {
            LocalDate userDateLD = SchoolUtils.getDate();
            if (userDateLD.equals(SchoolUtils.CANCEL_COMMAND_LOCAL_DATE)) {
                break;
            }
            int currentDayOfWeek = userDateLD.getDayOfWeek().getValue();
            LocalDate startOfWeek = userDateLD.minusDays(currentDayOfWeek);
            LocalDate endOfWeek = userDateLD.plusDays(8 - currentDayOfWeek);
            ArrayList<Assignment> assigmentsDueAL = new ArrayList<>();

            for (int i = 0; i < assignmentsAL.size(); i++) {
                LocalDate currentDueDate = assignmentsAL.get(i).getSubDateTime();
                if (currentDueDate.isAfter(startOfWeek)
                        && currentDueDate.isBefore(endOfWeek)) {
                    Assignment currentAssignment = assignmentsAL.get(i);
                    assigmentsDueAL.add(currentAssignment);
                }
            }
            if (assigmentsDueAL.isEmpty()) {
                System.out.println("No assigments due this week");
                isInputInvalid = false;
            } else {
                HashMap<Student, ArrayList<Assignment>> studentsAssignementDueHM = new HashMap<>();
                for (int i = 0; i < studentsAL.size(); i++) {
                    Student currentStudent = studentsAL.get(i);
                    ArrayList<Course> currentStudentCoursesAL = currentStudent.getCourses();
                    if (currentStudentCoursesAL != null && !currentStudentCoursesAL.isEmpty()) {
                        for (int j = 0; j < currentStudentCoursesAL.size(); j++) {
                            Course currentCourse = currentStudentCoursesAL.get(j);
                            ArrayList<Assignment> currentAssignmentsAL = currentCourse.getAssignments();
                            ArrayList<Assignment> currentAssignmentsDueAL = new ArrayList<>();
                            if (currentAssignmentsAL != null && !currentAssignmentsAL.isEmpty()) {
                                for (int k = 0; k < currentAssignmentsAL.size(); k++) {
                                    Assignment currentAssignment = currentAssignmentsAL.get(k);
                                    if (assigmentsDueAL.contains(currentAssignment)) {
                                        currentAssignmentsDueAL.add(currentAssignment);
                                    }
                                }
                            }
                            studentsAssignementDueHM.put(currentStudent, currentAssignmentsDueAL);
                        }
                    }
                }
                if (studentsAssignementDueHM.isEmpty()) {
                    System.out.println("No students have assignmenets due this week");
                    isInputInvalid = false;
                } else {
                    System.out.println("Students with Assignments due this week");
                    System.out.println("***************************************");
                    for (Student student : studentsAssignementDueHM.keySet()) {
                        System.out.println(student.getFirstName() + " " + student.getLastName() + ":");
                        ArrayList<Assignment> currentAssignmentsAL = studentsAssignementDueHM.get(student);
                        if (!currentAssignmentsAL.isEmpty()) {
                            for (int i = 0; i < assigmentsDueAL.size(); i++) {
                                System.out.println(assigmentsDueAL.get(i).getTitle());
                                System.out.println("Due on: " + assigmentsDueAL.get(i).getSubDateTime().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy", Locale.UK)));
                                System.out.println("");

                            }
                        }
                    }
                    isInputInvalid = false;
                }
            }
        }
    }
}

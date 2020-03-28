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
import java.util.Locale;
import java.util.Scanner;

/**
 * Class that contains the methods to generate a Menu that gives the option to
 * the user to Add more attributes.
 *
 * @author Nikos Syrios
 */
public class DataInsertMenu {

    private static final Scanner SC = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.UK);
    private static final String RESET_SHORTCUT_MESSAGE_STRING = "You can always type \""
            + SchoolUtils.CANCEL_COMMAND_STRING + "\" to start over ";
    private static ArrayList<Course> coursesAL;
    private static ArrayList<Trainer> trainerAL;
    private static ArrayList<Student> studentAL;
    private static ArrayList<Assignment> assignmentAL;

    /**
     * Main method used to generate the Menu and Dialogue. It gives the user the
     * option to Add more attributes. It also generates all the following
     * dialogues needed to add all info in all attribute Object types. Then it
     * stores them in an ArrayList of all ArrayList of each Object type.
     * <p>
     * It takes no parameter. It is used when there are no data already typed.
     *
     * @return the ArrayList of all ArrayLists of all Object Types (Course,
     * Trainer, Student, Assignment with that order)
     */
    public static ArrayList<ArrayList> generateMenu() {

        coursesAL = new ArrayList<>();
        trainerAL = new ArrayList<>();
        studentAL = new ArrayList<>();
        assignmentAL = new ArrayList<>();

        return printMenu();
    }

    /**
     * Main method used to generate the Menu and Dialogue. It gives the user the
     * option to Add more attributes. It also generates all the following
     * dialogues needed to add all info in all attribute Object types. Then it
     * stores them in an ArrayList of all ArrayList of each Object type.
     * <p>
     * It is used when there are already data.
     *
     * @param schoolAttrAL the ArrayList of ArrayLists of all Object Types
     *                     (Course, Trainer, Student, Assignment with that order)
     * @return the ArrayList of all ArrayLists of all Object Types (Course,
     * Trainer, Student, Assignment with that order)
     */
    public static ArrayList<ArrayList> generateMenu(ArrayList<ArrayList> schoolAttrAL) {

        coursesAL = schoolAttrAL.get(0);
        trainerAL = schoolAttrAL.get(1);
        studentAL = schoolAttrAL.get(2);
        assignmentAL = schoolAttrAL.get(3);

        return printMenu();
    }

    private static ArrayList<ArrayList> printMenu() {
        while (coursesAL.isEmpty()) {
            System.out.println("Please start by adding some courses");
            System.out.println("----------------------------------");
            coursesAL.addAll(insertCourseInfo());
        }

        boolean hasNextInput = true;
        while (hasNextInput) {
            System.out.println("What would you like to add next?");
            System.out.println("==================================");
            System.out.println("1 - Courses");
            System.out.println("2 - Trainers");
            System.out.println("3 - Students");
            System.out.println("4 - Assignements");
            System.out.println("----------------------------------");
            System.out.println("7 - Discart saved items and start over");
            System.out.println("----------------------------------");
            System.out.println("0 - Return to previous menu screen");
            System.out.println("----------------------------------");

            if (SC.hasNextInt()) {
                int userInput = SC.nextInt();
                switch (userInput) {
                    case 0:
                        hasNextInput = false;
                        DataViewAddMenu.generateMenu(aggregateAttributes(coursesAL, trainerAL, studentAL, assignmentAL));
                        break;
                    case 1:
                        coursesAL.addAll(insertCourseInfo());
                        hasNextInput = true;
                        break;
                    case 2:
                        trainerAL.addAll(insertTrainerInfo(coursesAL));
                        hasNextInput = true;
                        break;
                    case 3:
                        studentAL.addAll(insertStudentInfo(coursesAL));
                        hasNextInput = true;
                        break;
                    case 4:
                        assignmentAL.addAll(insertAssignementInfo(coursesAL));
                        hasNextInput = true;
                        break;
                    case 7:
                        System.out.println("Are you sure you want to DELETE everything and start over? y/n");
                        if (SchoolUtils.getPolarDialogue()) {
                            MainMenu.generateMenu();
                        } else {
                            hasNextInput = true;
                            break;
                        }
                    default:
                        hasNextInput = true;
                        System.out.println("Please insert the number next to the option you want to choose");
                        break;
                }
            } else {
                System.out.println("Please use only Numbers to navigate");
                SC.nextLine();
            }
        }
        System.out.print(coursesAL + "\n" + trainerAL + "\n" + studentAL + "\n" + assignmentAL + "\n");
        ArrayList<ArrayList> schoolDataAL = new ArrayList<ArrayList>() {
            {
                add(coursesAL);
                add(trainerAL);
                add(studentAL);
                add(assignmentAL);
            }
        };
        return schoolDataAL;
    }

    private static ArrayList<Course> insertCourseInfo() {
        ArrayList<Course> currentCoursesAL = new ArrayList<>();
        boolean hasNextEntry = true;
        boolean isInputInvalid = true;
        while (hasNextEntry) {
            while (isInputInvalid) {
                String courseTitle;
                String courseStream;
                String courseType;
                LocalDate courseStartDate;
                LocalDate courseEndDate;
                System.out.println(RESET_SHORTCUT_MESSAGE_STRING);
                System.out.println("----------------------------------");
                System.out.println("Type the Title of the Course");
                courseTitle = SchoolUtils.getStringInput();
                if (courseTitle.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                System.out.println("Type the Stream of the Course");
                courseStream = SchoolUtils.getStringInput();
                if (courseStream.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                System.out.println("Type the Type of the Course");
                courseType = SchoolUtils.getStringInput();
                if (courseType.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                System.out.println("Type the Date the Course Starts");
                courseStartDate = SchoolUtils.getDate();
                if (courseStartDate.equals(SchoolUtils.CANCEL_COMMAND_LOCAL_DATE)) {
                    break;
                }
                System.out.println("Type the Date the Course Ends");
                courseEndDate = SchoolUtils.getDate();
                if (courseEndDate.equals(SchoolUtils.CANCEL_COMMAND_LOCAL_DATE)) {
                    break;
                }
                System.out.println("----------------------------------");

                System.out.format("Course Title: %s\nCourse Stream: %s\nCourse Type: %s\nCourse Duration:\n%s - %s\n",
                        courseTitle, courseStream, courseType, courseStartDate.format(DATE_FORMATTER), courseEndDate.format(DATE_FORMATTER));
                System.out.println("----------------------------------");
                System.out.println("Is this information correct? y/n");
                boolean courseInfoCorrectPolarDialogue = SchoolUtils.getPolarDialogue();
                if (courseInfoCorrectPolarDialogue) {

                    Course currentCourse = new Course();
                    currentCourse.setTitle(courseTitle);
                    currentCourse.setType(courseType);
                    currentCourse.setStream(courseStream);
                    currentCourse.setStartDate(courseStartDate);
                    currentCourse.setEndDate(courseEndDate);
                    currentCoursesAL.add(currentCourse);

                    System.out.println("Course saved.");
                    isInputInvalid = false;

                } else if (!courseInfoCorrectPolarDialogue) {
                    System.out.println("Please enter the correct information below");
                    System.out.println("------------------------------------");
                }

            }
            System.out.println("Do you want to add more Courses? y/n");
            hasNextEntry = SchoolUtils.getPolarDialogue();
        }

        return currentCoursesAL;
    }

    private static ArrayList<Trainer> insertTrainerInfo(ArrayList<Course> coursesAL) {
        ArrayList<Trainer> currentTrainerAL = new ArrayList<>();
        boolean hasNextEntry = true;
        boolean isInputInvalid = true;
        while (hasNextEntry) {
            while (isInputInvalid) {

                String trainerFirstName;
                String trainerLastName;
                String trainerSubject;

                ArrayList<Course> currentTrainerCoursesAL = new ArrayList<>();
                System.out.println(RESET_SHORTCUT_MESSAGE_STRING);
                System.out.println("----------------------------------");
                System.out.println("Type the First Name of the Trainer");
                trainerFirstName = SchoolUtils.getStringInput();
                if (trainerFirstName.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                System.out.println("Type the Last Name of the Trainer");
                trainerLastName = SchoolUtils.getStringInput();
                if (trainerLastName.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                System.out.println("Type the Subject the Trainer teaches");
                trainerSubject = SchoolUtils.getStringInput();
                if (trainerLastName.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                System.out.println("Select the Course the Trainer teaches. For multiple Courses, seperate the numbers with a comma.");
                currentTrainerCoursesAL.addAll(SchoolUtils.selectCourses(coursesAL));
                if (currentTrainerCoursesAL.equals(SchoolUtils.CANCEL_COMMAND_COURSES_ARRAY_LIST)) {
                    break;
                }

                System.out.println("----------------------------------");
                System.out.format("Trainer Name: %s %s\nSubject: %s\n",
                        trainerFirstName, trainerLastName, trainerSubject);
                System.out.println("Courses taught:");
                for (Course course : currentTrainerCoursesAL) {
                    System.out.println(course.getTitle());
                }
                System.out.println("----------------------------------");
                System.out.println("Is this information correct? y/n");

                boolean trainerInfoCorrectPolarDialogue = SchoolUtils.getPolarDialogue();
                if (trainerInfoCorrectPolarDialogue) {
                    Trainer currentTrainer = new Trainer();
                    currentTrainer.setFirstName(trainerFirstName);
                    currentTrainer.setLastName(trainerLastName);
                    currentTrainer.setSubject(trainerSubject);
                    currentTrainer.setCourses(currentTrainerCoursesAL);
                    currentTrainerAL.add(currentTrainer);
                    System.out.println("Trainer saved.");
                    isInputInvalid = false;

                } else if (!trainerInfoCorrectPolarDialogue) {
                    System.out.println("Please enter the correct information below");
                    System.out.println("------------------------------------");
                }
            }
            System.out.println("Do you want to add more Trainers? y/n");
            hasNextEntry = SchoolUtils.getPolarDialogue();
        }

        return currentTrainerAL;
    }

    private static ArrayList<Student> insertStudentInfo(ArrayList<Course> coursesAL) {
        ArrayList<Student> currentStudentAL = new ArrayList<>();
        boolean hasNextEntry = true;
        boolean isInputInvalid = true;
        while (hasNextEntry) {
            while (isInputInvalid) {

                String studentFirstName;
                String studentLastName;
                LocalDate studentDateOfBirth;
                double studentTuitionFees;
                ArrayList<Course> currentStudentCoursesAL = new ArrayList<>();

                System.out.println(RESET_SHORTCUT_MESSAGE_STRING);
                System.out.println("----------------------------------");
                System.out.println("Type the First Name of the Student");
                studentFirstName = SchoolUtils.getStringInput();
                if (studentFirstName.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                System.out.println("Type the Last Name of the Student");
                studentLastName = SchoolUtils.getStringInput();
                if (studentLastName.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                System.out.println("Type the Student's Date of Birth");
                studentDateOfBirth = SchoolUtils.getDate();
                if (studentDateOfBirth.equals(SchoolUtils.CANCEL_COMMAND_LOCAL_DATE)) {
                    break;
                }
                System.out.println("Type the Student's Tuition Fees");
                studentTuitionFees = SchoolUtils.getDoubleInput();
                if (studentTuitionFees == SchoolUtils.CANCEL_COMMAND_DOUBLE) {
                    break;
                }
                System.out.println("Select the Course the Student attends. For multiple Courses, seperate the numbers with a comma.");
                currentStudentCoursesAL.addAll(SchoolUtils.selectCourses(coursesAL));
                if (currentStudentCoursesAL.equals(SchoolUtils.CANCEL_COMMAND_COURSES_ARRAY_LIST)) {
                    break;
                }

                System.out.println("----------------------------------");
                System.out.format("Student Name: %s %s\nDate Of Birth: %s\nTuition Fees: %s\n",
                        studentFirstName, studentLastName, studentDateOfBirth.format(DATE_FORMATTER), studentTuitionFees);
                System.out.println("Courses attended:");
                for (Course course : currentStudentCoursesAL) {
                    System.out.println(course.getTitle());
                }
                System.out.println("----------------------------------");
                System.out.println("Is this information correct? y/n");

                boolean studentInfoCorrectPolarDialogue = SchoolUtils.getPolarDialogue();
                if (studentInfoCorrectPolarDialogue) {
                    Student currentStudent = new Student();
                    currentStudent.setFirstName(studentFirstName);
                    currentStudent.setLastName(studentLastName);
                    currentStudent.setDateOfBirth(studentDateOfBirth);
                    currentStudent.setFees(studentTuitionFees);
                    currentStudent.setCourses(currentStudentCoursesAL);
                    currentStudentAL.add(currentStudent);
                    System.out.println("Student saved.");
                    isInputInvalid = false;

                } else if (!studentInfoCorrectPolarDialogue) {
                    System.out.println("Please enter the correct information below");
                    System.out.println("------------------------------------");
                }
            }
            System.out.println("Do you want to add more Students? y/n");
            hasNextEntry = SchoolUtils.getPolarDialogue();
        }

        return currentStudentAL;
    }

    private static ArrayList<Assignment> insertAssignementInfo(ArrayList<Course> coursesAL) {
        ArrayList<Assignment> currentAssignmentAL = new ArrayList<>();
        boolean hasNextEntry = true;
        boolean isInputInvalid = true;
        while (hasNextEntry) {
            while (isInputInvalid) {
                String assignmentTitle;
                String assignmentDescription;
                LocalDate assignmentSubDateTime;
                double assignmentOralMark;
                double assignmentTotalMark;
                ArrayList<Course> currentAssignmentCoursesAL = new ArrayList<>();

                SC.nextLine();
                System.out.println(RESET_SHORTCUT_MESSAGE_STRING);
                System.out.println("----------------------------------");
                System.out.println("Type the title of the Assignment");
                assignmentTitle = SchoolUtils.getStringInput();
                if (assignmentTitle.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                System.out.println("Type the Description of the Assignment");
                assignmentDescription = SchoolUtils.getStringInput();
                if (assignmentDescription.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                System.out.println("Type the Assignment's Final Submission Date");
                assignmentSubDateTime = SchoolUtils.getDate();
                if (assignmentSubDateTime.equals(SchoolUtils.CANCEL_COMMAND_LOCAL_DATE)) {
                    break;
                }
                System.out.println("Type the Assignment's Oral Mark");
                assignmentOralMark = SchoolUtils.getDoubleInput();
                if (assignmentOralMark == SchoolUtils.CANCEL_COMMAND_DOUBLE) {
                    break;
                }
                System.out.println("Type the Assignment's Total Mark");
                assignmentTotalMark = SchoolUtils.getDoubleInput();
                if (assignmentOralMark == SchoolUtils.CANCEL_COMMAND_DOUBLE) {
                    break;
                }
                System.out.println("Select the Course of the Assignment. For multiple Courses, seperate the numbers with a comma.");
                currentAssignmentCoursesAL.addAll(SchoolUtils.selectCourses(coursesAL));
                if (currentAssignmentCoursesAL.equals(SchoolUtils.CANCEL_COMMAND_COURSES_ARRAY_LIST)) {
                    break;
                }
                System.out.println("----------------------------------");

                System.out.format("Assignment Title: %s\nAssignment Description: %s\nSubmission Date: %s\nOral Mark: %s\nTotal Mark: %s\n",
                        assignmentTitle, assignmentDescription, assignmentSubDateTime.format(DATE_FORMATTER), assignmentOralMark, assignmentTotalMark);
                System.out.println("Courses:");
                for (Course course : currentAssignmentCoursesAL) {
                    System.out.println(course.getTitle());
                }
                System.out.println("----------------------------------");
                System.out.println("Is this information correct? y/n");

                boolean assignementInfoCorrectPolarDialogue = SchoolUtils.getPolarDialogue();
                if (assignementInfoCorrectPolarDialogue) {
                    Assignment currentAssignment = new Assignment();
                    currentAssignment.setTitle(assignmentTitle);
                    currentAssignment.setDescription(assignmentDescription);
                    currentAssignment.setSubDateTime(assignmentSubDateTime);
                    currentAssignment.setOralMark(assignmentOralMark);
                    currentAssignment.setTotalMark(assignmentOralMark);
                    currentAssignment.setCourses(currentAssignmentCoursesAL);

                    currentAssignmentAL.add(currentAssignment);
                    System.out.println("Assignement saved.");
                    isInputInvalid = false;

                } else if (!assignementInfoCorrectPolarDialogue) {
                    System.out.println("Please enter the correct information below");
                    System.out.println("------------------------------------");
                }
            }
            System.out.println("Do you want to add more Assignements? y/n");
            hasNextEntry = SchoolUtils.getPolarDialogue();
        }
        coursesAL.addAll(SchoolUtils.updateCourses(coursesAL, currentAssignmentAL));
        return currentAssignmentAL;
    }

    private static ArrayList<ArrayList> aggregateAttributes(ArrayList<Course> coursesAL,
                                                            ArrayList<Trainer> trainersAL, ArrayList<Student> studentsAL,
                                                            ArrayList<Assignment> assignmentsAL) {

        return new ArrayList<ArrayList>() {
            {
                add(coursesAL);
                add(trainersAL);
                add(studentsAL);
                add(assignmentsAL);
            }
        };
    }
}

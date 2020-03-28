/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import schoolstructure.Assignment;
import schoolstructure.Course;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that contains helper methods used in the application (e.g. methods that
 * validate, select data etc)
 *
 * @author Nikos Syrios
 */
public class SchoolUtils {

    public static final String CANCEL_COMMAND_STRING = "<X>";
    public static final int CANCEL_COMMAND_INTEGER = Integer.MIN_VALUE;
    public static final double CANCEL_COMMAND_DOUBLE = Double.MIN_VALUE;
    public static final LocalDate CANCEL_COMMAND_LOCAL_DATE = LocalDate.MAX;
    private static final Scanner SC = new Scanner(System.in);
    private static final Course CANCEL_COMMAND_COURSE = new Course() {
        {
            setTitle(CANCEL_COMMAND_STRING);
        }
    };
    public static final ArrayList<Course> CANCEL_COMMAND_COURSES_ARRAY_LIST = new ArrayList<Course>() {
        {
            add(CANCEL_COMMAND_COURSE);
        }
    };

    /**
     * This is just a yes/no dialogue (polar dialogue). Upon calling it, it
     * waits for user inputs y or n and then checks the input. It also checks
     * for wrong input and creates new dialogue until the user uses one of the
     * options.
     *
     * @return true(yes) or false(no) depending on user input
     */
    public static boolean getPolarDialogue() {
        boolean isInvalidInput = true;
        boolean result = false;
        while (isInvalidInput) {
            if (SC.hasNextLine()) {
                String currentInput = SC.nextLine();
                if (currentInput.equals("Y") || currentInput.equals("y")) {
                    result = true;
                    isInvalidInput = false;
                } else if (currentInput.equals("N") || currentInput.equals("n")) {
                    result = false;
                    isInvalidInput = false;
                } else {
                    System.out.println("Please type y for yes and n for no.");
                }
            }
        }
        return result;
    }

    /**
     * Gets the user input and verifies that it is a valid String.
     *
     * @return the validated String the user typed
     */
    public static String getStringInput() {
        boolean isInvalidInput = true;
        String result = "";
        while (isInvalidInput) {
            if (SC.hasNextLine()) {
                result = SC.nextLine();
                if (result.toUpperCase().contains(CANCEL_COMMAND_STRING)) {
                    return CANCEL_COMMAND_STRING;
                } else {
                    break;
                }
            } else {
                System.out.println("Please try again");
            }
        }
        return result;
    }

    /**
     * Gets the user input and verifies that it is a valid integer. If it is not
     * then the user is prompted to try again.
     *
     * @return the validated Integer the user typed
     */
    public static int getIntInput() {
        boolean isInvalidInput = true;
        int result = -1;
        while (isInvalidInput) {
            if (SC.hasNextInt()) {
                result = SC.nextInt();
                SC.nextLine();
                isInvalidInput = false;
            } else if (SC.hasNextLine()) {
                String currentInput = SC.nextLine();
                if (currentInput.toUpperCase().contains(CANCEL_COMMAND_STRING)) {
                    return CANCEL_COMMAND_INTEGER;
                }
            } else {
                System.out.println("Please type a valid integer");
            }
        }
        return result;
    }

    /**
     * Gets the user input and verifies that it is a valid integer and it is
     * within a range. Greater than or equal to the start parameter (inclusive)
     * and lower than the end parameter (exclusive).
     *
     * @param start of the range (inclusive)
     * @param end   of the range (exclusive)
     * @return the validated integer that the user typed
     */
    public static int getIntInput(int start, int end) {
        boolean isInvalidInput = true;
        int result = -1;
        while (isInvalidInput) {
            if (SC.hasNextInt()) {
                int currentUserInput = SC.nextInt();
                SC.nextLine();
                if (currentUserInput >= start && currentUserInput < end) {
                    result = currentUserInput;
                    break;
                } else {
                    System.out.println("Please type a number in the specified range");
                }
            } else if (SC.hasNextLine()) {
                String currentInput = SC.nextLine();
                if (currentInput.toUpperCase().contains(CANCEL_COMMAND_STRING)) {
                    return CANCEL_COMMAND_INTEGER;
                } else {
                    System.out.println("Please type a valid integer");
                }
            }
        }
        return result;
    }

    /**
     * Gets the user input and verifies that it is a valid double.
     *
     * @return the validated double that the user typed
     */
    public static double getDoubleInput() {
        boolean isInvalidInput = true;
        double result = -1.0;
        while (isInvalidInput) {
            if (SC.hasNextDouble()) {
                result = SC.nextDouble();
                SC.nextLine();
                isInvalidInput = false;
            } else if (SC.hasNextLine()) {
                String currentInput = SC.nextLine();
                if (currentInput.toUpperCase().contains(CANCEL_COMMAND_STRING)) {
                    return CANCEL_COMMAND_DOUBLE;
                }
            } else {
                System.out.println("Please type a valid number");
            }
        }
        return result;
    }

    /**
     * Dialogue to transform user input to a valid LocalDate date format.
     *
     * @return the validated LocalDate the user typed
     */
    public static LocalDate getDate() {
        boolean isInputInvalid = true;
        LocalDate userDateLD = LocalDate.MIN;
        int counter = 0;
        while (isInputInvalid && counter < 3) {
            System.out.println("Please insert the date in format DD-MM-YYYY, ex. 27-01-2020");
            if (SC.hasNextLine()) {
                String currentInputString = SC.nextLine();
                if (currentInputString.toUpperCase().contains(CANCEL_COMMAND_STRING)) {
                    return CANCEL_COMMAND_LOCAL_DATE;
                }
                if (!currentInputString.isEmpty() && currentInputString.contains("-")) {
                    String dateString[] = currentInputString.trim().split("-");
                    int day, month, year;
                    try {
                        day = Integer.parseInt(dateString[0]);
                        month = Integer.parseInt(dateString[1]);
                        year = Integer.parseInt(dateString[2]);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid date. Please try again.");
                        counter++;
                        continue;
                    }

                    if (day <= 31 && day > 0
                            && month > 0 && month <= 12
                            && year > Year.MIN_VALUE && year < Year.MAX_VALUE) {
                        try {
                            userDateLD = LocalDate.of(year, month, day);
                            isInputInvalid = false;
                        } catch (DateTimeException dte) {
                            System.out.println("Date cannot exist for this year. Please check your calendar and try again.");
                            counter++;
                        }
                    } else {
                        System.out.println("Invalid date. Please try again.");
                        counter++;
                    }
                } else {
                    System.out.println("Invalid date. Please try again.");
                    counter++;
                }
            }
        }
        while (counter > 2) {
            System.out.println("Insert the day (1-31)");
            int day = SchoolUtils.getIntInput(1, 32);
            if (day == CANCEL_COMMAND_INTEGER) {
                return CANCEL_COMMAND_LOCAL_DATE;
            }
            System.out.println("Insert the month (1-12)");
            int month = SchoolUtils.getIntInput(1, 13);
            if (month == CANCEL_COMMAND_INTEGER) {
                return CANCEL_COMMAND_LOCAL_DATE;
            }
            System.out.println("Insert the year");
            int year = SchoolUtils.getIntInput();
            if (year == CANCEL_COMMAND_INTEGER) {
                return CANCEL_COMMAND_LOCAL_DATE;
            }
            try {
                userDateLD = LocalDate.of(year, month, day);
                break;
            } catch (DateTimeException dte) {
                System.out.format("Date %02d-%02d-%04d cannot exist for this year. Please check your calendar and try again.",
                        day, month, year);
                counter++;
            }
        }
        return userDateLD;
    }

    /**
     * Special method to update Course ArrayList after and update to the
     * Assignment ArrayList. ArrayList of assignments should not be null or
     * empty.
     *
     * @param coursesAL   the initial Course ArrayList to modify
     * @param assignments the Assignment ArrayList that it will use to modify
     *                    the Course ArrayList
     * @return the modified Course ArrayList
     */
    public static ArrayList<Course> updateCourses(ArrayList<Course> coursesAL, ArrayList<Assignment> assignments) {
        if (coursesAL != null && !coursesAL.isEmpty()) {
            if (assignments != null && !assignments.isEmpty()) {
                for (int i = 0; i < assignments.size(); i++) {
                    Assignment currentAssignment = assignments.get(i);
                    ArrayList<Course> currentAssignmentCoursesAL = currentAssignment.getCourses();
                    for (int j = 0; j < currentAssignmentCoursesAL.size(); j++) {
                        Course currentCourse = currentAssignmentCoursesAL.get(j);
                        int currentCourseIndex = coursesAL.indexOf(currentCourse);
                        if (currentCourseIndex > -1) {
                            coursesAL.get(currentCourseIndex).setAssignments(currentAssignment);
                        }
                    }
                }
            } else {
                System.out.println("Course ArrayList is empty or null.");
            }
        } else {
            System.out.println("Assignments ArrayList is empty or null.");
        }
        return coursesAL;
    }

    /**
     * Dialogue that prints all Course titles and waits for the user to choose
     * one by typing the respective number.
     *
     * @param coursesAl the Course ArrayList from which it will get the Courses
     *                  and print their titles
     * @return the Course that the user has chosen
     */
    public static Course selectCourse(ArrayList<Course> coursesAl) {

        System.out.println("Type the number of the Course you want to choose");
        boolean isInputInvalid = true;
        while (isInputInvalid) {
            System.out.println("----------------------------------");
            for (int i = 0; i < coursesAl.size(); i++) {
                System.out.println((i + 1) + " - " + coursesAl.get(i).getTitle());
            }

            if (SC.hasNextInt()) {
                int currentUserInput = SC.nextInt();
                SC.nextLine();
                if (currentUserInput > 0 && currentUserInput <= coursesAl.size()) {
                    return coursesAl.get(currentUserInput - 1);

                } else {
                    isInputInvalid = true;
                    System.out.println("Please use one of the numbers below");
                }
            } else {
                isInputInvalid = true;
                SC.nextLine();
                System.out.println("Please insert the number next to the option you want to choose");
            }
        }
        return new Course();
    }

    /**
     * Dialogue that prints all Course titles and waits for the user to choose
     * one or multiple
     *
     * @param coursesAL the Course ArrayList from which it will get the Courses
     *                  and print their titles
     * @return the Course ArrayList that contains one or more Course objects
     * that the user has chosen
     */
    public static ArrayList<Course> selectCourses(ArrayList<Course> coursesAL) {

        ArrayList<Course> selectedCoursesAL = new ArrayList<>();

        boolean isInputInvalid = true;
        while (isInputInvalid) {
            System.out.println("----------------------------------");
            int counter = 1;
            for (int i = 0; i < coursesAL.size(); i++) {
                System.out.println((counter) + " - " + coursesAL.get(i).getTitle());
                counter++;
            }
            if (SC.hasNextLine()) {
                String currentUserInString = SC.nextLine();

                if (!currentUserInString.isEmpty() && currentUserInString.toUpperCase().contains(CANCEL_COMMAND_STRING)) {
                    return CANCEL_COMMAND_COURSES_ARRAY_LIST;
                }
                if (!currentUserInString.isEmpty() && currentUserInString.contains(",")) {
                    String coursesString[] = currentUserInString.split(",");
                    for (int i = 0; i < coursesString.length; i++) {
                        try {
                            int currentCourse = Integer.parseInt(coursesString[i]);
                            if (currentCourse > 0 && currentCourse < counter) {
                                selectedCoursesAL.add(coursesAL.get(currentCourse - 1));
                            } else {
                                System.out.println("Please insert the number next to the option you want to choose");
                                break;
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Please use only numbers seperated by comma");
                        }
                    }
                    isInputInvalid = false;
                } else if (!currentUserInString.isEmpty()) {
                    try {
                        int currentUserInInt = Integer.parseInt(currentUserInString);
                        if (currentUserInInt > 0 && currentUserInInt < counter) {
                            selectedCoursesAL.add(coursesAL.get(currentUserInInt - 1));
                            isInputInvalid = false;
                        } else {
                            isInputInvalid = true;
                            System.out.println("Please insert the number next to the option you want to choose");
                        }
                    } catch (NumberFormatException nfe) {
                        isInputInvalid = true;
                        System.out.println("Please insert the number next to the option you want to choose");
                    }

                }
            } else {
                isInputInvalid = true;
                System.out.println("Please insert the number next to the option you want to choose");
            }
        }
        return selectedCoursesAL;
    }

}

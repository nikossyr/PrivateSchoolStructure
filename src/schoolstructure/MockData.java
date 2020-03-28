/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolstructure;

import menus.SchoolUtils;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that contains methods to generate mock data for all Attributes Object
 * types.
 *
 * @author Nikos Syrios
 */
public class MockData {

    static Random rand = new Random();

    public static ArrayList<ArrayList> generateMockData() {
        ArrayList<Course> coursesAL = generateCourses();
        ArrayList<Assignment> assignmentsAL = generateAssignments(coursesAL);
        coursesAL = SchoolUtils.updateCourses(coursesAL, assignmentsAL);
        ArrayList<Trainer> trainersAL = generateTrainers(coursesAL);
        ArrayList<Student> studentsAL = generateStudents(coursesAL);

        ArrayList<ArrayList> mockDataAL = new ArrayList<>();
        mockDataAL.add(coursesAL);
        mockDataAL.add(trainersAL);
        mockDataAL.add(studentsAL);
        mockDataAL.add(assignmentsAL);

        return mockDataAL;
    }

    public static ArrayList<Course> generateCourses() {

        Course cb9JavaFTCourse = new Course() {
            {
                setTitle("CB9 Java FT");
                setStream("Java");
                setType("Full-time");
                setStartDate(LocalDate.of(2019, Month.NOVEMBER, 11));
                setEndDate(getStartDate().plusMonths(3));
            }
        };

        Course cb9JavaPTCourse = new Course() {
            {
                setTitle("CB9 JAVA PT");
                setStream("Java");
                setType("Part-time");
                setStartDate(LocalDate.of(2019, Month.NOVEMBER, 11));
                setEndDate(getStartDate().plusMonths(6));
            }
        };

        Course cb9CsharpFTCourse = new Course() {
            {
                setTitle("CB9 C# FT");
                setStream("Csharp");
                setType("Full-time");
                setStartDate(LocalDate.of(2019, Month.NOVEMBER, 11));
                setEndDate(getStartDate().plusMonths(3));
            }
        };

        Course cb9CsharpPTCourse = new Course() {
            {
                setTitle("CB9 C#PT");
                setStream("Csharp");
                setType("Part-time");
                setStartDate(LocalDate.of(2019, Month.NOVEMBER, 11));
                setEndDate(getStartDate().plusMonths(6));
            }
        };

        return new ArrayList<Course>() {
            {
                add(cb9JavaFTCourse);
                add(cb9JavaPTCourse);
                add(cb9CsharpFTCourse);
                add(cb9CsharpPTCourse);
            }
        };
    }

    private static ArrayList<Trainer> generateTrainers(ArrayList<Course> coursesAL) {
        Trainer tr01 = new Trainer() {
            {
                setFirstName("Kostas");
                setLastName("Meletiou");
                setCourses(coursesAL.get(rand.nextInt(coursesAL.size())));
                setSubject(getCourses().get(0).getStream());
            }
        };

        Trainer tr02 = new Trainer() {
            {
                setFirstName("Nikos");
                setLastName("Pergaris");
                setCourses(coursesAL.get(rand.nextInt(coursesAL.size())));
                setSubject(getCourses().get(0).getStream());
            }
        };

        Trainer tr03 = new Trainer() {
            {
                setFirstName("Panagiotis");
                setLastName("Psimoulis");
                setCourses(coursesAL.get(rand.nextInt(coursesAL.size())));
                setSubject(getCourses().get(0).getStream());
            }
        };

        Trainer tr04 = new Trainer() {
            {
                setFirstName("Elena");
                setLastName("Papageorgiou");
                setCourses(coursesAL.get(rand.nextInt(coursesAL.size())));
                setSubject(getCourses().get(0).getStream());
            }
        };

        Trainer tr05 = new Trainer() {
            {
                setFirstName("Anna");
                setLastName("Stergiou");
                setCourses(coursesAL.get(rand.nextInt(coursesAL.size())));
                setSubject(getCourses().get(0).getStream());
            }
        };

        Trainer tr06 = new Trainer() {
            {
                setFirstName("Nikos");
                setLastName("Anastasiou");
                setCourses(coursesAL.get(rand.nextInt(coursesAL.size())));
                setSubject(getCourses().get(0).getStream());
            }
        };

        return new ArrayList<Trainer>() {
            {
                add(tr01);
                add(tr02);
                add(tr03);
                add(tr04);
                add(tr05);
                add(tr06);
            }
        };

    }

    /*
     * Age range[20-40] years old.
     */
    private static ArrayList<Student> generateStudents(ArrayList<Course> coursesAL) {

        ArrayList<int[]> courseIndexAL = new ArrayList<>();
        for (int i = 0; i < 11; i++) {

            int numberOfCourses = (rand.nextInt(2) + 1);
            courseIndexAL.add(rand.ints(0, 4).distinct().limit(numberOfCourses).toArray());
//            System.out.println(Arrays.toString(courseIndexAL.get(i)));
        }

        Student st01 = new Student() {
            {
                setFirstName("George");
                setLastName("Smith");
                setFees(2500.0);
                setDateOfBirth(LocalDate.now().minusWeeks(rand.nextInt(1051) + 1050).plusDays(rand.nextInt(8)));
                setCourses(new ArrayList<Course>() {
                    {
                        int currentRandomIntsArray[] = courseIndexAL.get(1);
                        for (int i = 0; i < currentRandomIntsArray.length; i++) {
                            add(coursesAL.get(currentRandomIntsArray[i]));
                        }
                    }
                });

            }
        };
        Student st02 = new Student() {
            {
                setFirstName("Anna");
                setLastName("Brown");
                setFees(2500.0);
                setDateOfBirth(LocalDate.now().minusWeeks(rand.nextInt(1051) + 1050).plusDays(rand.nextInt(8)));
                setCourses(new ArrayList<Course>() {
                    {
                        int currentRandomIntsArray[] = courseIndexAL.get(2);
                        for (int i = 0; i < currentRandomIntsArray.length; i++) {
                            add(coursesAL.get(currentRandomIntsArray[i]));
                        }
                    }
                });
            }
        };
        Student st03 = new Student() {
            {
                setFirstName("Nikos");
                setLastName("Pappas");
                setFees(2500.0);
                setDateOfBirth(LocalDate.now().minusWeeks(rand.nextInt(1051) + 1050).plusDays(rand.nextInt(8)));
                setCourses(new ArrayList<Course>() {
                    {
                        int currentRandomIntsArray[] = courseIndexAL.get(3);
                        for (int i = 0; i < currentRandomIntsArray.length; i++) {
                            add(coursesAL.get(currentRandomIntsArray[i]));
                        }
                    }
                });
            }
        };
        Student st04 = new Student() {
            {
                setFirstName("Eleni");
                setLastName("Giannakaki");
                setFees(2500.0);
                setDateOfBirth(LocalDate.now().minusWeeks(rand.nextInt(1051) + 1050).plusDays(rand.nextInt(8)));
                setCourses(new ArrayList<Course>() {
                    {
                        int currentRandomIntsArray[] = courseIndexAL.get(4);
                        for (int i = 0; i < currentRandomIntsArray.length; i++) {
                            add(coursesAL.get(currentRandomIntsArray[i]));
                        }
                    }
                });
            }
        };
        Student st05 = new Student() {
            {
                setFirstName("Kostas");
                setLastName("Makris");
                setFees(2500.0);
                setDateOfBirth(LocalDate.now().minusWeeks(rand.nextInt(1051) + 1050).plusDays(rand.nextInt(8)));
                setCourses(new ArrayList<Course>() {
                    {
                        int currentRandomIntsArray[] = courseIndexAL.get(5);
                        for (int i = 0; i < currentRandomIntsArray.length; i++) {
                            add(coursesAL.get(currentRandomIntsArray[i]));
                        }
                    }
                });
            }
        };
        Student st06 = new Student() {
            {
                setFirstName("Maria");
                setLastName("Georgiou");
                setFees(2500.0);
                setDateOfBirth(LocalDate.now().minusWeeks(rand.nextInt(1051) + 1050).plusDays(rand.nextInt(8)));
                setCourses(new ArrayList<Course>() {
                    {
                        int currentRandomIntsArray[] = courseIndexAL.get(6);
                        for (int i = 0; i < currentRandomIntsArray.length; i++) {
                            add(coursesAL.get(currentRandomIntsArray[i]));
                        }
                    }
                });
            }
        };
        Student st07 = new Student() {
            {
                setFirstName("Nikos");
                setLastName("Karapanos");
                setFees(2500.0);
                setDateOfBirth(LocalDate.now().minusWeeks(rand.nextInt(1051) + 1050).plusDays(rand.nextInt(8)));
                setCourses(new ArrayList<Course>() {
                    {
                        int currentRandomIntsArray[] = courseIndexAL.get(7);
                        for (int i = 0; i < currentRandomIntsArray.length; i++) {
                            add(coursesAL.get(currentRandomIntsArray[i]));
                        }
                    }
                });
            }
        };
        Student st08 = new Student() {
            {
                setFirstName("Marios");
                setLastName("Makrandreou");
                setFees(2500.0);
                setDateOfBirth(LocalDate.now().minusWeeks(rand.nextInt(1051) + 1050).plusDays(rand.nextInt(8)));
                setCourses(new ArrayList<Course>() {
                    {
                        int currentRandomIntsArray[] = courseIndexAL.get(8);
                        for (int i = 0; i < currentRandomIntsArray.length; i++) {
                            add(coursesAL.get(currentRandomIntsArray[i]));
                        }
                    }
                });
            }
        };
        Student st09 = new Student() {
            {
                setFirstName("Manthos");
                setLastName("Liakopoulos");
                setFees(2500.0);
                setDateOfBirth(LocalDate.now().minusWeeks(rand.nextInt(1051) + 1050).plusDays(rand.nextInt(8)));
                setCourses(new ArrayList<Course>() {
                    {
                        int currentRandomIntsArray[] = courseIndexAL.get(9);
                        for (int i = 0; i < currentRandomIntsArray.length; i++) {
                            add(coursesAL.get(currentRandomIntsArray[i]));
                        }
                    }
                });
            }
        };
        Student st10 = new Student() {
            {
                setFirstName("Melina");
                setLastName("Limperi");
                setFees(2500.0);
                setDateOfBirth(LocalDate.now().minusWeeks(rand.nextInt(1051) + 1050).plusDays(rand.nextInt(8)));
                setCourses(new ArrayList<Course>() {
                    {
                        int currentRandomIntsArray[] = courseIndexAL.get(10);
                        for (int i = 0; i < currentRandomIntsArray.length; i++) {
                            add(coursesAL.get(currentRandomIntsArray[i]));
                        }
                    }
                });
            }
        };
        Student st11 = new Student() {
            {
                setFirstName("Mary");
                setLastName("Poppins");
                setFees(2500.0);
                setDateOfBirth(LocalDate.now().minusWeeks(rand.nextInt(1051) + 1050).plusDays(rand.nextInt(8)));
                setCourses(new ArrayList<Course>() {
                    {
                        int currentRandomIntsArray[] = courseIndexAL.get(0);
                        for (int i = 0; i < currentRandomIntsArray.length; i++) {
                            add(coursesAL.get(currentRandomIntsArray[i]));
                        }
                    }
                });
            }
        };

        return new ArrayList<Student>() {
            {
                add(st01);
                add(st02);
                add(st03);
                add(st04);
                add(st05);
                add(st06);
                add(st07);
                add(st08);
                add(st09);
                add(st10);
                add(st11);

            }
        };
    }

    private static ArrayList<Assignment> generateAssignments(ArrayList<Course> coursesAL) {

        Assignment indivProjectPartA = new Assignment() {
            {
                setTitle("Individual Project - Part A");
                setSubDateTime(LocalDate.of(2020, 1, 2));
                setCourses(coursesAL);
            }
        };
        Assignment indivProjectPartΒ = new Assignment() {
            {
                setTitle("Individual Project - Part B");
                setSubDateTime(LocalDate.of(2020, 1, 30));
                setCourses(coursesAL);
            }
        };
        Assignment assignment1HTMLjs = new Assignment() {
            {
                setTitle("Assignment 1 (HTML/js)");
                setSubDateTime(LocalDate.of(2020, 2, 14));
                setCourses(coursesAL);
            }
        };
        Assignment assignment2MVC = new Assignment() {
            {
                setTitle("Assignment 1 (MVC)");
                setSubDateTime(LocalDate.of(2020, 3, 27));
                setCourses(coursesAL);
            }
        };
        Assignment groupProject = new Assignment() {
            {
                setTitle("Group Project");
                setSubDateTime(LocalDate.of(2020, 5, 20));
                setCourses(coursesAL);
            }
        };
        return new ArrayList<Assignment>() {
            {
                add(indivProjectPartA);
                add(indivProjectPartΒ);
                add(assignment1HTMLjs);
                add(assignment2MVC);
                add(groupProject);
            }
        };

    }
}

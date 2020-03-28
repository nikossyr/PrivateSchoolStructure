/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that contains the methods to generate a Menu that gives the option to
 * the user to Show(print) the attributes or Add more.
 *
 * @author Nikos Syrios
 */
public class DataViewAddMenu {

    /**
     * Main method used to generate the Menu and Dialogue. It gives the user the
     * option to View (print) or Add more attributes.
     *
     * @param schoolDataAL all the ArrayLists of all the attributes the current
     *                     application holds
     */
    public static void generateMenu(ArrayList<ArrayList> schoolDataAL) {
        Scanner sc = new Scanner(System.in);

        System.out.println("What would you like to do next?");
        boolean isInputInvalid = true;
        while (isInputInvalid) {
            System.out.println("==================================");
            System.out.println("1 - View attributes (students, trainers, courses etc)");
            System.out.println("2 - Add more attributes");
            System.out.println("----------------------------------");
            System.out.println("0 - Discart saved items and start over");
            System.out.println("----------------------------------");
            if (sc.hasNextInt()) {
                int currentUserInput = sc.nextInt();
                switch (currentUserInput) {
                    case 0:
                        System.out.println("Are you sure you want to DELETE everything and start over? y/n");
                        if (SchoolUtils.getPolarDialogue()) {
                            MainMenu.generateMenu();
                        } else {
                            isInputInvalid = true;
                            System.out.println("What would you like to do next?");
                            break;
                        }
                    case 1:
                        isInputInvalid = false;
                        DataDisplayMenu.generateMenu(schoolDataAL);
                        break;
                    case 2:
                        DataInsertMenu.generateMenu(schoolDataAL);
                        isInputInvalid = false;
                        break;
                    default:
                        isInputInvalid = true;
                        sc.nextLine();
                        System.out.println("Please insert the number next to the option you want to choose");
                        break;
                }
            }
        }
    }

}

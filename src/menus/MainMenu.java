/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import schoolstructure.MockData;

import java.util.ArrayList;

/**
 * Class that contains the code to generate the Main Menu of the application.
 *
 * @author Nikos Syrios
 */
public class MainMenu {

    /**
     * It prints the initial dialogue for the application. It asks the user
     * whether they want to add data or use some mock data
     */
    public static void generateMenu() {

        System.out.println("Would you like to add your school's data "
                + "(courses, trainers, students, assignements)? y/n");
        ArrayList<ArrayList> schoolDataAL = new ArrayList<>();
        boolean polarDialogue = SchoolUtils.getPolarDialogue();
        if (polarDialogue) {
            schoolDataAL.addAll(DataInsertMenu.generateMenu());
        } else if (!polarDialogue) {
            System.out.println("Dummy data were inserted for you.");
            System.out.println("==================================");
            schoolDataAL.addAll(MockData.generateMockData());
        }
        DataViewAddMenu.generateMenu(schoolDataAL);

    }

}

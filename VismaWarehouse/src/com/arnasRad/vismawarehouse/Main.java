package com.arnasRad.vismawarehouse;

import com.arnasRad.vismawarehouse.model.Warehouse;
import com.arnasRad.vismawarehouse.utils.MenuUtilities;
import com.arnasRad.vismawarehouse.utils.Utilities;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {

        int option; // menu option
        while(true) {
            
            MenuUtilities.printMainMenu();
            option = Utilities.getIntInputBounded(0,
                    MenuUtilities.menuOptionCount, "Enter option");

            switch (option) {
                case MenuUtilities.MainOptions.LOAD:
                    try {
                        String fileName = Utilities.getFileNameInput();
                        Warehouse warehouse = Utilities.loadWarehouseItems(fileName);

                        boolean breakLoop = false;
                        while(!breakLoop) {
                            MenuUtilities.printSubMenu(fileName);
                            option = Utilities.getIntInputBounded(0,
                                    MenuUtilities.menuOptionCount,
                                    "Enter option");

                            switch (option) {
                                case MenuUtilities.SubOptions.LIST:
                                    warehouse.printItems();
                                    break;
                                case MenuUtilities.SubOptions.QUANTITIES:
                                    System.out.println("\nShows items with lower quantities than specified by the user.");
                                    System.out.print("Enter quantity: ");
                                    long quantity = Utilities.getLongInput();
                                    warehouse.printInsufficientQuantityItems(quantity);
                                    break;
                                case MenuUtilities.SubOptions.EXPIRES:

                                    warehouse.printExpiredItems(Utilities.getDateInput());
                                    break;
                                case MenuUtilities.SubOptions.SOON_TO_EXPIRE:

                                    warehouse.printSoonToExpireItems(Utilities.getDateInput());
                                    break;
                                case MenuUtilities.SubOptions.MAIN_MENU:
                                    breakLoop = true;
                                    break;
                            }
                        }

                    } catch (IOException | ParseException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case MenuUtilities.MainOptions.ABOUT:     // about warehouse system
                    printAbout();
                    System.out.println();
                    break;
                case MenuUtilities.MainOptions.QUIT:     // close the application
                    System.out.println("Closing the application...");
                    return;
            }
        }
    }

    /**
     * Print About information in console, specifying the purpose of this application
     * and how to use it
     */
    private static void printAbout() {
        System.out.println("\nABOUT\n");
        System.out.println("Warehouse System is an application used to " +
                "monitor warehouse items provided in input .csv file.\n" +
                "Application can only load files with .csv extension.\n" +
                "User (warehouse administrator) is able to:\n\t1) see what items " +
                "have insufficient quantities;\n\t2) get list of items that " +
                "expires by specified date.");

        System.out.println("\nHOW TO USE\n");
        System.out.println("* Enter corresponding integer values to console\n\t" +
                "to choose an option from menu or submenu");
        System.out.println("1) Enter option 1 to load data .csv file.");
        System.out.println("2) Enter a file name without specifying file " +
                "extension (.csv).\n\tExample: sample");
        System.out.println("3) If input file name is specified correctly,\n\t" +
                "user is taken to sub menu page.");
        System.out.println("4) Choose a corresponding option to process data\n\t" +
                " and see the results.");
        System.out.println("5) Enter 0 from main menu to quit the program or\n\t" +
                "enter 0 from sub menu to return to main menu and load another file");

    }
}

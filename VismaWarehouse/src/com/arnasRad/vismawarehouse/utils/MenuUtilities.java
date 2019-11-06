package com.arnasRad.vismawarehouse.utils;

public class MenuUtilities {

    /**
     * Option indexes for main menu
     */
    public static final class MainOptions {

        public static final byte LOAD = 1;
        public static final byte ABOUT = 2;
        public static final byte QUIT = 0;
    }

    /**
     * Option indexes for sub menu
     */
    public static final class SubOptions {

        public static final byte LIST = 1;
        public static final byte QUANTITIES = 2;
        public static final byte EXPIRES = 3;
        public static final byte SOON_TO_EXPIRE = 4;
        public static final byte MAIN_MENU = 0;
    }

    private static final int MENU_LINE_WIDTH = 35;      // menu width
    private static final String NUMBER_LABEL_TEMPLATE = " [%d]";    // template for menu option number String
    public static int menuOptionCount = -1; // option count in menu

    /**
     * Prints the main menu screen to the console
     */
    public static void printMainMenu() {
        try {
            menuOptionCount = -1;
            drawHorizontalLine();
            drawMenuLine(null);
            drawMenuLine("WAREHOUSE SYSTEM");
            drawMenuLine(null);
            drawMenuLine(null);
            drawMenuOptionLine("LOAD FILE", 1);
            drawMenuOptionLine("ABOUT", 2);
            drawMenuOptionLine("QUIT", 0);
            drawMenuLine(null);
            drawHorizontalLine();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Program terminated");
        }
    }

    /**
     * Prints the sub menu screen to the console
     * @param fileName name of the file that was loaded into the system
     */
    public static void printSubMenu(String fileName) {
        try {
            menuOptionCount = -1;
            drawHorizontalLine();
            drawMenuLine(null);
            drawMenuLine(fileName.toUpperCase());
            drawMenuLine(null);
            drawMenuLine(null);
            drawMenuOptionLine("ITEM LIST", 1);
            drawMenuOptionLine("INSUFFICIENT QUANTITIES", 2);
            drawMenuOptionLine("EXPIRED ITEMS", 3);
            drawMenuOptionLine("SOON TO EXPIRE ITEMS", 4);
            drawMenuOptionLine("MAIN MENU", 0);
            drawMenuLine(null);
            drawHorizontalLine();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Draws a horizontal star-line to the console
     * Mainly used for application menu
     */
    private static void drawHorizontalLine() {
        // + 2 characters for vertical lines on each side of the menu
        System.out.println(Utilities.getRepeatedCharString(MENU_LINE_WIDTH + 2, '*'));
    }

    /**
     * Adds a new line with text to the menu
     * null and "" as argument here works the same (outputs blank menu line)
     * @param text text to print to the menu
     * @throws Exception thrown when text size is greater than width of the menu
     */
    private static void drawMenuLine(String text) throws Exception {
        String textString = "";
        if (text != null) {
            textString = text;
        }

        if (textString.length() > MENU_LINE_WIDTH) {
            System.err.println();
            throw new Exception("ERROR in method drawMenuLine(): " +
                    "text string length is greater than menu line width");
        }

        StringBuilder sb = new StringBuilder();
        sb.append('*');
        int whiteSpaceLen = (MENU_LINE_WIDTH - textString.length()) / 2;

        sb.append(Utilities.getRepeatedCharString(whiteSpaceLen, ' '));
        sb.append(textString);
        sb.append(Utilities.getRepeatedCharString(MENU_LINE_WIDTH -
                textString.length() - whiteSpaceLen, ' '));
        sb.append('*');
        System.out.println(sb.toString());
    }

    /**
     * Adds a new option line to the menu
     * Options are numbered
     * @param option option text (label)
     * @param number option number
     * @throws Exception thrown when text size is greater than width of the menu
     */
    private static void drawMenuOptionLine(String option, int number) throws Exception {
        String numberLabel = String.format(NUMBER_LABEL_TEMPLATE, number);

        String optionString = "";
        if (option != null) {
            optionString = option;
        }

        if (optionString.length() > (MENU_LINE_WIDTH - numberLabel.length() - 1)) {
            throw new Exception("ERROR in method drawMenuLine(): " +
                    "option string length is greater than menu line width");
        }

        StringBuilder sb = new StringBuilder();
        sb.append('*');
        sb.append(numberLabel);
        ++menuOptionCount;
        int whiteSpaceLen = (MENU_LINE_WIDTH - optionString.length()) / 2 - numberLabel.length();
        if (whiteSpaceLen < 1) {
            whiteSpaceLen = 1;
        }

        sb.append(Utilities.getRepeatedCharString(whiteSpaceLen, ' '));
        sb.append(optionString);
        sb.append(Utilities.getRepeatedCharString(MENU_LINE_WIDTH - optionString.length() -
                whiteSpaceLen - numberLabel.length(), ' '));
        sb.append('*');
        System.out.println(sb.toString());
    }
}

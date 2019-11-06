package com.arnasRad.vismawarehouse.utils;

import com.arnasRad.vismawarehouse.model.Item;
import com.arnasRad.vismawarehouse.model.Warehouse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utilities {

    /**
     * column indexes
     */
    public static final class Indexes {
        public final static byte NAME_INDEX = 0;
        public final static byte CODE_INDEX = 1;
        public final static byte QUANTITY_INDEX = 2;
        public final static byte EXP_DATE_INDEX = 3;
    }

    private final static Scanner scanner = new Scanner(System.in);
    private final static String CSV_DELIMITER = ","; // delimiter for .csv input files
    public final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Reads data from input .csv file specified by parameter fileName,
     * processes it and returns Warehouse object
     *
     * @param fileName name of input file to load data from
     * @return Warehouse object that contains distinct items
     */
    public static Warehouse loadWarehouseItems(String fileName)
            throws IOException, ParseException {

        Warehouse warehouse = new Warehouse();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            // skip the first line (column names)
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] item = line.split(CSV_DELIMITER);
                String name = item[Indexes.NAME_INDEX];
                String code = item[Indexes.CODE_INDEX];
                long quantity = Long.parseLong(item[Indexes.QUANTITY_INDEX]);
                Date expirationDate = DATE_FORMAT.parse(item[Indexes.EXP_DATE_INDEX]);

                if (quantity <= 0) {
                    throw new IOException("Negative value specified for item" +
                            ". Name: " + name +
                            ", code: " + code);
                }
                warehouse.addItem(new Item(code, name, expirationDate), quantity);
            }
        }

        return warehouse;
    }

    /**
     * Gets a sorted items map String
     * Items are sorted firstly by name, then code and lastly by expiration date
     *
     * @param map     items list
     * @param caption caption that goes before item list String
     * @return Sorted items map String
     */
    public static String getItemMapString(LinkedHashMap<Item, Long> map, String caption) {

        StringBuilder sb = new StringBuilder();

        sb.append("\n").append(caption).append(":\n");

        if (map.size() == 0) {
            sb.append("There are no such items in warehouse.");
            return sb.toString();
        }

        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> sb
                        .append(e.getKey())
                        .append(", Quantity: ")
                        .append(e.getValue())
                        .append("\n"));

        return sb.toString();
    }

    /**
     * requests user integer input at bounds [start, end]
     * loops until user inputs an integer that satisfies bounds
     *
     * @param start  start of bounds
     * @param end    end of bounds
     * @param prompt text that's printed to the console before number input
     * @return option entered by user
     */
    public static int getIntInputBounded(int start, int end, String prompt) {
        while (true) {
//            System.out.println("Enter option: ");
            System.out.println(prompt + ": ");
            int option = Utilities.getIntInput();
            if (option < start || option > end)
                System.out.println("Enter a number between " + start + " and " + end);
            else
                return option;
        }

    }

    /**
     * requests user integer input
     * loops until user inputs an integer
     *
     * @return integer value entered by the user
     */
    public static int getIntInput() {

        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Enter an integer");
            scanner.nextLine();
        }

        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    /**
     * requests user long input
     * loops until user inputs a long integer
     *
     * @return long value entered by the user
     */
    public static long getLongInput() {

        while (!scanner.hasNextLong()) {
            System.out.println("Invalid input. Enter a long integer");
            scanner.nextLine();
        }

        long number = scanner.nextLong();
        scanner.nextLine();
        return number;
    }

    /**
     * Utility function used to get character-filled strings of required length
     *
     * @param n         length of the String to return
     * @param character characters that fill the returned String
     * @return String of length n filled with passed character values
     */
    public static String getRepeatedCharString(int n, char character) {

        return String.valueOf(character).repeat(Math.max(0, n));
    }

    /**
     * Utility function used to get file name input from user.
     * Prints a prompt text to console and waits for user input.
     * Text input is concatenated with ".csv" extension.
     *
     * @return user file name input concatenated with ".csv" extension
     */
    public static String getFileNameInput() {

        System.out.println("Enter input file name (excluding file extension):");
        String fileName = scanner.nextLine();
        return fileName.concat(".csv");
    }

    /**
     * Gets last day of month by specifying year and month values
     *
     * @param year  value to calculate last day of month
     * @param month value to calculate last day of month
     * @return last day of month integer
     */
    public static int getDayMaximum(int year, int month) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Gets a Date object by specifying year, month and day of month
     *
     * @param year       date value
     * @param month      date value
     * @param dayOfMonth date value
     * @return Date object
     */
    public static Date getDate(int year, int month, int dayOfMonth) {

        try {
            return DATE_FORMAT.parse(year + "-" + month + "-" + dayOfMonth);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Get date string formatted with default date formatter
     * @param date value to format string
     * @return String representing specified date
     */
    public static String getDateString(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static Date getDateInput() {

        int year = Utilities.getIntInputBounded(
                1990, Integer.MAX_VALUE, "Enter year");
        int month = Utilities.getIntInputBounded(
                1, 12, "Enter month");

        int dayMaxValue = Utilities.getDayMaximum(year, month-1);
        int dayOfMonth = Utilities.getIntInputBounded(
                1, dayMaxValue, "Enter day of month");

        return Utilities.getDate(year, month, dayOfMonth);
    }

}

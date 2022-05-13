/**
 * CS312 Assignment 8.
 *
 * On my honor, Sneha Kamal, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to read a file with raw data from a Keirsey personality test
 * and print out the results.
 *
 *  email address: snehakamal@utexas.edu
 *  UTEID: sk52223
 *  TA name: Emma
 *  Number of slip days used on this assignment: 0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Personality {

    // CS312 Student- Add your constants here.
    public static final int DIMENSIONS = 4;
    // The main method to process the data from the personality tests
    public static void main(String[] args) {
        // do not make any other Scanners connected to System.in
        // CS312 students - your code and methods calls go here
        Scanner keyboard = new Scanner(System.in);
        Scanner fileScanner = getFileScanner(keyboard);

        // gets number of records
        int numPairs = fileScanner.nextInt();
        fileScanner.nextLine();

        // runs until there are no more records to read
        for(int i = 1; i <= numPairs; i++){

            // counts number of b's and a's
            int[] countB = new int[DIMENSIONS];
            int [] countA = new int[DIMENSIONS];

            // calculates percentages of b answers
            int [] calcPercent = new int[DIMENSIONS];

            String answers = readFileLine(fileScanner);
            categories(answers, countB, countA);
            results(countB, countA, calcPercent);
        }
        fileScanner.close();
        keyboard.close();
    }


    // prints name of person, gets their answers
    public static String readFileLine (Scanner fileScanner){
        String name = fileScanner.nextLine();
        String answers = fileScanner.nextLine();
        System.out.printf("%30s", name);
        System.out.print(":");
        return answers;
    }

    // counts their answer choices for each personality category
    public static void categories (String answers, int[] countB, int [] countA){

        // start position for each category
        int startPosition = 0;
        int numQ = 7;

        // runs for starting question of each category
        for(int i = 0; i < numQ ; i++ ) {
            for (int j = startPosition; j < answers.length(); j += numQ) {
                if (answers.charAt(j) == 'B' || answers.charAt(j) == 'b') {
                    countB[(startPosition + 1) / 2]++;
                }
                else if (answers.charAt(j) == 'A' || answers.charAt(j) == 'a') {
                    countA[(startPosition + 1) / 2]++;
                }
            }

            // increments start position to next question
            if (startPosition < numQ - 1){
                startPosition++;
            }
        }
    }

    // calculates category percentages
    public static void results(int [] countB, int [] countA, int [] calcPercent){

        for (int i = 0; i < calcPercent.length ; i++){
            calcPercent [i] = (int) (((countB[i] + 0.0) / (countB[i] + countA[i])) * 100 + 0.5);

            // prints if no answers for category
            if(calcPercent[i] == 0){
                System.out.printf("%11s", "NO ANSWERS");
            }else {
                System.out.printf("%11s", calcPercent[i]);
            }
        }
        String personalityType = conclusion(calcPercent);
        System.out.println(" = " + personalityType);
    }

    // gets personality type
    public static String conclusion(int [] catPercent){
        String personalityType = "";
        int greaterPercent = 50;

        // personality dimensions if number of b's is majority
        String [] above50 = new String [] {"I", "N", "F", "P"};

        // personality dimensions if number of b's is minority
        String [] below50 = new String [] {"E", "S", "T", "J"};

        // assigns personality dimension based on percentage of b answers
        for(int i = 0; i < catPercent.length; i++){
            if(catPercent[i] > greaterPercent){
                personalityType += above50[i];
            }
            else if(catPercent[i] < greaterPercent && catPercent[i] != 0){
                personalityType += below50[i];
            }
            // runs if no answer given for category
            else if(catPercent[i] == 0){
                personalityType += "-";
            }
            // runs if percentage of answers is equal
            else{
                personalityType += "X";
            }
        }
        return personalityType;
    }

    // Method to choose a file.
    // Asks user for name of file.
    // If file not found create a Scanner hooked up to a dummy set of data
    // Example use:
    public static Scanner getFileScanner(Scanner keyboard){
        Scanner result = null;
        try {
            System.out.print("Enter the name of the file with"
                    + " the personality data: ");
            String fileName = keyboard.nextLine().trim();
            System.out.println();
            result = new Scanner(new File(fileName));
        } catch(FileNotFoundException e) {
            System.out.println("Problem creating Scanner: " + e);
            System.out.println("Creating Scanner hooked up to default data "
                    + e);
            String defaultData = "1\nDEFAULT DATA\n"
                    + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                    + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
            result = new Scanner(defaultData);
        }
        return result;
    }
}
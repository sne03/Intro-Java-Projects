/*
 *  CS312 Assignment 2.
 *  On my honor, Sneha Kamal, this programming assignment is my own work.
 *
 *  A program to print out the UT Tower in ASCII art form.
 *
 *  Name: Sneha Kamal
 *  email address: snehakamal@utexas.edu
 *  UTEID: sk52223
 *  Section 5 digit ID: 51095
 *  Grader name: Emma Simon
 *  Number of slip days used on this assignment: 1
 */

public class Tower {

    // CS312 students, DO NOT ALTER THE FOLLOWING LINE except for the
    // value of the literal int.
    // You may change the literal int assigned to SIZE to any value from 2 to 100.
    // In the final version of the program you submit set the SIZE to 3.
    public static final int SIZE = 3;
    public static final int BASE_LENGTH = SIZE * 10 + 3;
    public static final int BASE_CHAR = (BASE_LENGTH- 3) / 2;
    public static final int STEP_LINES = SIZE / 2 + 1;
    public static final int NUM_HASH = SIZE * 2 - 1;
    public static final int TOP_LAYER = SIZE * 2 - 2;
    public static final int PRONG_SPACER = BASE_LENGTH / 2 - NUM_HASH / 2;
    public static final int NUM_TILDA = SIZE * 2 + 3;
    public static final int BORDER_SPACER = BASE_LENGTH / 2 - NUM_TILDA / 2;
    public static final int BODY_SPACER = BASE_LENGTH / 2 - (SIZE * 2 + 3) / 2;
    // CS312 students, if you add any constants DO NOT include the word SIZE
    // (including any variations on capitilazations such as sIZE, etc.) in
    // the name of that constant. So for example nothing like:
    //
    // public static final int BASE_SIZE2 = 5;
    //
    // If you do, you will flummox our grading script
    // and lose correctness points.

    public static void main(String[] args) {
        top();
        border();
        body();
        steps();
        base();
    }
    //prints base
    public static void base() {
        for (int i = 0; i < SIZE; i++) {
            System.out.print("/");
            //prints number of lines in base
            for (int j = 0; j < BASE_CHAR; j++) {
                System.out.print("\"O");
                //prints number of characters in base
            }
            System.out.println("\"\\");
        }
    }

    //prints steps
    public static void steps() {
        int firstStepLength = BASE_LENGTH - ((STEP_LINES - 1) * 6);
        int stepSpacer = BASE_LENGTH / 2 - firstStepLength / 2;
        int charAmt = firstStepLength / 2 - 1;
        for (int i = 0; i < STEP_LINES; i++) {
            for (int l = 0; l < stepSpacer; l++) {
                System.out.print(" ");
                //prints out spaces in step
            }
            System.out.print("/");
            //prints number of steps
            for (int j = 0; j < charAmt; j++) {
                System.out.print("\"'");
                //prints step characters
            }
            firstStepLength += 6;
            charAmt = firstStepLength / 2 - 1;
            stepSpacer = BASE_LENGTH / 2 - firstStepLength / 2;
            System.out.println("\"\\");
            //decrements step length characters, sets step spaces
        }
    }

    //prints top of tower spaces
    public static void topSpacer() {
        int topSpace = BASE_LENGTH / 2 - NUM_HASH / 2;
        for (int k = 0; k < topSpace ; k++) {
            System.out.print(" ");
            //prints spaces
        }
    }
    public static void top() {
        topSpacer();
        for (int i = 0; i < NUM_HASH; i++) {
            System.out.print("#");
            //prints number of hashtags
        }
        topProngs();
        //prints vertical lines
        topSpacer();
        for (int i = 0; i < NUM_HASH; i++) {
            System.out.print("#");
            //prints number hashtags
        }
        System.out.println();
    }

    //prints vertical lines
    public static void topProngs() {
        for (int k = 0; k < TOP_LAYER; k++) {
            System.out.println();
            for (int l = 0; l < PRONG_SPACER; l++) {
                System.out.print(" ");
                //prints number of spaces and vertical lines
            }
            for (int j = 0; j < NUM_HASH; j++) {
                System.out.print("|");
                //prints vertical line characters
            }
        }
        System.out.println();
    }

    //prints spaces and tildas
    public static void border() {
        for (int l = 0; l < BORDER_SPACER; l++) {
            System.out.print(" ");
            //prints spaces
        }
        for (int i = 0; i < NUM_TILDA; i++) {
            System.out.print("~");
            //prints number of tildas
        }
        System.out.println();
    }

    //prints body of tower
    public static void body() {
        for (int i = 0; i < SIZE * SIZE; i++) {
            for (int l = 0; l < BODY_SPACER; l++) {
                System.out.print(" ");
                //prints spaces
            }
            System.out.print("|");
            //prints number of body layers
            for (int j = 0; j < SIZE ; j++) {
                System.out.print("-O");
                //prints characters in body
            }
            System.out.println("-|");
            border();
            //prints number of tildas and spaces
        }
    }
}




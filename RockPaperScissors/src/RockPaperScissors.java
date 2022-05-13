import java.util.Scanner;

/**
 * CS312 Assignment 4.
 *
 * On my honor, Sneha Kamal, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play Rock Paper Scissors
 *
 *  email address: snehakamal@utexas.edu
 *  UTEID: sk52223
 *  Number of slip days used on this assignment: 0
 */

public class RockPaperScissors {
    public static final int ROCK = 1;
    public static final int PAPER = 2;
    public static final int SCISSORS = 3;
    /* A program to allow a human player to play rock - paper - scissors
     * against the computer. If args.length != 0 then we assume
     * the first element of args can be converted to an int
     */
    public static void main(String[] args) {
        // CS312 Students. Do not change the following line.
        RandomPlayer computerPlayer = buildRandomPlayer(args);
        // CS312 students do not change the following line.
        // Do not create any other Scanners.
        Scanner keyboard = new Scanner(System.in);
        String playerName = getName(keyboard);
        int numRounds = roundsAmt(keyboard, playerName);
        rounds(keyboard, playerName, numRounds, computerPlayer);
        keyboard.close();
    }

    //gets player name
    public static String getName (Scanner keyboard){
        System.out.println("Welcome to ROCK PAPER SCISSORS. I, Computer, will be your opponent.");
        System.out.print("Please type in your name and press return: ");
        return keyboard.next();
    }

    //gets players number of rounds
    public static int roundsAmt (Scanner keyboard, String playerName){
        System.out.println();
        System.out.println("Welcome " + playerName + ".");
        System.out.println();
        System.out.println("All right " + playerName + ". How many rounds would you like to play?");
        System.out.print("Enter the number of rounds you want to play and press return: ");
        return keyboard.nextInt();
    }

    //prints rounds and results
    public static void rounds(Scanner keyboard, String playerName, int numRounds, RandomPlayer computerPlayer){
        int playerChoice;
        int computerChoice;
        int playerWinCount = 0;
        int compWinCount = 0;
        for(int i = 1; i <= numRounds; i++ ){
            System.out.println();
            System.out.println("Round " + i + ".");
            System.out.println(playerName + ", please enter your choice for this round.");
            System.out.print("1 for ROCK, 2 for PAPER, and 3 for SCISSORS: ");

            // gets player choice
            playerChoice = keyboard.nextInt();


            // gets computer choice
            computerChoice = computerPlayer.getComputerChoice();

            playerWinCount += playerWins(playerName, playerChoice, computerChoice);
            compWinCount += computerWins(playerName, computerChoice, playerChoice);
            if(playerChoice == computerChoice){
                draw(playerName, playerChoice);
            }
        }
        System.out.println();
        results(playerName, numRounds, compWinCount, playerWinCount);
    }

    //prints when player wins round, adds to player win count
    public static int playerWins (String playerName, int playerChoice, int computerChoice){
        if(computerChoice == SCISSORS && playerChoice == ROCK){
            System.out.println("Computer picked SCISSORS, " + playerName + " picked ROCK.");
            System.out.println();
            //public static final ROCK = 1;
            System.out.println("ROCK breaks SCISSORS. You win.");
            return 1;
        }
        else if (computerChoice == ROCK && playerChoice == PAPER ){
            System.out.println("Computer picked ROCK, " + playerName + " picked PAPER.");
            System.out.println();
            System.out.println("PAPER covers ROCK. You win.");
            return 1;
        }
        else if (computerChoice == PAPER && playerChoice == SCISSORS ){
            System.out.println("Computer picked PAPER, " + playerName + " picked SCISSORS.");
            System.out.println();
            System.out.println("SCISSORS cut PAPER. You win.");
            return 1;
        }
        return 0;
    }

    //prints when computer wins, adds to computer win count
    public static int computerWins (String playerName, int computerChoice, int playerChoice){
        if(computerChoice == ROCK && playerChoice == SCISSORS){
            System.out.println("Computer picked ROCK, " + playerName + " picked SCISSORS.");
            System.out.println();
            System.out.println("ROCK breaks SCISSORS. I win.");
            return 1;
        }
        else if (computerChoice == PAPER && playerChoice == ROCK){
            System.out.println("Computer picked PAPER, " + playerName + " picked ROCK.");
            System.out.println();
            System.out.println("PAPER covers ROCK. I win.");
            return 1;
        }
        else if (computerChoice == SCISSORS && playerChoice == PAPER){
            System.out.println("Computer picked SCISSORS, " + playerName + " picked PAPER.");
            System.out.println();
            System.out.println("SCISSORS cut PAPER. I win.");
            return 1;
        }
        return 0;
    }

    //prints when round is a draw
    public static void draw (String playerName, int playerChoice){
        String sameChoice;

        // sets same choice variable
        if(playerChoice == ROCK){
            sameChoice = "ROCK";
        }
        else if(playerChoice == PAPER){
            sameChoice = "PAPER";
        }
        else{
            sameChoice = "SCISSORS";
        }
        System.out.println("Computer picked " + sameChoice + ", " + playerName + " picked " + sameChoice + ".");
        System.out.println();
        System.out.println("We picked the same thing! This round is a draw.");
    }

    //prints game results
    public static void results (String playerName, int numRounds, int compWinCount, int playerWinCount){
        int numDraws = numRounds - compWinCount - playerWinCount;
        System.out.println();
        System.out.println("Number of games of ROCK PAPER SCISSORS: " + numRounds);
        System.out.println("Number of times Computer won: " + compWinCount);
        System.out.println("Number of times " + playerName + " won: " + playerWinCount);
        System.out.println("Number of draws: " + numDraws);
        if(compWinCount > playerWinCount){
            System.out.print("I, Computer, am a master at ROCK, PAPER, SCISSORS.");
        }
        else if(playerWinCount > compWinCount){
            System.out.print("You, " + playerName + ", are a master at ROCK, PAPER, SCISSORS.");
        }
        else{
            System.out.print("We are evenly matched.");
        }
    }

    /*
     * Build the random player. If args is length 0 then the
     * default RandomPlayer is built that follows a predictable
     * sequence. If args.length > 0 then we assume we can
     * convert the first element to an int and build the
     * RandomPlayer with that initial value.
     */
    public static RandomPlayer buildRandomPlayer(String[] args) {
        if(args.length == 0) {
            return new RandomPlayer();
        } else {
            int seed = Integer.parseInt(args[0]);
            return new RandomPlayer(seed);
        }
    }
}


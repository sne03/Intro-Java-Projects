/**
 * CS312 Assignment 6.
 *
 * On my honor, SNEHA KAMAL, this programming assignment is my own work
 * and I have not shared my solution with any other student in the class.
 *
 * A program to play Hangman.
 *
 *  email address: snehakamal@utexas.edu
 *  UTEID: sk52223
 *  Unique 5 digit course ID: 51095
 *  Number of slip days used on this assignment: 2
 */

import java.util.Scanner;

public class Hangman {

    //keeps track of amount of wrong guesses
    public static int numWrong;

    public static void main(String[] args) {
        PhraseBank phrases = buildPhraseBank(args);
        Scanner keyboard = new Scanner(System.in);
        String playAgain = "y";
        String alphabet;
        String randTopic;
        // runs if player wants to play or play again
        intro();
        while(playAgain.equals("y")|| playAgain.equals("Y")){
            randTopic = phrases.getTopic();
            alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            numWrong = 0;
            System.out.println();
            System.out.println("I am thinking of a " + randTopic + " ...");
            System.out.println();
            String randPhrase = phrases.getNextPhrase();
            String currPhrase = initHiddenPhrase(randPhrase);
            // lets player guess if they haven't lost or won
            while(numWrong < 5 && !currPhrase.equals(randPhrase)){
                // display current phrase
                System.out.println("The current phrase is " + currPhrase);
                System.out.println();
                // display letters not guessed
                initAlphaBank(alphabet);
                // get guess from user until it is a valid guess
                char letterGuessed = guess(keyboard, alphabet);
                // says if guess is present in secret phrase
                alphabet = randPhraseContains(randPhrase, letterGuessed, alphabet);
                // update and display wrong guesses so far
                System.out.println("Number of wrong guesses so far: " + numWrong);
                // update currPhrase
                currPhrase = makeGuess(randPhrase, letterGuessed, currPhrase);
            }
            playAgain = conclusion(keyboard, numWrong, randPhrase, currPhrase);
        }
        keyboard.close();
    }

    // creates hidden phrase from random phrase
    public static String initHiddenPhrase (String randPhrase){
        String starPhrase = "";
        for(int i = 0; i < randPhrase.length(); i++){
            if(randPhrase.charAt(i) == '-' || randPhrase.charAt(i) == '_'){
                starPhrase += randPhrase.charAt(i);
            }else{
                starPhrase += "*";
            }
        }
        return starPhrase;
    }

    // displays letters not guessed
    public static void initAlphaBank (String alphabet){
        String currAlphabet = "";
        System.out.println("The letters you have not guessed yet are: ");
        currAlphabet += alphabet.charAt(0);
        for(int i = 1; i < alphabet.length(); i++){
            currAlphabet += "--" + alphabet.charAt(i);
        }
        System.out.println(currAlphabet);
        System.out.println();
    }

    // gets player guess
    public static char guess(Scanner keyboard, String alphabet) {
        System.out.print("Enter your next guess: ");
        String enterGuess = keyboard.next();
        System.out.println();
        String capsGuess = enterGuess.toUpperCase();
        char guess = capsGuess.charAt(0);
        // runs if guess is not valid, prompts until guess is valid
        while(alphabet.indexOf(guess) == -1){
            System.out.println(enterGuess + " is not a valid guess.");
            initAlphaBank(alphabet);
            System.out.print("Enter your next guess: ");
            enterGuess = keyboard.next();
            System.out.println();
            capsGuess = enterGuess.toUpperCase();
            guess = capsGuess.charAt(0);
        }
        System.out.println("You guessed " + guess + ".");
        System.out.println();
        return guess;
    }

    // checks if random phrase has player's guess
    public static String randPhraseContains (String randPhrase, char enteredGuess, String alphabet){
        if(randPhrase.indexOf(enteredGuess) == -1) {
            System.out.println("That is not present in the secret phrase.");
            System.out.println();
            numWrong++;
        }else{
            System.out.println("That is present in the secret phrase.");
            System.out.println();
        }
        // returns new alphabet list
        int alphInd = alphabet.indexOf(enteredGuess);
        String firstHalf = (alphabet.substring(0,alphInd));
        String secondHalf = (alphabet.substring(alphInd + 1));
        return firstHalf + secondHalf;
    }

    // creates current phrase based on player guess
    public static String makeGuess(String randPhrase, char letterGuessed, String currPhrase){
        String newPhrase = "";
        for(int i = 0; i < randPhrase.length(); i++){
            if(randPhrase.charAt(i) == letterGuessed) {
                newPhrase += letterGuessed;
            }else{
                newPhrase += currPhrase.charAt(i);
            }
        }
        return newPhrase;
    }

    //prints results
    public static String conclusion(Scanner keyboard, int numWrong, String randPhrase, String currPhrase){
        if (numWrong < 5 && randPhrase.equals(currPhrase)){
            System.out.println("The phrase is " + randPhrase + ".");
            System.out.println("You win!!");
        }else{
            System.out.println("You lose. The secret phrase was " + randPhrase);
        }
        System.out.println("Do you want to play again?");
        System.out.print("Enter 'Y' or 'y' to play again: ");
        String playAgain = keyboard.next();
        return playAgain;
    }
    // Build the PhraseBank.
    // If args is empty or null, build the default phrase bank.
    // If args is not null and has a length greater than 0
    // then the first elements is assumed to be the name of the
    // file to build the PhraseBank from.
    public static PhraseBank buildPhraseBank(String[] args) {
        PhraseBank result;
        if (args == null || args.length == 0
                || args[0] == null || args[0].length() == 0) {
            result =  new PhraseBank();
            /* CS312 students, uncomment the following line if you
             * would like less predictable behavior from the PhraseBank
             * during testing. NOTE, this line MUST be commented out
             * in the version of the program you turn in
             * or your will fail all tests.
             */
            // result = new PhraseBank("HangmanMovies.txt", true); // MUST be commented out in version you submit.
        }else{
            result = new PhraseBank(args[0]);
        }

        return result;
    }

    // Print the intro to the program.
    public static void intro() {
        System.out.println("This program plays the game of hangman.");
        System.out.println();
        System.out.println("The computer will pick a random phrase.");
        System.out.println("Enter letters for your guess.");
        System.out.println("After 5 wrong guesses you lose.");
    }
}
import java.util.Scanner;
/**
 * CS312 Assignment 10.
 *
 * On my honor, Sneha Kamal, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 *  email address: snehakamal@utexas.edu
 *  UTEID: sk52223
 *  TA name: Emma
 *  Number of slip days used on this assignment: 1
 *
 * Program that allows two people to play Connect Four.
 */


public class ConnectFour {
public static final int NUM_ROWS = 6;
public static final int NUM_COLUMNS = 7;
public static final int NUM_FOR_WIN = 4;
    // CS312 Students, add you constants here

    public static void main(String[] args) {
        Scanner key = new Scanner (System.in);
        intro();
        // complete this method
        // Recall make and use one Scanner connected to System.in
        char [][] gameBoard = new char [NUM_ROWS][NUM_COLUMNS];

        // gets players and prints initial game board
        String playerOne = playerOne(key);
        String playerTwo = playerTwo(key);
        getGameBoard(gameBoard);
        printGameBoard(gameBoard, false);
        boolean winFound = false;
        int roundsPlayed = 0;
        String currentPlayer = "";

        // plays game until win is found or board is filled
        while(!winFound && roundsPlayed < NUM_ROWS * NUM_COLUMNS){
            currentPlayer = getPlayer(playerOne, playerTwo, roundsPlayed);
            char playerPiece = getPlayerPiece(playerOne, currentPlayer);
            winFound = playTurn(currentPlayer, playerPiece, key, gameBoard, roundsPlayed);
            roundsPlayed++;
            if(!winFound && roundsPlayed < NUM_ROWS * NUM_COLUMNS){
                printGameBoard(gameBoard, false);
            }
        }
        if(winFound){
            winGame(currentPlayer, gameBoard);
        }else{
            draw(gameBoard);
        }

    }
    // CS312 Students, add your methods

    // gets player one
    public static String playerOne(Scanner key){
        System.out.print("Player 1 enter your name: ");
        String playerOne = key.nextLine();
        System.out.println();
        return playerOne;
    }

    // gets player two
    public static String playerTwo (Scanner key){
        System.out.print("Player 2 enter your name: ");
        String playerTwo = key.nextLine();
        return playerTwo;
    }

    // get initial game board
    public static void getGameBoard (char [][] gameBoard){
        for (int rows = 0; rows < gameBoard.length; rows++){
            for (int columns = 0; columns <= gameBoard.length; columns ++){
                gameBoard [rows][columns] = '.';
            }
        }
    }

    // prints game board
    public static void printGameBoard (char [][] gameBoard, boolean endGame){
        String boardVersion;

        // gets game board version
        if(!endGame){
            boardVersion = "Current";
        }else{
            boardVersion = "Final";
        }
        System.out.println();
        System.out.println(boardVersion+ " Board");
        System.out.println("1 2 3 4 5 6 7  column numbers");

        // prints game board
        for (int rows = 0; rows < gameBoard.length; rows++){
            for (int columns = 0; columns <= gameBoard.length; columns++){
                System.out.print(gameBoard[rows][columns]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // gets which player is currently playing
    public static String getPlayer (String playerOne, String playerTwo, int roundsPlayed){
        String currentPlayer;
        if(roundsPlayed % 2 == 0){
            currentPlayer = playerOne;
        }else{
            currentPlayer = playerTwo;
        }
        return currentPlayer;
    }

    // gets player piece based on current player
    public static char getPlayerPiece (String playerOne, String currentPlayer){
        char playerPiece;
        if(currentPlayer.equals(playerOne)){
            playerPiece = 'r';
        } else{
            playerPiece = 'b';
        }
        return playerPiece;
    }

    // plays each player turn
    public static boolean playTurn(String currentPlayer, char playerPiece, Scanner key,
                                   char [][] gameBoard, int roundsPlayed){
        int chosenColumn;
        int rowIndex = gameBoard.length - 1;
        boolean winFound;
        String prompt = currentPlayer + ", enter the column to drop your checker: ";
        System.out.println(currentPlayer + " it is your turn.");
        System.out.println("Your pieces are the " + playerPiece + "'s.");
        System.out.print(prompt);
        chosenColumn = getInt(key, prompt);

        // prompts player to input column until it is valid
        while(!fineChoice(chosenColumn, currentPlayer, gameBoard)) {
            chosenColumn = getInt(key, prompt);
        }

        int columnIndex = chosenColumn- 1;

        // finds free row for player's chosen column
        while(gameBoard[rowIndex][columnIndex] != '.' && rowIndex > 0){
            rowIndex--;
        }
        gameBoard[rowIndex][columnIndex] = playerPiece;

        // checks if there is a win
        winFound = checkWin(rowIndex, gameBoard, columnIndex, playerPiece, roundsPlayed);
        return winFound;
    }

    // checks for vertical wins
    public static boolean checkVertical(char [][] gameBoard, int rowIndex, int columnIndex, char playerPiece){
        int numInRow = 0;
        for(int i = rowIndex; numInRow < NUM_FOR_WIN && i < NUM_ROWS; i++){
            if(gameBoard[i][columnIndex] == playerPiece){
                numInRow++;
            }else{
                numInRow = 0;
            }
        }
        return numInRow >= NUM_FOR_WIN;
    }

    // checks for horizontal wins
    public static boolean checkHorizontal(char [][] gameBoard, int rowIndex , char playerPiece){
        int numInRow = 0;
        for(int i = 0; i < gameBoard[0].length; i++){
            if(gameBoard[rowIndex][i] == playerPiece){
                numInRow++;
                if(numInRow == NUM_FOR_WIN){
                    return true;
                }
            }else{
                numInRow = 0;
            }
        }
        return false;
    }

    // checks the left to right diagonal
    public static boolean checkLToR(int currRow, int currColumn, char[][] gameBoard, char playerPiece){
        int numInLine = 0;
        int counter = 0;
        while(currRow + counter < gameBoard.length && currColumn + counter < gameBoard[0].length){
            if(gameBoard[currRow + counter][currColumn + counter] == playerPiece){
                numInLine++;
                if(numInLine == NUM_FOR_WIN){
                    return true;
                }
            }else{
                numInLine = 0;
            }
            counter++;
        }
        return false;
    }

    // checks right to left diagonal
    public static boolean checkRToL(int currRow, int currColumn, char[][] gameBoard, char playerPiece){
        int numInLine = 0;
        int counter = 0;
        while(currRow + counter < gameBoard.length && currColumn - counter >= 0){
            if(gameBoard[currRow + counter][currColumn - counter] == playerPiece){
                numInLine++;
                if(numInLine == NUM_FOR_WIN){
                    return true;
                }
            }else{
                numInLine = 0;
            }
            counter++;
        }
        return false;
    }

    // checks for diagonal wins
    public static boolean checkDiagonal(char [][] gameBoard, int rowIndex, int columnIndex, char playerPiece){
        // go to top left edge of board to start check
        int currColumn = columnIndex;
        int currRow = rowIndex;

        // finds start position of left to right diagonal where piece is found
        while(currColumn > 0 && currRow > 0){
            currColumn--;
            currRow--;
        }
        if(checkLToR(currRow, currColumn, gameBoard, playerPiece)){
            return true;
        }

        currColumn = columnIndex;
        currRow = rowIndex;

        // finds start position of right to left diagonal where piece is found
        while(currRow > 0 && currColumn < gameBoard[0].length - 1){
            currColumn++;
            currRow--;
        }
        if(checkRToL(currRow, currColumn, gameBoard, playerPiece)){
            return true;
        }
        return false;
    }

    // checks all directions for a win
    public static boolean checkWin(int rowIndex, char [][] gameBoard, int columnIndex, char playerPiece,
                                int roundsPlayed){
        boolean winFound;
        boolean vertWin = false;
        boolean horizWin = false;
        boolean diagWin = false;

        // checks for win if at least four rounds have been played
        if(roundsPlayed >= NUM_FOR_WIN){
            vertWin = checkVertical(gameBoard, rowIndex, columnIndex, playerPiece);
            horizWin = checkHorizontal(gameBoard, rowIndex, playerPiece);
            diagWin = checkDiagonal(gameBoard, rowIndex, columnIndex, playerPiece);
        }
        winFound = vertWin || horizWin || diagWin;
        return winFound;
    }

    // checks if player column input is valid
    public static boolean fineChoice(int chosenColumn, String currentPlayer, char[][]gameBoard){
        if(chosenColumn < 1 || chosenColumn > NUM_COLUMNS) {
            System.out.println();
            System.out.println(chosenColumn + " is not a valid column.");
            System.out.print(currentPlayer + ", enter the column to drop your checker: ");
            return false;
        }
        if(gameBoard[0][chosenColumn - 1] != '.'){
            System.out.println();
            System.out.println(chosenColumn + " is not a legal column. That column is full");
            System.out.print(currentPlayer + ", enter the column to drop your checker: ");
            return false;
        }
        return true;
    }

    // runs if game is won
    public static void winGame (String currentPlayer, char[][] gameBoard){
        System.out.println();
        System.out.println(currentPlayer + " wins!!");
        printGameBoard(gameBoard, true);
    }

    // runs if game is a draw
    public static void draw(char [][] gameBoard){
        System.out.println();
        System.out.println("The game is a draw.");
        printGameBoard(gameBoard, true);
    }

    // show the intro
    public static void intro() {
        System.out.println("This program allows two people to play the");
        System.out.println("game of Connect four. Each player takes turns");
        System.out.println("dropping a checker in one of the open columns");
        System.out.println("on the board. The columns are numbered 1 to 7.");
        System.out.println("The first player to get four checkers in a row");
        System.out.println("horizontally, vertically, or diagonally wins");
        System.out.println("the game. If no player gets fours in a row and");
        System.out.println("and all spots are taken the game is a draw.");
        System.out.println("Player one's checkers will appear as r's and");
        System.out.println("player two's checkers will appear as b's.");
        System.out.println("Open spaces on the board will appear as .'s.\n");
    }


    // Prompt the user for an int. The String prompt will
    // be printed out. I expect key is connected to System.in.
    public static int getInt(Scanner key, String prompt) {
        while(!key.hasNextInt()) {
            String notAnInt = key.nextLine();
            System.out.println();
            System.out.println(notAnInt + " is not an integer.");
            System.out.print(prompt);
        }
        int result = key.nextInt();
        key.nextLine();
        return result;
    }
}

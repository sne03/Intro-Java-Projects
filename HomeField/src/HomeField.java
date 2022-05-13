import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * CS312 Assignment 7.
 *
 * On my honor, Sneha Kamal, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play determine the extend of home field advantage in sports.
 *
 *  email address: snehakamal@utexas.edu
 *  UTEID: sk52223
 *  Unique 5 digit course ID: 51095
 *  Grader name: Emma
 *  Number of slip days used on this assignment: 0
 */

/**
 * Analysis of results. Include your write-up of results and analysis here:
 *
 * mlb12.txt results:
 * Total number of games: 2,467
 * Number of games with a home team: 2,465
 * Percentage of games with a home team: 99.9%
 * Number of games the home team won: 1,312
 * Home team win percentage: 53.2%
 * Home team average margin: 0.16
 *
 * mscc06.txt results:
 * Total number of games: 8,380
 * Number of games with a home team: 7,373
 * Percentage of games with a home team: 88.0%
 * Number of games the home team won: 3,962
 * Home team win percentage: 53.7%
 * Home team average margin: 0.51
 *
 * wbb14.txt results:
 * Total number of games: 15,790
 * Number of games with a home team: 14,305
 * Percentage of games with a home team: 90.6%
 * Number of games the home team won: 8,471
 * Home team win percentage: 59.2%
 * Home team average margin: 4.24
 *
 * wscc10.txt results:
 * Total number of games: 10,593
 * Number of games with a home team: 9,941
 * Percentage of games with a home team: 93.8%
 * Number of games the home team won: 5,392
 * Home team win percentage: 54.2%
 * Home team average margin: 0.51
 *
 * Analysis of results:
 *
 * Based on the larger files, we can see that the great majority of the games played had home teams because the
 * percentages of games with a home team are all above 88%, with most in the 90th percentile. It is difficult to tell,
 * however, if there is a home field advantage in all sports. Taking a look at the percentages of the games the home
 * team won, the percentages are close to 50% and no greater than the 60th percentile. Thus, it can be concluded that
 * there is no significant advantage because the home team is winning roughly half of the time out of all the games
 * they have played. Since there is a high percentage of games with a home team, there is a larger sample size to look
 * at to consider if a home field advantage exists, so the conclusion derived is more accurate because it is based on
 * more data.
 *
 * The one exception to this is if you take a look at the smaller sample file for Women's basketball
 * 2011-2012. The percentage of games with a home team is in the 60th percentile, and the home team win percentage is
 * higher than 50%, it is around 67%. If solely considering this data set to determine whether a home field advantage
 * exists in all sports, it seems as though there is a home field advantage, but considering the larger data files with
 * a greater amount of home games, it disproves this idea.
 *
 * Another quantitative value that supports this conclusion is if you consider the home team average margin for
 * each of the sports. The average margin is less than 1 except for women's basketball 2013-2014 and women's basketball
 * 2011-2012. The average margin can tell us the average of the point difference between the home team points and away
 * team points for that sport during the designated season. Since those values are less than one, it also supports
 * that there is an insignificant home field advantage. However, one could argue that for Women's basketball, a home
 * field advantage is present. Both of those data sets show that the home team win percentage is around or in the 60th
 * percentile and the home team average margin is greater than 4. These values demonstrate that the home team is
 * outperforming the away team on their home field, and averaging around 4-6 points higher than the away team during the
 * corresponding season. This can support the claim that for women's basketball specifically, there could be a home
 * field advantage.
 */

public class HomeField {
    // Ask the user for the name of a data file and process
    // it until they want to quit.
    public static void main(String[] args) throws IOException {
        System.out.println("A program to analyze home field advantage in sports.");
        // CS312 students. Do not create any other Scanners connected to System.in.
        // Pass keyboard as a parameter to all the methods that need it.
        Scanner keyboard = new Scanner(System.in);
        // CS312 students - Add your code here
        String processFile;
        processFile = "y";
        // analyzes file if user chooses to
        while(processFile.equals("y") || processFile.equals("Y")) {
            System.out.println();
            File validFile = getFile(keyboard);
            Scanner read = fileHeader(validFile);
            readFile(read);
            processFile = doAgain(keyboard);
        }
        keyboard.close();
    }

    // gets file
    public static File getFile (Scanner keyboard) {
        System.out.print("Enter the file name: ");
        String chosenFile = keyboard.nextLine();
        File fileInput = new File(chosenFile);
        // checks if file exists
        while(!fileInput.exists()){
            System.out.println("Sorry, that file does not exist");
            System.out.print("Enter the file name: ");
            chosenFile = keyboard.nextLine();
            fileInput = new File(chosenFile);
        }
        return fileInput;
    }

    // prints file header
    public static Scanner fileHeader (File validFile) throws IOException{
        Scanner read = new Scanner(validFile);
        String firstLine = read.nextLine();
        String secondLine = read.nextLine();
        System.out.println();
        System.out.println("**********   " + firstLine + " --- " + secondLine + "   **********");
        System.out.println();
        System.out.println("HOME FIELD ADVANTAGE RESULTS");
        System.out.println();
        return read;
    }

    // analyzes game
    public static void readFile (Scanner read){
        double avgMargin = 0;
        int totalGames = 0;
        int homeGames = 0;
        int homeTeamWon = 0;
        String nextLine;
        while(read.hasNextLine()){
            nextLine = read.nextLine();
            totalGames ++;
            // reads file for home games
            if(nextLine.contains("@")){
                homeGames++;
                // reads each home game file line
                int gameDiff = homeGameAnalysis(nextLine);
                // adds point difference
                avgMargin += gameDiff;
                    if(gameDiff > 0){
                        homeTeamWon++;
                    }
            }
        }
        avgMargin = avgMargin / homeGames;
        double homeTeamPercent = ((homeGames + 0.0) / totalGames) * 100;
        double homeTeamWins = ((homeTeamWon + 0.0 ) / homeGames * 100);
        printAnalysis(totalGames, homeGames, homeTeamPercent, homeTeamWon, homeTeamWins, avgMargin);
    }

    // analyzes file lines with a home game
    public static int homeGameAnalysis (String nextLine){
        Scanner readLine = new Scanner(nextLine);
        readLine.next();
        String team1 = "";
        int pointDiff;
        //gets team names and scores
        while(!readLine.hasNextInt()){
            team1 += readLine.next();
        }
        int t1Score = readLine.nextInt();
        while(!readLine.hasNextInt()){
            readLine.next();
        }
        int t2Score = readLine.nextInt();
        // calculates point difference
        if(team1.contains("@")){
            pointDiff = t1Score - t2Score;
        }else{
            pointDiff = t2Score - t1Score;
        }
        return pointDiff;
    }

    // prints file analysis statements
    public static void printAnalysis (int totalGames, int homeGames, double homeTeamPercent, int homeTeamWon,
                                      double homeTeamWins, double avgMargin){
        System.out.print("Total number of games: ");
        System.out.printf("%,d\n" , totalGames);
        System.out.print("Number of games with a home team: ");
        System.out.printf("%,d\n" , homeGames);
        System.out.print("Percentage of games with a home team: ");
        System.out.printf("%,.1f" , homeTeamPercent);
        System.out.print("%");
        System.out.println();
        System.out.print("Number of games the home team won: ");
        System.out.printf("%,d\n" , homeTeamWon);
        System.out.print("Home team win percentage: ");
        System.out.printf("%,.1f" , homeTeamWins);
        System.out.print("%");
        System.out.println();
        System.out.print("Home team average margin: ");
        System.out.printf("%,.2f" , avgMargin);
        System.out.println();
    }

    // prompts user if they want to check another file
    public static String doAgain (Scanner keyboard){
        System.out.println();
        System.out.println("Do you want to check another data set?");
        System.out.print("Enter Y or y to analyze another file, anything else to quit: ");
        return keyboard.nextLine();
    }
}
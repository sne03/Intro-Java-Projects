import java.util.Arrays;
import java.util.Scanner;

/**
 * CS312 Assignment 9.
 *
 * On my honor, Sneha Kamal, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 *  email address: snehakamal@utexas.edu
 *  UTEID: sk52223
 *  TA name: Emma
 *  Number of slip days used on this assignment: 0
 *
 * Program to decrypt a message that has been
 * encrypted with a substitution cipher.
 * We assume only characters with ASCII codes
 * from 32 to 126 inclusive have been encrypted.
 */

public class Decrypt {

    public static final int ASCII_CHAR = 128;
    public static final int ASCII_START = 32;
    public static final int ASCII_STOP = 126;

    public static void main(String[] arg) {
        // CS312 Students, do not create any other Scanners connected to System.in
        Scanner keyboard = new Scanner(System.in);
        String fileName = getFileName(keyboard);
        String encryptedText = DecryptUtilities.convertFileToString(fileName);
        // The other method from DecryptUtilities you will have to use is
        // DecryptUtilities.getDecryptionKey(int[]), but first you need to
        // create an array with the frequency of all the ASCII characters in the
        // encrypted text. Count ALL characters from ASCII code 0 to ASCII code 127

        System.out.println("The encrypted text is:");
        System.out.println(encryptedText);

        // stores frequency of characters
        int [] frequencyTable = new int[ASCII_CHAR];

        getFreq(frequencyTable, encryptedText);
        printFreq(frequencyTable);

        // stores encryption key
        char [] encryptKey = DecryptUtilities.getDecryptionKey(frequencyTable);
        
        encryptFile(encryptKey);
        decryptText(encryptedText, encryptKey, false);
        System.out.println("Do you want to make a change to the key?");
        System.out.print("Enter 'Y' or 'y' to make change: ");
        char userChoice = keyboard.next().charAt(0);

        // runs while user wants to change key
        while(userChoice == 'Y' || userChoice == 'y') {
            changeKey (keyboard, encryptedText,encryptKey);
            System.out.println("Do you want to make a change to the key?");
            System.out.print("Enter 'Y' or 'y' to make change: ");
            userChoice = keyboard.next().charAt(0);
        }
        decryptText(encryptedText, encryptKey, true);
        keyboard.close();
    }

    // gets frequencies of characters
    public static void getFreq (int [] frequencyTable, String encryptedText){
        for(int i = 0; i < encryptedText.length(); i++){
            frequencyTable[encryptedText.charAt(i)]++;
        }
    }

    // prints frequencies
    public static void printFreq (int [] frequencyTable){
        System.out.println("Frequencies of characters.");
        System.out.println("Character - Frequency");
        for(int i = ASCII_START; i <= ASCII_STOP; i++){
            System.out.println((char)i + " - " + frequencyTable[i]);
        }
    }

    // prints encryption key
    public static void encryptFile (char [] encryptKey){
        System.out.println();
        System.out.println("The current version of the key for ASCII characters 32 to 126 is: ");
        for(int i = ASCII_START; i <= ASCII_STOP; i++){
            System.out.println("Encrypt character: " + (char)i + ", decrypt character: " + encryptKey[i]);
        }
    }

    // prints decrypted text
    public static void decryptText (String encryptedText, char [] encryptKey, boolean atEnd){
        String version = "current";

        // runs if user does not want to change encryption key
        if (atEnd){
            version = "final";
            encryptFile(encryptKey);
        }

        String decryptedText = "";
        int indexChar;
        for(int i = 0; i < encryptedText.length(); i++ ){
            indexChar = encryptedText.charAt(i);
            decryptedText += encryptKey[indexChar];
        }
        System.out.println();
        System.out.println("The " + version + " version of the decrypted text is: ");
        System.out.println();
        System.out.println(decryptedText);
    }

    // changes encryption key
    public static void changeKey (Scanner keyboard, String encryptedText, char [] encryptKey){
        System.out.print("Enter the decrypt character you want to change: ");
        char changeChar = keyboard.next().charAt(0);
        System.out.print("Enter what the character " + changeChar + " should decrypt to instead: ");
        char replaceChar = keyboard.next().charAt(0);
        System.out.println(changeChar + "'s will now decrypt to " + replaceChar + "'s and vice versa.");
        int changeCharFound = -1;
        int replaceCharFound = -1;

        // swaps characters in key
        for(int i = ASCII_START; changeCharFound < 0 || replaceCharFound < 0; i++){
            if(encryptKey[i] == changeChar){
                changeCharFound = i;
            }
            if(encryptKey[i] == replaceChar){
                replaceCharFound = i;
            }
        }
        char temp = encryptKey[changeCharFound];
        encryptKey[changeCharFound] = replaceChar;
        encryptKey[replaceCharFound] = temp;
        decryptText(encryptedText, encryptKey, false);
    }

    // Get the name of file to use. For the assignment no error
    // checking is required.
    public static String getFileName(Scanner kbScanner) {
        System.out.print("Enter the name of the encrypted file: ");
        String fileName = kbScanner.nextLine().trim();
        System.out.println();
        return fileName;
    }
}

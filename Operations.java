import java.util.Random;
import java.util.Scanner;

        /*
        *	Author:	Nyland Sidifall
		*	Date:	08/27/17
		*	Assignment:		Program	#1	- Battleship
		*	File:	operations.java
		*	Description:		This program will recreate a version of the game battleship in the command line.
		    This class contains the different operations in order for the game to play.
		*/

public class Operations {

    private static char[][] playerBoard;    //The player's battleship board
    private static char[][] compBoard;      //The computer's battleship board
    private char playerLetter;              //A placeholder for value for placing ships on the player's board
    private char playerNumber;              //A placeholder for value for placing ships on the player's board
    private char indexLetter;               //A placeholder for value for placing ships on the either player or the computer's board
    private char indexNumber;               //A placeholder for value for placing ships on the either player or the computer's board
    private String placeHolderString;       //A placeholder value that is used to convert string to char in later methods
    private int numPlayerShips = 5;         //The total number of ship spaces in on the player's board
    private int numCompShips = 5;           //The total number of ship spaces in on the computer's board
    private boolean gameOver = true;        //A value to sense if the game is over

    private Scanner scnr = new Scanner(System.in);  //This is a scanner for the player to enter values


    //This method start's the game.
    /*
    * Here, the player and computer boards will be instantiated and populated with sea tiles, "~".
    * Then the player is greeted and asked to place down five ships in rapid succession. After that, the
    * computer's 5 ships are placed on it's board randomly. Then it is time to Duel and the player is prompted
    * to attack one of the computer's ships.
    * */
    public void start() {
        setPlayerBoard(new char[7][7]);
        setCompBoard(new char[7][7]);
        makeBoard(getPlayerBoard());
        makeBoard(getCompBoard());
        System.out.println("");
        System.out.println("Hello and welcome to battleship");
        System.out.println("Please input your ship by entering the dimensions with a capital letter no greater than G," +
                "\nand then a number no greater than 7");
        System.out.println("You must place 5 ships.");

        //This loop allows the player to place 5 ships down on their board
        for (int i = 0; i < 5; i++) {
            placeHolderString = scnr.next();
            playerLetter = placeHolderString.charAt(0);
            playerNumber = placeHolderString.charAt(1);
            placeShip();
        }

        randomizeCompBoard();

        System.out.println("The computer has placed it's 5 ships in the waters.");
        System.out.println("Now it's time to d-d d-d d-d-d-d-Duel!.");
        System.out.println("Please type in your coordinates to blow up the computers ships.");
        placeHolderString = "";

    }

    //This method populates any board with "~" tiles
    public void makeBoard(char board[][]) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = '~';
            }
        }
    }

    //This method prints any board in the command line
    public void printBoard(char board[][]) {
        //This can print either the player or the computer's board.
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");

    }

    //This method places ships only on the player's board
    public void placeShip() {

        checkLetter(playerLetter);
        checkNumber(playerNumber);
        playerBoard[indexLetter][indexNumber] = '#';

        System.out.println("You've placed a ship at: " + playerLetter + " " + playerNumber);
        resetIndexes();


    }

    //This method is for the player to guess where the computer's ships are.
    public void findShip() {

        System.out.println("Please input where you think the enemy ship might be by entering the dimensions;\n" +
                "a capital letter no larger than G and a number no larger than 7");
        placeHolderString = scnr.next();
        playerLetter = placeHolderString.charAt(0);
        checkLetter(playerLetter);

        playerNumber = placeHolderString.charAt(1);
        checkNumber(playerNumber);

        //Checks to see if the player's attack hit the computer. If it does hit, then the player is told so
        /*and the number of computer ships decreases. If the hit does not register, then the player is told
        *they missed. If the number of computer ships reaches zero, then game over gets set to false which in turn will
        *end the game after being prompted to enter the debug code.
        */
        if (compBoard[indexLetter][indexNumber] == '#') {
            System.out.println("A computer ship has been attacked!");
            compBoard[indexLetter][indexNumber] = 'X';
            numCompShips--;
            if (numCompShips == 0) {
                gameOver = false;
            }
        } else if (compBoard[indexLetter][indexNumber] == '~') {
            System.out.println("Your attack missed!");
        }
        placeHolderString = "";
    }

    //This method sets the index letter and number to 0
    private void resetIndexes() {
        indexLetter = 0;
        indexNumber = 0;
    }

    //This method takes the player's coordinates and creates a numeric representation for the array
    private void checkLetter(char letter) {

        if (letter == 'A') {
            indexLetter = 0;
        } else if (letter == 'B') {
            indexLetter = 1;
        } else if (letter == 'C') {
            indexLetter = 2;
        } else if (letter == 'D') {
            indexLetter = 3;
        } else if (letter == 'E') {
            indexLetter = 4;
        } else if (letter == 'F') {
            indexLetter = 5;
        } else if (letter == 'G') {
            indexLetter = 6;
        } else {
            System.out.println("You did not input a proper letter, make sure that it is capital and no larger than G");
        }

    }

    //This method takes the player's coordinates and creates a numeric representation for the array
    private void checkNumber(char number) {

        if (number == '1') {
            indexNumber = 0;
        } else if (number == '2') {
            indexNumber = 1;
        } else if (number == '3') {
            indexNumber = 2;
        } else if (number == '4') {
            indexNumber = 3;
        } else if (number == '5') {
            indexNumber = 4;
        } else if (number == '6') {
            indexNumber = 5;
        } else if (number == '7') {
            indexNumber = 6;
        } else {
            System.out.println("You did not input a proper number, make sure that it is no larger than 8");
        }

    }

    //This method places 5 random ships into the computer's board.
    private void randomizeCompBoard() {

        //This loop places 5 ships down on the computer's board randomly
        for (int i = 0; i < 5; i++) {
            int randoLetter = new Random().nextInt(compBoard.length);
            int randoNumber = new Random().nextInt(compBoard.length);
            compBoard[randoLetter][randoNumber] = '#';
        }
    }

    //This method creates random indexes so that it can attack the one of the player's ships
    public void compAttack() {

        int randoLetter = new Random().nextInt(playerBoard.length);
        int randoNumber = new Random().nextInt(playerBoard.length);

        //Checks to see if the computer's attack hit the player's ship. If it does hit, then the player is told so
        /*and the number of player ships decreases. If the hit does not register, then the player is told
        *the computer missed. If the number of player ships reaches zero, then game over gets set to false which in turn will
        *end the game after being prompted to enter the debug code.
        */
        if (playerBoard[randoLetter][randoNumber] == '#') {
            System.out.println("A ship has been attacked!");
            playerBoard[randoLetter][randoLetter] = 'X';
            numPlayerShips--;
            if (numPlayerShips == 0) {
                gameOver = false;
            }
        } else if (playerBoard[randoLetter][randoNumber] == '~') {
            System.out.println("The computer's attack missed!");
        }

    }


    //Below are my encapsulated fields
    public int getNumPlayerShips() {
        return numPlayerShips;
    }

    public int getNumCompShips() {
        return numCompShips;
    }

    public static char[][] getPlayerBoard() {
        return playerBoard;
    }

    public static void setPlayerBoard(char[][] playerBoard) {
        Operations.playerBoard = playerBoard;
    }

    public static char[][] getCompBoard() {
        return compBoard;
    }

    public static void setCompBoard(char[][] compBoard) {
        Operations.compBoard = compBoard;
    }

    public boolean getGameOver() {
        return gameOver;
    }


}


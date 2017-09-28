import java.util.Scanner;

        /*
        *	Author:	Nyland Sidifall
		*	Date:	08/27/17
		*	Assignment:		Program	#1	- Battleship
		*	File:	main.operations.java
		*	Description:		This program will recreate a version of the game battleship in the command line.
		    This is the main method class.
		*/
public class Main {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);      //This is a scanner so that the user can input values to the computer
        Operations operations = new Operations();   //This creates a new operations object
        int debugInt = 0;                           //This is used to enter debug mode, do you know the answer? if not google it.

        operations.start();

        //This do while loop will keep allowing the player to attack and bbe attacked until gameOver in the opperations
        /*
        * class is set to false
        * */
        do {

            operations.findShip();
            operations.compAttack();

            System.out.println("Here is the current status of your board");
            operations.printBoard(operations.getPlayerBoard());

            System.out.println("Type in the answer to life, the universe and everything\n" +
                    "to enter debug mode so you can find the computer's ships.");
            debugInt = scnr.nextInt();

            if (debugInt == 42) {
                operations.printBoard(operations.getCompBoard());
            }

        } while (operations.getGameOver() == true);

        //This if will print either you win or you loose depending on either if the player's ships or computer's ships
        /*
        * reaches zero.
        * */
        if (operations.getNumPlayerShips() == 0) {
            System.out.println("You Lose!");
        } else if (operations.getNumCompShips() == 0) {
            System.out.println("You Win!");
        }


    }

}

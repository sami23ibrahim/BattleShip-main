//----------------------------------------------------
// Assignment (4)
// Written by: (1. Sami Ibrahim   ID: 40156134)(2. (Zhangbin Cai ID:40165425)

// For COMP 248 Section 2202 T – Fall 2020
//----------------------------------------------------
//-------------------------------------------------------------------------------------------

/*this program is a version of the game of Battleship against the
computer. this simplified version of the game will be played on a single 8 by 8 grid. Before the actual
game, each player secretly places 6 ships and 4 grenades on the grid. Ships and grenades are a single
position on the grid. The position of these ships and grenades are of course hidden from the opponent.
Once both player and computer have placed their ships and grenades, the actual game starts. Each player, in turn,
“shoots a rocket” on the grid (i.e. calls a position).
- If the rocket (the position called) falls on a position where there is nothing, then nothing happens,
and the other player can shoot his/her rocket.
- If the rocket falls on a coordinate where the opponent (or the player) has a grenade, then the player
loses a turn, and next time, the opponent will play twice in a row.
- If the rocket falls on a coordinate where the opponent (or the player...) has a ship, then that ship
sinks.
- If the rocket falls on a coordinate that has been called before, regardless of what was there before,
nothing happens. (So, for example, a grenade can only explode once).
The goal of the game is to sink all of your opponent’s ships before your opponent sinks yours.
For the sake of simplicity, ships and grenades cannot overlap. So, you cannot have
2 grenades on the same position, have 2 ships on the same position, or have a grenade on a ship. */


//-------------------------------------------------------------------------------------------
package assignment4;

import java.util.Scanner;

public class Grid {

	//declare instant varaibles (array)
	private char array[][] = new char[8][8];

	//Implement default constructor. declare "-" as the array default value
	public Grid() {

		populateGrid('-');

	}

	public Grid(char symbol) {

		populateGrid(symbol);

	}

//==============================================================================================
	//Implement default constructor. declare "-" as the array default value
	public void populateGrid(char symbol) {

		int i = 0, j = 0;

		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++)
				array[i][j] = symbol;
		}

	}

//================================================================================================
	//printing array method
	public void printArray() {

		int i = 0, j = 0;
		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {

				System.out.print(array[i][j] + " ");
			}
			System.out.println();

		}

	}

//============================================================================================
	//Implement place ships/grenades method
	public void playerPleaceVessle(char vessle, int numOfVessle) {

		Scanner keyboard = new Scanner(System.in);
		int playerRow = 0, num = 0;
		String playerColum, name = "--";
		String playerEnter;
		int i = 0;

		int count = 1;
		//use for loop to ask the user to place ships and grenades 
		for (i = 0; i < numOfVessle; i++) {
			if (vessle == 's')
				name = "ship";
			else
				name = "grenade";
			System.out.print("Enter the coordinates of your " + name + " #" + count + ":");//print the # of ships/grenades the use has entered
			playerEnter = keyboard.next();//read the user's input as a string as the format is a char with an integer such as "A1"

			playerColum = Character.toString(playerEnter.charAt(0));//extract the first char of the input string as the column index

			playerRow = Integer.parseInt(Character.toString(playerEnter.charAt(1)));//extract the second char of the input of the input string as the row index. transfer the string to an int
			//use while loop to make sure the user enter coordinates inside the grid (a-h, 1-8)
			while (playerColum.matches("a|b|c|d|e|f|g|h|A|B|C|D|E|F|G") == false || playerRow > 8 || playerRow < 1) {

				System.out.println("Sorry, coordinates outside the grid. Try again");

				playerEnter = keyboard.next();

				playerColum = Character.toString(playerEnter.charAt(0));

				playerRow = Integer.parseInt(Character.toString(playerEnter.charAt(1)));
			}
			//use switch statement to switch the column char index to integers
			switch (playerColum.toLowerCase()) {
			case "a":
				num = 1;
				break;
			case "b":
				num = 2;
				break;
			case "c":

				num = 3;
				break;
			case "d":
				num = 4;
				break;
			case "e":
				num = 5;
				break;
			case "f":
				num = 6;
				break;
			case "g":
				num = 7;
				break;
			case "h":
				num = 8;
				break;
			}
			//use if statement to prevent the user placing ships/grenades at the spot that has been taken
			if (array[playerRow - 1][num - 1] == 's' || array[playerRow - 1][num - 1] == 'g') {
				System.out.println("Sorry, Coordinates already used. Try again.");
				i--;
				//assign the value to he array if the spot is available
			} else {
				array[playerRow - 1][num - 1] = vessle;
				count++;
			}

		}

	}

//==========================================================================================================
	//method for the computer to randomly place ships and grenades
	public void ComputerplaceVessle(char vessle, int numOfVessle) {

		int i = 0;
		int colum;
		int row;
		for (i = 0; i < numOfVessle; i++) {

			colum = (int) (Math.random() * 8);

			row = (int) (Math.random() * 8);

			while (array[row][colum] == 'G' || array[row][colum] == 'S' || array[row][colum] == 's'
					|| array[row][colum] == 'g') {

				colum = (int) (Math.random() * 8);

				row = (int) (Math.random() * 8);

			}
			array[row][colum] = vessle;

		}
	}

//=================================================================================================================
	//method for shooting rockets
	public void players(Grid outputGrid) {

		char[][] outputArray = outputGrid.array;

		Scanner keyboard = new Scanner(System.in);
		int playerRow = 0, num = 0;
		int mydestroyed = 0;
		int compdestroyed = 0;
		String playerColum;
		boolean stop = true;
		boolean stop1 = true;
		int counter = 0;
		int counter1 = 0;
		boolean exist = true;
		boolean exist1 = true;
		String playerEnter;
//use while loop to keep playing. once the user/computer destroy opponent's 6 ships will end the loop as such the game
		while (mydestroyed < 6 && compdestroyed < 6) {
			while (stop == true && exist == true) {//implement booleans to check if the user shoot a location already has been called, and also to add one more turn if one of the players hit a grenade 
				
				System.out.print("potition of player rocket:");

				playerEnter = keyboard.next();//read the user's input coordinates

				playerColum = Character.toString(playerEnter.charAt(0));

				playerRow = Integer.parseInt(Character.toString(playerEnter.charAt(1)));
//use while loop to make sure the user input correct locations
				while (playerColum.matches("a|b|c|d|e|f|g|h|A|B|C|D|E|F|G") == false || playerRow > 8
						|| playerRow < 1) {

					System.out.println("Sorry, coordinates outside the grid. Try again");

					playerEnter = keyboard.next();

					playerColum = Character.toString(playerEnter.charAt(0));

					playerRow = Integer.parseInt(Character.toString(playerEnter.charAt(1)));

				}

				switch (playerColum.toLowerCase()) {
				case "a":
					num = 1;
					break;
				case "b":
					num = 2;
					break;
				case "c":

					num = 3;
					break;
				case "d":
					num = 4;
					break;
				case "e":
					num = 5;
					break;
				case "f":
					num = 6;
					break;
				case "g":
					num = 7;
					break;
				case "h":
					num = 8;
					break;
				}

				char target = outputArray[playerRow - 1][num - 1];
				if (target == '*' || target == 'g' || target == 'G' || target == 's' || target == 'S') {
					System.out.println("position already called. please enter again");
					exist = true;
					
				}//use if statement to prevent the user enter a location that has been called

				else {//the following statements are implemented to check which element the user has been shoot
					exist = false;

					if (array[playerRow - 1][num - 1] == '-') {

						outputArray[playerRow - 1][num - 1] = '*';
						System.out.println("nothing");
						
					}

					else if (array[playerRow - 1][num - 1] == 'S') {
						outputArray[playerRow - 1][num - 1] = 'S';
						System.out.println("ship hit.");
						mydestroyed++;
						

					}

					else if (array[playerRow - 1][num - 1] == 'G') {
						outputArray[playerRow - 1][num - 1] = 'G';
						System.out.println("Boom! Grenade");
						// exist=false;
						stop = false;
						counter--;
					} else if (array[playerRow - 1][num - 1] == 'g') {
						outputArray[playerRow - 1][num - 1] = 'g';
						System.out.println("Boom! my Grenade");
						
						stop = false;
						counter--;
					}

					else if (array[playerRow - 1][num - 1] == 's') {
						outputArray[playerRow - 1][num - 1] = 's';
						System.out.println("Sunk my ship.");
						
						compdestroyed++;

					}
					outputGrid.printArray();
					
					counter--;

				}
			}
			//check the end of the game; check if one of players destroyed 6 opponent's ships
			if (compdestroyed > 5) {
				System.out.println("After a difficult, but fair game..");
				System.out.println("COMPUTER WINS !");
				break;
			} else if (mydestroyed > 5) {
				System.out.println("After a difficult, but fair game..");
				System.out.println("YOU WIN !");
				break;
			}
		
			// ======================COMPUTER TURN============================//

			while (stop1 == true && exist1 == true) {

				int colum;
				int row;

				colum = (int) (Math.random() * 8);

				row = (int) (Math.random() * 8);

				char target1 = outputArray[row][colum];
				char target2 = array[row][colum];
//use while loop to prevent the computer shoot the location already been called
				if (target1 == '*' || target1 == 'g' || target1 == 'G' || target1 == 's' || target1 == 'S'
						|| target2 == 'G' || target2 == 'S') {
					
					exist1 = true;
				}

				else {
//switch the column indexes to letters so the user can read
					char c = 'p';

					switch (colum + 1) {
					case 1:
						c = 'A';
						break;
					case 2:
						c = 'B';
						break;
					case 3:

						c = 'C';
						break;
					case 4:
						c = 'D';
						break;
					case 5:
						c = 'E';
						break;
					case 6:
						c = 'F';
						break;
					case 7:
						c = 'G';
						break;
					case 8:
						c = 'H';
						break;
					}

					//output the location of computer shot 
					System.out.println("potition of computer's rocket:" + c + (row + 1));
					exist1 = false;
					//the following statements to check element that the computer shot
					if (array[row][colum] == '-') {

						outputArray[row][colum] = '*';
						System.out.println("nothing");
					}
					exist1 = false;
					if (array[row][colum] == 's') {
						outputArray[row][colum] = 's';
						System.out.println("ship hit.");
						compdestroyed++;
					}

					else if (array[row][colum] == 'g') {
						outputArray[row][colum] = 'g';
						System.out.println("Boom! Grenade");
						stop1 = false;
						counter1--;
					}

					outputGrid.printArray();
					counter1--;
				}

			}
			counter++;
			counter1++;
			
			if (counter1 == 0) {
				exist1 = true;
				stop1 = true;
				
			}
			if (counter == 0) {
				exist = true;
				stop = true;
			}

			if (compdestroyed > 5) {
				System.out.println("After a difficult, but fair game..");
				System.out.println("COMPUTER WINS !");
				break;
			} else if (mydestroyed > 5) {
				System.out.println("After a difficult, but fair game..");
				System.out.println("YOU WIN !");
				break;
			}

		}
	}
}
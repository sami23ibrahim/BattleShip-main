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
//*READ ME***: please enter the coordinates by a letter (A/a-H/h) as column and an integer (1-8) as row, with no space in between e.x. A2, h5, D4..etc.
package assignment4;

import java.util.Scanner;

public class GameDriver {//declare the driver class as GameDriver

	public static void main(String[] args) {

		//two array objects. playGrid allows the user/computer to placed ships/grenades; the outputGrid will be used to play rocket attack without showing the locations of ships and grenades
		Grid playGrid = new Grid();
		Grid outputGrid = new Grid();
		
	
		System.out.println("Hi, Let's play BattleShip");//prompt the user beginning of the game
		
		playGrid.playerPleaceVessle('s',6); //call playerPleaceVessle method. so the user starts placing 6 ships. "s" represents user's ships
		System.out.println("============");
		
		playGrid.playerPleaceVessle('g',4);//call playerPleaceVessle method. so the user starts placing 4 grenades. "g" represents user's grenades
		System.out.println("============");

		//call the ComputerplaceVessle method. the computer will randomly placed 6 ships "S" and 4 grenades "G"
		playGrid.ComputerplaceVessle('S', 6); 
	
		playGrid.ComputerplaceVessle('G', 4);
	
		System.out.println("Ok, the computer placed its ships and grenades at random. Let's play.");
	
	
		playGrid.players(outputGrid);//call the players method to start shooting rockets 
		
		System.out.println("==============");
		playGrid.printArray();//print the array after the game

	}

}

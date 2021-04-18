package battleBoats;
import java.util.ArrayList;
import java.util.Scanner;

public class BattleMain {

	//I'm sorry this code is such a mess.
	public static void main(String[] args) {
		
		//creating an ArrayList to hold 25 objects; the tiles used as the game board
		ArrayList<Construct> tile = new ArrayList<Construct>();
		
		//populating arraylist with objects made with in a loop
		for(int i = 0; i < 25; i++) {
			tile.add(new Construct(0, false, "   |"));
		}
	
		//creating variables to store the boat positions for later
		int shipX1 = 0;											
		int shipX2 = 0;											
		int shipY1 = 0;
		int shipY2 = 0;
		int shipZ1 = 0;
		int shipZ2 = 0;
				
		//setting the tile positions for 3 ships. I know it's ugly, but I don't know how to do this any other way right now
		boolean loopEnd = false;
		
		while(loopEnd == false) {
			int a = ShipYard.first();
			int b = ShipYard.second();
			if (tile.get(a).status == 1 || tile.get(b).status == 1) {		//logic to prevent overlapping boats
				loopEnd = false;
			}
			else {															//if no overlapping ships:
				shipX1 = a;													//stores the 2 tiles' positions for later use
				shipX2 = b;
				tile.get(a).status = 1;										//sets the 2 tiles to having ships on them
				tile.get(b).status = 1;
				loopEnd = true;
			}
		}
		
		loopEnd = false;
		while(loopEnd == false) {
			int a = ShipYard.first();
			int b = ShipYard.second();
			if (tile.get(a).status == 1 || tile.get(b).status == 1) {		
				loopEnd = false;
			}
			else {															
				shipY1 = a;													
				shipY2 = b;
				tile.get(a).status = 1;										
				tile.get(b).status = 1;
				loopEnd = true;
			}
		}
		
		loopEnd = false;
		while(loopEnd == false) {
			int a = ShipYard.first();
			int b = ShipYard.second();
			if (tile.get(a).status == 1 || tile.get(b).status == 1) {		
				loopEnd = false;
			}
			else {															
				shipZ1 = a;													
				shipZ2 = b;
				tile.get(a).status = 1;										
				tile.get(b).status = 1;
				loopEnd = true;
			}
		}
		String message = " ";																//stores a message to be displayed on each turn.
		boolean gameEnd = false;
		int turnCounter = 0;
		int difficulty = 0;
		//set difficulty with scanner/switch method
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Welcome to my game!");
		System.out.println("Choose Difficulty: Easy, Medium, or Hard");						//easy = 20 shots, medium = 16, hard = 12
		difficulty = Switchers.difficultySwitch(keyboard.nextLine().toLowerCase());
		
		//main game loop. I don't like how many loops/if-else statements I'm nesting inside eachother here.
		while(gameEnd == false) {
			for (int i = 0; i < 30; ++i) System.out.println("\n");							//bootleg console clear
			boolean gameWin = false;
			//loop always starts with checking if the game is over.
			if(tile.get(shipX1).status == 2 && tile.get(shipX2).status == 2 				//setting the win condition (all boats sunk).
					&& tile.get(shipY1).status == 2 && tile.get(shipY2).status == 2
					&& tile.get(shipZ1).status == 2 && tile.get(shipZ2).status == 2) { 
				gameWin = true;															
				keyboard.close();
			}
			
			if(gameWin == true) {
				System.out.println("Congratulations, you sunk all enemy ships!");
				System.out.println("Job well done!");
				gameEnd = true;
				keyboard.close();
				
			}
			else if(turnCounter > difficulty) {												//lose condition (running out of ammo).
				System.out.println("Sorry, you ran out of ammo!");
				System.out.println("Better luck next time!");
				gameEnd = true;
				keyboard.close();
			}
			else {																			//running the game.
				//printing ships
				String xStatus = " ";
				String yStatus = " ";
				String zStatus = " ";
				if(tile.get(shipX1).status == 2 && tile.get(shipX2).status == 2) {
					xStatus = "You sank the ship! Wonderful Job!";
				}
				else if(tile.get(shipX1).status == 2 || tile.get(shipX2).status == 2) {
					xStatus = "The ship is damaged!";
				}
				System.out.println("Ship 1: |" + tile.get(shipX1).ascii + tile.get(shipX2).ascii + " " + xStatus);
				
				
				if(tile.get(shipY1).status == 2 && tile.get(shipY2).status == 2) {
					yStatus = "You sank the ship! Wonderful Job!";
				}
				else if(tile.get(shipY1).status == 2 || tile.get(shipY2).status == 2) {
					yStatus = "The ship is damaged!";
				}
				System.out.println("Ship 2: |" + tile.get(shipY1).ascii + tile.get(shipY2).ascii + " " + yStatus);
				
				
				if(tile.get(shipZ1).status == 2 && tile.get(shipZ2).status == 2) {
					zStatus = "You sank the ship! Wonderful Job!";
				}
				else if(tile.get(shipZ1).status == 2 || tile.get(shipZ2).status == 2) {
					zStatus = "The ship is damaged!";
				}
				System.out.println("Ship 3: |" + tile.get(shipZ1).ascii + tile.get(shipZ2).ascii + " " + zStatus);
				
				
				//printing ascii board.
				System.out.println("  | 1 | 2 | 3 | 4 | 5 |");
				System.out.println("A |" + tile.get(0).ascii + tile.get(1).ascii + tile.get(2).ascii + tile.get(3).ascii + tile.get(4).ascii); 
				System.out.println("B |" + tile.get(5).ascii + tile.get(6).ascii + tile.get(7).ascii + tile.get(8).ascii + tile.get(9).ascii);
				System.out.println("C |" + tile.get(10).ascii + tile.get(11).ascii + tile.get(12).ascii + tile.get(13).ascii + tile.get(14).ascii);
				System.out.println("D |" + tile.get(15).ascii + tile.get(16).ascii + tile.get(17).ascii + tile.get(18).ascii + tile.get(19).ascii);
				System.out.println("E |" + tile.get(20).ascii + tile.get(21).ascii + tile.get(22).ascii + tile.get(23).ascii + tile.get(24).ascii); 
				
				//user choosing where to fire loop.
				boolean turnEnd = false;
				while(turnEnd == false) {
					String tileName;
					System.out.println(message);
					System.out.println("Choose where to fire!");
					tileName = keyboard.nextLine().toLowerCase();
					switch(tileName) {
					case "a1":
						if(tile.get(0).firedAt == false && tile.get(0).status == 0) {			//if selected tile hasn't been fired at, and has no ship.
							tile.get(0).firedAt = true;
							tile.get(0).ascii = " O |";											//O indicates a miss
							turnEnd = true;														//ends the turn.
							message = "You Missed!";
							turnCounter++;														//if successful firing, turnCounter increases.
							break;
						}
						else if(tile.get(0).firedAt == false && tile.get(0).status == 1) {		//if selected tile hasn't been fired at, and has a ship.
							tile.get(0).firedAt = true;
							tile.get(0).status = 2;												//if ship has been hit, status changes to indicate
							tile.get(0).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;														
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"a2":
						if(tile.get(1).firedAt == false && tile.get(1).status == 0) {			
							tile.get(1).firedAt = true;
							tile.get(1).ascii = " O |";											
							turnEnd = true;
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(1).firedAt == false && tile.get(1).status == 1) {		
							tile.get(1).firedAt = true;
							tile.get(1).status = 2;
							tile.get(1).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"a3":
						if(tile.get(2).firedAt == false && tile.get(2).status == 0) {			
							tile.get(2).firedAt = true;
							tile.get(2).ascii = " O |";											
							turnEnd = true;	
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(2).firedAt == false && tile.get(2).status == 1) {		
							tile.get(2).firedAt = true;
							tile.get(2).status = 2;
							tile.get(2).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"a4":
						if(tile.get(3).firedAt == false && tile.get(3).status == 0) {			
							tile.get(3).firedAt = true;
							tile.get(3).ascii = " O |";											
							turnEnd = true;	
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(3).firedAt == false && tile.get(3).status == 1) {		
							tile.get(3).firedAt = true;
							tile.get(3).status = 2;
							tile.get(3).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"a5":
						if(tile.get(4).firedAt == false && tile.get(4).status == 0) {			
							tile.get(4).firedAt = true;
							tile.get(4).ascii = " O |";											
							turnEnd = true;			
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(4).firedAt == false && tile.get(4).status == 1) {		
							tile.get(4).firedAt = true;
							tile.get(4).status = 2;
							tile.get(4).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"b1":
						if(tile.get(5).firedAt == false && tile.get(5).status == 0) {			
							tile.get(5).firedAt = true;
							tile.get(5).ascii = " O |";											
							turnEnd = true;				
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(5).firedAt == false && tile.get(5).status == 1) {		
							tile.get(5).firedAt = true;
							tile.get(5).status = 2;
							tile.get(5).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"b2":
						if(tile.get(6).firedAt == false && tile.get(6).status == 0) {			
							tile.get(6).firedAt = true;
							tile.get(6).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(6).firedAt == false && tile.get(6).status == 1) {		
							tile.get(6).firedAt = true;
							tile.get(6).status = 2;
							tile.get(6).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"b3":
						if(tile.get(7).firedAt == false && tile.get(7).status == 0) {			
							tile.get(7).firedAt = true;
							tile.get(7).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(7).firedAt == false && tile.get(7).status == 1) {		
							tile.get(7).firedAt = true;
							tile.get(7).status = 2;
							tile.get(7).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"b4":
						if(tile.get(8).firedAt == false && tile.get(8).status == 0) {			
							tile.get(8).firedAt = true;
							tile.get(8).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(8).firedAt == false && tile.get(8).status == 1) {		
							tile.get(8).firedAt = true;
							tile.get(8).status = 2;
							tile.get(8).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"b5":
						if(tile.get(9).firedAt == false && tile.get(9).status == 0) {			
							tile.get(9).firedAt = true;
							tile.get(9).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(9).firedAt == false && tile.get(9).status == 1) {		
							tile.get(9).firedAt = true;
							tile.get(9).status = 2;
							tile.get(9).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"c1":
						if(tile.get(10).firedAt == false && tile.get(10).status == 0) {			
							tile.get(10).firedAt = true;
							tile.get(10).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(10).firedAt == false && tile.get(10).status == 1) {		
							tile.get(10).firedAt = true;
							tile.get(10).status = 2;
							tile.get(10).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"c2":
						if(tile.get(11).firedAt == false && tile.get(11).status == 0) {			
							tile.get(11).firedAt = true;
							tile.get(11).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(11).firedAt == false && tile.get(11).status == 1) {		
							tile.get(11).firedAt = true;
							tile.get(11).status = 2;
							tile.get(11).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"c3":
						if(tile.get(12).firedAt == false && tile.get(12).status == 0) {			
							tile.get(12).firedAt = true;
							tile.get(12).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(12).firedAt == false && tile.get(12).status == 1) {		
							tile.get(12).firedAt = true;
							tile.get(12).status = 2;
							tile.get(12).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"c4":
						if(tile.get(13).firedAt == false && tile.get(13).status == 0) {			
							tile.get(13).firedAt = true;
							tile.get(13).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(13).firedAt == false && tile.get(13).status == 1) {		
							tile.get(13).firedAt = true;
							tile.get(13).status = 2;
							tile.get(13).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"c5":
						if(tile.get(14).firedAt == false && tile.get(14).status == 0) {			
							tile.get(14).firedAt = true;
							tile.get(14).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(14).firedAt == false && tile.get(14).status == 1) {		
							tile.get(14).firedAt = true;
							tile.get(14).status = 2;
							tile.get(14).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"d1":
						if(tile.get(15).firedAt == false && tile.get(15).status == 0) {			
							tile.get(15).firedAt = true;
							tile.get(15).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(15).firedAt == false && tile.get(15).status == 1) {		
							tile.get(15).firedAt = true;
							tile.get(15).status = 2;
							tile.get(15).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"d2":
						if(tile.get(16).firedAt == false && tile.get(16).status == 0) {			
							tile.get(16).firedAt = true;
							tile.get(16).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(16).firedAt == false && tile.get(16).status == 1) {		
							tile.get(16).firedAt = true;
							tile.get(16).status = 2;
							tile.get(16).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"d3":
						if(tile.get(17).firedAt == false && tile.get(17).status == 0) {			
							tile.get(17).firedAt = true;
							tile.get(17).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(17).firedAt == false && tile.get(17).status == 1) {		
							tile.get(17).firedAt = true;
							tile.get(17).status = 2;
							tile.get(17).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"d4":
						if(tile.get(18).firedAt == false && tile.get(18).status == 0) {			
							tile.get(18).firedAt = true;
							tile.get(18).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(18).firedAt == false && tile.get(18).status == 1) {		
							tile.get(18).firedAt = true;
							tile.get(18).status = 2;
							tile.get(18).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"d5":
						if(tile.get(19).firedAt == false && tile.get(19).status == 0) {			
							tile.get(19).firedAt = true;
							tile.get(19).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(19).firedAt == false && tile.get(19).status == 1) {		
							tile.get(19).firedAt = true;
							tile.get(19).status = 2;
							tile.get(19).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"e1":
						if(tile.get(20).firedAt == false && tile.get(20).status == 0) {			
							tile.get(20).firedAt = true;
							tile.get(20).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(20).firedAt == false && tile.get(20).status == 1) {		
							tile.get(20).firedAt = true;
							tile.get(20).status = 2;
							tile.get(20).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"e2":
						if(tile.get(21).firedAt == false && tile.get(21).status == 0) {			
							tile.get(21).firedAt = true;
							tile.get(21).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(21).firedAt == false && tile.get(21).status == 1) {		
							tile.get(21).firedAt = true;
							tile.get(21).status = 2;
							tile.get(21).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"e3":
						if(tile.get(22).firedAt == false && tile.get(22).status == 0) {			
							tile.get(22).firedAt = true;
							tile.get(22).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(22).firedAt == false && tile.get(22).status == 1) {		
							tile.get(22).firedAt = true;
							tile.get(22).status = 2;
							tile.get(22).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"e4":
						if(tile.get(23).firedAt == false && tile.get(23).status == 0) {			
							tile.get(23).firedAt = true;
							tile.get(23).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(23).firedAt == false && tile.get(23).status == 1) {		
							tile.get(23).firedAt = true;
							tile.get(23).status = 2;
							tile.get(23).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					case"e5":
						if(tile.get(24).firedAt == false && tile.get(24).status == 0) {			
							tile.get(24).firedAt = true;
							tile.get(24).ascii = " O |";											
							turnEnd = true;					
							message = "You Missed!";
							turnCounter++;
							break;
						}
						else if(tile.get(24).firedAt == false && tile.get(24).status == 1) {		
							tile.get(24).firedAt = true;
							tile.get(24).status = 2;
							tile.get(24).ascii = " X |";
							turnEnd = true;
							message = "That's a hit!";
							turnCounter++;
							break;
						}
						else {
							message = "You cannot fire at the same spot twice!";
							break;
						}
					default:
						message = "That's not a valid place to fire!";
						break;
					}//there has to be an alternative for a switch this long. or maybe my issue is the commands for each switch statement.
				}
			}
		}
	}

}

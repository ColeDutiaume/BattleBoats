package battleBoats;

public class Switchers {
	
	//takes the first random number from ShipYard and randomly chooses where to place the next boat in a logical position.
	public static int indexSwitch(int index2) {
		int switcher = (int) (Math.random() * 4);
		switch (switcher){
		case 0:
			index2 = index2+1;
			break;
		case 1:
			index2 = index2-1;
			break;
		case 2:
			index2 = index2+5;
			break;
		case 3:
			index2 = index2-5;
			break;
		default:
			System.out.println("This really shouldn't ever happen.");
			break;
		}
		return index2;
	}
	
	//setting difficulty value. invalid inputs will default to easy mode.
	public static int difficultySwitch(String select) {
		int rtrn = 0;
		switch(select) {
		case "easy":
			rtrn = 20;
			break;
		case"medium":
			rtrn = 16;
			break;
		case"hard":
			rtrn = 12;
			break;
		default:
			rtrn = 20;
			break;
		}
		return rtrn;
	}
	
}

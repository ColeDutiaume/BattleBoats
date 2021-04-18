package battleBoats;

public class ShipYard {
	
	//int to store the "first" random output to use as base for "second" method
	static int index;
	
	//places first boat value
	public static int first() {
		index = (int) (Math.random()*25);
		return index;
	}
	
	///second boat value
	public static int second() {
		boolean loopEnd = false;
		int index2 = 0;												
		while(loopEnd == false) {
			int transfer = (Switchers.indexSwitch(index));
			if(transfer > 24 || transfer < 0) {																			//logic check 1: makes sure boat stays on grid
				loopEnd = false;
			}
			else if (transfer*index == 20 || transfer*index == 90 || transfer*index == 210 || transfer*index == 380) {	//logic check 2: prevents illogical ship placement
				loopEnd = false;
			}
			else {
				loopEnd = true;
				index2 = transfer;																						//set return value of checks are passed
			}
		}
		return index2;
	}

}

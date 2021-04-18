package battleBoats;

public class Construct {
	
	int status;
	boolean firedAt;
	String ascii;
	//int status = 0||1||2. 0 = no ship, 1 = ship, 2 = damaged ship.
	
	public Construct(int status, boolean firedAt, String ascii) {
		this.status = status;
		this.firedAt = firedAt;
		this.ascii = ascii;
	}
}

package cell;

public class Cell {
	private int situation = 0;
	
	public void defult() { situation = 0; }
	public void usr() { situation = 1; }
	public void computer() { situation = 2; }
	public int status() { return situation; }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

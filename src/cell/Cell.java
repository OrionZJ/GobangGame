package cell;

public class Cell {
	private int situation = 0;
	
	public void defult() { situation = 0; }    //格子没棋
	public void usr() { situation = 1; }       //落了用户的棋
	public void computer() { situation = 2; }  //落了电脑的棋
	public int status() { return situation; }  //返回格子落棋装填
	
// 	public static void main(String[] args) {
// 		// TODO Auto-generated method stub

// 	}

}

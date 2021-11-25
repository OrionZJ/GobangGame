package cell;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	private int situation = 0;
	
	public void defult() { situation = 0; }    //格子没棋
	public void usr() { situation = 1; }       //落了用户的棋
	public void computer() { situation = 2; }  //落了电脑的棋
	
	public int status() { return situation; }  //返回格子落棋装填

	//画出自己这个小方格
	public void draw(Graphics g, int x, int y, int size) {    //x、y是矩形左上角的位置
		if ( status() == 0 ) {
			g.setColor(new Color(224,224,224));
			g.fillRect(x, y, size, size);
		}
		if ( status() == 1 ) {
			g.setColor(new Color(63,81,181));
			g.fillRect(x, y, size, size);
		}
		if ( status() == 2 ) {
			g.setColor(new Color(244,67,54));
			g.fillRect(x, y, size, size);
		}
		g.setColor(new Color(0,0,0));
		g.drawRect(x, y, size, size);
	}

}

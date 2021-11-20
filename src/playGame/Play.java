package playGame;

import java.util.Scanner;
import java.util.Random;

import cell.Cell;
import field.Field;
// import field.View;

public class Play {
	//判断落棋位置是否合理（越界）
	public static boolean ifReasonable(int limit, int input) {
		if (input >= 0 && input < limit) return true;
		else return false;
	}
	//判断落棋位置是否冲突（事先有棋）
	public static boolean ifConflict(int row, int col, Field field) {
		if (field.get(row, col).status() == 0) return false;
		else return true;
	}
	//打印格子
	public static void printCell(Cell cell) {
		if (cell.status() == 0) System.out.print('.');
		if (cell.status() == 1) System.out.print('X');
		if (cell.status() == 2) System.out.print('O');
	}
	//打印棋盘
	public static void printTable(Field field) {
		for ( int i=0; i<field.getHeight(); i++ ) {
			for ( int j=0; j<field.getWidth(); j++ ) {
				printCell(field.get(i, j));
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int width = 15;
		int height = 15;
		
		Scanner in = new Scanner(System.in);
		Field table = new Field(width,height);
		Random random = new Random();
		//初始化用户的棋子
		Cell usrCell = new Cell();
		usrCell.usr();
		//初始化电脑的棋子
		Cell comCell = new Cell();
		comCell.computer();

		while (true) {
			boolean reasonable = false;
			boolean conflict = false;
			boolean ifWin = false;
			int col = 0;
			int row = 0;
			//输入下棋位置
			do {
				while (!reasonable) {    //若用户输入数据越界，则重复要求用户输入数据
					System.out.print("请输入下子的坐标：[row col]");
					row = in.nextInt() - 1;
					if(!(reasonable = ifReasonable(height,row))) 
						System.out.println("Overflow!");
					if (reasonable) {
						col = in.nextInt() - 1;
						if(!(reasonable = ifReasonable(width, col)))
							System.out.println("Overflow!");
					}
				}
				conflict = ifConflict(row, col, table);
				reasonable = false;    //重置
			} while (conflict);
			table.place(row, col, usrCell);
			printTable(table);
			//判断输赢
			if (ifWin = table.ifWin(row, col)) {
				System.out.println("You win!");
				break;
			}
			//机器随机下棋
			do {    
				//均匀分布int值介于0（含）和 bound（不包括），参数bound 是上限。
				col = random.nextInt(table.getWidth());  
				row = random.nextInt(table.getHeight());
				conflict = ifConflict(row, col, table);
			} while (conflict);
			table.place(row, col, comCell);
			System.out.println("电脑下子：["+row+' '+col+"]");
			printTable(table);
			//判断输赢
			if (ifWin = table.ifWin(row, col)) {
				System.out.println("You lose.");
				break;
			}
		}
		in.close();
	}

}

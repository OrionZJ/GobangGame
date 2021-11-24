package playGame;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Random;

import cell.Cell;
import field.Field;
import field.View;

public class GUI {
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
		boolean ifWin = false;
		
		Scanner in = new Scanner(System.in);
		Field table = new Field(width,height);
		Random random = new Random();
		//初始化用户的棋子
		Cell usrCell = new Cell();
		usrCell.usr();
		//初始化电脑的棋子
		Cell comCell = new Cell();
		comCell.computer();

        View view = new View(table);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Cells");
		frame.add(view);
        frame.addMouseListener(new MouseListener() {   //为窗口添加鼠标事件监听器
			@Override
			public void mousePressed(MouseEvent e) {
			  // TODO Auto-generated method stub
			  if(e.getButton()==MouseEvent.BUTTON1){    // 判断获取的按钮是否为鼠标的左击     
				view.mouseX = (int) (e.getX() - 7) / View.GRID_SIZE ;    //不知道最左坐标为什么是7
				view.mouseY = (int) (e.getY() - 30) / View.GRID_SIZE ;    ////不知道最上坐标为什么是30
				// view.mouseX = (int) e.getX();
				// view.mouseY = (int) e.getY();
				System.out.println("You clicked: x=" + view.mouseX + "   y=" + view.mouseY);		
			   }
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			  // TODO Auto-generated method stub
	 
			}
	 
			@Override
			public void mouseReleased(MouseEvent e) {
			  // TODO Auto-generated method stub
	 
			}
	 
			@Override
			public void mouseEntered(MouseEvent e) {
			  // TODO Auto-generated method stub
	 
			}
	 
			@Override
			public void mouseExited(MouseEvent e) {
			  // TODO Auto-generated method stub
	 
			}	 
			
		  });
          
		frame.pack();
		frame.setVisible(true);

		while (!ifWin) {
			boolean reasonable = false;
			boolean conflict = false;
			
			int col = 0; int row = 0;
			//输入下棋位置
			do {
                view.mouseX = -1; view.mouseY = -1;
				while (!reasonable) {    //若用户输入数据越界，则重复要求用户输入数据
					// System.out.print("请输入下子的坐标：[row col]");
					// row = in.nextInt() - 1;
                    row = view.mouseY;
					if(!(reasonable = ifReasonable(height,row))) 
						System.out.print("就绪\r");
					if (reasonable) {
						// col = in.nextInt() - 1;
                        col = view.mouseX;
						if(!(reasonable = ifReasonable(width, col)))
							System.out.print("就绪\r");
					}
				}
				conflict = ifConflict(row, col, table);
				reasonable = false;    //重置
			} while (conflict);
			table.place(row, col, usrCell);
			printTable(table);
            frame.repaint();
			//判断输赢
			if (ifWin = table.ifWin(row, col)) {
				System.out.println("You win!");
                JOptionPane.showMessageDialog(frame, "你赢了！");
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
            frame.repaint();
			//判断输赢
			if (ifWin = table.ifWin(row, col)) {
				System.out.println("You lose.");
                JOptionPane.showMessageDialog(null, "你输了。");
				break;
			}
		}
		in.close();
	}

}


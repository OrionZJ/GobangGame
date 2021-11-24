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
	//�ж�����λ���Ƿ������Խ�磩
	public static boolean ifReasonable(int limit, int input) {
		if (input >= 0 && input < limit) return true;
		else return false;
	}
	//�ж�����λ���Ƿ��ͻ���������壩
	public static boolean ifConflict(int row, int col, Field field) {
		if (field.get(row, col).status() == 0) return false;
		else return true;
	}
	//��ӡ����
	public static void printCell(Cell cell) {
		if (cell.status() == 0) System.out.print('.');
		if (cell.status() == 1) System.out.print('X');
		if (cell.status() == 2) System.out.print('O');
	}
	//��ӡ����
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
		//��ʼ���û�������
		Cell usrCell = new Cell();
		usrCell.usr();
		//��ʼ�����Ե�����
		Cell comCell = new Cell();
		comCell.computer();

        View view = new View(table);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Cells");
		frame.add(view);
        frame.addMouseListener(new MouseListener() {   //Ϊ������������¼�������
			@Override
			public void mousePressed(MouseEvent e) {
			  // TODO Auto-generated method stub
			  if(e.getButton()==MouseEvent.BUTTON1){    // �жϻ�ȡ�İ�ť�Ƿ�Ϊ�������     
				view.mouseX = (int) (e.getX() - 7) / View.GRID_SIZE ;    //��֪����������Ϊʲô��7
				view.mouseY = (int) (e.getY() - 30) / View.GRID_SIZE ;    ////��֪����������Ϊʲô��30
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
			//��������λ��
			do {
                view.mouseX = -1; view.mouseY = -1;
				while (!reasonable) {    //���û���������Խ�磬���ظ�Ҫ���û���������
					// System.out.print("���������ӵ����꣺[row col]");
					// row = in.nextInt() - 1;
                    row = view.mouseY;
					if(!(reasonable = ifReasonable(height,row))) 
						System.out.print("����\r");
					if (reasonable) {
						// col = in.nextInt() - 1;
                        col = view.mouseX;
						if(!(reasonable = ifReasonable(width, col)))
							System.out.print("����\r");
					}
				}
				conflict = ifConflict(row, col, table);
				reasonable = false;    //����
			} while (conflict);
			table.place(row, col, usrCell);
			printTable(table);
            frame.repaint();
			//�ж���Ӯ
			if (ifWin = table.ifWin(row, col)) {
				System.out.println("You win!");
                JOptionPane.showMessageDialog(frame, "��Ӯ�ˣ�");
				break;
			}
			//�����������
			do {    
				//���ȷֲ�intֵ����0�������� bound����������������bound �����ޡ�
				col = random.nextInt(table.getWidth());  
				row = random.nextInt(table.getHeight());
				conflict = ifConflict(row, col, table);
			} while (conflict);
			table.place(row, col, comCell);
			System.out.println("�������ӣ�["+row+' '+col+"]");
			printTable(table);
            frame.repaint();
			//�ж���Ӯ
			if (ifWin = table.ifWin(row, col)) {
				System.out.println("You lose.");
                JOptionPane.showMessageDialog(null, "�����ˡ�");
				break;
			}
		}
		in.close();
	}

}

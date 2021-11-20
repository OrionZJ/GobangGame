package playGame;

import java.util.Scanner;
import java.util.Random;

import cell.Cell;
import field.Field;
// import field.View;

public class Play {
	//�ж�����λ���Ƿ����Խ�磩
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
		
		Scanner in = new Scanner(System.in);
		Field table = new Field(width,height);
		Random random = new Random();
		//��ʼ���û�������
		Cell usrCell = new Cell();
		usrCell.usr();
		//��ʼ�����Ե�����
		Cell comCell = new Cell();
		comCell.computer();

		while (true) {
			boolean reasonable = false;
			boolean conflict = false;
			boolean ifWin = false;
			int col = 0;
			int row = 0;
			//��������λ��
			do {
				while (!reasonable) {    //���û���������Խ�磬���ظ�Ҫ���û���������
					System.out.print("���������ӵ����꣺[row col]");
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
				reasonable = false;    //����
			} while (conflict);
			table.place(row, col, usrCell);
			printTable(table);
			//�ж���Ӯ
			if (ifWin = table.ifWin(row, col)) {
				System.out.println("You win!");
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
			//�ж���Ӯ
			if (ifWin = table.ifWin(row, col)) {
				System.out.println("You lose.");
				break;
			}
		}
		in.close();
	}

}

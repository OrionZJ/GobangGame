package field;

import java.util.ArrayList;

import cell.Cell;

public class Field {
	private int width;
	private int height;
	
	public Field(int width, int height) {
		this.width = width;
		this.height = height;
		field = new Cell[height][width];
		//棋盘置空
		for ( int i=0; i<height; i++ ) {
			for ( int j=0; j<width; j++ ) {
				Cell cell = new Cell();
				cell.defult();
				field[i][j] = cell;
			}
		}
	}

	private Cell[][] field;
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public Cell place(int row, int col, Cell s) {
		Cell lay = field[row][col];
		field[row][col] = s;
		return lay;
	}
	
	public Cell get(int row, int col) {
		return field[row][col];
	}
	
	public boolean ifWin(int row, int col) {
		//TODO
		
		
		
		
		
		
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

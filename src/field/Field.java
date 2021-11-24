package field;

import cell.Cell;

public class Field {
	private int width;
	private int height;
	//初始化棋盘
	public Field(int width, int height) {
		this.width = width;
		this.height = height;
		field = new Cell[height][width];
		clean(width, height);    //棋盘置空
	}
	//二位数组名称为field，内部元素为Cell类型
	private Cell[][] field;
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	//放置棋子
	public Cell place(int row, int col, Cell s) {
		Cell lay = field[row][col];
		field[row][col] = s;
		return lay;    //返回下子前的格子属性
	}
	//返回方格状态
	public Cell get(int row, int col) {
		return field[row][col];
	}
	//清空棋盘
	public boolean clean(int width, int height) {
		for ( int i = 0; i  <height; i++ ) {
			for ( int j = 0; j < width; j++ ) {
				Cell cell = new Cell();
				cell.defult();
				field[i][j] = cell;
			}
		}
		return true;
	}
	//根据落子周围判断输赢
	public boolean ifWin(int row, int col) {
		int a = row;
		int b = col;
		int northNum = 0; int southNum = 0;
		int westNum = 0; int eastNum = 0;
		int southWestNum = 0; int southEastNum = 0;
		int northWestNum = 0; int northEastNum = 0;
		int ox = 1; int xo = 1; int xx =1; int x_x =1;    //横、竖、主对角线、副对角线
		//向上数数
		a = row - 1; b = col;
		while (a >= 0 && field[a][b].status() == field[row][col].status()) {
			northNum++;
			a--;
		}
		//向下数数
		a = row + 1; b = col;
		while (a < height && field[a][b].status() == field[row][col].status()) {
			southNum++;
			a++;
		}
		//向左数数
		a = row ; b = col - 1;
		while (b >=0 && field[a][b].status() == field[row][col].status()) {
			westNum++;
			b--;
		}
		//向右数数
		a = row ; b = col + 1;
		while (b < width && field[a][b].status() == field[row][col].status()) {
			eastNum++;
			b++;
		}
		//左上
		a = row - 1; b = col - 1;
		while (a >= 0 && b >= 0 && field[a][b].status() == field[row][col].status()) {
			southWestNum++;
			a--; b--;
		}
		//左下
		a = row + 1; b = col - 1;
		while (a < height && b >= 0 && field[a][b].status() == field[row][col].status()) {
			northWestNum++;
			a++; b--;
		}
		//右上
		a = row - 1; b = col + 1;
		while (a >=0 && b < width && field[a][b].status() == field[row][col].status()) {
			southEastNum++;
			a--; b++;
		}
		//右下
		a = row + 1; b = col + 1;
		while (a < height && b < width && field[a][b].status() == field[row][col].status()) {
			northEastNum++;
			a++; b++;
		}
		ox += eastNum + westNum;
		xo += southNum + northNum; 
		xx += northEastNum + southWestNum;
		x_x += northWestNum + southEastNum;
		if (ox >= 5 || xo >= 5 || xx >= 5 || x_x >=5) return true;		
		else return false;
	}
	
	// public static void main(String[] args) {
	// 	// TODO Auto-generated method stub

	// }

}

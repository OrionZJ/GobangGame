package field;

import java.util.HashMap;

import cell.Cell;

public class Robot {

	private int height = 15;
	private int width = 15;

	private Cell[][] Chess;
	private int[][] chess =  new int[height][width];;//棋盘上棋子数据 
	private int[][] chessValue = new int[height][width];//保存棋盘上的权值
	private int maxi, maxj;

	//存放权值的数组
	HashMap<String, Integer> hm = new HashMap<String, Integer>();

	public Robot(Cell[][] chess, int height, int width) {
		// TODO Auto-generated constructor stub
		this.Chess = chess;
		this.height = height;
		this.width = width;
	}

	public void setChess() {    //把Field类存储的数据转换为int型二维数组
		for(int i=0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				chess[i][j]=0;
				if(Chess[i][j].status() != 0){
					if(Chess[i][j].status() == 1){
						chess[i][j]=1;
					}
					else if(Chess[i][j].status() == 2){
						chess[i][j]=2;
					}
				}
			}
		}
	}

	public void ai() {
		int plaColor = 0;
		setChess();
		hm.put("1", 20);
		hm.put("11", 400);
		hm.put("111", 600);
		hm.put("1111", 9000);

		hm.put("12", 4);
		hm.put("112", 40);
		hm.put("1112", 400);
		hm.put("11112", 8000);

		hm.put("2", 8);
		hm.put("22", 80);
		hm.put("222", 1000);
		hm.put("2222", 10000);
		hm.put("22221",10000);

		hm.put("21", 6);
		hm.put("221", 60);
		hm.put("2221", 600);
		hm.put("121", 5);
		hm.put("1221", 5);
		hm.put("2112", 5);
		hm.put("212", 5);
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (chess[i][j] == 0) {
					// 向右
					String code = "";
					int color = 0;
					for (int k = j + 1; k < width; k++) {
						if (chess[i][k] == 0) {
							break;
							}else {
								// 有棋子
								if (color == plaColor) {
									color = chess[i][k];    // 保存第一颗棋子的颜色
									code += chess[i][k];    // 保存棋子相连的情况
										} else if (chess[i][k] == color)
											code += chess[i][k];    // 保存棋子相连的情况
											else {    // 不同棋子跳出循环
												code += chess[i][k];    // 保存棋子相连的情况
												break;
												}
									}
					}
					Integer value = hm.get(code);
					if (value != null)
					chessValue[i][j] += value; // 权值累加

					// 向左
					code = "";
					color = 0;
					for (int k = j - 1; k >= 0; k--) {
						if(chess[i][k] == 0) {
							break;
						}else {
							//有棋子
							if(color == plaColor) {
								color = chess[i][k];
								code += chess[i][k];
							}else if(chess[i][k] == color) {
								code +=chess[i][k];
							}else {
								code += chess[i][k];
								break;
									}
							}
					}
						value = hm.get(code);
						if (value != null)
						chessValue[i][j] += value; // 权值累加
					
					//向下
					code = "";
					color = 0;
					for (int k = i + 1; k < height; k++) {
						if(chess[k][j] == 0) {
							break;
						}else {
							//有棋子
							if(color == plaColor) {
								color = chess[k][j];
								code += chess[k][j];
							}else if(chess[k][j] == color) {
								code +=chess[k][j];
							}else {
								code += chess[k][j];
								break;
									}
						}
						
					}
					value = hm.get(code);
					if (value != null)
					chessValue[i][j] += value;    // 权值累加
					
				
				
					// 向上
					code = "";
					color = 0;
					for (int k = i - 1; k >= 0; k--) {
						if(chess[k][j] == 0) {
							break;
						}else {
							//有棋子
							if(color == plaColor) {
								color = chess[k][j];
								code += chess[k][j];
							}else if(chess[k][j] == color) {
								code +=chess[k][j];
							}else {
								code += chess[k][j];
								break;
									}
						}
					}
					value = hm.get(code);
					if (value != null)
					chessValue[i][j] += value;    // 权值累加
				
				
					// 左上
					code = "";
					color = 0;
					for (int m = i - 1, n = j - 1; m >= 0 && n >= 0; m--, n--) {
						if(chess[m][n] == 0) {
							break;
						}else {
							if(color == plaColor) {
								color = chess[m][n];
								code += chess[m][n];
							}else if(chess[m][n] == color){
								code += chess[m][n];
							}else {
								code += chess[m][n];
								break;
							}
						}
					}
					value = hm.get(code);
					if (value != null)
					chessValue[i][j] += value;    // 权值累加
					
					// 左下
					code = "";
					color = 0;
					for (int m = i + 1, n = j - 1;n >= 0 && m < height; m++, n--) {
						if(chess[m][n] == 0) {
							break;
						}else {
							if(color == plaColor) {
								color = chess[m][n];
								code += chess[m][n];
							}else if(chess[m][n] == color){
								code += chess[m][n];
							}else {
								code += chess[m][n];
								break;
							}
						}
					}
					value = hm.get(code);
					if (value != null)
					chessValue[i][j] += value;    // 权值累加
					
					// 右下
					code = "";
					color = 0;
					for (int m = i + 1, n = j + 1;n < width && m < height; m++, n++) {
						if(chess[m][n] == 0) {
							break;
						}else {
							if(color == plaColor) {
								color = chess[m][n];
								code += chess[m][n];
							}else if(chess[m][n] == color){
								code += chess[m][n];
							}else {
								code += chess[m][n];
								break;
							}
						}
					}
					value = hm.get(code);
					if (value != null)
					chessValue[i][j] += value; // 权值累加
					
					// 右上
					code = "";
					color = 0;
					for (int m = i - 1, n = j + 1;m >= 0 && n < width; m--, n++) {
						if(chess[m][n] == 0) {
							break;
						}else {
							if(color == plaColor) {
								color = chess[m][n];
								code += chess[m][n];
							}else if(chess[m][n] == color){
								code += chess[m][n];
							}else {
								code += chess[m][n];
								break;
							}
						}
					}
					value = hm.get(code);
					if (value != null)
					chessValue[i][j] += value; // 权值累加
				}
		}
	}
		int maxv = 0;

		for (int i = 0; i <height; i++) {
			for(int j = 0;j <width; j++) {
				if (maxv < chessValue[i][j]) {
					maxv = chessValue[i][j];
					maxi = i;
					maxj = j;
				}
			}
		}
		

		for (int j = 0; j < width; j++)
			for (int i = 0; i < height; i++)
					chessValue[i][j] = 0;
	}
	
	public int getx() {    //返回机器下点的x值
		return maxj;
	}

	public int gety() {    //返回机器下点的y值
		return maxi;
	}

}



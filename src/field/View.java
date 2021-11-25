package field;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cell.Cell;

public class View extends JPanel {
	//被保存的对象继承了Serializable，所以先前保存的对象已经有一个序列号，如果将类进行改变了，那么该类的序列号就会发生改变，因此被保存的对象就不能赋值给改变后的类的对象
	private static final long serialVersionUID = -5258995676212660595L;
	public static final int GRID_SIZE = 30;    //设置方格大小
	private Field theField;
	public int mouseX = -1;
	public int mouseY = -1;
	
	public View(Field field) {
		theField = field;
	}

	@Override
	public void paint(Graphics g) {    //画出整个棋盘
		super.paint(g);
		for ( int row = 0; row < theField.getHeight(); row++ ) {
			for ( int col = 0; col < theField.getWidth(); col++ ) {
				Cell cell = theField.get(row, col);    //获取cell的位置
				if ( cell != null ) {
					cell.draw(g, col*GRID_SIZE, row*GRID_SIZE, GRID_SIZE);    //画出小方格
				}
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {    //封装组件的宽度和高度，以调整窗口大小
		return new Dimension(theField.getWidth()*GRID_SIZE+1, theField.getHeight()*GRID_SIZE+1);
	}

	public static void main(String[] args) {    //仅供调试使用
		Field field = new Field(15,15);
		for ( int row = 0; row<field.getHeight(); row++ ) {
			for ( int col = 0; col<field.getWidth(); col++ ) {
				field.place(row, col, new Cell());
			}
		}
		View view = new View(field);
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
				view.mouseX = (int) (e.getX() - 7) / GRID_SIZE ;    //不知道最左坐标为什么是7
				view.mouseY = (int) (e.getY() - 30) / GRID_SIZE ;    //不知道最上坐标为什么是30
				// view.mouseX = (int) e.getX();
				// view.mouseY = (int) e.getY();
				System.out.println("x=" + view.mouseX + "   y=" + view.mouseY);		
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
	}

}
  
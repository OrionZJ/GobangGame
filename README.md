# <center>GobangGame</center>
<center>一个下五子棋的java小程序</center>
<br>

# 概述及目标

五子棋是一种两人对弈的纯策略型棋类游戏，通常双方分别使用两色的棋子，下在棋盘上，先形成5子连线者获胜。

# 管理棋子

棋子由Cell类管理，Cell类的变量situation记录当前棋格上是否有棋子，落了什么棋子（默认无棋0，用户的棋1，电脑的棋2）。

Cell有两个方法：
- `status()`返回当前棋格中的落子情况
- `draw(Graphics g, int x, int y, int size)`通过Graphics类画出当前棋格，x、y是矩形左上角的位置，size为方格的大小

# 管理棋盘

棋盘由Field类管理，有变量width、height管理整个棋盘的大小，field[ ][ ]的本质是由Cell类组成的“二维数组”。

重要的方法大致包括：
- `place(int row, int col, Cell s)`更改row行、col列的棋格s的状态，也就是“把棋放到棋盘上”。
- `get(int row, int col)`获得row行、col列的落棋情况
- `ifWin(int row, int col)`判胜负：从横向、纵向、反斜杠和斜杠四方向去判断，任何一个方向的5个黑或白棋连成一条线，则胜利。

# 进行游戏

`Play.java`运行游戏的命令行界面版。

棋盘是由长宽为15*15的“.”符号绘制形成。用户执X、电脑执O，其中X先下。

下子时是通过输入坐标来确定所下棋子的位置，用户下X时需要通过手动输入row和col坐标下子，在输入坐标时应注意不能超出棋盘的大小，即row和col值均不能超出15，否则提示输入非法，需重新输入下子的坐标。电脑下子时的坐标使用Robot类生成，具体下文会有介绍。以下是部分代码实现。

判断输入是否非法包括：
- 判断下棋位置是否超出棋盘
```java
//判断落棋位置是否合理（越界）
	public static boolean ifReasonable(int limit, int input) {
		if (input >= 0 && input < limit) return true;
		else return false;
	}
```

- 判断下棋位置是否已经有落子
```java
//判断落棋位置是否冲突（事先有棋）
	public static boolean ifConflict(int row, int col, Field field) {
		if (field.get(row, col).status() == 0) return false;
		else return true;
	}
```
打印格子和打印棋盘的函数不再赘述，下面是执行流程：
```java
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
    conflict = ifConflict(row, col, table);    //若用户输入数据不可下，则重复要求用户输入数据
    reasonable = false;    //重置
} while (conflict);
//放置用户下的子
table.place(row, col, usrCell);
printTable(table);
//判断输赢
if (ifWin = table.ifWin(row, col)) {
    System.out.println("You win!");
    break;
}
//电脑下子
Robot robot = new Robot(table.field,table.getHeight(),table.getWidth());
robot.ai();
row = robot.gety(); col = robot.getx();
//放置电脑下的棋
table.place(row, col, comCell);
System.out.println("电脑下子：["+(row+1)+' '+(col+1)+"]");
printTable(table);
//判断输赢
if (ifWin = table.ifWin(row, col)) {
    System.out.println("You lose.");
    break;
}
```

以下是命令行界面的运行结果：
```
请输入下子的坐标：[row col]7 6
...............
...............
...............
...............
...............
.....O.........
.....XX........
...............
...............
...............
...............
...............
...............
...............
...............
电脑下子：[7 5]
...............
...............
...............
...............
...............
.....O.........
....OXX........
...............
...............
...............
...............
...............
...............
...............
...............
```

# GUI图形界面

界面可视化由View类管理。View类继承自面板组件JPanel类。单击格子即可完成下棋操作。

<img src="demo\下棋GUI截图.png" width = "200" alt="下棋GUI截图" />

最重要的方法是：`paint(Graphics g)`一个个画出Cell类的方格，最终绘制出整个棋盘。

要运行GUI图形界面，则运行`GUI.java`,大致过程与命令行界面差不多，不同之处是创建窗体并添加鼠标事件监视器。鼠标事件监视器获取鼠标位置并转换为单击方格的位置坐标，每次下棋后repaint棋盘。
```java
//创建窗体
View view = new View(table);
JFrame frame = new JFrame();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setResizable(false);
frame.setTitle("GobangGame");
frame.add(view);
frame.addMouseListener(new MouseListener() {   //为窗口添加鼠标事件监听器
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        if(e.getButton()==MouseEvent.BUTTON1){    // 判断获取的按钮是否为鼠标的左击     
        view.mouseX = (int) (e.getX() - 7) / View.GRID_SIZE ;    //窗体左边界坐标为7
        view.mouseY = (int) (e.getY() - 30) / View.GRID_SIZE ;    //包含标题栏上边界坐标为30
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
```

得出输赢结果后弹窗提醒：

<img src="demo\输赢弹窗截图.png" width = "150" alt="输赢弹窗截图" />

```java
JOptionPane.showMessageDialog(null, "你赢了。");
```

# 简单的AI对战

最后是AI的思路，这里参考别人资料使用权值算法：

对棋盘上所有空着没棋子的地方，每一处空位都进行八个方向的权值判断，八个方向的权值加起来就为该处的权值。ai就会下在权值最大的那一处空位。

用哈希表创建一个权值表，将各种棋子的排列情况作为键，对应设定数字大小表示这种排列的威胁程度以及重要性。供判断后权值取用。

步骤如下：
1. 创建权值表：HashMap<String,Integer> hm = new HashMap<String,Integer>();
2. 人先下，判断输赢！
3. 搜索棋盘上棋局情况，设置权值 定义chessValue[][]数组，保存棋盘上的权值
4. 找出chessValue最大值的交点位置，该位置电脑下棋，判断输赢！
5. 清空chessValue数组

Robot类中ai方法，返回了ai要下的棋的棋盘位置。
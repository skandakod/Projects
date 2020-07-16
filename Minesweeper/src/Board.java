import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{

	int order =0, widthOrder=0;
	int [][]bX, bY;
	boolean[][]mines;	
	int[][]nums;
	boolean[][]opened;
	boolean[][]flagged;
	boolean reveal = false;

	static JFrame f;
	Timer timer;
	static Board board;
	static JLabel startL, leftC, rightC, time, result, timeC, highScore, highScoreC, minesLeft, minesLeftC;
	static JButton startB, again;
	static JCheckBox b15, b20, b25, b30;
	boolean start = false;
	static Font font1;
	double elapsedTime = 0;
	static double highscore = 0;
	int mineCount = 0;
	static int MINES_NUM = 0;

	public Board()
	{
		setBackground(Color.BLACK);
		setLayout(null);
		timer = new Timer(100, this);
	}

	public static Board createGUI()
	{
		f = new JFrame();
		f.setSize(950,700);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		board = new Board();
		font1 = new Font ("Comic Sans MS", Font.PLAIN, 30);

		startL = new JLabel("Click to Start Game");
		startL.setBounds(50,20,500,50);
		startL.setFont(font1);
		startL.setForeground(Color.WHITE);
		board.add(startL);

		again = new JButton("Restart");
		again.setBounds(350,20,150,50);
		again.setFont(font1);
		again.setForeground(Color.WHITE);
		again.setBackground(Color.black);
		again.setFocusPainted(false);
		again.addActionListener(board);
		board.add(again);
		again.setVisible(false);

		b15 = new JCheckBox("15");
		b15.setBounds(530,20,75,50);
		b15.setFont(font1);
		b15.setForeground(Color.WHITE);
		b15.setBackground(Color.black);
		b15.setFocusPainted(false);
		b15.addActionListener(board);
		board.add(b15);

		b20 = new JCheckBox("20");
		b20.setBounds(630,20,75,50);
		b20.setFont(font1);
		b20.setForeground(Color.WHITE);
		b20.setBackground(Color.black);
		b20.setFocusPainted(false);
		b20.addActionListener(board);
		board.add(b20);

		b25 = new JCheckBox("25");
		b25.setBounds(730,20,75,50);
		b25.setFont(font1);
		b25.setForeground(Color.WHITE);
		b25.setBackground(Color.black);
		b25.setFocusPainted(false);
		b25.addActionListener(board);
		board.add(b25);

		b30 = new JCheckBox("30");
		b30.setBounds(830,20,75,50);
		b30.setFont(font1);
		b30.setForeground(Color.WHITE);
		b30.setBackground(Color.black);
		b30.setFocusPainted(false);
		b30.addActionListener(board);
		board.add(b30);

		leftC = new JLabel("Left Click to Clear a Tile");
		leftC.setBounds(530,90,500,50);
		leftC.setFont(font1);
		leftC.setForeground(Color.WHITE);
		board.add(leftC);

		rightC = new JLabel("Right Click to Mark a Mine");
		rightC.setBounds(530,140,500,50);
		rightC.setFont(font1);
		rightC.setForeground(Color.WHITE);
		board.add(rightC);

		result = new JLabel("");
		result.setBounds(190,575,500,50);
		result.setFont(font1);
		result.setForeground(Color.WHITE);
		board.add(result);

		time = new JLabel("Elapsed Time");
		time.setBounds(530,210,500,50);
		time.setFont(font1);
		time.setForeground(Color.WHITE);
		board.add(time);

		timeC = new JLabel("0");
		timeC.setBounds(530,260,500,50);
		timeC.setFont(font1);
		timeC.setForeground(Color.WHITE);
		board.add(timeC);

		minesLeft = new JLabel("Mines Left");
		minesLeft.setBounds(530,330,500,50);
		minesLeft.setFont(font1);
		minesLeft.setForeground(Color.WHITE);
		board.add(minesLeft);

		minesLeftC = new JLabel("");
		minesLeftC.setBounds(530,380,500,50);
		minesLeftC.setFont(font1);
		minesLeftC.setForeground(Color.WHITE);
		board.add(minesLeftC);

		highScore = new JLabel("Best Time");
		highScore.setBounds(530,450,500,50);
		highScore.setFont(font1);
		highScore.setForeground(Color.WHITE);
		board.add(highScore);

		highScoreC = new JLabel("0");
		highScoreC.setBounds(530,500,500,50);
		highScoreC.setFont(font1);
		highScoreC.setForeground(Color.WHITE);
		board.add(highScoreC);

		readScore();

		startB = new JButton("Start");
		startB.setBounds(350,20,150,50);
		startB.setFont(font1);
		startB.setForeground(Color.WHITE);
		startB.setBackground(Color.black);
		startB.setFocusPainted(false);
		startB.addActionListener(board);
		board.add(startB);

		f.add(board);
		f.setVisible(true);	

		return board;
	}	

	public void setOrder(int num, int width, boolean[][]minesBox, int[][]numbers, int bombNum)
	{
		mineCount = bombNum;
		MINES_NUM=bombNum;
		minesLeftC.setText(mineCount+"");
		order = num;
		widthOrder = width;
		mines = minesBox;
		nums=numbers;
		opened = new boolean[order][order];
		flagged = new boolean[order][order];
		bX = new int[order][order];
		bY = new int[order][order];

		readScore();
		setBoxCoords();
		clickSet();		
	}

	public boolean update(int x, int y)
	{
		if(start)
		{
			int arr[]= {x,y};	
			flagged[x][y]=false;
			pathFind(arr,nums, mines);
			repaint();
			if(checkVictory())
			{
				return true;
			}
		}
		return false;
	}

	public void flag(int i, int j)
	{
		if(start)
		{
			if(flagged[i][j])
			{
				flagged[i][j]=false;
				mineCount++;			
				if(mineCount>=0)
				{
					minesLeftC.setText(mineCount+"");
				}			
			}
			else
			{
				if(!opened[i][j])
				{
					mineCount--;
					if(mineCount>=0)
					{
						minesLeftC.setText(mineCount+"");
					}
					flagged[i][j]=true;
				}
			}
			repaint();
		}
	}

	public boolean checkVictory()
	{
		boolean victory = false;
		for(int i = 0;i<order;i++)
		{
			for(int j = 0;j<order;j++)
			{
				if(!mines[i][j])
				{
					if(opened[i][j])
					{
						victory = true;
					}
					else
					{
						victory = false;
						return victory;
					}
				}
			}
		}
		timer.stop();
		result.setText("YOU WIN!");
		saveScore();
		return victory;
	}

	public void clickSet()
	{
		for(int i = 0;i<order;i++)
		{
			for(int j = 0;j<order;j++)
			{
				opened[i][j]=false;
			}
		}
	}

	public void setBoxCoords()
	{
		for(int j= 0;j<order;j++)
		{			
			for(int i= 0;i<order;i++)
			{
				bX[i][j]=50+(widthOrder*i);
			}
		}

		for(int j= 0;j<order;j++)
		{			
			for(int i= 0;i<order;i++)
			{
				bY[i][j]=100+(widthOrder*j);
			}						
		}
	}

	public void pathFind(int currBox[], int [][]nums, boolean [][]mines)
	{
		int x = currBox[0];
		int y = currBox[1];

		if(x>=0 && x<order && y>=0 && y<order)
		{
			if(mines[x][y])
			{
				return;
			}
			else if(nums[x][y]!=0)
			{
				opened[x][y]=true;
				flagged[x][y]=false;
				return;
			}
			else
			{
				opened[x][y]=true;
				flagged[x][y]=false;
				if(x-1>=0)
				{
					if(!opened[x-1][y])
					{
						currBox[0]--;
						pathFind(currBox, nums, mines);
						currBox[0]++;
					}
				}

				if(x+1<order)
				{
					if(!opened[x+1][y])
					{
						currBox[0]++;
						pathFind(currBox, nums, mines);
						currBox[0]--;
					}
				}

				if(y-1>=0)
				{
					if(!opened[x][y-1])
					{
						currBox[1]--;
						pathFind(currBox, nums, mines);
						currBox[1]++;
					}
				}

				if(y+1<order)
				{
					if(!opened[x][y+1])
					{
						currBox[1]++;
						pathFind(currBox, nums, mines);
						currBox[1]--;
					}
				}

				if(x-1>=0 && y-1>=0)
				{
					if(!opened[x-1][y-1])
					{
						currBox[0]--;
						currBox[1]--;
						pathFind(currBox, nums, mines);
						currBox[0]++;
						currBox[1]++;
					}
				}

				if(x+1<order && y-1>=0)
				{
					if(!opened[x+1][y-1])
					{
						currBox[0]++;
						currBox[1]--;
						pathFind(currBox, nums, mines);
						currBox[0]--;
						currBox[1]++;
					}
				}

				if(x-1>=0 && y+1<order)
				{
					if(!opened[x-1][y+1])
					{
						currBox[0]--;
						currBox[1]++;
						pathFind(currBox, nums, mines);
						currBox[0]++;
						currBox[1]--;
					}
				}

				if(x+1<order && y+1<order)
				{
					if(!opened[x+1][y+1])
					{
						currBox[0]++;
						currBox[1]++;
						pathFind(currBox, nums, mines);
						currBox[0]--;
						currBox[1]--;
					}
				}
			}
		}
		else
		{
			return;
		}

		repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{			
		super.paintComponent(g);	


		g.setColor(Color.GRAY);
		g.drawRect(50,100,(30*order),(30*order));

		for(int i = 0;i<order;i++)
		{
			g.drawLine(50+widthOrder*i, 100, 50+widthOrder*i, 100+(order*30));
		}

		for(int i = 0;i<order;i++)
		{
			g.drawLine(50, 100+widthOrder*(i+1), 50+(order*30), 100+widthOrder*(i+1));
		}

		if(start)
		{
			if(reveal)
			{
				for(int i = 0;i<order;i++)
				{
					for(int j = 0;j<order;j++)
					{
						if(mines[i][j])
						{
							g.setColor(Color.red);
							g.fillOval(bX[i][j]+4, bY[i][j]+4, widthOrder-8, widthOrder-8);
						}
					}
				}
			}

			for(int i = 0;i<order;i++)
			{
				for(int j = 0;j<order;j++)
				{
					if(opened[i][j])
					{					
						g.setColor(Color.cyan);
						g.fillRect(bX[i][j]+1, bY[i][j]+1, widthOrder-1, widthOrder-1);
						if(nums[i][j]!=0)
						{
							g.setColor(Color.black);
							g.setFont(font1);
							g.drawString(nums[i][j]+"", bX[i][j]+8, bY[i][j]+27);
						}
					}
				}
			}

			/*for(int i = 0;i<order;i++)
		{
			for(int j = 0;j<order;j++)
			{
					if(nums[i][j]!=0)
					{
						g.setColor(Color.black);
						g.drawString(nums[i][j]+"", bX[i][j]+12, bY[i][j]+17);
					}

			}
		}*/

			for(int i = 0;i<order;i++)
			{
				for(int j = 0;j<order;j++)
				{
					if(flagged[i][j])
					{
						g.setColor(Color.orange);
						g.fillOval(bX[i][j]+4, bY[i][j]+4, widthOrder-8, widthOrder-8);
					}
				}
			}
		}
	}

	public void reveal()
	{
		timer.stop();
		reveal = true;
		repaint();
	}

	public static void readScore()
	{
		try {

			BufferedReader br = new BufferedReader(new FileReader("bestTime.txt"));

			for(int i = 15;i<=MINES_NUM;i+=5)
			{
				highscore = Double.parseDouble(br.readLine());				
			}			

			if(highscore != 0)
			{
				highScoreC.setText(highscore/1000 + "s");
			}
			else
			{
				highScoreC.setText("(NONE)");
			}
			br.close();

		} catch (IOException e) {}
	}

	public void saveScore()
	{
		try {

			BufferedReader br = new BufferedReader(new FileReader("bestTime.txt"));

			double temp[]=new double[4];
			for(int i = 0;i<4;i++)
			{
				temp[i] = Double.parseDouble(br.readLine());
			}


			PrintWriter pw = new PrintWriter(new FileWriter("bestTime.txt"));
			double num=0;

			for(int i = 15;i<=30;i+=5)
			{
				if(i==MINES_NUM)
				{
					if(elapsedTime<temp[i/5-3] || temp[i/5-3]==0)
					{
						timeC.setText(elapsedTime/1000 + "s | New Highscore!");
						pw.println(elapsedTime);
					}
				}
				else
				{
					pw.println(temp[i/5-3]);
				}				
			}		

			pw.close();
			br.close();

		} catch (IOException e) {}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource()==timer)
		{
			elapsedTime+=100;
			timeC.setText(elapsedTime/1000+"s");
		}		
	}	
}

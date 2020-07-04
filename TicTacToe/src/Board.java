import java.awt.*;
import javax.swing.*;

public class Board extends JFrame {

	int order =0, widthOrder=0, counter = 0, pScore =0, cScore =0;;
	boolean highlight = false;
	boolean [][]click, compClick;
	int[]highlightBoxNum = new int[2];
	int [][]bX, bY;
	
	JButton playAgain,menu;
	JLabel winnerC, winnerP,draw,score, scoreC, scoreP;
	Container frame;

	public Board()
	{
		super("TicTacToe");
		setSize(555,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setResizable(false);
		setLocationRelativeTo(null);		
		
		Font font1 = new Font ("Comic Sans MS", Font.PLAIN, 30);
				
		frame = getContentPane();
		frame.setLayout(null);
		frame.setBackground(Color.black);
		
		winnerC = new JLabel("The Computer Beat You!");
		winnerC.setBounds(105,550,600,50);
		winnerC.setFont(font1);
		winnerC.setForeground(Color.white);
		winnerC.setVisible(false);
		frame.add(winnerC);
		
		winnerP = new JLabel("Congrats!! You Won!");
		winnerP.setBounds(143,550,600,50);
		winnerP.setFont(font1);
		winnerP.setForeground(Color.white);
		winnerP.setVisible(false);
		frame.add(winnerP);
		
		draw = new JLabel("It's A Draw!");
		draw.setBounds(193,550,600,50);
		draw.setFont(font1);
		draw.setForeground(Color.white);
		draw.setVisible(false);
		frame.add(draw);
		
		score = new JLabel("First To 3 Wins!");
		score.setBounds(160,0,600,50);
		score.setFont(font1);
		score.setForeground(Color.white);
		frame.add(score);
		
		menu = new JButton("Main Menu");
		menu.setBounds(35,700,480,50);
		menu.setFont(font1);
		menu.setForeground(Color.white);
		menu.setBackground(Color.BLACK);
		menu.setFocusPainted(false);
		frame.add(menu);
		
		scoreC = new JLabel(cScore+"");
		scoreC.setBounds(430,0,600,50);
		scoreC.setFont(font1);
		scoreC.setForeground(Color.white);
		frame.add(scoreC);
		
		scoreP = new JLabel(pScore+"");
		scoreP.setBounds(100,0,600,50);
		scoreP.setFont(font1);
		scoreP.setForeground(Color.white);
		frame.add(scoreP);
		
		playAgain = new JButton("Click Here For The Next Round!");
		playAgain.setBounds(35,620,480,50);
		playAgain.setFont(font1);
		playAgain.setForeground(Color.white);
		playAgain.setBackground(Color.BLACK);
		playAgain.setFocusPainted(false);
		playAgain.setVisible(false);
		frame.add(playAgain);		
	}
	
	public void setOrder(int num, int width)
	{
		order = num;
		widthOrder = width;
		click = new boolean[order][order];
		compClick = new boolean[order][order];
		bX = new int[order][order];
		bY = new int[order][order];
		
		setBoxCoords();
		clickSet();		
	}

	public void reset()
	{
		setBoxCoords();
		clickSet();
		repaint();
	}	
	
	public void clickSet()
	{
		for(int i = 0;i<order;i++)
		{
			for(int j = 0;j<order;j++)
			{
				click[i][j] = false;
				compClick[i][j] = false;
			}
		}
	}

	public void setBoxCoords()
	{
		for(int j= 0;j<order;j++)
		{			
			for(int i= 0;i<order;i++)
			{
				bX[i][j]=35+(widthOrder*i);
			}
		}
		
		for(int j= 0;j<order;j++)
		{			
			for(int i= 0;i<order;i++)
			{
				bY[i][j]=75+(widthOrder*j);
			}						
		}
	}

	public void paint (Graphics g)
	{			
		super.paintComponents(g);	
		g.setColor(Color.white);
		g.drawRect(35,75,480,480);
		
		for(int i = 0;i<order;i++)
		{
			g.drawLine(35+widthOrder*i, 75, 35+widthOrder*i, 555);
		}
		
		for(int i = 0;i<order;i++)
		{
			g.drawLine(35, 75+widthOrder*(i+1), 515, 75+widthOrder*(i+1));
		}
		
		g.setColor(Color.RED);
		g.fillOval(35,30,40,40);
		
		g.setColor(Color.BLUE);
		g.fillOval(477,30,40,40);

		if(highlight)
		{
			g.setColor(Color.green);
			g.fillRect(bX[highlightBoxNum[0]][highlightBoxNum[1]]+1, bY[highlightBoxNum[0]][highlightBoxNum[1]]+1, widthOrder-1, widthOrder-1);
			highlight = false;
		}

		for(int j = 0;j<order;j++)
		{
			for(int i = 0;i<order;i++)
			{
				if(click[i][j])
				{
					g.setColor(Color.red);
					g.fillRect(bX[i][j]+1, bY[i][j]+1, widthOrder-1, widthOrder-1);
				}
				else if(compClick[i][j])
				{					
					g.setColor(Color.blue);
					g.fillRect(bX[i][j]+1, bY[i][j]+1, widthOrder-1, widthOrder-1);
				}
			}
		}
	}

	public void highlight(int[]arr)
	{
		if(arr[0]>=0 && arr[1]>=0)
		{
			highlightBoxNum[0] = arr[0];
			highlightBoxNum[1] = arr[1];
			highlight = true;
			repaint();
		}
		else
		{
			highlight = false;
		}
	}

	public void click(int[]arr)
	{
		highlightBoxNum[0] = arr[0];
		highlightBoxNum[1] = arr[1];
		click[arr[0]][arr[1]]=true;
		highlight = false;
		repaint();
	}

	public void clickComp(int[]arr)
	{		
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		highlightBoxNum[0] = arr[0];
		highlightBoxNum[1] = arr[1];
		compClick[arr[0]][arr[1]]=true;
		highlight = false;		
		repaint();
	}
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener,KeyListener{

	int x=0,y=400,xSpeed=1,ySpeed=0,scl=20;
	int levelCnt = 1;
	
	int fM = 5;
	
	Food food;
	int[][] tail = new int[1600][2];
	int total = 1;
	int temp = 0;
	int livesCounter = 3;
	int finalScore[] = new int[3];
	boolean death = false;
	boolean startGame = true, endGame = false;

	JLabel levelN,welcome,length,score;
	JLabel start,again,start2,nextLvl,speed;
	JLabel livesImage;
	Container frame,frame1,frame2;
	JFrame splash  = new JFrame();
	JFrame result  = new JFrame();

	Font font = new Font ("Comic Sans MS", Font.BOLD, 24);
	Font font2 = new Font ("Comic Sans MS", Font.PLAIN, 20);
	Font font3 = new Font ("Comic Sans MS", Font.BOLD, 36);
	
	Color [] snakeColors = new Color[5];
	
	ImageIcon lives;
	
	ArrayList<Ellipse2D> livesOvals= new ArrayList<Ellipse2D>();
	
	Ellipse2D livesOvals1 = new Ellipse2D.Double(790,115,40,40);
	Ellipse2D 	livesOvals2 = new Ellipse2D.Double(860,115,40,40);
	Ellipse2D 	livesOvals3 = new Ellipse2D.Double(930,115,40,40);
	
	public Window()
	{		
		super("");
		setSize(1000,800);
		addKeyListener(this);
		splash.addKeyListener(this);	
		result.addKeyListener(this);
		
		finalScore[0] = 1;
		livesOvals.add(livesOvals1);
		livesOvals.add(livesOvals2);
		livesOvals.add(livesOvals3);
		
		frame = getContentPane();
		frame.setLayout(null);
		frame.setBackground(Color.BLACK);
		frame1 = splash.getContentPane();
		frame1.setLayout(null);
		frame1.setBackground(Color.BLACK);

		frame2 = result.getContentPane();
		frame2.setLayout(null);
		frame2.setBackground(Color.BLACK);

		splash.setTitle("Snake");
		splash.setSize(800,800);
		splash.setVisible(true);

		result.setTitle("Game Over!");
		result.setSize(800,800);
		result.setVisible(false);

		start = new JLabel("Welcome to Snake!");
		start.setBounds(280,300,300,100);
		start.setFont(font);
		start.setForeground(Color.WHITE);
		splash.add(start);

		start2 = new JLabel("Press spacebar to start!");
		start2.setBounds(250,350,300,100);
		start2.setFont(font);
		start2.setForeground(Color.WHITE);
		splash.add(start2);

		again = new JLabel("Press enter to play again!");
		again.setBounds(170,600,600,100);
		again.setFont(font3);
		again.setForeground(Color.WHITE);
		result.add(again);

		length = new JLabel("Total length: " + total);
		length.setBounds(780,0,200,50);
		length.setFont(font);
		length.setForeground(Color.WHITE);
		add(length);
		
		speed = new JLabel("Current Speed: " + fM);
		speed.setBounds(780,210,200,50);
		speed.setFont(font2);
		speed.setForeground(Color.WHITE);
		add(speed);
		
		nextLvl = new JLabel("(" + (10-(total%10)) + ") more until lvl " + (levelCnt+1) + "!");
		nextLvl.setBounds(780,180,250,50);
		nextLvl.setFont(font2);
		nextLvl.setForeground(Color.WHITE);
		add(nextLvl);

		score = new JLabel("Final Score: " + finalScore[0]);
		score.setBounds(270,100,300,50);
		score.setFont(font3);
		score.setForeground(Color.WHITE);
		result.add(score);

		levelN = new JLabel("Level: " + levelCnt);
		levelN.setBounds(780,150,100,50);
		levelN.setFont(font);
		levelN.setForeground(Color.WHITE);
		add(levelN);
		
		setSnakeColor();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		food = new Food();
		food.setLocation(36, 35);
		setResizable(false);
		splash.setResizable(false);
		result.setResizable(false);
		setVisible(false);              
	}
	
	public void setSnakeColor()
	{
		snakeColors[0] = Color.green;
		snakeColors[1] = Color.blue;
		snakeColors[2] = Color.yellow;
		snakeColors[3] = Color.pink;
		snakeColors[4] = Color.magenta;
	}

	public void paint(Graphics g)
	{            
		super.paintComponents(g);  		

		g.setColor(Color.WHITE);
		g.drawRect(20,40,760,740);
		
		g.setColor(Color.red);
		Graphics2D g2 = (Graphics2D) g;
		for(Ellipse2D oval : livesOvals)
		{
			g2.fill(oval);
		}		

		x+=xSpeed*scl;
		y+=ySpeed*scl;  

		if(x>760)
		{
			x = x-760;
		}
		if(x<20)
		{
			x+=760;
		}

		if(y>760)
		{
			y-=740;
		}
		if(y<40)
		{
			y+=740;
		}

		for(int i =0;i<total;i++)
		{
			if(x==tail[i][0] && y==tail[i][1])
			{
				death = true;
				temp = total;
				length.setText("Total score: " + total);
				fM = 5;
				levelN.setText("Level: " + levelCnt);

			}
		}       

		if(death)
		{
			int win = 0;
			livesCounter--;
			g2.setColor(Color.BLACK);
			for(Ellipse2D oval : livesOvals)
			{
				g2.fill(oval);
			}	
			livesOvals.remove(livesCounter);
			g2.setColor(Color.red);
			for(Ellipse2D oval : livesOvals)
			{
				g2.fill(oval);
			}
			
			endGame= true;
			
			if(livesCounter==0)
			{
			finalScore[2-livesCounter] = total;
			if(finalScore[0]>finalScore[1] && finalScore[0]>finalScore[2])
			{
				win = 0;
			}
			else if(finalScore[1]>finalScore[2] && finalScore[1]>finalScore[0])
			{
				win = 1;
			}
			else if(finalScore[2]>finalScore[1] && finalScore[2]>finalScore[0])
			{
				win = 2;
			}
			total = finalScore[win];
			System.out.println(finalScore[0] + " " + finalScore[1] + " " + finalScore[2]);
			score.setText("Highest Score: " + (total));
			total= 1;
			for(int i =0;i<4;i++)
			{
				g.setColor(Color.RED);
				g.fillRect(food.getFoodX(), food.getFoodY(), scl, scl);
				int tempColor=levelCnt;
				if(levelCnt>5)
				{
					tempColor = levelCnt;
					levelCnt-=5;
				}
				g.setColor(snakeColors[levelCnt-1]);
				levelCnt = tempColor;
				g.fillRect(x, y, scl, scl);
				for(int a =0;a<temp;a++)
				{
					g.fillRect(tail[a][0],tail[a][1],scl,scl);
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.setColor(Color.BLACK);
				g.fillRect(21, 51, 758, 728);

				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}                       
			}
			setVisible(false);
			result.setVisible(true);
			}

			wash();
		}

		g.setColor(Color.RED);
		g.fillRect(food.getFoodX(), food.getFoodY(), scl, scl);
		
		int tempColor=levelCnt;
		if(levelCnt>5)
		{
			tempColor = levelCnt;
			levelCnt-=5;
		}
		g.setColor(snakeColors[levelCnt-1]);
		levelCnt = tempColor;
		
		g.fillRect(x, y, scl, scl);

		for(int i =0;i<total-1;i++)
		{
			for(int j =0;j<2;j++)
			{
				tail[i][j] = tail[i+1][j];
			}                       
		}

		if(total>0)
		{
			tail[total-1][0]=x;
			tail[total-1][1]=y;
		}

		for(int i =0;i<total;i++)
		{
			g.fillRect(tail[i][0],tail[i][1],scl,scl);
		}       

		eatCheck();

		if(total==levelCnt*10)
		{
			levelCnt++;
			levelN.setText("Level: " + levelCnt);	
			nextLvl.setText("(" + (10-(total%10)) + ") more until lvl " + (levelCnt+1) + "!");
			fM+=5;
			speed.setText("Current speed: " + fM);
		}

		try {
			Thread.sleep(1000/fM);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}

	public void eatCheck()
	{
		if((food.getFoodX() == x) && (food.getFoodY() == y))
		{
			total++;
			length.setText("Total score: " + total);
			nextLvl.setText("(" + (10-(total%10)) + ") more until lvl " + (levelCnt+1) + "!");
			food.setLocation(36, 35);
		}
	}

	public void wash()
	{
		for(int i =0;i<temp;i++)
		{
			for(int j =0;j<2;j++)
			{
				tail[i][j] = 0;
			}
		}
		death = false;
		finalScore[2-livesCounter] = total;
		total = 1;
		levelCnt = 1;
		fM = 5;
		length.setText("Total score: " + total);
		levelN.setText("Level: " + levelCnt);
		nextLvl.setText("(" + (10-(total%10)) + ") more until lvl " + (levelCnt+1) + "!");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Window();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			if(startGame)
			{
				splash.setVisible(false);
				setVisible(true);
				startGame = false;
			}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(endGame)
			{
				result.setVisible(false);
				setVisible(true);
				endGame = false;
				wash();
			}
		}

		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			ySpeed=-1;
			xSpeed=0;
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			ySpeed=1;
			xSpeed=0;
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			ySpeed=0;
			xSpeed=1;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			ySpeed=0;
			xSpeed=-1;
		}
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}

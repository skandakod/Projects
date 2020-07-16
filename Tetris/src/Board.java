import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

	public class Board extends JPanel implements ActionListener, KeyListener{
		
		int piecesNum=0, bufferX = 3, bufferY = 0, drawX = 0, drawY=0, delay = 800, num=0;
		boolean[][]buffer, nextPiece;
		int[]bX, bY;
		Color c, nextColor;

		int tempX[]=new int[2];
		int tempY[]=new int[2];
		
		boolean[][]isOcc=new boolean[10][22];
		Color[][]occCol=new Color[10][22];
		
		static int highscore=0;
		boolean contact=false, end=false;

		Timer timer;
		Piece piece = new Piece();

		static JLabel next, score, level, gameOver, highScore, pause, resume;
		static JButton again;
		static JFrame f;
		
		int linesCount = 0;
		int scoreCount = 0;
		int levelCount = 1;
	    
		public Board() 
		{
			setBackground(Color.black);
			setLayout(null);
			timer = new Timer(delay,this);
			startDropping();
			start();
	    }
	    
	    public static void CreateGUI()
	    {
	    	f = new JFrame();
	    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setSize(600,850);
	        f.setResizable(false);
	        f.setLocationRelativeTo(null);

	        Board board=new Board();				
			
			Font font1 = new Font ("Comic Sans MS", Font.PLAIN, 30);

			next = new JLabel("Next Piece");
			next.setBounds(375,150,200,50);
			next.setFont(font1);
			next.setForeground(Color.WHITE);
			board.add(next);
			
			score = new JLabel("Score: 0");
			score.setBounds(375,30,300,50);
			score.setFont(font1);
			score.setForeground(Color.WHITE);
			board.add(score);
			
			highScore = new JLabel("");
			highScore.setBounds(375,90,500,50);
			highScore.setFont(font1);
			highScore.setForeground(Color.WHITE);
			board.add(highScore);			

			readScore();
			
			level = new JLabel("Level 1 | 0/10");
			level.setBounds(375,720,275,50);
			level.setFont(font1);
			level.setForeground(Color.WHITE);
			board.add(level);
			
			gameOver = new JLabel("GAME OVER");
			gameOver.setBounds(385,400,200,50);
			gameOver.setFont(font1);
			gameOver.setForeground(Color.WHITE);
			board.add(gameOver);
			gameOver.setVisible(false);
			
			again = new JButton("Retry");
			again.setBounds(385,450,178,50);
			again.setFont(font1);
			again.setForeground(Color.WHITE);
			again.setBackground(Color.black);
			again.setFocusPainted(false);
			again.addActionListener(board);
			board.add(again);
			again.setVisible(false);
			
			pause = new JLabel("ESC to Pause");
			pause.setBounds(55,30,200,50);
			pause.setFont(font1);
			pause.setForeground(Color.WHITE);
			board.add(pause);
			
			resume = new JLabel("ENTER to Resume");
			resume.setBounds(55,30,400,50);
			resume.setFont(font1);
			resume.setForeground(Color.WHITE);
			board.add(resume);
			resume.setVisible(false);
		    
	        f.add(board);
			f.addKeyListener(board);
			
			f.setVisible(true);
			
			//375,60,275,50
	    }
	    
	    public void start()
		{
			setCoords();
			timer.start();
		}		
	    
	    public void setPiece(boolean[][]arr, Color color)
		{		
			if(nextPiece!=null)
			{
				buffer=nextPiece;
				c = nextColor;
			}
			else
			{
				buffer = arr;
				c=color;
			}		
					
			int tempNum = num;
			num = (int)(Math.random()*7);
			
			if(num==tempNum)
			{
				num = (int)(Math.random()*7);
			}
			nextPiece = piece.getPiece(num);
			nextColor = piece.getColor();
		}

		public void startDropping()
		{
			int fnum = (int)(Math.random()*7);
			setPiece(piece.getPiece(fnum), piece.getColor());
		}
		
	    @Override
	    public void paintComponent(Graphics g) 
	    {
	        super.paintComponent(g);
	        g.setColor(Color.WHITE);
			g.drawRect(60,100,300,59);			
			
			g.setColor(Color.gray);
			for(int i = 0; i<=10;i++)
			{
				g.drawLine(60+(i*30), 160, 60+(i*30), 760);
			}

			for(int i = 2; i<=22;i++)
			{
				g.drawLine(60, 100+(i*30), 360, 100+(i*30));
			}
			
			//Next Piece
			g.setColor(nextColor);
			for(int j = 0;j<nextPiece[0].length;j++)
			{
				for(int i = 0;i<nextPiece.length;i++)
				{
					if(nextPiece[i][j])
					{
						g.fillRect(380+i*30, 200+j*30,30,30);
					}
				}
			}

			//topCheck
			for(int i = 0; i<10;i++)
			{
				if(isOcc[i][1])
				{
					saveScore();
					timer.stop();
					if(bufferY>0)
					{
						bufferY--;
					}
					end=true;
					gameOver.setVisible(true);
					again.setVisible(true);
					break;
				}
			}		

			//Final Pieces
			for(int j = 0; j<22;j++)
			{
				for(int i = 0; i<10;i++)
				{
					if(isOcc[i][j])
					{
						g.setColor(occCol[i][j]);
						g.fillRect(bX[i], bY[j], 29, 29);
					}
				}
			}

			//The Buffer Piece
			if(!end)
			{
				g.setColor(c);				
				for(int j = bufferY;j<buffer[0].length+bufferY;j++)
				{
					for(int i = bufferX;i<buffer.length+bufferX;i++)
					{
						if(buffer[i-bufferX][j-bufferY] && (bufferY+buffer[0].length)<=22)
						{									
							if((bufferY+buffer[0].length)<22 && (buffer[i-bufferX][j-bufferY] && isOcc[i][j+1]))
							{
								contact=true;
								g.fillRect(bX[i], bY[j], 29, 29);
								tempX[0]=bufferX;
								tempX[1]=buffer.length+bufferX;

								tempY[0]=bufferY;
								tempY[1]=buffer[0].length+bufferY;
							}
							else
							{
								g.fillRect(bX[i], bY[j], 29, 29);

								drawX=bX[i];
								drawY = bY[j];

								tempX[0]=bufferX;
								tempX[1]=buffer.length+bufferX;

								tempY[0]=bufferY;
								tempY[1]=buffer[0].length+bufferY;
							}
						}
					}
				}		

				if((bufferY + buffer[0].length)==22 || contact)
				{
					scoreCount+=10;
					score.setText("Score: " + scoreCount);
					timer.setDelay(delay);
					if(contact)
					{
						contact=false;
					}

					for(int j = tempY[0];j<tempY[1];j++)
					{
						for(int i = tempX[0];i<tempX[1];i++)
						{
							//System.out.println((i-bufferX) + " || " + j + "," + bufferY);
							if(buffer[i-bufferX][j-bufferY])
							{
								isOcc[i][j]=true;
								occCol[i][j]=c;
							}
						}
					}	
					bufferY=0;
					bufferX=3;

					if(!end)
					{
						lineClear();
						startDropping();
					}
				}
			}	        
	    }

		public static void readScore()
		{
			try {

				BufferedReader br = new BufferedReader(new FileReader("highscore.txt"));
				highscore = Integer.parseInt(br.readLine());
				highScore.setText("Best: " + highscore);
				br.close();

			} catch (IOException e) {}
		}

		public void saveScore()
		{
			try {

				BufferedReader br = new BufferedReader(new FileReader("highscore.txt"));

				if(scoreCount>Integer.parseInt(br.readLine()))
				{
					PrintWriter pw = new PrintWriter(new FileWriter("highscore.txt"));
					pw.println(scoreCount);
					pw.close();
				}
							
				br.close();
				
			} catch (IOException e) {}
			
		}

		public void setCoords()
		{
			bX=new int[10];
			bY=new int[22];		
			
			for(int i = 0; i<10;i++)
			{
				bX[i] = 61+(i*30);
			}

			for(int i = 0; i<22;i++)
			{
				bY[i] = 101+(i*30);
			}

			for(int i = 0; i<22;i++)
			{
				for(int j= 0; j<10;j++)
				{
					isOcc[j][i]=false;
				}
			}
		}

		
		
		public void lineClear()
		{
			boolean lineCleared[]=new boolean[22];

			for(int i = 0;i<22;i++)
			{
				lineCleared[i]=false;
			}


			for(int i=21;i>=2;i--)
			{
				for(int j = 0;j<10;j++)
				{
					if(isOcc[j][i])
					{
						lineCleared[i]=true;
					}
					else
					{
						lineCleared[i]=false;
						break;
					}
				}
			}
			
			clearLines(lineCleared);
		}				

		public void clearLines(boolean[]lineCleared)
		{
			int counter = 0;
			for(int i=21;i>=2;i--)
			{
				if(lineCleared[i])
				{
					scoreCount+=100;
					linesCount++;
					counter++;
					for(int j = 0;j<10;j++)
					{
						isOcc[j][i]=false;
					}
				}
			}	
			
			switch(counter)
			{
			case 2:
				scoreCount+=100;
			case 3:
				scoreCount+=200;
			case 4:
				scoreCount+=400;
			}		
			
			score.setText("Score: " + scoreCount);
			level.setText("Level " + levelCount + " | " + linesCount + "/10");
			
			if(linesCount>=10)
			{
				linesCount=0;
				levelCount++;
				delay-=100;
				timer.setDelay(delay);
				level.setText("Level " + levelCount + " | " + linesCount + "/10");
			}

			boolean[][]temp=isOcc;
			
			for(int j=21;j>=2;j--)
			{
				if(lineCleared[j])
				{
					linesReposition(temp, j, lineCleared);
					temp = isOcc;
					j++;
				}
			}
			repaint();
		}	
		
		public void linesReposition(boolean[][]temp, int num, boolean[]lineCleared)
		{
			boolean[]tempCleared=lineCleared;
			
			for(int i=num;i>=2;i--)
			{
				for(int j = 0;j<10;j++)
				{
					isOcc[j][i]=temp[j][i-1];
					occCol[j][i]=occCol[j][i-1];
				}
				lineCleared[i]=tempCleared[i-1];
			}		
		}

		public void moveLeft()
		{
			if(bufferX>0)
			{
				bufferX--;
				if(!noContact())
				{
					bufferX++;
				}
			}
			
		}

		public void moveRight()
		{
			if(bufferX+buffer.length<10)
			{
				bufferX++;
				if(!noContact())
				{
					bufferX--;
				}
			}		
		}

		public void moveDown()
		{
			if(bufferY+buffer[0].length<22)
			{
				bufferY++;
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==timer)
			{
				moveDown();
				repaint();
			}
			else if(e.getSource()==again)
			{
				f.dispose();
				CreateGUI();
				start();
				startDropping();
			}
		}

		public void rotateC()
		{
			boolean temp[][]=buffer;
			buffer = new boolean[buffer[0].length][buffer.length];
			for(int j = 0;j<buffer[0].length;j++)
			{
				for(int i = 0;i<buffer.length;i++)
				{
					buffer[i][j]=temp[j][buffer.length-i-1];
				}
			}
			if(!noContact())
			{
				buffer=new boolean[buffer[0].length][buffer.length];
				buffer = temp;
			}
		}

		public void rotateCC()
		{
			boolean temp[][]=buffer;
			buffer = new boolean[buffer[0].length][buffer.length];
			for(int j = 0;j<buffer[0].length;j++)
			{
				for(int i = 0;i<buffer.length;i++)
				{
					buffer[i][j]=temp[buffer[0].length-j-1][i];
				}
			}
			if(!noContact())
			{
				buffer=new boolean[buffer[0].length][buffer.length];
				buffer = temp;
			}
		}

		public boolean noContact()
		{
			for(int j = bufferY;j<buffer[0].length+bufferY;j++)
			{
				for(int i = bufferX;i<buffer.length+bufferX;i++)
				{
					try
					{
						if((bufferY+buffer[0].length)<22 && (buffer[i-bufferX][j-bufferY] && isOcc[i][j+1]))
						{
							return false;
						}
					}
					catch(ArrayIndexOutOfBoundsException a)
					{
						return false;
					}
				}
			}
			return true;
		}

		@Override
		public void keyPressed(KeyEvent k) {
			// TODO Auto-generated method stub
			if(k.getKeyCode()==KeyEvent.VK_LEFT)
			{
				moveLeft();
				repaint();
			}
			else if(k.getKeyCode()==KeyEvent.VK_RIGHT)
			{
				moveRight();
				repaint();
			}
			else if(k.getKeyCode()==KeyEvent.VK_UP)
			{
				rotateC();
				repaint();
			}
			else if(k.getKeyCode()==KeyEvent.VK_Z)
			{
				rotateCC();
				repaint();
			}
			else if(k.getKeyCode()==KeyEvent.VK_SPACE)
			{
				for(int i = bufferY;i<22-buffer[0].length;i++)
				{
					if(noContact())
					{
						bufferY++;
					}
					else
					{
						break;
					}				
				}
				repaint();
			}
			else if(k.getKeyCode()==KeyEvent.VK_ESCAPE)
			{
				pause.setVisible(false);
				resume.setVisible(true);
				timer.stop();
			}
			else if(k.getKeyCode()==KeyEvent.VK_ENTER)
			{
				pause.setVisible(true);
				resume.setVisible(false);
				timer.start();
			}
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

	
	


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Main extends JFrame implements MouseListener, MouseMotionListener, KeyListener, ActionListener{

	int order = 0, widthOrder = 0, diff=0, clickCount=0;	
	boolean compTurn = false, gameEnd = false, notComp=false, click = true;

	MoveFind compMove = new MoveFind();
	Result results = new Result();
	Board board = new Board();
	Splash splash = new Splash();
	BoardSize size = new BoardSize();

	int[]currBox=new int[2];
	boolean boxOcc[][];

	public static void main(String[]args)
	{
		new Main();
	}

	public void setup(int order, int num)
	{
		boxOcc = new boolean[order][order];
		for(int i = 0; i < order; i++)
		{
			for(int j = 0; j < order; j++)
			{
				boxOcc[i][j]=false;
			}
		}	
		currBox[0]=order;
		currBox[1]=order;
		board.setOrder(order, widthOrder);
		compMove.setOrder(order);
	}

	public Main()
	{
		splash.setVisible(true);			

		board.addMouseMotionListener(this);
		board.addMouseListener(this);
		board.addKeyListener(this);
		board.menu.addActionListener(this);
		board.playAgain.addActionListener(this);

		results.addKeyListener(this);

		splash.easy.addActionListener(this);
		splash.med.addActionListener(this);
		splash.hard.addActionListener(this);
		splash.imp.addActionListener(this);

		size.three.addActionListener(this);
		size.four.addActionListener(this);
		size.five.addActionListener(this);		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent m) {
		// TODO Auto-generated method stub
		if(!gameEnd)
		{
			click = true;
			for(int j = 0;j<order;j++)
			{
				for(int i = 0;i<order;i++)
				{
					if(m.getX()>(35+widthOrder*i) && m.getX()<(35+widthOrder*(i+1)) && m.getY()>(75+widthOrder*j) && m.getY()<(75+widthOrder*(j+1)))
					{
						currBox[0] = i;
						currBox[1] = j;
						if(!boxOcc[i][j])
						{
							boxClick(currBox);
							clickCount++;
							boxOcc[i][j]=true;
							compMove.pBox[i][j]=true;

							if(clickCount==Math.ceil(Math.pow(order, 2)/2))
							{
								if(compMove.win(compMove.pBox))
								{
									compMove.endP=true;
									gameEnd=true;
								}
								else
								{
									if(notComp || order%2==0)
									{
										gameEnd = compMove.update(board.click, board.compClick, boxOcc, diff, compTurn);
										boxOcc[compMove.compMove[0]][compMove.compMove[1]]=true;
										board.clickComp(compMove.compMove);
									}
									end();
								}
							}
							else
							{							
								if(compMove.win(compMove.pBox))
								{
									compMove.endP=true;
									gameEnd=true;
								}
								else
								{
									gameEnd = compMove.update(board.click, board.compClick, boxOcc, diff, compTurn);
									boxOcc[compMove.compMove[0]][compMove.compMove[1]]=true;
									board.clickComp(compMove.compMove);
								}
							}
						}
					}
				}
			}
		}

		if(gameEnd && click)
		{
			board.playAgain.setVisible(true);
			if(compMove.endP)
			{
				board.winnerP.setVisible(true);
				board.pScore++;				
				board.scoreP.setText(board.pScore+"");
				if(board.pScore==3||board.cScore==3)
				{
					results.setScore("player", board.cScore, board.pScore);
					results.setVisible(true);
					board.playAgain.setVisible(false);
				}				
			}
			else if(compMove.endC)
			{				
				board.winnerC.setVisible(true);
				board.cScore++;
				if(board.pScore==3||board.cScore==3)
				{
					results.setScore("computer", board.cScore, board.pScore);
					results.setVisible(true);
					board.playAgain.setVisible(false);
				}
				board.scoreC.setText(board.cScore+"");
			}
			click=false;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent m) {
		// TODO Auto-generated method stub
		if(!gameEnd)
		{
			for(int j = 0;j<order;j++)
			{
				for(int i = 0;i<order;i++)
				{
					if(m.getX()>(35+widthOrder*i) && m.getX()<(35+widthOrder*(i+1)) && m.getY()>(75+widthOrder*j) && m.getY()<(75+widthOrder*(j+1)))
					{
						boxHighlight(i,j);		
					}
				}
			}
		}
	}

	public void boxHighlight(int x, int y)
	{
		if(!boxOcc[x][y])
		{
			if(currBox[0]!=x || currBox[1]!=y)
			{
				currBox[0] = x;
				currBox[1] = y;
				board.highlight(currBox);
			}
		}	
	}

	public void boxClick(int[]arr)
	{
		if(!boxOcc[arr[0]][arr[1]])
		{
			board.click(arr);
		}
	}

	public void end()
	{
		if(!compMove.endC)
		{
			if(!compMove.endP)
			{
				board.draw.setVisible(true);
				board.winnerC.setVisible(false);
				board.winnerP.setVisible(false);
				board.playAgain.setVisible(true);
				board.pScore++;
				board.cScore++;

				if(board.pScore==3 && board.cScore==3)
				{
					results.setScore("draw", board.cScore, board.pScore);
					results.setVisible(true);
					board.playAgain.setVisible(false);
				}
				else if(board.pScore==3)
				{
					results.setScore("player", board.cScore, board.pScore);
					results.setVisible(true);
					board.playAgain.setVisible(false);
				}
				else if(board.cScore==3)
				{
					results.setScore("computer", board.cScore, board.pScore);
					results.setVisible(true);
					board.playAgain.setVisible(false);
				}				
				board.scoreP.setText(board.pScore+"");
				board.scoreC.setText(board.cScore+"");
			}
		}
	}

	public void reset()
	{
		gameEnd=false;
		compMove.reset();
		board.reset();
		clickCount=0;
		for(int i = 0; i < order; i++)
		{
			for(int j = 0; j < order; j++)
			{
				boxOcc[i][j]=false;
			}
		}

		if(!notComp)
		{
			gameEnd = compMove.update(board.click, board.compClick, boxOcc, diff, compTurn);
			compTurn=false;
			boxOcc[compMove.compMove[0]][compMove.compMove[1]]=true;
			board.clickComp(compMove.compMove);
			if(order%2==1)
			{
				clickCount++;
			}
			notComp=true;
		}
		else if(notComp)
		{
			notComp=false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			results.setVisible(false);
			board.setVisible(false);
			splash.setVisible(true);
			board.winnerC.setVisible(false);
			board.winnerP.setVisible(false);
			board.draw.setVisible(false);
			board.playAgain.setVisible(false);
			reset();
			board.cScore=0;
			board.pScore=0;
			board.scoreC.setText("0");
			board.scoreP.setText("0");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==splash.easy)
		{
			diff=1;			
			splash.setVisible(false);
			size.setVisible(true);
		}
		else if(e.getSource()==splash.med)
		{			
			diff=2;
			splash.setVisible(false);
			size.setVisible(true);
		}
		else if(e.getSource()==splash.hard)
		{
			diff=3;
			splash.setVisible(false);
			size.setVisible(true);
		}
		else if(e.getSource()==splash.imp)
		{
			diff=6;
			splash.setVisible(false);
			size.setVisible(true);
		}
		else if(e.getSource()==size.three)
		{
			order = 3;
			widthOrder=160;
			setup(order, widthOrder);
			size.setVisible(false);
			board.setVisible(true);
		}
		else if(e.getSource()==size.four)
		{
			order = 4;
			widthOrder=120;
			setup(order, widthOrder);
			size.setVisible(false);
			board.setVisible(true);
		}
		else if(e.getSource()==size.five)
		{
			order = 5;
			widthOrder=96;
			setup(order, widthOrder);
			size.setVisible(false);
			board.setVisible(true);
		}
		else if(e.getSource()==board.menu)
		{
			board.setVisible(false);
			splash.setVisible(true);
			board.winnerC.setVisible(false);
			board.winnerP.setVisible(false);
			board.draw.setVisible(false);
			board.playAgain.setVisible(false);
			reset();
			board.cScore=0;
			board.pScore=0;
			board.scoreC.setText("0");
			board.scoreP.setText("0");
		}
		else if(e.getSource()==board.playAgain)
		{	
			board.winnerC.setVisible(false);
			board.winnerP.setVisible(false);
			board.draw.setVisible(false);
			board.playAgain.setVisible(false);
			reset();
		}
	}
}

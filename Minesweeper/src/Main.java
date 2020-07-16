import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Main implements ActionListener, MouseListener, MouseMotionListener{

	int order = 15, widthOrder = 30;	
	int bombNum = 0;

	boolean compTurn = false, gameEnd = false, notComp=false, click = true;
	int[]currBox=new int[2];
	Numbers nums=new Numbers();
	boolean end=false;
	Board board;
	boolean mines[][];

	public Main()
	{
		board = Board.createGUI();
		setup();
		nums.calculate(mines, order);
		board.setOrder(order, 30, mines, nums.nums, bombNum);
		board.addMouseListener(this);
		board.addMouseMotionListener(this);
		board.again.addActionListener(this);
		board.startB.addActionListener(this);
		board.b15.addActionListener(this);
		board.b20.addActionListener(this);
		board.b25.addActionListener(this);
		board.b30.addActionListener(this);
		currBox[0]=order;
		currBox[1]=order;
	}

	public void setup()
	{
		mines = new boolean[order][order];
		for(int i = 0; i < order; i++)
		{
			for(int j = 0; j < order; j++)
			{
				mines[i][j]=false;
			}
		}	


		for(int i = 0;i<bombNum;i++)
		{
			int numr = (int)(Math.random()*(Math.pow(order, 2)-1))+1;

			if(numr%order != 0)
			{
				if(!mines[(numr%order)-1][(numr-(numr%order))/order])
				{
					mines[numr%order-1][(numr-(numr%order))/order]=true;
				}
				else
				{
					i--;
				}
			}
			else
			{
				if(!mines[order-1][(numr-(numr%order))/order])
				{
					mines[order-1][(numr-(numr%order))/order]=true;
				}
				else
				{
					i--;
				}
			}
		}
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

		if(!end)
		{
			for(int j = 0;j<order;j++)
			{
				for(int i = 0;i<order;i++)
				{
					if(m.getX()>(50+widthOrder*i) && m.getX()<(50+widthOrder*(i+1)) && m.getY()>(100+widthOrder*j) && m.getY()<(100+widthOrder*(j+1)))
					{
						if(m.getButton()==MouseEvent.BUTTON3)
						{
							board.flag(i,j);
						}
						else
						{
							if(!mines[i][j])
							{
								if(board.update(i,j))
								{
									end=true;
								}
							}
							else
							{
								if(board.start)
								{
									board.result.setText("YOU LOSE!");
									board.reveal();
									end=true;
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent m) {}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==board.again)
		{
			board.timer.stop();
			board.start=false;
			board.f.dispose();
			new Main();
		}
		else if(e.getSource()==board.startB)
		{
			if(!board.b15.isSelected()&&!board.b20.isSelected()&&!board.b25.isSelected()&&!board.b30.isSelected())
			{
				board.startL.setText("Choose Mine Count!");
			}
			else
			{
				if(board.b15.isSelected())
				{
					bombNum=15;
				}
				else if(board.b20.isSelected())
				{
					bombNum=20;
				}
				else if(board.b25.isSelected())
				{
					bombNum=25;
				}
				else if(board.b30.isSelected())
				{
					bombNum=30;
				}

				setup();
				nums.calculate(mines, order);
				board.setOrder(order, 30, mines, nums.nums, bombNum);			

				board.start=true;
				board.repaint();
				board.timer.start();
				board.startL.setText("Click to Restart");
				board.startB.setVisible(false);
				board.again.setVisible(true);
			}
		}
		else if(e.getSource()==board.b15)
		{
			board.b20.setSelected(false);
			board.b25.setSelected(false);
			board.b30.setSelected(false);
		}
		else if(e.getSource()==board.b20)
		{
			board.b15.setSelected(false);
			board.b25.setSelected(false);
			board.b30.setSelected(false);
		}
		else if(e.getSource()==board.b25)
		{
			board.b20.setSelected(false);
			board.b15.setSelected(false);
			board.b30.setSelected(false);
		}
		else if(e.getSource()==board.b30)
		{
			board.b20.setSelected(false);
			board.b25.setSelected(false);
			board.b15.setSelected(false);
		}
	}

}

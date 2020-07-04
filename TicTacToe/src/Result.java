import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;

public class Result extends JFrame{

	Container frame;
	JLabel lab, again, cScore, pScore;
	
	public Result()
	{
		super("TicTacToe");
		setSize(555,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setResizable(false);
		setLocationRelativeTo(null);
		
		Font font1 = new Font ("Comic Sans MS", Font.PLAIN, 30);

		frame = getContentPane();
		frame.setLayout(null);
		frame.setBackground(Color.black);
		
		lab = new JLabel("");
		lab.setBounds(200,400,200,50);
		lab.setFont(font1);
		lab.setForeground(Color.WHITE);
		frame.add(lab);
		
		cScore = new JLabel("");
		cScore.setBounds(355,34,55,55);
		cScore.setFont(font1);
		cScore.setForeground(Color.WHITE);
		frame.add(cScore);
		
		pScore = new JLabel("");
		pScore.setBounds(175,34,55,55);
		pScore.setFont(font1);
		pScore.setForeground(Color.WHITE);
		frame.add(pScore);
		
		again = new JLabel("Press Enter For The Main Menu");
		again.setBounds(60,180,600,50);
		again.setFont(font1);
		again.setForeground(Color.WHITE);
		frame.add(again);
	}
	
	public void paint(Graphics g)
	{
		super.paintComponents(g);
		g.setColor(Color.RED);
		g.fillOval(110,60,55,55);
		
		g.setColor(Color.BLUE);
		g.fillOval(390,60,55,55);
	}
	
	public void setScore(String winner, int scoreC, int scoreP)
	{
		pScore.setText(scoreP+"");
		cScore.setText(scoreC+"");
		if(winner.equals("computer"))
		{
			lab.setText("The Computer Beat You!");			
			lab.setBounds(105,110,500,50);
		}
		else if(winner.equals("player"))
		{
			lab.setText("You Beat The Computer!");
			lab.setBounds(105,110,500,50);
		}
		else if(winner.equals("draw"))
		{
			lab.setText("The Game Was A Draw!");
			lab.setBounds(115,110,500,50);
		}
	}
}

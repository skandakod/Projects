import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Splash extends JFrame{

	Container frame;
	JButton start;
	JLabel welcome;
	
	public Splash()
	{
		super("Tetris");
		setSize(600,850);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);	
		frame = getContentPane();
		frame.setLayout(null);
		frame.setBackground(Color.black);
		
		Font font1 = new Font ("Comic Sans MS", Font.PLAIN, 30);
				
		start = new JButton("Start");
		start.setBounds(100,400,400,50);
		start.setFont(font1);
		start.setForeground(Color.white);
		start.setBackground(Color.black);
		start.setFocusPainted(false);
		frame.add(start);
	}
}

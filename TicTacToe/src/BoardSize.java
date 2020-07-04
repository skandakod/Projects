import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BoardSize extends JFrame{
	
	Container frame;
	JButton three, four, five;
	JLabel welcome, chooseDiff, sig;
	
	public BoardSize()
	{
		super("TicTacToe");
		setSize(555,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setResizable(false);
		setLocationRelativeTo(null);
		
		Font font1 = new Font ("Comic Sans MS", Font.PLAIN, 30);
		
		frame = getContentPane();
		frame.setLayout(null);
		frame.setBackground(Color.black);
		
		welcome = new JLabel("Welcome!");
		welcome.setBounds(205,10,600,50);
		welcome.setFont(font1);
		welcome.setForeground(Color.WHITE);
		frame.add(welcome);
		
		chooseDiff = new JLabel("Choose Your Board Size");
		chooseDiff.setBounds(110,70,600,50);
		chooseDiff.setFont(font1);
		chooseDiff.setForeground(Color.WHITE);
		frame.add(chooseDiff);
		
		three = new JButton("3x3 Grid");
		three.setBounds(148,150,255,75);
		three.setForeground(Color.BLACK);
		three.setBackground(Color.pink);
		three.setFont(font1);
		three.setFocusPainted(false);
		frame.add(three);
		
		four = new JButton("4x4 Grid");
		four.setBounds(148,250,255,75);
		four.setForeground(Color.BLACK);
		four.setBackground(Color.CYAN);
		four.setFont(font1);
		four.setFocusPainted(false);
		frame.add(four);
		
		five = new JButton("5x5 Grid");
		five.setBounds(148,350,255,75);
		five.setForeground(Color.BLACK);
		five.setBackground(Color.ORANGE);
		five.setFont(font1);
		five.setFocusPainted(false);
		frame.add(five);
		
		sig = new JLabel("Made By: SKANDA KODUNAD");
		sig.setBounds(65,565,600,50);
		sig.setFont(font1);
		sig.setForeground(Color.WHITE);
		frame.add(sig);		
	}
}

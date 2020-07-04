import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Splash extends JFrame{
	
	Container frame;
	JButton easy, med, hard, imp;
	JLabel welcome, chooseDiff, sig;
	
	public Splash()
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
		
		chooseDiff = new JLabel("Choose Your Level");
		chooseDiff.setBounds(148,70,600,50);
		chooseDiff.setFont(font1);
		chooseDiff.setForeground(Color.WHITE);
		frame.add(chooseDiff);
		
		easy = new JButton("EASY");
		easy.setBounds(148,150,255,75);
		easy.setForeground(Color.BLACK);
		easy.setBackground(Color.pink);
		easy.setFont(font1);
		easy.setFocusPainted(false);
		frame.add(easy);
		
		med = new JButton("MEDIUM");
		med.setBounds(148,250,255,75);
		med.setForeground(Color.BLACK);
		med.setBackground(Color.CYAN);
		med.setFont(font1);
		med.setFocusPainted(false);
		frame.add(med);
		
		hard = new JButton("HARD");
		hard.setBounds(148,350,255,75);
		hard.setForeground(Color.BLACK);
		hard.setBackground(Color.ORANGE);
		hard.setFont(font1);
		hard.setFocusPainted(false);
		frame.add(hard);
		
		imp = new JButton("IMPOSSIBLE");
		imp.setBounds(148,450,255,75);
		imp.setForeground(Color.BLACK);
		imp.setBackground(Color.RED);
		imp.setFont(font1);
		imp.setFocusPainted(false);
		frame.add(imp);
		
		sig = new JLabel("Made By: SKANDA KODUNAD");
		sig.setBounds(65,565,600,50);
		sig.setFont(font1);
		sig.setForeground(Color.WHITE);
		frame.add(sig);		
	}
}

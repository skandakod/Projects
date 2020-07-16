import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main implements ActionListener {

	Splash splash = new Splash();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}
	
	public Main()
	{
		splash.setVisible(true);
		splash.start.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==splash.start)
		{
			Board.CreateGUI();
			splash.setVisible(false);
		}
	}
}

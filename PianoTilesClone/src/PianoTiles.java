import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;

public class PianoTiles extends JFrame implements KeyListener
{
    int[] tiles = new int [100];
    int rows[] = {26, 176, 326, 476};
    int width = 120, height = 150;
    int cols[] = {0, 120, 240, 360};
    int count = 0, startUp = 1, tileNumber = 0, first = 1;
    long startTime = 0, endTime = 0, elapsed = 0;
    double deltaTime = 0;
    long time = 0;
    JLabel timeDisp, timer, mistakes, missCnt, accuracy, accN, again, letters;
    Container frame;
    int milliseconds = 0, seconds = 0, startNow = 1, missCnter = 0;
    boolean state = true;
    int restart = 0,middle=0;
    double acc = 0;

    public PianoTiles ()
    {
        super ("PIANO TILES KNOCKOFF");
        setSize (800, 700);
        setResizable (false);

        frame = getContentPane ();
        frame.setLayout (null);

        frame.setBackground (Color.WHITE);

        Font font1 = new Font ("Comic Sans MS", Font.PLAIN, 36);
        Font font2 = new Font ("Comic Sans MS", Font.BOLD, 39);

        timeDisp = new JLabel ("00.000 s");
        timeDisp.setBounds (565, 110, 150, 33);
        timeDisp.setFont (font1);
        frame.add (timeDisp);

        timer = new JLabel ("Timer:");
        timer.setBounds (571, 50, 200, 50);
        timer.setFont (font2);
        frame.add (timer);

        letters = new JLabel ("  D      F     J      K");
        letters.setBounds (10, 610, 450, 50);
        letters.setFont (font2);
        frame.add (letters);

        mistakes = new JLabel ("Mistypes:");
        mistakes.setBounds (557, 200, 200, 50);
        mistakes.setFont (font1);
        frame.add (mistakes);

        missCnt = new JLabel ("0");
        missCnt.setBounds (620, 245, 200, 50);
        missCnt.setFont (font1);
        missCnt.setForeground (Color.red);
        frame.add (missCnt);

        accuracy = new JLabel ("Accuracy:");
        accuracy.setBounds (557, 320, 200, 50);
        accuracy.setFont (font1);
        frame.add (accuracy);

        accN = new JLabel ("100%");
        accN.setBounds (597, 365, 200, 50);
        accN.setFont (font1);
        accN.setForeground (Color.green);
        frame.add (accN);

        again = new JLabel ("<html>Press space to<br/restart or play again</html>");
        again.setBounds (517, 425, 300, 200);
        again.setFont (font1);
        frame.add (again);

        addKeyListener (this);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo (null);
        setVisible (true);
    }


    public void paint (Graphics g)  //top bar takes up 26 pixels
    {
        super.paintComponents (g);
        g.drawLine (3, 0, 3, 626);
        g.drawLine (120, 0, 120, 626);
        g.drawLine (240, 0, 240, 626);
        g.drawLine (360, 0, 360, 626);
        g.drawLine (480, 0, 480, 626);

        g.drawLine (0, 26, 480, 26);
        g.drawLine (0, 176, 480, 176);
        g.drawLine (0, 326, 480, 326);
        g.drawLine (0, 476, 480, 476);
        g.drawLine (0, 626, 480, 626);


        if (startUp == 1)
        {
            setBlack ();
            state = true;
            g.setColor (Color.GRAY);
            g.fillRect (cols [tiles [0] - 1] + 1, rows [3] + 1, width - 1, height - 1);
            g.fillRect (cols [tiles [1] - 1] + 1, rows [2] + 1, width - 1, height - 1);
            g.fillRect (cols [tiles [2] - 1] + 1, rows [1] + 1, width - 1, height - 1);
            g.fillRect (cols [tiles [3] - 1] + 1, rows [0] + 1, width - 1, height - 1);
            startUp = 0;
        }
        else
        {
            if (tileNumber < 97)
            {
                g.setColor (Color.GRAY);
                g.fillRect (cols [tiles [tileNumber] - 1] + 1, rows [3] + 1, width - 1, height - 1);
                g.fillRect (cols [tiles [tileNumber + 1] - 1] + 1, rows [2] + 1, width - 1, height - 1);
                g.fillRect (cols [tiles [tileNumber + 2] - 1] + 1, rows [1] + 1, width - 1, height - 1);
                g.fillRect (cols [tiles [tileNumber + 3] - 1] + 1, rows [0] + 1, width - 1, height - 1);
            }
            else if (tileNumber == 97)
            {
                g.setColor (Color.GRAY);
                g.fillRect (cols [tiles [tileNumber] - 1] + 1, rows [3] + 1, width - 1, height - 1);
                g.fillRect (cols [tiles [tileNumber + 1] - 1] + 1, rows [2] + 1, width - 1, height - 1);
                g.fillRect (cols [tiles [tileNumber + 2] - 1] + 1, rows [1] + 1, width - 1, height - 1);
            }
            else if (tileNumber == 98)
            {
                g.setColor (Color.GRAY);
                g.fillRect (cols [tiles [tileNumber] - 1] + 1, rows [3] + 1, width - 1, height - 1);
                g.fillRect (cols [tiles [tileNumber + 1] - 1] + 1, rows [2] + 1, width - 1, height - 1);
            }
            else if (tileNumber == 99)
            {
                g.setColor (Color.GRAY);
                g.fillRect (cols [tiles [tileNumber] - 1] + 1, rows [3] + 1, width - 1, height - 1);
            }
            else
            {
                state = false;
            }
        }
    }


    //using d,f,j,k
    public void keyPressed (KeyEvent e)
    {
        if (count < 100)
        {
            if (startNow == 1 && ((e.getKeyCode () == KeyEvent.VK_D && tiles [count] == 1) || (e.getKeyCode () == KeyEvent.VK_F && tiles [count] == 2) || (e.getKeyCode () == KeyEvent.VK_J && tiles [count] == 3) || (e.getKeyCode () == KeyEvent.VK_K && tiles [count] == 4)))
            {
                startNow = 0;
                Thread t = new Thread ()
                {
                    public void run ()
                    {
                        for (;;)
                        {
                            if (state == true)
                            {
                                try
                                {
                                    sleep (1);

                                    endTime = System.currentTimeMillis ();
                                    elapsed = endTime - startTime;
                                    deltaTime = elapsed / 1000.0;

                                    timeDisp.setText ("" + deltaTime + " s");
                                }
                                catch (Exception a)
                                {
                                }
                            }
                            else
                            {
                                if (middle == 1)
                                {
                                    timeDisp.setText ("00.000 s");
                                    middle = 0;
                                }
                                break;
                            }
                        }
                    }
                }
                ;
                t.start ();
            }
            if (e.getKeyCode () == KeyEvent.VK_D && tiles [count] == 1)
            {
                if (first == 1)
                {
                    startTime = System.currentTimeMillis ();
                    first = 0;
                }
                tileNumber = count + 1;
                count++;
                NumberFormat nm = NumberFormat.getInstance ();
                nm.setMaximumFractionDigits (2);
                acc = ((double) (tileNumber - missCnter) / (double) tileNumber) * 100.00;
                if (acc >= 0)
                {
                    accN.setText (nm.format (acc) + "%");
                }
                else
                {
                    accN.setText ("0%");
                }
                repaint ();
            }
            else if (e.getKeyCode () == KeyEvent.VK_F && tiles [count] == 2)
            {
                if (first == 1)
                {
                    startTime = System.currentTimeMillis ();
                    first = 0;
                }
                tileNumber = count + 1;
                count++;
                NumberFormat nm = NumberFormat.getInstance ();
                nm.setMaximumFractionDigits (2);
                acc = ((double) (tileNumber - missCnter) / (double) tileNumber) * 100.00;
                if (acc >= 0)
                {
                    accN.setText (nm.format (acc) + "%");
                }
                else
                {
                    accN.setText ("0%");
                }
                repaint ();
            }
            else if (e.getKeyCode () == KeyEvent.VK_J && tiles [count] == 3)
            {
                if (first == 1)
                {
                    startTime = System.currentTimeMillis ();
                    first = 0;
                }
                tileNumber = count + 1;
                count++;
                NumberFormat nm = NumberFormat.getInstance ();
                nm.setMaximumFractionDigits (2);
                acc = ((double) (tileNumber - missCnter) / (double) tileNumber) * 100.00;
                if (acc >= 0)
                {
                    accN.setText (nm.format (acc) + "%");
                }
                else
                {
                    accN.setText ("0%");
                }
                repaint ();
            }
            else if (e.getKeyCode () == KeyEvent.VK_K && tiles [count] == 4)
            {
                if (first == 1)
                {
                    startTime = System.currentTimeMillis ();
                    first = 0;
                }
                tileNumber = count + 1;
                count++;
                NumberFormat nm = NumberFormat.getInstance ();
                nm.setMaximumFractionDigits (2);
                acc = ((double) (tileNumber - missCnter) / (double) tileNumber) * 100.00;
                if (acc >= 0)
                {
                    accN.setText (nm.format (acc) + "%");
                }
                else
                {
                    accN.setText ("0%");
                }
                repaint ();
            }
            else if (e.getKeyCode () == KeyEvent.VK_SPACE)
            {
                middle = 1;
                state = false;
                startUp = 1;
                accN.setText ("100%");
                missCnt.setText ("0");
                missCnter =0;
                timeDisp.setText ("00.000 s");
                tileNumber = 0;
                startNow = 1;
                first = 1;
                count = 0;
                repaint ();
            }
            else
            {
                if (first != 1)
                {
                    missCnter++;
                    missCnt.setText ("" + missCnter);
                    NumberFormat nm = NumberFormat.getInstance ();
                    nm.setMaximumFractionDigits (2);
                    acc = ((double) (tileNumber - missCnter) / (double) tileNumber) * 100.00;
                    if (acc >= 0)
                    {
                        accN.setText (nm.format (acc) + "%");
                    }
                    else
                    {
                        accN.setText ("0%");
                    }
                }
            }
        }
        else
        {
            if (e.getKeyCode () == KeyEvent.VK_SPACE)
            {
                startUp = 1;
                accN.setText ("100%");
                missCnt.setText ("0");
                timeDisp.setText ("00.000 s");
                tileNumber = 0;
                missCnter =0;
                startNow = 1;
                first = 1;
                count = 0;
                repaint ();
                state = true;
            }
        }
    }


    public void keyTyped (KeyEvent e)
    {

    }


    public void keyReleased (KeyEvent e)
    {

    }


    public void setBlack ()
    {
        for (int i = 0 ; i < 100 ; i++)
        {
            tiles [i] = (int) (Math.random () * 4) + 1;
        }
    }


    public static void main (String[] args)
    {
        new PianoTiles ();
    } // main method
} // PianoTiles class



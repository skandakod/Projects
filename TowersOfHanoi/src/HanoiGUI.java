/*
Skanda Kodunad
June 4th, 2018
The HanoiGUI class is responsible for taking the logic from the Hanoi class and visually represting it in a colorful GUI.
*/

//package imports
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;

public class HanoiGUI extends JFrame implements ActionListener
{
    //components and variable declaration
    private JButton nextMove, prevMove, go, skip, click;
    private Container frame;
    private JLabel counter, welcome, enterNum, win, optimalMoves, enterSpeed;
    private JTextField inputNum, inputSpeed;
    private Hanoi hanoi;
    private int numOfDiscs = 0, moveCount = 0, temp, start = 0, mid = 1, end = 2, h = 0, skipper = 0, speedCnt = 0, speedAni = 0;
    private Color colors[] = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, new Color (50, 122, 11), Color.BLACK, Color.CYAN, Color.ORANGE, Color.MAGENTA, Color.GRAY};
    private int[] discs = new int [3], startingP, x;
    private int[] [] arr1, arr2, arr3;

    public HanoiGUI ()  //the HanoiGUI constructor is responsible for setting up the window for first time use
    {
        super ("Towers of Hanoi!"); //title of the window

        frame = getContentPane ();
        frame.setLayout (null);

        //the three different fonts used in the program
        Font font1 = new Font ("Comic Sans MS", Font.BOLD, 24);
        Font font3 = new Font ("Comic Sans MS", Font.BOLD, 18);
        Font font2 = new Font ("Comic Sans MS", Font.PLAIN, 12);

        //creation of the various components used on screen
        nextMove = new JButton ("NEXT MOVE");
        nextMove.setBounds (500, 600, 150, 50);
        nextMove.addActionListener (this);
        nextMove.setFont (font2);
        frame.add (nextMove);

        prevMove = new JButton ("PREVIOUS MOVE");
        prevMove.setBounds (50, 600, 150, 50);
        prevMove.addActionListener (this);
        prevMove.setFont (font2);
        frame.add (prevMove);

        counter = new JLabel ("0");
        counter.setBounds (335, 600, 70, 50);
        counter.setFont (font1);
        frame.add (counter);

        win = new JLabel ("");
        win.setBounds (295, 550, 150, 50);
        win.setFont (font1);
        frame.add (win);

        optimalMoves = new JLabel ("");
        optimalMoves.setBounds (50, 540, 500, 40);
        optimalMoves.setFont (font1);
        frame.add (optimalMoves);

        welcome = new JLabel ("Welcome to the Towers of Hanoi Tutorial!");
        welcome.setBounds (100, 50, 500, 50);
        welcome.setFont (font1);
        frame.add (welcome);

        enterNum = new JLabel ("Number of Discs: (3-10)");
        enterNum.setBounds (150, 100, 600, 50);
        enterNum.setFont (font3);
        frame.add (enterNum);

        inputNum = new JTextField ();
        inputNum.setBounds (390, 110, 50, 30);
        inputNum.setFont (font3);
        frame.add (inputNum);

        go = new JButton ("SET / RESET");
        go.setBounds (465, 110, 160, 30);
        go.addActionListener (this);
        go.setFont (font3);
        frame.add (go);

        skip = new JButton ("AUTO");
        skip.setBounds (70, 170, 90, 30);
        skip.addActionListener (this);
        skip.setFont (font3);
        skip.setVisible (false);
        frame.add (skip);

        click = new JButton ("Click this button to enable auto feature");
        click.setBounds (100, 170, 500, 40);
        click.addActionListener (this);
        click.setFont (font3);
        frame.add (click);

        inputSpeed = new JTextField ();
        inputSpeed.setBounds (170, 170, 90, 30);
        inputSpeed.setFont (font3);
        inputSpeed.setVisible (false);
        frame.add (inputSpeed);

        enterSpeed = new JLabel ("Enter delay between moves (In milliseconds)");
        enterSpeed.setBounds (270, 170, 500, 30);
        enterSpeed.setFont (font3);
        enterSpeed.setVisible (false);
        frame.add (enterSpeed);

        startup (3); //sets up the discs for the first time (default at 3 discs)

        //frame setup
        setSize (700, 700);
        setResizable (false);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo (null);
        frame.setBackground (Color.WHITE);
        setVisible (true);
    }


    //the startup method is responsible for setting up the various components used in the program for a new disc puzzle
    public void startup (int num)//takes in the number of discs
    {
        numOfDiscs = num;
        moveCount = 0;
        counter.setText (moveCount + "");//counter label is set
        temp = numOfDiscs - 1;
        
        //starting positions and lengths of the discs
        startingP = new int [numOfDiscs + 1];
        x = new int [numOfDiscs + 1];
        
        discs [0] = numOfDiscs;
        hanoi = new Hanoi (numOfDiscs);//creates a Hanoi object
        setValues ();//based on the number of discs, the lengths and x coordinates are set
        hanoi.moveDisc (discs, start, mid, end);//the Hanoi class runs based on the number of discs
        
        optimalMoves.setText ("Optimal Moves: " + hanoi.getOpt ());
        
        //the positions of every disc after each move is retrieved
        arr1 = hanoi.getFinal1 ();
        arr2 = hanoi.getFinal2 ();
        arr3 = hanoi.getFinal3 ();
        win.setText ("");
        
        //information for the auto feature
        h = hanoi.getOpt () - 1;
        skipper = 1;
        
        //updates the screen
        repaint ();
    }


    //method that sets the values of the discs initially
    public void setValues ()
    {
        for (int i = 0 ; i < numOfDiscs ; i++)
        {
            startingP [i + 1] = 120 - (7 * i);
            x [i + 1] = 60 + (14 * i);
        }
    }


    //method that handles button presses in the program
    public void actionPerformed (ActionEvent e)
    {
        try
        {
            //updates the number of discs initially shown on screen, so startup is called again with the new number
            if (e.getSource () == go)
            {
                if (Integer.parseInt (inputNum.getText ()) > 10 || Integer.parseInt (inputNum.getText ()) < 3)
                {
                    return;
                }
                else
                {
                    startup (Integer.parseInt (inputNum.getText ()));
                }
            }
            else if (e.getSource () == click)//enables the auto feature
            {
                skip.setVisible (true);
                inputSpeed.setVisible (true);
                enterSpeed.setVisible (true);
                click.setVisible (false);
            }
            else if (e.getSource () == skip)//skips through all the moves at a user specified pace
            {
                h = moveCount;
                skipper = 1;
                speedAni = Integer.parseInt (inputSpeed.getText ());
                repaint ();
            }
            else if (e.getSource () == nextMove)//updates the screen to show the next move
            {
                if (moveCount >= hanoi.getOpt ())
                {
                    moveCount = hanoi.getOpt ();
                    return;
                }
                else
                {
                    win.setText ("");
                    counter.setText (moveCount + "");
                    repaint ();
                }
            }
            else if (e.getSource () == prevMove)//updates the screen to show the previous move
            {
                moveCount -= 2;
                if (moveCount < -1)
                {
                    moveCount = 0;
                    return;
                }
                else
                {
                    win.setText ("");
                    counter.setText (moveCount + "");
                    repaint ();
                }
            }
        }
        catch (Exception a)//catches any sort of exception that may occur when pressing buttons
        {
            return;
        }
    }


    //the paint method updates the screen after each move
    public void paint (Graphics g)
    {
        //the skipper variable indicates that the auto button was clicked
        if (skipper == 1)
        {
            if (h != moveCount)
            {
                moveCount--;
            }
        }
        
        //if the auto button was clicked, this loop repaints the screen move after move starting from the move before the user clicked
        //the auto button
        //otherwise, the variable h is set to one less than the optimal moves, causing this loop to run just once
        for (h = h ; h < hanoi.getOpt () ; h++)
        {
            moveCount++;//the moveCount is increased and the label is updated
            counter.setText (moveCount + "");

            if (moveCount == hanoi.getOpt ())//if the moveCount is equal to the optimal moves, the puzzle is solved
            {
                win.setText ("SOLVED!");
            }

            super.paintComponents (g);
            //draws the main four lines of the screen (three posts and a base)
            g.drawLine (150, 250, 150, 549);
            g.drawLine (350, 250, 350, 549);
            g.drawLine (550, 250, 550, 549);
            g.drawLine (0, 550, 700, 550);

            //the following three for loops are responsible for drawing the discs after each move.
            //the loops use the three arrays from Hanoi that stored the number of discs on each post after every move.
            
            //this first loop is responsible for the repainting of the first post and its discs
            for (int i = 0 ; i < numOfDiscs ; i++)
            {
                if (arr1 [temp - i] [moveCount] != 0)
                {
                    //the colors array contains various colors used by the discs
                    g.setColor (colors [arr1 [temp - i] [moveCount] - 1]);
                    //a rectangle is filled using that color and the starting position and length calculated above
                    g.fillRect (startingP [arr1 [temp - i] [moveCount]], 525 - (25 * i), x [arr1 [temp - i] [moveCount]], 25);
                    //the rectangle is then outlined in black (aesthetic)
                    g.setColor (Color.BLACK);
                    g.drawRect (startingP [arr1 [temp - i] [moveCount]], 525 - (25 * i), x [arr1 [temp - i] [moveCount]], 25);
                }
            }

            //this second loop is responsible for the repainting of the second post and its discs
            for (int i = 0 ; i < numOfDiscs ; i++)
            {
                if (arr2 [temp - i] [moveCount] != 0)
                {
                    g.setColor (colors [arr2 [temp - i] [moveCount] - 1]);
                    g.fillRect (200 + startingP [arr2 [temp - i] [moveCount]], 525 - (25 * i), x [arr2 [temp - i] [moveCount]], 25);
                    g.setColor (Color.BLACK);
                    g.drawRect (200 + startingP [arr2 [temp - i] [moveCount]], 525 - (25 * i), x [arr2 [temp - i] [moveCount]], 25);
                }
            }

            //this third loop is responsible for the repainting of the third post and its discs
            for (int i = 0 ; i < numOfDiscs ; i++)
            {
                if (arr3 [temp - i] [moveCount] != 0)
                {
                    g.setColor (colors [arr3 [temp - i] [moveCount] - 1]);
                    g.fillRect (400 + startingP [arr3 [temp - i] [moveCount]], 525 - (25 * i), x [arr3 [temp - i] [moveCount]], 25);
                    g.setColor (Color.BLACK);
                    g.drawRect (400 + startingP [arr3 [temp - i] [moveCount]], 525 - (25 * i), x [arr3 [temp - i] [moveCount]], 25);
                }
            }
            if (skipper == 1)//if the auto button was clicked a delay is added, based on a user specified delay
            {
                try
                {
                    Thread.sleep (speedAni);
                }
                catch (Exception a)
                {
                }
            }
        }
        skipper = 0;//the auto feature is disabled after the loop is complete
        h = hanoi.getOpt () - 1;//h is reset to cause the loop to loop only once by default again
    }


    public static void main (String[] args)
    {
        new HanoiGUI ();//calls constructor
    } // main method
} // GUI class

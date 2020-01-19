/*
Skanda Kodunad
June 4th, 2018
The Hanoi class is responsible for the logic that solves the Towers of Hanoi puzzle, the output is given using standard output
*/
import javax.swing.*;
public class Hanoi
{
    //variable declaration
    private int start = 0, mid = 1, end = 2, numberOfDiscs, moveCount = 1, count = 0, opt = 0;
    private int[] [] final1, final2, final3, weights;
    private int[] topMost = new int [3], discs;
    private static int main = 0;

    public static void main (String[] args)  //self testing main tests the class as a whole
    {
        Hanoi.setTest (1);
        Hanoi hanoi = new Hanoi (4);
    }


    public static void setTest (int num)  //the purpose of the "main" variable is to differentiate main method testing and GUI calling
    {
        //if the main variable is equal to 1, the output is displayed on the screen using standard output
        //otherwise the main variable is 0 and the output is not displayed as the GUI displays it.
        main = num;
    }


    public Hanoi (int numOfDiscs)  //constructor takes in the number of discs in the puzzle
    {
        discs = new int [3]; //the discs array stores the number of discs on each peg
        numberOfDiscs = numOfDiscs;
        weights = new int [3] [numberOfDiscs]; //the weights array stores the actual value of each disc and is used for certain comparisons
        discs [0] = numberOfDiscs; //at the start, the first peg contains all the discs

        opt = calcOpt (numberOfDiscs); //calculates the optimal number of moves to solve the puzzle given the number of discs

        //These three arrays are used to retrive the post data (after each move) from the GUI, so it can display the discs accordingly
        final1 = new int [numberOfDiscs] [opt + 1];
        final2 = new int [numberOfDiscs] [opt + 1];
        final3 = new int [numberOfDiscs] [opt + 1];

        //before the first move, the first post has all the discs
        for (int i = 0 ; i < numberOfDiscs ; i++)
        {
            final1 [i] [0] = (i + 1);
            weights [0] [i] = (numberOfDiscs - i);
        }

        //at the start, the top most disc on post 1 has a value of 1
        topMost [0] = 1;

        //if the program is being run through the testing main, the moveDisc method is called
        if (getTest () == 1)
        {
            moveDisc (discs, start, mid, end);
        }
    }


    //method that calculates the optimal number of moves based on number of discs
    public int calcOpt (int numDiscs)
    {
        //the optimal solution for 3 discs is known as 7, and everything else is calculated based on this fact.
        int opt = 7;
        int num = 3;
        for (int i = num ; i < numDiscs ; i++)
        {
            opt = (opt * 2) + 1;
        }
        return opt;
    }


    //based on a given start and end post, the "middle" post is assigned (the post not involved)
    public int calcMid (int start, int end)
    {
        int mid = 0;
        if (start == 1 && end == 0 || start == 0 && end == 1)
        {
            mid = 2;
        }
        else if (start == 2 && end == 0 || start == 0 && end == 2)
        {
            mid = 1;
        }
        else if (start == 1 && end == 2 || start == 2 && end == 1)
        {
            mid = 0;
        }
        return mid;
    }


    //these "get" methods are mainly used by the GUI to retrieve certain variables
    public int getOpt ()
    {
        return opt;
    }


    public int getTest ()
    {
        return main;
    }


    public int getNumOfDiscs ()
    {
        return numberOfDiscs;
    }


    public int[] [] getFinal1 ()
    {
        return final1;
    }


    public int[] [] getFinal2 ()
    {
        return final2;
    }


    public int[] [] getFinal3 ()
    {
        return final3;
    }


    public void moveDisc (int[] discs, int start, int mid, int end)  //the core method in this program
    {
        //first the method takes in the number of discs on each post, the start post, middle post and end post
        //the moves depend on whether the number of discs on the start post is odd or even
        if (discs [start] % 2 == 0)
        {
            //inital code that applies to all even disc puzzles
            moveThreeEven (discs, start, mid, end);
            //base condition that outputs the final result if the first two posts are 0
            if (discs [0] <= 0 && discs [1] <= 0)
            {
                if (getTest () == 1)
                {
                    System.out.println ("The optimal number of moves to solve this (" + numberOfDiscs + ") disc puzzle is: " + (moveCount - 1));
                }
                return;
            }

            //this if else block is used to find the "switch" move that happens after every 7 moves.
            if (weights [end] [0] == 0)
            {
                swap (discs, start, end);
            }
            else if (weights [start] [0] == 0)
            {
                swap (discs, end, start);
            }
            else if (topMost [start] < topMost [end])
            {
                swap (discs, start, end);
            }
            else if (topMost [start] > topMost [end])
            {
                swap (discs, end, start);
            }

            //the new start, end and middle are calculated
            start = start (start);
            end = end (discs, start, end);
            mid = calcMid (start, end);

            //the method is called again with new information
            moveDisc (discs, start, mid, end);
        }
        else if (discs [start] % 2 == 1)
        {
            //initial code that applies to all odd disc puzzles
            moveThreeOdd (discs, start, mid, end);
            //base condition that outputs the final result if the first two posts are 0
            if (discs [0] <= 0 && discs [1] <= 0)
            {
                if (getTest () == 1)
                {
                    System.out.println ("The optimal number of moves to solve this (" + numberOfDiscs + ") disc puzzle is: " + (moveCount - 1));
                }
                return;
            }

            //this if else block is used to find the "switch" move that happens after every 7 moves.
            //(it differs slightly compared to the even block)
            if (weights [mid] [0] == 0)
            {
                swap (discs, start, mid);
            }
            else if (weights [start] [0] == 0)
            {
                swap (discs, mid, start);
            }
            else if (topMost [start] < topMost [mid])
            {
                swap (discs, start, mid);
            }
            else if (topMost [start] > topMost [mid])
            {
                swap (discs, mid, start);
            }

            //the new start, end and middle are calculated
            start = start (start);
            end = end (discs, start, end);
            mid = calcMid (start, end);

            //the method is called again with new information
            moveDisc (discs, start, mid, end);
        }
    }


    //method that calculates the new starting post (based on the old starting post)
    public int start (int start)
    {
        //the new starting post follows a systematic pattern based on whether the puzzle was an even or odd disc puzzle
        if (numberOfDiscs % 2 == 1)//if odd, the new start is one less than the old starting post
        {
            start--;
            if (start < 0)
            {
                start = 2;
            }
        }
        else//if even, the new start is one more than the old starting post
        {
            start++;
            if (start > 2)
            {
                start = 0;
            }
        }
        return start;
    }


    //the new end post is calculated based on the starting post, and the number of discs on the starting post
    public int end (int discs[], int start, int end)
    {
        //calculating the ending post is a bit more complex than the start post. 
        //first, if the puzzle is an odd disc puzzle,
        if (numberOfDiscs % 2 == 1)
        {
            //if the number of discs on the starting post is odd, the ending post is one less than before
            if (discs [start] % 2 == 1)
            {
                end = start - 1;
                if (end < 0)
                {
                    end = 2;
                }
            }
            else//if the number of discs on the starting post is even, the ending post is one more than before
            {
                end = start + 1;
                if (end > 2)
                {
                    end = 0;
                }
            }
        }
        else//now, if the puzzle is an even disc puzzle,
        {
            //if the number of discs on the starting post is odd, the ending post is one more than before
            if (discs [start] % 2 == 1)
            {
                end = start + 1;
                if (end > 2)
                {
                    end = 0;
                }
            }
            else//if the number of discs on the starting post is even, the ending post is one less than before
            {
                end = start - 1;
                if (end < 0)
                {
                    end = 2;
                }
            }
        }
        return end;
    }


    //the inital 7 swap code for any even disc puzzle
    public void moveThreeEven (int[] discs, int start, int mid, int end)
    {
        swap (discs, start, mid);
        swap (discs, start, end);
        swap (discs, mid, end);
        swap (discs, start, mid);
        swap (discs, end, start);
        swap (discs, end, mid);
        swap (discs, start, mid);
    }


    //the initial 7 swap code for any odd disc puzzle
    public void moveThreeOdd (int[] discs, int start, int mid, int end)
    {
        swap (discs, start, end);
        swap (discs, start, mid);
        swap (discs, end, mid);
        swap (discs, start, end);
        swap (discs, mid, start);
        swap (discs, mid, end);
        swap (discs, start, end);
    }


    //the swap method is responsible for updating the number of discs on each post after a move and updating which disc was moved.
    public void swap (int[] discs, int n1, int n2)
    {
        //simply updates the number of discs on each post.
        discs [n1]--;
        discs [n2]++;

        //the formatted output for main testing, does not display when using the GUI
        if (getTest () == 1)
        {
            System.out.println ("Move disc (" + topMost [n1] + ") from post (" + (n1 + 1) + ") to post (" + (n2 + 1) + ")");
            System.out.println ("Move count: " + moveCount);
            System.out.println ("");
        }

        //updates the position of each disc after every move (based on the discs' "weight")
        //starts from the bottom on each post and works its way up
        for (int i = 0 ; i < numberOfDiscs ; i++)
        {
            if (weights [n2] [i] == 0)
            {
                while (weights [n1] [count] != 0)
                {
                    if (count == numberOfDiscs - 1)
                    {
                        count++;
                        break;
                    }
                    count++;
                }
                //the swap occurs here
                weights [n2] [i] = weights [n1] [count - 1];
                weights [n1] [count - 1] = 0;
                i = numberOfDiscs;//ends the loop
            }
        }


        //loop that calculates the value of the topMost disc after each move (used for calculating the "switch" moves)
        for (int i = 0 ; i < 3 ; i++)
        {
            for (int j = 1 ; j <= numberOfDiscs ; j++)
            {
                if (weights [i] [numberOfDiscs - j] != 0)
                {
                    topMost [i] = weights [i] [numberOfDiscs - j];
                    break;
                }
                if (j == numberOfDiscs)
                {
                    topMost [i] = 0;
                }
            }
        }

        //the position of each disc after each move is stored in these three arrays and is crucial to the function of the GUI
        for (int j = 1 ; j <= numberOfDiscs ; j++)
        {
            final1 [j - 1] [moveCount] = weights [0] [numberOfDiscs - j];
            final2 [j - 1] [moveCount] = weights [1] [numberOfDiscs - j];
            final3 [j - 1] [moveCount] = weights [2] [numberOfDiscs - j];
        }
        moveCount++;//the moveCount is increased
        count = 0;//the count variable (used in the "weights" calculation) is reset
    }


    // main method
} // Hanoi class

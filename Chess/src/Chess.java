//IMPORTANT LINE NUMBERS FOR MAJOR METHODS:
//actionPerformed - 997 (FUNCTION: When a piece is clicked on)
//check - 5167(FUNCTION: checks any move made by the king so ensure it is safe)
//checkMate - 9914(FUNCTION: If the king is in check, this method checks for checkmate)
//userClick - 13156(FUNCTION: Responsible for the apparent movement of pieces, icons of JButtons)
//validCheck - 14668(FUNCTION: Checks the validity of a move made by the user)

//IMPORTANT, pieceCodes for each piece
//0 - white King
//5 - black King
//1 and 11 are the two black knights
//6 and 66 are the two white knights
//2 and 22 are the two white rooks
//3 and 33 are the two black rooks
//4 and 44 are the two white bishops
//7 and 77 are the two black bishops
//8 is the white queen
//9 is the black queen
//101-108 are the white pawns
//201 - 208 are the black pawns
//These numbers have been used throughout the entire program used to determine which piece is being manipulated.

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
public class Chess extends JFrame implements ActionListener
{
    //General Variables
    JButton[] [] tiles;
    Container frame;
    JLabel backgL, intro, backgL1, labl, range, namesearch;
    JButton but, but1, but2, back;
    String promo = "", line = " ", userFlip = " ";
    int pieceCode = 90, place = 0, sorter = 1, sorter1 = 1, flip = 0, whoMoves = 0;
    JTextArea textarea;
    JScrollPane scr;
    JButton ratingSort, nameSort, rangeb, nameb;
    JTextField min, max, name;
    int[] position = new int [100];
    int[] rating = new int [100];
    String[] names = new String [100];
    String[] bits;

    JFrame fram = new JFrame ();
    Container fr = fram.getContentPane ();

    JFrame fram1 = new JFrame ();
    Container fr1 = fram1.getContentPane ();

    //Icons for all pieces
    ImageIcon whiteKing, whiteQueen, blackKnight, blackKing, whiteKnight, whiteRook, blackRook;
    ImageIcon whiteBish, blackBish, blackQueen, whitePawn, backg1, blackPawn;
	ImageIcon backg;
    Icon iconReset;
    ImageIcon[] allIcons;

    //counters
    int count = 1, count1 = 1, counter = 0, randnum = 0, randnum1 = 0, num = 1, var = 1, cnter1 = 0, randnum2 = 0, randnum3 = 0, cnter2 = 0;
    int counter1 = 0, counter2 = 0, counter3 = 0, counter4 = 0, counter5 = 0, counter6 = 0, randnum4 = 0, randnum5 = 0, randnum6 = 0, first7 = 0;
    int randnum7 = 0, randnum8 = 0, randnum9 = 0, bRand6 = 0, bCount6 = 0, row, col, number = 1000, tempx, tempy, colorDet, first = 0, first6 = 0;
    int pawncnt1 = 0, pawncnt2 = 0, pawncnt3 = 0, pawncnt4 = 0, pawncnt5 = 0, pawncnt6 = 0, pawncnt7 = 0, pawncnt8 = 0, cnter = 0, first4 = 0;
    int bRand1 = 0, bCount1 = 0, bRand2 = 0, bCount2 = 0, bRand3 = 0, bCount3 = 0, bRand4 = 0, bCount4 = 0, bRand5 = 0, bCount5 = 0, first5 = 0;
    int bPawncnt1 = 0, bPawncnt2 = 0, bPawncnt3 = 0, bPawncnt4 = 0, bPawncnt5 = 0, bPawncnt6 = 0, evenOdd = 1, first1 = 0, first2 = 0, first3 = 0;
    int checkMate, checkMate1, checkMate2, checkMate3, checkMate4, checkMate5, checkMate6, checkMate7, pwncount23 = 0, pawncnt31 = 0;
    int wValid, wValid1, wValid2, wValid3, wValid4, wValid5, wValid6, wValid7, disp;

    //Variables related to the Kings
    int whiteKingx = 4, whiteKingy = 7, blackKingx = 0, blackKingy = 4, kingX, kingY, threat = 0, ran = 0, ran1 = 0, kingFirst = 0, chk = 1;
    int checkThreat, tempKingx, tempKingy, checkThreat1, a, b, aaq, bbq, checkmate = 0, wkingFirstMove = 0, bkingFirstMove = 0;
    int[] checkmateChk = new int [8];
    int finalCheckMt, wRookCastle = 0, wRook1Castle = 0, bRookCastle = 0, bRook1Castle = 0;

    //Variables related to the Knights
    int blackKnightx = 0, blackKnighty = 1, whiteKnightx = 7, whiteKnighty = 1, whiteKnightx1 = 7, whiteKnighty1 = 6, blackKnightx1 = 0, blackKnighty1 = 6;
    int bKnight1Death = 0, bKnight2Death = 0, wKnight1Death = 0, wKnight2Death = 0, knightX, knightY, wKnightx, wKnighty, promoKnightx, promoKnighty;
    int promKnightFlag = 0, tempKnightx, tempKnighty, promKnightNum = 0, bPromKnightNum, bPromoKnightx, bPromoKnighty;

    //Variables related to the Rooks
    int whiteRookx = 7, whiteRooky = 7, blackRookx = 0, blackRooky = 7, whiteRookx1 = 7, whiteRooky1 = 0, blackRookx1 = 0, blackRooky1 = 0, wRook1Death = 0;
    int bRook1Death = 0, wRook2Death = 0, bRook2Death = 0, rookX, rookY, promoRookx, promoRooky, promRookFlag = 0, promRookNum, bPromRookNum, bPromoRookx;
    int wRook1T, bPromoRooky, wRookFM = 0, wRook1FM = 0, bRookFM = 0, bRook1FM = 0;

    //Variables related to the Bishops
    int whiteBishx = 7, whiteBishy = 5, blackBishx = 0, blackBishy = 5, wBish1Death = 0, wBish2Death = 0, bBish1Death = 0, bBish2Death = 0, blackBishx1 = 0;
    int blackBishy1 = 2, whiteBishx1 = 7, whiteBishy1 = 2, bPromBishNum, bPromoBishx, bPromoBishy, promoBishx, promoBishy, promBishFlag = 0, promBishNum;
    int bishX, bishY;

    //Variables related to the Queens
    int blackQueenx = 0, blackQueeny = 3, bQueen1Death = 0, promoQueenx, promoQueeny, promQueenFlag = 0, promQueenNum, wQueen1Death = 0, whiteQueenx = 7;
    int whiteQueeny = 3, bPromQueenNum, bPromoQueenx, bPromoQueeny, queenX, queenY;

    //Variables related to the Pawns
    int blackPawnX, blackPawnY, whitePawnX, whitePawnY, pawnX, pawnY, firstMove = 0, whitePawnx2 = 6, whitePawny2 = 3, whitePawnx1 = 6, whitePawny1 = 1;
    int whitePawnx3 = 6, whitePawny3 = 0, blackPawnx1 = 1, blackPawny1 = 2, blackPawnx2 = 1, blackPawny2 = 4, blackPawnx3 = 1, blackPawny3 = 0;
    int blackPawny4 = 1, blackPawnx5 = 1, blackPawny5 = 3, blackPawnx6 = 1, blackPawny6 = 5, blackPawnx7 = 1, blackPawny7 = 6, blackPawnx8 = 1;
    int whitePawnx4 = 6, whitePawny4 = 2, whitePawnx5 = 6, whitePawny5 = 4, whitePawnx6 = 6, whitePawny6 = 5, whitePawnx7 = 6, whitePawny7 = 6;
    int blackPawny8 = 7, whitePawnx8 = 6, whitePawny8 = 7, blackPawnx4 = 1, tempPawnx, tempPawny;
    int[] whitePAWNXs = new int [8], whitePAWNYs = new int [8], pawnFirstMvCnts = new int [8], wPawnDeaths = new int [8];
    int[] blackPAWNXs = new int [8], blackPAWNYs = new int [8], bpawnFirstMvCnts = new int [8], bPawnDeaths = new int [8];
    int enPassx, enPassy, enPass = 0;
    int wPawn1Death = 0, wPawn2Death = 0, wPawn3Death = 0, wPawn4Death = 0, wPawn5Death = 0, wPawn6Death = 0, wPawn7Death = 0, wPawn8Death = 0;
    int bPawn1Death = 0, bPawn2Death = 0, bPawn3Death = 0, bPawn4Death = 0, bPawn5Death = 0, bPawn6Death = 0, bPawn7Death = 0, bPawn8Death = 0;

    public Chess ()
    {
        super ("Chess");
        frame = getContentPane ();
        fr.setLayout (null);
        fr1.setLayout (null);
        frame.setLayout (new GridLayout (8, 8));
        Toolkit dir = Toolkit.getDefaultToolkit ();
        whiteKing = new ImageIcon (getClass().getResource("whiteKing.png"));
        whiteQueen = new ImageIcon (getClass().getResource("whiteQueen.png"));
        blackQueen = new ImageIcon (getClass().getResource("blackQueen.png"));
        blackKnight = new ImageIcon (getClass().getResource("blackKnight.png"));
        blackKing = new ImageIcon (getClass().getResource("blackKing.png"));
        whiteKnight = new ImageIcon (getClass().getResource("whiteKnight.png")); //Load all the images of the pieces
        whiteRook = new ImageIcon (getClass().getResource("whiteRook.png"));
        blackRook = new ImageIcon (getClass().getResource("blackRook.png"));
        whiteBish = new ImageIcon (getClass().getResource("whiteBish.png"));
        blackBish = new ImageIcon (getClass().getResource("blackBish.png"));
        whitePawn = new ImageIcon (getClass().getResource("whitePawn.png"));
        blackPawn = new ImageIcon (getClass().getResource("blackPawn.png"));
        backg = new ImageIcon(getClass().getResource("backg.jpg"));
        backg1 = new ImageIcon (getClass().getResource("backg1.jpg"));
        backgL = new JLabel (backg);
        backgL.setBounds (0, 0, 600, 600); // Background Image

        backgL1 = new JLabel (backg1);
        backgL1.setBounds (0, 0, 800, 700); // Background Image

        //Creation of all the tiles (Squares making up the board) and color Determination
        tiles = new JButton [8] [8];
        for (int i = 0 ; i < 8 ; i++)
        {
            for (int j = 0 ; j < 8 ; j++)
            {
                tiles [i] [j] = new JButton ();
                if ((i + j) % 2 != 0)
                {
                    tiles [i] [j].setBackground (Color.GRAY);
                }
                if ((i + j) % 2 == 0)
                {
                    tiles [i] [j].setBackground (Color.WHITE);
                }
                tiles [i] [j].addActionListener (this);
                frame.add (tiles [i] [j]);
            }
        }
        //Setting the default position of each piece
        tiles [7] [4].setIcon (whiteKing);
        tiles [0] [1].setIcon (blackKnight);
        tiles [0] [6].setIcon (blackKnight);
        tiles [7] [6].setIcon (whiteKnight);
        tiles [0] [4].setIcon (blackKing);
        tiles [7] [1].setIcon (whiteKnight);
        tiles [7] [7].setIcon (whiteRook);
        tiles [0] [7].setIcon (blackRook);
        tiles [7] [0].setIcon (whiteRook);
        tiles [0] [0].setIcon (blackRook);
        tiles [7] [5].setIcon (whiteBish);
        tiles [0] [5].setIcon (blackBish);
        tiles [7] [2].setIcon (whiteBish);
        tiles [0] [2].setIcon (blackBish);
        tiles [7] [3].setIcon (whiteQueen);
        tiles [0] [3].setIcon (blackQueen);
        tiles [6] [1].setIcon (whitePawn);
        tiles [6] [3].setIcon (whitePawn);
        tiles [6] [0].setIcon (whitePawn);
        tiles [6] [2].setIcon (whitePawn);
        tiles [6] [4].setIcon (whitePawn);
        tiles [6] [5].setIcon (whitePawn);
        tiles [6] [6].setIcon (whitePawn);
        tiles [6] [7].setIcon (whitePawn);
        tiles [1] [2].setIcon (blackPawn);
        tiles [1] [4].setIcon (blackPawn);
        tiles [1] [0].setIcon (blackPawn);
        tiles [1] [1].setIcon (blackPawn);
        tiles [1] [3].setIcon (blackPawn);
        tiles [1] [5].setIcon (blackPawn);
        tiles [1] [6].setIcon (blackPawn);
        tiles [1] [7].setIcon (blackPawn);

        ImageIcon[] allIcons = {whiteKing, whiteQueen, blackKnight, blackKing, whiteKnight, whiteRook, blackRook, whiteBish, blackBish, blackQueen, whitePawn, backg, blackPawn};

        //Adding components to the Frame
        Font newfont = new Font ("Comic Sans MS", Font.BOLD, 24);

        whitePAWNXs [0] = whitePawnx1;
        whitePAWNXs [1] = whitePawnx2;
        whitePAWNXs [2] = whitePawnx3;
        whitePAWNXs [3] = whitePawnx4;
        whitePAWNXs [4] = whitePawnx5; //Storing the white pawn X variables
        whitePAWNXs [5] = whitePawnx6;
        whitePAWNXs [6] = whitePawnx7;
        whitePAWNXs [7] = whitePawnx8;

        whitePAWNYs [0] = whitePawny1;
        whitePAWNYs [1] = whitePawny2;
        whitePAWNYs [2] = whitePawny3;
        whitePAWNYs [3] = whitePawny4;
        whitePAWNYs [4] = whitePawny5; //Storing the white pawn Y variables
        whitePAWNYs [5] = whitePawny6;
        whitePAWNYs [6] = whitePawny7;
        whitePAWNYs [7] = whitePawny8;

        blackPAWNXs [0] = blackPawnx1;
        blackPAWNXs [1] = blackPawnx2;
        blackPAWNXs [2] = blackPawnx3;
        blackPAWNXs [3] = blackPawnx4;
        blackPAWNXs [4] = blackPawnx5; //Storing the black pawn X variables
        blackPAWNXs [5] = blackPawnx6;
        blackPAWNXs [6] = blackPawnx7;
        blackPAWNXs [7] = blackPawnx8;

        blackPAWNYs [0] = blackPawny1;
        blackPAWNYs [1] = blackPawny2;
        blackPAWNYs [2] = blackPawny3;
        blackPAWNYs [3] = blackPawny4;
        blackPAWNYs [4] = blackPawny5; //Storing the black pawn Y variables
        blackPAWNYs [5] = blackPawny6;
        blackPAWNYs [6] = blackPawny7;
        blackPAWNYs [7] = blackPawny8;

        wPawnDeaths [0] = wPawn1Death;
        wPawnDeaths [1] = wPawn3Death;
        wPawnDeaths [2] = wPawn2Death;
        wPawnDeaths [3] = wPawn4Death; //Storing the white pawn death(removed from game) variables
        wPawnDeaths [4] = wPawn5Death;
        wPawnDeaths [5] = wPawn6Death;
        wPawnDeaths [6] = wPawn7Death;
        wPawnDeaths [7] = wPawn8Death;

        bPawnDeaths [0] = bPawn1Death;
        bPawnDeaths [1] = bPawn2Death;
        bPawnDeaths [2] = bPawn3Death;
        bPawnDeaths [3] = bPawn4Death;
        bPawnDeaths [4] = bPawn5Death; //Storing the black pawn death(removed from game) variables
        bPawnDeaths [5] = bPawn6Death;
        bPawnDeaths [6] = bPawn7Death;
        bPawnDeaths [7] = bPawn8Death;

        //Creation of components to add to the multiple JFrames
        but = new JButton ("MultiPlayer Chess!");
        but.setBounds (28, 400, 250, 50);
        but.addActionListener (this);
        but.setFont (newfont);

        but2 = new JButton ("Top 100 Players!");
        but2.setBounds (318, 400, 250, 50);
        but2.addActionListener (this);
        but2.setFont (newfont);
                
        intro = new JLabel ("Welcome To My Chess Game!");
        intro.setBounds (120, -10, 400, 50);
        intro.setFont (newfont);

        fram.setSize (600, 600);
        fram.setVisible (true);
        fram.setResizable (false);
        fram.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        fr.add (but);
        fr.add (intro);
        fr.add (but2);
        fr.add (backgL);

        textarea = new JTextArea ();
        textarea.setBounds (100, 150, 600, 500);
        textarea.setFont (newfont);

        min = new JTextField (10);
        min.setBounds (130, 510, 100, 50);
        min.setFont (newfont);

        max = new JTextField (10);
        max.setBounds (280, 510, 100, 50);
        max.setFont (newfont);

        name = new JTextField (10);
        name.setBounds (475, 510, 200, 50);
        name.setFont (newfont);

        ratingSort = new JButton ("Sort by Rating");
        ratingSort.setBounds (100, 50, 300, 50);
        ratingSort.setFont (newfont);
        ratingSort.addActionListener (this);
        
        back = new JButton ("Back");
        back.setBounds (10, 510, 100, 50);
        back.setFont (newfont);
        back.addActionListener (this);

        nameSort = new JButton ("Sort by Name");
        nameSort.setBounds (400, 50, 300, 50);
        nameSort.setFont (newfont);
        nameSort.addActionListener (this);

        labl = new JLabel ("Top 100 Chess Players WorldWide!");
        labl.setBounds (205, 0, 450, 50);
        labl.setFont (newfont);

        range = new JLabel ("to");
        range.setBounds (241, 510, 49, 50);
        range.setFont (newfont);

        rangeb = new JButton ("Search by rating range");
        rangeb.setBounds (100, 570, 310, 50);
        rangeb.setFont (newfont);
        rangeb.addActionListener (this);

        nameb = new JButton ("Search by name");
        nameb.setBounds (450, 570, 250, 50);
        nameb.setFont (newfont);
        nameb.addActionListener (this);

        scr = new JScrollPane (textarea);
        scr.setBounds (100, 100, 600, 400);

        fr1.add (scr);
        fr1.add (ratingSort);
        fr1.add (nameSort);
        fr1.add (labl);
        fr1.add (min);
        fr1.add (max);
        fr1.add (rangeb);
        fr1.add (range);
        fr1.add (nameb);
        fr1.add (name);
        fr1.add (back);
        fr1.add (backgL1);

        fram1.setSize (800, 700);
        fram1.setVisible (false);
        fram1.setResizable (false);
        fram1.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        setSize (600, 600);
        setResizable (false);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }


    //the variable ColorDet is assigned to every piece, determining whether it is black or white,
    public void promoKnight (int pawnX, int pawnY, ActionEvent e)  //If user chooses to promote white Pawn to Knight
    {
        promoKnightx = pawnX;
        promoKnighty = pawnY;
        promKnightFlag = 1;

        if (first == 0) //counter
        {
        }
        else
        {
            if (tiles [promoKnightx] [promoKnighty].getIcon () != whiteKnight)
            {
                promKnightFlag = 0;
            }
        }
        first = 1;

        if (e.getSource () == tiles [promoKnightx] [promoKnighty] && promKnightFlag == 1) //if the promoted piece is clicked
        {
            knightX = promoKnightx;
            knightY = promoKnighty;
            promKnightFlag = 1;
            pieceCode = 6;
            colorDet = 0;
            knight (knightX, knightY);
        }
    }


    public void promoBish (int pawnX, int pawnY, ActionEvent e)  //If user chooses to promote white Pawn to Bishop
    {
        promoBishx = pawnX;
        promoBishy = pawnY;
        promBishFlag = 1;
        if (first1 == 0)
        {
        }
        else
        {
            if (tiles [promoBishx] [promoBishy].getIcon () != whiteBish)
            {
                promBishFlag = 0;
            }
        }
        first1 = 1;

        if (e.getSource () == tiles [promoBishx] [promoBishy] && promBishFlag == 1) //if the promoted piece is clicked
        {
            bishX = promoBishx;
            bishY = promoBishy;
            promBishFlag = 1;
            pieceCode = 4;
            colorDet = 0;
            bishop (bishX, bishY);
        }
    }


    public void promoRook (int pawnX, int pawnY, ActionEvent e)  //If user chooses to promote white Pawn to Rook
    {
        promoRookx = pawnX;
        promoRooky = pawnY;
        promRookFlag = 1;
        if (first2 == 0)
        {
        }
        else
        {
            if (tiles [promoRookx] [promoRooky].getIcon () != whiteRook)
            {
                promRookFlag = 0;
            }
        }
        first2 = 1;

        if (e.getSource () == tiles [promoRookx] [promoRooky] && promRookFlag == 1) //if the promoted piece is clicked
        {
            rookX = promoRookx;
            rookY = promoRooky;
            promRookFlag = 1;
            pieceCode = 2;
            colorDet = 0;
            rook (rookX, rookY);
        }
    }


    public void promoQueen (int pawnX, int pawnY, ActionEvent e)  //If user chooses to promote white Pawn to Queen
    {
        promQueenFlag = 1;
        promoQueenx = pawnX;
        promoQueeny = pawnY;
        if (first3 == 0)
        {
        }
        else
        {
            if (tiles [promoQueenx] [promoQueeny].getIcon () != whiteQueen)
            {
                promQueenFlag = 0;
            }
        }
        first3 = 1;

        if (e.getSource () == tiles [promoQueenx] [promoQueeny] && promQueenFlag == 1) //if the promoted piece is clicked
        {
            bishX = promoQueenx;
            bishY = promoQueeny;
            pieceCode = 8;
            promQueenFlag = 1;
            bishop (bishX, bishY);
            rookX = promoQueenx;
            rookY = promoQueeny;
            rook (rookX, rookY);
            colorDet = 0;
        }
    }


    public void bPromoKnight (int pawnX, int pawnY, ActionEvent e)  //If user chooses to promote black Pawn to Knight
    {
        bPromoKnightx = pawnX;
        bPromoKnighty = pawnY;
        promKnightFlag = 1;

        if (first4 == 0)
        {

        }
        else
        {
            if (tiles [bPromoKnightx] [bPromoKnighty].getIcon () != blackKnight)
            {
                promKnightFlag = 0;
            }
        }
        first4 = 1;

        if (e.getSource () == tiles [bPromoKnightx] [bPromoKnighty] && promKnightFlag == 1) //if the promoted piece is clicked
        {
            knightX = bPromoKnightx;
            knightY = bPromoKnighty;
            promKnightFlag = 1;
            pieceCode = 1;
            colorDet = 1;
            knight (knightX, knightY);
        }
    }


    public void bPromoBish (int pawnX, int pawnY, ActionEvent e)  //If user chooses to promote black Pawn to Bishop
    {
        bPromoBishx = pawnX;
        bPromoBishy = pawnY;
        promBishFlag = 1;
        if (first5 == 0)
        {

        }
        else
        {
            if (tiles [bPromoBishx] [bPromoBishy].getIcon () != blackBish)
            {
                promBishFlag = 0;
            }
        }
        first5 = 1;

        if (e.getSource () == tiles [bPromoBishx] [bPromoBishy] && promBishFlag == 1) //if the promoted piece is clicked
        {
            bishX = bPromoBishx;
            bishY = bPromoBishy;
            promBishFlag = 1;
            pieceCode = 7;
            colorDet = 1;
            bishop (bishX, bishY);
        }
    }


    public void bPromoRook (int pawnX, int pawnY, ActionEvent e)  //If user chooses to promote black Pawn to Rook
    {
        bPromoRookx = pawnX;
        bPromoRooky = pawnY;
        promRookFlag = 1;
        if (first6 == 0)
        {
        }
        else
        {
            if (tiles [bPromoRookx] [bPromoRooky].getIcon () != blackRook)
            {
                promRookFlag = 0;
            }
        }
        first6 = 1;

        if (e.getSource () == tiles [bPromoRookx] [bPromoRooky] && promRookFlag == 1) //if the promoted piece is clicked
        {
            rookX = bPromoRookx;
            rookY = bPromoRooky;
            promRookFlag = 1;
            pieceCode = 3;
            colorDet = 1;
            rook (rookX, rookY);
        }
    }


    public void bPromoQueen (int pawnX, int pawnY, ActionEvent e)  //If user chooses to promote black Pawn to Queen
    {
        promQueenFlag = 1;
        bPromoQueenx = pawnX;
        bPromoQueeny = pawnY;

        if (first7 == 0)
        {

        }
        else
        {
            if (tiles [bPromoQueenx] [bPromoQueeny].getIcon () != blackQueen)
            {
                promQueenFlag = 0;
            }
        }
        first7 = 1;

        if (e.getSource () == tiles [bPromoQueenx] [bPromoQueeny] && promQueenFlag == 1) //if the promoted piece is clicked
        {
            bishX = bPromoQueenx;
            bishY = bPromoQueeny;
            pieceCode = 9;
            promQueenFlag = 1;
            bishop (bishX, bishY);
            rookX = bPromoQueenx;
            rookY = bPromoQueeny;
            rook (rookX, rookY);
            colorDet = 1;
        }
    }


    public void topPlayers ()  //Method used to read the file to display the names and scores of the top 100 players
    {
        try
        {
            BufferedReader kb = new BufferedReader (new FileReader ("textFileChess.txt"));
            while ((line = kb.readLine ()) != null) ///reads till the last line in the file
            {
                bits = line.split (",");
                position [place] = Integer.parseInt (bits [0]);
                names [place] = bits [1];
                rating [place] = Integer.parseInt (bits [2]);
                textarea.append (line + "\n");
                place++;
            }
        }
        catch (IOException w)
        {
        }
    }


    public void ratingSort ()  //Sorts the scores of the top players in descending order
    {
        try
        {
            PrintWriter pw = new PrintWriter (new FileWriter ("OutPutFile.txt"));
            for (int i = 0 ; i < 100 ; i++)
            {
                for (int j = 0 ; j < 99 ; j++)
                {
                    if (rating [j] < rating [j + 1]) //bubble sort
                    {
                        int temp = rating [j];
                        rating [j] = rating [j + 1];
                        rating [j + 1] = temp;

                        int temp1 = position [j];
                        position [j] = position [j + 1];
                        position [j + 1] = temp1;

                        String tempnam = names [j];
                        names [j] = names [j + 1];
                        names [j + 1] = tempnam;
                    }
                }
            }


            textarea.setText ("");
            for (int i = 0 ; i < 100 ; i++)
            {
                pw.println (position [i] + ", " + names [i] + ", " + rating [i]);
                textarea.append (position [i] + ", " + names [i] + ", " + rating [i] + "\n");
            }
            pw.close ();
        }
        catch (IOException q)
        {
        }
    }


    public void ratingSortRev ()  //Sorts the scores of the top players in ascedning order
    {
        try
        {
            PrintWriter pw = new PrintWriter (new FileWriter ("OutPutFile.txt"));
            for (int i = 0 ; i < 100 ; i++)
            {
                for (int j = 0 ; j < 99 ; j++)
                {
                    if (rating [j] > rating [j + 1])
                    {
                        int temp = rating [j]; //bubble sort
                        rating [j] = rating [j + 1];
                        rating [j + 1] = temp;

                        int temp1 = position [j];
                        position [j] = position [j + 1];
                        position [j + 1] = temp1;

                        String tempnam = names [j];
                        names [j] = names [j + 1];
                        names [j + 1] = tempnam;
                    }
                }
            }

            textarea.setText ("");
            for (int i = 0 ; i < 100 ; i++)
            {
                pw.println (position [i] + ", " + names [i] + ", " + rating [i]);
                textarea.append (position [i] + ", " + names [i] + ", " + rating [i] + "\n");
            }
            pw.close ();
        }
        catch (IOException q)
        {
        }
    }


    public void nameSort ()
    {
        try
        {
            PrintWriter pw = new PrintWriter (new FileWriter ("OutPutFile.txt"));
            char a, b;
            int[] ascii = new int [100];
            int counter = 1;
            for (int i = 0 ; i < 100 ; i++)
            {
                a = names [i].charAt (0);
                ascii [i] = (int) a;
            }
            for (int i = 0 ; i < 100 ; i++)
            {
                for (int j = 0 ; j < 99 ; j++)
                {
                    if (ascii [j] > ascii [j + 1])
                    {
                        int tempasc = ascii [j + 1];
                        ascii [j + 1] = ascii [j];
                        ascii [j] = tempasc;

                        int temp = rating [j];
                        rating [j] = rating [j + 1]; //bubble sort
                        rating [j + 1] = temp;

                        int temp1 = position [j];
                        position [j] = position [j + 1];
                        position [j + 1] = temp1;

                        String tempnam = names [j];
                        names [j] = names [j + 1];
                        names [j + 1] = tempnam;
                    }
                }
            }
            for (int i = 0 ; i < 100 ; i++)
            {
                for (int j = 0 ; j < 99 ; j++)
                {
                    try
                    {
                        if (ascii [j] == ascii [j + 1])
                        {
                            a = names [j].charAt (counter);
                            b = names [j + 1].charAt (counter); //sort to check next letter if the first letters match
                            ascii [j] = (int) a;
                            ascii [j + 1] = (int) b;
                            if (ascii [j] > ascii [j + 1])
                            {
                                if (j < 98)
                                {
                                    int tempasc = ascii [j];
                                    ascii [j] = ascii [j + 1];
                                    ascii [j + 1] = tempasc;

                                    int temp = rating [j];
                                    rating [j] = rating [j + 1];
                                    rating [j + 1] = temp;

                                    int temp1 = position [j];
                                    position [j] = position [j + 1];
                                    position [j + 1] = temp1;

                                    String tempnam = names [j];
                                    names [j] = names [j + 1];
                                    names [j + 1] = tempnam;

                                    a = names [j + 1].charAt (0);
                                    b = names [j + 2].charAt (0);
                                    ascii [j + 1] = (int) a;
                                    ascii [j + 2] = (int) b;
                                    counter = 1;
                                }
                                else
                                {
                                    int tempasc = ascii [j];
                                    ascii [j] = ascii [j + 1];
                                    ascii [j + 1] = tempasc;

                                    int temp = rating [j];
                                    rating [j] = rating [j + 1];
                                    rating [j + 1] = temp;

                                    int temp1 = position [j];
                                    position [j] = position [j + 1];
                                    position [j + 1] = temp1;

                                    String tempnam = names [j];
                                    names [j] = names [j + 1];
                                    names [j + 1] = tempnam;

                                    a = names [j].charAt (0);
                                    b = names [j + 1].charAt (0);
                                    ascii [j] = (int) a;
                                    ascii [j + 1] = (int) b;
                                    counter = 1;
                                }
                            }
                            else if (ascii [j] == ascii [j + 1])
                            {
                                counter++;
                                j--;
                            }
                            else
                            {
                                if (j < 98)
                                {
                                    a = names [j + 1].charAt (0);
                                    b = names [j + 2].charAt (0);
                                    ascii [j + 1] = (int) a;
                                    ascii [j + 2] = (int) b;
                                    counter = 1;
                                }
                                else
                                {
                                    a = names [j].charAt (0);
                                    b = names [j + 1].charAt (0);
                                    ascii [j] = (int) a;
                                    ascii [j + 1] = (int) b;
                                    counter = 1;
                                }
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException es)
                    {
                    }
                }
            }
            textarea.setText ("");
            for (int i = 0 ; i < 100 ; i++)
            {
                pw.println (position [i] + ", " + names [i] + ", " + rating [i]);
                textarea.append (position [i] + ", " + names [i] + ", " + rating [i] + "\n");
            }
            pw.close ();
        }
        catch (IOException q)
        {
        }
    }


    public void nameSortRev ()
    {
        try
        {
            PrintWriter pw = new PrintWriter (new FileWriter ("OutPutFile.txt"));
            char a, b;
            int[] ascii = new int [100];
            int counter = 1;

            for (int i = 0 ; i < 100 ; i++)
            {
                a = names [i].charAt (0);
                ascii [i] = (int) a;
            }
            for (int i = 0 ; i < 100 ; i++)
            {
                for (int j = 0 ; j < 99 ; j++)
                {
                    if (ascii [j] > ascii [j + 1]) //bubble sort
                    {
                        int tempasc = ascii [j + 1];
                        ascii [j + 1] = ascii [j];
                        ascii [j] = tempasc;

                        int temp = rating [j];
                        rating [j] = rating [j + 1];
                        rating [j + 1] = temp;

                        int temp1 = position [j];
                        position [j] = position [j + 1];
                        position [j + 1] = temp1;

                        String tempnam = names [j];
                        names [j] = names [j + 1];
                        names [j + 1] = tempnam;
                    }
                }
            }
            for (int i = 0 ; i < 100 ; i++)
            {
                for (int j = 0 ; j < 99 ; j++)
                {
                    try
                    {
                        if (ascii [j] == ascii [j + 1])
                        {
                            a = names [j].charAt (counter);
                            b = names [j + 1].charAt (counter);
                            ascii [j] = (int) a;
                            ascii [j + 1] = (int) b;
                            if (ascii [j] > ascii [j + 1])
                            {
                                if (j < 98)
                                {
                                    int tempasc = ascii [j];
                                    ascii [j] = ascii [j + 1];
                                    ascii [j + 1] = tempasc;

                                    int temp = rating [j];
                                    rating [j] = rating [j + 1];
                                    rating [j + 1] = temp;

                                    int temp1 = position [j]; //sort to check next letter if the first letters match
                                    position [j] = position [j + 1];
                                    position [j + 1] = temp1;

                                    String tempnam = names [j];
                                    names [j] = names [j + 1];
                                    names [j + 1] = tempnam;

                                    a = names [j + 1].charAt (0);
                                    b = names [j + 2].charAt (0);
                                    ascii [j + 1] = (int) a;
                                    ascii [j + 2] = (int) b;
                                    counter = 1;
                                }
                                else
                                {
                                    int tempasc = ascii [j];
                                    ascii [j] = ascii [j + 1];
                                    ascii [j + 1] = tempasc;

                                    int temp = rating [j];
                                    rating [j] = rating [j + 1];
                                    rating [j + 1] = temp;

                                    int temp1 = position [j];
                                    position [j] = position [j + 1];
                                    position [j + 1] = temp1;

                                    String tempnam = names [j];
                                    names [j] = names [j + 1];
                                    names [j + 1] = tempnam;

                                    a = names [j].charAt (0);
                                    b = names [j + 1].charAt (0);
                                    ascii [j] = (int) a;
                                    ascii [j + 1] = (int) b;
                                    counter = 1;
                                }
                            }
                            else if (ascii [j] == ascii [j + 1])
                            {
                                counter++;
                                j--;
                            }
                            else
                            {
                                if (j < 98)
                                {
                                    a = names [j + 1].charAt (0);
                                    b = names [j + 2].charAt (0);
                                    ascii [j + 1] = (int) a;
                                    ascii [j + 2] = (int) b;
                                    counter = 1;
                                }
                                else
                                {
                                    a = names [j].charAt (0);
                                    b = names [j + 1].charAt (0);
                                    ascii [j] = (int) a;
                                    ascii [j + 1] = (int) b;
                                    counter = 1;
                                }
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException es)
                    {

                    }
                }
            }

            textarea.setText ("");
            for (int i = 99 ; i >= 0 ; i--)
            {
                pw.println (position [i] + ", " + names [i] + ", " + rating [i]);
                textarea.append (position [i] + ", " + names [i] + ", " + rating [i] + "\n");
            }
            pw.close ();
        }
        catch (IOException q)
        {
        }
    }


    public void range ()  //when the user enters a range of scores to display
    {
        textarea.setText ("");
        int minimum, maximum;
        minimum = Integer.parseInt (min.getText ()); //gets the user inputs
        maximum = Integer.parseInt (max.getText ());

        for (int i = 0 ; i < 100 ; i++)
        {
            if (rating [i] >= minimum && rating [i] <= maximum)
            {
                textarea.append (position [i] + ", " + names [i] + ", " + rating [i] + "\n");
            }
        }

    }


    public void nameSearch ()  //method that searches for a player based on his/her name
    {
        textarea.setText ("");
        String inp;
        inp = name.getText ();

        for (int i = 0 ; i < 100 ; i++)
        {
            if (inp.equalsIgnoreCase (names [i]))
            {
                textarea.append (position [i] + ", " + names [i] + ", " + rating [i] + "\n");
            }
        }
    }


    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource () == but) //If multiplayer chess button is clicked, board is loaded
        {
            fram.setVisible (false);
            setVisible (true);
            userFlip = JOptionPane.showInputDialog ("Would you like to turn on board flips? (yes/no)");
            JOptionPane.showMessageDialog (null, "Welcome to Chess!");
            JOptionPane.showMessageDialog (null, "In this game, click on the piece you wish\nto move and then click on the tile you\nwant to move the piece to");
            JOptionPane.showMessageDialog (null, "Please refrain from misclicking, or trying to click when it isn't your turn");
            JOptionPane.showMessageDialog (null, "If you get your pawn to the opposite side,\nwait until ur next turn to promote it");
        }

        if (e.getSource () == but2) //If Top 100 Players is clicked, screen is loaded
        {
            fram.setVisible (false);
            fram1.setVisible (true);
            topPlayers ();
        }
        if(e.getSource()==back)
        {
        	fram1.setVisible (false);
        	setVisible (true);
            userFlip = JOptionPane.showInputDialog ("Would you like to turn on board flips? (yes/no)");
            JOptionPane.showMessageDialog (null, "Welcome to Chess!");
            JOptionPane.showMessageDialog (null, "In this game, click on the piece you wish\nto move and then click on the tile you\nwant to move the piece to");
            JOptionPane.showMessageDialog (null, "Please refrain from misclicking, or trying to click when it isn't your turn");
            JOptionPane.showMessageDialog (null, "If you get your pawn to the opposite side,\nwait until ur next turn to promote it");
        }
        if (e.getSource () == ratingSort) //if the user clicks on the button that sorts rating
        {
            if (sorter % 2 == 1) //if the click is odd
            {
                ratingSort ();
            }
            else //if the click is even
            {
                ratingSortRev ();
            }
            sorter++;
        }
        if (e.getSource () == nameSort) //if the user clicks on the button that sorts names alphabetically
        {
            if (sorter1 % 2 == 1) //if the click is odd
            {
                for (int i = 0 ; i < 5 ; i++)
                {
                    nameSort ();
                }
            }
            else //if the click is even
            {
                nameSortRev ();
            }
            sorter1++;
        }
        if (e.getSource () == rangeb) //if the user clicks on the button that displays a range of players based on user inputs
        {
            range ();
        }
        if (e.getSource () == nameb) //if the user clicks on the button that searches for a particaular player based on their name
        {
            nameSearch ();
        }

        //If any of the white Pawns reach the opposite end of the board, the promotion options are given to the user
        //Flags represent the promoted pieces,i.e, the promoted rook, promoted queen, etc
        //The number of the pawn is associated with the number on the promoted piece, eg, promKnightNum = 1 corresponds to the first pawn being promoted
        //When any of the pawns are promoted, their 'deaths' become 1, meaning that the pawn is longer in the game and has taken the form of a promted piece
        if (evenOdd % 2 == 0)
        {
            if (whitePawnx1 == 0 || randnum == 1) //if the pawn has reached the opposite side of the board
            {
                randnum = 1;
                if (e.getSource () == tiles [whitePawnx1] [whitePawny1] && pieceCode == 101) //if this specific pawn is clicked(x and y values are used)
                {
                    if (counter == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promKnightFlag = 1;
                            promKnightNum = 1;
                            wPawn1Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promBishFlag = 1;
                            promBishNum = 1;
                            wPawn1Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promRookFlag = 1;
                            promRookNum = 1;
                            wPawn1Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            promQueenNum = 1;
                            wPawn1Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promoKnight (whitePawnx1, whitePawny1, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promoBish (whitePawnx1, whitePawny1, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promoRook (whitePawnx1, whitePawny1, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promoQueen (whitePawnx1, whitePawny1, e);
                        }
                    }
                    counter++;
                }
            }
            if (whitePawnx2 == 0 || randnum1 == 1) //if the pawn has reached the opposite side of the board
            {
                randnum1 = 1;
                if (e.getSource () == tiles [whitePawnx2] [whitePawny2]) //if this specific pawn is clicked(x and y values are used)
                {
                    if (cnter == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equals ("knight"))
                        {
                            promKnightFlag = 1;
                            promKnightNum = 2;
                            wPawn3Death = 1;

                        }
                        else if (promo.equals ("bishop"))
                        {
                            promBishFlag = 1;
                            promBishNum = 2;
                            wPawn3Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promBishFlag = 1;
                            promRookNum = 2;
                            wPawn3Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            promQueenNum = 2;
                            wPawn3Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promoKnight (whitePawnx2, whitePawny2, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promoBish (whitePawnx2, whitePawny2, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promoRook (whitePawnx2, whitePawny2, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promoQueen (whitePawnx2, whitePawny2, e);
                        }
                    }
                    cnter++;
                }
            }
            if (whitePawnx3 == 0 || randnum4 == 1) //if the pawn has reached the opposite side of the board
            {
                randnum4 = 1;
                if (e.getSource () == tiles [whitePawnx3] [whitePawny3]) //if this specific pawn is clicked(x and y values are used)
                {
                    if (counter1 == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promKnightFlag = 1;
                            promKnightNum = 3;
                            wPawn2Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promBishFlag = 1;
                            promBishNum = 3;
                            wPawn2Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promRookFlag = 1;
                            promRookNum = 3;
                            wPawn2Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            promQueenNum = 3;
                            wPawn2Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promoKnight (whitePawnx3, whitePawny3, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promoBish (whitePawnx3, whitePawny3, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promoRook (whitePawnx3, whitePawny3, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promoQueen (whitePawnx3, whitePawny3, e);
                        }
                    }
                    counter1++;
                }
            }
            if (whitePawnx4 == 0 || randnum5 == 1) //if the pawn has reached the opposite side of the board
            {
                randnum5 = 1;
                if (e.getSource () == tiles [whitePawnx4] [whitePawny4]) //if this specific pawn is clicked(x and y values are used)
                {
                    if (counter2 == 0)
                    {   JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promKnightFlag = 1;
                            promKnightNum = 4;
                            wPawn4Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promBishFlag = 1;
                            promBishNum = 4;
                            wPawn4Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promRookFlag = 1;
                            promRookNum = 4;
                            wPawn4Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            promQueenNum = 4;
                            wPawn4Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promoKnight (whitePawnx4, whitePawny4, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promoBish (whitePawnx4, whitePawny4, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promoRook (whitePawnx4, whitePawny4, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promoQueen (whitePawnx4, whitePawny4, e);
                        }
                    }
                    counter2++;
                }
            }
            if (whitePawnx5 == 0 || randnum6 == 1) //if the pawn has reached the opposite side of the board
            {
                randnum6 = 1;
                if (e.getSource () == tiles [whitePawnx5] [whitePawny5])      //if this specific pawn is clicked(x and y values are used)
                {
                    if (counter3 == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promKnightFlag = 1;
                            promKnightNum = 5;
                            wPawn5Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promBishFlag = 1;
                            promBishNum = 5;
                            wPawn5Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promRookFlag = 1;
                            promRookNum = 5;
                            wPawn5Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            promQueenNum = 5;
                            wPawn5Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promoKnight (whitePawnx5, whitePawny5, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promoBish (whitePawnx5, whitePawny5, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promoRook (whitePawnx5, whitePawny5, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promoQueen (whitePawnx5, whitePawny5, e);
                        }
                    }
                    counter3++;
                }
            }
            if (whitePawnx6 == 0 || randnum7 == 1) //if the pawn has reached the opposite side of the board
            {
                randnum7 = 1;
                if (e.getSource () == tiles [whitePawnx6] [whitePawny6]) //if this specific pawn is clicked(x and y values are used)
                {
                    if (counter4 == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promKnightFlag = 1;
                            promKnightNum = 6;
                            wPawn6Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promBishFlag = 1;
                            promBishNum = 6;
                            wPawn6Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promRookFlag = 1;
                            promRookNum = 6;
                            wPawn6Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            promQueenNum = 6;
                            wPawn6Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promoKnight (whitePawnx6, whitePawny6, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promoBish (whitePawnx6, whitePawny6, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promoRook (whitePawnx6, whitePawny6, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promoQueen (whitePawnx6, whitePawny6, e);
                        }
                    }
                    counter4++;
                }
            }
            if (whitePawnx7 == 0 || randnum8 == 1) //if the pawn has reached the opposite side of the board
            {
                randnum8 = 1;
                if (e.getSource () == tiles [whitePawnx7] [whitePawny7]) //if this specific pawn is clicked(x and y values are used)
                {
                    if (counter5 == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promKnightFlag = 1;
                            promKnightNum = 7;
                            wPawn7Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promBishFlag = 1;
                            promBishNum = 7;
                            wPawn7Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promRookFlag = 1;
                            promRookNum = 7;
                            wPawn7Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            promQueenNum = 7;
                            wPawn7Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promoKnight (whitePawnx7, whitePawny7, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promoBish (whitePawnx7, whitePawny7, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promoRook (whitePawnx7, whitePawny7, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promoQueen (whitePawnx7, whitePawny7, e);
                        }
                    }
                    counter5++;
                }
            }
            if (whitePawnx8 == 0 || randnum9 == 1) //if the pawn has reached the opposite side of the board
            {
                randnum9 = 1;
                if (e.getSource () == tiles [whitePawnx8] [whitePawny8]) //if this specific pawn is clicked(x and y values are used)
                {
                    if (counter6 == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promKnightFlag = 1;
                            promKnightNum = 8;
                            wPawn8Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promBishFlag = 1;
                            promBishNum = 8;
                            wPawn8Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promRookFlag = 1;
                            promRookNum = 8;
                            wPawn8Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            promQueenNum = 8;
                            wPawn8Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            promoKnight (whitePawnx8, whitePawny8, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            promoBish (whitePawnx8, whitePawny8, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promoRook (whitePawnx8, whitePawny8, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promoQueen (whitePawnx8, whitePawny8, e);
                        }
                    }
                    counter6++;
                }
            }


            //same thing for all black pawns
            if (blackPawnx1 == 7 || randnum2 == 1) //if the pawn has reached the opposite side of the board
            {
                randnum2 = 1;
                if (e.getSource () == tiles [blackPawnx1] [blackPawny1]) //if this specific pawn is clicked(x and y values are used)
                {
                    if (cnter1 == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equals ("knight"))
                        {
                            promKnightFlag = 1;
                            bPromKnightNum = 1;
                            bPawn1Death = 1;
                        }
                        else if (promo.equals ("bishop"))
                        {
                            promBishFlag = 1;
                            bPromBishNum = 1;
                            bPawn1Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promBishFlag = 1;
                            bPromRookNum = 1;
                            bPawn1Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            bPromQueenNum = 1;
                            bPawn1Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            bPromoKnight (blackPawnx1, blackPawny1, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            bPromoBish (blackPawnx1, blackPawny1, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            bPromoRook (blackPawnx1, blackPawny1, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            bPromoQueen (blackPawnx1, blackPawny1, e);
                        }
                    }
                    cnter1++;
                }
            }
            if (blackPawnx2 == 7 || randnum3 == 1) //if the pawn has reached the opposite side of the board
            {
                randnum3 = 1;
                if (e.getSource () == tiles [blackPawnx2] [blackPawny2]) //if this specific pawn is clicked(x and y values are used)
                {
                    if (cnter2 == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equals ("knight"))
                        {
                            promKnightFlag = 1;
                            bPromKnightNum = 2;
                            bPawn2Death = 1;
                        }
                        else if (promo.equals ("bishop"))
                        {
                            promBishFlag = 1;
                            bPromBishNum = 2;
                            bPawn2Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promBishFlag = 1;
                            bPromRookNum = 2;
                            bPawn2Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            bPromQueenNum = 2;
                            bPawn2Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            bPromoKnight (blackPawnx2, blackPawny2, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            bPromoBish (blackPawnx2, blackPawny2, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            bPromoRook (blackPawnx2, blackPawny2, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            bPromoQueen (blackPawnx2, blackPawny2, e);
                        }
                    }
                    cnter2++;
                }
            }
            if (blackPawnx3 == 7 || bRand1 == 1)
            {
                bRand1 = 1;
                if (e.getSource () == tiles [blackPawnx3] [blackPawny3]) //if the pawn has reached the opposite side of the board
                {
                    if (bCount1 == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equals ("knight"))
                        {
                            promKnightFlag = 1;
                            bPromKnightNum = 3;
                            bPawn3Death = 1;
                        }
                        else if (promo.equals ("bishop"))
                        {
                            promBishFlag = 1;
                            bPromBishNum = 3;
                            bPawn3Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promBishFlag = 1;
                            bPromRookNum = 3;
                            bPawn3Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            bPromQueenNum = 3;
                            bPawn3Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            bPromoKnight (blackPawnx3, blackPawny3, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            bPromoBish (blackPawnx3, blackPawny3, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            bPromoRook (blackPawnx3, blackPawny3, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            bPromoQueen (blackPawnx3, blackPawny3, e);
                        }
                    }
                    bCount1++;
                }
            }
            if (blackPawnx4 == 7 || bRand2 == 1) //if the pawn has reached the opposite side of the board
            {
                bRand2 = 1;
                if (e.getSource () == tiles [blackPawnx4] [blackPawny4]) //if this specific pawn is clicked(x and y values are used)
                {
                    if (bCount2 == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equals ("knight"))
                        {
                            promKnightFlag = 1;
                            bPromKnightNum = 4;
                            bPawn4Death = 1;
                        }
                        else if (promo.equals ("bishop"))
                        {
                            promBishFlag = 1;
                            bPromBishNum = 4;
                            bPawn4Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promBishFlag = 1;
                            bPromRookNum = 4;
                            bPawn4Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            bPromQueenNum = 4;
                            bPawn4Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            bPromoKnight (blackPawnx4, blackPawny4, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            bPromoBish (blackPawnx4, blackPawny4, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            bPromoRook (blackPawnx4, blackPawny4, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            bPromoQueen (blackPawnx4, blackPawny4, e);
                        }
                    }
                    bCount2++;
                }
            }
            if (blackPawnx5 == 7 || bRand3 == 1) //if the pawn has reached the opposite side of the board
            {
                bRand3 = 1;
                if (e.getSource () == tiles [blackPawnx5] [blackPawny5]) //if this specific pawn is clicked(x and y values are used)
                {
                    if (bCount3 == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equals ("knight"))
                        {
                            promKnightFlag = 1;
                            bPromKnightNum = 5;
                            bPawn5Death = 1;
                        }
                        else if (promo.equals ("bishop"))
                        {
                            promBishFlag = 1;
                            bPromBishNum = 5;
                            bPawn5Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promBishFlag = 1;
                            bPromRookNum = 5;
                            bPawn5Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            bPromQueenNum = 5;
                            bPawn5Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            bPromoKnight (blackPawnx5, blackPawny5, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            bPromoBish (blackPawnx5, blackPawny5, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            bPromoRook (blackPawnx5, blackPawny5, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            bPromoQueen (blackPawnx5, blackPawny5, e);
                        }
                    }
                    bCount3++;
                }
            }
            if (blackPawnx6 == 7 || bRand4 == 1) //if the pawn has reached the opposite side of the board
            {
                bRand4 = 1;
                if (e.getSource () == tiles [blackPawnx6] [blackPawny6]) //if this specific pawn is clicked(x and y values are used)
                {
                    if (bCount4 == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equals ("knight"))
                        {
                            promKnightFlag = 1;
                            bPromKnightNum = 6;
                            bPawn6Death = 1;
                        }
                        else if (promo.equals ("bishop"))
                        {
                            promBishFlag = 1;
                            bPromBishNum = 6;
                            bPawn6Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promBishFlag = 1;
                            bPromRookNum = 6;
                            bPawn6Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            bPromQueenNum = 6;
                            bPawn6Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            bPromoKnight (blackPawnx6, blackPawny6, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            bPromoBish (blackPawnx6, blackPawny6, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            bPromoRook (blackPawnx6, blackPawny6, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            bPromoQueen (blackPawnx6, blackPawny6, e);
                        }
                    }
                    bCount4++;
                }
            }
            if (blackPawnx7 == 7 || bRand5 == 1) //if the pawn has reached the opposite side of the board
            {
                bRand5 = 1;
                if (e.getSource () == tiles [blackPawnx7] [blackPawny7]) //if this specific pawn is clicked(x and y values are used)
                {
                    if (bCount5 == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nClick on the pawn to activiate the promotion options\nThen click on the piece twice to initialize the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equals ("knight"))
                        {
                            promKnightFlag = 1;
                            bPromKnightNum = 7;
                            bPawn7Death = 1;
                        }
                        else if (promo.equals ("bishop"))
                        {
                            promBishFlag = 1;
                            bPromBishNum = 7;
                            bPawn7Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promBishFlag = 1;
                            bPromRookNum = 7;
                            bPawn7Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            bPromQueenNum = 7;
                            bPawn7Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            bPromoKnight (blackPawnx7, blackPawny7, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            bPromoBish (blackPawnx7, blackPawny7, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            bPromoRook (blackPawnx7, blackPawny7, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            bPromoQueen (blackPawnx7, blackPawny7, e);
                        }
                    }
                    bCount5++;
                }
            }
            if (blackPawnx8 == 7 || bRand6 == 1) //if the pawn has reached the opposite side of the board
            {
                bRand6 = 1;
                if (e.getSource () == tiles [blackPawnx8] [blackPawny8]) //if this specific pawn is clicked(x and y values are used)
                {
                    if (bCount1 == 0)
                    {
                        JOptionPane.showMessageDialog (null, "You have the ability to promote your pawn!\nclick on the piece twice to move the piece");
                        promo = JOptionPane.showInputDialog ("Type in the name of the piece you want to become");
                        if (promo.equals ("knight"))
                        {
                            promKnightFlag = 1;
                            bPromKnightNum = 8;
                            bPawn8Death = 1;
                        }
                        else if (promo.equals ("bishop"))
                        {
                            promBishFlag = 1;
                            bPromBishNum = 8;
                            bPawn8Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            promBishFlag = 1;
                            bPromRookNum = 8;
                            bPawn8Death = 1;
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            promQueenFlag = 1;
                            bPromQueenNum = 8;
                            bPawn8Death = 1;
                        }
                    }
                    else
                    {
                        if (promo.equalsIgnoreCase ("knight"))
                        {
                            bPromoKnight (blackPawnx8, blackPawny8, e);
                        }
                        else if (promo.equalsIgnoreCase ("bishop"))
                        {
                            bPromoBish (blackPawnx8, blackPawny8, e);
                        }
                        else if (promo.equalsIgnoreCase ("rook"))
                        {
                            bPromoRook (blackPawnx8, blackPawny8, e);
                        }
                        else if (promo.equalsIgnoreCase ("queen"))
                        {
                            bPromoQueen (blackPawnx8, blackPawny8, e);
                        }
                    }
                    bCount1++;
                }
            }
        }
        else
        {

        }
        evenOdd++;

        //a 'pieceCode' is assigned to every piece. This distinguishes each piece. The pieceCodes are assigned when the user clicks on a piece
        if (count % 2 == 0) //This allows the user to click on the piece they wish to move. It happens every odd time(i.e 1, 3, 5)
        {
            if (whoMoves % 2 == 0)
            {
                if (e.getSource () == tiles [whiteKingy] [whiteKingx])//If the user clicks on the white King
                {
                    if (wkingFirstMove == 0)
                    {
                        whiteCastle ();
                    }
                    wkingFirstMove++;
                    kingX = whiteKingy;
                    kingY = whiteKingx;
                    pieceCode = 0;
                    colorDet = 0;
                    king (kingX, kingY); //This method highlights the possible moves for the king
                }
                else if (e.getSource () == tiles [whiteKnightx] [whiteKnighty] && wKnight1Death != 1)//If the user clicks on the white knight
                {
                    knightX = whiteKnightx;
                    knightY = whiteKnighty;
                    pieceCode = 6;
                    colorDet = 0;
                    knight (knightX, knightY); //This method highlights the possible moves for the knight
                    promKnightFlag = 0;
                }
                else if (e.getSource () == tiles [whiteKnightx1] [whiteKnighty1] && wKnight2Death != 1)//If the user clicks on the white knight
                {
                    knightX = whiteKnightx1;
                    knightY = whiteKnighty1;
                    colorDet = 0;
                    pieceCode = 66;
                    knight (knightX, knightY); //This method highlights the possible moves for the knight
                }
                else if (e.getSource () == tiles [whiteRookx] [whiteRooky] && wRook1Death != 1)//If the user clicks on the white rook
                {
                    rookX = whiteRookx;
                    rookY = whiteRooky;
                    pieceCode = 2;
                    colorDet = 0;
                    rook (rookX, rookY); //This method highlights the possible moves for the rook
                    promRookFlag = 0;
                    wRookFM++;
                }
                else if (e.getSource () == tiles [whitePawnx1] [whitePawny1] && wPawn1Death != 1)//If the user clicks on the white pawn
                {
                    if (enPassx == whitePawnx1 && enPassy == whitePawny1)
                    {
                        enPass = 0;
                    }
                    pawnX = whitePawnx1;
                    pawnY = whitePawny1;

                    if (pawncnt1 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    pawncnt1++;
                    colorDet = 0;
                    pawn (pawnX, pawnY, firstMove); //This method highlights the possible moves for the pawn
                    pieceCode = 101;
                }
                else if (e.getSource () == tiles [whitePawnx2] [whitePawny2] && wPawn3Death != 1)//If the user clicks on the white pawn
                {
                    if (enPassx == whitePawnx2 && enPassy == whitePawny2)
                    {
                        enPass = 0;
                    }
                    pawnX = whitePawnx2;
                    pawnY = whitePawny2;
                    if (pawncnt2 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    pawncnt2++;
                    colorDet = 0;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 102;
                }
                else if (e.getSource () == tiles [whitePawnx3] [whitePawny3] && wPawn2Death != 1)//If the user clicks on the white pawn
                {
                    if (enPassx == whitePawnx3 && enPassy == whitePawny3)
                    {
                        enPass = 0;
                    }
                    pawnX = whitePawnx3;
                    pawnY = whitePawny3;
                    if (pawncnt31 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    pawncnt31++;
                    colorDet = 0;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 103;
                }
                else if (e.getSource () == tiles [whitePawnx4] [whitePawny4] && wPawn4Death != 1)//If the user clicks on the white pawn
                {
                    if (enPassx == whitePawnx4 && enPassy == whitePawny4)
                    {
                        enPass = 0;
                    }
                    pawnX = whitePawnx4;
                    pawnY = whitePawny4;
                    if (pawncnt4 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    pawncnt4++;
                    colorDet = 0;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 104;
                }
                else if (e.getSource () == tiles [whitePawnx5] [whitePawny5] && wPawn5Death != 1)//If the user clicks on the white pawn
                {
                    if (enPassx == whitePawnx5 && enPassy == whitePawny5)
                    {
                        enPass = 0;
                    }
                    pawnX = whitePawnx5;
                    pawnY = whitePawny5;
                    if (pawncnt5 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    pawncnt5++;
                    colorDet = 0;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 105;
                }
                else if (e.getSource () == tiles [whitePawnx6] [whitePawny6] && wPawn6Death != 1)//If the user clicks on the white pawn
                {
                    if (enPassx == whitePawnx6 && enPassy == whitePawny6)
                    {
                        enPass = 0;
                    }
                    pawnX = whitePawnx6;
                    pawnY = whitePawny6;
                    if (pawncnt6 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    pawncnt6++;
                    colorDet = 0;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 106;
                }
                else if (e.getSource () == tiles [whitePawnx7] [whitePawny7] && wPawn7Death != 1)//If the user clicks on the white pawn
                {
                    if (enPassx == whitePawnx7 && enPassy == whitePawny7)
                    {
                        enPass = 0;
                    }
                    pawnX = whitePawnx7;
                    pawnY = whitePawny7;
                    if (pawncnt7 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    pawncnt7++;
                    colorDet = 0;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 107;
                }
                else if (e.getSource () == tiles [whitePawnx8] [whitePawny8] && wPawn8Death != 1)//If the user clicks on the white pawn
                {
                    if (enPassx == whitePawnx8 && enPassy == whitePawny8)
                    {
                        enPass = 0;
                    }
                    pawnX = whitePawnx8;
                    pawnY = whitePawny8;
                    if (pawncnt8 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    pawncnt8++;
                    colorDet = 0;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 108;
                }
                else if (e.getSource () == tiles [whiteQueenx] [whiteQueeny] && wQueen1Death != 1)//If the user clicks on the white queen
                {
                    bishX = whiteQueenx;
                    bishY = whiteQueeny;
                    pieceCode = 8;
                    bishop (bishX, bishY); //Since the queen behaves both like a rook and a bishop, both of the methods are called
                    rookX = whiteQueenx;
                    rookY = whiteQueeny;
                    rook (rookX, rookY);
                    colorDet = 0;
                    promQueenFlag = 0;
                }
                else if (e.getSource () == tiles [whiteRookx1] [whiteRooky1] && wRook2Death != 1)//If the user clicks on the white rook
                {
                    rookX = whiteRookx1;
                    rookY = whiteRooky1;
                    pieceCode = 22;
                    colorDet = 0;
                    rook (rookX, rookY); //This method highlights the possible moves for the rook
                    wRook1FM++;
                }
                else if (e.getSource () == tiles [whiteBishx] [whiteBishy] && wBish1Death != 1)//If the user clicks on the white bishop
                {
                    bishX = whiteBishx;
                    bishY = whiteBishy;
                    pieceCode = 4;
                    colorDet = 0;
                    bishop (bishX, bishY); //This method highlights the possible moves for the bishop
                    promBishFlag = 0;
                }
                else if (e.getSource () == tiles [whiteBishx1] [whiteBishy1] && wBish2Death != 1)//If the user clicks on the white bishop
                {
                    bishX = whiteBishx1;
                    bishY = whiteBishy1;
                    pieceCode = 44;
                    colorDet = 0;
                    bishop (bishX, bishY); //This method highlights the possible moves for the bishop
                }
            }
            else
            {
                if (e.getSource () == tiles [blackKnightx] [blackKnighty] && bKnight1Death != 1)//If the user clicks on the black knight
                {
                    knightX = blackKnightx;
                    knightY = blackKnighty;
                    pieceCode = 1;
                    colorDet = 1;
                    knight (knightX, knightY); //This method highlights the possible moves for the knight
                    promKnightFlag = 0;
                }
                else if (e.getSource () == tiles [blackKnightx1] [blackKnighty1] && bKnight2Death != 1)//If the user clicks on the black knight
                {
                    knightX = blackKnightx1;
                    knightY = blackKnighty1;
                    pieceCode = 11;
                    colorDet = 1;
                    knight (knightX, knightY); //This method highlights the possible moves for the knight
                }
                else if (e.getSource () == tiles [blackKingx] [blackKingy])
                {
                    if (bkingFirstMove == 0)
                    {
                        blackCastle ();
                    }
                    bkingFirstMove++;
                    kingX = blackKingx;
                    kingY = blackKingy;
                    pieceCode = 5;
                    colorDet = 1;
                    king (kingX, kingY); //This method highlights the possible moves for the king
                }
                else if (e.getSource () == tiles [blackPawnx1] [blackPawny1] && bPawn1Death != 1)//If the user clicks on the black pawn
                {
                    if (enPassx == blackPawnx1 && enPassy == blackPawny1)
                    {
                        enPass = 0;
                    }
                    pawnX = blackPawnx1;
                    pawnY = blackPawny1;
                    if (pwncount23 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    pwncount23++;
                    colorDet = 1;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 201;
                }
                else if (e.getSource () == tiles [blackPawnx2] [blackPawny2] && bPawn2Death != 1)//If the user clicks on the black pawn
                {
                    if (enPassx == blackPawnx2 && enPassy == blackPawny2)
                    {
                        enPass = 0;
                    }
                    pawnX = blackPawnx2;
                    pawnY = blackPawny2;
                    if (pawncnt3 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    pawncnt3++;
                    colorDet = 1;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 202;
                }
                else if (e.getSource () == tiles [blackPawnx3] [blackPawny3] && bPawn3Death != 1)//If the user clicks on the black pawn
                {
                    if (enPassx == blackPawnx3 && enPassy == blackPawny3)
                    {
                        enPass = 0;
                    }
                    pawnX = blackPawnx3;
                    pawnY = blackPawny3;
                    if (bPawncnt1 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    bPawncnt1++;
                    colorDet = 1;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 203;
                }
                else if (e.getSource () == tiles [blackPawnx4] [blackPawny4] && bPawn4Death != 1)//If the user clicks on the black pawn
                {
                    if (enPassx == blackPawnx4 && enPassy == blackPawny4)
                    {
                        enPass = 0;
                    }
                    pawnX = blackPawnx4;
                    pawnY = blackPawny4;
                    if (bPawncnt2 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    bPawncnt2++;
                    colorDet = 1;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 204;
                }
                else if (e.getSource () == tiles [blackPawnx5] [blackPawny5] && bPawn5Death != 1)//If the user clicks on the black pawn
                {
                    if (enPassx == blackPawnx5 && enPassy == blackPawny5)
                    {
                        enPass = 0;
                    }
                    pawnX = blackPawnx5;
                    pawnY = blackPawny5;
                    if (bPawncnt3 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    bPawncnt3++;
                    colorDet = 1;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 205;
                }
                else if (e.getSource () == tiles [blackPawnx6] [blackPawny6] && bPawn6Death != 1)//If the user clicks on the black pawn
                {
                    if (enPassx == blackPawnx6 && enPassy == blackPawny6)
                    {
                        enPass = 0;
                    }
                    pawnX = blackPawnx6;
                    pawnY = blackPawny6;
                    if (bPawncnt4 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    bPawncnt4++;
                    colorDet = 1;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 206;
                }
                else if (e.getSource () == tiles [blackPawnx7] [blackPawny7] && bPawn7Death != 1)//If the user clicks on the black pawn
                {
                    if (enPassx == blackPawnx7 && enPassy == blackPawny7)
                    {
                        enPass = 0;
                    }
                    pawnX = blackPawnx7;
                    pawnY = blackPawny7;
                    if (bPawncnt5 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    bPawncnt5++;
                    colorDet = 1;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 207;
                }
                else if (e.getSource () == tiles [blackPawnx8] [blackPawny8] && bPawn8Death != 1)//If the user clicks on the black pawn
                {
                    if (enPassx == blackPawnx8 && enPassy == blackPawny8)
                    {
                        enPass = 0;
                    }
                    pawnX = blackPawnx8;
                    pawnY = blackPawny8;
                    if (bPawncnt6 == 0)
                    {
                        firstMove = 0;
                    }
                    else
                    {
                        firstMove = 1;
                    }
                    bPawncnt6++;
                    colorDet = 1;
                    pawn (pawnX, pawnY, firstMove);
                    pieceCode = 208;
                }
                else if (e.getSource () == tiles [blackRookx] [blackRooky] && bRook1Death != 1)//If the user clicks on the black rook
                {
                    rookX = blackRookx;
                    rookY = blackRooky;
                    pieceCode = 3;
                    colorDet = 1;
                    rook (rookX, rookY); //This method highlights the possible moves for the rook
                    promRookFlag = 0;
                    bRookFM++;
                }
                else if (e.getSource () == tiles [blackRookx1] [blackRooky1] && bRook2Death != 1)//If the user clicks on the black rook
                {
                    rookX = blackRookx1;
                    rookY = blackRooky1;
                    pieceCode = 33;
                    colorDet = 1;
                    rook (rookX, rookY); //This method highlights the possible moves for the rook
                    bRook1FM++;
                }
                else if (e.getSource () == tiles [blackBishx1] [blackBishy1] && bBish2Death != 1)//If the user clicks on the black bishop
                {
                    bishX = blackBishx1;
                    bishY = blackBishy1;
                    pieceCode = 77;
                    colorDet = 1;
                    bishop (bishX, bishY); //This method highlights the possible moves for the bishop
                }

                else if (e.getSource () == tiles [blackQueenx] [blackQueeny] && bQueen1Death != 1)//If the user clicks on the black queen
                {
                    bishX = blackQueenx;
                    bishY = blackQueeny;
                    pieceCode = 9;
                    bishop (bishX, bishY);
                    rookX = blackQueenx;
                    rookY = blackQueeny;
                    rook (rookX, rookY);
                    colorDet = 1;
                    promQueenFlag = 0;
                }
                else if (e.getSource () == tiles [blackBishx] [blackBishy] && bBish1Death != 1)//If the user clicks on the black bishop
                {
                    bishX = blackBishx;
                    bishY = blackBishy;
                    pieceCode = 7;
                    colorDet = 1;
                    bishop (bishX, bishY); //This method highlights the possible moves for the bishop
                    promBishFlag = 0;
                }
            }
            whoMoves++;
        }
        else
        {
            actionPerformed1 (e, pieceCode); //this method is called every 2nd time, ie when the user clicks on the square they want to move to

            if (userFlip.equals ("yes")) //if the user wants the option of board flips or not
            {
                flip = var;
                if (flip % 2 == 0)
                {
                    for (int i = 7 ; i >= 0 ; i--)
                    {
                        for (int j = 7 ; j >= 0 ; j--)
                        {
                            frame.add (tiles [i] [j]); //displays the tiles in rveerse order to simulate a 'flip'
                        }
                    }
                }
                else if (flip % 2 == 1)
                {
                    for (int i = 0 ; i < 8 ; i++)
                    {
                        for (int j = 0 ; j < 8 ; j++)
                        {
                            frame.add (tiles [i] [j]);
                        }
                    }
                }
                var++;
            }
            //though these are already created and stored, after every move these values are updated
            whitePAWNXs [0] = whitePawnx1;
            whitePAWNXs [1] = whitePawnx2;
            whitePAWNXs [2] = whitePawnx3;
            whitePAWNXs [3] = whitePawnx4;
            whitePAWNXs [4] = whitePawnx5;
            whitePAWNXs [5] = whitePawnx6;
            whitePAWNXs [6] = whitePawnx7;
            whitePAWNXs [7] = whitePawnx8;

            whitePAWNYs [0] = whitePawny1;
            whitePAWNYs [1] = whitePawny2;
            whitePAWNYs [2] = whitePawny3;
            whitePAWNYs [3] = whitePawny4;
            whitePAWNYs [4] = whitePawny5;
            whitePAWNYs [5] = whitePawny6;
            whitePAWNYs [6] = whitePawny7;
            whitePAWNYs [7] = whitePawny8;

            blackPAWNXs [0] = blackPawnx1;
            blackPAWNXs [1] = blackPawnx2;
            blackPAWNXs [2] = blackPawnx3;
            blackPAWNXs [3] = blackPawnx4;
            blackPAWNXs [4] = blackPawnx5;
            blackPAWNXs [5] = blackPawnx6;
            blackPAWNXs [6] = blackPawnx7;
            blackPAWNXs [7] = blackPawnx8;

            blackPAWNYs [0] = blackPawny1;
            blackPAWNYs [1] = blackPawny2;
            blackPAWNYs [2] = blackPawny3;
            blackPAWNYs [3] = blackPawny4;
            blackPAWNYs [4] = blackPawny5;
            blackPAWNYs [5] = blackPawny6;
            blackPAWNYs [6] = blackPawny7;
            blackPAWNYs [7] = blackPawny8;

            wPawnDeaths [0] = wPawn1Death;
            wPawnDeaths [1] = wPawn3Death;
            wPawnDeaths [2] = wPawn2Death;
            wPawnDeaths [3] = wPawn4Death;
            wPawnDeaths [4] = wPawn5Death;
            wPawnDeaths [5] = wPawn6Death;
            wPawnDeaths [6] = wPawn7Death;
            wPawnDeaths [7] = wPawn8Death;

            bPawnDeaths [0] = bPawn1Death;
            bPawnDeaths [1] = bPawn2Death;
            bPawnDeaths [2] = bPawn3Death;
            bPawnDeaths [3] = bPawn4Death;
            bPawnDeaths [4] = bPawn5Death;
            bPawnDeaths [5] = bPawn6Death;
            bPawnDeaths [6] = bPawn7Death;
            bPawnDeaths [7] = bPawn8Death;
        }
        count++;
    }


    public void whiteCastle ()  //when the white king is ready to castle
    {
        if (wRookFM == 0)
        {
            //if the rook hasn't moved yet, the castle continues
            if (tiles [whiteKingy] [whiteKingx + 1].getIcon () == null && tiles [whiteKingy] [whiteKingx + 2].getIcon () == null)
            {
                tiles [whiteKingy] [whiteKingx + 2].setBackground (Color.green);
                wRookCastle = 1;
            }
        }

        if (wRook1FM == 0)
        {
            //if the rook hasn't moved yet, the castle continues
            if (tiles [whiteKingy] [whiteKingx - 1].getIcon () == null && tiles [whiteKingy] [whiteKingx - 2].getIcon () == null && tiles [whiteKingy] [whiteKingx - 3].getIcon () == null)
            {
                tiles [whiteKingy] [whiteKingx - 2].setBackground (Color.green);
                tiles [whiteKingy] [whiteKingx - 3].setBackground (Color.green);
                wRook1Castle = 1;
            }
        }
    }


    public void blackCastle ()  //when the black king is ready to castle
    {
        if (bRookFM == 0)
        {
            //if the black rook hasn't moved yet, the castle continues
            if (tiles [blackKingx] [blackKingy + 1].getIcon () == null && tiles [blackKingx] [blackKingy + 2].getIcon () == null)
            {
                tiles [blackKingx] [blackKingy + 2].setBackground (Color.green);
                bRookCastle = 1;
            }
        }

        if (bRook1FM == 0)
        {
            //if the black rook hasn't moved yet, the castle continues
            if (tiles [blackKingx] [blackKingy - 1].getIcon () == null && tiles [blackKingx] [blackKingy - 2].getIcon () == null && tiles [blackKingx] [blackKingy - 3].getIcon () == null)
            {
                tiles [blackKingx] [blackKingy - 2].setBackground (Color.green);
                tiles [blackKingx] [blackKingy - 3].setBackground (Color.green);
                bRook1Castle = 1;
            }
        }
    }


    public void pawn (int pawnX, int pawnY, int firstMove)  //This method highlights the possible moves for the pawn
    {
        try
        {
            if (colorDet == 0)//for the white pawns
            {
                if (firstMove == 0)//If the pawn hasn't move yet, it has the ability to move two spaces
                {
                    if (tiles [pawnX - 1] [pawnY].getIcon () == null)
                    {
                        tiles [pawnX - 1] [pawnY].setBackground (Color.green);
                    }
                    if (tiles [pawnX - 2] [pawnY].getIcon () == null)
                    {
                        tiles [pawnX - 2] [pawnY].setBackground (Color.green);
                    }
                }
                else
                {
                    if (pawnX == 0)
                    {
                    }
                    else
                    {
                        if (tiles [pawnX - 1] [pawnY].getIcon () == null)
                        {
                            tiles [pawnX - 1] [pawnY].setBackground (Color.green);
                        }
                    }
                }
                if (pawnX != 0)//if it has reached the end of the board, no squares can be highlighted as promotions kick in
                {
                    if (pawnY == 0)
                    {
                        if (tiles [pawnX - 1] [pawnY + 1].getIcon () == blackPawn || tiles [pawnX - 1] [pawnY + 1].getIcon () == blackKnight || tiles [pawnX - 1] [pawnY + 1].getIcon () == blackRook || tiles [pawnX - 1] [pawnY + 1].getIcon () == blackBish || tiles [pawnX - 1] [pawnY + 1].getIcon () == blackQueen)
                        {
                            tiles [pawnX - 1] [pawnY + 1].setBackground (Color.green);
                        }
                    }
                    else if (pawnY == 7)
                    {
                        if (tiles [pawnX - 1] [pawnY - 1].getIcon () == blackPawn || tiles [pawnX - 1] [pawnY - 1].getIcon () == blackKnight || tiles [pawnX - 1] [pawnY - 1].getIcon () == blackRook || tiles [pawnX - 1] [pawnY - 1].getIcon () == blackBish || tiles [pawnX - 1] [pawnY - 1].getIcon () == blackQueen)
                        {
                            tiles [pawnX - 1] [pawnY - 1].setBackground (Color.green);
                        }
                    }
                    else
                    {
                        if (tiles [pawnX - 1] [pawnY - 1].getIcon () == blackPawn || tiles [pawnX - 1] [pawnY - 1].getIcon () == blackKnight || tiles [pawnX - 1] [pawnY - 1].getIcon () == blackRook || tiles [pawnX - 1] [pawnY - 1].getIcon () == blackBish || tiles [pawnX - 1] [pawnY - 1].getIcon () == blackQueen)
                        {
                            tiles [pawnX - 1] [pawnY - 1].setBackground (Color.green);
                        }

                        if (tiles [pawnX - 1] [pawnY + 1].getIcon () == blackPawn || tiles [pawnX - 1] [pawnY + 1].getIcon () == blackKnight || tiles [pawnX - 1] [pawnY + 1].getIcon () == blackRook || tiles [pawnX - 1] [pawnY + 1].getIcon () == blackBish || tiles [pawnX - 1] [pawnY + 1].getIcon () == blackQueen)
                        {
                            tiles [pawnX - 1] [pawnY + 1].setBackground (Color.green);
                        }
                    }
                }

                if (enPass == 1)
                {
                    for (int i = 0 ; i < 8 ; i++)
                    {
                        if ((enPassy == pawnY + 1 || enPassy == pawnY - 1) && enPassx == pawnX)
                        {
                            tiles [enPassx - 1] [enPassy].setBackground (Color.green);
                        }
                    }
                }
            }
            else//for the black pawns
            {
                if (firstMove == 0)
                {
                    if (tiles [pawnX + 1] [pawnY].getIcon () == null)
                    {
                        tiles [pawnX + 1] [pawnY].setBackground (Color.green);
                    }
                    if (tiles [pawnX + 2] [pawnY].getIcon () == null)
                    {
                        tiles [pawnX + 2] [pawnY].setBackground (Color.green);
                    }
                }
                else
                {
                    if (pawnX == 7)
                    {
                    }
                    else
                    {
                        if (tiles [pawnX + 1] [pawnY].getIcon () == null)
                        {
                            tiles [pawnX + 1] [pawnY].setBackground (Color.green);
                        }
                    }
                }
                if (pawnX != 7)
                {

                    if (pawnY == 0)
                    {
                        if (tiles [pawnX + 1] [pawnY + 1].getIcon () == whitePawn || tiles [pawnX + 1] [pawnY + 1].getIcon () == whiteKnight || tiles [pawnX + 1] [pawnY + 1].getIcon () == whiteRook || tiles [pawnX + 1] [pawnY + 1].getIcon () == whiteBish || tiles [pawnX + 1] [pawnY + 1].getIcon () == whiteQueen)
                        {
                            tiles [pawnX + 1] [pawnY + 1].setBackground (Color.green);
                        }
                    }
                    if (pawnY == 7)
                    {
                        if (tiles [pawnX + 1] [pawnY - 1].getIcon () == whitePawn || tiles [pawnX + 1] [pawnY - 1].getIcon () == whiteKnight || tiles [pawnX + 1] [pawnY - 1].getIcon () == whiteRook || tiles [pawnX + 1] [pawnY - 1].getIcon () == whiteBish || tiles [pawnX + 1] [pawnY - 1].getIcon () == whiteQueen)
                        {
                            tiles [pawnX + 1] [pawnY - 1].setBackground (Color.green);
                        }
                    }
                    else
                    {
                        if (tiles [pawnX + 1] [pawnY - 1].getIcon () == whitePawn || tiles [pawnX + 1] [pawnY - 1].getIcon () == whiteKnight || tiles [pawnX + 1] [pawnY - 1].getIcon () == whiteRook || tiles [pawnX + 1] [pawnY - 1].getIcon () == whiteBish || tiles [pawnX + 1] [pawnY - 1].getIcon () == whiteQueen)
                        {
                            tiles [pawnX + 1] [pawnY - 1].setBackground (Color.green);
                        }

                        if (tiles [pawnX + 1] [pawnY + 1].getIcon () == whitePawn || tiles [pawnX + 1] [pawnY + 1].getIcon () == whiteKnight || tiles [pawnX + 1] [pawnY + 1].getIcon () == whiteRook || tiles [pawnX + 1] [pawnY + 1].getIcon () == whiteBish || tiles [pawnX + 1] [pawnY + 1].getIcon () == whiteQueen)
                        {
                            tiles [pawnX + 1] [pawnY + 1].setBackground (Color.green);
                        }
                    }
                }
                if (enPass == 1)
                {
                    for (int i = 0 ; i < 8 ; i++)
                    {
                        if ((enPassy == pawnY + 1 || enPassy == pawnY - 1) && enPassx == pawnX) //move highlights for en passants
                        {
                            tiles [enPassx + 1] [enPassy].setBackground (Color.green);
                        }
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException es)
        {
        }
    }


    public void king (int kingX, int kingY)  //This method highlights the possible moves for the king
    {
        if ((kingX + 1 >= 8) && (kingY + 1 >= 8))
        {
            tiles [kingX - 1] [kingY].setBackground (Color.green);
            tiles [kingX] [kingY - 1].setBackground (Color.green);
            tiles [kingX - 1] [kingY - 1].setBackground (Color.green);
        }
        else if ((kingX - 1 < 0) && (kingY + 1 >= 8))
        {
            tiles [kingX + 1] [kingY].setBackground (Color.green);
            tiles [kingX] [kingY - 1].setBackground (Color.green);
            tiles [kingX + 1] [kingY - 1].setBackground (Color.green);
        }
        else if ((kingX + 1 >= 8) && (kingY - 1 < 0))
        {
            tiles [kingX - 1] [kingY].setBackground (Color.green);
            tiles [kingX] [kingY + 1].setBackground (Color.green);
            tiles [kingX - 1] [kingY + 1].setBackground (Color.green);
        }
        else if ((kingX - 1 < 0) && (kingY - 1 < 0))
        {
            tiles [kingX + 1] [kingY].setBackground (Color.green);
            tiles [kingX] [kingY + 1].setBackground (Color.green);
            tiles [kingX + 1] [kingY + 1].setBackground (Color.green);
        }
        else if ((kingX + 1 >= 8))
        {
            tiles [kingX] [kingY + 1].setBackground (Color.green);
            tiles [kingX] [kingY - 1].setBackground (Color.green);
            tiles [kingX - 1] [kingY + 1].setBackground (Color.green);
            tiles [kingX - 1] [kingY - 1].setBackground (Color.green);
            tiles [kingX - 1] [kingY].setBackground (Color.green);
        }
        else if ((kingX + 1 < 8) && (kingX - 1 >= 0) && (kingY + 1 < 8) && (kingY - 1 >= 0))
        {
            tiles [kingX] [kingY + 1].setBackground (Color.green);
            tiles [kingX] [kingY - 1].setBackground (Color.green);
            tiles [kingX - 1] [kingY + 1].setBackground (Color.green);
            tiles [kingX - 1] [kingY - 1].setBackground (Color.green);
            tiles [kingX - 1] [kingY].setBackground (Color.green);
            tiles [kingX + 1] [kingY].setBackground (Color.green);
            tiles [kingX + 1] [kingY + 1].setBackground (Color.green);
            tiles [kingX + 1] [kingY - 1].setBackground (Color.green);
        }
        else if ((kingX - 1 < 0))
        {
            tiles [kingX] [kingY + 1].setBackground (Color.green);
            tiles [kingX] [kingY - 1].setBackground (Color.green);
            tiles [kingX + 1] [kingY + 1].setBackground (Color.green);
            tiles [kingX + 1] [kingY - 1].setBackground (Color.green);
            tiles [kingX + 1] [kingY].setBackground (Color.green);
        }
        else if ((kingY + 1 > 7))
        {
            tiles [kingX + 1] [kingY].setBackground (Color.green);
            tiles [kingX - 1] [kingY].setBackground (Color.green);
            tiles [kingX + 1] [kingY - 1].setBackground (Color.green);
            tiles [kingX - 1] [kingY - 1].setBackground (Color.green);
            tiles [kingX] [kingY - 1].setBackground (Color.green);
        }
        else if ((kingY - 1 < 0))
        {
            tiles [kingX + 1] [kingY].setBackground (Color.green);
            tiles [kingX - 1] [kingY].setBackground (Color.green);
            tiles [kingX + 1] [kingY + 1].setBackground (Color.green);
            tiles [kingX - 1] [kingY + 1].setBackground (Color.green);
            tiles [kingX] [kingY + 1].setBackground (Color.green);
        }
    }


    public void bishop (int bishX, int bishY)  //This method highlights the possible moves for the Bishop
    {
        int i, tempsum;
        if (bishY < bishX)
        {
            tempsum = bishX - bishY;
            for (i = 1 ; i <= bishX - tempsum ; i++)
            {
                if (tiles [bishX - i] [bishY - i].getIcon () != null)
                {
                    if (pieceCode == 4 || pieceCode == 44 || pieceCode == 8)
                    {
                        if (tiles [bishX - i] [bishY - i].getIcon () == blackKnight || tiles [bishX - i] [bishY - i].getIcon () == blackRook || tiles [bishX - i] [bishY - i].getIcon () == blackBish || tiles [bishX - i] [bishY - i].getIcon () == blackQueen || tiles [bishX - i] [bishY - i].getIcon () == blackPawn)
                        {
                            tiles [bishX - i] [bishY - i].setBackground (Color.green);
                        }
                    }
                    if (pieceCode == 7 || pieceCode == 77 || pieceCode == 9)
                    {
                        if (tiles [bishX - i] [bishY - i].getIcon () == whiteKnight || tiles [bishX - i] [bishY - i].getIcon () == whiteRook || tiles [bishX - i] [bishY - i].getIcon () == whiteBish || tiles [bishX - i] [bishY - i].getIcon () == whiteQueen || tiles [bishX - i] [bishY - i].getIcon () == whitePawn)
                        {
                            tiles [bishX - i] [bishY - i].setBackground (Color.green);
                        }
                    }
                    break;
                }
                tiles [bishX - i] [bishY - i].setBackground (Color.green);
            }
            for (i = 1 ; i <= 7 - bishX ; i++)
            {
                if (tiles [bishX + i] [bishY + i].getIcon () != null)
                {
                    if (pieceCode == 4 || pieceCode == 44 || pieceCode == 8)
                    {
                        if (tiles [bishX + i] [bishY + i].getIcon () == blackKnight || tiles [bishX + i] [bishY + i].getIcon () == blackRook || tiles [bishX + i] [bishY + i].getIcon () == blackBish || tiles [bishX + i] [bishY + i].getIcon () == blackQueen || tiles [bishX + i] [bishY + i].getIcon () == blackPawn)
                        {
                            tiles [bishX + i] [bishY + i].setBackground (Color.green);
                        }
                    }
                    if (pieceCode == 7 || pieceCode == 77 || pieceCode == 9)
                    {
                        if (tiles [bishX + i] [bishY + i].getIcon () == whiteKnight || tiles [bishX + i] [bishY + i].getIcon () == whiteRook || tiles [bishX + i] [bishY + i].getIcon () == whiteBish || tiles [bishX + i] [bishY + i].getIcon () == whiteQueen || tiles [bishX + i] [bishY + i].getIcon () == whitePawn)
                        {
                            tiles [bishX + i] [bishY + i].setBackground (Color.green);
                        }
                    }
                    break;
                }
                tiles [bishX + i] [bishY + i].setBackground (Color.green);
            }
            tempsum = 7 - bishY;

            if (bishX < tempsum)
            {
                tempsum = bishX;
            }
            else
            {
                tempsum = tempsum;
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [bishX - i] [bishY + i].getIcon () != null)
                {
                    if (pieceCode == 4 || pieceCode == 44 || pieceCode == 8)
                    {
                        if (tiles [bishX - i] [bishY + i].getIcon () == blackKnight || tiles [bishX - i] [bishY + i].getIcon () == blackRook || tiles [bishX - i] [bishY + i].getIcon () == blackBish || tiles [bishX - i] [bishY + i].getIcon () == blackQueen || tiles [bishX - i] [bishY + i].getIcon () == blackPawn)
                        {
                            tiles [bishX - i] [bishY + i].setBackground (Color.green);
                        }
                    }
                    if (pieceCode == 7 || pieceCode == 77 || pieceCode == 9)
                    {
                        if (tiles [bishX - i] [bishY + i].getIcon () == whiteKnight || tiles [bishX - i] [bishY + i].getIcon () == whiteRook || tiles [bishX - i] [bishY + i].getIcon () == whiteBish || tiles [bishX - i] [bishY + i].getIcon () == whiteQueen || tiles [bishX - i] [bishY + i].getIcon () == whitePawn)
                        {
                            tiles [bishX - i] [bishY + i].setBackground (Color.green);
                        }
                    }
                    break;
                }
                tiles [bishX - i] [bishY + i].setBackground (Color.green);
            }
            tempsum = 7 - bishX;

            if (bishY < tempsum)
            {
                tempsum = bishY;
            }
            else
            {
                tempsum = tempsum;
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [bishX + i] [bishY - i].getIcon () != null)
                {
                    if (pieceCode == 4 || pieceCode == 44 || pieceCode == 8)
                    {
                        if (tiles [bishX + i] [bishY - i].getIcon () == blackKnight || tiles [bishX + i] [bishY - i].getIcon () == blackRook || tiles [bishX + i] [bishY - i].getIcon () == blackBish || tiles [bishX + i] [bishY - i].getIcon () == blackQueen || tiles [bishX + i] [bishY - i].getIcon () == blackPawn)
                        {
                            tiles [bishX + i] [bishY - i].setBackground (Color.green);
                        }
                    }
                    if (pieceCode == 7 || pieceCode == 77 || pieceCode == 9)
                    {
                        if (tiles [bishX + i] [bishY - i].getIcon () == whiteKnight || tiles [bishX + i] [bishY - i].getIcon () == whiteRook || tiles [bishX + i] [bishY - i].getIcon () == whiteBish || tiles [bishX + i] [bishY - i].getIcon () == whiteQueen || tiles [bishX + i] [bishY - i].getIcon () == whitePawn)
                        {
                            tiles [bishX + i] [bishY - i].setBackground (Color.green);
                        }
                    }
                    break;
                }
                tiles [bishX + i] [bishY - i].setBackground (Color.green);
            }

        }
        else
        {
            tempsum = 7 - bishY;

            if (bishX < tempsum)
            {
                tempsum = bishX;
            }
            else
            {
                tempsum = tempsum;
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [bishX - i] [bishY + i].getIcon () != null)
                {
                    if (pieceCode == 4 || pieceCode == 44 || pieceCode == 8)
                    {
                        if (tiles [bishX - i] [bishY + i].getIcon () == blackKnight || tiles [bishX - i] [bishY + i].getIcon () == blackRook || tiles [bishX - i] [bishY + i].getIcon () == blackBish || tiles [bishX - i] [bishY + i].getIcon () == blackQueen || tiles [bishX - i] [bishY + i].getIcon () == blackPawn)
                        {
                            tiles [bishX - i] [bishY + i].setBackground (Color.green);
                        }
                    }
                    if (pieceCode == 7 || pieceCode == 77 || pieceCode == 9)
                    {
                        if (tiles [bishX - i] [bishY + i].getIcon () == whiteKnight || tiles [bishX - i] [bishY + i].getIcon () == whiteRook || tiles [bishX - i] [bishY + i].getIcon () == whiteBish || tiles [bishX - i] [bishY + i].getIcon () == whiteQueen || tiles [bishX - i] [bishY + i].getIcon () == whitePawn)
                        {
                            tiles [bishX - i] [bishY + i].setBackground (Color.green);
                        }
                    }
                    break;
                }
                tiles [bishX - i] [bishY + i].setBackground (Color.green);
            }
            tempsum = 7 - bishX;

            if (tempsum < (7 - bishY))
            {
                tempsum = tempsum;
            }
            else
            {
                tempsum = (7 - bishY);
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [bishX + i] [bishY + i].getIcon () != null)
                {
                    if (pieceCode == 4 || pieceCode == 44 || pieceCode == 8)
                    {
                        if (tiles [bishX + i] [bishY + i].getIcon () == blackKnight || tiles [bishX + i] [bishY + i].getIcon () == blackRook || tiles [bishX + i] [bishY + i].getIcon () == blackBish || tiles [bishX + i] [bishY + i].getIcon () == blackQueen || tiles [bishX + i] [bishY + i].getIcon () == blackPawn)
                        {
                            tiles [bishX + i] [bishY + i].setBackground (Color.green);
                        }
                    }
                    if (pieceCode == 7 || pieceCode == 77 || pieceCode == 9)
                    {
                        if (tiles [bishX + i] [bishY + i].getIcon () == whiteKnight || tiles [bishX + i] [bishY + i].getIcon () == whiteRook || tiles [bishX + i] [bishY + i].getIcon () == whiteBish || tiles [bishX + i] [bishY + i].getIcon () == whiteQueen || tiles [bishX + i] [bishY + i].getIcon () == whitePawn)
                        {
                            tiles [bishX + i] [bishY + i].setBackground (Color.green);
                        }
                    }
                    break;
                }
                tiles [bishX + i] [bishY + i].setBackground (Color.green);
            }
            tempsum = 7 - bishX;

            if (bishY < tempsum)
            {
                tempsum = bishY;
            }
            else
            {
                tempsum = tempsum;
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [bishX + i] [bishY - i].getIcon () != null)
                {
                    if (pieceCode == 4 || pieceCode == 44 || pieceCode == 8)
                    {
                        if (tiles [bishX + i] [bishY - i].getIcon () == blackKnight || tiles [bishX + i] [bishY - i].getIcon () == blackRook || tiles [bishX + i] [bishY - i].getIcon () == blackBish || tiles [bishX + i] [bishY - i].getIcon () == blackQueen || tiles [bishX + i] [bishY - i].getIcon () == blackPawn)
                        {
                            tiles [bishX + i] [bishY - i].setBackground (Color.green);
                        }
                    }
                    if (pieceCode == 7 || pieceCode == 77 || pieceCode == 9)
                    {
                        if (tiles [bishX + i] [bishY - i].getIcon () == whiteKnight || tiles [bishX + i] [bishY - i].getIcon () == whiteRook || tiles [bishX + i] [bishY - i].getIcon () == whiteBish || tiles [bishX + i] [bishY - i].getIcon () == whiteQueen || tiles [bishX + i] [bishY - i].getIcon () == whitePawn)
                        {
                            tiles [bishX + i] [bishY - i].setBackground (Color.green);
                        }
                    }
                    break;
                }
                tiles [bishX + i] [bishY - i].setBackground (Color.green);
            }


            for (i = 1 ; i <= bishX ; i++)
            {
                if (tiles [bishX - i] [bishY - i].getIcon () != null)
                {
                    if (pieceCode == 4 || pieceCode == 44 || pieceCode == 8)
                    {
                        if (tiles [bishX - i] [bishY - i].getIcon () == blackKnight || tiles [bishX - i] [bishY - i].getIcon () == blackRook || tiles [bishX - i] [bishY - i].getIcon () == blackBish || tiles [bishX - i] [bishY - i].getIcon () == blackQueen || tiles [bishX - i] [bishY - i].getIcon () == blackPawn)
                        {
                            tiles [bishX - i] [bishY - i].setBackground (Color.green);
                        }
                    }
                    if (pieceCode == 7 || pieceCode == 77 || pieceCode == 9)
                    {
                        if (tiles [bishX - i] [bishY - i].getIcon () == whiteKnight || tiles [bishX - i] [bishY - i].getIcon () == whiteRook || tiles [bishX - i] [bishY - i].getIcon () == whiteBish || tiles [bishX - i] [bishY - i].getIcon () == whiteQueen || tiles [bishX - i] [bishY - i].getIcon () == whitePawn)
                        {
                            tiles [bishX - i] [bishY - i].setBackground (Color.green);
                        }
                    }
                    break;
                }
                tiles [bishX - i] [bishY - i].setBackground (Color.green);
            }
        }
    }


    public void rook (int rookX, int rookY)//This method highlights possible moves for the rook
    {
        int i;
        for (i = 1 ; i <= rookX ; i++)
        {
            if (tiles [rookX - i] [rookY].getIcon () != null)
            {
                if (pieceCode == 2 || pieceCode == 22 || pieceCode == 8)
                {
                    if (tiles [rookX - i] [rookY].getIcon () == blackKnight || tiles [rookX - i] [rookY].getIcon () == blackRook || tiles [rookX - i] [rookY].getIcon () == blackBish || tiles [rookX - i] [rookY].getIcon () == blackQueen || tiles [rookX - i] [rookY].getIcon () == blackPawn)
                    {
                        tiles [rookX - i] [rookY].setBackground (Color.green);
                    }
                }
                if (pieceCode == 3 || pieceCode == 33 || pieceCode == 9)
                {
                    if (tiles [rookX - i] [rookY].getIcon () == whiteKnight || tiles [rookX - i] [rookY].getIcon () == whiteRook || tiles [rookX - i] [rookY].getIcon () == whiteBish || tiles [rookX - i] [rookY].getIcon () == whiteQueen || tiles [rookX - i] [rookY].getIcon () == whitePawn)
                    {
                        tiles [rookX - i] [rookY].setBackground (Color.green);
                    }
                }
                break;
            }
            tiles [rookX - i] [rookY].setBackground (Color.green);
        }


        for (i = 1 ; i <= 7 - rookX ; i++)
        {
            if (tiles [rookX + i] [rookY].getIcon () != null)
            {
                if (pieceCode == 2 || pieceCode == 22 || pieceCode == 8)
                {
                    if (tiles [rookX + i] [rookY].getIcon () == blackKnight || tiles [rookX + i] [rookY].getIcon () == blackRook || tiles [rookX + i] [rookY].getIcon () == blackBish || tiles [rookX + i] [rookY].getIcon () == blackQueen || tiles [rookX + i] [rookY].getIcon () == blackPawn)
                    {
                        tiles [rookX + i] [rookY].setBackground (Color.green);
                    }
                }
                if (pieceCode == 3 || pieceCode == 33 || pieceCode == 9)
                {
                    if (tiles [rookX + i] [rookY].getIcon () == whiteKnight || tiles [rookX + i] [rookY].getIcon () == whiteRook || tiles [rookX + i] [rookY].getIcon () == whiteBish || tiles [rookX + i] [rookY].getIcon () == whiteQueen || tiles [rookX + i] [rookY].getIcon () == whitePawn)
                    {
                        tiles [rookX + i] [rookY].setBackground (Color.green);
                    }
                }
                break;
            }
            tiles [rookX + i] [rookY].setBackground (Color.green);
        }


        for (i = 1 ; i <= rookY ; i++)
        {
            if (tiles [rookX] [rookY - i].getIcon () != null)
            {
                if (pieceCode == 2 || pieceCode == 22 || pieceCode == 8)
                {
                    if (tiles [rookX] [rookY - i].getIcon () == blackKnight || tiles [rookX] [rookY - i].getIcon () == blackRook || tiles [rookX] [rookY - i].getIcon () == blackBish || tiles [rookX] [rookY - i].getIcon () == blackQueen || tiles [rookX] [rookY - i].getIcon () == blackPawn)
                    {
                        tiles [rookX] [rookY - i].setBackground (Color.green);
                    }
                }
                if (pieceCode == 3 || pieceCode == 33 || pieceCode == 9)
                {
                    if (tiles [rookX] [rookY - i].getIcon () == whiteKnight || tiles [rookX] [rookY - i].getIcon () == whiteRook || tiles [rookX] [rookY - i].getIcon () == whiteBish || tiles [rookX] [rookY - i].getIcon () == whiteQueen || tiles [rookX] [rookY - i].getIcon () == whitePawn)
                    {
                        tiles [rookX] [rookY - i].setBackground (Color.green);
                    }
                }
                break;
            }
            tiles [rookX] [rookY - i].setBackground (Color.green);
        }


        for (i = 1 ; i <= 7 - rookY ; i++)
        {
            if (tiles [rookX] [rookY + i].getIcon () != null)
            {
                if (pieceCode == 2 || pieceCode == 22 || pieceCode == 8)
                {
                    if (tiles [rookX] [rookY + i].getIcon () == blackKnight || tiles [rookX] [rookY + i].getIcon () == blackRook || tiles [rookX] [rookY + i].getIcon () == blackBish || tiles [rookX] [rookY + i].getIcon () == blackQueen || tiles [rookX] [rookY + i].getIcon () == blackPawn)
                    {
                        tiles [rookX] [rookY + i].setBackground (Color.green);
                    }
                }
                if (pieceCode == 3 || pieceCode == 33 || pieceCode == 9)
                {
                    if (tiles [rookX] [rookY + i].getIcon () == whiteKnight || tiles [rookX] [rookY + i].getIcon () == whiteRook || tiles [rookX] [rookY + i].getIcon () == whiteBish || tiles [rookX] [rookY + i].getIcon () == whiteQueen || tiles [rookX] [rookY + i].getIcon () == whitePawn)
                    {
                        tiles [rookX] [rookY + i].setBackground (Color.green);
                    }
                }
                break;
            }
            tiles [rookX] [rookY + i].setBackground (Color.green);
        }
    }


    public void knight (int knightX, int knightY)  //This method highlights the possible moves for the Knight
    {
        try
        {
            tiles [knightX + 2] [knightY + 1].setBackground (Color.GREEN);
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            tiles [knightX + 2] [knightY - 1].setBackground (Color.GREEN);
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            tiles [knightX - 2] [knightY + 1].setBackground (Color.GREEN);
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            tiles [knightX - 2] [knightY - 1].setBackground (Color.GREEN);
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            tiles [knightX + 1] [knightY + 2].setBackground (Color.GREEN);
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            tiles [knightX + 1] [knightY - 2].setBackground (Color.GREEN);
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            tiles [knightX - 1] [knightY + 2].setBackground (Color.GREEN);
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            tiles [knightX - 1] [knightY - 2].setBackground (Color.GREEN);
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
    }


    public void actionPerformed1 (ActionEvent e, int pieceCode)
    {
        //To stop the program from running the actionPerformed method and this method immediately, a 'number' is set for each type of piece.
        //When the protgram comes to this method every odd time(1st, 3rd, 5th, 7th, etc) the pieceCode is assigned in the actionPerformed method,
        //However when the user clicks on a different square, no pieceCode is assigned meaning this method wont execute
        //When the program comes here every alternate time a number is assigned, which allows it come to this method again the even time.
        //before returning the number is changed to a random one, to avoid confusions and the program going into the wrong ifs
        //the tile the user clikcs on is stored in two variables, "pieceX" and "pieceY" its x and y position. This is used throughout the program

        int pieceX, pieceY, count;
        if (number == 10 || (pieceCode == 0 && number != 11) || (pieceCode == 5 && number != 11))
        {
            number = 10;
            for (int aa = 0 ; aa < 8 ; aa++)
            {
                for (int bb = 0 ; bb < 8 ; bb++)
                {
                    if (pieceCode == 5)
                    {
                        //if the user clicks on the black king
                        if ((e.getSource () == tiles [aa] [bb]) && (e.getSource () != tiles [blackKingx] [blackKingy]))
                        {
                            pieceCode = 5;
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);
                            number = 20;
                            number = 8291;
                            return;
                        }
                    }
                    else if (pieceCode == 0)
                    {
                        //if the user clicks on the black king
                        if ((e.getSource () == tiles [aa] [bb]) && (e.getSource () != tiles [whiteKingy] [whiteKingx]))
                        {
                            pieceCode = 0;
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);
                            number = 8291;
                            return;
                        }
                    }
                    else
                    {
                        pieceCode = 100;
                    }
                }
            }

        }
        else if ((pieceCode == 1 && number != 10) || number == 11 || (pieceCode == 6 && number != 10) || (pieceCode == 66 && number != 10) || (pieceCode == 11 && number != 10))
        {
            number = 11;
            for (int aa = 0 ; aa < 8 ; aa++)
            {
                for (int bb = 0 ; bb < 8 ; bb++)
                {
                    if (pieceCode == 1)
                    {
                        //if the user clicks on the black knight
                        if ((e.getSource () == tiles [aa] [bb]) && (e.getSource () != tiles [blackKnightx] [blackKnighty]))
                        {
                            pieceCode = 1;
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);

                            try
                            {
                                bknightChk (pieceX, pieceY);
                            }
                            catch (ArrayIndexOutOfBoundsException es)
                            {
                            }

                            if (ran1 == 1)
                            {
                                a = check (blackKingx, blackKingy, 5);
                                if (a == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [blackKnightx1] [blackKnighty1].setIcon (blackKnight);

                                }
                                else
                                {
                                    blackKnightx1 = pieceX;
                                    blackKnighty1 = pieceY;
                                }
                            }
                            a = bknightChk (pieceX, pieceY);

                            if (a == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }

                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 191;
                            return;
                        }

                        else
                        {
                        }
                    }
                    else if (pieceCode == 11)
                    {
                        //if the user clicks on the black knight
                        if ((e.getSource () == tiles [aa] [bb]) && (e.getSource () != tiles [blackKnightx1] [blackKnighty1]))
                        {
                            pieceCode = 11;
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);

                            try
                            {
                                bknightChk (pieceX, pieceY);
                            }
                            catch (ArrayIndexOutOfBoundsException es)
                            {
                            }

                            if (ran1 == 1)
                            {
                                a = check (blackKingx, blackKingy, 5);
                                if (a == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [blackKnightx] [blackKnighty].setIcon (blackKnight);

                                }
                                else
                                {
                                    blackKnightx = pieceX;
                                    blackKnighty = pieceY;
                                }
                            }
                            a = bknightChk (pieceX, pieceY);

                            if (a == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }

                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 191;
                            return;
                        }

                        else
                        {
                        }
                    }
                    else if (pieceCode == 6)
                    {
                        //if the user clicks on the white knight
                        if ((e.getSource () == tiles [aa] [bb]) && (e.getSource () != tiles [whiteKnightx] [whiteKnighty]))
                        {
                            pieceCode = 6;
                            pieceX = aa;
                            pieceY = bb;
                            number = 20;
                            userClick (pieceX, pieceY, pieceCode);
                            try
                            {
                                wknightChk (pieceX, pieceY);
                            }
                            catch (ArrayIndexOutOfBoundsException es)
                            {
                            }
                            if (ran == 1)
                            {
                                aaq = check (whiteKingy, whiteKingx, 0);
                                if (aaq == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [whiteKnightx] [whiteKnighty].setIcon (whiteKnight);
                                }
                                else
                                {
                                    whiteKnightx = pieceX;
                                    whiteKnighty = pieceY;
                                }
                            }

                            aaq = wknightChk (pieceX, pieceY);
                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }

                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 191;
                            return;
                        }
                        else
                        {
                        }
                    }
                    else if (pieceCode == 66)
                    {
                        //if the user clicks on the white knight
                        if ((e.getSource () == tiles [aa] [bb]) && (e.getSource () != tiles [whiteKnightx1] [whiteKnighty1]))
                        {
                            pieceCode = 66;
                            pieceX = aa;
                            pieceY = bb;
                            number = 20;
                            userClick (pieceX, pieceY, pieceCode);
                            try
                            {
                                wknightChk (pieceX, pieceY);
                            }
                            catch (ArrayIndexOutOfBoundsException es)
                            {
                            }

                            if (ran == 1)
                            {
                                aaq = check (whiteKingy, whiteKingx, 0);
                                if (aaq == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [whiteKnightx1] [whiteKnighty1].setIcon (whiteKnight);

                                }
                                else
                                {
                                    whiteKnightx1 = pieceX;
                                    whiteKnighty1 = pieceY;
                                }
                            }
                            aaq = wknightChk (pieceX, pieceY);

                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }

                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 191;
                            return;
                        }
                        else
                        {
                        }
                    }
                }
            }
        }
        else if (pieceCode == 2 || number == 22 || pieceCode == 22 || pieceCode == 3 || pieceCode == 33)
        {
            number = 22;
            for (int aa = 0 ; aa < 8 ; aa++)
            {
                for (int bb = 0 ; bb < 8 ; bb++)
                {
                    if (pieceCode == 2) //if the user clicks on the white rook
                    {
                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != whiteRook)
                        {
                            pieceCode = 2;
                            pieceX = aa;
                            pieceY = bb;
                            number = 20;
                            userClick (pieceX, pieceY, pieceCode);
                            if (ran == 1)
                            {
                                aaq = check (whiteKingy, whiteKingx, 0);
                                if (aaq == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [whiteRookx] [whiteRooky].setIcon (whiteRook);
                                }
                                else
                                {
                                    whiteRookx = pieceX;
                                    whiteRooky = pieceY;
                                }
                            }

                            aaq = rook1Chk (pieceX, pieceY);
                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }

                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 110;
                            return;


                        }
                    }
                    if (pieceCode == 3) //if the user clicks on the black rook
                    {
                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != blackRook)
                        {
                            pieceCode = 3;
                            pieceX = aa;
                            pieceY = bb;
                            number = 20;
                            userClick (pieceX, pieceY, pieceCode);

                            if (ran1 == 1)
                            {
                                aaq = check (blackKingx, blackKingy, 5);
                                if (aaq == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [blackRookx] [blackRooky].setIcon (blackRook);

                                }
                                else
                                {
                                    blackRookx = pieceX;
                                    blackRooky = pieceY;
                                }
                            }

                            a = rook2Chk (pieceX, pieceY);

                            if (a == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }
                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 110;
                            return;
                        }
                    }
                    if (pieceCode == 22) //if the user clicks on the white rook
                    {
                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != whiteRook)
                        {
                            pieceCode = 22;
                            pieceX = aa;
                            pieceY = bb;
                            number = 20;
                            userClick (pieceX, pieceY, pieceCode);

                            if (ran == 1)
                            {
                                aaq = check (whiteKingy, whiteKingx, 0);
                                if (aaq == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [whiteRookx1] [whiteRooky1].setIcon (whiteRook);

                                }
                                else
                                {
                                    whiteRookx1 = pieceX;
                                    whiteRooky1 = pieceY;
                                }
                            }

                            aaq = rook1Chk (pieceX, pieceY);
                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }

                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 110;
                            return;
                        }
                    }
                    if (pieceCode == 33) //if the user clicks on the black rook
                    {
                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != blackRook)
                        {
                            pieceCode = 33;
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);

                            if (ran1 == 1)
                            {
                                aaq = check (blackKingx, blackKingy, 5);
                                if (aaq == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [blackRookx1] [blackRooky1].setIcon (blackRook);
                                }
                                else
                                {
                                    blackRookx1 = pieceX;
                                    blackRooky1 = pieceY;
                                }
                            }
                            a = rook2Chk (pieceX, pieceY);
                            if (a == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }

                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 110;
                            return;
                        }
                    }
                }
            }
        }
        else if (pieceCode == 4 || number == 27 || pieceCode == 7 || pieceCode == 44 || pieceCode == 77)
        {
            number = 27;
            for (int aa = 0 ; aa < 8 ; aa++)
            {
                for (int bb = 0 ; bb < 8 ; bb++)
                {
                    if (pieceCode == 4) //if the user clicks on the white bishop
                    {
                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != whiteBish)
                        {
                            pieceCode = 4;
                            pieceX = aa;
                            pieceY = bb;

                            userClick (pieceX, pieceY, pieceCode);

                            if (ran == 1)
                            {
                                aaq = check (whiteKingy, whiteKingx, 0);
                                if (aaq == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [whiteBishx] [whiteBishy].setIcon (whiteBish);

                                }
                                else
                                {
                                    whiteBishx = pieceX;
                                    whiteBishy = pieceY;
                                }
                            }
                            aaq = wBishChk (pieceX, pieceY);
                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }
                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 190;
                            return;
                        }
                    }
                    if (pieceCode == 7) //if the user clicks on the black bishop
                    {
                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != blackBish)
                        {
                            pieceCode = 7;
                            pieceX = aa;
                            pieceY = bb;

                            userClick (pieceX, pieceY, pieceCode);

                            if (ran1 == 1)
                            {
                                aaq = check (blackKingx, blackKingy, 5);
                                if (aaq == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [blackBishx] [blackBishy].setIcon (blackBish);
                                }
                                else
                                {
                                    blackBishx = pieceX;
                                    blackBishy = pieceY;
                                }
                            }

                            a = bBishChk (pieceX, pieceY);

                            if (a == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }

                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 190;
                            return;
                        }
                    }
                    if (pieceCode == 44) //if the user clicks on the white bishop
                    {
                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != whiteBish)
                        {
                            pieceCode = 44;
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);

                            if (ran == 1)
                            {
                                aaq = check (whiteKingy, whiteKingx, 0);
                                if (aaq == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [whiteBishx1] [whiteBishy1].setIcon (whiteBish);

                                }
                                else
                                {
                                    whiteBishx1 = pieceX;
                                    whiteBishy1 = pieceY;
                                }
                            }

                            aaq = wBishChk (pieceX, pieceY);

                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }

                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 190;
                            return;
                        }
                    }
                    if (pieceCode == 77) //if the user clicks on the black bishop
                    {
                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != blackBish)
                        {
                            pieceCode = 77;
                            pieceX = aa;
                            pieceY = bb;

                            userClick (pieceX, pieceY, pieceCode);

                            if (ran1 == 1)
                            {
                                aaq = check (blackKingx, blackKingy, 5);
                                if (aaq == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [blackBishx1] [blackBishy1].setIcon (blackBish);

                                }
                                else
                                {
                                    blackBishx1 = pieceX;
                                    blackBishy1 = pieceY;
                                }
                            }

                            a = bBishChk (pieceX, pieceY);

                            if (a == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }
                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 190;
                            return;
                        }
                    }
                }
            }
        }
        else if ((pieceCode == 8 || number == 43 || pieceCode == 9) && pieceCode != 102)
        {
            number = 43;
            for (int aa = 0 ; aa < 8 ; aa++)
            {
                for (int bb = 0 ; bb < 8 ; bb++)
                {
                    if (pieceCode == 8) //if the user clicks on the white queen
                    {
                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != whiteQueen)
                        {
                            pieceCode = 8;
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);

                            if (ran == 1)
                            {
                                aaq = check (whiteKingy, whiteKingx, 0);
                                if (aaq == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [whiteQueenx] [whiteQueeny].setIcon (whiteQueen);

                                }
                                else
                                {
                                    whiteQueenx = pieceX;
                                    whiteQueeny = pieceY;
                                }
                            }
                            aaq = wBishChk (pieceX, pieceY);
                            a = rook1Chk (pieceX, pieceY);
                            if (aaq == 1 || a == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }

                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 120;
                            return;
                        }
                    }
                    else if (pieceCode == 9) //if the user clicks on the black queen
                    {
                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != blackQueen)
                        {
                            pieceCode = 9;

                            pieceX = aa;
                            pieceY = bb;

                            userClick (pieceX, pieceY, pieceCode);

                            if (ran1 == 1)
                            {
                                aaq = check (blackKingx, blackKingy, 5);
                                if (aaq == 1)
                                {
                                    tiles [pieceX] [pieceY].setIcon (null);
                                    tiles [blackQueenx] [blackQueeny].setIcon (blackQueen);

                                }
                                else
                                {
                                    blackQueenx = pieceX;
                                    blackQueeny = pieceY;
                                }
                            }
                            aaq = bBishChk (pieceX, pieceY);
                            a = rook2Chk (pieceX, pieceY);


                            if (aaq == 1 || a == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }
                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 120;
                            return;

                        }
                    }
                }
            }
        }
        else if (pieceCode == 101 || number == 98 || pieceCode == 102 || pieceCode == 103 || pieceCode == 104 || pieceCode == 105 || pieceCode == 106 || pieceCode == 107 || pieceCode == 108 || pieceCode == 201 || pieceCode == 202 || pieceCode == 203 || pieceCode == 204 || pieceCode == 205 || pieceCode == 206 || pieceCode == 207 || pieceCode == 208)
        {
            number = 98;
            for (int aa = 0 ; aa < 8 ; aa++)
            {
                for (int bb = 0 ; bb < 8 ; bb++)
                {
                    if (pieceCode == 101) //if the user clicks on the white pawn
                    {
                        if (whitePawnx1 == 0) //end of the board
                        {
                            return;
                        }
                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != whitePawn)
                        {
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);
                            aaq = wPawnChk (pieceX, pieceY);
                            int theThreat;
                            theThreat = check (whiteKingy, whiteKingx, 0);

                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (whitePawn);
                                whitePawnx1 = tempPawnx;
                                whitePawny1 = tempPawny;
                                number = 19289128;
                            }
                            if (ran == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [whitePawnx1] [whitePawny1].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                                    whitePawnx1 = pieceX;
                                    whitePawny1 = pieceY;
                                    ran = 0;
                                }
                            }

                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }

                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }

                            number = 1291;
                            return;
                        }
                    }
                    else if (pieceCode == 102) //if the user clicks on the white pawn
                    {
                        if (whitePawnx2 == 0) //end of the board
                        {
                            return;
                        }

                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != whitePawn)
                        {
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);
                            aaq = wPawnChk (pieceX, pieceY);
                            int theThreat;
                            theThreat = check (whiteKingy, whiteKingx, 0);

                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (whitePawn);
                                whitePawnx2 = tempPawnx;
                                whitePawny2 = tempPawny;
                                number = 19289128;
                            }
                            if (ran == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [whitePawnx2] [whitePawny2].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                                    whitePawnx2 = pieceX;
                                    whitePawny2 = pieceY;
                                    ran = 0;
                                }
                            }

                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }

                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 1291;
                            return;
                        }
                    }
                    else if (pieceCode == 103) //if the user clicks on the white pawn
                    {
                        if (whitePawnx3 == 0) //end of the board
                        {
                            return;
                        }

                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != whitePawn)
                        {
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);
                            aaq = wPawnChk (pieceX, pieceY);
                            int theThreat;
                            theThreat = check (whiteKingy, whiteKingx, 0);
                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (whitePawn);
                                whitePawnx3 = tempPawnx;
                                whitePawny3 = tempPawny;
                                number = 19289128;
                            }
                            if (ran == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [whitePawnx3] [whitePawny3].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                                    whitePawnx3 = pieceX;
                                    whitePawny3 = pieceY;
                                    ran = 0;
                                }
                            }

                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }

                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 1291;
                            return;
                        }
                    }
                    else if (pieceCode == 104) //if the user clicks on the white pawn
                    {
                        if (whitePawnx4 == 0) //end of the board
                        {
                            return;
                        }
                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != whitePawn)
                        {
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);
                            aaq = wPawnChk (pieceX, pieceY);
                            int theThreat;
                            theThreat = check (whiteKingy, whiteKingx, 0);
                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (whitePawn);
                                whitePawnx4 = tempPawnx;
                                whitePawny4 = tempPawny;
                                number = 19289128;
                            }
                            if (ran == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [whitePawnx4] [whitePawny4].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                                    whitePawnx4 = pieceX;
                                    whitePawny4 = pieceY;
                                    ran = 0;
                                }
                            }

                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }

                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 1291;
                            return;
                        }
                    }
                    else if (pieceCode == 105) //if the user clicks on the white pawn
                    {
                        if (whitePawnx5 == 0) //end of the board
                        {
                            return;
                        }

                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != whitePawn)
                        {
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);
                            aaq = wPawnChk (pieceX, pieceY);
                            int theThreat;
                            theThreat = check (whiteKingy, whiteKingx, 0);
                            if (ran == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [whitePawnx5] [whitePawny5].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                                    whitePawnx5 = pieceX;
                                    whitePawny5 = pieceY;
                                    ran = 0;
                                }
                            }
                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (whitePawn);
                                whitePawnx5 = tempPawnx;
                                whitePawny5 = tempPawny;
                                number = 19289128;
                            }


                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }
                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 1291;
                            return;
                        }
                    }
                    else if (pieceCode == 106) //if the user clicks on the white pawn
                    {
                        if (whitePawnx6 == 0) //end of the board
                        {
                            return;
                        }
                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != whitePawn)
                        {
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);
                            aaq = wPawnChk (pieceX, pieceY);
                            int theThreat;
                            theThreat = check (whiteKingy, whiteKingx, 0);
                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (whitePawn);
                                whitePawnx6 = tempPawnx;
                                whitePawny6 = tempPawny;
                                number = 19289128;
                            }
                            if (ran == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [whitePawnx6] [whitePawny6].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                                    whitePawnx6 = pieceX;
                                    whitePawny6 = pieceY;
                                    ran = 0;
                                }
                            }
                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }

                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 1291;
                            return;
                        }
                    }
                    else if (pieceCode == 107) //if the user clicks on the white pawn
                    {
                        if (whitePawnx7 == 0) //end of the board
                        {
                            return;
                        }

                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != whitePawn)
                        {
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);
                            aaq = wPawnChk (pieceX, pieceY);
                            int theThreat;
                            theThreat = check (whiteKingy, whiteKingx, 0);
                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (whitePawn);
                                whitePawnx7 = tempPawnx;
                                whitePawny7 = tempPawny;
                                number = 19289128;
                            }
                            if (ran == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [whitePawnx7] [whitePawny7].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                                    whitePawnx7 = pieceX;
                                    whitePawny7 = pieceY;
                                    ran = 0;
                                }
                            }

                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }


                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 1291;

                            return;
                        }
                    }
                    else if (pieceCode == 108) //if the user clicks on the white pawn
                    {
                        if (whitePawnx8 == 0) //end of the board
                        {
                            return;
                        }

                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != whitePawn)
                        {
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);
                            aaq = wPawnChk (pieceX, pieceY);
                            int theThreat;
                            theThreat = check (whiteKingy, whiteKingx, 0);
                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (whitePawn);
                                whitePawnx8 = tempPawnx;
                                whitePawny8 = tempPawny;
                                number = 19289128;
                            }
                            if (ran == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [whitePawnx8] [whitePawny8].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                                    whitePawnx8 = pieceX;
                                    whitePawny8 = pieceY;
                                    ran = 0;
                                }
                            }

                            if (aaq == 1)
                            {
                                ran1 = 1;
                            }
                            else
                            {
                                ran1 = 0;
                            }
                            if (ran1 == 1)
                            {
                                disp = checkMate (5);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            number = 1291;
                            return;
                        }
                    }
                    else if (pieceCode == 201) //if the user clicks on the black pawn
                    {
                        if (blackPawnx1 == 7) //end of the board
                        {
                            return;
                        }

                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != blackPawn)
                        {
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);
                            aaq = bPawnChk (pieceX, pieceY);
                            int theThreat;
                            theThreat = check (blackKingx, blackKingy, 5);
                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (blackPawn);
                                blackPawnx1 = tempPawnx;
                                blackPawny1 = tempPawny;
                                number = 19289128;
                            }
                            if (ran1 == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [blackPawnx1] [blackPawny1].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                                    blackPawnx1 = pieceX;
                                    blackPawny1 = pieceY;
                                    ran1 = 0;
                                }
                            }

                            if (aaq == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }
                            number = 1291;

                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            return;
                        }
                    }
                    else if (pieceCode == 202) //if the user clicks on the black pawn
                    {
                        if (blackPawnx2 == 7) //end of the board
                        {
                            return;
                        }

                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != blackPawn)
                        {
                            pieceX = aa;
                            pieceY = bb;

                            userClick (pieceX, pieceY, pieceCode);

                            aaq = bPawnChk (pieceX, pieceY);


                            int theThreat;
                            theThreat = check (blackKingx, blackKingy, 5);

                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (blackPawn);
                                blackPawnx2 = tempPawnx;
                                blackPawny2 = tempPawny;
                                number = 19289128;


                            }
                            if (ran1 == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [blackPawnx2] [blackPawny2].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                                    blackPawnx2 = pieceX;
                                    blackPawny2 = pieceY;
                                    ran1 = 0;
                                }
                            }

                            if (aaq == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }

                            number = 1291;

                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }

                            return;
                        }
                    }
                    else if (pieceCode == 203) //if the user clicks on the black pawn
                    {
                        if (blackPawnx3 == 7) //end of the board
                        {
                            return;
                        }

                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != blackPawn)
                        {
                            pieceX = aa;
                            pieceY = bb;

                            userClick (pieceX, pieceY, pieceCode);

                            aaq = bPawnChk (pieceX, pieceY);


                            int theThreat;
                            theThreat = check (blackKingx, blackKingy, 5);

                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (blackPawn);
                                blackPawnx3 = tempPawnx;
                                blackPawny3 = tempPawny;
                                number = 19289128;
                            }
                            if (ran1 == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [blackPawnx3] [blackPawny3].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                                    blackPawnx3 = pieceX;
                                    blackPawny3 = pieceY;
                                    ran1 = 0;
                                }
                            }

                            if (aaq == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }

                            number = 1291;

                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            return;
                        }
                    }
                    else if (pieceCode == 204) //if the user clicks on the black pawn
                    {
                        if (blackPawnx4 == 7) //end of the board
                        {
                            return;
                        }

                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != blackPawn)
                        {
                            pieceX = aa;
                            pieceY = bb;

                            userClick (pieceX, pieceY, pieceCode);

                            aaq = bPawnChk (pieceX, pieceY);


                            int theThreat;
                            theThreat = check (blackKingx, blackKingy, 5);

                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (blackPawn);
                                blackPawnx4 = tempPawnx;
                                blackPawny4 = tempPawny;
                                number = 19289128;


                            }
                            if (ran1 == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [blackPawnx4] [blackPawny4].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                                    blackPawnx4 = pieceX;
                                    blackPawny4 = pieceY;
                                    ran1 = 0;
                                }
                            }

                            if (aaq == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }

                            number = 1291;

                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }

                            return;
                        }
                    }
                    else if (pieceCode == 205) //if the user clicks on the black pawn
                    {
                        if (blackPawnx5 == 7) //end of the board
                        {
                            return;
                        }

                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != blackPawn)
                        {
                            pieceX = aa;
                            pieceY = bb;

                            userClick (pieceX, pieceY, pieceCode);
                            aaq = bPawnChk (pieceX, pieceY);
                            int theThreat;
                            theThreat = check (blackKingx, blackKingy, 5);

                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (blackPawn);
                                blackPawnx5 = tempPawnx;
                                blackPawny5 = tempPawny;
                                number = 19289128;
                            }
                            if (ran1 == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [blackPawnx5] [blackPawny5].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                                    blackPawnx5 = pieceX;
                                    blackPawny5 = pieceY;
                                    ran1 = 0;
                                }
                            }

                            if (aaq == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }

                            number = 1291;

                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }

                            return;
                        }
                    }
                    else if (pieceCode == 206) //if the user clicks on the black pawn
                    {
                        if (blackPawnx6 == 7) //end of the board
                        {
                            return;
                        }

                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != blackPawn)
                        {
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);

                            aaq = bPawnChk (pieceX, pieceY);

                            int theThreat;
                            theThreat = check (blackKingx, blackKingy, 5);
                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (blackPawn);
                                blackPawnx6 = tempPawnx;
                                blackPawny6 = tempPawny;
                                number = 19289128;
                            }

                            if (ran1 == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [blackPawnx6] [blackPawny6].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                                    blackPawnx6 = pieceX;
                                    blackPawny6 = pieceY;
                                    ran1 = 0;
                                }
                            }

                            if (aaq == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }

                            number = 1291;

                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }

                            return;
                        }
                    }
                    else if (pieceCode == 207) //if the user clicks on the black pawn
                    {
                        if (blackPawnx7 == 7) //end of the board
                        {
                            return;
                        }

                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != blackPawn)
                        {
                            pieceX = aa;
                            pieceY = bb;

                            userClick (pieceX, pieceY, pieceCode);

                            aaq = bPawnChk (pieceX, pieceY);

                            int theThreat;
                            theThreat = check (blackKingx, blackKingy, 5);

                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (blackPawn);
                                blackPawnx7 = tempPawnx;
                                blackPawny7 = tempPawny;
                                number = 19289128;
                            }
                            if (ran1 == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [blackPawnx7] [blackPawny7].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                                    blackPawnx7 = pieceX;
                                    blackPawny7 = pieceY;
                                    ran1 = 0;
                                }
                            }
                            else
                            {
                            }

                            if (aaq == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }

                            number = 1291;

                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }

                            return;
                        }
                    }
                    else if (pieceCode == 208) //if the user clicks on the black pawn
                    {
                        if (blackPawnx8 == 7) //end of the board
                        {
                            return;
                        }

                        if (e.getSource () == tiles [aa] [bb] && tiles [aa] [bb].getIcon () != blackPawn)
                        {
                            pieceX = aa;
                            pieceY = bb;
                            userClick (pieceX, pieceY, pieceCode);
                            aaq = bPawnChk (pieceX, pieceY);
                            int theThreat;
                            theThreat = check (blackKingx, blackKingy, 5);
                            if (theThreat == 1)
                            {
                                tiles [pieceX] [pieceY].setIcon (null);
                                tiles [tempPawnx] [tempPawny].setIcon (blackPawn);
                                blackPawnx8 = tempPawnx;
                                blackPawny8 = tempPawny;
                                number = 19289128;
                            }
                            if (ran1 == 1)
                            {
                                if (pieceX == tempx && pieceY == tempy)
                                {
                                    tiles [blackPawnx8] [blackPawny8].setIcon (null);
                                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                                    blackPawnx8 = pieceX;
                                    blackPawny8 = pieceY;
                                    ran1 = 0;
                                }
                            }

                            if (aaq == 1)
                            {
                                ran = 1;
                            }
                            else
                            {
                                ran = 0;
                            }

                            number = 1291;

                            if (ran == 1)
                            {
                                disp = checkMate (0);
                                if (disp == 1)
                                {
                                    JOptionPane.showMessageDialog (null, "CheckMate");
                                }
                            }
                            return;
                        }
                    }
                }
            }
        }
    }


    public int wknightChk (int pieceX, int pieceY)  //Method that determins if the white knight  is checking the black king
    {
        int threat = 0;
        try
        {
            if (tiles [pieceX + 2] [pieceY - 1].getIcon () == blackKing)
            {
                tiles [pieceX + 2] [pieceY - 1].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX + 2] [pieceY + 1].getIcon () == blackKing)
            {
                tiles [pieceX + 2] [pieceY + 1].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX - 2] [pieceY + 1].getIcon () == blackKing)
            {
                tiles [pieceX - 2] [pieceY + 1].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX - 2] [pieceY - 1].getIcon () == blackKing)
            {
                tiles [pieceX - 2] [pieceY - 1].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX + 1] [pieceY + 2].getIcon () == blackKing)
            {
                tiles [pieceX + 1] [pieceY + 2].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX - 1] [pieceY + 2].getIcon () == blackKing)
            {
                tiles [pieceX - 1] [pieceY + 2].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX + 1] [pieceY - 2].getIcon () == blackKing)
            {
                tiles [pieceX + 1] [pieceY - 2].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX - 1] [pieceY - 2].getIcon () == blackKing)
            {
                tiles [pieceX - 1] [pieceY - 2].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }

        if (threat == 1)
        {
            tempx = pieceX;
            tempy = pieceY;
        }
        return threat;
    }


    public int bknightChk (int pieceX, int pieceY)  //Method that determins if the black knight  is checking the white king
    {
        int threat = 0;

        try
        {
            if (tiles [pieceX + 2] [pieceY - 1].getIcon () == whiteKing)
            {
                tiles [pieceX + 2] [pieceY - 1].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX + 2] [pieceY + 1].getIcon () == whiteKing)
            {
                tiles [pieceX + 2] [pieceY + 1].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX - 2] [pieceY + 1].getIcon () == whiteKing)
            {
                tiles [pieceX - 2] [pieceY + 1].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX - 2] [pieceY - 1].getIcon () == whiteKing)
            {
                tiles [pieceX - 2] [pieceY - 1].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX + 1] [pieceY + 2].getIcon () == whiteKing)
            {
                tiles [pieceX + 1] [pieceY + 2].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX - 1] [pieceY + 2].getIcon () == whiteKing)
            {
                tiles [pieceX - 1] [pieceY + 2].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX + 1] [pieceY - 2].getIcon () == whiteKing)
            {
                tiles [pieceX + 1] [pieceY - 2].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        try
        {
            if (tiles [pieceX - 1] [pieceY - 2].getIcon () == whiteKing)
            {
                tiles [pieceX - 1] [pieceY - 2].setBackground (Color.RED);
                threat = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException p)
        {
        }
        if (threat == 1)
        {
            tempx = pieceX;
            tempy = pieceY;
        }
        return threat;
    }


    public int check (int pieceX, int pieceY, int pieceCode)  //Largest method that determines a check when the king moves. If a move is made by the king
    { //that puts it in check, it is analysed here and sent to a method that prevents the move
        int threat = 0;
        int cnt1 = 0;
        try
        {
            if (pieceCode == 0) //white King
            {
                if (pieceY < 7)
                {
                    if (tiles [pieceX - 1] [pieceY + 1].getIcon () == blackPawn) //blackPawn #1
                    {
                        threat = 1;
                        return threat;
                    }
                }

                if (pieceY > 0)
                {
                    if (tiles [pieceX - 1] [pieceY - 1].getIcon () == blackPawn) //blackPawn #2
                    {
                        threat = 1;
                        return threat;
                    }
                }
                if (pieceX < blackRookx) //blackRook
                {
                    for (int i = 1 ; i <= 7 - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {
                        }
                        if (cnt1 == 1)
                        {

                            if (tiles [pieceX + i] [pieceY].getIcon () == blackRook)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }

                if (blackRookx < pieceX)
                {
                    for (int j = 1 ; j <= pieceX ; j++)
                    {
                        if (tiles [pieceX - j] [pieceY].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {

                        }

                        if (cnt1 == 1)
                        {
                            if (tiles [pieceX - j] [pieceY].getIcon () == blackRook)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }

                if (pieceY > blackRooky)
                {
                    for (int i = 1 ; i <= pieceY ; i++)
                    {
                        if (tiles [pieceX] [pieceY - i].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {

                        }

                        if (cnt1 == 1)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () == blackRook)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }

                if (pieceY < blackRooky)
                {
                    for (int j = 1 ; j <= 7 - pieceY ; j++)
                    {
                        if (tiles [pieceX] [pieceY + j].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {
                        }
                        if (cnt1 == 1)
                        {
                            if (tiles [pieceX] [pieceY + j].getIcon () == blackRook)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }
                if (threat == 1)
                {
                    return threat;
                }
                else
                {
                }

                int row1, col1;
                row1 = Math.abs (pieceX - blackRookx1);
                col1 = Math.abs (pieceY - blackRooky1);

                if (pieceX < blackRookx1 && col1 == 0) //second black rook
                {
                    for (int i = 1 ; i <= 7 - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {

                        }
                        if (cnt1 == 1)
                        {
                            if (tiles [pieceX + i] [pieceY].getIcon () == blackRook)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }

                if (blackRookx1 < pieceX && col1 == 0)
                {
                    for (int j = 1 ; j <= pieceX ; j++)
                    {
                        if (tiles [pieceX - j] [pieceY].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {
                            cnt1 = 0;
                        }

                        if (cnt1 == 1)
                        {
                            if (tiles [pieceX - j] [pieceY].getIcon () == blackRook)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }


                if (pieceY > blackRooky1 && row1 == 0)
                {
                    for (int i = 1 ; i <= pieceY ; i++)
                    {
                        if (tiles [pieceX] [pieceY - i].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {
                            cnt1 = 0;
                        }

                        if (cnt1 == 1)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () == blackRook)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }


                if (pieceY < blackRooky1 && row1 == 0)
                {
                    for (int j = 1 ; j <= 7 - pieceY ; j++)
                    {
                        if (tiles [pieceX] [pieceY + j].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {
                            cnt1 = 0;
                        }

                        if (cnt1 == 1)
                        {
                            if (tiles [pieceX] [pieceY + j].getIcon () == blackRook)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;

                            }
                            else
                            {
                                threat = 0;
                                break;

                            }
                        }
                    }
                }
                if (threat == 1)
                {
                    return threat;
                }
                else
                {
                }

                if (pieceX < blackQueenx) //black queen - rook portion
                {
                    for (int i = 1 ; i <= 7 - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {
                            cnt1 = 0;
                        }
                        if (cnt1 == 1)
                        {

                            if (tiles [pieceX + i] [pieceY].getIcon () == blackQueen)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }

                if (blackQueenx < pieceX)
                {
                    for (int j = 1 ; j <= pieceX ; j++)
                    {
                        if (tiles [pieceX - j] [pieceY].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {
                            cnt1 = 0;
                        }

                        if (cnt1 == 1)
                        {
                            if (tiles [pieceX - j] [pieceY].getIcon () == blackQueen)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }

                if (pieceY > blackQueeny)
                {
                    for (int i = 1 ; i <= pieceY ; i++)
                    {
                        if (tiles [pieceX] [pieceY - i].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {
                            cnt1 = 0;
                        }

                        if (cnt1 == 1)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () == blackQueen)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }
                if (pieceY < blackQueeny)
                {
                    for (int j = 1 ; j <= 7 - pieceY ; j++)
                    {
                        if (tiles [pieceX] [pieceY + j].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {
                            cnt1 = 0;
                        }

                        if (cnt1 == 1)
                        {
                            if (tiles [pieceX] [pieceY + j].getIcon () == blackQueen)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;

                            }
                            else
                            {
                                threat = 0;
                                break;

                            }
                        }
                    }
                }
                if (threat == 1)
                {
                    return threat;
                }
                int i, tempsum, count;
                if (pieceY < pieceX)
                {
                    if (pieceX > blackBishx && pieceY > blackBishy) //blackBishop
                    {
                        tempsum = pieceX - pieceY;
                        for (i = 1 ; i <= pieceX - tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }

                        }
                    }

                    if (pieceX < blackBishx && pieceY < blackBishy)
                    {
                        for (i = 1 ; i <= 7 - pieceX ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }


                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX > blackBishx && pieceY < blackBishy)
                    {
                        tempsum = 7 - pieceY;

                        if (pieceX < tempsum)
                        {
                            tempsum = pieceX;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX < blackBishx && pieceY > blackBishy)
                    {
                        tempsum = 7 - pieceX;

                        if (pieceY < tempsum)
                        {
                            tempsum = pieceY;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                }
                else
                {

                    if (pieceX > blackBishx && pieceY < blackBishy)
                    {
                        tempsum = 7 - pieceY;

                        if (pieceX < tempsum)
                        {
                            tempsum = pieceX;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }
                    if (pieceX < blackBishx && pieceY < blackBishy)
                    {
                        tempsum = 7 - pieceX;

                        if (tempsum < (7 - pieceY))
                        {
                            tempsum = tempsum;
                        }
                        else
                        {
                            tempsum = (7 - pieceY);
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX < blackBishx && pieceY > blackBishy)
                    {
                        tempsum = 7 - pieceX;

                        if (pieceY < tempsum)
                        {
                            tempsum = pieceY;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX > blackBishx && pieceY > blackBishy)
                    {
                        for (i = 1 ; i <= pieceX ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (threat == 1)
                {
                    return threat;
                }
                if (pieceY < pieceX)
                {
                    if (pieceX > blackBishx1 && pieceY > blackBishy1) //second black bishop
                    {
                        tempsum = pieceX - pieceY;
                        for (i = 1 ; i <= pieceX - tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }

                        }
                    }

                    if (pieceX < blackBishx1 && pieceY < blackBishy1)
                    {
                        for (i = 1 ; i <= 7 - pieceX ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }


                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX > blackBishx1 && pieceY < blackBishy1)
                    {
                        tempsum = 7 - pieceY;

                        if (pieceX < tempsum)
                        {
                            tempsum = pieceX;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX < blackBishx1 && pieceY > blackBishy1)
                    {
                        tempsum = 7 - pieceX;

                        if (pieceY < tempsum)
                        {
                            tempsum = pieceY;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }
                }
                else
                {

                    if (pieceX > blackBishx1 && pieceY < blackBishy1)
                    {
                        tempsum = 7 - pieceY;
                        if (pieceX < tempsum)
                        {
                            tempsum = pieceX;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }
                    if (pieceX < blackBishx1 && pieceY < blackBishy1)
                    {
                        tempsum = 7 - pieceX;

                        if (tempsum < (7 - pieceY))
                        {
                            tempsum = tempsum;
                        }
                        else
                        {
                            tempsum = (7 - pieceY);
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX < blackBishx1 && pieceY > blackBishy1)
                    {
                        tempsum = 7 - pieceX;

                        if (pieceY < tempsum)
                        {
                            tempsum = pieceY;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX > blackBishx1 && pieceY > blackBishy1)
                    {
                        for (i = 1 ; i <= pieceX ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () == blackBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (threat == 1)
                {
                    return threat;
                }

                if (pieceY < pieceX)
                {
                    if (pieceX > blackQueenx && pieceY > blackQueeny) //black Queen - bishop portion
                    {
                        tempsum = pieceX - pieceY;
                        for (i = 1 ; i <= pieceX - tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () == blackQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }

                        }
                    }

                    if (pieceX < blackQueenx && pieceY < blackQueeny)
                    {
                        for (i = 1 ; i <= 7 - pieceX ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }


                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () == blackQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX > blackQueenx && pieceY < blackQueeny)
                    {
                        tempsum = 7 - pieceY;

                        if (pieceX < tempsum)
                        {
                            tempsum = pieceX;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () == blackQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX < blackQueenx && pieceY > blackQueeny)
                    {
                        tempsum = 7 - pieceX;

                        if (pieceY < tempsum)
                        {
                            tempsum = pieceY;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () == blackQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                }
                else
                {

                    if (pieceX > blackQueenx && pieceY < blackQueeny)
                    {
                        tempsum = 7 - pieceY;
                        if (pieceX < tempsum)
                        {
                            tempsum = pieceX;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () == blackQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }
                    if (pieceX < blackQueenx && pieceY < blackQueeny)
                    {
                        tempsum = 7 - pieceX;

                        if (tempsum < (7 - pieceY))
                        {
                            tempsum = tempsum;
                        }
                        else
                        {
                            tempsum = (7 - pieceY);
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () == blackQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX < blackQueenx && pieceY > blackQueeny)
                    {
                        tempsum = 7 - pieceX;

                        if (pieceY < tempsum)
                        {
                            tempsum = pieceY;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () == blackQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX > blackQueenx && pieceY > blackQueeny)
                    {
                        for (i = 1 ; i <= pieceX ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () == blackQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (threat == 1)
                {
                    return threat;
                }
                else
                {
                }

                if (promQueenFlag == 1)
                {
                    if (pieceY < pieceX)
                    {
                        if (pieceX > bPromoQueenx && pieceY > bPromoQueeny) //promotion queen
                        {
                            tempsum = pieceX - pieceY;
                            for (i = 1 ; i <= pieceX - tempsum ; i++)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX - i] [pieceY - i].getIcon () == blackQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }

                            }
                        }

                        if (pieceX < bPromoQueenx && pieceY < bPromoQueeny)
                        {
                            for (i = 1 ; i <= 7 - pieceX ; i++)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }


                                if (count == 1)
                                {
                                    if (tiles [pieceX + i] [pieceY + i].getIcon () == blackQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }

                        if (pieceX > bPromoQueenx && pieceY < bPromoQueeny)
                        {
                            tempsum = 7 - pieceY;

                            if (pieceX < tempsum)
                            {
                                tempsum = pieceX;
                            }
                            else
                            {
                                tempsum = tempsum;
                            }
                            for (i = 1 ; i <= tempsum ; i++)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX - i] [pieceY + i].getIcon () == blackQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }

                        if (pieceX < bPromoQueenx && pieceY > bPromoQueeny)
                        {
                            tempsum = 7 - pieceX;

                            if (pieceY < tempsum)
                            {
                                tempsum = pieceY;
                            }
                            else
                            {
                                tempsum = tempsum;
                            }
                            for (i = 1 ; i <= tempsum ; i++)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX + i] [pieceY - i].getIcon () == blackQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }

                    }
                    else
                    {

                        if (pieceX > bPromoQueenx && pieceY < bPromoQueeny)
                        {
                            tempsum = 7 - pieceY;

                            if (pieceX < tempsum)
                            {
                                tempsum = pieceX;
                            }
                            else
                            {
                                tempsum = tempsum;
                            }
                            for (i = 1 ; i <= tempsum ; i++)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX - i] [pieceY + i].getIcon () == blackQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }
                        if (pieceX < bPromoQueenx && pieceY < bPromoQueeny)
                        {
                            tempsum = 7 - pieceX;

                            if (tempsum < (7 - pieceY))
                            {
                                tempsum = tempsum;
                            }
                            else
                            {
                                tempsum = (7 - pieceY);
                            }
                            for (i = 1 ; i <= tempsum ; i++)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX + i] [pieceY + i].getIcon () == blackQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }

                        if (pieceX < bPromoQueenx && pieceY > bPromoQueeny)
                        {
                            tempsum = 7 - pieceX;

                            if (pieceY < tempsum)
                            {
                                tempsum = pieceY;
                            }
                            else
                            {
                                tempsum = tempsum;
                            }
                            for (i = 1 ; i <= tempsum ; i++)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX + i] [pieceY - i].getIcon () == blackQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }

                        if (pieceX > bPromoQueenx && pieceY > bPromoQueeny)
                        {
                            for (i = 1 ; i <= pieceX ; i++)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX - i] [pieceY - i].getIcon () == blackQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if (threat == 1)
                {
                    return threat;
                }
                else
                {
                }

                try
                {
                    if (tiles [pieceX + 2] [pieceY - 1].getIcon () == blackKnight) //black knight
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX + 2] [pieceY + 1].getIcon () == blackKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX - 2] [pieceY + 1].getIcon () == blackKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX - 2] [pieceY - 1].getIcon () == blackKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX + 1] [pieceY + 2].getIcon () == blackKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX - 1] [pieceY + 2].getIcon () == blackKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX + 1] [pieceY - 2].getIcon () == blackKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX - 1] [pieceY - 2].getIcon () == blackKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                if (threat == 1)
                {
                    return threat;
                }
                return threat;
            }
            else if (pieceCode == 5) //black King
            {
                if (tiles [pieceX + 1] [pieceY + 1].getIcon () == whitePawn || tiles [pieceX + 1] [pieceY - 1].getIcon () == whitePawn) //white Pawn
                {
                    threat = 1;
                    return threat;
                }


                int cnt = 0;
                if (pieceX < whiteRookx) //white Rook
                {
                    for (int i = 1 ; i <= 7 - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY].getIcon () != null)
                        {
                            cnt = 1;
                        }
                        else
                        {
                            cnt = 0;
                        }
                        if (cnt == 1)
                        {

                            if (tiles [pieceX + i] [pieceY].getIcon () == whiteRook)
                            {
                                threat = 1;
                                cnt = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }

                if (whiteRookx < pieceX)
                {
                    for (int j = 1 ; j <= pieceX ; j++)
                    {
                        if (tiles [pieceX - j] [pieceY].getIcon () != null)
                        {
                            cnt = 1;
                        }
                        else
                        {
                            cnt = 0;
                        }

                        if (cnt == 1)
                        {
                            if (tiles [pieceX - j] [pieceY].getIcon () == whiteRook)
                            {
                                threat = 1;
                                cnt = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }

                if (pieceY > whiteRooky)
                {
                    for (int i = 1 ; i <= pieceY ; i++)
                    {
                        if (tiles [pieceX] [pieceY - i].getIcon () != null)
                        {
                            cnt = 1;
                        }
                        else
                        {
                            cnt = 0;
                        }

                        if (cnt == 1)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () == whiteRook)
                            {
                                threat = 1;
                                cnt = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }


                if (pieceY < whiteRooky)
                {
                    for (int j = 1 ; j <= 7 - pieceY ; j++)
                    {
                        if (tiles [pieceX] [pieceY + j].getIcon () != null)
                        {
                            cnt = 1;
                        }
                        else
                        {
                            cnt = 0;
                        }

                        if (cnt == 1)
                        {
                            if (tiles [pieceX] [pieceY + j].getIcon () == whiteRook)
                            {
                                threat = 1;
                                cnt = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;

                            }
                        }
                    }
                }

                if (threat == 1)
                {
                    return threat;
                }

                if (pieceX < whiteRookx1) //second white rook
                {
                    for (int i = 1 ; i <= 7 - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY].getIcon () != null)
                        {
                            cnt = 1;
                        }
                        else
                        {
                            cnt = 0;
                        }
                        if (cnt == 1)
                        {

                            if (tiles [pieceX + i] [pieceY].getIcon () == whiteRook)
                            {
                                threat = 1;
                                cnt = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }


                if (whiteRookx1 < pieceX)
                {
                    for (int j = 1 ; j <= pieceX ; j++)
                    {
                        if (tiles [pieceX - j] [pieceY].getIcon () != null)
                        {
                            cnt = 1;
                        }
                        else
                        {
                            cnt = 0;
                        }

                        if (cnt == 1)
                        {
                            if (tiles [pieceX - j] [pieceY].getIcon () == whiteRook)
                            {
                                threat = 1;
                                cnt = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }


                if (pieceY > whiteRooky1)
                {
                    for (int i = 1 ; i <= pieceY ; i++)
                    {
                        if (tiles [pieceX] [pieceY - i].getIcon () != null)
                        {
                            cnt = 1;
                        }
                        else
                        {
                            cnt = 0;
                        }

                        if (cnt == 1)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () == whiteRook)
                            {
                                threat = 1;
                                cnt = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }


                if (pieceY < whiteRooky1)
                {
                    for (int j = 1 ; j <= 7 - pieceY ; j++)
                    {
                        if (tiles [pieceX] [pieceY + j].getIcon () != null)
                        {
                            cnt = 1;
                        }
                        else
                        {
                            cnt = 0;
                        }

                        if (cnt == 1)
                        {
                            if (tiles [pieceX] [pieceY + j].getIcon () == whiteRook)
                            {
                                threat = 1;
                                cnt = 2;
                                break;

                            }
                            else
                            {
                                threat = 0;
                                break;

                            }
                        }
                    }
                }
                if (threat == 1)
                {
                    return threat;
                }


                if (pieceX < whiteQueenx) //white queen rook portion
                {
                    for (int i = 1 ; i <= 7 - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY].getIcon () != null)
                        {
                            cnt1 = 1;

                        }
                        else
                        {
                            cnt1 = 0;
                        }
                        if (cnt1 == 1)
                        {

                            if (tiles [pieceX + i] [pieceY].getIcon () == whiteQueen)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }


                if (whiteQueenx < pieceX)
                {
                    for (int j = 1 ; j <= pieceX ; j++)
                    {
                        if (tiles [pieceX - j] [pieceY].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {
                            cnt1 = 0;
                        }

                        if (cnt1 == 1)
                        {
                            if (tiles [pieceX - j] [pieceY].getIcon () == whiteQueen)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }

                if (pieceY > whiteQueeny)
                {
                    for (int i = 1 ; i <= pieceY ; i++)
                    {
                        if (tiles [pieceX] [pieceY - i].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {
                            cnt1 = 0;
                        }

                        if (cnt1 == 1)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () == whiteQueen)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;
                            }
                            else
                            {
                                threat = 0;
                                break;
                            }
                        }
                    }
                }


                if (pieceY < whiteQueeny)
                {
                    for (int j = 1 ; j <= 7 - pieceY ; j++)
                    {
                        if (tiles [pieceX] [pieceY + j].getIcon () != null)
                        {
                            cnt1 = 1;
                        }
                        else
                        {
                            cnt1 = 0;
                        }

                        if (cnt1 == 1)
                        {
                            if (tiles [pieceX] [pieceY + j].getIcon () == whiteQueen)
                            {
                                threat = 1;
                                cnt1 = 2;
                                break;

                            }
                            else
                            {
                                threat = 0;
                                break;

                            }
                        }
                    }
                }
                if (threat == 1)
                {
                    return threat;
                }

                int i, tempsum, count;
                if (pieceY < pieceX)
                {
                    if (pieceX > whiteBishx && pieceY > whiteBishy) //white bishop
                    {
                        tempsum = pieceX - pieceY;
                        for (i = 1 ; i <= pieceX - tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX < whiteBishx && pieceY < whiteBishy)
                    {
                        for (i = 1 ; i <= 7 - pieceX ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX > whiteBishx && pieceY < whiteBishy)
                    {
                        tempsum = 7 - pieceY;

                        if (pieceX < tempsum)
                        {
                            tempsum = pieceX;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX < whiteBishx && pieceY > whiteBishy)
                    {
                        tempsum = 7 - pieceX;

                        if (pieceY < tempsum)
                        {
                            tempsum = pieceY;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                }
                else
                {

                    if (pieceX > whiteBishx && pieceY < whiteBishy)
                    {
                        tempsum = 7 - pieceY;
                        if (pieceX < tempsum)
                        {
                            tempsum = pieceX;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }
                    if (pieceX < whiteBishx && pieceY < whiteBishy)
                    {
                        tempsum = 7 - pieceX;

                        if (tempsum < (7 - pieceY))
                        {
                            tempsum = tempsum;
                        }
                        else
                        {
                            tempsum = (7 - pieceY);
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX < whiteBishx && pieceY > whiteBishy)
                    {
                        tempsum = 7 - pieceX;

                        if (pieceY < tempsum)
                        {
                            tempsum = pieceY;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX > whiteBishx && pieceY > whiteBishy)
                    {
                        for (i = 1 ; i <= pieceX ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (threat == 1)
                {
                    return threat;
                }


                if (pieceY < pieceX)
                {
                    if (pieceX > whiteBishx1 && pieceY > whiteBishy1) //second white bishop
                    {
                        tempsum = pieceX - pieceY;
                        for (i = 1 ; i <= pieceX - tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }

                        }
                    }

                    if (pieceX < whiteBishx1 && pieceY < whiteBishy1)
                    {
                        for (i = 1 ; i <= 7 - pieceX ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }


                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX > whiteBishx1 && pieceY < whiteBishy1)
                    {
                        tempsum = 7 - pieceY;

                        if (pieceX < tempsum)
                        {
                            tempsum = pieceX;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX < whiteBishx1 && pieceY > whiteBishy1)
                    {
                        tempsum = 7 - pieceX;

                        if (pieceY < tempsum)
                        {
                            tempsum = pieceY;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                }
                else
                {

                    if (pieceX > whiteBishx1 && pieceY < whiteBishy1)
                    {
                        tempsum = 7 - pieceY;
                        if (pieceX < tempsum)
                        {
                            tempsum = pieceX;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }
                    if (pieceX < whiteBishx1 && pieceY < whiteBishy1)
                    {
                        tempsum = 7 - pieceX;

                        if (tempsum < (7 - pieceY))
                        {
                            tempsum = tempsum;
                        }
                        else
                        {
                            tempsum = (7 - pieceY);
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX < whiteBishx1 && pieceY > whiteBishy1)
                    {
                        tempsum = 7 - pieceX;

                        if (pieceY < tempsum)
                        {
                            tempsum = pieceY;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX > whiteBishx1 && pieceY > whiteBishy1)
                    {
                        for (i = 1 ; i <= pieceX ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () == whiteBish)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (threat == 1)
                {
                    return threat;
                }


                if (pieceY < pieceX)
                {
                    if (pieceX > whiteQueenx && pieceY > whiteQueeny) //white queen bishop portion
                    {
                        tempsum = pieceX - pieceY;
                        for (i = 1 ; i <= pieceX - tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () == whiteQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }

                        }
                    }

                    if (pieceX < whiteQueenx && pieceY < whiteQueeny)
                    {
                        for (i = 1 ; i <= 7 - pieceX ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }


                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () == whiteQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX > whiteQueenx && pieceY < whiteQueeny)
                    {
                        tempsum = 7 - pieceY;

                        if (pieceX < tempsum)
                        {
                            tempsum = pieceX;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () == whiteQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX < whiteQueenx && pieceY > whiteQueeny)
                    {
                        tempsum = 7 - pieceX;

                        if (pieceY < tempsum)
                        {
                            tempsum = pieceY;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () == whiteQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                }
                else
                {

                    if (pieceX > whiteQueenx && pieceY < whiteQueeny)
                    {
                        tempsum = 7 - pieceY;

                        if (pieceX < tempsum)
                        {
                            tempsum = pieceX;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () == whiteQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }
                    if (pieceX < whiteQueenx && pieceY < whiteQueeny)
                    {
                        tempsum = 7 - pieceX;

                        if (tempsum < (7 - pieceY))
                        {
                            tempsum = tempsum;
                        }
                        else
                        {
                            tempsum = (7 - pieceY);
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () == whiteQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                }
                                else
                                {
                                    threat = 0;
                                }
                            }
                        }
                    }

                    if (pieceX < whiteQueenx && pieceY > whiteQueeny)
                    {
                        tempsum = 7 - pieceX;

                        if (pieceY < tempsum)
                        {
                            tempsum = pieceY;
                        }
                        else
                        {
                            tempsum = tempsum;
                        }
                        for (i = 1 ; i <= tempsum ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () == whiteQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }

                    if (pieceX > whiteQueenx && pieceY > whiteQueeny)
                    {
                        for (i = 1 ; i <= pieceX ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                            {
                                count = 1;
                            }
                            else
                            {
                                count = 0;
                            }

                            if (count == 1)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () == whiteQueen)
                                {
                                    threat = 1;
                                    count = 2;
                                    break;
                                }
                                else
                                {
                                    threat = 0;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (threat == 1)
                {
                    return threat;
                }
                else
                {
                }

                if (promQueenFlag == 1)
                {

                    if (pieceY < pieceX)
                    {
                        if (pieceX > promoQueenx && pieceY > promoQueeny) //promoted white queen, both rook and sishop portions
                        {
                            tempsum = pieceX - pieceY;
                            for (i = 1 ; i <= pieceX - tempsum ; i++)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX - i] [pieceY - i].getIcon () == whiteQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }

                            }
                        }

                        if (pieceX < promoQueenx && pieceY < promoQueeny)
                        {
                            for (i = 1 ; i <= 7 - pieceX ; i++)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }


                                if (count == 1)
                                {
                                    if (tiles [pieceX + i] [pieceY + i].getIcon () == whiteQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }

                        if (pieceX > promoQueenx && pieceY < promoQueeny)
                        {
                            tempsum = 7 - pieceY;

                            if (pieceX < tempsum)
                            {
                                tempsum = pieceX;
                            }
                            else
                            {
                                tempsum = tempsum;
                            }
                            for (i = 1 ; i <= tempsum ; i++)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX - i] [pieceY + i].getIcon () == whiteQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }

                        if (pieceX < promoQueenx && pieceY > promoQueeny)
                        {
                            tempsum = 7 - pieceX;

                            if (pieceY < tempsum)
                            {
                                tempsum = pieceY;
                            }
                            else
                            {
                                tempsum = tempsum;
                            }
                            for (i = 1 ; i <= tempsum ; i++)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX + i] [pieceY - i].getIcon () == whiteQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }

                    }
                    else
                    {

                        if (pieceX > promoQueenx && pieceY < promoQueeny)
                        {
                            tempsum = 7 - pieceY;

                            if (pieceX < tempsum)
                            {
                                tempsum = pieceX;
                            }
                            else
                            {
                                tempsum = tempsum;
                            }
                            for (i = 1 ; i <= tempsum ; i++)
                            {
                                if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX - i] [pieceY + i].getIcon () == whiteQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }
                        if (pieceX < promoQueenx && pieceY < promoQueeny)
                        {
                            tempsum = 7 - pieceX;

                            if (tempsum < (7 - pieceY))
                            {
                                tempsum = tempsum;
                            }
                            else
                            {
                                tempsum = (7 - pieceY);
                            }
                            for (i = 1 ; i <= tempsum ; i++)
                            {
                                if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX + i] [pieceY + i].getIcon () == whiteQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }

                        if (pieceX < promoQueenx && pieceY > promoQueeny)
                        {
                            tempsum = 7 - pieceX;

                            if (pieceY < tempsum)
                            {
                                tempsum = pieceY;
                            }
                            else
                            {
                                tempsum = tempsum;
                            }
                            for (i = 1 ; i <= tempsum ; i++)
                            {
                                if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX + i] [pieceY - i].getIcon () == whiteQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }

                        if (pieceX > promoQueenx && pieceY > promoQueeny)
                        {
                            for (i = 1 ; i <= pieceX ; i++)
                            {
                                if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                                {
                                    count = 1;
                                }
                                else
                                {
                                    count = 0;
                                }

                                if (count == 1)
                                {
                                    if (tiles [pieceX - i] [pieceY - i].getIcon () == whiteQueen)
                                    {
                                        threat = 1;
                                        count = 2;
                                        break;
                                    }
                                    else
                                    {
                                        threat = 0;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (threat == 1)
                    {
                        return threat;
                    }
                }

                try
                {
                    if (tiles [pieceX + 2] [pieceY - 1].getIcon () == whiteKnight) //white knight
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX + 2] [pieceY + 1].getIcon () == whiteKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX - 2] [pieceY + 1].getIcon () == whiteKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX - 2] [pieceY - 1].getIcon () == whiteKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX + 1] [pieceY + 2].getIcon () == whiteKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX - 1] [pieceY + 2].getIcon () == whiteKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX + 1] [pieceY - 2].getIcon () == whiteKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }
                try
                {
                    if (tiles [pieceX - 1] [pieceY - 2].getIcon () == whiteKnight)
                    {
                        threat = 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException p)
                {
                }

                if (pieceCode == 5)
                {
                    try
                    {
                        if (tiles [pieceX] [pieceY + 1].getIcon () == whiteKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException p)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX] [pieceY - 1].getIcon () == whiteKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException p)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX - 1] [pieceY + 1].getIcon () == whiteKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException p)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX - 1] [pieceY - 1].getIcon () == whiteKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException p)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX - 1] [pieceY].getIcon () == whiteKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException p)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX + 1] [pieceY].getIcon () == whiteKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException p)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX + 1] [pieceY + 1].getIcon () == whiteKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException p)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX + 1] [pieceY - 1].getIcon () == whiteKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException p)
                    {
                    }
                }
                else if (pieceCode == 0)
                {
                    try
                    {
                        if (tiles [pieceX] [pieceY + 1].getIcon () == blackKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException q)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX] [pieceY - 1].getIcon () == blackKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException q)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX - 1] [pieceY + 1].getIcon () == blackKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException q)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX - 1] [pieceY - 1].getIcon () == blackKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException q)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX - 1] [pieceY].getIcon () == blackKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException q)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX + 1] [pieceY].getIcon () == blackKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException q)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX + 1] [pieceY + 1].getIcon () == blackKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException q)
                    {
                    }
                    try
                    {
                        if (tiles [pieceX + 1] [pieceY - 1].getIcon () == blackKing)
                        {
                            threat = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException q)
                    {
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException es)
        {
        }
        return threat;
    }


    public int rook1Chk (int pieceX, int pieceY)  //method that determines whether the white rook checks the black king
    {
        row = pieceX - whiteRookx;
        col = pieceY - whiteRooky;
        int cnt = 0;
        int num = 1;

        if (num == 1)
        {
            for (int i = 1 ; i <= 7 - pieceX ; i++)
            {
                if (tiles [pieceX + i] [pieceY].getIcon () != null)
                {
                    cnt = 1;
                }
                else
                {
                    cnt = 0;
                }
                if (cnt == 1)
                {

                    if (tiles [pieceX + i] [pieceY].getIcon () == blackKing)
                    {
                        tiles [pieceX + i] [pieceY].setBackground (Color.red);
                        threat = 1;
                        cnt = 2;
                        ran1 = 1;

                        tempx = pieceX;
                        tempy = pieceY;

                        return threat;
                    }
                    else if (tiles [pieceX + i] [pieceY].getIcon () == whiteKing)
                    {
                        cnt = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran1 = 0;
                        break;
                    }
                }
            }
        }


        if (num == 1)
        {
            for (int j = 1 ; j <= pieceX ; j++)
            {
                if (tiles [pieceX - j] [pieceY].getIcon () != null)
                {
                    cnt = 1;
                }
                else
                {
                    cnt = 0;
                }

                if (cnt == 1)
                {
                    if (tiles [pieceX - j] [pieceY].getIcon () == blackKing)
                    {
                        tiles [pieceX - j] [pieceY].setBackground (Color.red);
                        threat = 1;
                        ran1 = 1;
                        cnt = 2;

                        tempx = pieceX;
                        tempy = pieceY;

                        return threat;
                    }
                    else if (tiles [pieceX - j] [pieceY].getIcon () == whiteKing)
                    {
                        cnt = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran1 = 0;
                        break;
                    }
                }
            }
        }


        if (num == 1)
        {
            for (int i = 1 ; i <= pieceY ; i++)
            {
                if (tiles [pieceX] [pieceY - i].getIcon () != null)
                {
                    cnt = 1;
                }
                else
                {
                    cnt = 0;
                }

                if (cnt == 1)
                {
                    if (tiles [pieceX] [pieceY - i].getIcon () == blackKing)
                    {
                        tiles [pieceX] [pieceY - i].setBackground (Color.red);
                        threat = 1;
                        ran1 = 1;
                        cnt = 2;

                        tempx = pieceX;
                        tempy = pieceY;

                        return threat;
                    }
                    else if (tiles [pieceX] [pieceY - i].getIcon () == whiteKing)
                    {
                        cnt = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran1 = 0;
                        break;
                    }
                }
            }
        }


        if (num == 1)
        {
            for (int j = 1 ; j <= 7 - pieceY ; j++)
            {
                if (tiles [pieceX] [pieceY + j].getIcon () != null)
                {
                    cnt = 1;
                }
                else
                {
                    cnt = 0;
                }

                if (cnt == 1)
                {
                    if (tiles [pieceX] [pieceY + j].getIcon () == blackKing)
                    {
                        tiles [pieceX] [pieceY + j].setBackground (Color.red);
                        threat = 1;
                        ran1 = 1;
                        cnt = 2;

                        tempx = pieceX;
                        tempy = pieceY;

                        return threat;
                    }
                    else if (tiles [pieceX] [pieceY + j].getIcon () == whiteKing)
                    {
                        cnt = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran1 = 0;
                        break;
                    }
                }
            }
        }


        tempx = pieceX;
        tempy = pieceY;

        return threat;
    }


    public int rook2Chk (int pieceX, int pieceY)  //method that determines whether the black rook checks the white king
    {
        row = pieceX - blackRookx;
        col = pieceY - blackRooky;
        int cnt = 0;
        int num = 1;

        if (num == 1)
        {
            for (int i = 1 ; i <= 7 - pieceX ; i++)
            {
                if (tiles [pieceX + i] [pieceY].getIcon () != null)
                {
                    cnt = 1;
                }
                else
                {
                    cnt = 0;
                }
                if (cnt == 1)
                {

                    if (tiles [pieceX + i] [pieceY].getIcon () == whiteKing)
                    {
                        tiles [pieceX + i] [pieceY].setBackground (Color.red);
                        threat = 1;
                        cnt = 2;
                        ran = 1;
                        return threat;
                    }
                    else if (tiles [pieceX + i] [pieceY].getIcon () == blackKing)
                    {
                        cnt = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran = 0;
                        break;
                    }
                }
            }
        }


        if (num == 1)
        {
            for (int j = 1 ; j <= pieceX ; j++)
            {
                if (tiles [pieceX - j] [pieceY].getIcon () != null)
                {
                    cnt = 1;
                }
                else
                {
                    cnt = 0;
                }

                if (cnt == 1)
                {
                    if (tiles [pieceX - j] [pieceY].getIcon () == whiteKing)
                    {
                        tiles [pieceX - j] [pieceY].setBackground (Color.red);
                        threat = 1;
                        ran = 1;
                        cnt = 2;
                        return threat;
                    }
                    else if (tiles [pieceX - j] [pieceY].getIcon () == blackKing)
                    {
                        cnt = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran = 0;
                        break;
                    }
                }
            }
        }


        if (num == 1)
        {
            for (int i = 1 ; i <= pieceY ; i++)
            {
                if (tiles [pieceX] [pieceY - i].getIcon () != null)
                {
                    cnt = 1;
                }
                else
                {
                    cnt = 0;
                }

                if (cnt == 1)
                {
                    if (tiles [pieceX] [pieceY - i].getIcon () == whiteKing)
                    {
                        tiles [pieceX] [pieceY - i].setBackground (Color.red);
                        threat = 1;
                        ran = 1;
                        cnt = 2;
                        return threat;
                    }
                    else if (tiles [pieceX] [pieceY - i].getIcon () == blackKing)
                    {
                        cnt = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran = 0;
                        break;
                    }
                }
            }
        }


        if (num == 1)
        {
            for (int j = 1 ; j <= 7 - pieceY ; j++)
            {
                if (tiles [pieceX] [pieceY + j].getIcon () != null)
                {
                    cnt = 1;
                }
                else
                {
                    cnt = 0;
                }

                if (cnt == 1)
                {
                    if (tiles [pieceX] [pieceY + j].getIcon () == whiteKing)
                    {
                        tiles [pieceX] [pieceY + j].setBackground (Color.red);
                        threat = 1;
                        ran = 1;
                        cnt = 2;
                        return threat;
                    }
                    else if (tiles [pieceX] [pieceY + j].getIcon () == blackKing)
                    {
                        cnt = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran = 0;
                        break;
                    }
                }
            }
        }
        if (threat == 1)
        {
            tempx = pieceX;
            tempy = pieceY;
        }
        return threat;
    }


    public int wBishChk (int pieceX, int pieceY)  //method that determines whether the white bishop checks the black king
    {
        int i, tempsum, count;
        if (pieceY < pieceX)
        {
            tempsum = pieceX - pieceY;
            for (i = 1 ; i <= pieceX - tempsum ; i++)
            {
                if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX - i] [pieceY - i].getIcon () == blackKing)
                    {
                        tiles [pieceX - i] [pieceY - i].setBackground (Color.red);
                        threat = 1;
                        ran1 = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX - i] [pieceY - i].getIcon () == whiteKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran1 = 0;
                        break;
                    }
                }

            }
            for (i = 1 ; i <= 7 - pieceX ; i++)
            {
                if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }


                if (count == 1)
                {
                    if (tiles [pieceX + i] [pieceY + i].getIcon () == blackKing)
                    {
                        tiles [pieceX + i] [pieceY + i].setBackground (Color.red);
                        threat = 1;
                        ran1 = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX + i] [pieceY + i].getIcon () == whiteKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran1 = 0;
                        break;
                    }
                }
            }
            tempsum = 7 - pieceY;

            if (pieceX < tempsum)
            {
                tempsum = pieceX;
            }
            else
            {
                tempsum = tempsum;
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX - i] [pieceY + i].getIcon () == blackKing)
                    {
                        tiles [pieceX - i] [pieceY + i].setBackground (Color.red);
                        threat = 1;
                        ran1 = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX - i] [pieceY + i].getIcon () == whiteKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran1 = 0;
                        break;
                    }
                }
            }
            tempsum = 7 - pieceX;

            if (pieceY < tempsum)
            {
                tempsum = pieceY;
            }
            else
            {
                tempsum = tempsum;
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX + i] [pieceY - i].getIcon () == blackKing)
                    {
                        tiles [pieceX + i] [pieceY - i].setBackground (Color.red);
                        threat = 1;
                        ran1 = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX + i] [pieceY - i].getIcon () == whiteKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran1 = 0;
                        break;
                    }
                }
            }

        }


        else
        {
            tempsum = 7 - pieceY;

            if (pieceX < tempsum)
            {
                tempsum = pieceX;
            }
            else
            {
                tempsum = tempsum;
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX - i] [pieceY + i].getIcon () == blackKing)
                    {
                        tiles [pieceX - i] [pieceY + i].setBackground (Color.red);
                        threat = 1;
                        ran1 = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX - i] [pieceY + i].getIcon () == whiteKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran1 = 0;
                        break;
                    }
                }
            }
            tempsum = 7 - pieceX;

            if (tempsum < (7 - pieceY))
            {
                tempsum = tempsum;
            }
            else
            {
                tempsum = (7 - pieceY);
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX + i] [pieceY + i].getIcon () == blackKing)
                    {
                        tiles [pieceX + i] [pieceY + i].setBackground (Color.red);
                        threat = 1;
                        ran1 = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX + i] [pieceY + i].getIcon () == whiteKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran1 = 0;
                        break;
                    }
                }
            }
            tempsum = 7 - pieceX;

            if (pieceY < tempsum)
            {
                tempsum = pieceY;
            }
            else
            {
                tempsum = tempsum;
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX + i] [pieceY - i].getIcon () == blackKing)
                    {
                        tiles [pieceX + i] [pieceY - i].setBackground (Color.red);
                        threat = 1;
                        ran1 = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX + i] [pieceY - i].getIcon () == whiteKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran1 = 0;
                        break;
                    }
                }
            }


            for (i = 1 ; i <= pieceX ; i++)
            {
                if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX - i] [pieceY - i].getIcon () == blackKing)
                    {
                        tiles [pieceX - i] [pieceY - i].setBackground (Color.red);
                        threat = 1;
                        ran1 = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX - i] [pieceY - i].getIcon () == whiteKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran1 = 0;
                        break;
                    }
                }
            }
        }


        if (threat == 1)
        {
            tempx = pieceX;
            tempy = pieceY;
        }


        return threat;
    }


    public int bBishChk (int pieceX, int pieceY)  //method that determines whether the black bishop checks the white king
    {
        int i, tempsum, count;
        if (pieceY < pieceX)
        {
            tempsum = pieceX - pieceY;
            for (i = 1 ; i <= pieceX - tempsum ; i++)
            {
                if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX - i] [pieceY - i].getIcon () == whiteKing)
                    {
                        tiles [pieceX - i] [pieceY - i].setBackground (Color.red);
                        threat = 1;
                        ran = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX - i] [pieceY - i].getIcon () == blackKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran = 0;
                        break;
                    }
                }

            }
            for (i = 1 ; i <= 7 - pieceX ; i++)
            {
                if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }


                if (count == 1)
                {
                    if (tiles [pieceX + i] [pieceY + i].getIcon () == whiteKing)
                    {
                        tiles [pieceX + i] [pieceY + i].setBackground (Color.red);
                        threat = 1;
                        ran = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX + i] [pieceY + i].getIcon () == blackKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran = 0;
                        break;
                    }
                }
            }
            tempsum = 7 - pieceY;

            if (pieceX < tempsum)
            {
                tempsum = pieceX;
            }
            else
            {
                tempsum = tempsum;
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX - i] [pieceY + i].getIcon () == whiteKing)
                    {
                        tiles [pieceX - i] [pieceY + i].setBackground (Color.red);
                        threat = 1;
                        ran = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX - i] [pieceY + i].getIcon () == blackKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran = 0;
                        break;
                    }
                }
            }
            tempsum = 7 - pieceX;

            if (pieceY < tempsum)
            {
                tempsum = pieceY;
            }
            else
            {
                tempsum = tempsum;
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX + i] [pieceY - i].getIcon () == whiteKing)
                    {
                        tiles [pieceX + i] [pieceY - i].setBackground (Color.red);
                        threat = 1;
                        ran = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX + i] [pieceY - i].getIcon () == blackKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran = 0;
                        break;
                    }
                }
            }

        }
        else
        {
            tempsum = 7 - pieceY;

            if (pieceX < tempsum)
            {
                tempsum = pieceX;
            }
            else
            {
                tempsum = tempsum;
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX - i] [pieceY + i].getIcon () == whiteKing)
                    {
                        tiles [pieceX - i] [pieceY + i].setBackground (Color.red);
                        threat = 1;
                        ran = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX - i] [pieceY + i].getIcon () == blackKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran = 0;
                        break;
                    }
                }
            }
            tempsum = 7 - pieceX;

            if (tempsum < (7 - pieceY))
            {
                tempsum = tempsum;
            }
            else
            {
                tempsum = (7 - pieceY);
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX + i] [pieceY + i].getIcon () == whiteKing)
                    {
                        tiles [pieceX + i] [pieceY + i].setBackground (Color.red);
                        threat = 1;
                        ran = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX + i] [pieceY + i].getIcon () == blackKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran = 0;
                        break;
                    }
                }
            }
            tempsum = 7 - pieceX;

            if (pieceY < tempsum)
            {
                tempsum = pieceY;
            }
            else
            {
                tempsum = tempsum;
            }
            for (i = 1 ; i <= tempsum ; i++)
            {
                if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX + i] [pieceY - i].getIcon () == whiteKing)
                    {
                        tiles [pieceX + i] [pieceY - i].setBackground (Color.red);
                        threat = 1;
                        ran = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX + i] [pieceY - i].getIcon () == blackKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran = 0;
                        break;
                    }
                }
            }


            for (i = 1 ; i <= pieceX ; i++)
            {
                if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                {
                    count = 1;
                }
                else
                {
                    count = 0;
                }

                if (count == 1)
                {
                    if (tiles [pieceX - i] [pieceY - i].getIcon () == whiteKing)
                    {
                        tiles [pieceX - i] [pieceY - i].setBackground (Color.red);
                        threat = 1;
                        ran = 1;
                        count = 2;
                        tempx = pieceX;
                        tempy = pieceY;
                        return threat;
                    }
                    else if (tiles [pieceX - i] [pieceY - i].getIcon () == blackKing)
                    {
                        count = 2;
                        break;
                    }
                    else
                    {
                        threat = 0;
                        ran = 0;
                        break;
                    }
                }
            }
        }


        if (threat == 1)
        {
            tempx = pieceX;
            tempy = pieceY;
        }


        return threat;
    }


    public int wPawnChk (int pieceX, int pieceY)  //method that determines whether the white pawn checks the black king
    {
        try
        {
            if (pieceX > 0)
            {
                if (pieceY < 8)
                {
                    if (tiles [pieceX - 1] [pieceY + 1].getIcon () == blackKing)
                    {
                        threat = 1;
                        tiles [pieceX - 1] [pieceY + 1].setBackground (Color.red);
                        ran1 = 1;
                    }
                }
                if (pieceY > 0)
                {
                    if (tiles [pieceX - 1] [pieceY - 1].getIcon () == blackKing)
                    {
                        threat = 1;
                        tiles [pieceX - 1] [pieceY - 1].setBackground (Color.red);
                        ran1 = 1;
                    }
                }
            }
            if (threat == 1)
            {
                tempx = pieceX;
                tempy = pieceY;
            }
        }
        catch (ArrayIndexOutOfBoundsException Q)
        {
        }
        return threat;
    }


    public int bPawnChk (int pieceX, int pieceY)  //method that determines whether the black pawn checks the black king
    {
        try
        {
            if (pieceX < 7)
            {
                if (pieceY < 8)
                {
                    if (tiles [pieceX + 1] [pieceY + 1].getIcon () == whiteKing)
                    {
                        threat = 1;
                        tiles [pieceX + 1] [pieceY + 1].setBackground (Color.red);
                        ran = 1;
                    }
                }
                if (pieceY > 0)
                {
                    if (tiles [pieceX + 1] [pieceY - 1].getIcon () == whiteKing)
                    {
                        threat = 1;
                        tiles [pieceX + 1] [pieceY - 1].setBackground (Color.red);
                        ran = 1;
                    }
                }
            }
            if (threat == 1)
            {
                tempx = pieceX;
                tempy = pieceY;
            }
        }
        catch (ArrayIndexOutOfBoundsException U)
        {

        }
        return threat;
    }



    public int checkMate (int pieceCode)  //method that is called once a king has been put it check. This methods checks for potential CheckMates
    {
        int pieceXplus = whiteKingy + 1;
        int pieceYplus = whiteKingx + 1;
        int pieceXminus = whiteKingy - 1;
        int pieceYminus = whiteKingx - 1;

        int bpieceXplus = blackKingx + 1;
        int bpieceYplus = blackKingy + 1;
        int bpieceXminus = blackKingx - 1;
        int bpieceYminus = blackKingy - 1;

        if (pieceCode == 0) //white King
        {
            //First, the method checks the 8 possible moves for the king to see if the king can exscape checkmate by moving itself, invalid moves are not considered
            try
            {
                checkMate1 = check (pieceXplus, whiteKingx, pieceCode);
                wValid = validCheck (pieceXplus, whiteKingx, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate1 = 1;
            }

            try
            {
                checkMate2 = check (pieceXminus, whiteKingx, pieceCode);
                wValid1 = validCheck (pieceXminus, whiteKingx, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate2 = 1;
            }

            try
            {
                checkMate3 = check (whiteKingy, pieceYplus, pieceCode);
                wValid2 = validCheck (whiteKingy, pieceYplus, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate3 = 1;
            }

            try
            {
                checkMate4 = check (whiteKingy, pieceYminus, pieceCode);
                wValid3 = validCheck (whiteKingy, pieceYminus, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate4 = 1;
            }

            try
            {
                checkMate5 = check (pieceXplus, pieceYplus, pieceCode);
                wValid4 = validCheck (pieceXplus, pieceYplus, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate5 = 1;
            }

            try
            {
                checkMate6 = check (pieceXminus, pieceYminus, pieceCode);
                wValid5 = validCheck (pieceXminus, pieceYminus, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate6 = 1;
            }
            try
            {
                checkMate7 = check (pieceXplus, pieceYminus, pieceCode);
                wValid6 = validCheck (pieceXplus, pieceYminus, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate7 = 1;
            }

            try
            {
                checkMate = check (pieceXminus, pieceYplus, pieceCode);
                wValid7 = validCheck (pieceXminus, pieceYplus, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate = 1;
            }

            if (wValid == 0)
            {
                checkMate1 = 1;
            }
            if (wValid1 == 0)
            {
                checkMate2 = 1;
            }
            if (wValid2 == 0)
            {
                checkMate3 = 1;
            }
            if (wValid3 == 0)
            {
                checkMate4 = 1;
            }
            if (wValid4 == 0)
            {
                checkMate5 = 1;
            }
            if (wValid5 == 0)
            {
                checkMate6 = 1;
            }
            if (wValid6 == 0)
            {
                checkMate7 = 1;
            }
            if (wValid7 == 0)
            {
                checkMate = 1;
            }

            //if all of the possible moves of the king would also put it in check, the method checks if the attacking piece can be captured.
            //here, the variables tempx and tempy are the values of the attacking piece. When a piece puts a king in check, these values are assigned
            //to it in its specific method
            //finalCheckMt is the final value of checkmate that is used to determien whether a checkmate has occured or not
            if (checkMate == 1 && checkMate1 == 1 && checkMate2 == 1 && checkMate3 == 1 && checkMate4 == 1 && checkMate5 == 1 && checkMate6 == 1 && checkMate7 == 1)
            {
                finalCheckMt = 1;
                if ((whiteKnightx + 2 == tempx && whiteKnighty + 1 == tempy) || (whiteKnightx - 2 == tempx && whiteKnighty - 1 == tempy) || (whiteKnightx - 2 == tempx && whiteKnighty + 1 == tempy) || (whiteKnightx + 2 == tempx && whiteKnighty - 1 == tempy) || (whiteKnightx + 1 == tempx && whiteKnighty + 2 == tempy) || (whiteKnightx - 1 == tempx && whiteKnighty - 2 == tempy) || (whiteKnightx - 1 == tempx && whiteKnighty + 2 == tempy) || (whiteKnightx + 1 == tempx && whiteKnighty - 2 == tempy))
                {
                    finalCheckMt = 0;
                    return finalCheckMt;
                }
                if ((whiteKnightx1 + 2 == tempx && whiteKnighty1 + 1 == tempy) || (whiteKnightx1 - 2 == tempx && whiteKnighty1 - 1 == tempy) || (whiteKnightx1 - 2 == tempx && whiteKnighty1 + 1 == tempy) || (whiteKnightx1 + 2 == tempx && whiteKnighty1 - 1 == tempy) || (whiteKnightx1 + 1 == tempx && whiteKnighty1 + 2 == tempy) || (whiteKnightx1 - 1 == tempx && whiteKnighty1 - 2 == tempy) || (whiteKnightx1 - 1 == tempx && whiteKnighty1 + 2 == tempy) || (whiteKnightx1 + 1 == tempx && whiteKnighty1 - 2 == tempy))
                {
                    finalCheckMt = 0;
                    return finalCheckMt;
                }
                for (int i = 0 ; i < 8 ; i++)
                {
                    if ((whiteBishx + i == tempx && whiteBishy + i == tempy) || (whiteBishx - i == tempx && whiteBishy + i == tempy) || (whiteBishx + i == tempx && whiteBishy - i == tempy) || (whiteBishx - i == tempx && whiteBishy - i == tempy))
                    {
                        if (tempy < whiteBishy && tempx < whiteBishx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteBishx - u] [whiteBishy - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > whiteBishy && tempx > whiteBishx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteBishx + u] [whiteBishy + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > whiteBishy && tempx < whiteBishx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteBishx - u] [whiteBishy + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy < whiteBishy && tempx > whiteBishx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteBishx + u] [whiteBishy - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (finalCheckMt == 0)
                {
                    return finalCheckMt;
                }
                for (int i = 0 ; i < 8 ; i++)
                {
                    if ((whiteBishx1 + i == tempx && whiteBishy1 + i == tempy) || (whiteBishx1 - i == tempx && whiteBishy1 + i == tempy) || (whiteBishx1 + i == tempx && whiteBishy1 - i == tempy) || (whiteBishx1 - i == tempx && whiteBishy1 - i == tempy))
                    {
                        if (tempy < whiteBishy1 && tempx < whiteBishx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteBishx1 - u] [whiteBishy1 - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > whiteBishy1 && tempx > whiteBishx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteBishx1 + u] [whiteBishy1 + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > whiteBishy1 && tempx < whiteBishx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteBishx1 - u] [whiteBishy1 + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy < whiteBishy1 && tempx > whiteBishx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteBishx1 + u] [whiteBishy1 - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (finalCheckMt == 0)
                {
                    return finalCheckMt;
                }
                for (int i = 0 ; i < 8 ; i++)
                {
                    if ((whiteRookx + i == tempx && whiteRooky == tempy) || (whiteRookx - i == tempx && whiteRooky == tempy) || (whiteRookx == tempx && whiteRooky + i == tempy) || (whiteRookx == tempx && whiteRooky - i == tempy))
                    {
                        if (tempy == whiteRooky && tempx < whiteRookx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteRookx - u] [whiteRooky].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy == whiteRooky && tempx > whiteRookx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteRookx + u] [whiteRooky].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > whiteRooky && tempx == whiteRookx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteBishx] [whiteRooky + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy < whiteRooky && tempx == whiteRookx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteRookx] [whiteRooky - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (finalCheckMt == 0)
                {
                    return finalCheckMt;
                }
                for (int i = 0 ; i < 8 ; i++)
                {
                    if ((whiteRookx1 + i == tempx && whiteRooky1 == tempy) || (whiteRookx1 - i == tempx && whiteRooky1 == tempy) || (whiteRookx1 == tempx && whiteRooky1 + i == tempy) || (whiteRookx1 == tempx && whiteRooky1 - i == tempy))
                    {
                        if (tempy == whiteRooky1 && tempx < whiteRookx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteRookx1 - u] [whiteRooky1].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy == whiteRooky1 && tempx > whiteRookx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteRookx1 + u] [whiteRooky1].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > whiteRooky1 && tempx == whiteRookx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteRookx1] [whiteRooky1 + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy < whiteRooky1 && tempx == whiteRookx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteRookx1] [whiteRooky1 - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (finalCheckMt == 0)
                {
                    return finalCheckMt;
                }
                for (int i = 0 ; i < 8 ; i++)
                {
                    if ((whiteQueenx + i == tempx && whiteQueeny == tempy) || (whiteQueenx - i == tempx && whiteQueeny == tempy) || (whiteQueenx == tempx && whiteQueeny + i == tempy) || (whiteQueenx == tempx && whiteQueeny - i == tempy))
                    {
                        if (tempy == whiteQueeny && tempx < whiteQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteQueenx - u] [whiteQueeny].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy == whiteQueeny && tempx > whiteQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteQueenx + u] [whiteQueeny].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > whiteQueeny && tempx == whiteQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteQueenx] [whiteQueeny + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy < whiteQueeny && tempx == whiteQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteQueenx] [whiteQueeny - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                    }
                    if (finalCheckMt == 0)
                    {
                        return finalCheckMt;
                    }
                    if ((whiteQueenx + i == tempx && whiteQueeny + i == tempy) || (whiteQueenx - i == tempx && whiteQueeny + i == tempy) || (whiteQueenx + i == tempx && whiteQueeny - i == tempy) || (whiteQueenx - i == tempx && whiteQueeny - i == tempy))
                    {
                        if (tempy < whiteQueeny && tempx < whiteQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteQueenx - u] [whiteQueeny - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > whiteQueeny && tempx > whiteQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteQueenx + u] [whiteQueeny + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > whiteQueeny && tempx < whiteQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteQueenx - u] [whiteQueeny + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy < whiteQueeny && tempx > whiteQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [whiteQueenx + u] [whiteQueeny - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (finalCheckMt == 0)
                {
                    return finalCheckMt;
                }

                try
                {
                    for (int i = 0 ; i < 8 ; i++)
                    {
                        if ((whitePAWNXs [i] - 1 == tempx && whitePAWNYs [i] - 1 == tempy))
                        {
                            iconReset = tiles [whitePAWNXs [i] - 1] [whitePAWNYs [i] - 1].getIcon ();
                            tiles [whitePAWNXs [i] - 1] [whitePAWNYs [i] - 1].setIcon (whitePawn);
                            tiles [whitePAWNXs [i]] [whitePAWNYs [i]].setIcon (null);

                            aaq = check (whiteKingy, whiteKingx, 0);
                            if (aaq == 1)
                            {
                                finalCheckMt = 1;
                            }
                            else
                            {
                                finalCheckMt = 0;
                            }
                            tiles [whitePAWNXs [i] - 1] [whitePAWNYs [i] - 1].setIcon (iconReset);
                            tiles [whitePAWNXs [i]] [whitePAWNYs [i]].setIcon (whitePawn);



                            break;
                        }
                        else if (whitePAWNXs [i] - 1 == tempx && whitePAWNYs [i] + 1 == tempy)
                        {
                            iconReset = tiles [whitePAWNXs [i] - 1] [whitePAWNYs [i] + 1].getIcon ();
                            tiles [whitePAWNXs [i] - 1] [whitePAWNYs [i] + 1].setIcon (whitePawn);
                            tiles [whitePAWNXs [i]] [whitePAWNYs [i]].setIcon (null);

                            aaq = check (whiteKingy, whiteKingx, 0);
                            if (aaq == 1)
                            {
                                finalCheckMt = 1;
                            }
                            else
                            {
                                finalCheckMt = 0;
                            }
                            tiles [whitePAWNXs [i] - 1] [whitePAWNYs [i] + 1].setIcon (iconReset);
                            tiles [whitePAWNXs [i]] [whitePAWNYs [i]].setIcon (whitePawn);

                            break;
                        }
                    }
                }
                catch (NullPointerException o)
                {
                }
                if (finalCheckMt == 0)
                {
                    return finalCheckMt;
                }

                pawnFirstMvCnts [0] = pawncnt1;
                pawnFirstMvCnts [1] = pawncnt2;
                pawnFirstMvCnts [2] = pawncnt31;
                pawnFirstMvCnts [3] = pawncnt4;
                pawnFirstMvCnts [4] = pawncnt5; //the first move counts of the pawns are stored for en passant purposes
                pawnFirstMvCnts [5] = pawncnt6;
                pawnFirstMvCnts [6] = pawncnt7;
                pawnFirstMvCnts [7] = pawncnt8;

                checkmateChk [0] = whiteKnightChkr ();
                checkmateChk [1] = whiteKnight1Chkr ();
                checkmateChk [2] = whiteBishChkr ();
                checkmateChk [3] = whiteBish1Chkr ();
                checkmateChk [4] = whiteRookChkr (); //these methods all check whether the check can be blocked by any other friendly piece
                checkmateChk [5] = whiteRook1Chkr ();
                checkmateChk [6] = whiteQueenChkr ();
                checkmateChk [7] = whitePawnChk ();

                int i;
                for (i = 0 ; i < 8 ; i++)
                {
                    if (checkmateChk [i] == 0)
                    {
                        finalCheckMt = 0;
                        break;
                    }
                }
                if (i == 8)
                {
                    return finalCheckMt;
                }

            }
            else
            {
                finalCheckMt = 0;
                return finalCheckMt;
            }

        }

        else if (pieceCode == 5) //black King, same as white king
        {
            try
            {
                checkMate1 = check (bpieceXplus, blackKingy, pieceCode);
                wValid = validCheck (bpieceXplus, blackKingy, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate1 = 1;
            }

            try
            {
                checkMate2 = check (bpieceXminus, blackKingy, pieceCode);
                wValid1 = validCheck (bpieceXminus, blackKingy, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate2 = 1;
            }

            try
            {
                checkMate3 = check (blackKingx, bpieceYplus, pieceCode);
                wValid2 = validCheck (blackKingx, bpieceYplus, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate3 = 1;
            }

            try
            {
                checkMate4 = check (blackKingx, bpieceYminus, pieceCode);
                wValid3 = validCheck (blackKingx, bpieceYminus, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate4 = 1;
            }

            try
            {
                checkMate5 = check (bpieceXplus, bpieceYplus, 5);
                wValid4 = validCheck (bpieceXplus, bpieceYplus, 5);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate5 = 1;
            }

            try
            {
                checkMate6 = check (bpieceXminus, bpieceYminus, pieceCode);
                wValid5 = validCheck (bpieceXminus, bpieceYminus, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate6 = 1;
            }

            try
            {
                checkMate7 = check (bpieceXplus, bpieceYminus, pieceCode);
                wValid6 = validCheck (bpieceXplus, bpieceYminus, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate7 = 1;
            }

            try
            {
                checkMate = check (bpieceXminus, bpieceYplus, pieceCode);
                wValid7 = validCheck (bpieceXminus, bpieceYplus, pieceCode);
            }
            catch (ArrayIndexOutOfBoundsException ea)
            {
                checkMate = 1;
            }

            if (wValid == 0)
            {
                checkMate1 = 1;
            }
            if (wValid1 == 0)
            {
                checkMate2 = 1;
            }
            if (wValid2 == 0)
            {
                checkMate3 = 1;
            }
            if (wValid3 == 0)
            {
                checkMate4 = 1;
            }
            if (wValid4 == 0)
            {
                checkMate5 = 1;
            }
            if (wValid5 == 0)
            {
                checkMate6 = 1;
            }
            if (wValid6 == 0)
            {
                checkMate7 = 1;
            }
            if (wValid7 == 0)
            {
                checkMate = 1;
            }

            if (checkMate == 1 && checkMate1 == 1 && checkMate2 == 1 && checkMate3 == 1 && checkMate4 == 1 && checkMate5 == 1 && checkMate6 == 1 && checkMate7 == 1)
            {
                finalCheckMt = 1;
                if ((blackKnightx + 2 == tempx && blackKnighty + 1 == tempy) || (blackKnightx - 2 == tempx && blackKnighty - 1 == tempy) || (blackKnightx - 2 == tempx && blackKnighty + 1 == tempy) || (blackKnightx + 2 == tempx && blackKnighty - 1 == tempy) || (blackKnightx + 1 == tempx && blackKnighty + 2 == tempy) || (blackKnightx - 1 == tempx && blackKnighty - 2 == tempy) || (blackKnightx - 1 == tempx && blackKnighty + 2 == tempy) || (blackKnightx + 1 == tempx && blackKnighty - 2 == tempy))
                {
                    finalCheckMt = 0;
                    return finalCheckMt;
                }
                if ((blackKnightx1 + 2 == tempx && blackKnighty1 + 1 == tempy) || (blackKnightx1 - 2 == tempx && blackKnighty1 - 1 == tempy) || (blackKnightx1 - 2 == tempx && blackKnighty1 + 1 == tempy) || (blackKnightx1 + 2 == tempx && blackKnighty1 - 1 == tempy) || (blackKnightx1 + 1 == tempx && blackKnighty1 + 2 == tempy) || (blackKnightx1 - 1 == tempx && blackKnighty1 - 2 == tempy) || (blackKnightx1 - 1 == tempx && blackKnighty1 + 2 == tempy) || (blackKnightx1 + 1 == tempx && blackKnighty1 - 2 == tempy))
                {
                    finalCheckMt = 0;
                    return finalCheckMt;
                }
                for (int i = 0 ; i < 8 ; i++)
                {
                    if ((blackBishx + i == tempx && blackBishy + i == tempy) || (blackBishx - i == tempx && blackBishy + i == tempy) || (blackBishx + i == tempx && blackBishy - i == tempy) || (blackBishx - i == tempx && blackBishy - i == tempy))
                    {
                        if (tempy < blackBishy && tempx < blackBishx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackBishx - u] [blackBishy - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > blackBishy && tempx > blackBishx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackBishx + u] [blackBishy + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > blackBishy && tempx < blackBishx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackBishx - u] [blackBishy + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy < blackBishy && tempx > blackBishx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackBishx + u] [blackBishy - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (finalCheckMt == 0)
                {
                    return finalCheckMt;
                }
                for (int i = 0 ; i < 8 ; i++)
                {
                    if ((blackBishx1 + i == tempx && blackBishy1 + i == tempy) || (blackBishx1 - i == tempx && blackBishy1 + i == tempy) || (blackBishx1 + i == tempx && blackBishy1 - i == tempy) || (blackBishx1 - i == tempx && blackBishy1 - i == tempy))
                    {
                        if (tempy < blackBishy1 && tempx < blackBishx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackBishx1 - u] [blackBishy1 - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > blackBishy1 && tempx > blackBishx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackBishx1 + u] [blackBishy1 + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > blackBishy1 && tempx < blackBishx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackBishx1 - u] [blackBishy1 + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy < blackBishy1 && tempx > blackBishx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackBishx1 + u] [blackBishy1 - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (finalCheckMt == 0)
                {
                    return finalCheckMt;
                }
                for (int i = 0 ; i < 8 ; i++)
                {
                    if ((blackRookx + i == tempx && blackRooky == tempy) || (blackRookx - i == tempx && blackRooky == tempy) || (blackRookx == tempx && blackRooky + i == tempy) || (blackRookx == tempx && blackRooky - i == tempy))
                    {
                        if (tempy == blackRooky && tempx < blackRookx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackRookx - u] [blackRooky].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy == blackRooky && tempx > blackRookx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackRookx + u] [blackRooky].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > blackRooky && tempx == blackRookx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackBishx] [blackRooky + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy < blackRooky && tempx == blackRookx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackRookx] [blackRooky - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (finalCheckMt == 0)
                {
                    return finalCheckMt;
                }
                for (int i = 0 ; i < 8 ; i++)
                {
                    if ((blackRookx1 + i == tempx && blackRooky1 == tempy) || (blackRookx1 - i == tempx && blackRooky1 == tempy) || (blackRookx1 == tempx && blackRooky1 + i == tempy) || (blackRookx1 == tempx && blackRooky1 - i == tempy))
                    {
                        if (tempy == blackRooky1 && tempx < blackRookx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackRookx1 - u] [blackRooky1].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy == blackRooky1 && tempx > blackRookx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackRookx1 + u] [blackRooky1].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > blackRooky1 && tempx == blackRookx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackRookx1] [blackRooky1 + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy < blackRooky1 && tempx == blackRookx1)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackRookx1] [blackRooky1 - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (finalCheckMt == 0)
                {
                    return finalCheckMt;
                }
                for (int i = 0 ; i < 8 ; i++)
                {
                    if ((blackQueenx + i == tempx && blackQueeny == tempy) || (blackQueenx - i == tempx && blackQueeny == tempy) || (blackQueenx == tempx && blackQueeny + i == tempy) || (blackQueenx == tempx && blackQueeny - i == tempy))
                    {
                        if (tempy == blackQueeny && tempx < blackQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackQueenx - u] [blackQueeny].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy == blackQueeny && tempx > blackQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackQueenx + u] [blackQueeny].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > blackQueeny && tempx == blackQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackQueenx] [blackQueeny + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy < blackQueeny && tempx == blackQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackQueenx] [blackQueeny - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                    }
                    if (finalCheckMt == 0)
                    {
                        return finalCheckMt;
                    }
                    if ((blackQueenx + i == tempx && blackQueeny + i == tempy) || (blackQueenx - i == tempx && blackQueeny + i == tempy) || (blackQueenx + i == tempx && blackQueeny - i == tempy) || (blackQueenx - i == tempx && blackQueeny - i == tempy))
                    {
                        if (tempy < blackQueeny && tempx < blackQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackQueenx - u] [blackQueeny - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > blackQueeny && tempx > blackQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackQueenx + u] [blackQueeny + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy > blackQueeny && tempx < blackQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackQueenx - u] [blackQueeny + u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                        if (tempy < blackQueeny && tempx > blackQueenx)
                        {
                            for (int u = 1 ; u < i ; u++)
                            {
                                if (tiles [blackQueenx + u] [blackQueeny - u].getIcon () == null)
                                {
                                    finalCheckMt = 0;
                                }
                                else
                                {
                                    finalCheckMt = 1;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (finalCheckMt == 0)
                {
                    return finalCheckMt;
                }

                try
                {
                    for (int i = 0 ; i < 8 ; i++)
                    {
                        if ((blackPAWNXs [i] - 1 == tempx && blackPAWNYs [i] - 1 == tempy))
                        {
                            iconReset = tiles [blackPAWNXs [i] - 1] [blackPAWNYs [i] - 1].getIcon ();
                            tiles [blackPAWNXs [i] - 1] [blackPAWNYs [i] - 1].setIcon (blackPawn);
                            tiles [blackPAWNXs [i]] [blackPAWNYs [i]].setIcon (null);

                            aaq = check (blackKingx, blackKingy, 5);
                            if (aaq == 1)
                            {
                                finalCheckMt = 1;
                            }
                            else
                            {
                                finalCheckMt = 0;
                            }
                            tiles [blackPAWNXs [i] - 1] [blackPAWNYs [i] - 1].setIcon (iconReset);
                            tiles [blackPAWNXs [i]] [blackPAWNYs [i]].setIcon (blackPawn);



                            break;
                        }
                        else if (blackPAWNXs [i] - 1 == tempx && blackPAWNYs [i] + 1 == tempy)
                        {
                            iconReset = tiles [blackPAWNXs [i] - 1] [blackPAWNYs [i] + 1].getIcon ();
                            tiles [blackPAWNXs [i] - 1] [blackPAWNYs [i] + 1].setIcon (blackPawn);
                            tiles [blackPAWNXs [i]] [blackPAWNYs [i]].setIcon (null);

                            aaq = check (blackKingx, blackKingy, 5);
                            if (aaq == 1)
                            {
                                finalCheckMt = 1;
                            }
                            else
                            {
                                finalCheckMt = 0;
                            }
                            tiles [blackPAWNXs [i] - 1] [blackPAWNYs [i] + 1].setIcon (iconReset);
                            tiles [blackPAWNXs [i]] [blackPAWNYs [i]].setIcon (blackPawn);

                            break;
                        }
                    }
                }
                catch (NullPointerException o)
                {
                }

                if (finalCheckMt == 0)
                {
                    return finalCheckMt;
                }

                bpawnFirstMvCnts [0] = pwncount23;
                bpawnFirstMvCnts [1] = pawncnt3;
                bpawnFirstMvCnts [2] = bPawncnt1;
                bpawnFirstMvCnts [3] = bPawncnt2;
                bpawnFirstMvCnts [4] = bPawncnt3; //the first move counter of all the black pawns are stored for en passant purposes
                bpawnFirstMvCnts [5] = bPawncnt4;
                bpawnFirstMvCnts [6] = bPawncnt5;
                bpawnFirstMvCnts [7] = bPawncnt6;

                checkmateChk [0] = blackKnightChkr ();
                checkmateChk [1] = blackKnightChkr1 ();
                checkmateChk [2] = blackBishChkr ();
                checkmateChk [3] = blackBishChkr1 ();
                checkmateChk [4] = blackRookChkr (); //methods that check if the check can be blocked.
                checkmateChk [5] = blackRookChkr1 ();
                checkmateChk [6] = blackQueenChkr ();
                checkmateChk [7] = blackPawnChk ();

                int i;
                for (i = 0 ; i < 8 ; i++)
                {
                    if (checkmateChk [i] == 0)
                    {
                        finalCheckMt = 0;
                        break;
                    }
                }
                if (i == 8)
                {
                    return finalCheckMt;
                }

            }
            else
            {
                finalCheckMt = 0;
                return finalCheckMt;
            }
        }
        return finalCheckMt;
    }


    public int whiteKnightChkr ()  //checks if the white knight can block a check
    {
        knightX = whiteKnightx;
        knightY = whiteKnighty;
        try
        {
            whiteKnightx = knightX - 2;
            whiteKnighty = knightY + 1;
            checkmate = knightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            whiteKnightx = knightX + 2;
            whiteKnighty = knightY + 1;
            checkmate = knightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            whiteKnightx = knightX - 2;
            whiteKnighty = knightY - 1;
            checkmate = knightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            whiteKnightx = knightX + 2;
            whiteKnighty = knightY - 1;
            checkmate = knightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            whiteKnightx = knightX - 1;
            whiteKnighty = knightY + 2;
            checkmate = knightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            whiteKnightx = knightX + 1;
            whiteKnighty = knightY + 2;
            checkmate = knightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            whiteKnightx = knightX - 1;
            whiteKnighty = knightY - 2;
            checkmate = knightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            whiteKnightx = knightX + 1;
            whiteKnighty = knightY - 2;
            checkmate = knightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        return checkmate;

    }


    public int blackKnightChkr1 ()  //checks if the black knight can block a check
    {
        knightX = blackKnightx1;
        knightY = blackKnighty1;
        try
        {
            blackKnightx1 = knightX - 2;
            blackKnighty1 = knightY + 1;
            checkmate = bknightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            blackKnightx1 = knightX + 2;
            blackKnighty1 = knightY + 1;
            checkmate = bknightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            blackKnightx1 = knightX - 2;
            blackKnighty1 = knightY - 1;
            checkmate = bknightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            blackKnightx1 = knightX + 2;
            blackKnighty1 = knightY - 1;
            checkmate = bknightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            blackKnightx1 = knightX - 1;
            blackKnighty1 = knightY + 2;
            checkmate = bknightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            blackKnightx1 = knightX + 1;
            blackKnighty1 = knightY + 2;
            checkmate = bknightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            blackKnightx1 = knightX - 1;
            blackKnighty1 = knightY - 2;
            checkmate = bknightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            blackKnightx1 = knightX + 1;
            blackKnighty1 = knightY - 2;
            checkmate = bknightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        return checkmate;
    }


    public int blackKnightChkr ()  //checks if the black knight can block a check
    {
        knightX = blackKnightx;
        knightY = blackKnighty;
        try
        {
            blackKnightx = knightX - 2;
            blackKnighty = knightY + 1;
            checkmate = bknightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }

        try
        {
            blackKnightx = knightX + 2;
            blackKnighty = knightY + 1;
            checkmate = bknightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        try
        {
            blackKnightx = knightX - 2;
            blackKnighty = knightY - 1;
            checkmate = bknightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        try
        {
            blackKnightx = knightX + 2;
            blackKnighty = knightY - 1;
            checkmate = bknightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        try
        {
            blackKnightx = knightX - 1;
            blackKnighty = knightY + 2;
            checkmate = bknightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        try
        {
            blackKnightx = knightX + 1;
            blackKnighty = knightY + 2;
            checkmate = bknightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        try
        {
            blackKnightx = knightX - 1;
            blackKnighty = knightY - 2;
            checkmate = bknightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        try
        {
            blackKnightx = knightX + 1;
            blackKnighty = knightY - 2;
            checkmate = bknightCheckMt ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        return checkmate;
    }


    public int whiteKnight1Chkr ()  //checks if the white knight can block a check
    {
        knightX = whiteKnightx1;
        knightY = whiteKnighty1;

        try
        {
            whiteKnightx1 = knightX - 2;
            whiteKnighty1 = knightY + 1;
            checkmate = knightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        try
        {

            whiteKnightx1 = knightX + 2;
            whiteKnighty1 = knightY + 1;
            checkmate = knightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        try
        {
            whiteKnightx1 = knightX - 2;
            whiteKnighty1 = knightY - 1;
            checkmate = knightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        try
        {
            whiteKnightx1 = knightX + 2;
            whiteKnighty1 = knightY - 1;
            checkmate = knightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        try
        {
            whiteKnightx1 = knightX - 1;
            whiteKnighty1 = knightY + 2;
            checkmate = knightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        try
        {
            whiteKnightx1 = knightX + 1;
            whiteKnighty1 = knightY + 2;
            checkmate = knightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        try
        {
            whiteKnightx1 = knightX - 1;
            whiteKnighty1 = knightY - 2;
            checkmate = knightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        try
        {
            whiteKnightx1 = knightX + 1;
            whiteKnighty1 = knightY - 2;
            checkmate = knightCheckMt1 ();
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
        }
        return checkmate;
    }


    public int whiteRookChkr ()  //checks if the white rook can block a check
    {
        rookX = whiteRookx;
        rookY = whiteRooky;

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteRookx = rookX + i;
            if (whiteRookx >= 8)
            {
                break;
            }
            if (tiles [whiteRookx] [whiteRooky].getIcon () != null)
            {
                break;
            }
            checkmate = wRookCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        for (int i = 1 ; i < 8 ; i++)
        {
            whiteRookx = rookX - i;
            if (whiteRookx < 0)
            {
                break;
            }
            if (tiles [whiteRookx] [whiteRooky].getIcon () != null)
            {
                break;
            }
            checkmate = wRookCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        whiteRookx = rookX;
        for (int i = 1 ; i < 8 ; i++)
        {
            whiteRooky = rookY + i;
            if (whiteRooky >= 8)
            {
                break;
            }
            if (tiles [whiteRookx] [whiteRooky].getIcon () != null)
            {
                break;
            }
            checkmate = wRookCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        for (int i = 1 ; i < 8 ; i++)
        {
            whiteRooky = rookY - i;
            if (whiteRooky < 0)
            {
                break;
            }
            if (tiles [whiteRookx] [whiteRooky].getIcon () != null)
            {
                break;
            }
            checkmate = wRookCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        return checkmate;
    }


    public int whiteRook1Chkr ()  //checks if the white rook can block a check
    {
        rookX = whiteRookx1;
        rookY = whiteRooky1;

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteRookx1 = rookX + i;
            if (whiteRookx1 >= 8)
            {
                break;
            }
            if (tiles [whiteRookx1] [whiteRooky1].getIcon () != null)
            {
                break;
            }
            checkmate = wRookCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        for (int i = 1 ; i < 8 ; i++)
        {
            whiteRookx1 = rookX - i;
            if (whiteRookx1 < 0)
            {
                break;
            }
            if (tiles [whiteRookx1] [whiteRooky1].getIcon () != null)
            {
                break;
            }
            checkmate = wRookCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        for (int i = 1 ; i < 8 ; i++)
        {
            whiteRooky1 = rookY + i;
            if (whiteRooky1 >= 8)
            {
                break;
            }
            if (tiles [whiteRookx1] [whiteRooky1].getIcon () != null)
            {
                break;
            }
            checkmate = wRookCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        for (int i = 1 ; i < 8 ; i++)
        {
            whiteRooky1 = rookY - i;
            if (whiteRooky1 < 0)
            {
                break;
            }
            if (tiles [whiteRookx1] [whiteRooky1].getIcon () != null)
            {
                break;
            }
            checkmate = wRookCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        return checkmate;
    }


    public int blackRookChkr ()  //checks if the black rook can block a check
    {
        rookX = blackRookx;
        rookY = blackRooky;

        for (int i = 1 ; i < 8 ; i++)
        {
            blackRookx = rookX + i;
            if (blackRookx >= 8)
            {
                break;
            }
            if (tiles [blackRookx] [blackRooky].getIcon () != null)
            {
                break;
            }
            checkmate = bRookCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        for (int i = 1 ; i < 8 ; i++)
        {
            blackRookx = rookX - i;
            if (blackRookx < 0)
            {
                break;
            }
            if (tiles [blackRookx] [blackRooky].getIcon () != null)
            {
                break;
            }
            checkmate = bRookCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        blackRookx = rookX;
        for (int i = 1 ; i < 8 ; i++)
        {
            blackRooky = rookY + i;
            if (blackRooky >= 8)
            {
                break;
            }
            if (tiles [blackRookx] [blackRooky].getIcon () != null)
            {
                break;
            }
            checkmate = bRookCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        for (int i = 1 ; i < 8 ; i++)
        {
            blackRooky = rookY - i;
            if (blackRooky < 0)
            {
                break;
            }
            if (tiles [blackRookx] [blackRooky].getIcon () != null)
            {
                break;
            }
            checkmate = bRookCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        return checkmate;
    }


    public int blackRookChkr1 ()  //checks if the black rook can block a check
    {
        rookX = blackRookx1;
        rookY = blackRooky1;

        for (int i = 1 ; i < 8 ; i++)
        {
            blackRookx1 = rookX + i;
            if (blackRookx1 >= 8)
            {
                break;
            }
            if (tiles [blackRookx1] [blackRooky1].getIcon () != null)
            {
                break;
            }
            checkmate = bRookCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        for (int i = 1 ; i < 8 ; i++)
        {
            blackRookx1 = rookX - i;
            if (blackRookx1 < 0)
            {
                break;
            }
            if (tiles [blackRookx1] [blackRooky1].getIcon () != null)
            {
                break;
            }
            checkmate = bRookCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        blackRookx1 = rookX;
        for (int i = 1 ; i < 8 ; i++)
        {
            blackRooky1 = rookY + i;
            if (blackRooky1 >= 8)
            {
                break;
            }
            if (tiles [blackRookx1] [blackRooky1].getIcon () != null)
            {
                break;
            }
            checkmate = bRookCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        for (int i = 1 ; i < 8 ; i++)
        {
            blackRooky1 = rookY - i;
            if (blackRooky1 < 0)
            {
                break;
            }
            if (tiles [blackRookx1] [blackRooky1].getIcon () != null)
            {
                break;
            }
            checkmate = bRookCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        return checkmate;
    }


    public int whiteQueenChkr ()  //checks if the white queen can block a check
    {
        queenX = whiteQueenx;
        queenY = whiteQueeny;

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteQueenx = queenX + i;
            if (whiteQueenx >= 8)
            {
                break;
            }
            if (tiles [whiteQueenx] [whiteQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = wQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        for (int i = 1 ; i < 8 ; i++)
        {
            whiteQueenx = queenX - i;
            if (whiteQueenx < 0)
            {
                break;
            }
            if (tiles [whiteQueenx] [whiteQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = wQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        whiteQueenx = queenX;
        for (int i = 1 ; i < 8 ; i++)
        {
            whiteQueeny = queenY + i;
            if (whiteQueeny >= 8)
            {

                break;
            }
            if (tiles [whiteQueenx] [whiteQueeny].getIcon () != null)
            {
                break;
            }

            checkmate = wQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        for (int i = 1 ; i < 8 ; i++)
        {
            whiteQueeny = queenY - i;
            if (whiteQueeny < 0)
            {
                break;
            }
            if (tiles [whiteQueenx] [whiteQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = wQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        whiteQueenx = queenX;
        whiteQueeny = queenY;

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteQueenx = queenX + i;
            whiteQueeny = queenY + i;
            if (whiteQueenx >= 8 || whiteQueeny >= 8)
            {
                break;
            }
            if (tiles [whiteQueenx] [whiteQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = wQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteQueenx = queenX - i;
            whiteQueeny = queenY - i;
            if (whiteQueenx < 0 || whiteQueeny < 0)
            {
                break;
            }
            if (tiles [whiteQueenx] [whiteQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = wQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteQueenx = queenX + i;
            whiteQueeny = queenY - i;
            if (whiteQueenx >= 8 || whiteQueeny < 0)
            {
                break;
            }
            if (tiles [whiteQueenx] [whiteQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = wQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteQueenx = queenX - i;
            whiteQueeny = queenY + i;
            if (whiteQueenx < 0 || whiteQueeny >= 8)
            {
                break;
            }
            if (tiles [whiteQueenx] [whiteQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = wQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        return checkmate;
    }


    public int blackQueenChkr ()  //checks if the black queen can block a check
    {
        queenX = blackQueenx;
        queenY = blackQueeny;

        for (int i = 1 ; i < 8 ; i++)
        {
            blackQueenx = queenX + i;
            if (blackQueenx >= 8)
            {
                break;
            }
            if (tiles [blackQueenx] [blackQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = bQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        for (int i = 1 ; i < 8 ; i++)
        {
            blackQueenx = queenX - i;
            if (blackQueenx < 0)
            {
                break;
            }
            if (tiles [blackQueenx] [blackQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = bQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        blackQueenx = queenX;
        for (int i = 1 ; i < 8 ; i++)
        {
            blackQueeny = queenY + i;
            if (blackQueeny >= 8)
            {

                break;
            }
            if (tiles [blackQueenx] [blackQueeny].getIcon () != null)
            {
                break;
            }

            checkmate = bQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        for (int i = 1 ; i < 8 ; i++)
        {
            blackQueeny = queenY - i;
            if (blackQueeny < 0)
            {
                break;
            }
            if (tiles [blackQueenx] [blackQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = bQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        blackQueenx = queenX;
        blackQueeny = queenY;

        for (int i = 1 ; i < 8 ; i++)
        {
            blackQueenx = queenX + i;
            blackQueeny = queenY + i;
            if (blackQueenx >= 8 || blackQueeny >= 8)
            {
                break;
            }
            if (tiles [blackQueenx] [blackQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = bQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            blackQueenx = queenX - i;
            blackQueeny = queenY - i;
            if (blackQueenx < 0 || blackQueeny < 0)
            {
                break;
            }
            if (tiles [blackQueenx] [blackQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = bQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            blackQueenx = queenX + i;
            blackQueeny = queenY - i;
            if (blackQueenx >= 8 || blackQueeny < 0)
            {
                break;
            }
            if (tiles [blackQueenx] [blackQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = bQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            blackQueenx = queenX - i;
            blackQueeny = queenY + i;
            if (blackQueenx < 0 || blackQueeny >= 8)
            {
                break;
            }
            if (tiles [blackQueenx] [blackQueeny].getIcon () != null)
            {
                break;
            }
            checkmate = bQueenCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        return checkmate;
    }


    public int whiteBishChkr ()  //checks if the white bishop can block a check
    {
        bishX = whiteBishx;
        bishY = whiteBishy;

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteBishx = bishX + i;
            whiteBishy = bishY + i;
            if (whiteBishx >= 8 || whiteBishy >= 8)
            {
                break;
            }
            if (tiles [whiteBishx] [whiteBishy].getIcon () != null)
            {
                break;
            }
            checkmate = wbishopCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteBishx = bishX - i;
            whiteBishy = bishY - i;
            if (whiteBishx < 0 || whiteBishy < 0)
            {
                break;
            }
            if (tiles [whiteBishx] [whiteBishy].getIcon () != null)
            {
                break;
            }
            checkmate = wbishopCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteBishx = bishX + i;
            whiteBishy = bishY - i;
            if (whiteBishx >= 8 || whiteBishy < 0)
            {
                break;
            }
            if (tiles [whiteBishx] [whiteBishy].getIcon () != null)
            {
                break;
            }
            checkmate = wbishopCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteBishx = bishX - i;
            whiteBishy = bishY + i;
            if (whiteBishx < 0 || whiteBishy >= 8)
            {
                break;
            }
            if (tiles [whiteBishx] [whiteBishy].getIcon () != null)
            {
                break;
            }
            checkmate = wbishopCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        return checkmate;
    }


    public int whiteBish1Chkr ()  //checks if the white bishop can block a check
    {
        bishX = whiteBishx1;
        bishY = whiteBishy1;

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteBishx1 = bishX + i;
            whiteBishy1 = bishY + i;
            if (whiteBishx1 >= 8 || whiteBishy1 >= 8)
            {
                break;
            }
            if (tiles [whiteBishx1] [whiteBishy1].getIcon () != null)
            {
                break;
            }
            checkmate = wbishopCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }



        for (int i = 1 ; i < 8 ; i++)
        {
            whiteBishx1 = bishX - i;
            whiteBishy1 = bishY - i;
            if (whiteBishx1 < 0 || whiteBishy1 < 0)
            {
                break;
            }
            if (tiles [whiteBishx1] [whiteBishy1].getIcon () != null)
            {
                break;
            }
            checkmate = wbishopCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteBishx1 = bishX + i;
            whiteBishy1 = bishY - i;
            if (whiteBishx1 >= 8 || whiteBishy1 < 0)
            {
                break;
            }
            if (tiles [whiteBishx1] [whiteBishy1].getIcon () != null)
            {
                break;
            }
            checkmate = wbishopCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            whiteBishx1 = bishX - i;
            whiteBishy1 = bishY + i;
            if (whiteBishx1 < 0 || whiteBishy1 >= 8)
            {
                break;
            }
            if (tiles [whiteBishx1] [whiteBishy1].getIcon () != null)
            {
                break;
            }
            checkmate = wbishopCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        return checkmate;
    }


    public int blackBishChkr ()  //checks if the black bishop can block a check
    {
        bishX = blackBishx;
        bishY = blackBishy;

        for (int i = 1 ; i < 8 ; i++)
        {
            blackBishx = bishX + i;
            blackBishy = bishY + i;
            if (blackBishx >= 8 || blackBishy >= 8)
            {
                break;
            }
            if (tiles [blackBishx] [blackBishy].getIcon () != null)
            {
                break;
            }
            checkmate = bbishopCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            blackBishx = bishX - i;
            blackBishy = bishY - i;
            if (blackBishx < 0 || blackBishy < 0)
            {
                break;
            }
            if (tiles [blackBishx] [blackBishy].getIcon () != null)
            {
                break;
            }
            checkmate = bbishopCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            blackBishx = bishX + i;
            blackBishy = bishY - i;
            if (blackBishx >= 8 || blackBishy < 0)
            {
                break;
            }
            if (tiles [blackBishx] [blackBishy].getIcon () != null)
            {
                break;
            }
            checkmate = bbishopCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            blackBishx = bishX - i;
            blackBishy = bishY + i;
            if (blackBishx < 0 || blackBishy >= 8)
            {
                break;
            }
            if (tiles [blackBishx] [blackBishy].getIcon () != null)
            {
                break;
            }
            checkmate = bbishopCheckMt ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        return checkmate;
    }


    public int blackBishChkr1 ()  //checks if the black bishop can block a check
    {
        bishX = blackBishx1;
        bishY = blackBishy1;

        for (int i = 1 ; i < 8 ; i++)
        {
            blackBishx1 = bishX + i;
            blackBishy1 = bishY + i;
            if (blackBishx1 >= 8 || blackBishy1 >= 8)
            {
                break;
            }
            if (tiles [blackBishx1] [blackBishy1].getIcon () != null)
            {
                break;
            }
            checkmate = bbishopCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            blackBishx1 = bishX - i;
            blackBishy1 = bishY - i;
            if (blackBishx1 < 0 || blackBishy1 < 0)
            {
                break;
            }
            if (tiles [blackBishx1] [blackBishy1].getIcon () != null)
            {
                break;
            }
            checkmate = bbishopCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            blackBishx1 = bishX + i;
            blackBishy1 = bishY - i;
            if (blackBishx1 >= 8 || blackBishy1 < 0)
            {
                break;
            }
            if (tiles [blackBishx1] [blackBishy1].getIcon () != null)
            {
                break;
            }
            checkmate = bbishopCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        for (int i = 1 ; i < 8 ; i++)
        {
            blackBishx1 = bishX - i;
            blackBishy1 = bishY + i;
            if (blackBishx1 < 0 || blackBishy1 >= 8)
            {
                break;
            }
            if (tiles [blackBishx1] [blackBishy1].getIcon () != null)
            {
                break;
            }
            checkmate = bbishopCheckMt1 ();
            if (checkmate == 0)
            {
                return checkmate;
            }
        }

        return checkmate;
    }


    public int whitePawnChk ()  //checks if the white pawn can block a check
    {
        for (int i = 0 ; i < 8 ; i++)
        {
            pawnX = whitePAWNXs [i];
            pawnY = whitePAWNYs [i];

            if (pawnFirstMvCnts [i] == 0)
            {
                whitePAWNXs [i] = whitePAWNXs [i] - 2;
                if (whitePAWNXs [i] < 0)
                {
                    break;
                }
                if (tiles [whitePAWNXs [i] + 1] [whitePAWNYs [i]].getIcon () != null)
                {
                    break;
                }
                if (whitePAWNXs [i] == tempx && whitePAWNYs [i] == tempy)
                {
                    break;
                }
                checkmate = wPawnCheckMt (whitePAWNXs [i], whitePAWNYs [i]);
                if (checkmate == 0)
                {
                    return checkmate;
                }
            }
            whitePAWNXs [i] = whitePAWNXs [i] - 1;
            if (whitePAWNXs [i] < 0)
            {
                break;
            }
            if (whitePAWNXs [i] == tempx && whitePAWNYs [i] == tempy)
            {
                break;
            }
            checkmate = wPawnCheckMt (whitePAWNXs [i], whitePAWNYs [i]);
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        return checkmate;
    }


    public int blackPawnChk ()  //checks if the black bishop can block a check
    {
        for (int i = 0 ; i < 8 ; i++)
        {
            pawnX = blackPAWNXs [i];
            pawnY = blackPAWNYs [i];

            if (bpawnFirstMvCnts [i] == 0)
            {
                blackPAWNXs [i] = blackPAWNXs [i] - 2;
                if (blackPAWNXs [i] < 0)
                {
                    break;
                }
                if (tiles [blackPAWNXs [i] + 1] [blackPAWNYs [i]].getIcon () != null)
                {
                    break;
                }
                if (blackPAWNXs [i] == tempx && blackPAWNYs [i] == tempy)
                {
                    break;
                }
                checkmate = bPawnCheckMt (blackPAWNXs [i], blackPAWNYs [i]);
                if (checkmate == 0)
                {
                    return checkmate;
                }
            }
            blackPAWNXs [i] = blackPAWNXs [i] - 1;
            if (blackPAWNXs [i] < 0)
            {
                break;
            }
            if (blackPAWNXs [i] == tempx && blackPAWNYs [i] == tempy)
            {
                break;
            }
            checkmate = bPawnCheckMt (blackPAWNXs [i], blackPAWNYs [i]);
            if (checkmate == 0)
            {
                return checkmate;
            }
        }
        return checkmate;
    }


    public int knightCheckMt ()  //checks if the white knight can block a check
    {
        iconReset = tiles [whiteKnightx] [whiteKnighty].getIcon ();
        tiles [whiteKnightx] [whiteKnighty].setIcon (whiteKnight);
        aaq = check (whiteKingy, whiteKingx, 0);

        if (aaq == 1)
        {
            tiles [whiteKnightx] [whiteKnighty].setIcon (iconReset);
            whiteKnightx = knightX;
            whiteKnighty = knightY;
            checkmate = 1;
        }
        else
        {
            tiles [whiteKnightx] [whiteKnighty].setIcon (iconReset);
            whiteKnightx = knightX;
            whiteKnighty = knightY;
            checkmate = 0;
        }
        return checkmate;
    }


    public int bknightCheckMt ()  //checks if the black knight can block a check
    {
        iconReset = tiles [blackKnightx] [blackKnighty].getIcon ();
        tiles [blackKnightx] [blackKnighty].setIcon (blackKnight);

        aaq = check (blackKingx, blackKingy, 5);

        if (aaq == 1)
        {
            tiles [blackKnightx] [blackKnighty].setIcon (iconReset);
            blackKnightx = knightX;
            blackKnighty = knightY;
            checkmate = 1;
        }
        else
        {
            tiles [blackKnightx] [blackKnighty].setIcon (iconReset);
            blackKnightx = knightX;
            blackKnighty = knightY;
            checkmate = 0;
        }
        return checkmate;
    }


    public int bknightCheckMt1 ()  //checks if the black knight can block a check
    {
        iconReset = tiles [blackKnightx1] [blackKnighty1].getIcon ();
        tiles [blackKnightx1] [blackKnighty1].setIcon (blackKnight);

        aaq = check (blackKingx, blackKingy, 5);

        if (aaq == 1)
        {
            tiles [blackKnightx1] [blackKnighty1].setIcon (iconReset);
            blackKnightx1 = knightX;
            blackKnighty1 = knightY;
            checkmate = 1;
        }
        else
        {
            tiles [blackKnightx1] [blackKnighty1].setIcon (iconReset);
            blackKnightx1 = knightX;
            blackKnighty1 = knightY;
            checkmate = 0;
        }
        return checkmate;
    }


    public int knightCheckMt1 ()  //checks if the white knight can block a check
    {
        iconReset = tiles [whiteKnightx1] [whiteKnighty1].getIcon ();
        tiles [whiteKnightx1] [whiteKnighty1].setIcon (whiteKnight);

        aaq = check (whiteKingy, whiteKingx, 0);

        if (aaq == 1)
        {
            tiles [whiteKnightx1] [whiteKnighty1].setIcon (iconReset);
            whiteKnightx1 = knightX;
            whiteKnighty1 = knightY;
            checkmate = 1;
        }
        else
        {
            tiles [whiteKnightx1] [whiteKnighty1].setIcon (iconReset);
            whiteKnightx1 = knightX;
            whiteKnighty1 = knightY;
            checkmate = 0;
        }
        return checkmate;
    }


    public int wbishopCheckMt ()  //checks if the white bishop can block a check
    {
        iconReset = tiles [whiteBishx] [whiteBishy].getIcon ();
        tiles [whiteBishx] [whiteBishy].setIcon (whiteBish);

        aaq = check (whiteKingy, whiteKingx, 0);

        if (aaq == 1)
        {
            tiles [whiteBishx] [whiteBishy].setIcon (iconReset);
            whiteBishx = bishX;
            whiteBishy = bishY;
            checkmate = 1;
        }
        else
        {
            tiles [whiteBishx] [whiteBishy].setIcon (iconReset);
            whiteBishx = bishX;
            whiteBishy = bishY;
            checkmate = 0;
        }

        return checkmate;
    }


    public int wbishopCheckMt1 ()  //checks if the white bishop can block a check
    {
        iconReset = tiles [whiteBishx1] [whiteBishy1].getIcon ();
        tiles [whiteBishx1] [whiteBishy1].setIcon (whiteBish);
        aaq = check (whiteKingy, whiteKingx, 0);

        if (aaq == 1)
        {
            tiles [whiteBishx1] [whiteBishy1].setIcon (iconReset);
            whiteBishx1 = bishX;
            whiteBishy1 = bishY;
            checkmate = 1;
        }
        else
        {
            tiles [whiteBishx1] [whiteBishy1].setIcon (iconReset);
            whiteBishx1 = bishX;
            whiteBishy1 = bishY;
            checkmate = 0;
        }

        return checkmate;
    }


    public int bbishopCheckMt ()  //checks if the black bishop can block a check
    {
        iconReset = tiles [blackBishx] [blackBishy].getIcon ();
        tiles [blackBishx] [blackBishy].setIcon (blackBish);

        aaq = check (blackKingx, blackKingy, 5);

        if (aaq == 1)
        {
            tiles [blackBishx] [blackBishy].setIcon (iconReset);
            blackBishx = knightX;
            blackBishy = knightY;
            checkmate = 1;
        }
        else
        {
            tiles [blackBishx] [blackBishy].setIcon (iconReset);
            blackBishx = knightX;
            blackBishy = knightY;
            checkmate = 0;
        }
        return checkmate;
    }


    public int bbishopCheckMt1 ()  //checks if the black bishop can block a check
    {
        iconReset = tiles [blackBishx1] [blackBishy1].getIcon ();
        tiles [blackBishx1] [blackBishy1].setIcon (blackBish);

        aaq = check (blackKingx, blackKingy, 5);

        if (aaq == 1)
        {
            tiles [blackBishx1] [blackBishy1].setIcon (iconReset);
            blackBishx1 = knightX;
            blackBishy1 = knightY;
            checkmate = 1;
        }
        else
        {
            tiles [blackBishx1] [blackBishy1].setIcon (iconReset);
            blackBishx1 = knightX;
            blackBishy1 = knightY;
            checkmate = 0;
        }
        return checkmate;
    }


    public int wRookCheckMt ()  //checks if the white rook can block a check
    {
        iconReset = tiles [whiteRookx] [whiteRooky].getIcon ();
        tiles [whiteRookx] [whiteRooky].setIcon (whiteRook);

        aaq = check (whiteKingy, whiteKingx, 0);

        if (aaq == 1)
        {
            tiles [whiteRookx] [whiteRooky].setIcon (iconReset);
            whiteRookx = rookX;
            whiteRooky = rookY;
            checkmate = 1;
        }
        else
        {
            tiles [whiteRookx] [whiteRooky].setIcon (iconReset);
            whiteRookx = rookX;
            whiteRooky = rookY;
            checkmate = 0;
        }

        return checkmate;
    }


    public int bRookCheckMt ()  //checks if the black rook can block a check
    {
        iconReset = tiles [blackRookx] [blackRooky].getIcon ();
        tiles [blackRookx] [blackRooky].setIcon (whiteRook);
        aaq = check (blackKingx, blackKingy, 5);

        if (aaq == 1)
        {
            tiles [blackRookx] [blackRooky].setIcon (iconReset);
            blackRookx = rookX;
            blackRooky = rookY;
            checkmate = 1;
        }
        else
        {
            tiles [blackRookx] [blackRooky].setIcon (iconReset);
            blackRookx = rookX;
            blackRooky = rookY;
            checkmate = 0;
        }

        return checkmate;
    }


    public int wRookCheckMt1 ()  //checks if the white rook can block a check
    {
        iconReset = tiles [whiteRookx1] [whiteRooky1].getIcon ();
        tiles [whiteRookx1] [whiteRooky1].setIcon (whiteRook);
        aaq = check (whiteKingy, whiteKingx, 0);

        if (aaq == 1)
        {
            tiles [whiteRookx1] [whiteRooky1].setIcon (iconReset);
            whiteRookx1 = rookX;
            whiteRooky1 = rookY;
            checkmate = 1;
        }
        else
        {
            tiles [whiteRookx1] [whiteRooky1].setIcon (iconReset);
            whiteRookx1 = rookX;
            whiteRooky1 = rookY;
            checkmate = 0;
        }

        return checkmate;
    }


    public int bRookCheckMt1 ()  //checks if the black rook can block a check
    {
        iconReset = tiles [blackRookx1] [blackRooky1].getIcon ();
        tiles [blackRookx1] [blackRooky1].setIcon (whiteRook);

        aaq = check (blackKingx, blackKingy, 5);

        if (aaq == 1)
        {
            tiles [blackRookx1] [blackRooky1].setIcon (iconReset);
            blackRookx1 = rookX;
            blackRooky1 = rookY;
            checkmate = 1;
        }
        else
        {
            tiles [blackRookx1] [blackRooky1].setIcon (iconReset);
            blackRookx1 = rookX;
            blackRooky1 = rookY;
            checkmate = 0;
        }

        return checkmate;
    }


    public int wQueenCheckMt ()  //checks if the white queen  can block a check
    {
        iconReset = tiles [whiteQueenx] [whiteQueeny].getIcon ();
        tiles [whiteQueenx] [whiteQueeny].setIcon (whiteQueen);

        aaq = check (whiteKingy, whiteKingx, 0);

        if (aaq == 1)
        {
            tiles [whiteQueenx] [whiteQueeny].setIcon (iconReset);
            whiteQueenx = queenX;
            whiteQueeny = queenY;
            checkmate = 1;
        }
        else
        {
            tiles [whiteQueenx] [whiteQueeny].setIcon (iconReset);
            whiteQueenx = queenX;
            whiteQueeny = queenY;
            checkmate = 0;
        }
        return checkmate;
    }


    public int bQueenCheckMt ()  //checks if the black queen can block a check
    {
        iconReset = tiles [blackQueenx] [blackQueeny].getIcon ();
        tiles [blackQueenx] [blackQueeny].setIcon (whiteQueen);

        aaq = check (blackKingx, blackKingy, 5);

        if (aaq == 1)
        {
            tiles [blackQueenx] [blackQueeny].setIcon (iconReset);
            blackQueenx = queenX;
            blackQueeny = queenY;
            checkmate = 1;
        }
        else
        {
            tiles [blackQueenx] [blackQueeny].setIcon (iconReset);
            blackQueenx = queenX;
            blackQueeny = queenY;
            checkmate = 0;
        }
        return checkmate;
    }


    public int wPawnCheckMt (int X, int Y)  //checks if the white pawns can block a check
    {
        iconReset = tiles [X] [Y].getIcon ();
        tiles [X] [Y].setIcon (whitePawn);

        aaq = check (whiteKingy, whiteKingx, 0);

        if (aaq == 1)
        {
            tiles [X] [Y].setIcon (iconReset);
            X = pawnX;
            Y = pawnY;
            checkmate = 1;
        }
        else
        {
            tiles [X] [Y].setIcon (iconReset);
            X = pawnX;
            Y = pawnY;
            checkmate = 0;
        }
        return checkmate;
    }


    public int bPawnCheckMt (int X, int Y)  //checks if the black pawns can block a check
    {
        iconReset = tiles [X] [Y].getIcon ();
        tiles [X] [Y].setIcon (blackPawn);

        aaq = check (blackKingx, blackKingy, 5);

        if (aaq == 1)
        {
            tiles [X] [Y].setIcon (iconReset);
            X = pawnX;
            Y = pawnY;
            checkmate = 1;
        }
        else
        {
            tiles [X] [Y].setIcon (iconReset);
            X = pawnX;
            Y = pawnY;
            checkmate = 0;
        }
        return checkmate;
    }


    public void whiteDeaths (int pieceX, int pieceY)  //method that checks if a piece has been captured or not
    {
        if ((pieceX == whiteKnightx && pieceY == whiteKnighty) && ran1 != 1)
        {
            wKnight1Death = 1;
        }
        if ((pieceX == whiteKnightx1 && pieceY == whiteKnighty1) && ran1 != 1)
        {
            wKnight2Death = 1;
        }
        if ((pieceX == whiteRookx && pieceY == whiteRooky) && ran1 != 1)
        {
            wRook1Death = 1;
        }
        if ((pieceX == whiteRookx1 && pieceY == whiteRooky1) && ran1 != 1)
        {
            wRook2Death = 1;
        }
        if ((pieceX == whiteBishx && pieceY == whiteBishy) && ran1 != 1)
        {
            wBish1Death = 1;
        }
        if ((pieceX == whiteBishx1 && pieceY == whiteBishy1) && ran1 != 1)
        {
            wBish2Death = 1;
        }
        if ((pieceX == whiteQueenx && pieceY == whiteQueeny) && ran1 != 1)
        {
            wQueen1Death = 1;
        }
        if ((pieceX == whitePawnx1 && pieceY == whitePawny1) && ran1 != 1)
        {
            wPawn1Death = 1;
        }
        if ((pieceX == whitePawnx2 && pieceY == whitePawny2) && ran1 != 1)
        {
            wPawn3Death = 1;
        }
        if ((pieceX == whitePawnx3 && pieceY == whitePawny3) && ran1 != 1)
        {
            wPawn2Death = 1;
        }
        if ((pieceX == whitePawnx4 && pieceY == whitePawny4) && ran1 != 1)
        {
            wPawn4Death = 1;
        }
        if ((pieceX == whitePawnx5 && pieceY == whitePawny5) && ran1 != 1)
        {
            wPawn5Death = 1;
        }
        if ((pieceX == whitePawnx6 && pieceY == whitePawny6) && ran1 != 1)
        {
            wPawn6Death = 1;
        }
        if ((pieceX == whitePawnx7 && pieceY == whitePawny7) && ran1 != 1)
        {
            wPawn7Death = 1;
        }
        if ((pieceX == whitePawnx8 && pieceY == whitePawny8) && ran1 != 1)
        {
            wPawn8Death = 1;
        }
    }


    public void blackDeaths (int pieceX, int pieceY)  //method that checks if a black piece has been captured.
    {
        if ((pieceX == blackKnightx && pieceY == blackKnighty) && ran != 1)
        {
            bKnight1Death = 1;
        }
        if ((pieceX == blackKnightx1 && pieceY == blackKnighty1) && ran != 1)
        {
            bKnight2Death = 1;
        }
        if ((pieceX == blackRookx && pieceY == blackRooky) && ran != 1)
        {
            bRook1Death = 1;
        }
        if ((pieceX == blackRookx1 && pieceY == blackRooky1) && ran != 1)
        {
            bRook2Death = 1;
        }
        if ((pieceX == blackBishx && pieceY == blackBishy) && ran != 1)
        {
            bBish1Death = 1;
        }
        if ((pieceX == blackBishx1 && pieceY == blackBishy1) && ran != 1)
        {
            bBish2Death = 1;
        }
        if ((pieceX == blackQueenx && pieceY == blackQueeny) && ran != 1)
        {
            bQueen1Death = 1;
        }
        if ((pieceX == blackPawnx1 && pieceY == blackPawny1) && ran != 1)
        {
            bPawn1Death = 1;
        }
        if ((pieceX == blackPawnx2 && pieceY == blackPawny2) && ran != 1)
        {
            bPawn2Death = 1;
        }
        if ((pieceX == blackPawnx3 && pieceY == blackPawny3) && ran != 1)
        {
            bPawn3Death = 1;
        }
        if ((pieceX == blackPawnx4 && pieceY == blackPawny4) && ran != 1)
        {
            bPawn4Death = 1;
        }
        if ((pieceX == blackPawnx5 && pieceY == blackPawny5) && ran != 1)
        {
            bPawn5Death = 1;
        }
        if ((pieceX == blackPawnx6 && pieceY == blackPawny6) && ran != 1)
        {
            bPawn6Death = 1;
        }
        if ((pieceX == blackPawnx7 && pieceY == blackPawny7) && ran != 1)
        {
            bPawn7Death = 1;
        }
        if ((pieceX == blackPawnx8 && pieceY == blackPawny8) && ran != 1)
        {
            bPawn8Death = 1;
        }
    }


    //This method is responsible for the apparent movement of the pieces. Before moving the pieces, criteria such as, is the move valid?, was a castle
    //performed?, has an en passant taken place?, etc are first checked.
    public void userClick (int pieceX, int pieceY, int pieceCode)
    {
        int chkr;
        int kingThreat;
        int redChk = 0;
        if (pieceCode == 0) //whiet king
        {
            kingThreat = check (pieceX, pieceY, pieceCode);
            chkr = validCheck (pieceX, pieceY, pieceCode);

            if (chkr != 1)
            {
                return;
            }
            else if (chkr == 1)
            {
                if (kingThreat == 1)
                {
                    return;
                }
                else
                {
                    if (pieceX == blackKnightx && pieceY == blackKnighty)
                    {
                        bKnight1Death = 1;
                    }
                    if (pieceX == blackKnightx1 && pieceY == blackKnighty1)
                    {
                        bKnight2Death = 1;
                    }
                    if (pieceX == blackRookx && pieceY == blackRooky)
                    {
                        bRook1Death = 1;
                    }
                    if (pieceX == blackRookx1 && pieceY == blackRooky1)
                    {
                        bRook2Death = 1;
                    }
                    if (pieceX == blackBishx && pieceY == blackBishy)
                    {
                        bBish1Death = 1;
                    }
                    if (pieceX == blackBishx1 && pieceY == blackBishy1)
                    {
                        bBish2Death = 1;
                    }
                    if (pieceX == blackQueenx && pieceY == blackQueeny)
                    {
                        bQueen1Death = 1;
                    }
                    if (pieceX == blackPawnx1 && pieceY == blackPawny1)
                    {
                        bPawn1Death = 1;
                    }
                    if (pieceX == blackPawnx2 && pieceY == blackPawny2)
                    {
                        bPawn2Death = 1;
                    }
                    if (pieceX == blackPawnx3 && pieceY == blackPawny3)
                    {
                        bPawn3Death = 1;
                    }
                    if (pieceX == blackPawnx4 && pieceY == blackPawny4)
                    {
                        bPawn4Death = 1;
                    }
                    if (pieceX == blackPawnx5 && pieceY == blackPawny5)
                    {
                        bPawn5Death = 1;
                    }
                    if (pieceX == blackPawnx6 && pieceY == blackPawny6)
                    {
                        bPawn6Death = 1;
                    }
                    if (pieceX == blackPawnx7 && pieceY == blackPawny7)
                    {
                        bPawn7Death = 1;
                    }
                    if (pieceX == blackPawnx8 && pieceY == blackPawny8)
                    {
                        bPawn8Death = 1;
                    }
                    tiles [whiteKingy] [whiteKingx].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (whiteKing);
                    whiteKingy = pieceX;
                    whiteKingx = pieceY;

                    if (wRookCastle == 1)
                    {
                        tiles [whiteRookx] [whiteRooky].setIcon (null);
                        tiles [whiteRookx] [whiteRooky - 2].setIcon (whiteRook); //"movement"
                        whiteRookx = whiteRookx;
                        whiteRooky = whiteRooky - 2;
                    }
                    if (wRook1Castle == 1)
                    {
                        tiles [whiteRookx1] [whiteRooky1].setIcon (null);
                        tiles [whiteRookx1] [whiteRooky1 + 3].setIcon (whiteRook);
                        whiteRookx1 = whiteRookx1;
                        whiteRooky1 = whiteRooky1 + 3;
                    }
                }
            }
        }


        else if (pieceCode == 5) //black king
        {
            chkr = validCheck (pieceX, pieceY, pieceCode);
            kingThreat = check (pieceX, pieceY, pieceCode);

            if (chkr != 1)
            {
                return;
            }
            else if (chkr == 1)
            {
                if (kingThreat == 1)
                {
                    return;
                }
                else
                {

                    tiles [blackKingx] [blackKingy].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (blackKing);
                    blackKingx = pieceX;
                    blackKingy = pieceY;

                    if (bRookCastle == 1)
                    {
                        tiles [blackRookx] [blackRooky].setIcon (null);
                        tiles [blackRookx] [blackRooky - 2].setIcon (blackRook); //"movement"
                        blackRookx = blackRookx;
                        blackRooky = blackRooky - 2;
                    }
                    if (bRook1Castle == 1)
                    {
                        tiles [blackRookx1] [blackRooky1].setIcon (null);
                        tiles [blackRookx1] [blackRooky1 + 3].setIcon (blackRook);
                        blackRookx1 = blackRookx1;
                        blackRooky1 = blackRooky1 + 3;
                    }


                    if (pieceX == whiteKnightx && pieceY == whiteKnighty)
                    {
                        wKnight1Death = 1;
                    }
                    if (pieceX == whiteKnightx1 && pieceY == whiteKnighty1)
                    {
                        wKnight2Death = 1;
                    }
                    if (pieceX == whiteRookx && pieceY == whiteRooky)
                    {
                        wRook1Death = 1;
                    }
                    if (pieceX == whiteRookx1 && pieceY == whiteRooky1)
                    {
                        wRook2Death = 1;
                    }
                    if (pieceX == whiteBishx && pieceY == whiteBishy)
                    {
                        wBish1Death = 1;
                    }
                    if (pieceX == whiteBishx1 && pieceY == whiteBishy1)
                    {
                        wBish2Death = 1;
                    }
                    if (pieceX == whiteQueenx && pieceY == whiteQueeny)
                    {
                        wQueen1Death = 1;
                    }
                    if (pieceX == whitePawnx1 && pieceY == whitePawny1)
                    {
                        wPawn1Death = 1;
                    }
                    if (pieceX == whitePawnx2 && pieceY == whitePawny2)
                    {
                        wPawn3Death = 1;
                    }
                    if (pieceX == whitePawnx3 && pieceY == whitePawny3)
                    {
                        wPawn2Death = 1;
                    }
                    if (pieceX == whitePawnx4 && pieceY == whitePawny4)
                    {
                        wPawn4Death = 1;
                    }
                    if (pieceX == whitePawnx5 && pieceY == whitePawny5)
                    {
                        wPawn5Death = 1;
                    }
                    if (pieceX == whitePawnx6 && pieceY == whitePawny6)
                    {
                        wPawn6Death = 1;
                    }
                    if (pieceX == whitePawnx7 && pieceY == whitePawny7)
                    {
                        wPawn7Death = 1;
                    }
                    if (pieceX == whitePawnx8 && pieceY == whitePawny8)
                    {
                        wPawn8Death = 1;
                    }
                }
            }
        }


        else if (pieceCode == 1) //black knight
        {
            kingThreat = 0;
            chkr = validCheck (pieceX, pieceY, pieceCode);

            if (chkr != 1)
            {
                return;
            }
            else if (chkr == 1)
            {
                if (ran1 == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [blackKnightx] [blackKnighty].setIcon (null); //"movement"
                        tiles [pieceX] [pieceY].setIcon (blackKnight);
                        blackKnightx = pieceX;
                        blackKnighty = pieceY;
                        ran1 = 0;
                    }
                    else
                    {
                        tiles [blackKnightx] [blackKnighty].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackKnight);
                    }
                }
                else
                {
                    if (promKnightFlag == 1)
                    {
                        tiles [bPromoKnightx] [bPromoKnighty].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackKnight);
                        bPromoKnightx = pieceX;
                        bPromoKnighty = pieceY;
                        if (bPromKnightNum == 1)
                        {
                            blackPawnx1 = bPromoKnightx;
                            blackPawny1 = bPromoKnighty;
                        }
                        if (bPromKnightNum == 2)
                        {
                            blackPawnx2 = bPromoKnightx;
                            blackPawny2 = bPromoKnighty;
                        }
                        if (bPromKnightNum == 3)
                        {
                            blackPawnx3 = bPromoKnightx;
                            blackPawny3 = bPromoKnighty;
                        }
                        if (bPromKnightNum == 4)
                        {
                            blackPawnx4 = bPromoKnightx;
                            blackPawny4 = bPromoKnighty;
                        }
                        if (bPromKnightNum == 5)
                        {
                            blackPawnx5 = bPromoKnightx;
                            blackPawny5 = bPromoKnighty;
                        }
                        if (bPromKnightNum == 6)
                        {
                            blackPawnx6 = bPromoKnightx;
                            blackPawny6 = bPromoKnighty;
                        }
                        if (bPromKnightNum == 7)
                        {
                            blackPawnx7 = bPromoKnightx;
                            blackPawny7 = bPromoKnighty;
                        }
                        if (bPromKnightNum == 8)
                        {
                            blackPawnx8 = bPromoKnightx;
                            blackPawny8 = bPromoKnighty;
                        }
                    }
                    else
                    {
                        tiles [blackKnightx] [blackKnighty].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackKnight); //"movement"
                        blackKnightx = pieceX;
                        blackKnighty = pieceY;
                    }
                }

                whiteDeaths (pieceX, pieceY);
                colorDet = 0;
            }
        }
        else if (pieceCode == 11) //black knight
        {
            kingThreat = 0;
            chkr = validCheck (pieceX, pieceY, pieceCode);
            if (chkr != 1)
            {
                redChk = 1;
            }
            else if (chkr == 1)
            {
                if (ran1 == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [blackKnightx1] [blackKnighty1].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackKnight); //"movement"
                        blackKnightx1 = pieceX;
                        blackKnighty1 = pieceY;
                        ran1 = 0;
                    }
                    else
                    {
                        tiles [blackKnightx1] [blackKnighty1].setIcon (null); //"movement"
                        tiles [pieceX] [pieceY].setIcon (blackKnight);
                    }
                }
                else
                {
                    tiles [blackKnightx1] [blackKnighty1].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (blackKnight);
                    blackKnightx1 = pieceX;
                    blackKnighty1 = pieceY;
                }


                whiteDeaths (pieceX, pieceY);
                colorDet = 0;
            }
        }


        else if (pieceCode == 6) //white knight
        {
            kingThreat = 0;
            chkr = validCheck (pieceX, pieceY, pieceCode);

            if (chkr != 1)
            {
                return;
            }
            else if (chkr == 1)
            {
                if (ran == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [whiteKnightx] [whiteKnighty].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteKnight);
                        whiteKnightx = pieceX;
                        whiteKnighty = pieceY;
                        ran = 0;
                    }
                    else
                    {
                        tiles [whiteKnightx] [whiteKnighty].setIcon (null); //"movement"
                        tiles [pieceX] [pieceY].setIcon (whiteKnight);
                    }
                }
                else
                {
                    if (promKnightFlag == 1)
                    {
                        tiles [promoKnightx] [promoKnighty].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteKnight);
                        promoKnightx = pieceX;
                        promoKnighty = pieceY;
                        if (promKnightNum == 1)
                        {
                            whitePawnx1 = promoKnightx;
                            whitePawny1 = promoKnighty;
                        }
                        if (promKnightNum == 2)
                        {
                            whitePawnx2 = promoKnightx;
                            whitePawny2 = promoKnighty;
                        }
                        if (promKnightNum == 3)
                        {
                            whitePawnx3 = promoKnightx;
                            whitePawny3 = promoKnighty;
                        }
                        if (promKnightNum == 4)
                        {
                            whitePawnx4 = promoKnightx;
                            whitePawny4 = promoKnighty;
                        }
                        if (promKnightNum == 5)
                        {
                            whitePawnx5 = promoKnightx;
                            whitePawny5 = promoKnighty;
                        }
                        if (promKnightNum == 6)
                        {
                            whitePawnx6 = promoKnightx;
                            whitePawny6 = promoKnighty;
                        }
                        if (promKnightNum == 7)
                        {
                            whitePawnx7 = promoKnightx;
                            whitePawny7 = promoKnighty;
                        }
                        if (promKnightNum == 8)
                        {
                            whitePawnx8 = promoKnightx;
                            whitePawny8 = promoKnighty;
                        }
                    }
                    else
                    {
                        tiles [whiteKnightx] [whiteKnighty].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteKnight);
                        whiteKnightx = pieceX;
                        whiteKnighty = pieceY;
                    }
                }
                blackDeaths (pieceX, pieceY);

            }
        }


        else if (pieceCode == 66) //white knight
        {
            kingThreat = 0;
            chkr = validCheck (pieceX, pieceY, pieceCode);
            if (chkr != 1)
            {
                return;
            }
            else if (chkr == 1)
            {
                if (ran == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [whiteKnightx1] [whiteKnighty1].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteKnight);
                        whiteKnightx1 = pieceX;
                        whiteKnighty1 = pieceY;
                        ran = 0;
                    }
                    else
                    {
                        tiles [whiteKnightx1] [whiteKnighty1].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteKnight);
                    }
                }
                else
                {
                    tiles [whiteKnightx1] [whiteKnighty1].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (whiteKnight);
                    whiteKnightx1 = pieceX;
                    whiteKnighty1 = pieceY;
                }


                blackDeaths (pieceX, pieceY);
            }
        }


        else if (pieceCode == 2) //white rook
        {
            chkr = validCheck (pieceX, pieceY, pieceCode);
            if (chkr != 1)
            {
            }
            else if (chkr == 1)
            {
                if (ran == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [whiteRookx] [whiteRooky].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteRook);
                        whiteRookx = pieceX;
                        whiteRooky = pieceY;
                        ran = 0;
                    }
                    else
                    {
                        tiles [whiteRookx] [whiteRooky].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteRook);
                    }
                }
                else
                {
                    if (promRookFlag == 1)
                    {
                        tiles [promoRookx] [promoRooky].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteRook);
                        promoRookx = pieceX;
                        promoRooky = pieceY;

                        if (promRookNum == 1)
                        {
                            whitePawnx1 = promoRookx;
                            whitePawny1 = promoRooky;
                        }
                        else if (promRookNum == 2)
                        {
                            whitePawnx2 = promoRookx;
                            whitePawny2 = promoRooky;
                        }
                        else if (promRookNum == 3)
                        {
                            whitePawnx3 = promoRookx;
                            whitePawny3 = promoRooky;
                        }
                        else if (promRookNum == 4)
                        {
                            whitePawnx4 = promoRookx;
                            whitePawny4 = promoRooky;
                        }
                        else if (promRookNum == 5)
                        {
                            whitePawnx5 = promoRookx;
                            whitePawny5 = promoRooky;
                        }
                        else if (promRookNum == 6)
                        {
                            whitePawnx6 = promoRookx;
                            whitePawny6 = promoRooky;
                        }
                        else if (promRookNum == 7)
                        {
                            whitePawnx7 = promoRookx;
                            whitePawny7 = promoRooky;
                        }
                        else if (promRookNum == 8)
                        {
                            whitePawnx8 = promoRookx;
                            whitePawny8 = promoRooky;
                        }
                    }
                    else
                    {
                        tiles [whiteRookx] [whiteRooky].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteRook);
                        whiteRookx = pieceX;
                        whiteRooky = pieceY;
                    }
                }

                blackDeaths (pieceX, pieceY);
            }
        }


        else if (pieceCode == 3) //black rook
        {
            chkr = validCheck (pieceX, pieceY, pieceCode);
            if (chkr != 1)
            {
            }
            else if (chkr == 1)
            {

                if (ran1 == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [blackRookx] [blackRooky].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackRook);
                        blackRookx = pieceX;
                        blackRooky = pieceY;
                        ran1 = 0;
                    }
                    else
                    {
                        tiles [blackRookx] [blackRooky].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackRook);
                    }
                }
                else
                {
                    if (promRookFlag == 1)
                    {
                        tiles [bPromoRookx] [bPromoRooky].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackRook);
                        bPromoRookx = pieceX;
                        bPromoRooky = pieceY;

                        if (bPromRookNum == 1)
                        {
                            blackPawnx1 = bPromoRookx;
                            blackPawny1 = bPromoRooky;
                        }
                        else if (promRookNum == 2)
                        {
                            blackPawnx2 = bPromoRookx;
                            blackPawny2 = bPromoRooky;
                        }
                        else if (promRookNum == 3)
                        {
                            blackPawnx3 = bPromoRookx;
                            blackPawny3 = bPromoRooky;
                        }
                        else if (promRookNum == 4)
                        {
                            blackPawnx4 = bPromoRookx;
                            blackPawny4 = bPromoRooky;
                        }
                        else if (promRookNum == 5)
                        {
                            blackPawnx5 = bPromoRookx;
                            blackPawny5 = bPromoRooky;
                        }
                        else if (promRookNum == 6)
                        {
                            blackPawnx6 = bPromoRookx;
                            blackPawny6 = bPromoRooky;
                        }
                        else if (promRookNum == 7)
                        {
                            blackPawnx7 = bPromoRookx;
                            blackPawny7 = bPromoRooky;
                        }
                        else if (promRookNum == 8)
                        {
                            blackPawnx8 = bPromoRookx;
                            blackPawny8 = bPromoRooky;
                        }
                    }
                    else
                    {
                        tiles [blackRookx] [blackRooky].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackRook);
                        blackRookx = pieceX;
                        blackRooky = pieceY;
                    }
                }
                whiteDeaths (pieceX, pieceY);
            }
        }


        else if (pieceCode == 22) //white rook
        {
            chkr = validCheck (pieceX, pieceY, pieceCode);
            if (chkr != 1)
            {
            }
            else if (chkr == 1)
            {
                if (ran == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [whiteRookx1] [whiteRooky1].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteRook);
                        whiteRookx1 = pieceX;
                        whiteRooky1 = pieceY;
                        ran = 0;
                    }
                    else
                    {
                        tiles [whiteRookx1] [whiteRooky1].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteRook);
                    }

                }
                else
                {
                    tiles [whiteRookx1] [whiteRooky1].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (whiteRook);
                    whiteRookx1 = pieceX;
                    whiteRooky1 = pieceY;
                }
                blackDeaths (pieceX, pieceY);
            }
        }
        else if (pieceCode == 33) //black rook
        {
            chkr = validCheck (pieceX, pieceY, pieceCode);
            if (chkr != 1)
            {
            }
            else if (chkr == 1)
            {
                if (ran1 == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [blackRookx1] [blackRooky1].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackRook);
                        blackRookx1 = pieceX;
                        blackRooky1 = pieceY;
                        ran1 = 0;
                    }
                    else
                    {
                        tiles [blackRookx1] [blackRooky1].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackRook);
                    }
                }
                else
                {
                    tiles [blackRookx1] [blackRooky1].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (blackRook);
                    blackRookx1 = pieceX;
                    blackRooky1 = pieceY;
                }
                whiteDeaths (pieceX, pieceY);
            }
        }


        else if (pieceCode == 4) //white bishop
        {
            chkr = validCheck (pieceX, pieceY, pieceCode);
            if (chkr != 1)
            {
            }
            else if (chkr == 1)
            {
                if (ran == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [whiteBishx] [whiteBishy].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteBish);
                        whiteBishx = pieceX;
                        whiteBishy = pieceY;
                        ran = 0;
                    }
                    else
                    {
                        tiles [whiteBishx] [whiteBishy].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteBish);
                    }
                }
                else
                {
                    if (promBishFlag == 1)
                    {
                        tiles [promoBishx] [promoBishy].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteBish);
                        promoBishx = pieceX;
                        promoBishy = pieceY;
                        if (promBishNum == 1)
                        {
                            whitePawnx1 = promoBishx;
                            whitePawny1 = promoBishy;
                        }
                        if (promBishNum == 2)
                        {
                            whitePawnx2 = promoBishx;
                            whitePawny2 = promoBishy;
                        }
                        if (promBishNum == 3)
                        {
                            whitePawnx3 = promoBishx;
                            whitePawny3 = promoBishy;
                        }
                        if (promBishNum == 4)
                        {
                            whitePawnx4 = promoBishx;
                            whitePawny4 = promoBishy;
                        }
                        if (promBishNum == 5)
                        {
                            whitePawnx5 = promoBishx;
                            whitePawny5 = promoBishy;
                        }
                        if (promBishNum == 6)
                        {
                            whitePawnx6 = promoBishx;
                            whitePawny6 = promoBishy;
                        }
                        if (promBishNum == 7)
                        {
                            whitePawnx7 = promoBishx;
                            whitePawny7 = promoBishy;
                        }
                        if (promBishNum == 8)
                        {
                            whitePawnx8 = promoBishx;
                            whitePawny8 = promoBishy;
                        }
                    }
                    else
                    {
                        tiles [whiteBishx] [whiteBishy].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteBish);
                        whiteBishx = pieceX;
                        whiteBishy = pieceY;
                    }
                }

                blackDeaths (pieceX, pieceY);
            }
        }


        else if (pieceCode == 7) //black  bishop
        {
            chkr = validCheck (pieceX, pieceY, pieceCode);

            if (chkr != 1)
            {

            }
            else if (chkr == 1)
            {

                if (ran1 == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [blackBishx] [blackBishy].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackBish);
                        blackBishx = pieceX;
                        blackBishy = pieceY;
                        ran1 = 1;
                    }
                    else
                    {
                        tiles [blackBishx] [blackBishy].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackBish);
                    }
                }
                else
                {
                    if (promBishFlag == 1)
                    {
                        tiles [bPromoBishx] [bPromoBishy].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackBish);
                        bPromoBishx = pieceX;
                        bPromoBishy = pieceY;
                        if (bPromBishNum == 1)
                        {
                            blackPawnx1 = bPromoBishx;
                            blackPawny1 = bPromoBishy;
                        }
                        if (promBishNum == 2)
                        {
                            blackPawnx2 = bPromoBishx;
                            blackPawnx2 = bPromoBishy;
                        }
                        if (promBishNum == 3)
                        {
                            blackPawnx3 = bPromoBishx;
                            blackPawnx3 = bPromoBishy;
                        }
                        if (promBishNum == 4)
                        {
                            blackPawnx4 = bPromoBishx;
                            blackPawnx4 = bPromoBishy;
                        }
                        if (promBishNum == 5)
                        {
                            blackPawnx5 = bPromoBishx;
                            blackPawnx5 = bPromoBishy;
                        }
                        if (promBishNum == 6)
                        {
                            blackPawnx6 = bPromoBishx;
                            blackPawnx6 = bPromoBishy;
                        }
                        if (promBishNum == 7)
                        {
                            blackPawnx7 = bPromoBishx;
                            blackPawnx7 = bPromoBishy;
                        }
                        if (promBishNum == 8)
                        {
                            blackPawnx8 = bPromoBishx;
                            blackPawnx8 = bPromoBishy;
                        }
                    }
                    else
                    {
                        tiles [blackBishx] [blackBishy].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackBish);
                        blackBishx = pieceX;
                        blackBishy = pieceY;
                    }
                }
                whiteDeaths (pieceX, pieceY);
            }
        }


        else if (pieceCode == 44) //white bishop
        {
            chkr = validCheck (pieceX, pieceY, pieceCode);
            if (chkr != 1)
            {
            }
            else if (chkr == 1)
            {
                if (ran == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [whiteBishx1] [whiteBishy1].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteBish);
                        whiteBishx1 = pieceX;
                        whiteBishy1 = pieceY;
                        ran = 0;
                    }
                    else
                    {
                        tiles [whiteBishx1] [whiteBishy1].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteBish);
                    }
                }
                else
                {
                    tiles [whiteBishx1] [whiteBishy1].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (whiteBish);
                    whiteBishx1 = pieceX;
                    whiteBishy1 = pieceY;
                }
                blackDeaths (pieceX, pieceY);
            }
        }
        else if (pieceCode == 77) //black bishop
        {
            chkr = validCheck (pieceX, pieceY, pieceCode);
            if (chkr != 1)
            {
            }
            else if (chkr == 1)
            {
                if (ran1 == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [blackBishx1] [blackBishy1].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackBish);
                        blackBishx1 = pieceX;
                        blackBishy1 = pieceY;
                        ran1 = 1;
                    }
                    else
                    {
                        tiles [blackBishx1] [blackBishy1].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackBish);
                    }
                }
                else
                {
                    tiles [blackBishx1] [blackBishy1].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (blackBish);
                    blackBishx1 = pieceX;
                    blackBishy1 = pieceY;
                }
                whiteDeaths (pieceX, pieceY);
            }
        }


        else if (pieceCode == 8) //white queen
        {
            chkr = validCheck (pieceX, pieceY, pieceCode);
            if (chkr != 1)
            {
            }
            else if (chkr == 1)
            {
                if (ran == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [whiteQueenx] [whiteQueeny].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteQueen);
                        whiteQueenx = pieceX;
                        whiteQueeny = pieceY;
                        ran = 0;
                    }
                    else
                    {
                        tiles [whiteQueenx] [whiteQueeny].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteQueen);
                    }
                }
                else
                {
                    if (promQueenFlag == 1)
                    {
                        tiles [promoQueenx] [promoQueeny].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteQueen);
                        promoQueenx = pieceX;
                        promoQueeny = pieceY;
                        if (promQueenNum == 1)
                        {
                            whitePawnx1 = promoQueenx;
                            whitePawny1 = promoQueeny;
                        }
                        else if (promQueenNum == 2)
                        {
                            whitePawnx2 = promoQueenx;
                            whitePawny2 = promoQueeny;
                        }
                        else if (promQueenNum == 3)
                        {
                            whitePawnx3 = promoQueenx;
                            whitePawny3 = promoQueeny;
                        }
                        else if (promQueenNum == 4)
                        {
                            whitePawnx4 = promoQueenx;
                            whitePawny4 = promoQueeny;
                        }
                        else if (promQueenNum == 5)
                        {
                            whitePawnx5 = promoQueenx;
                            whitePawny5 = promoQueeny;
                        }
                        else if (promQueenNum == 6)
                        {
                            whitePawnx6 = promoQueenx;
                            whitePawny6 = promoQueeny;
                        }
                        else if (promQueenNum == 7)
                        {
                            whitePawnx7 = promoQueenx;
                            whitePawny7 = promoQueeny;
                        }
                        else if (promQueenNum == 8)
                        {
                            whitePawnx8 = promoQueenx;
                            whitePawny8 = promoQueeny;
                        }
                    }
                    else
                    {
                        tiles [whiteQueenx] [whiteQueeny].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (whiteQueen);
                        whiteQueenx = pieceX;
                        whiteQueeny = pieceY;
                    }
                }

                blackDeaths (pieceX, pieceY);
            }
        }


        else if (pieceCode == 9) //black queen
        {
            chkr = validCheck (pieceX, pieceY, pieceCode);
            if (chkr != 1)
            {
            }
            else if (chkr == 1)
            {
                if (ran1 == 1)
                {
                    if (pieceX == tempx && pieceY == tempy)
                    {
                        tiles [blackQueenx] [blackQueeny].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackQueen);
                        blackQueenx = pieceX;
                        blackQueeny = pieceY;
                        ran1 = 0;
                    }
                    else
                    {
                        tiles [blackQueenx] [blackQueeny].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackQueen);
                    }
                }
                else
                {
                    if (promQueenFlag == 1)
                    {
                        tiles [bPromoQueenx] [bPromoQueeny].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackQueen);
                        bPromoQueenx = pieceX;
                        bPromoQueeny = pieceY;
                        if (bPromQueenNum == 1)
                        {
                            blackPawnx1 = bPromoQueenx;
                            blackPawny1 = bPromoQueeny;
                        }
                        else if (bPromQueenNum == 2)
                        {
                            blackPawnx2 = bPromoQueenx;
                            blackPawny2 = bPromoQueeny;
                        }
                        else if (bPromQueenNum == 3)
                        {
                            blackPawnx3 = bPromoQueenx;
                            blackPawny3 = bPromoQueeny;
                        }
                        else if (bPromQueenNum == 4)
                        {
                            blackPawnx4 = bPromoQueenx;
                            blackPawny4 = bPromoQueeny;
                        }
                        else if (bPromQueenNum == 5)
                        {
                            blackPawnx5 = bPromoQueenx;
                            blackPawny5 = bPromoQueeny;
                        }
                        else if (bPromQueenNum == 6)
                        {
                            blackPawnx6 = bPromoQueenx;
                            blackPawny6 = bPromoQueeny;
                        }
                        else if (bPromQueenNum == 7)
                        {
                            blackPawnx7 = bPromoQueenx;
                            blackPawny7 = bPromoQueeny;
                        }
                        else if (bPromQueenNum == 8)
                        {
                            blackPawnx8 = bPromoQueenx;
                            blackPawny8 = bPromoQueeny;
                        }
                    }
                    else
                    {
                        tiles [blackQueenx] [blackQueeny].setIcon (null);
                        tiles [pieceX] [pieceY].setIcon (blackQueen);
                        blackQueenx = pieceX;
                        blackQueeny = pieceY;
                    }
                }
                whiteDeaths (pieceX, pieceY);
            }
        }
        else if (pieceCode == 101 || pieceCode == 102 || pieceCode == 103 || pieceCode == 104 || pieceCode == 105 || pieceCode == 106 || pieceCode == 107 || pieceCode == 108)
        {
            //white pawns
            if (pieceCode == 101)
            {
                whitePawnX = whitePawnx1;
                whitePawnY = whitePawny1;
            }
            if (pieceCode == 102)
            {
                whitePawnX = whitePawnx2;
                whitePawnY = whitePawny2;
            }
            if (pieceCode == 103)
            {
                whitePawnX = whitePawnx3;
                whitePawnY = whitePawny3;
            }
            if (pieceCode == 104)
            {
                whitePawnX = whitePawnx4;
                whitePawnY = whitePawny4;
            }
            if (pieceCode == 105)
            {
                whitePawnX = whitePawnx5;
                whitePawnY = whitePawny5;
            }
            if (pieceCode == 106)
            {
                whitePawnX = whitePawnx6;
                whitePawnY = whitePawny6;
            }
            if (pieceCode == 107)
            {
                whitePawnX = whitePawnx7;
                whitePawnY = whitePawny7;
            }
            if (pieceCode == 108)
            {
                whitePawnX = whitePawnx8;
                whitePawnY = whitePawny8;
            }
            chkr = validCheck (pieceX, pieceY, pieceCode);
            if (chkr != 1)
            {
                return;
            }
            else if (chkr == 1)
            {
                if (pieceCode == 101)
                {
                    if (pieceX == whitePawnx1 - 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [whitePawnx1] [whitePawny1].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                    tempPawnx = whitePawnx1;
                    tempPawny = whitePawny1;
                    whitePawnx1 = pieceX;
                    whitePawny1 = pieceY;
                }
                else if (pieceCode == 102)
                {
                    if (pieceX == whitePawnx2 - 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [whitePawnx2] [whitePawny2].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                    tempPawnx = whitePawnx2;
                    tempPawny = whitePawny2;
                    whitePawnx2 = pieceX;
                    whitePawny2 = pieceY;

                }
                else if (pieceCode == 103)
                {
                    if (pieceX == whitePawnx3 - 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [whitePawnx3] [whitePawny3].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                    tempPawnx = whitePawnx3;
                    tempPawny = whitePawny3;
                    whitePawnx3 = pieceX;
                    whitePawny3 = pieceY;

                }
                else if (pieceCode == 104)
                {
                    if (pieceX == whitePawnx4 - 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [whitePawnx4] [whitePawny4].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                    tempPawnx = whitePawnx4;
                    tempPawny = whitePawny4;
                    whitePawnx4 = pieceX;
                    whitePawny4 = pieceY;

                }
                else if (pieceCode == 105)
                {
                    if (pieceX == whitePawnx5 - 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [whitePawnx5] [whitePawny5].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                    tempPawnx = whitePawnx5;
                    tempPawny = whitePawny5;
                    whitePawnx5 = pieceX;
                    whitePawny5 = pieceY;

                }
                else if (pieceCode == 106)
                {
                    if (pieceX == whitePawnx6 - 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [whitePawnx6] [whitePawny6].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                    tempPawnx = whitePawnx6;
                    tempPawny = whitePawny6;
                    whitePawnx6 = pieceX;
                    whitePawny6 = pieceY;

                }
                else if (pieceCode == 107)
                {
                    if (pieceX == whitePawnx7 - 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [whitePawnx7] [whitePawny7].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                    tempPawnx = whitePawnx7;
                    tempPawny = whitePawny7;
                    whitePawnx7 = pieceX;
                    whitePawny7 = pieceY;

                }
                else if (pieceCode == 108)
                {
                    if (pieceX == whitePawnx8 - 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [whitePawnx8] [whitePawny8].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (whitePawn);
                    tempPawnx = whitePawnx8;
                    tempPawny = whitePawny8;
                    whitePawnx8 = pieceX;
                    whitePawny8 = pieceY;

                }
                blackDeaths (pieceX, pieceY);
            }
        }
        else if (pieceCode == 201 || pieceCode == 202 || pieceCode == 203 || pieceCode == 204 || pieceCode == 205 || pieceCode == 206 || pieceCode == 207 || pieceCode == 208)
        {
            //black pawns
            if (pieceCode == 201)
            {
                blackPawnX = blackPawnx1;
                blackPawnY = blackPawny1;
            }
            if (pieceCode == 202)
            {
                blackPawnX = blackPawnx2;
                blackPawnY = blackPawny2;
            }
            if (pieceCode == 203)
            {
                blackPawnX = blackPawnx3;
                blackPawnY = blackPawny3;
            }
            if (pieceCode == 204)
            {
                blackPawnX = blackPawnx4;
                blackPawnY = blackPawny4;
            }
            if (pieceCode == 205)
            {
                blackPawnX = blackPawnx5;
                blackPawnY = blackPawny5;
            }
            if (pieceCode == 206)
            {
                blackPawnX = blackPawnx6;
                blackPawnY = blackPawny6;
            }
            if (pieceCode == 207)
            {
                blackPawnX = blackPawnx7;
                blackPawnY = blackPawny7;
            }
            if (pieceCode == 208)
            {
                blackPawnX = blackPawnx8;
                blackPawnY = blackPawny8;
            }
            chkr = validCheck (pieceX, pieceY, pieceCode);
            if (chkr != 1)
            {
            }
            else if (chkr == 1)
            {
                if (pieceCode == 201)
                {
                    if (pieceX == blackPawnx1 + 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [blackPawnx1] [blackPawny1].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                    tempPawnx = blackPawnx1;
                    tempPawny = blackPawny1;
                    blackPawnx1 = pieceX;
                    blackPawny1 = pieceY;

                }
                else if (pieceCode == 202)
                {
                    if (pieceX == blackPawnx2 + 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [blackPawnx2] [blackPawny2].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                    tempPawnx = blackPawnx2;
                    tempPawny = blackPawny2;
                    blackPawnx2 = pieceX;
                    blackPawny2 = pieceY;

                }
                else if (pieceCode == 203)
                {
                    if (pieceX == blackPawnx3 + 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [blackPawnx3] [blackPawny3].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                    tempPawnx = blackPawnx3;
                    tempPawny = blackPawny3;
                    blackPawnx3 = pieceX;
                    blackPawny3 = pieceY;

                }
                else if (pieceCode == 204)
                {
                    if (pieceX == blackPawnx4 + 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [blackPawnx4] [blackPawny4].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                    tempPawnx = blackPawnx4;
                    tempPawny = blackPawny4;
                    blackPawnx4 = pieceX;
                    blackPawny4 = pieceY;

                }
                else if (pieceCode == 205)
                {
                    if (pieceX == blackPawnx5 + 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [blackPawnx5] [blackPawny5].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                    tempPawnx = blackPawnx5;
                    tempPawny = blackPawny5;
                    blackPawnx5 = pieceX;
                    blackPawny5 = pieceY;
                }
                else if (pieceCode == 206)
                {
                    if (pieceX == blackPawnx6 + 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [blackPawnx6] [blackPawny6].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                    tempPawnx = blackPawnx6;
                    tempPawny = blackPawny6;
                    blackPawnx6 = pieceX;
                    blackPawny6 = pieceY;


                }
                else if (pieceCode == 207)
                {
                    if (pieceX == blackPawnx7 + 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [blackPawnx7] [blackPawny7].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                    tempPawnx = blackPawnx7;
                    tempPawny = blackPawny7;
                    blackPawnx7 = pieceX;
                    blackPawny7 = pieceY;

                }
                else if (pieceCode == 208)
                {
                    if (pieceX == blackPawnx8 + 2)
                    {
                        enPass = 1;
                        enPassx = pieceX;
                        enPassy = pieceY;
                    }
                    tiles [blackPawnx8] [blackPawny8].setIcon (null);
                    tiles [pieceX] [pieceY].setIcon (blackPawn);
                    tempPawnx = blackPawnx8;
                    tempPawny = blackPawny8;
                    blackPawnx8 = pieceX;
                    blackPawny8 = pieceY;
                }
                whiteDeaths (pieceX, pieceY);
            }
        }


        for (int i = 0 ; i < 8 ; i++)
        {
            for (int j = 0 ; j < 8 ; j++)
            {
                if ((i + j) % 2 != 0)
                {
                    tiles [i] [j].setBackground (Color.GRAY);
                }
                if ((i + j) % 2 == 0)
                {
                    tiles [i] [j].setBackground (Color.WHITE);
                }
            }
        }
    }


    public int validCheck (int pieceX, int pieceY, int pieceCode)
    {
        //the primary function of this method is determining whether the move made by the user is valid or not
        //considerations such as castling and en passant are also made
        chk = 0;
        if (pieceCode == 0)
        {
            row = Math.abs (pieceX - whiteKingy);
            col = Math.abs (pieceY - whiteKingx);
            if ((row <= 1) && (col <= 1))
            {
                chk = 1;
            }
            if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook)
            {
                chk = 0;
            }
            if (wRookCastle == 1)
            {
                if (pieceX == whiteKingy && pieceY == whiteKingx + 2)
                {
                    chk = 1;
                }
            }
            if (wRook1Castle == 1)
            {
                if (pieceX == whiteKingy && pieceY == whiteKingx - 2)
                {
                    chk = 1;
                }
            }
        }
        else if (pieceCode == 5)
        {
            row = Math.abs (pieceX - blackKingx);
            col = Math.abs (pieceY - blackKingy);
            if ((row <= 1) && (col <= 1))
            {
                chk = 1;
            }

            if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook)
            {
                chk = 0;
            }

            if (bRookCastle == 1)
            {
                if (pieceX == blackKingx && pieceY == blackKingy + 2)
                {
                    chk = 1;
                }
            }
            if (bRook1Castle == 1)
            {
                if (pieceX == blackKingx && pieceY == blackKingy - 2)
                {
                    chk = 1;
                }
            }
        }
        else if (pieceCode == 1 || pieceCode == 11)
        {
            if (pieceCode == 11)
            {
                row = Math.abs (pieceX - blackKnightx1);
                col = Math.abs (pieceY - blackKnighty1);
            }
            else
            {
                row = Math.abs (pieceX - blackKnightx);
                col = Math.abs (pieceY - blackKnighty);
                if (promKnightFlag == 1)
                {
                    row = Math.abs (pieceX - bPromoKnightx);
                    col = Math.abs (pieceY - bPromoKnighty);
                }
            }

            if ((row == 2 && col == 1) || (row == 1 && col == 2))
            {
                chk = 1;
            }
            if (tiles [pieceX] [pieceY].getIcon () == whiteKing)
            {
                chk = 0;

            }
            if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackBish)
            {
                chk = 0;
            }
        }


        else if (pieceCode == 6 || pieceCode == 66)
        {

            if (pieceCode == 66)
            {
                row = Math.abs (pieceX - whiteKnightx1);
                col = Math.abs (pieceY - whiteKnighty1);
            }
            else
            {
                row = Math.abs (pieceX - whiteKnightx);
                col = Math.abs (pieceY - whiteKnighty);
                if (promKnightFlag == 1)
                {
                    row = Math.abs (pieceX - promoKnightx);
                    col = Math.abs (pieceY - promoKnighty);
                }
            }
            if ((row == 2 && col == 1) || (row == 1 && col == 2))
            {
                chk = 1;
            }
            if (tiles [pieceX] [pieceY].getIcon () == blackKing)
            {
                chk = 0;

            }
            if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
            {
                chk = 0;
            }
        }
        else if (pieceCode == 2)
        {

            if (promRookFlag == 1)
            {
                row = pieceX - promoRookx;
                col = pieceY - promoRooky;
                int i;
                try
                {
                    if (col == 0 && (pieceX < promoRookx))
                    {
                        for (i = 0 ; i < (promoRookx - pieceX) ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                        {
                            chk = 1;
                        }
                    }
                    if (col == 0 && (pieceX > promoRookx))
                    {
                        for (i = 0 ; i < (pieceX - promoRookx) ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY < promoRooky))
                    {
                        for (i = 0 ; i < (promoRooky - pieceY) ; i++)
                        {
                            if (tiles [pieceX] [pieceY + i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY > promoRooky))
                    {
                        for (i = 0 ; i < (pieceY - promoRooky) ; i++)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                        {
                            chk = 1;
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException poi)
                {
                }
                if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                {
                    chk = 0;
                }
            }
            else
            {
                row = pieceX - whiteRookx;
                col = pieceY - whiteRooky;
                int i;
                try
                {
                    if (col == 0 && (pieceX < whiteRookx))
                    {
                        for (i = 0 ; i < (whiteRookx - pieceX) ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                        {
                            chk = 1;
                        }
                    }
                    if (col == 0 && (pieceX > whiteRookx))
                    {
                        for (i = 0 ; i < (pieceX - whiteRookx) ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY < whiteRooky))
                    {
                        for (i = 0 ; i < (whiteRooky - pieceY) ; i++)
                        {
                            if (tiles [pieceX] [pieceY + i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY > whiteRooky))
                    {
                        for (i = 0 ; i < (pieceY - whiteRooky) ; i++)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                        {
                            chk = 1;
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException poi)
                {
                }
                if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                {
                    chk = 0;
                }
            }
        }
        else if (pieceCode == 3)
        {
            if (promRookFlag == 1)
            {
                row = pieceX - bPromoRookx;
                col = pieceY - bPromoRooky;
                int i;
                try
                {
                    if (col == 0 && (pieceX < bPromoRookx))
                    {
                        for (i = 0 ; i < (bPromoRookx - pieceX) ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                        {
                            chk = 1;
                        }
                    }
                    if (col == 0 && (pieceX > bPromoRookx))
                    {
                        for (i = 0 ; i < (pieceX - bPromoRookx) ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY < bPromoRooky))
                    {
                        for (i = 0 ; i < (bPromoRooky - pieceY) ; i++)
                        {
                            if (tiles [pieceX] [pieceY + i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY > bPromoRooky))
                    {
                        for (i = 0 ; i < (pieceY - bPromoRooky) ; i++)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                        {
                            chk = 1;
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException poi)
                {
                }
                if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackBish)
                {
                    chk = 0;
                }
            }
            else
            {
                row = pieceX - blackRookx;
                col = pieceY - blackRooky;
                int i;
                try
                {
                    if (col == 0 && (pieceX < blackRookx))
                    {
                        for (i = 0 ; i < (blackRookx - pieceX) ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                        {
                            chk = 1;
                        }
                    }
                    if (col == 0 && (pieceX > blackRookx))
                    {
                        for (i = 0 ; i < (pieceX - blackRookx) ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY < blackRooky))
                    {
                        for (i = 0 ; i < (blackRooky - pieceY) ; i++)
                        {
                            if (tiles [pieceX] [pieceY + i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY > blackRooky))
                    {
                        for (i = 0 ; i < (pieceY - blackRooky) ; i++)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                        {
                            chk = 1;
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException poi)
                {
                }
                if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackBish)
                {
                    chk = 0;
                }
            }
        }
        else if (pieceCode == 22)
        {
            row = pieceX - whiteRookx1;
            col = pieceY - whiteRooky1;
            int i;
            try
            {
                if (col == 0 && (pieceX < whiteRookx1))
                {
                    for (i = 0 ; i < (whiteRookx1 - pieceX) ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                    {
                        chk = 1;
                    }
                }
                if (col == 0 && (pieceX > whiteRookx1))
                {
                    for (i = 0 ; i < (pieceX - whiteRookx1) ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                    {
                        chk = 1;
                    }
                }
                if (row == 0 && (pieceY < whiteRooky1))
                {
                    for (i = 0 ; i < (whiteRooky1 - pieceY) ; i++)
                    {
                        if (tiles [pieceX] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                    {
                        chk = 1;
                    }
                }
                if (row == 0 && (pieceY > whiteRooky1))
                {
                    for (i = 0 ; i < (pieceY - whiteRooky1) ; i++)
                    {
                        if (tiles [pieceX] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                    {
                        chk = 1;
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException poi)
            {
            }
            if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
            {
                chk = 0;
            }
        }
        else if (pieceCode == 33)
        {
            row = pieceX - blackRookx1;
            col = pieceY - blackRooky1;
            int i;
            try
            {
                if (col == 0 && (pieceX < blackRookx1))
                {
                    for (i = 0 ; i < (blackRookx1 - pieceX) ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                    {
                        chk = 1;
                    }
                }
                if (col == 0 && (pieceX > blackRookx1))
                {
                    for (i = 0 ; i < (pieceX - blackRookx1) ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                    {
                        chk = 1;
                    }
                }
                if (row == 0 && (pieceY < blackRooky1))
                {
                    for (i = 0 ; i < (blackRooky1 - pieceY) ; i++)
                    {
                        if (tiles [pieceX] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                    {
                        chk = 1;
                    }
                }
                if (row == 0 && (pieceY > blackRooky1))
                {
                    for (i = 0 ; i < (pieceY - blackRooky1) ; i++)
                    {
                        if (tiles [pieceX] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                    {
                        chk = 1;
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException poi)
            {
            }
            if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackBish)
            {
                chk = 0;
            }
        }
        else if (pieceCode == 4)
        {
            int i, tempsum;


            if (promBishFlag == 1)
            {
                if (pieceX < promoBishx && pieceY < promoBishy)
                {

                    tempsum = pieceX - pieceY;
                    for (i = 0 ; i < promoBishx - pieceX ; i++)
                    {

                        if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > promoBishx && pieceY > promoBishy)
                {
                    for (i = 0 ; i < pieceX - promoBishx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > promoBishx && pieceY < promoBishy)
                {
                    for (i = 0 ; i < pieceX - promoBishx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX < promoBishx && pieceY > promoBishy)
                {
                    for (i = 0 ; i < promoBishx - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                    {
                        chk = 1;
                    }
                }
                if (Math.abs (pieceX - promoBishx) != Math.abs (pieceY - promoBishy))
                {
                    chk = 0;
                }

                if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                {
                    chk = 0;
                }
            }
            else
            {

                if (pieceX < whiteBishx && pieceY < whiteBishy)
                {

                    tempsum = pieceX - pieceY;
                    for (i = 0 ; i < whiteBishx - pieceX ; i++)
                    {

                        if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > whiteBishx && pieceY > whiteBishy)
                {
                    for (i = 0 ; i < pieceX - whiteBishx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > whiteBishx && pieceY < whiteBishy)
                {
                    for (i = 0 ; i < pieceX - whiteBishx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX < whiteBishx && pieceY > whiteBishy)
                {
                    for (i = 0 ; i < whiteBishx - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                    {
                        chk = 1;
                    }
                }


                if (Math.abs (pieceX - whiteBishx) != Math.abs (pieceY - whiteBishy))
                {
                    chk = 0;
                }

                if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                {
                    chk = 0;
                }
            }
        }
        else if (pieceCode == 7)
        {
            if (promBishFlag == 1)
            {
                int i, tempsum;
                if (pieceX < bPromoBishx && pieceY < bPromoBishy)
                {

                    tempsum = pieceX - pieceY;
                    for (i = 0 ; i < bPromoBishx - pieceX ; i++)
                    {

                        if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > bPromoBishx && pieceY > bPromoBishy)
                {
                    for (i = 0 ; i < pieceX - bPromoBishx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > bPromoBishx && pieceY < bPromoBishy)
                {
                    for (i = 0 ; i < pieceX - bPromoBishx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX < bPromoBishx && pieceY > bPromoBishy)
                {
                    for (i = 0 ; i < bPromoBishx - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                    {
                        chk = 1;
                    }
                }


                if (Math.abs (pieceX - bPromoBishx) != Math.abs (pieceY - bPromoBishy))
                {
                    chk = 0;
                }

                if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                {
                    chk = 0;
                }
            }
            else
            {
                int i, tempsum;
                if (pieceX < blackBishx && pieceY < blackBishy)
                {

                    tempsum = pieceX - pieceY;
                    for (i = 0 ; i < blackBishx - pieceX ; i++)
                    {

                        if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > blackBishx && pieceY > blackBishy)
                {
                    for (i = 0 ; i < pieceX - blackBishx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > blackBishx && pieceY < blackBishy)
                {
                    for (i = 0 ; i < pieceX - blackBishx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX < blackBishx && pieceY > blackBishy)
                {
                    for (i = 0 ; i < blackBishx - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                    {
                        chk = 1;
                    }
                }


                if (Math.abs (pieceX - blackBishx) != Math.abs (pieceY - blackBishy))
                {
                    chk = 0;
                }

                if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                {
                    chk = 0;
                }
            }
        }
        else if (pieceCode == 44)
        {
            int i, tempsum;

            if (pieceX < whiteBishx1 && pieceY < whiteBishy1)
            {

                tempsum = pieceX - pieceY;
                for (i = 0 ; i < whiteBishx1 - pieceX ; i++)
                {

                    if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                    {
                        chk = 0;
                        break;
                    }
                    else
                    {
                        chk = 1;
                    }
                }
                if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                {
                    chk = 1;
                }
            }
            else if (pieceX > whiteBishx1 && pieceY > whiteBishy1)
            {
                for (i = 0 ; i < pieceX - whiteBishx1 ; i++)
                {
                    if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                    {
                        chk = 0;
                        break;
                    }
                    else
                    {
                        chk = 1;
                    }
                }
                if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                {
                    chk = 1;
                }
            }
            else if (pieceX > whiteBishx1 && pieceY < whiteBishy1)
            {
                for (i = 0 ; i < pieceX - whiteBishx1 ; i++)
                {
                    if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                    {
                        chk = 0;
                        break;
                    }
                    else
                    {
                        chk = 1;
                    }
                }
                if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                {
                    chk = 1;
                }
            }
            else if (pieceX < whiteBishx1 && pieceY > whiteBishy1)
            {
                for (i = 0 ; i < whiteBishx1 - pieceX ; i++)
                {
                    if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                    {
                        chk = 0;
                        break;
                    }
                    else
                    {
                        chk = 1;
                    }
                }
                if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish || tiles [pieceX] [pieceY].getIcon () == blackQueen)
                {
                    chk = 1;
                }
            }


            if (Math.abs (pieceX - whiteBishx1) != Math.abs (pieceY - whiteBishy1))
            {
                chk = 0;
            }

            if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
            {
                chk = 0;
            }
        }
        else if (pieceCode == 77)
        {
            int i, tempsum;

            if (pieceX < blackBishx1 && pieceY < blackBishy1)
            {

                tempsum = pieceX - pieceY;
                for (i = 0 ; i < blackBishx1 - pieceX ; i++)
                {

                    if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                    {
                        chk = 0;
                        break;
                    }
                    else
                    {
                        chk = 1;
                    }
                }
                if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                {
                    chk = 1;
                }
            }
            else if (pieceX > blackBishx1 && pieceY > blackBishy1)
            {
                for (i = 0 ; i < pieceX - blackBishx1 ; i++)
                {
                    if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                    {
                        chk = 0;
                        break;
                    }
                    else
                    {
                        chk = 1;
                    }
                }
                if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                {
                    chk = 1;
                }
            }
            else if (pieceX > blackBishx1 && pieceY < blackBishy1)
            {
                for (i = 0 ; i < pieceX - blackBishx1 ; i++)
                {
                    if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                    {
                        chk = 0;
                        break;
                    }
                    else
                    {
                        chk = 1;
                    }
                }
                if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                {
                    chk = 1;
                }
            }
            else if (pieceX < blackBishx1 && pieceY > blackBishy1)
            {
                for (i = 0 ; i < blackBishx1 - pieceX ; i++)
                {
                    if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                    {
                        chk = 0;
                        break;
                    }
                    else
                    {
                        chk = 1;
                    }
                }
                if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                {
                    chk = 1;
                }
            }


            if (Math.abs (pieceX - blackBishx1) != Math.abs (pieceY - blackBishy1))
            {
                chk = 0;
            }

            if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackQueen)
            {
                chk = 0;
            }
        }
        else if (pieceCode == 8)
        {
            if (promQueenFlag == 1)
            {
                int i, tempsum;
                if (pieceX < promoQueenx && pieceY < promoQueeny)
                {

                    tempsum = pieceX - pieceY;
                    for (i = 0 ; i < promoQueenx - pieceX ; i++)
                    {

                        if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > promoQueenx && pieceY > promoQueeny)
                {
                    for (i = 0 ; i < pieceX - promoQueenx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > promoQueenx && pieceY < promoQueeny)
                {
                    for (i = 0 ; i < pieceX - promoQueenx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX < promoQueenx && pieceY > promoQueeny)
                {
                    for (i = 0 ; i < promoQueenx - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                    {
                        chk = 1;
                    }
                }


                if (Math.abs (pieceX - promoQueenx) != Math.abs (pieceY - promoQueeny))
                {
                    chk = 0;
                }

                if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == whiteKnight)
                {
                    chk = 0;
                }
                row = pieceX - promoQueenx;
                col = pieceY - promoQueeny;
                try
                {
                    if (col == 0 && (pieceX < promoQueenx))
                    {
                        for (i = 0 ; i < (promoQueenx - pieceX) ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                        {
                            chk = 1;
                        }
                    }
                    if (col == 0 && (pieceX > promoQueenx))
                    {
                        for (i = 0 ; i < (pieceX - promoQueenx) ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY < promoQueeny))
                    {
                        for (i = 0 ; i < (promoQueeny - pieceY) ; i++)
                        {
                            if (tiles [pieceX] [pieceY + i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY > promoQueeny))
                    {
                        for (i = 0 ; i < (pieceY - promoQueeny) ; i++)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                        {
                            chk = 1;
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException poi)
                {
                }
                if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                {
                    chk = 0;
                }
            }
            else
            {
                int i, tempsum;
                if (pieceX < whiteQueenx && pieceY < whiteQueeny)
                {

                    tempsum = pieceX - pieceY;
                    for (i = 0 ; i < whiteQueenx - pieceX ; i++)
                    {

                        if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > whiteQueenx && pieceY > whiteQueeny)
                {
                    for (i = 0 ; i < pieceX - whiteQueenx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > whiteQueenx && pieceY < whiteQueeny)
                {
                    for (i = 0 ; i < pieceX - whiteQueenx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX < whiteQueenx && pieceY > whiteQueeny)
                {
                    for (i = 0 ; i < whiteQueenx - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                    {
                        chk = 1;
                    }
                }


                if (Math.abs (pieceX - whiteQueenx) != Math.abs (pieceY - whiteQueeny))
                {
                    chk = 0;
                }

                if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                {
                    chk = 0;
                }
                row = pieceX - whiteQueenx;
                col = pieceY - whiteQueeny;
                try
                {
                    if (col == 0 && (pieceX < whiteQueenx))
                    {
                        for (i = 0 ; i < (whiteQueenx - pieceX) ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                        {
                            chk = 1;
                        }
                    }
                    if (col == 0 && (pieceX > whiteQueenx))
                    {
                        for (i = 0 ; i < (pieceX - whiteQueenx) ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY < whiteQueeny))
                    {
                        for (i = 0 ; i < (whiteQueeny - pieceY) ; i++)
                        {
                            if (tiles [pieceX] [pieceY + i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY > whiteQueeny))
                    {
                        for (i = 0 ; i < (pieceY - whiteQueeny) ; i++)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                        {
                            chk = 1;
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException poi)
                {
                }
                if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteBish || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteQueen)
                {
                    chk = 0;
                }
            }
        }
        else if (pieceCode == 9)
        {
            if (promQueenFlag == 1)
            {
                int i, tempsum;
                if (pieceX < bPromoQueenx && pieceY < bPromoQueeny)
                {

                    tempsum = pieceX - pieceY;
                    for (i = 0 ; i < bPromoQueenx - pieceX ; i++)
                    {

                        if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > bPromoQueenx && pieceY > bPromoQueeny)
                {
                    for (i = 0 ; i < pieceX - bPromoQueenx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > bPromoQueenx && pieceY < bPromoQueeny)
                {
                    for (i = 0 ; i < pieceX - bPromoQueenx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX < bPromoQueenx && pieceY > bPromoQueeny)
                {
                    for (i = 0 ; i < bPromoQueenx - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                    {
                        chk = 1;
                    }
                }


                if (Math.abs (pieceX - bPromoQueenx) != Math.abs (pieceY - bPromoQueeny))
                {
                    chk = 0;
                }

                if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackBish)
                {
                    chk = 0;
                }

                row = pieceX - bPromoQueenx;
                col = pieceY - bPromoQueeny;

                try
                {
                    if (col == 0 && (pieceX < bPromoQueenx))
                    {
                        for (i = 0 ; i < (bPromoQueenx - pieceX) ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                        {
                            chk = 1;
                        }
                    }
                    if (col == 0 && (pieceX > bPromoQueenx))
                    {
                        for (i = 0 ; i < (pieceX - bPromoQueenx) ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY < bPromoQueeny))
                    {
                        for (i = 0 ; i < (bPromoQueeny - pieceY) ; i++)
                        {
                            if (tiles [pieceX] [pieceY + i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY > bPromoQueeny))
                    {
                        for (i = 0 ; i < (pieceY - bPromoQueeny) ; i++)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                        {
                            chk = 1;
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException poi)
                {
                }
                if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackBish)
                {
                    chk = 0;
                }
            }
            else
            {
                int i, tempsum;
                if (pieceX < blackQueenx && pieceY < blackQueeny)
                {

                    tempsum = pieceX - pieceY;
                    for (i = 0 ; i < blackQueenx - pieceX ; i++)
                    {

                        if (tiles [pieceX + i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > blackQueenx && pieceY > blackQueeny)
                {
                    for (i = 0 ; i < pieceX - blackQueenx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX > blackQueenx && pieceY < blackQueeny)
                {
                    for (i = 0 ; i < pieceX - blackQueenx ; i++)
                    {
                        if (tiles [pieceX - i] [pieceY + i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == blackBish)
                    {
                        chk = 1;
                    }
                }
                else if (pieceX < blackQueenx && pieceY > blackQueeny)
                {
                    for (i = 0 ; i < blackQueenx - pieceX ; i++)
                    {
                        if (tiles [pieceX + i] [pieceY - i].getIcon () != null)
                        {
                            chk = 0;
                            break;
                        }
                        else
                        {
                            chk = 1;
                        }
                    }
                    if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                    {
                        chk = 1;
                    }
                }


                if (Math.abs (pieceX - blackQueenx) != Math.abs (pieceY - blackQueeny))
                {
                    chk = 0;
                }

                if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackBish)
                {
                    chk = 0;
                }

                row = pieceX - blackQueenx;
                col = pieceY - blackQueeny;

                try
                {
                    if (col == 0 && (pieceX < blackQueenx))
                    {
                        for (i = 0 ; i < (blackQueenx - pieceX) ; i++)
                        {
                            if (tiles [pieceX + i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                        {
                            chk = 1;
                        }
                    }
                    if (col == 0 && (pieceX > blackQueenx))
                    {
                        for (i = 0 ; i < (pieceX - blackQueenx) ; i++)
                        {
                            if (tiles [pieceX - i] [pieceY].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY < blackQueeny))
                    {
                        for (i = 0 ; i < (blackQueeny - pieceY) ; i++)
                        {
                            if (tiles [pieceX] [pieceY + i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                        {
                            chk = 1;
                        }
                    }
                    if (row == 0 && (pieceY > blackQueeny))
                    {
                        for (i = 0 ; i < (pieceY - blackQueeny) ; i++)
                        {
                            if (tiles [pieceX] [pieceY - i].getIcon () != null)
                            {
                                chk = 0;
                                break;
                            }
                            else
                            {
                                chk = 1;
                            }
                        }
                        if (tiles [pieceX] [pieceY].getIcon () == whitePawn || tiles [pieceX] [pieceY].getIcon () == whiteQueen || tiles [pieceX] [pieceY].getIcon () == whiteKnight || tiles [pieceX] [pieceY].getIcon () == whiteRook || tiles [pieceX] [pieceY].getIcon () == whiteBish)
                        {
                            chk = 1;
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException poi)
                {
                }
                if (tiles [pieceX] [pieceY].getIcon () == blackPawn || tiles [pieceX] [pieceY].getIcon () == whiteKing || tiles [pieceX] [pieceY].getIcon () == blackKing || tiles [pieceX] [pieceY].getIcon () == blackKnight || tiles [pieceX] [pieceY].getIcon () == blackQueen || tiles [pieceX] [pieceY].getIcon () == blackBish)
                {
                    chk = 0;
                }
            }
        }
        else if (pieceCode == 101 || pieceCode == 102 || pieceCode == 103 || pieceCode == 104 || pieceCode == 105 || pieceCode == 106 || pieceCode == 107 || pieceCode == 108)
        {
            int X, Y;
            X = whitePawnX - pieceX;
            Y = Math.abs (whitePawnY - pieceY);

            if (pieceX >= whitePawnX)
            {
                chk = 0;
            }
            else
            {
                if (X == 2 && firstMove == 0 || X == 1)
                {
                    if (tiles [pieceX] [pieceY].getIcon () != null)
                    {
                        chk = 0;
                    }
                    else
                    {
                        chk = 1;
                    }
                }

                if (X == 1)
                {


                    if ((Y == 1 && tiles [pieceX] [pieceY].getIcon () == blackPawn || Y == 1 && tiles [pieceX] [pieceY].getIcon () == blackKnight || Y == 1 && tiles [pieceX] [pieceY].getIcon () == blackBish || Y == 1 && tiles [pieceX] [pieceY].getIcon () == blackRook || Y == 1 && tiles [pieceX] [pieceY].getIcon () == blackQueen))
                    {
                        chk = 1;
                    }

                    if (Y == 1 && tiles [pieceX] [pieceY].getIcon () == whiteKing || Y == 1 && tiles [pieceX] [pieceY].getIcon () == blackKing)
                    {
                        chk = 0;
                    }

                    if (Y == 1 && tiles [pieceX] [pieceY].getIcon () == null)
                    {
                        chk = 0;
                    }
                }
            }

            if (X == 1 && Y == 1 && tiles [pieceX] [pieceY].getIcon () == null)
            {
                if (enPass == 1)
                {
                    for (int i = 0 ; i < 8 ; i++)
                    {
                        if (pieceX + 1 == enPassx && pieceY == enPassy)
                        {
                            if (enPassx == blackPAWNXs [i] && enPassy == blackPAWNYs [i])
                            {
                                bPawnDeaths [i] = 1;
                                tiles [enPassx] [enPassy].setIcon (null);
                                chk = 1;
                            }
                        }
                    }
                }
            }
        }
        else if (pieceCode == 201 || pieceCode == 202 || pieceCode == 203 || pieceCode == 204 || pieceCode == 205 || pieceCode == 206 || pieceCode == 207 || pieceCode == 208)
        {
            int X, Y;
            X = pieceX - blackPawnX;
            Y = Math.abs (blackPawnY - pieceY);

            if (pieceX <= blackPawnX)
            {
                chk = 0;
            }
            else
            {
                if (X == 2 && firstMove == 0 || X == 1)
                {
                    chk = 1;
                }

                if (X == 1)
                {
                    if ((Y == 1 && tiles [pieceX] [pieceY].getIcon () == whitePawn || Y == 1 && tiles [pieceX] [pieceY].getIcon () == whiteKnight || Y == 1 && tiles [pieceX] [pieceY].getIcon () == whiteBish || Y == 1 && tiles [pieceX] [pieceY].getIcon () == whiteRook || Y == 1 && tiles [pieceX] [pieceY].getIcon () == whiteQueen))
                    {
                        chk = 1;
                    }

                    if (Y == 1 && tiles [pieceX] [pieceY].getIcon () == whiteKing || Y == 1 && tiles [pieceX] [pieceY].getIcon () == blackKing)
                    {
                        chk = 0;
                    }

                    if (Y == 1 && tiles [pieceX] [pieceY].getIcon () == null)
                    {
                        chk = 0;
                    }
                }
            }
        }
        return chk;
    }


    public static void main (String[] args)
    {
        new Chess ();
    }
}

import java.awt.Color;

public class Piece {
	
	boolean [][]pieceArray;
	Color color;
	
	public boolean[][] getPiece(int num)
	{
		if(num==0) // O piece
		{
			pieceArray = new boolean[2][2];
			pieceArray[0][0]=true;
			pieceArray[0][1]=true;
			pieceArray[1][0]=true;
			pieceArray[1][1]=true;
			color = Color.yellow;
		}
		else if(num==1) // I piece
		{
			pieceArray = new boolean[4][1];
			pieceArray[0][0]=true;
			pieceArray[1][0]=true;
			pieceArray[2][0]=true;
			pieceArray[3][0]=true;
			color = Color.cyan;
		}
		else if(num==2) // T piece
		{
			pieceArray = new boolean[3][2];
			pieceArray[0][0]=false;
			pieceArray[1][0]=true;
			pieceArray[2][0]=false;
			pieceArray[0][1]=true;
			pieceArray[1][1]=true;
			pieceArray[2][1]=true;
			color = Color.pink;
		} 
		else if(num==3) // Z piece
		{
			pieceArray = new boolean[3][2];
			pieceArray[0][0]=true;
			pieceArray[1][0]=true;
			pieceArray[2][0]=false;
			pieceArray[0][1]=false;
			pieceArray[1][1]=true;
			pieceArray[2][1]=true;
			color = Color.red;
		} 
		else if(num==4) // S piece
		{
			pieceArray = new boolean[3][2];
			pieceArray[0][0]=false;
			pieceArray[1][0]=true;
			pieceArray[2][0]=true;
			pieceArray[0][1]=true;
			pieceArray[1][1]=true;
			pieceArray[2][1]=false;
			color = Color.green;
		} 
		else if(num==5) // L piece
		{
			pieceArray = new boolean[3][2];
			pieceArray[0][0]=true;
			pieceArray[1][0]=true;
			pieceArray[2][0]=true;
			pieceArray[0][1]=true;
			pieceArray[1][1]=false;
			pieceArray[2][1]=false;
			color = Color.orange;
		} 
		else if(num==6) // J piece
		{
			pieceArray = new boolean[3][2];
			pieceArray[0][0]=true;
			pieceArray[1][0]=true;
			pieceArray[2][0]=true;
			pieceArray[0][1]=false;
			pieceArray[1][1]=false;
			pieceArray[2][1]=true;
			color = Color.blue;
		}	
		else
		{
			pieceArray = new boolean[1][1];
			pieceArray[0][0]=false;
		}
		return pieceArray;
	}	
	
	public Color getColor()
	{
		return color;
	}
}

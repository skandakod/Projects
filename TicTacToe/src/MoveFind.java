
public class MoveFind {

	int order = 0, count = 0, globalDepth = 0;
	boolean [][]pBox, cBox, boxOcc;
	boolean endC = false, endP=false, ran=false, turn = false;
	int compMove[]=new int[2];
	int notOcc[][];
	
	Win win = new Win();

	public MoveFind(){}

	public void setOrder(int num)
	{
		order = num;
		pBox = new boolean[order][order];
		cBox = new boolean[order][order];	
		boxOcc=new boolean[order][order];
		compMove[0]=order;
		compMove[1]=order;
		notOcc=new int[order*order][2];
	}

	public void reset()
	{
		pBox = new boolean[order][order];
		cBox = new boolean[order][order];	
		boxOcc=new boolean[order][order];
		endC = false; 
		endP=false; 
		ran=false;
		turn = false;
		count = 0;
		compMove= new int[2];
		compMove[0]=order;
		compMove[1]=order;
		globalDepth = 0;
		notOcc=new int[order*order][2];
	}

	public boolean update(boolean[][] player, boolean[][] computer,boolean [][] occupiedBoxes, int depth, boolean playerTurn)
	{
		globalDepth = depth;
		if(endP)
		{			
			return true;
		}
		else
		{
			pBox = player;
			cBox = computer;
			boxOcc = occupiedBoxes;

			eachChild(boxOcc);

			minimax(pBox, cBox,notOcc, depth, playerTurn, -100000, 100000);
			count = 0;

			cBox[getCompMove()[0]][getCompMove()[1]]=true;

			if(win(cBox))
			{
				endC=true;
				return true;
			}
		}			
		return false;
	}

	public void eachChild(boolean[][]boxOcc)
	{
		int count = 0;

		for(int j = 0; j < notOcc.length; j++)
		{
			for(int i = 0; i < 2; i++)
			{				
				notOcc[j][i] = -1;					
			}
		}

		for(int j = 0; j < order; j++)
		{
			for(int i = 0; i < order; i++)
			{
				if(!boxOcc[i][j])
				{
					notOcc[count][0] = i;
					notOcc[count][1] = j;				

					count++;
				}
			}
		}
	}

	public int minimax(boolean [][]pBox, boolean [][]cBox,int[][]notOcc, int depth, boolean player, int alpha, int beta)
	{
		count++;
		int playerEval = 0;
		int eval = 0;
		int compEval = 0;

		if(depth == 0 || endC || endP)
		{
			endC = false;
			endP = false;
			return evalPos();
		}		

		if(player)
		{
			ran=false;
			playerEval = -1000;
			for(int i = 0; i < notOcc.length && notOcc[i][0]>=0;i++)
			{
				pBox[notOcc[i][0]][notOcc[i][1]]=true;
				int tempX = notOcc[i][0];
				int tempY = notOcc[i][1];
				boxOcc[notOcc[i][0]][notOcc[i][1]]=true;
				eachChild(boxOcc);

				if(win(pBox))
				{
					endP=true;
				}

				eval = minimax(pBox, cBox, notOcc, depth-1, false, alpha, beta);
				if(eval>=playerEval && depth==globalDepth)
				{
					setCompMove(tempX,tempY);
				}
				
				playerEval = Math.max(playerEval, eval);	
				alpha = Math.max(alpha, eval);
				boxOcc[tempX][tempY]=false;
				eachChild(boxOcc);
				pBox[tempX][tempY]=false;
				ran=true;
				
				if(beta<alpha)
				{
					break;
				}
			}

			if(!ran)
			{
				return evalPos();
			}
			return playerEval;  
		}
		else
		{					
			ran=false;
			compEval = 1000;
			for(int i = 0; i < notOcc.length && notOcc[i][0]>=0;i++)
			{
				cBox[notOcc[i][0]][notOcc[i][1]]=true;
				int tempX = notOcc[i][0];
				int tempY = notOcc[i][1];
				boxOcc[notOcc[i][0]][notOcc[i][1]]=true;
				eachChild(boxOcc);

				if(win(cBox))
				{
					endC=true;
				}

				eval = minimax(pBox, cBox, notOcc, depth-1, true, alpha, beta);

				if(eval<=compEval && depth==globalDepth)
				{
					setCompMove(tempX,tempY);
				}
				
				compEval = Math.min(compEval, eval);	
				beta = Math.min(beta, eval);
				boxOcc[tempX][tempY]=false;
				eachChild(boxOcc);
				cBox[tempX][tempY]=false;
				ran=true;
				
				if(beta<alpha)
				{
					break;
				}
			}

			if(!ran)
			{
				return evalPos();
			}
			return compEval; 
		}
	}

	public int[] getCompMove()
	{
		return compMove;
	}

	public void setCompMove(int a, int b)
	{
		compMove[0]=a;
		compMove[1]=b;
	}

	public int evalPos()
	{
		int result = 0;
		for(int j = 0; j < order; j++)
		{
			for(int i = 0; i < order; i++)
			{
				if(cBox[i][j])
				{
					if(win(cBox))
					{
						if(!endP)
						{
							return -1000;
						}
					}

					if(win(pBox))
					{
						if(!endC)
						{
							return 1000;
						}
					}

					if(i-1>=0) //left
					{
						if(cBox[i-1][j])
						{
							result--;
						}
					}

					if(j-1>=0) //up
					{
						if(cBox[i][j-1])
						{
							result--;
						}
					}

					if(i+1<3) //right
					{
						if(cBox[i+1][j])
						{
							result--;
						}
					}

					if(j+1<3) //down
					{
						if(cBox[i][j+1])
						{
							result--;
						}
					}

					if(i-1>=0 && j-1>=0) //top left
					{
						if(cBox[i-1][j-1])
						{
							result--;
						}
					}

					if(j-1>=0 && i+1<3) //top right
					{
						if(cBox[i+1][j-1])
						{
							result--;
						}
					}

					if(j+1<3 && i-1>=0) //bottom left
					{
						if(cBox[i-1][j+1])
						{
							result--;
						}
					}

					if(j+1<3 && i+1<3) //bottom right
					{
						if(cBox[i+1][j+1])
						{
							result--;
						}
					}
				}
				else if(pBox[i][j])
				{
					if(i-1>=0) //left
					{
						if(pBox[i-1][j])
						{
							result++;
						}
					}

					if(j-1>=0) //up
					{
						if(pBox[i][j-1])
						{
							result++;
						}
					}

					if(i+1<3) //right
					{
						if(pBox[i+1][j])
						{
							result++;
						}
					}

					if(j+1<3) //down
					{
						if(pBox[i][j+1])
						{
							result++;
						}
					}

					if(i-1>=0 && j-1>=0) //top left
					{
						if(pBox[i-1][j-1])
						{
							result++;
						}
					}

					if(j-1>=0 && i+1<3) //top right
					{
						if(pBox[i+1][j-1])
						{
							result++;
						}
					}

					if(j+1<3 && i-1>=0) //bottom left
					{
						if(pBox[i-1][j+1])
						{
							result++;
						}
					}

					if(j+1<3 && i+1<3) //bottom right
					{
						if(pBox[i+1][j+1])
						{
							result++;
						}
					}
				}
			}
		}		
		return result;
	}

	public boolean win(boolean[][] arr)
	{		
		if(win.win(arr, order))
		{
			return true;
		}		
		return false;
	}
}

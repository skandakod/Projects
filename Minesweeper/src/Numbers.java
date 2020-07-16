public class Numbers {

	int lim;
	int[][]nums;
	public void calculate(boolean[][]mines, int order)
	{
		lim = order;
		nums = new int[order][order];
		for(int i = 0;i<order;i++)
		{
			for(int j = 0;j<order;j++)
			{
				if(!mines[i][j])
				{
					nums[i][j]=check(i,j, mines);
				}
			}
		}
	}

	public int check(int i, int j, boolean[][]mines)
	{
		int result = 0;

		if(i-1>=0) //left
		{
			if(mines[i-1][j])
			{
				result++;
			}
		}

		if(j-1>=0) //up
		{
			if(mines[i][j-1])
			{
				result++;
			}
		}

		if(i+1<lim) //right
		{
			if(mines[i+1][j])
			{
				result++;
			}
		}

		if(j+1<lim) //down
		{
			if(mines[i][j+1])
			{
				result++;
			}
		}

		if(i-1>=0 && j-1>=0) //top left
		{
			if(mines[i-1][j-1])
			{
				result++;
			}
		}

		if(j-1>=0 && i+1<lim) //top right
		{
			if(mines[i+1][j-1])
			{
				result++;
			}
		}

		if(j+1<lim && i-1>=0) //bottom left
		{
			if(mines[i-1][j+1])
			{
				result++;
			}
		}

		if(j+1<lim && i+1<lim) //bottom right
		{
			if(mines[i+1][j+1])
			{
				result++;
			}

		}	
		return result;
	}
}

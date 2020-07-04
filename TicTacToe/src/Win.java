
public class Win {
	public boolean win(boolean arr[][], int order)
	{
		if(order==3)
		{
			if(arr[0][0] && arr[1][0] && arr[2][0])
				return true;
			if(arr[0][1] && arr[1][1] && arr[2][1])
				return true;
			if(arr[0][2] && arr[1][2] && arr[2][2])
				return true;
			if(arr[0][0] && arr[0][1] && arr[0][2])
				return true;
			if(arr[1][0] && arr[1][1] && arr[1][2])
				return true;
			if(arr[2][0] && arr[2][1] && arr[2][2])
				return true;
			if(arr[0][0] && arr[1][1] && arr[2][2])
				return true;
			if(arr[2][0] && arr[1][1] && arr[0][2])
				return true;
		}
		else if(order == 4)
		{			
			if(arr[0][0] && arr[1][0] && arr[2][0] && arr[3][0])
				return true;
			if(arr[0][1] && arr[1][1] && arr[2][1] && arr[3][1])
				return true;
			if(arr[0][2] && arr[1][2] && arr[2][2] && arr[3][2])
				return true;
			if(arr[0][3] && arr[1][3] && arr[2][3] && arr[3][3])
				return true;			
			if(arr[0][0] && arr[0][1] && arr[0][2] && arr[0][3])
				return true;
			if(arr[1][0] && arr[1][1] && arr[1][2] && arr[1][3])
				return true;
			if(arr[2][0] && arr[2][1] && arr[2][2] && arr[2][3])
				return true;
			if(arr[3][0] && arr[3][1] && arr[3][2] && arr[3][3])
				return true;				
			if(arr[0][0] && arr[1][1] && arr[2][2] && arr[3][3])
				return true;
			if(arr[3][0] && arr[2][1] && arr[1][2] && arr[0][3])
				return true;		
		}
		else if(order == 5)
		{
			if(arr[0][0] && arr[1][0] && arr[2][0] && arr[3][0])
				return true;
			if(arr[0][1] && arr[1][1] && arr[2][1] && arr[3][1])
				return true;
			if(arr[0][2] && arr[1][2] && arr[2][2] && arr[3][2])
				return true;
			if(arr[0][3] && arr[1][3] && arr[2][3] && arr[3][3])
				return true;			
			if(arr[0][0] && arr[0][1] && arr[0][2] && arr[0][3])
				return true;
			if(arr[1][0] && arr[1][1] && arr[1][2] && arr[1][3])
				return true;
			if(arr[2][0] && arr[2][1] && arr[2][2] && arr[2][3])
				return true;
			if(arr[3][0] && arr[3][1] && arr[3][2] && arr[3][3])
				return true;				
			if(arr[0][0] && arr[1][1] && arr[2][2] && arr[3][3])
				return true;
			if(arr[3][0] && arr[2][1] && arr[1][2] && arr[0][3])
				return true;			
			if(arr[1][0] && arr[2][0] && arr[3][0] && arr[4][0])
				return true;
			if(arr[1][1] && arr[2][1] && arr[3][1] && arr[4][1])
				return true;
			if(arr[1][2] && arr[2][2] && arr[3][2] && arr[4][2])
				return true;
			if(arr[1][3] && arr[2][3] && arr[3][3] && arr[4][3])
				return true;			
			if(arr[1][0] && arr[1][1] && arr[1][2] && arr[1][3])
				return true;
			if(arr[2][0] && arr[2][1] && arr[2][2] && arr[2][3])
				return true;
			if(arr[3][0] && arr[3][1] && arr[3][2] && arr[3][3])
				return true;
			if(arr[4][0] && arr[4][1] && arr[4][2] && arr[4][3])
				return true;				
			if(arr[1][0] && arr[2][1] && arr[3][2] && arr[4][3])
				return true;
			if(arr[4][0] && arr[3][1] && arr[2][2] && arr[1][3])
				return true;			
			if(arr[0][1] && arr[1][1] && arr[2][1] && arr[3][1])
				return true;
			if(arr[0][2] && arr[1][2] && arr[2][2] && arr[3][2])
				return true;
			if(arr[0][3] && arr[1][3] && arr[2][3] && arr[3][3])
				return true;
			if(arr[0][4] && arr[1][4] && arr[2][4] && arr[3][4])
				return true;			
			if(arr[0][1] && arr[0][2] && arr[0][3] && arr[0][4])
				return true;
			if(arr[1][1] && arr[1][2] && arr[1][3] && arr[1][4])
				return true;
			if(arr[2][1] && arr[2][2] && arr[2][3] && arr[2][4])
				return true;
			if(arr[3][1] && arr[3][2] && arr[3][3] && arr[3][4])
				return true;				
			if(arr[0][1] && arr[1][2] && arr[2][3] && arr[3][4])
				return true;
			if(arr[3][1] && arr[2][2] && arr[1][3] && arr[0][4])
				return true;			
			if(arr[1][1] && arr[2][1] && arr[3][1] && arr[4][1])
				return true;
			if(arr[1][2] && arr[2][2] && arr[3][2] && arr[4][2])
				return true;
			if(arr[1][3] && arr[2][3] && arr[3][3] && arr[4][3])
				return true;
			if(arr[1][4] && arr[2][4] && arr[3][4] && arr[4][4])
				return true;			
			if(arr[1][1] && arr[1][2] && arr[1][3] && arr[1][4])
				return true;
			if(arr[2][1] && arr[2][2] && arr[2][3] && arr[2][4])
				return true;
			if(arr[3][1] && arr[3][2] && arr[3][3] && arr[3][4])
				return true;
			if(arr[4][1] && arr[4][2] && arr[4][3] && arr[4][4])
				return true;				
			if(arr[1][1] && arr[2][2] && arr[3][3] && arr[4][4])
				return true;
			if(arr[4][1] && arr[3][2] && arr[2][3] && arr[1][4])
				return true;
		}
		return false;
	}
}


public class Food {

        int[]foodL = new int[2];
        int scl = 20;
        
        public Food()
        {
                
        }
        
        public void setLocation(int cols,int rows)
        {
                foodL[0] = (int)(Math.random()*cols)+2;
                foodL[1] = (int)(Math.random()*rows)+2;
        }
        
        
        public int getFoodX()
        {
                return foodL[0]*scl;
        }
        
        public int getFoodY()
        {
                return foodL[1]*scl;
        }
}

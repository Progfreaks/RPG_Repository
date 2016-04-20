package valueobject;



public class Board {
	private int[][] map = new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0},
									  {0,0,0,0,1,1,1,1,1,1,0,0},
								   	  {0,1,1,1,1,1,1,1,1,1,0,0},
									  {0,2,1,1,1,1,1,1,1,3,1,0},
									  {0,1,1,1,1,1,1,1,1,1,1,0},
									  {0,1,1,2,3,1,1,2,1,1,0,0},
									  {0,1,1,1,1,1,0,1,1,1,1,0},
									  {0,1,1,1,1,1,0,1,1,1,1,0},
									  {0,1,3,1,1,1,0,1,1,3,1,0},
									  {0,0,0,0,1,1,1,1,1,1,1,0},
									  {0,1,1,1,1,4,1,1,1,1,1,0},
									  {0,0,0,0,0,0,0,0,0,0,0,0}};
public Board(){
	

	}
public int[][] getBoard(){
	

	return this.map;
	
	}
public void setMap(int[][] board){
	
	
	this.map = board;
	
	}
public void printBoard(){
	
	for(int i = 0; i < map.length; i++)
	{
		for(int s = 0; s < map[i].length; s++)
		{
			System.out.print(map[i][s]);
		}
		System.out.println("");
	}
}
}




public class Game {

	public static void main(String[] args) {
		boolean[][] cells = new boolean[150][150];
		boolean[][] next = new boolean[150][150];
		
		for( int i = 0; i < cells.length; ++i )
			for( int j = 0; j < cells[i].length; ++j )
				if( Math.random() < .7 )
					cells[i][j] = true;
		
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.enableDoubleBuffering();		
		while( true ) {			
			draw(cells);
			StdDraw.show();
			for( int row = 0; row < cells.length; ++row )
				for( int column = 0; column < cells[row].length; ++column ) {
					int livingNeighbors = livingNeighbors(cells, row, column);
					if( cells[row][column] ) { //alive!
						if( livingNeighbors == 2 || livingNeighbors == 3)
							next[row][column] = true;
						else
							next[row][column] = false;
					}
					else { //dead
						if( livingNeighbors == 3 )
							next[row][column] = true;
						else
							next[row][column] = false;					
					}
				}
			
			//swap cells and next
			boolean[][] temp = next;
			next = cells;
			cells = temp;
			
			StdDraw.pause(50);
			StdDraw.clear();
		}
		
	}
	
	public static void draw(boolean[][] cells) {		
		double y = 1 - .5 / cells.length;
		for( int row = 0; row < cells.length; ++row ) {
			double x = .5 / cells.length;
			for( int column = 0; column < cells[row].length; ++column ) {
				if( cells[row][column] )
					StdDraw.filledSquare(x, y, .5 / cells.length);
				x += 1.0 / cells.length;
			}
			y -= 1.0 / cells.length;
		}		
	}
	
	public static int livingNeighbors(boolean[][] cells, int row, int column) {
		int living = 0;
		for( int i = Math.max(row - 1, 0); i <= Math.min(row + 1, cells.length - 1); ++i ) {
			for( int j = Math.max(column - 1, 0); j <= Math.min(column + 1, cells[i].length - 1); ++j ) {
				if( cells[i][j] )
					living++;
			}
		}
		
		if( cells[row][column] )
			living--;
		
		return living;
	}
}

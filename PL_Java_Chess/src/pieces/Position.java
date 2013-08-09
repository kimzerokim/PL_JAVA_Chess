package pieces;

public class Position {
	
	//   a b c d e f g h
	// 8
	// 7
	// 6
	// 5
	// 4
	// 3
	// 2
	// 1
	
	int pieceX;
	int pieceY;
	
	public Position() {
		
	}
	/*
	 * int 형식으로 열을 받아올 때 
	 */
	public Position(int x, int y) {
		this.pieceX = x-1;
		this.pieceY = y-1;
	}
	/*
	 * string 형식으로 열을 받아올 때 
	 */
	public Position(String x, int y) {
		int transX = Character.toLowerCase(x.charAt(0)) - 'a';
		this.pieceX = transX;
		this.pieceY = y-1;		
	}
	
	public int getX() {
		return pieceX;
	}
	
	public int getY() {
		return pieceY;
	}
}

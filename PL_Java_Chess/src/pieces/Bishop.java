package pieces;

public class Bishop extends Piece {
	Bishop(Color color, Position position) {
		super(color, position, 3.0);
	}
	
	public Character getDefaultRepresentation() {
		return 'b';
	}
}

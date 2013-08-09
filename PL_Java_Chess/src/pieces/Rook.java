package pieces;

public class Rook extends Piece {
	Rook(Color color, Position position) {
		super(color, position, 5.0);
	}
	
	public Character getDefaultRepresentation() {
		return 'r';
	}
}

package pieces;

public class Pawn extends Piece {
	Pawn(Color color, Position position) {
		super(color, position, 1.0);
	}
	
	public Character getDefaultRepresentation() {
		return 'p';
	}
}

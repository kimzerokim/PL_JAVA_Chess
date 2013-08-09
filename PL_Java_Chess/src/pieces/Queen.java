package pieces;

public class Queen extends Piece {
	Queen(Color color, Position position) {
		super(color, position, 9.0);
	}
	
	public Character getDefaultRepresentation() {
		return 'q';
	}
}

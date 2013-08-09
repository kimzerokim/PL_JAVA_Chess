package pieces;

public class Knight extends Piece {
	Knight(Color color, Position position) {
		super(color, position, 2.5);
	}
	
	public Character getDefaultRepresentation() {
		return 'n';
	}
}

package pieces;

public class King extends Piece {
	King(Color color, Position position) {
		super(color, position, 0.0);
	}
	
	public Character getDefaultRepresentation() {
		return 'k';
	}
}

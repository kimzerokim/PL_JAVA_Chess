package pieces;


public class Blank extends Piece{
	Blank (Position position) {
		super(Color.BLANK, position, 0.0);
	}
	
	public Character getDefaultRepresentation() {
		return '.';
	}
}

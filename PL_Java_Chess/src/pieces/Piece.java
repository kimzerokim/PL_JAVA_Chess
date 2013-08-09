package pieces;

abstract public class Piece implements Comparable<Piece> {

	public enum Color {	WHITE, BLACK, BLANK	}

	public static final char BLANK_REPRESENTATION = '.';
	public static final char PAWN_REPRESENTATION = 'p';
	public static final char ROOK_REPRESENTATION = 'r';
	public static final char KNIGHT_REPRESENTATION = 'n';
	public static final char BISHOP_REPRESENTATION = 'b';
	public static final char QUEEN_REPRESENTATION = 'q';
	public static final char KING_REPRESENTATION = 'k';

	private final Color pieceColor;
	private Position piecePosition;
	private Double score;
	private Character representation;

	protected Piece(Color color, Position position, Double score) {
		this.pieceColor = color;
		this.piecePosition = position;
		this.score = score;
	}

	public static King createKing(Color color, Position position) {
		return new King(color, position);
	}

	public static Queen createQueen(Color color, Position position) {
		return new Queen(color, position);
	}

	public static Rook createRook(Color color, Position position) {
		return new Rook(color, position);
	}

	public static Bishop createBishop(Color color, Position position) {
		return new Bishop(color, position);
	}

	public static Knight createKnight(Color color, Position position) {
		return new Knight(color, position);
	}

	public static Pawn createPawn(Color color, Position position) {
		return new Pawn(color, position);
	}
	
	public static Blank createBlank(Position position) {
		return new Blank(position);
	}
	
	public void setPosition(Position position) {
		this.piecePosition = position;
	}
	
	public Color getColor() {
		return this.pieceColor;
	}
	
	public Double getScore() {
		return this.score;
	}
	
	public Position getPosition() {
		return this.piecePosition;
	}
	
	public Character getRepresentation() {
		representation = getDefaultRepresentation();
		
		if (this.pieceColor == Color.BLACK) {
			return Character.toUpperCase(representation);
		}
		else
			return representation;
	}
	
	abstract public Character getDefaultRepresentation();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((pieceColor == null) ? 0 : pieceColor.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (pieceColor != other.pieceColor)
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Piece that) {
		int compare = Integer.parseInt(String.valueOf(Math.round(that.getScore() - this.getScore())));
		return compare;
	}
}

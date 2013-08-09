package pieces;

public interface IPieceOperation extends Comparable<Piece>{
	public Character getRepresentation();
	public int compareTo(Piece that);
}

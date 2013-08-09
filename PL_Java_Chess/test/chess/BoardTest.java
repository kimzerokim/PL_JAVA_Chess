package chess;

import java.util.List;

import junit.framework.TestCase;
import pieces.Piece;
import pieces.Piece.Color;
import pieces.Position;

public class BoardTest extends TestCase {
	private static final String WHITE_PAWN_ROW = "pppppppp";
	private static final String BLACK_PAWN_ROW = "PPPPPPPP";
	Board board;
	
	@Override
	protected void setUp() throws Exception {
		board = new Board();
		board.initialize();
		super.setUp();
	}
	
	public void testCreate() throws Exception {
		Board board = new Board();
		assertTrue(board.isEmpty());
	}
	
	public void testSetPiece() throws Exception {
		Piece knight = Piece.createKnight(Color.WHITE, new Position());
		board.setPiece(knight, new Position("b", 6));
		assertEquals(knight, board.getPiece(new Position("b", 6)));
	}
	
	public void testRows() {
		assertEquals(WHITE_PAWN_ROW, board.getRow(1));
		assertEquals(BLACK_PAWN_ROW, board.getRow(6));
	}
	
	public void testPrintBoard() {
		String dot = "........";
		assertEquals("rnbqkbnr", board.getRow(0));
		assertEquals(WHITE_PAWN_ROW, board.getRow(1));
		assertEquals(dot, board.getRow(2));
		assertEquals(dot, board.getRow(3));
		assertEquals(dot, board.getRow(4));
		assertEquals(dot, board.getRow(5));
		assertEquals(BLACK_PAWN_ROW, board.getRow(6));
		assertEquals("RNBQKBNR", board.getRow(7));
//		board.print();
	}
	
	public void testGetPiecesCount() throws Exception {
		Piece piece = Piece.createPawn(Color.BLACK, new Position());
		assertEquals(8, board.getCount(piece));
		Piece piece2 = Piece.createQueen(Color.WHITE, new Position());
		assertEquals(1, board.getCount(piece2));
	}
	
	public void testGetPiece() throws Exception {
		Position pos1 = new Position("a", 8);
		assertTrue(Piece.createRook(Color.BLACK, new Position()).equals(board.getPiece(pos1)));
		Position pos2 = new Position("h", 1);
		assertTrue(Piece.createRook(Color.WHITE, new Position()).equals(board.getPiece(pos2)));
	}
	
	public void testPawnBonus() throws Exception {
		Board board = new Board();
		Piece whitePawn = Piece.createPawn(Color.WHITE, new Position());
		for (int i=5; i<9; i++) {
			board.setPiece(whitePawn, new Position("a", i));
		}		
		assertEquals(2.0, board.getScore(Color.WHITE), 0.05);
//		여기서 0.05는 오차 허용범위를 뜻한다. 형변환에서 발생할 수 있는 오차를 포용한다. 
	}
	/*
	 *  연습문제 5-4를 위한 테스트 
	 */
	public void testCountPieces() {
		Board board = new Board();
		Piece blackPawn = Piece.createPawn(Color.BLACK, new Position());
		
		// 연습문제에서 검은 졸의 갯수를 물어보고 있으므로 흰 말은 추가하지 않았다.		
		board.setPiece(Piece.createKing(Color.BLACK, new Position()), new Position("b", 8));
		board.setPiece(Piece.createRook(Color.BLACK, new Position()), new Position("c", 8));
		board.setPiece(Piece.createPawn(Color.BLACK, new Position()), new Position("a", 7));
		board.setPiece(Piece.createPawn(Color.BLACK, new Position()), new Position("c", 7));
		board.setPiece(Piece.createBishop(Color.BLACK, new Position()), new Position("d", 7));
		board.setPiece(Piece.createPawn(Color.BLACK, new Position()), new Position("b", 6));
		board.setPiece(Piece.createQueen(Color.BLACK, new Position()), new Position("e", 6));
		assertEquals(3, board.getCount(blackPawn));
	}
	
	/*
	 *  연습문제 5-5를 위한 테스트 
	 */
	public void testGenerateBoardEmpty () {
		Board board = new Board();
		// setUp에서는 initialize를 해주고 있으므로, new Board를 새로 만들어 Empty인 것을 확인한다.
		
		Piece WhiteKing = Piece.createKing(Color.WHITE, new Position());		
		Piece WhiteQueen = Piece.createQueen(Color.WHITE, new Position());
		Piece WhiteRook = Piece.createRook(Color.WHITE, new Position());
		Piece WhiteBishop = Piece.createBishop(Color.WHITE, new Position());
		Piece WhiteKnight = Piece.createKnight(Color.WHITE, new Position());
		Piece WhitePawn = Piece.createPawn(Color.WHITE, new Position());
		Piece BlackKing = Piece.createKing(Color.BLACK, new Position());
		Piece BlackQueen = Piece.createQueen(Color.BLACK, new Position());
		Piece BlackRook = Piece.createRook(Color.BLACK, new Position());
		Piece BlackBishop = Piece.createBishop(Color.BLACK, new Position());
		Piece BlackKnight = Piece.createKnight(Color.BLACK, new Position());
		Piece BlackPawn = Piece.createPawn(Color.BLACK, new Position());
		
		assertEquals(0, board.getCount(WhiteKing));
		assertEquals(0, board.getCount(WhiteQueen));
		assertEquals(0, board.getCount(WhiteRook));
		assertEquals(0, board.getCount(WhiteBishop));
		assertEquals(0, board.getCount(WhiteKnight));
		assertEquals(0, board.getCount(WhitePawn));
		assertEquals(0, board.getCount(BlackKing));
		assertEquals(0, board.getCount(BlackQueen));
		assertEquals(0, board.getCount(BlackRook));
		assertEquals(0, board.getCount(BlackBishop));
		assertEquals(0, board.getCount(BlackKnight));
		assertEquals(0, board.getCount(BlackPawn));
		
	}
	
	/*
	 *  연습문제 5-7을 위한 테스트 
	 */
	public void testCountScore() throws Exception {
		final double TOLERANCE = 0.05;
		Board board = new Board();
		board.setPiece(Piece.createKing(Color.WHITE, new Position()), new Position("b", 8));
		board.setPiece(Piece.createRook(Color.WHITE, new Position()), new Position("c", 8));
		board.setPiece(Piece.createPawn(Color.WHITE, new Position()), new Position("a", 7));
		board.setPiece(Piece.createPawn(Color.WHITE, new Position()), new Position("c", 7));
		board.setPiece(Piece.createBishop(Color.WHITE, new Position()), new Position("d", 7));
		board.setPiece(Piece.createPawn(Color.WHITE, new Position()), new Position("b", 6));
		board.setPiece(Piece.createQueen(Color.WHITE, new Position()), new Position("e", 6));
		assertEquals(20.0, board.getScore(Color.WHITE), TOLERANCE);
		
		board.setPiece(Piece.createKnight(Color.BLACK, new Position()), new Position("f", 4));
		board.setPiece(Piece.createQueen(Color.BLACK, new Position()), new Position("g", 4));
		board.setPiece(Piece.createPawn(Color.BLACK, new Position()), new Position("f", 3));
		board.setPiece(Piece.createPawn(Color.BLACK, new Position()), new Position("h", 3));
		board.setPiece(Piece.createPawn(Color.BLACK, new Position()), new Position("f", 2));
		board.setPiece(Piece.createPawn(Color.BLACK, new Position()), new Position("g", 2));
		board.setPiece(Piece.createRook(Color.BLACK, new Position()), new Position("e", 1));
		board.setPiece(Piece.createKing(Color.BLACK, new Position()), new Position("f", 1));
		assertEquals(19.5, board.getScore(Color.BLACK), TOLERANCE);
	}
	
	/*
	 *  연습문제 5-8을 위한 테스트
	 */
	public void testOrderPieces() {
		Board board = new Board();
	
		List<Piece> orderedPiece_White = null;
		List<Piece> orderedPiece_Black = null;
		
		Piece WhiteKing = Piece.createKing(Color.WHITE, new Position());		
		Piece WhiteQueen = Piece.createQueen(Color.WHITE, new Position());
		Piece WhiteRook = Piece.createRook(Color.WHITE, new Position());
		Piece WhiteBishop = Piece.createBishop(Color.WHITE, new Position());
		//Piece WhiteKnight = Piece.createKnight(Color.WHITE, new Position());
		Piece WhitePawn = Piece.createPawn(Color.WHITE, new Position());
		Piece BlackKing = Piece.createKing(Color.BLACK, new Position());
		Piece BlackQueen = Piece.createQueen(Color.BLACK, new Position());
		Piece BlackRook = Piece.createRook(Color.BLACK, new Position());
		//Piece BlackBishop = Piece.createBishop(Color.BLACK, new Position());
		Piece BlackKnight = Piece.createKnight(Color.BLACK, new Position());
		Piece BlackPawn = Piece.createPawn(Color.BLACK, new Position());
		
		board.setPiece(Piece.createKing(Color.WHITE, new Position()), new Position("b", 8));
		board.setPiece(Piece.createRook(Color.WHITE, new Position()), new Position("c", 8));
		board.setPiece(Piece.createPawn(Color.WHITE, new Position()), new Position("a", 7));
		board.setPiece(Piece.createPawn(Color.WHITE, new Position()), new Position("c", 7));
		board.setPiece(Piece.createBishop(Color.WHITE, new Position()), new Position("d", 7));
		board.setPiece(Piece.createPawn(Color.WHITE, new Position()), new Position("b", 6));
		board.setPiece(Piece.createQueen(Color.WHITE, new Position()), new Position("e", 6));
		
		board.setPiece(Piece.createKnight(Color.BLACK, new Position()), new Position("f", 4));
		board.setPiece(Piece.createQueen(Color.BLACK, new Position()), new Position("g", 4));
		board.setPiece(Piece.createPawn(Color.BLACK, new Position()), new Position("f", 3));
		board.setPiece(Piece.createPawn(Color.BLACK, new Position()), new Position("h", 3));
		board.setPiece(Piece.createPawn(Color.BLACK, new Position()), new Position("f", 2));
		board.setPiece(Piece.createPawn(Color.BLACK, new Position()), new Position("g", 2));
		board.setPiece(Piece.createRook(Color.BLACK, new Position()), new Position("e", 1));
		board.setPiece(Piece.createKing(Color.BLACK, new Position()), new Position("f", 1));
		
		orderedPiece_White = board.orderedPiece(Piece.Color.WHITE);
		orderedPiece_Black = board.orderedPiece(Piece.Color.BLACK);
		
		assertTrue(WhiteQueen.equals(Board.getOrderedPiece(orderedPiece_White, 0)));
		assertTrue(WhiteRook.equals(Board.getOrderedPiece(orderedPiece_White, 1)));
		assertTrue(WhiteBishop.equals(Board.getOrderedPiece(orderedPiece_White, 2)));
		assertTrue(WhitePawn.equals(Board.getOrderedPiece(orderedPiece_White, 3)));
		assertTrue(WhitePawn.equals(Board.getOrderedPiece(orderedPiece_White, 4)));
		assertTrue(WhitePawn.equals(Board.getOrderedPiece(orderedPiece_White, 5)));
		assertTrue(WhiteKing.equals(Board.getOrderedPiece(orderedPiece_White, 6)));
		
		assertTrue(BlackQueen.equals(Board.getOrderedPiece(orderedPiece_Black, 0)));
		assertTrue(BlackRook.equals(Board.getOrderedPiece(orderedPiece_Black, 1)));
		assertTrue(BlackKnight.equals(Board.getOrderedPiece(orderedPiece_Black, 2)));
		assertTrue(BlackPawn.equals(Board.getOrderedPiece(orderedPiece_Black, 3)));
		assertTrue(BlackPawn.equals(Board.getOrderedPiece(orderedPiece_Black, 4)));
		assertTrue(BlackPawn.equals(Board.getOrderedPiece(orderedPiece_Black, 5)));
		assertTrue(BlackPawn.equals(Board.getOrderedPiece(orderedPiece_Black, 6)));
		assertTrue(BlackKing.equals(Board.getOrderedPiece(orderedPiece_Black, 7)));	
	}
	
	/*
	public void testMovePiece() throws Exception {
		Board board = new Board();
		Piece king = Piece.createKing(Color.WHITE, new Position());
		board.setPiece(king, new Position("a", 1));
		board.movePiece("a1", "a2");
		
		Piece target = board.getPiece("a2");
		assertTrue(Piece.createKing(Color.WHITE).equals(target));
	}
	*/
}

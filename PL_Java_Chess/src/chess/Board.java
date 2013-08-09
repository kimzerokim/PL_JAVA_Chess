package chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pieces.Blank;
import pieces.Piece;
import pieces.Piece.Color;
import pieces.Position;

public class Board {
	private ArrayList<ArrayList<Piece>> rows;
	
	private List<Piece> orderedPieces_white = null;
	private List<Piece> orderedPieces_black = null;

	public Board() {
		initializeEmpty();
	}

	public boolean isEmpty() {
		Blank blank = Piece.createBlank(new Position(1,1));
		for (ArrayList<Piece> row : rows) {
			for (Piece piece : row)
				if (!piece.equals(blank))
					return false;
		}
		return true;
	}

	public void initializeEmpty() {
		rows = new ArrayList<ArrayList<Piece>>();
		
		for (int i = 1; i < 9; i++)
			rows.add(createBlankRow(i));
	}
	
	public void initialize() {
		rows = new ArrayList<ArrayList<Piece>>();
		
		rows.add(createWhiteRow());
		rows.add(createPawnRow(Color.WHITE));		
		for (int i=3; i<7; i++)
			rows.add(createBlankRow(i));
		rows.add(createPawnRow(Color.BLACK));
		rows.add(createBlackRow());
	}	

	private ArrayList<Piece> createWhiteRow() {
		ArrayList<Piece> arr = new ArrayList<Piece>(8);
		Color color = Color.WHITE;

		arr.add(Piece.createRook(color, new Position("a",1)));
		arr.add(Piece.createKnight(color, new Position("b",1)));
		arr.add(Piece.createBishop(color, new Position("c",1)));
		arr.add(Piece.createQueen(color, new Position("d",1)));
		arr.add(Piece.createKing(color, new Position("e",1)));
		arr.add(Piece.createBishop(color, new Position("f",1)));
		arr.add(Piece.createKnight(color, new Position("g",1)));
		arr.add(Piece.createRook(color, new Position("h",1)));

		return arr;
	}

	private ArrayList<Piece> createBlackRow() {
		ArrayList<Piece> arr = new ArrayList<Piece>(8);
		Color color = Color.BLACK;

		arr.add(Piece.createRook(color, new Position("a",8)));
		arr.add(Piece.createKnight(color, new Position("b",8)));
		arr.add(Piece.createBishop(color, new Position("c",8)));
		arr.add(Piece.createQueen(color, new Position("d",8)));
		arr.add(Piece.createKing(color, new Position("e",8)));
		arr.add(Piece.createBishop(color, new Position("f",8)));
		arr.add(Piece.createKnight(color, new Position("g",8)));
		arr.add(Piece.createRook(color, new Position("h",8)));

		return arr;
	}
	
	private ArrayList<Piece> createPawnRow(Color color) {
		ArrayList<Piece> arr = new ArrayList<Piece>(8);;
		
		for (int i = 0; i < 8; i++) {
			arr.add(Piece.createPawn(color, new Position(i,2)));
		}
		
		return arr;		
	}
	
	private ArrayList<Piece> createBlankRow(int rowNum) {
		ArrayList<Piece> arr = new ArrayList<Piece>(8);
		
		for (int i = 1; i < 9; i++) {
			arr.add(Piece.createBlank(new Position(i, rowNum)));
		}
		
		return arr;
	}
	
	public String getRow(int rowNum) {
		StringBuilder rowInfo = new StringBuilder();
		ArrayList<Piece> row = rows.get(rowNum);

		for (int i = 0; i < 8; i++) {
			rowInfo.append(row.get(i).getRepresentation());
		}

		return rowInfo.toString();
	}
	
	public int getCount(Piece tarPiece) {
		int count = 0;
		for (ArrayList<Piece> row : rows) {
			for (Piece piece : row) {
				if (piece.equals(tarPiece))
					count++;
			}
		}
		return count;
	}
	
	public double getScore(Color color) {
		double score = 0;
		for (ArrayList<Piece> row : rows) {
			for (Piece piece : row) {
				if (piece.getColor() == color)
					score += piece.getScore();
			}
		}
		score += countPawnBonus(color);
		return score;
	}
	
	/**
	 * 
	 * @param color
	 * @return 같은 줄에 폰이 있는지 확인하여 점수를 다르게 반환한다
	 */
	private double countPawnBonus(Piece.Color color) {
		final double minusRate = -0.5;
		double retBonus = 0.0;

		ArrayList<Integer> storage = new ArrayList<Integer>(8);
		for (int i = 0; i < 8; i++)
			storage.add(new Integer(0));

		// count pawn number by column
		final Piece pawn = Piece.createPawn(color, new Position());
		for (ArrayList<Piece> row : rows) {
			for (int i = 0; i < row.size(); i++) {
				Piece piece = row.get(i);
				if (piece.equals(pawn)) {
					Integer count = storage.get(i);
					storage.set(i, new Integer(count + 1));
				}
			}
		}

		// calculate
		for (Integer count : storage)
			if (count >= 2)
				retBonus += (minusRate * count);

		return retBonus;
	}
	
	public void setPiece(Piece piece, Position position) {
		int x = position.getX();
		int y = position.getY();
		piece.setPosition(position);
		rows.get(y).set(x, piece);
	}
	
	public Piece getPiece(Position position) {
		int x = position.getX();
		int y = position.getY();
		return rows.get(y).get(x);
	}
	
	public List<Piece> orderedPiece(Color color) {
		switch (color) {
		case WHITE:
			orderedPieces_white = new ArrayList<Piece>();
			orderedPieces_white = collectPieces(color);
			orderList(orderedPieces_white);
			return orderedPieces_white;
		case BLACK:
			orderedPieces_black = new ArrayList<Piece>();
			orderedPieces_black = collectPieces(color);
			orderList(orderedPieces_black);
			return orderedPieces_black;
		default : return null;
		}
	}

	public List<Piece> collectPieces(Color color) {
		ArrayList<Piece> retList = new ArrayList<Piece>();	
		Piece blankPiece = Piece.createBlank(new Position());
		for (ArrayList<Piece> row : rows) {
			for (Piece piece : row) {
				if(!piece.equals(blankPiece) && piece.getColor() == color) //아무것도 아닌 피스와 비교하여 유의미한 피스를 모두 리스트에 추가 
					retList.add(piece);
			}
		}
		return retList;
	}

	public void orderList(List<Piece> list) {
		Collections.sort(list);
	}
	
	public static Piece getOrderedPiece(List<Piece> list, int i) {
		return list.get(i);
	}
	
	public void print() {
		StringBuilder sb = new StringBuilder();
		for (ArrayList<Piece> row : rows) {
			StringBuilder tempSb = new StringBuilder();
			for (Piece piece : row)
				tempSb.append(piece.getRepresentation());
			tempSb.append(System.lineSeparator());
			sb = tempSb.append(sb);
		}
		System.out.println(sb.toString());
	}
	
	/*
	public void movePiece(Position source, Position target) {
		Piece srcPiece = getPiece(source);
		Piece tarPiece = getPiece(target);
		Piece tempPiece = srcPiece.clone();
		
		srcPiece = tarPiece.clone();
		tarPiece = tempPiece;
		
		setPiece(srcPiece, target);
		setPiece(tarPiece, source);
	}
	*/

}

package com.example.auro.lib.models.pieces;

import com.example.auro.lib.constants.Team;
import com.example.auro.lib.models.board.Board;
import com.example.auro.lib.models.cell.Cell;
import com.example.auro.lib.models.point.Point;

public class Pawn extends Piece {
	
	private boolean isFirstMove = true;
	
	public Pawn(Team teamType) {
		super(teamType);
	}

	@Override
	public Boolean validateMove(Board board, Point from, Point to) {
		
		int sourceRow = from.getRow().intValue();
		int sourceColumn = from.getColumn().intValue();
		
		int destinationRow = to.getRow().intValue();
		int destinationColumn = to.getColumn().intValue();
		
		// Restricting backward movement
		if (destinationRow < sourceRow) {
			return Boolean.FALSE;
		}
		
		int rowDiff = destinationRow - sourceRow;
		int colDiff = destinationColumn - sourceColumn;
		if (colDiff < 0) {
			colDiff = colDiff * (-1);
		}
		
		// The Pawn can move one or two spaces forward during its first move
		// ONLY if there's no piece in front of it
		if (isFirstMove && rowDiff >= 0 && rowDiff <=2 && colDiff == 0) {
			int num = rowDiff;
			int nextRow = sourceRow + 1;
			while (num != 0) {
				Point pointToNextCell = new Point(nextRow, sourceColumn);
				Cell nextCell = board.getCellAtPoint(pointToNextCell).get();
				Piece piece = nextCell.getOccupyingPiece();
				if (piece != null) {
					return Boolean.FALSE;
				}
				nextRow++;
				num--;
			}
			isFirstMove = false;
			return Boolean.TRUE;
		}
		
		// The Pawn can move diagonally forward if the occupying piece at the destination cell
		// is an opponent of the Pawn
		if (colDiff == 1 && rowDiff == 1) {
			Cell destCell = board.getCellAtPoint(to).get();
			Piece piece = destCell.getOccupyingPiece();
			if (piece != null && !piece.getTeam().equals(this.getTeam())) {
				if (isFirstMove) {
					isFirstMove = false;
				}
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		}
		
		// The Pawn can move forward only if there's no other piece occupying it
		if (rowDiff == 1 && colDiff == 0) {
			Cell destCell = board.getCellAtPoint(to).get();
			Piece piece = destCell.getOccupyingPiece();
			if (piece == null) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;		
	}

	@Override
	protected Piece makeMove(Board board, Point from, Point to) {
		Piece capturedPiece = null;
		
		Cell sourceCell = board.getCellAtPoint(from).get();
		Cell destinationCell = board.getCellAtPoint(to).get();
		
		if (destinationCell.getOccupyingPiece() != null) {
			capturedPiece = destinationCell.getOccupyingPiece();
		}
		
		destinationCell.setOccupyingPiece(sourceCell.getOccupyingPiece());
		destinationCell.setUnderAttack(Boolean.FALSE);
		sourceCell.setOccupyingPiece(null);
	}
	
	
}

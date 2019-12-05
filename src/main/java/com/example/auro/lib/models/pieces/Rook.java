package com.example.auro.lib.models.pieces;

import com.example.auro.lib.constants.Team;
import com.example.auro.lib.models.board.Board;
import com.example.auro.lib.models.cell.Cell;
import com.example.auro.lib.models.point.Point;

public class Rook extends Piece {

	public Rook(Team teamType) {
		super(teamType);
	}

	@Override
	public Boolean validateMove(Board board, Point from, Point to) {
		
		int sourceRow = from.getRow().intValue();
		int sourceCol = from.getColumn().intValue();
		
		int destinationRow = to.getRow().intValue();
		int destinationCol = to.getColumn().intValue();
		
		Cell destinationCell = board.getCellAtPoint(to).get();
		
		int rowDiff = destinationRow - sourceRow;
		int colDiff = destinationCol - sourceCol;
		
		if (rowDiff < 0) {
			rowDiff = rowDiff * (-1);
		}
		
		if (colDiff < 0) {
			colDiff = colDiff * (-1);
		}
		
		if (colDiff == 0) {
			
			// Checking row-wise movement where column is fixed
			// If on the way to destination cell, a piece is encountered, the Rook can't move
			// However, if at the destination there's an opponent piece, the Rook can make a valid move
			
			
			int fixedCol = sourceCol;
			int nextRow = destinationRow < sourceRow ? sourceRow - 1 : sourceRow + 1;
			int num = rowDiff;
			while (num != 0) {
				Point pointToNextCell = new Point(nextRow, fixedCol);
				Cell nextCell = board.getCellAtPoint(pointToNextCell).get();
				Piece piece = nextCell.getOccupyingPiece();
				
				if (piece != null) {
					if (nextCell == destinationCell && !piece.getTeam().equals(this.getTeam())) {
						return Boolean.TRUE;
					}
					return Boolean.FALSE;
				}
				num--;
				nextRow = destinationRow < sourceRow ? sourceRow - 1 : sourceRow + 1;				
			}
			
		} else if (rowDiff == 0) {
			
			/**
			 * Checking the column-wise movement where row is fixed
			 * If on the way to destination cell, a piece is encountered, the Rook can't make a valid move
			 * However, if at the destination cell, an opponent piece is found, the Rook can make a valid move
			 */
			
			int fixedRow = sourceRow;
			int nextCol = destinationCol < sourceCol ? sourceCol - 1 : sourceCol + 1;
			int num = colDiff;
			while (num != 0) {
				Point pointToNextCell = new Point(fixedRow, nextCol);
				Cell nextCell = board.getCellAtPoint(pointToNextCell).get();
				Piece piece = nextCell.getOccupyingPiece();
				
				if (piece != null) {
					if (nextCell == destinationCell && !piece.getTeam().equals(this.getTeam())) {
						return Boolean.TRUE;
					}
					return Boolean.FALSE;
				}
				num--;
				nextCol = destinationCol < sourceCol ? sourceCol - 1 : sourceCol + 1;
			}
			
		} 
		
		return Boolean.FALSE;
		
	}

}

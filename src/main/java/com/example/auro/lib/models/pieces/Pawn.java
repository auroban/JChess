package com.example.auro.lib.models.pieces;

import java.util.LinkedList;
import java.util.Optional;

import com.example.auro.lib.constants.Direction;
import com.example.auro.lib.constants.StartPoint;
import com.example.auro.lib.constants.Team;
import com.example.auro.lib.models.board.Board;
import com.example.auro.lib.models.cell.Cell;
import com.example.auro.lib.models.point.Point;


public class Pawn extends Piece {

	private boolean isFirstMove;

	public Pawn(final Team team, final StartPoint startPoint, final Point currentPoint, final Board board) {
		super(team, startPoint, currentPoint, board);
		isFirstMove = true;
	}

	@Override
	protected void init(final Board board) {

		directionPathMap.put(Direction.FORWARD, new LinkedList<>());
		directionPathMap.put(Direction.DIAGONAL_FORWARD_LEFT, new LinkedList<>());
		directionPathMap.put(Direction.DIAGONAL_FORWARD_RIGHT, new LinkedList<>());

		// For the cell diagonally left of pawn
		int diagonalForwardLeftRow;
		int diagonalForwardLeftCol;

		// For the cell diagonally right of pawn
		int diagonalForwardRightRow;
		int diagonalForwardRightCol;

		// For the 2 cells in front of the pawn
		int forwardRow1, forwardRow2;
		int forwardCol1, forwardCol2;

		int currentRow = getCurrentPoint().getRow();
		int currentCol = getCurrentPoint().getColumn();

		if (getStartPoint().equals(StartPoint.BOTTOM)) {

			forwardRow1 = currentRow - 1;
			forwardCol1 = currentCol;

			forwardRow2 = currentRow - 2;
			forwardCol2 = currentCol;

			diagonalForwardLeftRow = currentRow - 1;
			diagonalForwardLeftCol = currentCol - 1;

			diagonalForwardRightRow = currentRow - 1;
			diagonalForwardRightCol = currentCol + 1;
			
		} else {

			forwardRow1 = currentRow + 1;
			forwardCol1 = currentCol;

			forwardRow2 = currentRow + 2;
			forwardCol2 = currentCol;

			diagonalForwardLeftRow = currentRow + 1;
			diagonalForwardLeftCol = currentCol + 1;

			diagonalForwardRightRow = currentRow + 1;
			diagonalForwardRightCol = currentCol - 1;
		}
		
		Point forwardPoint1 = new Point(forwardRow1, forwardCol1);
		Point forwardPoint2 = new Point(forwardRow2, forwardCol2);
		
		Point diagonalForwardLeftPoint = new Point(diagonalForwardLeftRow, diagonalForwardLeftCol);
		Point diagonalForwardRightPoint = new Point(diagonalForwardRightRow, diagonalForwardRightCol);
		
		initDirectionPoint(forwardPoint1, board, Direction.FORWARD);
		initDirectionPoint(forwardPoint2, board, Direction.FORWARD);
		initDirectionPoint(diagonalForwardLeftPoint, board, Direction.DIAGONAL_FORWARD_LEFT);
		initDirectionPoint(diagonalForwardRightPoint, board, Direction.DIAGONAL_FORWARD_RIGHT);

	}

	@Override
	protected Boolean validateMove(Board board, Point destination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void update(Board board, Point destination) {

	}
	
	private void initDirectionPoint(final Point point, final Board board, final Direction direction) {
		Optional<Cell> cellOptional = board.getCellAtPoint(point);
		if (cellOptional.isPresent()) {
			LinkedList<Long> cellIdList = directionPathMap.get(direction);
			cellIdList.add(cellOptional.get().getId());
		}
	}

//	@Override
//	public Boolean validateMove(Board board, Point from, Point to) {
//		
//		int sourceRow = from.getRow().intValue();
//		int sourceColumn = from.getColumn().intValue();
//		
//		int destinationRow = to.getRow().intValue();
//		int destinationColumn = to.getColumn().intValue();
//		
//		// Restricting backward movement
//		if (destinationRow < sourceRow) {
//			return Boolean.FALSE;
//		}
//		
//		int rowDiff = destinationRow - sourceRow;
//		int colDiff = destinationColumn - sourceColumn;
//		if (colDiff < 0) {
//			colDiff = colDiff * (-1);
//		}
//		
//		// The Pawn can move one or two spaces forward during its first move
//		// ONLY if there's no piece in front of it
//		if (isFirstMove && rowDiff >= 0 && rowDiff <=2 && colDiff == 0) {
//			int num = rowDiff;
//			int nextRow = sourceRow + 1;
//			while (num != 0) {
//				Point pointToNextCell = new Point(nextRow, sourceColumn);
//				Cell nextCell = board.getCellAtPoint(pointToNextCell).get();
//				Piece piece = nextCell.getOccupyingPiece();
//				if (piece != null) {
//					return Boolean.FALSE;
//				}
//				nextRow++;
//				num--;
//			}
//			isFirstMove = false;
//			return Boolean.TRUE;
//		}
//		
//		// The Pawn can move diagonally forward if the occupying piece at the destination cell
//		// is an opponent of the Pawn
//		if (colDiff == 1 && rowDiff == 1) {
//			Cell destCell = board.getCellAtPoint(to).get();
//			Piece piece = destCell.getOccupyingPiece();
//			if (piece != null && !piece.getTeam().equals(this.getTeam())) {
//				if (isFirstMove) {
//					isFirstMove = false;
//				}
//				return Boolean.TRUE;
//			}
//			return Boolean.FALSE;
//		}
//		
//		// The Pawn can move forward only if there's no other piece occupying it
//		if (rowDiff == 1 && colDiff == 0) {
//			Cell destCell = board.getCellAtPoint(to).get();
//			Piece piece = destCell.getOccupyingPiece();
//			if (piece == null) {
//				return Boolean.TRUE;
//			}
//		}
//		return Boolean.FALSE;		
//	}
//
//	@Override
//	protected Piece makeMove(Board board, Point from, Point to) {
//		Piece capturedPiece = null;
//		
//		Cell sourceCell = board.getCellAtPoint(from).get();
//		Cell destinationCell = board.getCellAtPoint(to).get();
//		
//		if (destinationCell.getOccupyingPiece() != null) {
//			capturedPiece = destinationCell.getOccupyingPiece();
//		}
//		
//		destinationCell.setOccupyingPiece(sourceCell.getOccupyingPiece());
//		destinationCell.setUnderAttack(Boolean.FALSE);
//		sourceCell.setOccupyingPiece(null);
//	}

}

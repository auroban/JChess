package com.example.auro.lib;

import com.example.auro.lib.constants.CellType;
import com.example.auro.lib.constants.Team;
import com.example.auro.lib.exceptions.InvalidMoveException;
import com.example.auro.lib.exceptions.NoCellFoundException;
import com.example.auro.lib.exceptions.NoPieceFoundException;
import com.example.auro.lib.models.board.Board;
import com.example.auro.lib.models.cell.Cell;
import com.example.auro.lib.models.pieces.Piece;
import com.example.auro.lib.models.point.Point;
import com.example.auro.lib.service.gamemanager.GameManager;

import lombok.NonNull;

public class Game implements GameManager {

	private Board board;
	
	public Game() {
		board = new Board();
	}

	public CellType getCellTypeAtPoint(@NonNull final Point point) throws NoCellFoundException {
		Cell cell = board.getCellAtPoint(point).orElseThrow(() -> {
			return new NoCellFoundException("No cell found at " + point);
		});
		return cell.getCellType();
	}

	public Team getTeamOfPieceAtPoint(@NonNull final Point point) throws NoCellFoundException {
		Cell cell = board.getCellAtPoint(point).orElseThrow(() -> {
			return new NoCellFoundException("No cell found at " + point);
		});
		
		Piece piece = cell.getOccupyingPiece();
		return piece.getTeam() != null ? piece.getTeam() : null;
	}

	public Piece move(@NonNull final Point from, @NonNull final Point to) throws InvalidMoveException, NoCellFoundException, NoPieceFoundException {
		
		Cell sourceCell = board.getCellAtPoint(from).orElseThrow(() -> {
			return new NoCellFoundException("No cell found at " + from);
		});
		
		Cell destinationCell = board.getCellAtPoint(to).orElseThrow(() -> {
			return new NoCellFoundException("No cell found at " + to);
		});
		
		Piece piece = sourceCell.getOccupyingPiece();
		if (piece == null) {
			throw new NoPieceFoundException("No Piece found at " + from + ". Please select a point with a piece");
		}
		return piece.move(board, from, to);
		
	}

}

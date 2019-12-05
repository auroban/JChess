package com.example.auro.lib.models.pieces;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.auro.lib.constants.Direction;
import com.example.auro.lib.constants.StartPoint;
import com.example.auro.lib.constants.Team;
import com.example.auro.lib.exceptions.InvalidMoveException;
import com.example.auro.lib.models.board.Board;
import com.example.auro.lib.models.cell.Cell;
import com.example.auro.lib.models.point.Point;

import lombok.Getter;
import lombok.NonNull;

public abstract class Piece {
	
	private static final Logger LOGGER = LogManager.getLogger(Piece.class);

	@Getter
	private Team team;

	@Getter
	private StartPoint startPoint;

	@Getter
	private Point currentPoint;

	protected Map<Direction, LinkedList<Long>> directionPathMap;
	protected Map<Long, LinkedList<Long>> pathMap;

	public Piece(@NonNull final Team team, @NonNull final StartPoint startPoint, final @NonNull Point currentPoint, final @NonNull Board board) {
		this.team = team;
		this.startPoint = startPoint;
		this.currentPoint = currentPoint;
		directionPathMap = new HashMap<>();
		pathMap = new HashMap<>();
		init(board);
	}

	public Optional<Piece> move(@NonNull final Board board, @NonNull final Point destination) throws InvalidMoveException {
		if (!basicValidation(board, destination)) {
			throw new InvalidMoveException("Move failed at basic validation");
		}
		if (!validateMove(board, destination)) {
			throw new InvalidMoveException("Move failed at piece-specific validation");
		}
		
		Cell destinationCell = board.getCellAtPoint(destination).get();
		Piece piece = destinationCell.getOccupyingPiece();
		destinationCell.setOccupyingPiece(this);
		currentPoint = destination;
		update(board, destination);
		return Optional.ofNullable(piece);
	}

	protected abstract void init(final Board board);
	protected abstract Boolean validateMove(final Board board, final Point destination);
	protected abstract void update(final Board board, final Point destination);
	
	private boolean basicValidation(final Board board, final Point destination) {
		int maxGrid = board.getBoardLength();
		int destRow = destination.getRow();
		int destCol = destination.getColumn();
		if (!(destRow >= 0 && destRow < maxGrid)) {
			LOGGER.error("Invalid destination row");
			return false;
		}
		if (!(destCol >= 0 && destCol < maxGrid)) {
			LOGGER.error("Invalid destination column");
			return false;
		}
		return true;
	}

}

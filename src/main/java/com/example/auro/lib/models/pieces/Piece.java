package com.example.auro.lib.models.pieces;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.example.auro.lib.constants.Direction;
import com.example.auro.lib.constants.StartPoint;
import com.example.auro.lib.constants.Team;
import com.example.auro.lib.exceptions.InvalidMoveException;
import com.example.auro.lib.models.board.Board;
import com.example.auro.lib.models.point.Point;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public abstract class Piece {
	
	@Getter
	private Team team;
	
	@Getter
	private StartPoint startPoint;
	
	@Getter
	@Setter
	private Point currentLocation;
	
	protected Map<Direction, LinkedList<Long>> directionPathMap;
	protected Map<Long, LinkedList<Long>> pathMap;
	
	
	public Piece(@NonNull final Team team, @NonNull final StartPoint startPoint) {
		this.team = team;
		this.startPoint = startPoint;
		directionPathMap = new HashMap<>();
		pathMap = new HashMap<>();
	}
	
	
	protected abstract Boolean validateMove(final Board board, final Point destination); 
	protected abstract Piece makeMove(final Board board, final Point destination);
	
	public Piece move(final Board board, final Point destination) throws InvalidMoveException {
		return null;
	}
}

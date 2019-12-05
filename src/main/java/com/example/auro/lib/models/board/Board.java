package com.example.auro.lib.models.board;

import java.util.Optional;
import java.util.Random;

import com.example.auro.lib.constants.CellType;
import com.example.auro.lib.constants.Team;
import com.example.auro.lib.models.cell.Cell;
import com.example.auro.lib.models.point.Point;

import lombok.Getter;

public class Board {

	private static final int DEFAULT_BOARD_LENGTH = 8;
	private static final CellType[] CELL_TYPES = { CellType.WHITE, CellType.BLACK };
	private static final Team[] TEAM_TYPES = { Team.WHITE, Team.BLACK };

	@Getter
	private Integer boardLength;

	private Cell[][] board;
	private CellType cellType;
	private Team teamType;

	public Board() {
		this(DEFAULT_BOARD_LENGTH);
	}

	public Board(final int boardLength) {
		this.boardLength = boardLength;
		board = new Cell[boardLength][boardLength];
		cellType = CELL_TYPES[new Random(CELL_TYPES.length).nextInt()];
		teamType = TEAM_TYPES[new Random(TEAM_TYPES.length).nextInt()];
		initBoard();
	}

	public Optional<Cell> getCellAtPoint(final Point point) {
		if (point != null && point.getRow() >= 0 && point.getRow() < boardLength && point.getColumn() >= 0
				&& point.getColumn() < boardLength) {
			return Optional.of(board[point.getRow()][point.getColumn()]);
		}
		return Optional.empty();

	}

	private void initBoard() {
		for (int i = 0; i < boardLength; i++) {
			for (int j = 0; j < boardLength; j++) {
				Cell cell = new Cell(getAlteringCellType());
				
			}
		}
	}
	
	private CellType getAlteringCellType() {
		if (cellType.equals(CellType.WHITE)) {
			cellType = CellType.BLACK;
		} else {
			cellType = CellType.WHITE;
		}
		return cellType;
	}

}

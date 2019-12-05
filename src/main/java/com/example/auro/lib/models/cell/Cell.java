package com.example.auro.lib.models.cell;

import com.example.auro.lib.constants.CellType;
import com.example.auro.lib.models.pieces.Piece;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Cell {
	
	@NonNull
	@Getter
	private final Long id;
	
	@NonNull	
	@Getter
	private final CellType cellType;
	
	@Getter
	@Setter
	private Piece occupyingPiece;
	
	@Getter
	@Setter
	private Boolean underAttack;

}

package com.example.auro.lib.service.gamemanager;

import com.example.auro.lib.constants.CellType;
import com.example.auro.lib.constants.Team;
import com.example.auro.lib.models.pieces.Piece;
import com.example.auro.lib.models.point.Point;

public interface GameManager {
	
	public CellType getCellTypeAtPoint(final Point point) throws Exception;
	public Team getTeamOfPieceAtPoint(final Point point) throws Exception;
	public Piece move(final Point from, final Point to) throws Exception; 

}

package chess;

import java.util.ArrayList;

public class Rook extends Piece {
	public Rook(String name, ChessBoard board, Color player) {
		super(name, board, player);
	}

	public ArrayList<X_Y> getPossibleMoveX_Y() {
		ArrayList<X_Y> coordinates = new ArrayList<X_Y>();
		int rookx = this.x;
		int rooky = this.y;
		int startcoordx = rookx + 1;
		int startcoordy = rooky + 1;
		int index;
		index = startcoordx;
		while (index < 8) {
			if (Valid_Move_Array(coordinates, index, rooky)) {
				break;
			}
			index=index+1;
		}
		index = startcoordy;
		while (index < 8) {
			if (Valid_Move_Array(coordinates, rookx, index)) {
				break;
			}
			index=index+1;
		}
		index = startcoordx - 2;
		while (index >= 0) {
			if (Valid_Move_Array(coordinates, index, rooky)) {
				break;
			}
			index=index-1;
		}
		index = startcoordy - 2;
		while (index >= 0) {
			if (Valid_Move_Array(coordinates, rookx, index)) {
				break;
			}
			index=index-1;
		}
		return coordinates;
	}
}
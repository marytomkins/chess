package chess;

import java.util.ArrayList;

public class Bishop extends Piece {
	public Bishop(String name, ChessBoard board, Color player) {
		super(name, board, player);
	}

	public ArrayList<X_Y> getPossibleMoveX_Y() {
		ArrayList<X_Y> coordinates = new ArrayList<X_Y>();
		int bishopx = this.x;
		int bishopy = this.y;
		int index, indey;
		index = bishopx - 1;
		indey = bishopy + 1;
		while (index >= 0 && indey < 8) {
			if (Valid_Move_Array(coordinates, index, indey)) {
				break;
			}
			index=index-1;
			indey=indey+1;
		}

		index = bishopx + 1;
		indey = bishopy + 1;
		while (index < 8 && indey < 8) {
			if (Valid_Move_Array(coordinates, index, indey)) {
				break;
			}
			index=index+1;
			indey=indey+1;
		}

		index = bishopx - 1;
		indey = bishopy - 1;
		while (index >= 0 && indey >= 0) {
			if (Valid_Move_Array(coordinates, index, indey)) {
				break;
			}
			index=index-1;
			indey=indey-1;
		}

		index = bishopx + 1;
		indey = bishopy - 1;
		while (index < 8 && indey >= 0) {
			if (Valid_Move_Array(coordinates, index, indey)) {
				break;
			}
			index=index+1;
			indey=indey-1;
		}
		return coordinates;
	}
}
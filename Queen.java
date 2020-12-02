package chess;

import java.util.ArrayList;


public class Queen extends Piece {
	public Queen(String name, ChessBoard board, Color player) {
		super(name, board, player);
	}

	public ArrayList<X_Y> getPossibleMoveX_Y() {
		ArrayList<X_Y> coordinates = new ArrayList<X_Y>();
		int queenx = this.x;
		int queeny = this.y;
		int index, indey;
		index = queenx - 1;
		indey = queeny + 1;
		
		while (index >= 0 && indey < 8) {
			if (Valid_Move_Array(coordinates, index, indey)) {
				break;
			}
			index=index-1;
			indey++;
		}

		index = queenx + 1;
		indey = queeny + 1;
		while (index < 8 && indey < 8) {
			if (Valid_Move_Array(coordinates, index, indey)) {
				break;
			}
			index=index+1;
			indey++;
		}

		index = queenx + 1;
		while (index < 8) {
			if (Valid_Move_Array(coordinates, index, queeny)) {
				break;
			}
			index=index+1;
		}

		index = queeny + 1;
		while (index < 8) {
			if (Valid_Move_Array(coordinates, queenx, index)) {
				break;
			}
			index=index+1;
		}

		index = queenx - 1;
		indey = queeny - 1;
		while (index >= 0 && indey >= 0) {
			if (Valid_Move_Array(coordinates, index, indey)) {
				break;
			}
			index=index-1;
			indey=indey-1;
		}

		index = queenx + 1;
		indey = queeny - 1;
		while (index < 8 && indey >= 0) {
			if (Valid_Move_Array(coordinates, index, indey)) {
				break;
			}
			index=index+1;
			indey=indey-1;
		}

		index = queenx - 1;
		while (index >= 0) {
			if (Valid_Move_Array(coordinates, index, queeny)) {
				break;
			}
			index=index-1;
		}

		index = queeny - 1;
		while (index >= 0) {
			if (Valid_Move_Array(coordinates, queenx, index)) {
				break;
			}
			index=index-1;
		}

		return coordinates;
	}
}
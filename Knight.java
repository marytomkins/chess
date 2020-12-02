package chess;

import java.util.ArrayList;

public class Knight extends Piece {
	public Knight(String name, ChessBoard board, Color player) {
		super(name, board, player);
	}

	public ArrayList<X_Y> getPossibleMoveX_Y() {
		ArrayList<X_Y> coordinates = new ArrayList<X_Y>();
		int a = this.x - 2;
		int b = this.y + 1;
			Valid_Move_Array(coordinates, a, b);
			int c = this.x - 1;
			int d = this.y + 2;
			Valid_Move_Array(coordinates, c, d);
			int e = this.x + 1;
			int f = this.y + 2;
			Valid_Move_Array(coordinates, e, f);
			int g = this.x + 2;
			int h = this.y + 1;
			Valid_Move_Array(coordinates, g, h);
			int i = this.x - 2;
			int j = this.y - 1;
			Valid_Move_Array(coordinates, i, j);
			int k = this.x - 1;
			int l = this.y - 2;
			Valid_Move_Array(coordinates, k, l);
			int m = this.x + 1;
			int n = this.y - 2;
			Valid_Move_Array(coordinates, m, n);
			int o = this.x + 2;
			int p = this.y - 1;
			Valid_Move_Array(coordinates, o, p);
		return coordinates;
	}
}
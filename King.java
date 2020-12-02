package chess;

import java.util.ArrayList;

public class King extends Piece {
	public King(String name, ChessBoard board, Color player) {
		super(name, board, player);
		if (player != Color.B) {
			if (board.White_king() == null) {
				board.set_wk(this);
			}
		} else {
			if (board.bk() == null) {
				board.set_bk(this);
			}
		}
	}

	public boolean Check4Check() {
		ArrayList<Piece> potential_checks;
		if (this.player != Color.W) {
			potential_checks = this.board.get_whites();
		} else {
			potential_checks = this.board.get_blacks();
		}
		for (Piece potential_killer : potential_checks) {
			ArrayList<X_Y> valid_coords = potential_killer.getPossibleMoveX_Y();
			for (X_Y temp : valid_coords) {
				if (temp.Y() == this.y) {
					if (temp.X() == this.x) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public ArrayList<X_Y> getPossibleMoveX_Y() {
		ArrayList<X_Y> valid_coords = new ArrayList<X_Y>();
		int kingx = this.x;
		int kingy = this.y;
		int a = kingx - 1;
		int b = kingx + 1;
		Valid_Move_Array(valid_coords, a, kingy);
		int c = kingy - 1;
		Valid_Move_Array(valid_coords, b, c);
		int d = kingy + 1;
		Valid_Move_Array(valid_coords, a, d);
		Valid_Move_Array(valid_coords, b, kingy);
		Valid_Move_Array(valid_coords, kingx, d);
		Valid_Move_Array(valid_coords, b, d);
		Valid_Move_Array(valid_coords, a, c);
		Valid_Move_Array(valid_coords, kingx, c);
		return valid_coords;
	}

}
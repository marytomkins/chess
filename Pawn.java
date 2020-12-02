package chess;

import java.util.ArrayList;

public class Pawn extends Piece {
	private boolean virgin;

	public Pawn(String name, ChessBoard new_board, Color player) {
		super(name, new_board, player);
		this.virgin = true;
	}

	public boolean first_time() {
		return this.virgin;
	}

	public void set_first_pawn(boolean flag) {
		this.virgin = flag;
	}

	public ArrayList<X_Y> getPossibleMoveX_Y() {
		ArrayList<X_Y> possible_coords = new ArrayList<X_Y>();
		ChessBoard new_board = this.getBoard();
		int pawnx = this.x;
		int pawny = this.y;
		int possible_move = (this.player == Color.W) ? 1 : -1;
		if (pawny + possible_move >= 8 || pawny + possible_move < 0) {
            return possible_coords;
        }
		if (new_board.getPieceAtX_Y(pawnx, pawny + possible_move) == null) {
			possible_coords.add(new X_Y(pawnx, pawny + possible_move));
		}

		if (this.virgin) {
			if (new_board.getPieceAtX_Y(pawnx, pawny + possible_move) != null) {
				// invalid move
			} else {
				if (new_board.getPieceAtX_Y(pawnx, pawny + possible_move * 2) != null) {
					// invalid move 2

				} else {
					possible_coords.add(new X_Y(pawnx, pawny + possible_move * 2));
				}
			}
		}
		if (new_board.getPieceAtX_Y(pawnx - 1, pawny + possible_move) == null) {
			// invalid move

		} else {
			if (new_board.getPieceAtX_Y(pawnx - 1, pawny + possible_move).player == this.player) {
				// invalid move 2

			} else {
				possible_coords.add(new X_Y(pawnx - 1, pawny + possible_move));

			}
		}

		if (new_board.getPieceAtX_Y(pawnx + 1, pawny + possible_move) == null) {
			// invalid
		} else {
			if (new_board.getPieceAtX_Y(pawnx + 1, pawny + possible_move).player == this.player) {
				//invalid 2
			} else {
				possible_coords.add(new X_Y(pawnx + 1, pawny + possible_move));

			}
		}

		return possible_coords;
	}

	public boolean setX_Y(int x, int y) {
		  if (this.x == -1 || this.y == -1) { // first time init
	            this.virgin = true;
	        } else {
			this.virgin = false;
	        }
		return super.setX_Y(x, y);
	}

	public boolean flag_safe(int x, int y) {
		return super.setX_Y(x, y);
	}
}
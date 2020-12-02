package chess;

import java.util.ArrayList;
import chess.Color;

public abstract class Piece {
	protected ChessBoard board;
	protected Color player;
	String piece_name;
	String color;
	int x;
	int y;

	public Piece(String piece_name, ChessBoard board, Color player) {
		this.board = board;
		this.piece_name = piece_name;
		this.x = -1;
		this.y = -1;
		this.player = player;
		this.board.add_Piece(this);
	}

	public String getpiece_name() {
		return piece_name;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public boolean setX_Y(int x, int y) {
		if (x < 0) {
			return false;
		}
		if (x >= 8) {
			return false;
		}
		if (y < 0) {
			return false;
		}
		if (y >= 8) {
			return false;
		}

		if (this.board.getPieceAtX_Y(x, y) == null) {
		} else {
			return false;

		}

		if (this.x == -1 || this.y == -1) {

		} else {
			this.removeSelf();

		}

		this.x = x;
		this.y = y;

		this.board.setPieceAtX_Y(this, x, y);
		return true;
	}

	public void removeSelf() {
		this.board.removePiece(this);
		this.x = -1;
		this.y = -1;
	}

	public ChessBoard getBoard() {
		return this.board;
	}

	public Color getColor() {
		return this.player;
	}

	public String getPiece_name() {
		return this.piece_name;
	}

	public boolean Valid_Move_Array(ArrayList<X_Y> coordinates, int x, int y) {
		if (x < 0) {
			return false;
		}
		if (x >= 8) {
			return false;
		}
		if (y < 0) {
			return false;
		}
		if (y >= 8) {
			return false;
		}

		if (this.board.getPieceAtX_Y(x, y) == null) {
			coordinates.add(new X_Y(x, y));
			return false;
		} else if (this.board.getPieceAtX_Y(x, y).player != this.player) {
			coordinates.add(new X_Y(x, y));
			return true;
		} else {
			return true;
		}
	}

	public boolean kysmove(int xloc, int yloc) {
		Color current_player = this.player;
		boolean is_suicide = false;
		int kx = this.x;
		int ky = this.y;
		Piece king = (current_player == Color.W ? this.board.White_king() : this.board.bk());
		Piece remove_piece = this.board.getPieceAtX_Y(xloc, yloc);
		ArrayList<Piece> opponent_pieces = (current_player == Color.W ? this.board.get_blacks()
				: this.board.get_whites());

		if (remove_piece == null) {
		} else {
			remove_piece.removeSelf();
		}
		if (!this.piece_name.equals("pawn")) {
			this.setX_Y(xloc, yloc);
		} else {
			((Pawn) this).flag_safe(xloc, yloc);
		}

		for (Piece opponent_piece : opponent_pieces) {

			if (opponent_piece.getx() == -1 || opponent_piece.gety() == -1) {
				continue;
			}
			ArrayList<X_Y> coordinates = opponent_piece.getPossibleMoveX_Y();
			for (X_Y coord : coordinates) {
				if (coord.Y() != king.gety()) {

				} else {
					if (coord.X() != king.getx()) {
					} else {
						is_suicide = true;
						break;
					}
				}
			}
		}
		if (!this.piece_name.equals("pawn")) {
			this.setX_Y(kx, ky);

		} else {
			((Pawn) this).flag_safe(kx, ky);

		}
		if (remove_piece == null) {

		} else {
			if (!remove_piece.getPiece_name().equals("pawn")) {
				remove_piece.setX_Y(xloc, yloc);

			} else {
				((Pawn) remove_piece).flag_safe(xloc, yloc);

			}
		}
		return is_suicide;
	}

	public abstract ArrayList<X_Y> getPossibleMoveX_Y();
}
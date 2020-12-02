package chess;


import java.util.ArrayList;

public class ChessBoard {
	protected int width = 8;
	protected int height = 8;
	private Piece tiles[][];
	protected Piece king1;
	protected Piece king2;
	private ArrayList<Piece> W_pieces;
	private ArrayList<Piece> B_pieces;
	protected int turns;

	public ChessBoard() {
		this.width = 8;
		this.height = 8;
		this.tiles = new Piece[8][];
		int i = 0;
		while (i < 8) {
			this.tiles[i] = new Piece[8];
			i++;
		}
		this.W_pieces = new ArrayList<Piece>();
		this.B_pieces = new ArrayList<Piece>();
		this.king1 = null;
		this.king2 = null;
		this.turns = 0;
	}

	public void add_Piece(Piece piece) {
		if (piece.getColor() != Color.W) {
			this.B_pieces.add(piece);
		} else {
			this.W_pieces.add(piece);
		}
	}

	public ArrayList<Piece> get_whites() {
		return this.W_pieces;
	}

	public ArrayList<Piece> get_blacks() {
		return this.B_pieces;
	}

	public void set_wk(Piece piece) {
		this.king1 = piece;
	}

	public Piece getPieceAtX_Y(int x, int y) {
		if (x < 0) {
			return null;
		}
		if (x >= 8) {
			return null;
		}
		if (y < 0) {
			return null;
		}
		if (y >= 8) {
			return null;
		}
		return this.tiles[y][x];
	}

	public Piece bk() {
		return this.king2;
	}

	public void set_bk(Piece piece) {
		this.king2 = piece;
	}

	public Piece White_king() {
		return this.king1;
	}

	public Color get_current_color() {
        if (this.turns % 2 == 0){
            return Color.W; // White
        }
        return Color.B; // Black
	}

	public void setPieceAtX_Y(Piece p, int x, int y) {
		this.tiles[y][x] = p;
	}

	public void removePiece(Piece p) {
		int x = p.getx();
		int y = p.gety();
		this.tiles[y][x] = null;
	}

	public void i_t() {
		this.turns++;
	}

	public int getTurns() {
		return this.turns;
	}

	public boolean n_a(Color player) {
        ArrayList<Piece> player_pieces = ((player == Color.W) ? this.get_whites() : this.get_blacks());
	        for(Piece p : player_pieces){ 
	            if(p.getx() == -1 || p.gety() == -1)
	                continue;;
	            ArrayList<X_Y> coords = p.getPossibleMoveX_Y();
	            for(X_Y coord : coords) {
	                if (p.kysmove(coord.X(), coord.Y()) != false){ 
	                }else {
	                    return false; 
	                }
	            }
	        }
	        return true;
	    }

	public void makeboard() {
		Piece wr, wn, wq, wk, wb, wp, br, bn, bq, bk, bb, bp;

		wr = new Rook("wR", this, Color.W);
		wr.setX_Y(0, 0);

		wn = new Knight("wN", this, Color.W);
		wn.setX_Y(1, 0);

		wb = new Bishop("wB", this, Color.W);
		wb.setX_Y(2, 0);

		wq = new Queen("wQ", this, Color.W);
		wq.setX_Y(3, 0);

		wk = new King("wK", this, Color.W);
		wk.setX_Y(4, 0);

		wb = new Bishop("wB", this, Color.W);
		wb.setX_Y(5, 0);

		wn = new Knight("wN", this, Color.W);
		wn.setX_Y(6, 0);

		wr = new Rook("wR", this, Color.W);
		wr.setX_Y(7, 0);

		wp = new Pawn("wp", this, Color.W);
		wp.setX_Y(0, 1);

		wp = new Pawn("wp", this, Color.W);
		wp.setX_Y(1, 1);

		wp = new Pawn("wp", this, Color.W);
		wp.setX_Y(2, 1);

		wp = new Pawn("wp", this, Color.W);
		wp.setX_Y(3, 1);

		wp = new Pawn("wp", this, Color.W);
		wp.setX_Y(4, 1);

		wp = new Pawn("wp", this, Color.W);
		wp.setX_Y(5, 1);

		wp = new Pawn("wp", this, Color.W);
		wp.setX_Y(6, 1);

		wp = new Pawn("wp", this, Color.W);
		wp.setX_Y(7, 1);

		br = new Rook("bR", this, Color.B);
		br.setX_Y(0, 7);

		bn = new Knight("bN", this, Color.B);
		bn.setX_Y(1, 7);

		bb = new Bishop("bB", this, Color.B);
		bb.setX_Y(2, 7);

		bq = new Queen("bQ", this, Color.B);
		bq.setX_Y(3, 7);

		bk = new King("bK", this, Color.B);
		bk.setX_Y(4, 7);

		bb = new Bishop("bB", this, Color.B);
		bb.setX_Y(5, 7);

		bn = new Knight("bN", this, Color.B);
		bn.setX_Y(6, 7);

		br = new Rook("bR", this, Color.B);
		br.setX_Y(7, 7);

		bp = new Pawn("bp", this, Color.B);
		bp.setX_Y(0, 6);

		bp = new Pawn("bp", this, Color.B);
		bp.setX_Y(1, 6);

		bp = new Pawn("bp", this, Color.B);
		bp.setX_Y(2, 6);

		bp = new Pawn("bp", this, Color.B);
		bp.setX_Y(3, 6);

		bp = new Pawn("bp", this, Color.B);
		bp.setX_Y(4, 6);

		bp = new Pawn("bp", this, Color.B);
		bp.setX_Y(5, 6);

		bp = new Pawn("bp", this, Color.B);
		bp.setX_Y(6, 6);

		bp = new Pawn("bp", this, Color.B);
		bp.setX_Y(7, 6);
	}

}
package chess;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Chess {
	static ChessBoard game = new ChessBoard();
	static boolean gamenotover = true;

	public static String c_or_s() {

		if (game.n_a(game.getTurns() % 2 == 0 ? Color.W : Color.B)) {
			King king = (game.getTurns() % 2 == 0) ? (King) game.White_king() : (King) game.bk();
			if (king != null) {
			} else {
				return null;
			}
			if (!king.Check4Check()) {
				System.out.println("Stalemate");
				System.out.println("draw");
				gamenotover = false;
				return "stalemate";
			} else {
				if (game.get_current_color() != Color.W) {
					System.out.println("Checkmate");
					System.out.println("White wins");
				} else {
					System.out.println("Checkmate");
					System.out.println("Black wins");
				}
				gamenotover = false;
				return "checkmate";

			}
		}
		return null;
	}

	public static int coordtranslate(String input) {
		int index = 0;
		String coordtranslate[] = new String[8];
		coordtranslate[0] = "a";
		coordtranslate[1] = "b";
		coordtranslate[2] = "c";
		coordtranslate[3] = "d";
		coordtranslate[4] = "e";
		coordtranslate[5] = "f";
		coordtranslate[6] = "g";
		coordtranslate[7] = "h";
		for (index = 0; index < 8; index = index + 1) {
			if (coordtranslate[index].equals(input)) {
				return index;
			}
		}
		return index;
	}

	public static boolean k_i_c(Color player) {
		Piece king;
		if (player != Color.W) {
			king = game.bk();

		} else {
			king = game.White_king();

		}
		return ((King) king).Check4Check();
	}

	public static String p_t_e(Piece cp, int x, int y) {
		ArrayList<X_Y> coordinates = cp.getPossibleMoveX_Y();
		for (X_Y coord : coordinates) {
			//System.out.println(coord.X() + " " + coord.Y());
			if (cp.kysmove(coord.X(), coord.Y())) {
				continue;
			}
			if (coord.Y() == y) {
				if (coord.X() == x) {
					cp.setX_Y(x, y);
					cp = null;
					if (k_i_c(game.get_current_color()) != true) {
					} else {
						System.out.println("Check");
					}
					game.i_t();

					return "VALID";
				}
			}
		}
		return "INVALID";
	}

	public static String p_t_o(Piece cp, Piece op) {
		ArrayList<X_Y> coordinates = cp.getPossibleMoveX_Y();
		if (coordinates == null) {
			return "INVALID";

		} else {

			for (X_Y coord : coordinates) {
				if (cp.kysmove(coord.X(), coord.Y())) {
					continue;
				}
				if (coord.Y() != op.gety()) {

				} else {
					if (coord.X() == op.getx()) {
						game.removePiece(op);
						op.removeSelf();
						cp.setX_Y(coord.X(), coord.Y());
						if (k_i_c(game.get_current_color()) != true) {
						} else {
							System.out.println("Check!");
						}
						game.i_t();

						return "Valid";
					}
				}
			}

		}
		return "Nope";
	}

	public static String castle(int x, int y, Piece king) {
		String type;
		if (king.getColor() == Color.W) {
			type = "wR";
		} else {
			type = "bR";
		}
		if (x < 0 || y < 0 || x >= 8 || y >= 8)
			return "false";
		if (game.getPieceAtX_Y(x, y) == null) {
			return "true";
		} else if ((game.getPieceAtX_Y(x, y).player == king.player)
				&& (game.getPieceAtX_Y(x, y).getpiece_name().equals(type))) {
			String coord = Integer.toString(x) + " " + Integer.toString(y);
			return (coord);
		} else
			return "false";
	}

	public static String checkmove(String input) {
		String file1 = input.substring(0, 1);
		String rank1 = input.substring(1, 2);
		String file2 = input.substring(3, 4);
		String rank2 = input.substring(4, 5);
		int x, y, x2, y2;
		Piece p, location, rook;
		x = coordtranslate(file1);
		y = Integer.parseInt(rank1);
		y = y - 1;
		p = game.getPieceAtX_Y(x, y);

		if (p == null) {
			return "INVALID";
		}
		x2 = coordtranslate(file2);
		y2 = Integer.parseInt(rank2);
		y2 = y2 - 1;

		if (p.getpiece_name().equals("wp") || p.getpiece_name().equals("bp")) {
			if ((Integer.parseInt(rank2) == 7) || (Integer.parseInt(rank2) == 1)) {
				if (input.length() == 7) {
					String promotion = input.substring(6, 7);
					if (promotion.equals("N")) {
						p.removeSelf();
						if (p.getColor() == Color.W) {
							p = new Knight("wN", game, Color.W);
							p.setX_Y(x2, y2);
						} else {
							p = new Knight("bN", game, Color.B);
							p.setX_Y(x2, y2);
						}
					}
					if (promotion.equals("R")) {
						p.removeSelf();
						if (p.getColor() == Color.W) {
							p = new Rook("wR", game, Color.W);
							p.setX_Y(x2, y2);
						} else {
							p = new Rook("bR", game, Color.B);
							p.setX_Y(x2, y2);
						}
					}
					if (promotion.equals("B")) {
						p.removeSelf();
						if (p.getColor() == Color.W) {
							p = new Bishop("wB", game, Color.W);
							p.setX_Y(x2, y2);
						} else {
							p = new Bishop("bB", game, Color.B);
							p.setX_Y(x2, y2);
						}
					}
				} else {// queen
					p.removeSelf();
					if (p.getColor() == Color.W) {
						p = new Queen("wQ", game, Color.W);
						p.setX_Y(x2, y2);
					} else {
						p = new Queen("bQ", game, Color.B);
						p.setX_Y(x2, y2);
					}
				}
				game.i_t();

				return "VALID";
			}
		}
		boolean castlelegal = true;
		if (p.getpiece_name().equals("wK") || p.getpiece_name().equals("bK")) {
			int rookx = -1;
			int rookoldx = -1;
			int rookoldy = -1;
			int rooky = -1;

			if ((Math.abs(x - x2) > 1) || (Math.abs(y - y2) > 1)) {
				if ((Math.abs(x - x2) > 1)) {
					if (x > x2) {
						for (int i = x - 1; i >= 0; i--) {
							String result = castle(i, y, p);
							if (result.equals("false")) {
								castlelegal = false;
								break;
							} else if (!result.equals("true")) {
								rookx = x2 + 1;
								rooky = y2;
								rookoldx = Integer.parseInt(result.substring(0, 1));
								rookoldy = Integer.parseInt(result.substring(2, 3));
							}
						}
					} else {
						for (int i = x + 1; i < 8; i++) {
							String result = castle(i, y, p);
							if (result.equals("false")) {
								castlelegal = false;
								break;
							} else if (!result.equals("true")) {
								rookx = x2 - 1;
								rooky = y2;
								rookoldx = Integer.parseInt(result.substring(0, 1));
								rookoldy = Integer.parseInt(result.substring(2, 3));
							}
						}
					}
				} else {

					if (y > y2) {
						for (int i = y - 1; i >= 0; i--) {
							String result = castle(i, y, p);
							if (result.equals("false")) {
								castlelegal = false;
								break;
							} else if (!result.equals("true")) {
								rooky = y2 + 1;
								rookx = x2;
								rookoldx = Integer.parseInt(result.substring(0, 1));
								rookoldy = Integer.parseInt(result.substring(2, 3));
							}
						}
					} else {
						for (int i = y + 1; i < 8; i++) {
							String result = castle(i, y, p);
							if (result.equals("false")) {
								castlelegal = false;
								break;
							} else if (!result.equals("true")) {
								rooky = y2 - 1;
								rookx = x2;
								;
								rookoldx = Integer.parseInt(result.substring(0, 1));
								rookoldy = Integer.parseInt(result.substring(2, 3));
							}
						}
					}
				}

				if (castlelegal == true) {
					p.setX_Y(x2, y2);
					rook = game.getPieceAtX_Y(rookoldx, rookoldy);
					rook.setX_Y(rookx, rooky);
					game.i_t();

					return "VALID";
				}
			}

		}
		location = game.getPieceAtX_Y(x2, y2);
		if (location != null) {
			if (p.getColor() != game.get_current_color()) {
				return "INVALID";
			}
			if (location.getColor() == game.get_current_color()) {

				return "INVALID";
			} else {
				if (p_t_o(p, location).equals("INVALID")) {

					return "INVALID";
				}
			}
		} else {
			if (p.getColor() != game.get_current_color()) {
				return "INVALID";
			}
			if (p_t_e(p, x2, y2).equals("INVALID")) {

				return "INVALID";
			}
		}
		return "Nope";

	}

	public static void printBoard(ChessBoard board) {
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j <= 7; j++) {
				if (board.getPieceAtX_Y(j, i) != null) {
					System.out.print(board.getPieceAtX_Y(j, i).getPiece_name() + " ");
				} else {
					if (((j + i) % 2 == 0)) {
						System.out.print("   ");
					} else {
						System.out.print("## ");
					}
				}

				if (j == 7) {
					System.out.print(i + 1);
				}

			}
			System.out.println();
		}
		System.out.println(" a  b  c  d  e  f  g  h");
	}

	public static void main(String[] args) throws IOException {

		game.makeboard();
		printBoard(game);
		System.out.println();

		Scanner in = new Scanner(System.in);
		String move;
		boolean drawOffered = false;
		while (gamenotover == true) {
			boolean movecomplete = false;
			while (movecomplete == false) {
				System.out.print("White's move: ");

				move = in.nextLine();
				if (move.compareTo("draw") == 0) {
					if (drawOffered == true) {
						gamenotover = false;
						break;
					}
				}

				if (move.compareTo("resign") == 0) {
					System.out.println("Black wins");
					movecomplete = true;
					return;
				}
				if (move.length() > 8) {
					if (move.substring(6).equals("draw?")) {
						drawOffered = true;
					}
				}
				if (!(checkmove(move).equals("INVALID"))) {
					movecomplete = true;
				} else {
					System.out.println("Illegal move, try again");
				}

			}
			if (c_or_s() != null) {

			}
			if (gamenotover == false) {
				return;
			}
			System.out.println();
			printBoard(game);
			System.out.println();

			movecomplete = false;
			while (movecomplete == false) {
				System.out.print("Black's move: ");

				move = in.nextLine();

				if (move.compareTo("draw") == 0) {
					if (drawOffered == true) {
						gamenotover = false;
						break;
					}
				}
				if (move.compareTo("resign") == 0) {
					System.out.println("White wins");
					return;
				}
				if (move.length() > 8) {
					if (move.substring(6).equals("draw?")) {
						drawOffered = true;
					}
				}
				if (!(checkmove(move).equals("INVALID"))) {
					movecomplete = true;
				} else {
					System.out.println("Illegal move, try again");
				}
			}
			c_or_s();
			if (gamenotover == false) {
				return;
			}
			System.out.println();
			printBoard(game);
			System.out.println();

		}
	}
}
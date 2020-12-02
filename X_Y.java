package chess;
public class X_Y {
	private int x; 
	private int y; 
	public X_Y() {
		this.y = -1;
		this.x = -1;
	}
	public X_Y(int x, int y) {
		this.y = y;
		this.x = x;
	}
	public int Y() {
		return this.y;
	}
	public int X() {
		return this.x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setX(int x) {
		this.x = x;
	}
}


public class Square {
	
	private boolean isBlack;
	private boolean occupied;
	private Checker checker;
	private int x;
	private int y;

	public Square(boolean isBlack, int x, int y) {
		this.isBlack = isBlack;
		occupied = false;
		this.x = x;
		this.y = y;
	}
	
	public bool IsBlack() {
		return isBlack;
	}
	
	public bool IsOccupied() {
		return occupied;
	}
	
	public Checker GetChecker() {
		return checker;
	}
	
	public void SetChecker(Checker checker) {
		occupied = true;
		this.checker = checker;
	}
	
	public void RemoveChecker() {
		occupied = false;
		checker = null;
	}

}


public class Square {
	
	private bool isBlack;
	private bool occupied;
	private Checker checker;

	public Square(bool color) {
		this.isBlack = isBlack;
		occupied = false;
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

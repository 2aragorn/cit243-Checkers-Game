
public class Square {
	
	private String color;
	private bool occupied;
	private Checker checker;

	public Square(String color) {
		this.color = color;
		occupied = false;
	}
	
	public String GetColor() {
		return color;
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

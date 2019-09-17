public class Checker {
	
	private String player;
	private bool crowned;


	public Checker(String player) {
		this.player = player;
		crowned = false;
	}

	public String GetPlayer() {
		return player;
	}

	public String GetCrowned() {
		return crowned;
	}

	public void SetCrowned() {
		crowned = true;
	}
  
}

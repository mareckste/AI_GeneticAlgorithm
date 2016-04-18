package solution;

public class Gene {
	private int turn; //-1 left 1 right
	private int start;
	
	public Gene(int s, int t) {
		this.start = s;
		this.turn = t;
	}

	public int getTurn() {
		return turn;
	}

	public int getStart() {
		return start;
	}
	
	
}

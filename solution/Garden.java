package solution;

public class Garden {
	private int[][] field;
	
	public Garden(int[][] a) {
		this.field = a;
	}
	
	public int[][] getGarden() {
	    return field;
	}
	
	public void showGarden() {
		for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
	}
}

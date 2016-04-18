package solution;

public class Functions {
	public static int[][] arrayCopy(int[][] source) {
		int length = source.length;
		int [][]dest = new int [length][source[0].length];
		
		for (int i = 0; i < length; i++) {
			System.arraycopy(source[i], 0, dest[i], 0, dest[i].length);
		}
		
		return dest;
	}
	
	public static void startingCoordinates(int x, int y, int dir, int start, int height, int width) {
		switch (dir) {
		case 1: x = 0; y = start;
			break;
		case 2: x = start - width; y = width;
			break;
		case 3: x = height - 1; y = Math.abs(start - (2*width + height - 1));
			break;
		case 4: x = Math.abs(start - (2*width + 2*height - 1)); y = 0;
			break;
		
		default: 
			break;
			
		}
	}
	
	public static int calcFitness(int[][] g) {
        int fitness = 0;
        
        for (int i = 0; i < g.length; i++) {
        	for (int j = 0; j < g[0].length; j++)
        		if (g[i][j] > 0) fitness++;
        }
        return fitness;
    }
}

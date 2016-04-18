package solution;



public class Individual {
	private Gene[] genes; //chromosome
	private int fitness;
	
	public Individual(Gene[] g) {
		this.genes = g;
	}
	
	public int[][] plowGarden(Garden ig) {
		int[][] g = Functions.arrayCopy(ig.getGarden());
		int h = g.length; 
		int w = g[0].length;
		int dir = 0, i = 0;
		
		for (Gene gene : genes) {
			int start = gene.getStart();
			int x = 0, y = 0;
			int turn = gene.getTurn();
			int []index;
			
			if (start < w) dir = 1; 										//up -> down
			if ((start >= w) && (start < (w + h))) dir = 2; 				//right -> left
			if ((start >= (w + h)) && (start < (2*w + h))) dir = 3;		// down -> up
			if (start >= (2*w + h)) dir = 4;   							//left -> right
			
			index = Functions.startingCoordinates(x, y, dir, start, h, w);
			x = index[0];
			y = index[1];
			i++;
			
			while (true) {
				//****************
				if (dir == 1 || dir == 3) { //up -> down     down -> up
					if (dir == 1) {
						while (x < h && g[x][y] == 0) { g[x][y] = i; x++; }  
						}
					if (dir == 3) 
						while (x >= 0 && g[x][y] == 0) { g[x][y] = i; x--; }
					
					if (x < 0 || x > h - 1) break; 					//edge
					
					if (g[x][y] != 0) {							//prekazka
						if (dir == 1) {
							if (x - 1 >= 0) {
							g[--x][y] = 0;}
							else{ i--; break;}
						}
						else {
							if (x + 1 < h)
							g[++x][y] = 0;
							else { i--;
								break;
							}
							}
						
						
						if ((dir == 1 && turn == -1) || (dir == 3 && turn == 1)) { 
							if ((y + 1) > (w - 1)) {
								dir = 4;
								continue;
							}
							if (g[x][y+1] == 0) {
								dir = 4;
								continue;
							}
							else {
								if ((y - 1) < 0) {
									dir = 2;
									continue;
								}
								if (g[x][y-1] == 0) {
									dir = 2;
									continue;
								}
								else return g;
							}
						}
						if ((dir == 1 && turn == 1) || (dir == 3 && turn == -1)) { //right
							if ((y - 1) < 0) {
								dir = 2;
								continue;
							}
							if (g[x][y-1] == 0) {
								dir = 2;
								continue;
							}
							else {
								if ((y + 1) > (w - 1)) {
									dir = 4;
									continue;
								}
								if (g[x][y+1] == 0) {
									dir = 4;
									continue;
								}
								else return g;
							}
						}
						
					}
				}
				//*********************
				if (dir == 2 || dir == 4) { //right -> left     left -> right
					if (dir == 2) {
						while (y >= 0 && g[x][y] == 0) { g[x][y] = i; y--; }  
						}
					if (dir == 4) 
						while (y < w && g[x][y] == 0) { g[x][y] = i; y++; }
					
					if (y < 0 || y > w-1) break; 					//edge
					
					if (g[x][y] != 0) {							//prekazka
						if (dir == 4) {
							if (y - 1 >= 0) g[x][--y] = 0;
							else { i--; break;}
						}
						else {
							if (y + 1 < w) g[x][++y] = 0;
							else { i--; break;}
						}
						
						if ((dir == 2 && turn == -1) || (dir == 4 && turn == 1)) { 
							if (x == h - 1) {
								dir = 1;
								continue;
							}
							if (g[x+1][y] == 0) {
								dir = 1;
								continue;
							}
							else {
								if (x == 0) {
									dir = 3;
									continue;
								}
								if (g[x-1][y] == 0) {
									dir = 3;
									continue;
								}
								else return g;
							}
						}
						if ((dir == 2 && turn == 1) || (dir == 4 && turn == -1)) { 
							if (x == 0) {
								dir = 3;
								continue;
							}
							if (g[x-1][y] == 0) {
								dir = 3;
								continue;
							}
							else {
								if (x == h - 1) {
									dir = 1;
									continue;
								}
								if (g[x+1][y] == 0) {
									dir = 1;
									continue;
								}
								else return g;
							}
						}
						
					}
				}
			}			
		}
		
		
		return g;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public Gene[] getGenes() {
		return genes;
	}

	public int getFitness() {
		return fitness;
	}


	
}

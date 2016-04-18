package solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Functions {
	public static int[][] arrayCopy(int[][] source) {
		int length = source.length;
		int [][]dest = new int [length][source[0].length];
		
		for (int i = 0; i < length; i++) {
			System.arraycopy(source[i], 0, dest[i], 0, dest[i].length);
		}
		
		return dest;
	}
	
	public static int[] startingCoordinates(int x, int y, int dir, int start, int height, int width) {
		int [] t = new int[2];
		switch (dir) {
		case 1: x = 0; y = start;
			break;
		case 2: x = start - width; y = width-1;
			break;
		case 3: x = height - 1; y = Math.abs(start - (2*width + height - 1));
			break;
		case 4: x = Math.abs(start - (2*width + 2*height - 1)); y = 0;
			break;
		
		default: 
			break;
			
		}
		t[0] = x;
		t[1] = y;
		
		return t;
	}
	
	public static int calcFitness(int[][] g) {
        int fitness = 0;
        
        for (int i = 0; i < g.length; i++) {
        	for (int j = 0; j < g[0].length; j++)
        		if (g[i][j] > 0) fitness++;
        }
        return fitness;
    }
	
	public static boolean isSolved(int[][] garden) {
		for (int i = 0; i < garden.length; i++) {
			for (int j = 0; j < garden[0].length; j++) {
				if (garden[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	public static ArrayList<Individual> tournament(ArrayList<Individual> prev, int count, int size) {
		ArrayList<Individual> actual = new ArrayList<>();
		ArrayList<Individual> result = new ArrayList<>();
		int bound = prev.size();
		
		for (int i = 0; i < count ; i++){
			actual.clear();
			Individual best = null;
			int rndIndex = new Random().nextInt(bound);
			
			for (int j = 0; j < size; j++) {
				Individual currentI = prev.get(rndIndex);
				if (actual.contains(currentI) == false) actual.add(currentI);
				
				if (best == null) best = currentI;
				else {
					if (best.getFitness() < actual.get(actual.size()-1).getFitness()) {
						best = actual.get(actual.size()-1);
					}
				}
				
			}
			result.add(best);
		}
		
		return result;
	}
	
	public static ArrayList<Individual> roulette(ArrayList<Individual> prev, int count) {
		ArrayList<Individual> list = new ArrayList<>();
		int bound = 0;
		int sum = 0;
		int rNumber;
		
		for (Individual i : prev) {
			bound += i.getFitness();
		}
		
		for (int j = 0; j < count; j++) {
			rNumber = new Random().nextInt(bound);
			for (Individual i : prev) {
				sum += i.getFitness();
				if (sum >= rNumber) {
					list.add(i);
					break;
				}
			}
		}
		return list;
	}
	
	public static ArrayList<Individual> elite(ArrayList<Individual> prev, int count) {
		ArrayList<Individual> list = new ArrayList<>();
		Collections.sort(prev, new IndComparator());
		for (int i = 0; i < count; i++) {
			list.add(prev.get(i));
		}
		return list;
	}
	
	public static void displayPlownGarden(int [][] field) {
		for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.printf("%4d",field[i][j]);
            }
            System.out.println();
        }
        System.out.println();
	}
}

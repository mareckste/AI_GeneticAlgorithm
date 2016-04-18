package solution;

import java.util.ArrayList;
import java.util.Random;

public class Population {
	private ArrayList<Individual> individuals;
	
	public Population(int pSize, int geneLength, int positions) {
		individuals = new ArrayList<Individual>();
		generatePopulation(pSize, geneLength, positions);
	}
	
	public Population(ArrayList<Individual> previousPopulation, int pSize, int geneLength, int positions) {
		individuals = new ArrayList<Individual>();
	}
	
	private void generatePopulation(int pSize, int geneLength, int positions) {
		Gene[] chromosome;
		ArrayList<Integer> c = new ArrayList<Integer>();
		int turn;

		for (int i = 0; i < pSize; i++) {
			 c.clear();
			 chromosome = new Gene[geneLength];
			 for (int j = 0; j < geneLength; j++) {
				 turn = new Random().nextInt(2);
				 if (turn == 1) turn = 1;
				 else turn = -1;
				 Integer t = new Random().nextInt(positions);
				 while (c.contains(t)) t = new Random().nextInt(positions);
				 c.add(t);
				 chromosome[j] = new Gene(c.get(j), turn);
			 }
			individuals.add(i, new Individual(chromosome)); 
		}
	}
	
	public void crossOver(ArrayList<Individual> prev, int pSize, int geneLength, int positions) {
		int p1Index, p2Index;
		Gene[] newChromo, p1, p2;
		
		for (int i = 0; i < pSize; i++) {
			p1Index = new Random().nextInt(pSize);
			p2Index = new Random().nextInt(pSize);
			while (p2Index == p1Index) p2Index = new Random().nextInt(pSize);
			
			p1 = prev.get(p1Index).getGenes();
			p2 = prev.get(p2Index).getGenes();
			newChromo = new Gene[geneLength];
			int x = 0;
			
			while (x < geneLength) {
				int turn = new Random().nextInt(2);
				turn = (turn > 0 ? 1 : -1);
				
				if (new Random().nextInt(100) > 4) //mutation rate 4%
					newChromo[x] = p1[x];
				else 
					newChromo[x] = new Gene(new Random().nextInt(positions), turn);
				x++;
				
				if (new Random().nextInt(100) > 4) 
					newChromo[x] = p2[x];
				else 
					newChromo[x] = new Gene(new Random().nextInt(positions), turn);
				x++;
			}
			
			individuals.add(new Individual(newChromo));
		}
	}
	
	public ArrayList<Individual> tournament(ArrayList<Individual> prev, int count, int size) {
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
					if (best.getFitness() < currentI.getFitness()) { best = currentI; 
				}
				
			}
		}
		
		return result;
	}
	
	public ArrayList<Individual> roulette() {
		ArrayList<Individual> list = new ArrayList<>();
		
		
		return list;
	}

	public ArrayList<Individual> getIndividuals() {
		return individuals;
	}
}

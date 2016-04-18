package solution;

import java.util.ArrayList;

public class Runner {

	private final static int[][] garden = {
			{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
			{0,	0,	0,	0,	0,	-1,	0,	0,	0,	0,	0,	0},
			{0,	-1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
			{0,	0,	0,	0,	-1,	0,	0,	0,	0,	0,	0,	0},
			{0,	0,	-1,	0,	0,	0,	0,	0,	0,	0,	0,	0},
			{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
			{0,	0,	0,	0,	0,	0,	0,	0,	-1,	-1,	0,	0},
			{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
			{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
			{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0}
	};
	
	public static void main(String[] args) {
		Garden g = new Garden(garden);
		int genes = garden.length + garden[0].length + 6;
		int counter = 1;
		int positions = 2*(garden.length + garden[0].length);
		
		Population p = new Population(100, genes, positions);
		
		while (true) {
			ArrayList<Individual> populationList = p.getIndividuals();
			
			for (Individual ind : populationList) {
				int [][] garden = ind.plowGarden(g);
				int fitness;
				
				
				fitness = Functions.calcFitness(garden);
				
				ind.setFitness(fitness);
				if (Functions.isSolved(garden) == true) {
					System.out.println("Solution found on population number: " + counter);
					Functions.displayPlownGarden(garden);
					System.out.println("Fitness:" + fitness + "\n" );
					return;
				}				

			}
			ArrayList<Individual> newGen = Functions.elite(p.getIndividuals(), 20);
			//ArrayList<Individual> tournament = Functions.tournament(p.getIndividuals(), 100, 4);
			ArrayList<Individual> roulette = Functions.roulette(p.getIndividuals(), 80);
			p = new Population(roulette, 80, genes, positions);
			//p = new Population(100, genes, positions);
			counter++;
			p.getIndividuals().addAll(newGen);
		}
	}

}

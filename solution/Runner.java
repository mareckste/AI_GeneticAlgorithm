package solution;

import java.util.ArrayList;
import java.util.Collections;

public class Runner {

	public static void main(String[] args) {
		ArrayList<Individual> l = new ArrayList<>();
		Gene [] g = new Gene[4];
		
		Individual i = new Individual(g);
		i.setFitness(5);
		l.add(i);
		i = new Individual(g);
		i.setFitness(9);
		l.add(i);
		
		i = new Individual(g);
		i.setFitness(4);
		l.add(i);
		
		Collections.sort(l, new IndComparator());
		for (int i1 = 0; i1 < l.size(); i1++) {
			System.out.println(l.get(i1).getFitness());
		}
	}

}

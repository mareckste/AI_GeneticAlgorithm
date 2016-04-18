package solution;

import java.util.Comparator;

public class IndComparator implements Comparator<Individual> {
	@Override
	public int compare(Individual o1, Individual o2) {
		return o2.getFitness() - o1.getFitness();
	}
}

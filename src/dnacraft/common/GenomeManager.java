package dnacraft.common;

public class GenomeManager {
	
	public static Genome mutate(Genome parent1, Genome parent2)
	{
		Genome baby = new Genome();
		
		/**
		 * VERY basic mutation for now!!
		 */
		double parentsMix;
		for (int i = 0; i < parent1.size(); i++) 
		{
			parentsMix = (parent1.get(i) + parent2.get(i)) / 2;
			baby.set(i, (parentsMix + Math.random()) / 2);
		}
		
		return baby;
	}
	
}

package dnacraft.common.evolution.genome;

import dnacraft.common.evolution.Genome;
import dnacraft.common.evolution.TraitManager;

public class GenomeSeeds extends Genome {
	
	public GenomeSeeds () {
		
		addTrait(TraitManager.COLOR_GREEN, 0.8);
		addTrait(TraitManager.COLOR_YELLOW, 0.2);
		addTrait(TraitManager.DROPS_SEEDS, 1);
		addTrait(TraitManager.OFFSPRING_1, 0.5);
		addTrait(TraitManager.OFFSPRING_2, 0.5);
		addTrait(TraitManager.CONSUMES_BONEMEAL, 1);
		addTrait(TraitManager.NEEDS_LIGHT, 0.9);
		addTrait(TraitManager.SIZE_SMALL, 1);
		addTrait(TraitManager.HEALTH_VERY_LOW, 0.5);
		addTrait(TraitManager.HEALTH_LOW, 0.9);
		
	}
	
}

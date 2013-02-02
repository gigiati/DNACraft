package dnacraft.common.evolution.genome;

import dnacraft.common.evolution.Genome;
import dnacraft.common.evolution.TraitManager;

public class GenomePorkCooked extends Genome {
	
	public GenomePorkCooked() {
		addTrait(TraitManager.COLOR_WHITE, 1);
		addTrait(TraitManager.SIZE_SMALL, 1);
		addTrait(TraitManager.FOOD, 0.8);
		addTrait(TraitManager.PIG, 0.5);
	}
}

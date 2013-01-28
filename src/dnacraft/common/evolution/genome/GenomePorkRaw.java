package dnacraft.common.evolution.genome;

import dnacraft.common.evolution.Genome;
import dnacraft.common.evolution.TraitManager;

public class GenomePorkRaw extends Genome {
	
	public GenomePorkRaw()
	{
		addTrait(TraitManager.COLOR_PINK, 0.8);
		addTrait(TraitManager.COLOR_RED, 0.2);
		addTrait(TraitManager.SIZE_SMALL, 1);
		addTrait(TraitManager.FOOD, 0.5);
	}
	
}

package dnacraft.common.evolution.genome;

import dnacraft.common.evolution.Genome;
import dnacraft.common.evolution.TraitManager;

public class GenomeSpider extends Genome {
	
	public GenomeSpider () {
		addTrait(TraitManager.SPIDER, 20);
	}
	
}

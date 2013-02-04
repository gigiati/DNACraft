package dnacraft.common.evolution.materials;

import dnacraft.common.evolution.DNA;
import dnacraft.common.evolution.Genome;
import dnacraft.common.evolution.Trait;

public class DNAPorkRaw extends DNA {
	
	public DNAPorkRaw() {
		
		addGenome(Genome.createGenomeOfType(Genome.BODY_TYPE, Trait.ANIMAL_PIG));
		addGenome(Genome.createGenomeOfType(Genome.HEAD_TYPE, Trait.ANIMAL_PIG));
		addGenome(Genome.createGenomeOfType(Genome.WING_TYPE, Trait.ANIMAL_PIG));
		addGenome(Genome.createGenomeOfType(Genome.ARM_TYPE, Trait.ANIMAL_PIG));
		addGenome(Genome.createGenomeOfType(Genome.LEG_TYPE, Trait.ANIMAL_PIG));
		addGenome(Genome.createGenomeOfType(Genome.TAIL_TYPE, Trait.ANIMAL_PIG));
		
		addGenome(Genome.createGenomeOfType(Genome.AGGRESSION, 0));
		addGenome(Genome.createGenomeOfType(Genome.COLOR, Trait.COLOR_PINK));
		
		addGenome(Genome.createGenomeOfType(Genome.DROP_AMOUNT, 1, 2));
		addGenome(Genome.createGenomeOfType(Genome.DROP_TYPE, Trait.DROP_PORK_RAW));
		
		addGenome(Genome.createGenomeOfType(Genome.OFFSPRING, 1));
		
	}
}
